package com.lyw.hadoop.arithmetic;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort<T> {

    int[] heapSort(int[] arrs) {
        if (arrs == null || arrs.length == 1) {
            return arrs;
        }

        //构造堆数据
        int index = (arrs.length - 1) >> 1;
        for (int i = index; i >= 0 ; i--) {
            minHeap(arrs, i, arrs.length);
        }

        //交换首位，重新构造对结构（0，i)构造
        for (int i = arrs.length -1; i > 0; i--) {
            swap(arrs, 0, i);
            minHeap(arrs,0,i);
        }

        return arrs;
    }

    /**
     * 大堆排序
     * @param arrs
     * @param index
     * @param length
     */
    private void maxHeap(int[] arrs, int index, int length) {
        int l = (index >> 1) + 1;
        int r = (index >> 1) + 2;
        int max = index;
        if (r < length && arrs[r] > arrs[index]) {
            max = r;
        }
        if (l < length && arrs[max] < arrs[l]) {
            max = l;
        }
        if (max != index) {
            swap(arrs, index, max);
            //防止父级堆调整破坏子堆数据
            maxHeap(arrs,max,length);
        }
    }

    /**
     * 小堆排序
     * @param arrs
     * @param index
     * @param length
     */
    private void minHeap(int[] arrs, int index, int length) {
        int l = (index << 1) + 1;
        int r = (index << 1) + 2;
        int m = index;
        if (r < length && arrs[r] < arrs[index]){
            m = r;
        }
        if (l < length && arrs[l] < arrs[m]){
            m = l;
        }

        if (m != index) {
            swap(arrs, m, index);
            minHeap(arrs, m, length);
        }
    }

    private void swap(int[] arrs, int m, int index) {
        arrs[index] = arrs[index] + arrs[m];
        arrs[m] = arrs[index] - arrs[m];
        arrs[index] = arrs[index] - arrs[m];
    }


    public static void main(String[] args) {
        int[] arr = new int[]{3,5,3,0,8,6,1,5,8,6,2,4,9,4,7,0,1,8,9,7,3,1,2,5,9,7,4,0,2,6};

        new HeapSort<Integer>().heapSort(arr);
        System.err.println(Arrays.toString(arr));
    }
}
