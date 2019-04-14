package com.lyw.hadoop.design.singleton;

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
public class InnerClassSingleton {

    static class SingletonClass{
        static SingletonClass singletonClass = new SingletonClass();
    }

    public static SingletonClass getInstance() {
        return SingletonClass.singletonClass;
    }
}
