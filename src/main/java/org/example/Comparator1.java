package org.example;

public interface Comparator1 {

    default int compareTo(Cat o1, Cat o2) {
        return o1.getName().length() - o2.getName().length();
    }

    default int compareTo(CatShow o1, CatShow o2){

        return o1.getDate().compareTo(o2.getDate());
    }

    int compareTo(Cat o);
}
