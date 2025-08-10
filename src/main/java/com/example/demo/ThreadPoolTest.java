package com.example.demo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.RejectedExecutionException;

import lombok.extern.slf4j.Slf4j;
import cn.hutool.core.thread.ThreadUtil;

@Slf4j
public class ThreadPoolTest {

    public static void main(String[] args) {
        // 根据CPU核心数动态配置线程池大小
        int cpuCores = Runtime.getRuntime().availableProcessors();
        int corePoolSize = Math.max(1, cpuCores / 2);
        int maxPoolSize = cpuCores;
        int queueCapacity = 100; // 增加队列容量避免任务被拒绝
        
        log.info("CPU核心数: {}, 核心线程数: {}, 最大线程数: {}", cpuCores, corePoolSize, maxPoolSize);
        
        // 创建命名线程工厂
        ThreadFactory namedThreadFactory = new ThreadFactory() {
            private final AtomicInteger counter = new AtomicInteger(1);
            
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "demo-pool-thread-" + counter.getAndIncrement());
                thread.setUncaughtExceptionHandler((t, e) -> {
                    log.error("线程 {} 发生异常", t.getName(), e);
                });
                return thread;
            }
        };

        // 创建优化后的线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity),
                namedThreadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 添加线程池监控
        monitorThreadPool(threadPoolExecutor);

        // 提交任务示例
        submitTasksWithRetry(threadPoolExecutor);

        // 优雅关闭线程池
        gracefulShutdown(threadPoolExecutor);
    }

    /**
     * 提交任务并带有重试机制
     */
    private static void submitTasksWithRetry(ThreadPoolExecutor executor) {
        for (int i = 1; i <= 5; i++) {
            final int taskNumber = i;
            try {
                executor.execute(() -> {
                    try {
                        log.info("开始执行任务 {}", taskNumber);
                        if (taskNumber == 4) {
                            // 模拟耗时任务
                            ThreadUtil.sleep(5, TimeUnit.SECONDS);
                        } else {
                            ThreadUtil.sleep(1, TimeUnit.SECONDS);
                        }
                        log.info("任务 {} 执行完成", taskNumber);
                    } catch (Exception e) {
                        log.error("任务 {} 执行失败", taskNumber, e);
                        throw e;
                    }
                });
                log.info("任务 {} 已提交到线程池", taskNumber);
            } catch (RejectedExecutionException e) {
                log.error("任务 {} 被拒绝，尝试直接执行", taskNumber);
                // 如果被拒绝，直接在当前线程执行
                new Thread(() -> {
                    try {
                        log.info("直接执行任务 {}", taskNumber);
                        ThreadUtil.sleep(1, TimeUnit.SECONDS);
                    } catch (Exception ex) {
                        log.error("直接执行任务 {} 失败", taskNumber, ex);
                    }
                }, "fallback-thread-" + taskNumber).start();
            }
        }
    }

    /**
     * 监控线程池状态
     */
    private static void monitorThreadPool(ThreadPoolExecutor executor) {
        Thread monitorThread = new Thread(() -> {
            while (!executor.isTerminated()) {
                try {
                    Thread.sleep(1000);
                    log.info("线程池状态 - 活跃线程: {}, 池大小: {}, 队列大小: {}, 已完成任务: {}, 总任务: {}",
                            executor.getActiveCount(),
                            executor.getPoolSize(),
                            executor.getQueue().size(),
                            executor.getCompletedTaskCount(),
                            executor.getTaskCount());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            log.info("线程池已关闭");
        }, "thread-pool-monitor");
        monitorThread.setDaemon(true);
        monitorThread.start();
    }

    /**
     * 优雅关闭线程池
     */
    private static void gracefulShutdown(ThreadPoolExecutor executor) {
        // 注册JVM关闭钩子
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("开始关闭线程池...");
            shutdownThreadPool(executor);
        }));

        // 主线程等待一段时间后关闭
        try {
            Thread.sleep(5000); // 等待任务执行
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        shutdownThreadPool(executor);
    }

    /**
     * 关闭线程池的具体实现
     */
    private static void shutdownThreadPool(ThreadPoolExecutor executor) {
        executor.shutdown(); // 停止接受新任务
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                log.warn("线程池未在指定时间内关闭，强制关闭...");
                executor.shutdownNow(); // 强制关闭
                if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                    log.error("线程池强制关闭失败");
                }
            }
        } catch (InterruptedException e) {
            log.error("线程池关闭被中断", e);
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}