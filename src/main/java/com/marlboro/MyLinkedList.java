package com.marlboro;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
Моя реализация части LinkedList. Не копировал код) Писал все сам.
 */

public class MyLinkedList<T> implements MyList<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;


    // Создание пустого списка
    public MyLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    // Создание списка с добавлением коллекции
    public MyLinkedList(Collection<T> c) {
        this();
        for (T item : c) {
            add(item);
        }
    }

    // Создание списка с добавлением моей реализации коллекции
    public MyLinkedList(MyList<T> m) {
        this();
        for (int i = 0; i < size(); i++) {
            add(m.get(i));
        }
    }

    // Создание списка с добавлением массива или последовательности
    @SafeVarargs
    public MyLinkedList(T ... t) {
        this();
        for (T item : t) {
            add(item);
        }
    }

    // Класс нод, которые и будут элементами моего списка. Содержат, сам элемент и ссылки на слежующую и предыдущую ноду
    private static class Node<E> {
        E elem;
        Node<E> prev;
        Node<E> next;
        public Node(E elem, Node<E> prev, Node<E> next) {
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }

    // Добавление элеимента в конец списка
    @Override
    public void add(T o) {
        if (first == null) {
            first = new Node<>(o, first, first);
            last = first;
            first.prev = first;
            first.next = first;
        }
        else {
            Node<T> newNode = new Node<>(o, last, first);
            last.next = newNode;
            last = newNode;
            first.prev = newNode;
        }
        size ++;
    }

    // Добавление элеимента по индексу
    @Override
    public void add(int index, T o) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        else {
            Node<T> oldElem = nodeFromIndex(index);
            Node<T> newElem = new Node<>(o, oldElem.prev, oldElem);
            oldElem.prev.next = newElem;
            oldElem.prev = newElem;
        }
        size++;
    }

    // Добавление элементов в конец из коллекции
    @Override
    public void addAll(Collection<? extends T> c) {
        for (T t : c) {
            add(t);
        }
    }

    // Добавление элементов в конец из коллекции моей реализации
    @Override
    public void addAll(MyList<? extends T> m) {
        for (int i = 0; i < m.size(); i++) {
            add(m.get(i));
        }
    }

    // Получение элемента по индексу
    @Override
    public T get(int index) {
        return nodeFromIndex(index).elem;
    }

    // Получение размера
    @Override
    public int size() {
        return size;
    }

    // Удаление элемента по индексу
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            Node<T> removeNode = nodeFromIndex(index);
            removeNode.prev.next = removeNode.next;
            removeNode.next.prev = removeNode.prev;
            size--;
            if (index == 0) {
                first = removeNode.next;
            } else if (index == size() - 1) {
                last = removeNode.prev;
            }
            return removeNode.elem;
        }
    }

    // Удаление по элементу
    @Override
    public boolean remove(T o) {
        if (contains(o)) {
            remove(indexOf(o));
            return true;
        }
        return false;
    }

    // Удаление элементов перечисленных в коллекции
    @Override
    public boolean removeAll(Collection<? extends T> c) {
        for (T elem : c) {
            if (contains(elem)) {
                remove(elem);
            }
        }
        return true;
    }

    // Удаление элементов перечисленных в коллекции моей реализации
    @Override
    public boolean removeAll(MyList<? extends T> c) {
        for (int i = 0; i < c.size(); i++) {
            if (contains(c.get(i))) {
                remove(c.get(i));
            }
        }
        return true;
    }

    // Очистка списка
    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    // Проверка на содержание элемента
    @Override
    public boolean contains(T o) {
        for (Node<T> node = first; node != null; node = node.next) {
            if (node.elem.equals(o)) {
                return true;
            }
            if (node.next == first) break;
        }
        return false;
    }

    // Проверка на содержание всех элементов коллекции
    @Override
    public boolean containsAll(Collection<? extends T> c) {
        for (T elem : c) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    // Проверка на содержание всех элементов коллекции моей реализации
    @Override
    public boolean containsAll(MyList<? extends T> c) {
        for (int i = 0; i < c.size(); i++) {
            if (!contains(c.get(i))) {
                return false;
            }
        }
        return true;
    }

    // Проверка на содержание всех элементов массива или перечисления
    @Override
    @SafeVarargs
    public final boolean containsAll(T... t) {
        for (T elem : t) {
            if (!contains(elem)) {
                return false;
            }
        }
        return true;
    }

    // Проверка списка на пустоту
    @Override
    public boolean isEmpty() {
        return first == null;
    }

    // Поиск индекса элемента
    @Override
    public int indexOf(T o) {
        if (first == null) {
            return -1;
        } else {
            for (int i = 0; i < size(); i++) {
                if (get(i).equals(o)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // Поиск индекса последнего вхождения элемента
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size() - 1; i >= 0; i--) {
            if (get(i).equals(o)) {
                return i;
            }
        }
        return -1;
    }

    // Получение индекса ноды
    public Node<T> nodeFromIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            int i = 0;
            for (Node<T> node = first; node != null; node = node.next) {
                if (i == index) {
                    return node;
                } else {
                    i++;
                }
            }
        }
        return null;
    }

    // Установка значения по индексу
    @Override
    public void set(int index, T o) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        } else {
            nodeFromIndex(index).elem = o;
        }
    }

    // Получение строки элементов списка
    @Override
    public String toString() {
        if (first == null) {
            return "[]";
        } else {
            String s = "[";
            for (int i = 0; i < size() - 1; i++) {
                s += get(i) + ", ";
            }
            s += get(size() - 1) + "]";
            return s;
        }
    }

    // Быстрая сортировка коллекции, возвращает новый список
    public static <E extends Comparable<E>> List<E> fastSort(List<E> list){
        if (list == null || list.size() < 2) return list;
        else {
            E p = list.getFirst();
            list.removeFirst();
            List<E> less = list.stream()
                    .filter(l -> p.compareTo(l) > 0)
                    .collect(Collectors.toList());
            List<E> greater = list.stream()
                    .filter(l -> l.compareTo(p) >= 0)
                    .collect(Collectors.toList());
            List<E> result = fastSort(less);
            result.add(p);
            result.addAll(fastSort(greater));
            return result;
        }
    }

    // Быстрая сортировка коллекции моей реализации, возвращает новый список
    public static <E extends Comparable<E>> MyList<E> fastSort(MyList<E> list){
        if (list == null || list.size() < 2) return list;
        else {
            E p = list.get(0);
            list.remove(0);
            MyList<E> less = new MyLinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                if (p.compareTo(list.get(i)) > 0) {
                    less.add(list.get(i));
                }
            }
            MyList<E> greater = new MyLinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                if (p.compareTo(list.get(i)) <= 0) {
                    greater.add(list.get(i));
                }
            }
            MyList<E> result = fastSort(less);
            result.add(p);
            result.addAll(fastSort(greater));
            return result;
        }
    }

    // Преобразование текущего списка с учетом быстрой сортировки
    public static <E extends Comparable<E>> void sort(List<E> list){
        List<E> list1 = fastSort(list);
        list.clear();
        list.addAll(list1);
    }

    // Преобразование текущего списка моей реализации с учетом быстрой сортировки
    public static <E extends Comparable<E>> void sort(MyList<E> list){
        MyList<E> list1 = fastSort(list);
        list.clear();
        list.addAll(list1);
    }

    // Сортировка пузырьком коллекции
    public static <E extends Comparable<E>> void bubbleSort(List<E> list){
        if (list == null || list.isEmpty()) return;
        else {
            for (int i = 0; i < list.size() - 1; i++) {
                boolean flag = false;
                for (int j = 0; j < list.size() - i - 1; j++) {
                    if (list.get(j).compareTo(list.get(j+1)) > 0) {
                        flag = true;
                        E temp = list.get(j);
                        list.set(j, list.get(j+1));
                        list.set(j+1, temp);
                    }
                }
                if (!flag) break;
            }
        }
    }

    // Сортировка пузырьком коллекции моей реализации
    public static <E extends Comparable<E>> void bubbleSort(MyList<E> list){
        if (list == null || list.isEmpty()) return;
        else {
            for (int i = 0; i < list.size() - 1; i++) {
                boolean flag = false;
                for (int j = 0; j < list.size() - i - 1; j++) {
                    if (list.get(j).compareTo(list.get(j+1)) > 0) {
                        flag = true;
                        E temp = list.get(j);
                        list.set(j, list.get(j+1));
                        list.set(j+1, temp);
                    }
                }
                if (!flag) break;
            }
        }
    }

}
