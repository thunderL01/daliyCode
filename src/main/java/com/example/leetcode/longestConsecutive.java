package com.example.leetcode;

import java.util.HashSet;
import java.util.Set;

public class longestConsecutive {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        
        // Test cases
        int[] nums1 = {100, 4, 200, 1, 3, 2};
        System.out.println("Test 1: " + solution.longestConsecutive(nums1)); // Expected: 4
        
        int[] nums2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        System.out.println("Test 2: " + solution.longestConsecutive(nums2)); // Expected: 9
        
        int[] nums3 = {1, 2, 0, 1};
        System.out.println("Test 3: " + solution.longestConsecutive(nums3)); // Expected: 3
    }
}

class Solution2 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        

        int longest = 0;
        
        for (int num : numSet) {
            // Only start counting if it's the beginning of a sequence
            //判断数组中有没有比num小1的数，如果有，不执行if，没有，那么代表这是起点
            //然后while再判断数组有没有比num大1的数，如果有，执行while，代表现在序列长度增加
            //longest最后取最大的序列长度
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;
                
                while (numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                
                longest = Math.max(longest, currentStreak);
            }
        }
        
        return longest;
    }
}