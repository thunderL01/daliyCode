package com.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class threeSum {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        
        // 测试用例1
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result1 = solution.threeSum(nums1);
        System.out.println("测试用例1结果: " + result1);
        
        // 测试用例2
        int[] nums2 = {0, 1, 1};
        List<List<Integer>> result2 = solution.threeSum(nums2);
        System.out.println("测试用例2结果: " + result2);
        
        // 测试用例3
        int[] nums3 = {0, 0, 0};
        List<List<Integer>> result3 = solution.threeSum(nums3);
        System.out.println("测试用例3结果: " + result3);
    }
}

class Solution3 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 先对数组进行排序
        Arrays.sort(nums);
        
        // 遍历数组，固定第一个数
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的第一个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int left = i + 1;      // 左指针
            int right = nums.length - 1;  // 右指针
            int target = -nums[i];  // 目标和
            
            // 双指针查找
            while (left < right) {
                int sum = nums[left] + nums[right];
                
                if (sum == target) {
                    // 找到满足条件的三元组
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    
                    // 跳过重复的左指针元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的右指针元素
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    
                    // 移动指针
                    /*  为什么找到匹配后要同时移动两个指针：

                1. 当前状态：nums[left] + nums[right] = -nums[i] ✅
                - 找到了一个有效的三元组：(nums[i], nums[left],
                nums[right])
                2. 如果只移动左指针：
                - left++ → 新的 nums[left] ≥ 原来的 nums[left]
                - sum 会变大 → nums[left] + nums[right] > -nums[i] ❌
                3. 如果只移动右指针：
                - right-- → 新的 nums[right] ≤ 原来的 nums[right]
                - sum 会变小 → nums[left] + nums[right] < -nums[i] ❌
                4. 同时移动两个指针：
                - 寻找下一组可能的组合
                - 避免重复使用已经匹配过的元素
                - 继续在剩余的区间内搜索

                关键点：因为数组已排序，每个元素只能用一次，找到匹配后这两个
                位置的元素都已经"用完"了，所以需要同时移动到新的位置继续
                搜索。

                */
                    left++;
                    right--;
                } else if (sum < target) {
                    // 和小于目标，左指针右移
                    left++;
                } else {
                    // 和大于目标，右指针左移
                    right--;
                }
            }
        }
        
        return result;
    }
}