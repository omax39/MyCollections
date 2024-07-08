package com.marlboro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyList<String> ml = new MyLinkedList<>();
        ml.add("A");
        ml.add("B");
        ml.add("C");
        ml.add("D");
        ml.add("E");
        ml.add("F");
        ml.add("B");
        ml.add("G");
        ml.add("H");
        ml.add("I");
        ml.add("J");
        ml.add("K");
        System.out.println(ml);

        System.out.println(ml.lastIndexOf("B"));

        ml.add(2, "Y");
        System.out.println(ml);


        System.out.println(ml.remove(0));
        System.out.println(ml);

        ml.remove("B");
        System.out.println(ml);

        System.out.println(ml.get(2));

        List<String> list = new ArrayList<>();
        list.add("ooo");
        list.add("fff");
        list.add("ggg");
        list.add("hhh");

        MyList<String> myList = new MyLinkedList<>(list);
        System.out.println(myList);

        System.out.println(ml.contains("fff"));

        ml.addAll(list);
        System.out.println(ml);

        ml.addAll(myList);
        System.out.println(ml);

        System.out.println(ml.containsAll(list));
        System.out.println(ml.containsAll(myList));
        System.out.println(ml.containsAll("A", "P"));

        MyList<String> m = new MyLinkedList<>("1", "2");
        System.out.println(m);

        Integer[] i = new Integer[]{1, 2};
        MyList<Integer> m2 = new MyLinkedList<>(i);
        System.out.println(m2);

        System.out.println(ml);
        ml.removeAll(list);
        System.out.println(ml);
        ml.removeAll(myList);
        System.out.println(ml);

        System.out.println(ml.isEmpty());
        ml.clear();
        System.out.println(ml);

        MyList<Integer> m3 = new MyLinkedList<>();
        List<Integer> list1 = new ArrayList<>();

        Random r = new Random();

        for (int j = 0; j < 10; j++){
            list1.add(r.nextInt(100));
        }

        for (int j = 0; j < 10; j++){
            m3.add(r.nextInt(100));
        }

        System.out.println(list1);
        MyLinkedList.sort(list1);
        System.out.println(list1);

        System.out.println(m3);
        MyLinkedList.sort(m3);
        System.out.println(m3);


    }
}