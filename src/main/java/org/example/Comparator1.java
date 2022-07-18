package org.example;

public interface Comparator1 {

    public default int compareTo(Cat o1, Cat o2) {
        return o1.getName().length() - o2.getName().length();
    }

    public default int compareTo(CatShow o1, CatShow o2){

        return o1.getDate().compareTo(o2.getDate());
    }
}
