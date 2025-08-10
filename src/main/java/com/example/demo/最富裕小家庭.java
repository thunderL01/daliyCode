package com.example.demo;

import java.util.Scanner;

public class 最富裕小家庭 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] money = new int[n+1];
        int[] family = new int[n+1];

        for (int i = 1; i < n+1; i++) {
            money[i] = scanner.nextInt();
            family[i] = money[i];
        }

        for (int i = 1; i <= n-1 ; i++) {
            int father = scanner.nextInt();
            int kid = scanner.nextInt();
            family[father]+= money[kid];
        }

        int ans=0;
        for (int i = 1; i <n+1 ; i++) {
            ans = Math.max(family[i],ans);
        }

        System.out.println(ans);
    }
}
