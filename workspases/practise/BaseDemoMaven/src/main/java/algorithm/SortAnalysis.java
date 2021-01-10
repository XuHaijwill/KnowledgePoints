package algorithm;

import java.util.Arrays;

/**
 * @Author xhj
 * @Description https://www.cnblogs.com/onepixel/p/7674659.html
 * @Date 2020-03-18 22:11
 **/
public class SortAnalysis {

    public static void main(String[] args) {
        int[] arrs = {9, 0, 6, 2, 1, 3, 4, 5, 8, 7};
//        System.out.println(Arrays.toString(bubbleSort(arrs)));
//        System.out.println(Arrays.toString(selectSort(arrs)));
        System.out.println(Arrays.toString(insertionSort(arrs)));
    }

    /**
     * 冒泡排序法
     * 时间复杂度 O（n²）
     *
     * @param arrs
     * @return
     */
    public static int[] bubbleSort(int[] arrs) {
        int length = arrs.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    int temp = arrs[j + 1];
                    arrs[j + 1] = arrs[j];
                    arrs[j] = temp;
                }
            }
        }
        return arrs;
    }

    /**
     * 选择排序法 O（n²）
     *
     * @param arrs
     * @return
     */
    public static int[] selectSort(int[] arrs) {
        int minIndex = 0;
        int temp = 0;
        for (int i = 0; i < arrs.length - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[j] < arrs[minIndex]) {
                    minIndex = j;
                }
            }

            temp = arrs[i];
            arrs[i] = arrs[minIndex];
            arrs[minIndex] = temp;
        }
        return arrs;
    }

    /**
     * 插入排序法
     *
     * @param arrs
     * @return
     */
    public static int[] insertionSort(int[] arrs) {
        for (int i = 1; i < arrs.length; i++) {
            int preIndex = i - 1;
            int current = arrs[i];
            while (preIndex >= 0 && arrs[preIndex] > current) {
                arrs[preIndex + 1] = arrs[preIndex];
                preIndex--;
            }
            arrs[preIndex + 1] = current;

        }
        return arrs;
    }
}
