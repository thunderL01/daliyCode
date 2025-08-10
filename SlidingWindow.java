public class SlidingWindow {

    // 滑动窗口求最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int index = 0;
        
        // 使用双端队列来存储窗口中元素的索引
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            // 移除不在窗口内的元素（从队头移除）
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // 从队尾移除比当前元素小的元素
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            // 将当前元素的索引加入队尾
            deque.offerLast(i);
            
            // 当窗口形成后，开始记录最大值
            if (i >= k - 1) {
                result[index++] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    // 滑动窗口求最小和（长度为k的子数组的最小和）
    public int minWindowSum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return 0;
        }
        
        int n = nums.length;
        int minSum = Integer.MAX_VALUE;
        int currentSum = 0;
        
        // 初始化第一个窗口
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }
        minSum = currentSum;
        
        // 滑动窗口
        for (int i = k; i < n; i++) {
            // 移除窗口最左边的元素，添加新的元素
            currentSum = currentSum - nums[i - k] + nums[i];
            minSum = Math.min(minSum, currentSum);
        }
        
        return minSum;
    }
    
    // 寻找最长子串，其中最多包含k个不同字符
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) {
            return 0;
        }
        
        int left = 0;
        int maxLength = 0;
        Map<Character, Integer> charCount = new HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            
            // 当不同字符数量超过k时，移动左指针
            while (charCount.size() > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
    
    public static void main(String[] args) {
        SlidingWindow solution = new SlidingWindow();
        
        // 测试滑动窗口最大值
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = solution.maxSlidingWindow(nums, k);
        System.out.println("滑动窗口最大值：");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        // 测试滑动窗口最小和
        int[] nums2 = {1, 2, 3, 4, 5};
        int k2 = 2;
        int minSum = solution.minWindowSum(nums2, k2);
        System.out.println("长度为" + k2 + "的子数组最小和：" + minSum);
        
        // 测试最长子串（最多k个不同字符）
        String s = "eceba";
        int k3 = 2;
        int maxLength = solution.lengthOfLongestSubstringKDistinct(s, k3);
        System.out.println("字符串\"" + s + "\"中最多" + k3 + "个不同字符的最长子串长度：" + maxLength);
    }
}