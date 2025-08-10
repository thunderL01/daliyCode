package com.example.leetcode;

public class moveZeroes {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        
        // 测试方法1
        System.out.println("=== 测试方法1 ===");
        int[] nums1 = {0, 1, 0, 3, 12};
        System.out.print("测试1: ");
        solution.moveZeroes(nums1);
        printArray(nums1);
        
        int[] nums2 = {0};
        System.out.print("测试2: ");
        solution.moveZeroes(nums2);
        printArray(nums2);
        
        int[] nums3 = {1, 0, 2, 0, 3};
        System.out.print("测试3: ");
        solution.moveZeroes(nums3);
        printArray(nums3);
        
        // 测试方法2
        System.out.println("\n=== Test Method2 (Optimized) ===");
        int[] nums4 = {0, 1, 0, 3, 12};
        System.out.print("Test1: ");
        solution.moveZeroes2(nums4);
        printArray(nums4);
        
        int[] nums5 = {0};
        System.out.print("Test2: ");
        solution.moveZeroes2(nums5);
        printArray(nums5);
        
        int[] nums6 = {1, 0, 2, 0, 3};
        System.out.print("Test3: ");
        solution.moveZeroes2(nums6);
        printArray(nums6);
        
        int[] nums7 = {1, 2, 3, 4, 5};
        System.out.print("Test4 (All non-zero): ");
        solution.moveZeroes2(nums7);
        printArray(nums7);
        
        int[] nums8 = {0, 0, 0, 1, 2};
        System.out.print("Test5 (Multiple zeros at front): ");
        solution.moveZeroes2(nums8);
        printArray(nums8);
    }
    
    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

    }
}

class Solution4 {
    //方法1
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                nums[left] = nums[right];
                left++;
            }
        }
        
        for (int i = left; i < nums.length; i++) {
            nums[i] = 0;
            System.out.print(nums[i] + " ");
        }
    }

    //Method2: Two Pointers (Optimized)
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int low = 0;
        
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != 0) {
                // Swap elements at low and fast positions
                int temp = nums[low];
                nums[low] = nums[fast];
                nums[fast] = temp;
                low++;
            }
        }
    }
}
