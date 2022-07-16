package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
class Cat extends Animal {

    private String breed = null;
    @Setter
    @Getter
    private List<CatShow> catShowList = new ArrayList<>();

    public void addCatShow(CatShow catShow){
        catShowList.add(catShow);
    }


    public Cat(String color) {
        this.color = color;
    }

    public Cat(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public Cat(String color, String name, String breed) {
        this.color = color;
        this.name = name;
        this.breed = breed;
    }

    public Cat(String name, String breed, int countLife) {
        this.name = name;
        this.breed = breed;
        this.countLife = countLife;
    }


    public static Dog toDog(Cat cat) {
        return new Dog(cat.getColor(), cat.getName(), cat.getCountLife() / 3);
    }


}
