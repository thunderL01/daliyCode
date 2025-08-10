package com.example.demo;

import java.util.Scanner;

public class 小明找位置 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 读取已排好序的学号列表
        String[] idsStr = sc.nextLine().split(",");
        int[] ids = new int[idsStr.length];
        for (int i = 0; i < idsStr.length; i++) {
            ids[i] = Integer.parseInt(idsStr[i].trim());
        }

        // 读取小明学号
        int xiaomingId = Integer.parseInt(sc.nextLine().trim());

        // 二分查找插入位置
        int left = 0;
        int right = ids.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (ids[mid] < xiaomingId) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        // 输出位置（从1开始计数）
        System.out.println(left + 1);
    }
}

