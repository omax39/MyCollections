package com.marlboro;

import java.util.Collection;

public interface MyList<T> {

    void add(T o);
    void add(int index, T o);
    void addAll(Collection<? extends T> c);
    void addAll(MyList<? extends T> c);
    T get(int index);
    int size();
    T remove(int index);
    void clear();
    boolean contains(T o);
    boolean containsAll(Collection<? extends T> c);
    boolean containsAll(MyList<? extends T> c);
    boolean containsAll(T... t);
    boolean isEmpty();
    boolean remove(T o);
    boolean removeAll(Collection<? extends T> c);
    boolean removeAll(MyList<? extends T> c);
    int indexOf(T o);
    int lastIndexOf(T o);
    void set(int index, T o);

}
