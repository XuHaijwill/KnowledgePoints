package algorithm;

import java.util.Arrays;

/**
 * @Author xhj
 * @Description //TODO
 * @Date 2020-03-18 22:11
 **/
public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums =  {0,1,0,3,12};
        System.out.println(Arrays.toString(moveZeroes(nums)));
    }

    public static  int[] moveZeroes(int[] nums) {
        int index = 0;
        for(int num : nums){
            if(num != 0){
                nums[index++] = num;
            }
        }
        while (index < nums.length){
            nums[index++] = 0;
        }
        return nums;
    }
}
