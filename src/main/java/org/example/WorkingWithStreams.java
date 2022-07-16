package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class WorkingWithStreams {

    public static void main(String[] args) {
        List<String> stringList = List.of(
                "cat",
                "dog",
                "Java",
                "javascript",
                "javacat",
                "kotlin",
                "cat");
        List<String> result = filterCat(stringList);
        System.out.println(result);

        List<Cat> catList = List.of(
                new Cat(),
                new Cat("red"),
                new Cat("grey", "Barsic"),
                new Cat("Murzic", "pantera", 6));
        List<Dog> dogList = rechengeCats(catList);
        System.out.println(dogList);

        CatShow catShow1 = new CatShow("Wild_Cat_Show", "Chicago", LocalDate.now());
        CatShow catShow2 = new CatShow("SuperCat", "Muhosransk", LocalDate.of(2022, 1, 21));
        CatShow catShow3 = new CatShow("konopes", "Moscow", LocalDate.now());
        CatShow catShow4 = new CatShow("Wild_Cat_Show", "Moscow", LocalDate.now());
        CatShow catShow5 = new CatShow("Cat_Show", "Chicago", LocalDate.now());
        CatShow catShow6 = new CatShow("WildShow", "Chicago", LocalDate.now());


        catList.get(0).addCatShow(catShow1);
        catList.get(0).addCatShow(catShow3);
        catList.get(0).addCatShow(catShow5);
        catList.get(1).addCatShow(catShow1);
        catList.get(1).addCatShow(catShow2);
        catList.get(1).addCatShow(catShow6);
        catList.get(2).addCatShow(catShow4);
        catList.get(2).addCatShow(catShow5);
        catList.get(2).addCatShow(catShow1);

        catList.get(3).addCatShow(catShow2);
        catList.get(3).addCatShow(catShow6);
        catList.get(3).addCatShow(catShow5);

        List<Cat> catList2 = filterCat2(catList);
        System.out.println(catList2);
    }
    // создать метод, принимающий на вход список строк и выдающий список строк без строк "кот"

    private static List<String> filterCat(List<String> stringList) {
        return stringList.stream()
                .filter(anyString -> !anyString.equals("cat"))
                .collect(Collectors.toList());

    }
    // создать медод, принимающий на вход список объектов котов
    // метод должен преобразовать всех котов в собак по следующему принципу
    // имя совпадает, цвет совпадает, количество жизней уменьшить втрое.
    // коты и собаки должны иметь специфичные поля, которые при преобразовании принимают значение по умолчанию
    // в конечном списке не должно быть собак с цветом RED

    public static List<Dog> rechengeCats(List<Cat> catList) {
        return catList.stream()
                .filter(dog -> !dog.getColor().equals("red"))
                .map(Cat::toDog)
                .toList();
    }

    // Добавить в котов список выставок, в которых эти коты учавствовали.
    // Выставка должна иметь название, дату и место проведения.
    // Создать метод, принимающий список котов и возвращает список котов,
    // которые не учавствовали в выставке с названием "Wild_Cat_Show" или которая проводилась в Москве.

    public static List<Cat> filterCat2(List<Cat> catList2) {
        return catList2.stream()
                .filter(cat -> cat.getCatShowList()
                        .stream()
                        .noneMatch(catShow -> catShow.getShowName().equals("Wild_Cat_Show")
                                || catShow.getPlace().equals("Moscow")))
                .toList();

    }
}
