package sort;

import java.util.Arrays;

/**
 * Created by Admin on 2018/5/19.
 * 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] a = {20,30,90,40,70,110,60,10,100,50,80};

        //heapSortAsc(a,a.length);
        
        System.out.println("升序排序"+Arrays.toString(a));


        heapSortDesc(a, a.length);

        System.out.println("降序排序"+Arrays.toString(a));
        
    }

    /**
     * 堆排序（从大到小）
     * @param a---待排序数组
     * @param n---数组长度
     */
    private static void heapSortDesc(int[] a, int n) {
        int i, tmp;

        for (i=n/2-1;i>=0;i--){
            minHeapDown(a, i, n-1);
        }

        for (i = n-1; i>0;i--){
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;
            minHeapDown(a, 0, i-1);
        }
    }

    /**
     *
     * @param a
     * @param start
     * @param end
     */
    private static void minHeapDown(int[] a, int start, int end) {
        int c = start;
        int l = 2*c+1;
        int tmp = a[c];

        for (; l <end;c= l, l=2*l+1){
            if (l<end && a[l] > a[l+1]) {
                l++;  //选择左右还在中较小的
            }

            if (tmp<a[l]){
                break;
            } else {
                a[c] = a[l];
                a[l] = tmp;
            }

        }


    }

    /**
     * 堆排序(从小到大)
     * 参数说明
     *   a -- 待排序数组
     *   n --- 数组长度
     * @param a
     * @param n
     */
    private static void heapSortAsc(int[] a, int n) {
        int i, tmp;

        // 从(n/2-1) --->0逐次遍历。遍历之后，得到的数组实际上是一个最大二叉堆
        for (i = n/2 - 1; i>=0;i--) {
            maxHeapDown(a, i, n-1);
        }

        // 从最后一个元素开始对序列进行调整，不断的缩小调整范围直到第一个元素
        for (i = n-1; i>0; i--) {
            // 交换a[0]和a[i].交换后，a[i]是[0...i]中最大的
            tmp = a[0];
            a[0] = a[i];
            a[i] = tmp;

            // 调整a[0...i-1],使得a[0...i-1]仍然是一个最大堆
            // 即，保证a[i-1]是a[0...i-1]中的最大值
            maxHeapDown(a, 0, i-1);
        }


    }

    /**
     *
     * @param a
     * @param start
     * @param end
     */
    private static void maxHeapDown(int[] a, int start, int end) {
        int c = start;  // 当前节点位置
        int l = 2*c + 1; // 左孩子位置
        int tmp = a[c];  // 当前current节点大小

        for (;l<=end; c=l, l = 2*l+1) {
            // l 是左孩子， l+1是右孩子
             if (l<end && a[l] < a[l+1]){
                 l++;  // 左右孩子中选择较大者
             }
            if (tmp >= a[l])
                break;  // 调整结束
            else {
                a[c] = a[l];  // 交换值
                a[l] = tmp;
            }
        }
    }
}
