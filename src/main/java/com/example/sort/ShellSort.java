package com.example.sort;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {1,5,2,4,22,13,56,34,21};
        shellSort(arr);
        for (int i : arr) {
            System.out.println("arr[i]=" + i);
        }
    }
    public static void shellSort(int[] arr) {
        int length = arr.length;
        int temp;
        for (int step = length / 2; step >= 1; step /= 2) {
            for (int i = step; i < length; i++) {
                temp = arr[i];
                int j = i - step;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + step] = arr[j];
                    j -= step;
                }
                arr[j + step] = temp;
            }
        }
    }
}
