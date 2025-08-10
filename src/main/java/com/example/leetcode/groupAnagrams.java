package com.example.leetcode;

import java.util.*;

public class groupAnagrams
{
    public static void main(String[] args) {
        Solution1 solution = new Solution1();
        
        // 测试用例
        String[] strs1 = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println("测试1: " + solution.groupAnagrams(strs1));
        
        String[] strs2 = {""};
        System.out.println("测试2: " + solution.groupAnagrams(strs2));
        
        String[] strs3 = {"a"};
        System.out.println("测试3: " + solution.groupAnagrams(strs3));
    }
}

class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            // Method 1: Sort string as key（字符串如果是字母异位词，那么提前字符后再排序肯定是一样的key）
            char[] charArray = s.toCharArray();//将字符串的字符提取出来
            Arrays.sort(charArray);//对字符进行排序
            String key = new String(charArray);//将排序后的字符作为字符串key
            
            // Method 2: Character count as key (more efficient)
            // 创建key，value ->[  key，[""]  ]，将对应字符串key的字符放入value
            
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        
        return new ArrayList<>(map.values());
    }

}

