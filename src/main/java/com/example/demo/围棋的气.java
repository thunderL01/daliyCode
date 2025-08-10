package com.example.demo;

import java.util.*;

public class 围棋的气 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 读取黑棋坐标
        String[] blackInput = scanner.nextLine().split(" ");
        // 读取白棋坐标
        String[] whiteInput = scanner.nextLine().split(" ");

        // 存储黑棋坐标的集合
        Set<String> blackSet = new HashSet<>();
        // 存储白棋坐标的集合
        Set<String> whiteSet = new HashSet<>();

        // 解析黑棋坐标
        for (int i = 0; i < blackInput.length; i += 2) {
            int row = Integer.parseInt(blackInput[i]);
            int col = Integer.parseInt(blackInput[i + 1]);
            blackSet.add(row + "," + col);
        }

        // 解析白棋坐标
        for (int i = 0; i < whiteInput.length; i += 2) {
            int row = Integer.parseInt(whiteInput[i]);
            int col = Integer.parseInt(whiteInput[i + 1]);
            whiteSet.add(row + "," + col);
        }

        // 存储黑棋的气点
        Set<String> blackLiberties = new HashSet<>();
        // 存储白棋的气点
        Set<String> whiteLiberties = new HashSet<>();

        // 四个移动方向：上、下、左、右
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 计算黑棋的气
        for (String pos : blackSet) {
            String[] parts = pos.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // 检查是否在棋盘范围内
                if (newRow >= 0 && newRow <= 18 && newCol >= 0 && newCol <= 18) {
                    String newPos = newRow + "," + newCol;
                    // 如果该位置没有棋子，则加入气点集合
                    if (!blackSet.contains(newPos) && !whiteSet.contains(newPos)) {
                        blackLiberties.add(newPos);
                    }
                }
            }
        }

        // 计算白棋的气
        for (String pos : whiteSet) {
            String[] parts = pos.split(",");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // 检查是否在棋盘范围内
                if (newRow >= 0 && newRow <= 18 && newCol >= 0 && newCol <= 18) {
                    String newPos = newRow + "," + newCol;
                    // 如果该位置没有棋子，则加入气点集合
                    if (!blackSet.contains(newPos) && !whiteSet.contains(newPos)) {
                        whiteLiberties.add(newPos);
                    }
                }
            }
        }

        // 输出结果
        System.out.println(blackLiberties.size() + " " + whiteLiberties.size());
    }
}