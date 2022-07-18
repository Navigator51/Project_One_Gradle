package org.example;

public class Comparator implements Comparable<Cat>, Comparator1 {

    @Override
    public int compareTo(Cat o1, Cat o2) {
        return o1.getName().length() - o2.getName().length();
    }


    @Override
    public int compareTo(Cat o) {
        return 0;
    }
}
