package com.wysso.demo;

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
public class Qsort {

    public void qSort(int[] L, int low, int high) {
        if (low >= high) {
            return;
        }
        int index = partiton(L, low, high);
        qSort(L, low, index - 1);
        qSort(L, index + 1, high);
    }

    private int partiton(int[] l, int low, int high) {
        int p = l[low];
        while (low < high) {
            while (low < high && p <= l[high]) --high;
            l[low] = l[high];
            while (low < high && p >= l[low]) ++low;
            l[high] = l[low];
        }
        l[low] = p;
        return low;
    }

    public static void main(String[] args) {
        int[] a = {6, 56, 9,1,2,3,90, 23, 5};
        new Qsort().qSort(a,0,a.length-1);
    }
}
