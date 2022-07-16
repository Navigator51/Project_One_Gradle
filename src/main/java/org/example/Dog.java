package org.example;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
public class Dog extends Animal {


    private boolean isAngry = false;

    Dog(String color, String name, int countLife) {
        this.name = name;
        this.color = color;
        this.countLife = countLife;
    }

    public Dog(String color, String name) {
        this.color = color;
        this.name = name;
    }

}


