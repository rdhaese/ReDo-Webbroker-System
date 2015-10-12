package org.realdolmen.webbroker.util;

import java.io.Serializable;

/**
 * Generic class which can be used to keep track of two elements.
 *
 * @param <K> The type of the first element.
 * @param <V> The type of the second element.
 * @author Youri Flement
 */
public class Pair<K, V> implements Serializable {

    private K a;

    private V b;

    public Pair(K a, V b) {
        this.a = a;
        this.b = b;
    }

    public K getFirst() {
        return a;
    }

    public V getSecond() {
        return b;
    }

}
