package algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author xhj
 * @Description
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * @Date 2020-03-18 22:11
 **/
public class Solution {

    public static void main(String[] args) {
//        String a = "";
//        System.out.println(a.toLowerCase());
        System.out.println(Arrays.toString(twoSum2(new int[]{2, 7, 11, 15},9)));
    }

    /**
     * 暴力求解法
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;

        for(int i =0;i < n-1;i++){
            for(int j=i+1;j<n;j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }


    /**
     * 使用hash表
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> hashTable = new HashMap<Integer, Integer>();
        for(int i = 0;i < nums.length;i++){
            if(hashTable.containsKey(target - nums[i])){
                return new int[]{hashTable.get(target - nums[i]),i};
            }
            hashTable.put(nums[i],i);
        }
        return null;
    }

}
