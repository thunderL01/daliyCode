package com.example.sort;

import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {1,7,5,3,2,8,6};
        int [] sortedArr = insertionSort(arr);
        for (int i : sortedArr) {
            System.out.println("sortedArr[i]=" + i);
        }
    }
    public static int[] insertionSort(int[] sourceArray) {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i], j = i;
            while (j > 0 && val < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = val;
        }
        return arr;
    }
}
