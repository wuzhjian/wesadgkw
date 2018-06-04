package sort;

import java.util.Arrays;

/**
 * Created by Admin on 2018/5/19.
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] n = {37, 40, 38, 42, 461, 5, 7, 9, 12};

        quickSort(n);

        System.out.println(Arrays.toString(n));
        
    }

    private static void quickSort(int[] n) {
        if (isEmpty(n)){
            return;
        }

        quickSort(n, 0, n.length-1);
    }

    private static void quickSort(int[] n, int l, int h) {
        if (isEmpty(n))
            return;
        while (l<h){
            int pivote = partion(n, l, h);
            quickSort(n, l, pivote-1);
            quickSort(n, pivote+1, h);
        }
    }

    private static int partion(int[] n, int start, int end) {
        int tmp = n[start];
        while (start < end) {
            while (n[end] >= tmp && start<end) {
                end--;
            }
            if (start<end) {
                n[start++] = n[end];
            }

            while (n[start] < tmp && start< end){
                start++;
            }
            if (start<end) {
                n[end--] = n[start];
            }
        }
        n[start] = tmp;
        return start;
    }

    private static boolean isEmpty(int[] n) {
        return n==null || n.length==0;
    }




}
