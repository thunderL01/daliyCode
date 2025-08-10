package com.example.demo;

import java.util.Scanner;


public class 运输时间 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int n = scanner.nextInt();

        int[] speed = new int[m];
        double ans = 0;

        for (int i = 0; i <m ; i++) {
            speed[i] = scanner.nextInt();

            ans = Math.max(ans,n/(double)speed[i]+i);
        }

        // 到达时刻 - 出发时刻 = 路上花费的时间
        double cost = ans - (m - 1);

        System.out.println(cost); // 实际考试没有精度问题，可以直接输出cost，可以满分



    }
}
