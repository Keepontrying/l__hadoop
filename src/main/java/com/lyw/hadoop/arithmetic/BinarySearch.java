package com.lyw.hadoop.arithmetic;

import java.util.Arrays;

/**
 * An object that maps keys to values.  A map cannot contain duplicate keys;
 * each key can map to at most one value.
 *
 * <p>This interface takes the place of the <tt>Dictionary</tt> class, which
 * was a totally abstract class rather than an interface.
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @param <K>
 * @param <V>
 * @author Josh Bloch
 * @see HashMap
 * @since 1.2
 */
public class BinarySearch {

    public int bSearch(int[] arrs,int key) {
        int left = 0;
        int right = arrs.length - 1;
        while (left <= right) {
            int mid = (right - left) >>> 1;
            if (key > arrs[mid]) {
                left = mid + 1;
            } else if (key < arrs[mid]) {
                right = mid - 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 简单选择排序:从 n-i+1个记录中找出最小记录，和i+1交换位置
     */
    public int[] selectSort(int[] arrs) {
        int len = arrs.length;
        for (int i = 0; i < len; i++) {
            int index = i;
            for (int j = i + 1; j < arrs.length; j++) {
                if (arrs[j] < arrs[index]) {
                    index = j;
                }
            }
            if (index != i) {
                arrs[index] = arrs[i] + arrs[index];
                arrs[i] = arrs[index] - arrs[i];
                arrs[index] = arrs[index] - arrs[i];
            }
        }
        return arrs;
    }
}
