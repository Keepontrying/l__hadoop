package com.lyw.hadoop.arithmetic;

import java.util.Random;

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
public class PartitionArithmetic {
    private static int arint[] = new int[20];

    private static void fill() {
        for (int i = 0; i < 20; i++) {
            arint[i] = new Random().nextInt();
        }
    }

    private static int partition(int k) {
        int pos = arint[k];

        return 0;
    }

    public static void main(String[] args) {
        fill();
        partition(6);
    }
}
