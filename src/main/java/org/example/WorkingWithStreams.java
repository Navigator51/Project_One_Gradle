package org.example;

import lombok.Data;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Period;
import java.util.stream.Stream;

import static java.lang.System.out;

@Data
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
        out.println("Список строк без строки кот " + result);

        List<Cat> catList = List.of(
                new Cat(),
                new Cat("red"),
                new Cat("grey", "Barsic"),
                new Cat("Murzic", "pantera", 6),
                new Cat("kosmosss", "pantera", 4)
        );

        List<Dog> dogList = rechengeCats(catList);
        out.println("Вoт список котов превратили в список собак " + dogList);

        CatShow catShow1 = new CatShow("Wild_Cat_Show", "Chicago", LocalDate.of(2022, 6, 14), 10000);
        CatShow catShow2 = new CatShow("SuperCat", "Muhosransk", LocalDate.of(2022, 6, 21), 15200);
        CatShow catShow3 = new CatShow("konopes", "Moscow", LocalDate.of(2022, 7, 12), 106800);
        CatShow catShow4 = new CatShow("Wild_Cat_Show", "Moscow", LocalDate.of(2022, 6, 10), 123456);
        CatShow catShow5 = new CatShow("Cat_Show", "Chicago", LocalDate.of(2022, 4, 1), 654321);
        CatShow catShow6 = new CatShow("WildShow", "Chicago", LocalDate.now(), 13220);

        List<CatShow> catShowList = List.of(catShow1, catShow2, catShow3, catShow4, catShow5, catShow6);


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
        catList.get(4).addCatShow(catShow2);
        catList.get(4).addCatShow(catShow3);

        List<Cat> catList2 = filterCat2(catList);
        out.println(catList2);

        List<CatShow> catShowList3 = filterCat3(catList);
        out.println(catShowList3);

        Cat shotNameCat = filterCat4(catList);
        out.println(shotNameCat);

        List<CatShow> showListByDate = filterCatShowByDate(catShowList);
        out.println(showListByDate);

        int summPrizes = sumPrize(catList);
        out.println("Банк всех выставок равен " + summPrizes + " рубликов");

        long middlePrise = middlePrizeInMoscow(catList);
        out.println("В среднем по Москве за выставку " + middlePrise + " рубликов");

        Map<String, LocalDate> mapa = votTeMapa(catShowList);
        out.println("А вот и мапа " + mapa);

        Map<String, List<CatShow>> mapa2 = mapa4(catList);
        out.println(mapa2);

        Map<Cat, Integer> mapa3 = mapa3(catList);
        out.println(mapa3);

        CatShow middleCatShow = analiticCatShow(catShowList);
        out.println(middleCatShow);

        Set<Cat> catSet = createSetCollections(catList);

        List<Cat> catList1 = createCatList(catSet);
        out.println("5-й элемент списка " + catList1.get(4));

        Map<String, Cat> catMap = createMap(catList);
        catMap.putIfAbsent("Murzic", new Cat());
        out.println(catMap);
        out.println("Список ключей " + catMap.keySet());
        out.println("Список значений " + catMap.values());
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

    //написать метод, принимающий список котов, а возвращающий список выставок на которых были коты с породой пантера
    // и которые прходили в период с 01.06.22 по 01.07.22
    public static List<CatShow> filterCat3(List<Cat> catList) {
        return catList.stream()
                .filter(cat -> cat.getBreed().equals("pantera")) //Фильтр по имени
                .map(Cat::getCatShowList)
                //Преобразовали в стрим из  списков котошоу
                .flatMap(Collection::stream)
                //раскрыли списки в общий стрим
                .filter(catShow -> catShow.getDate().isAfter(LocalDate.of(2022, 6, 1))
                        && catShow.getDate().isBefore(LocalDate.of(2022, 6, 30)))
                // олфильтровали по дате
                .distinct()
                // проверили записи на уникальность
                .toList();

    }

    // Создать метод, принимающий список котов и возвращает одного кота с самым коротким именем.

    public static Cat filterCat4(List<Cat> catList) {
        Optional<Cat> cat = catList.stream()
                .min((o1, o2) -> new CatComparator().compare(o1, o2));
        cat.ifPresentOrElse(out::println, () -> out.println("conteiner is empty"));
        return new Cat();
    }

    // Создать метод, принимающий список выставок и возвращает 3 самые последние по дате выставки
    public static List<CatShow> filterCatShowByDate(List<CatShow> catShowList) {
        return catShowList.stream()
                .sorted((o1, o2) -> o2.getDate().compareTo(o1.getDate())) // Обратная сортировка заменой о1 и о2
                .limit(3L)
                .toList();
    }

    // Вывести сумму размеров призов для всех кошачих выставок. метод должен принимить список котов.

    public static int sumPrize(List<Cat> catList) {
        return catList.stream()
                .map(Cat::getCatShowList)
                .flatMap(Collection::stream)
                .distinct()
                .mapToInt(CatShow::getPrizeSize)
                .sum();

    }

    // Вывести среднее значение размеров призов московских кошачих выставок. метод должен принимать список котов

    public static long middlePrizeInMoscow(List<Cat> catList) {
        return Math.round(
                catList.stream()
                        //открыли стрим
                        .map(Cat::getCatShowList)
                        //создали внутренний стрим из списков
                        .flatMap(Collection::stream)
                        //вернули (объединили) в общий стрим
                        .distinct()
                        //проверили элементы на уникальность
                        .filter(CatShow -> CatShow.getPlace().equals("Moscow"))
                        //отфильтровали московские
                        .map(CatShow::getPrizeSize)
                        //добыли из элементов нужные данные
                        .mapToInt(value -> value)
                        //преобразовали в поток простых чисел
                        .average()
                        //вычислили среднее арифметическое
                        .orElse(0));
        // вернули итоговое значение и закрыли стрим
    }

    // надо принять список выставок, а возвращать мапу, где ключ имя выставки, а значением дата проведения
    public static Map<String, LocalDate> votTeMapa(List<CatShow> catShowList) {

        Map<String, LocalDate> mapa = new HashMap<>();

        catShowList.forEach(CatShow -> mapa.put(CatShow.getShowName(), CatShow.getDate()));
        return mapa;
    }

    public static Map<String, LocalDate> votTeMapa1(List<CatShow> catShowList) {
        return catShowList.stream()
                .collect(Collectors.toMap(CatShow::getShowName, CatShow::getDate));
    }

    // Принимаем список котов, а возвращаем мапу, в которой ключ имя выставки, а значение список выставок с этим именем
    public static Map<String, List<CatShow>> mapa2(List<Cat> catList) {

        return catList.stream()
                .map(Cat::getCatShowList)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toMap(CatShow::getShowName,
                        catShow -> catList.stream()
                                .map(Cat::getCatShowList)
                                .flatMap(Collection::stream)
                                .distinct()
                                .filter(a -> a.getShowName().equals(catShow.getShowName()))
                                .toList(), (oldListCatShow, newListCatShow) -> {
                            ArrayList<CatShow> sumCatShow = new ArrayList<>(oldListCatShow);
                            sumCatShow.addAll(newListCatShow);
                            return sumCatShow;
                        }));

    }
    // Принимаем список котов, а возвращаем мапу, где ключ объект кот, а значение сумма призов всех выставок,
    // на которых был этот кот

    public static Map<Cat, Integer> mapa3(List<Cat> catList) {
        return catList.stream()
                .collect(Collectors.toMap(cat -> cat, cat -> cat.getCatShowList().stream()
                        .map(CatShow::getPrizeSize)
                        .mapToInt(value -> value)
                        .sum()));
    }

    public static Map<String, List<CatShow>> mapa4(List<Cat> catList) {

        return catList.stream()
                .map(Cat::getCatShowList)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.groupingBy(CatShow::getShowName));
    }

    // Получить список кошачих выставок и вернуть одну выставку, у которой приз будет суммой всех призов,
    // имя составлено из первых букв названий всех выставок,
    // дата - средняя дата проведения всех,
    // место - составлен из двух последних букв каждого города, приведённых к верхнему регистру

    public static CatShow analiticCatShow(List<CatShow> catShowList) {

        CatShow middleCatShow = new CatShow();

        middleCatShow.setShowName(catShowList.stream()
                .map(CatShow::getShowName)
                .distinct()
                .reduce("", (a, b) -> (a += b.charAt(0))));

        middleCatShow.setPrizeSize(catShowList.stream()
                .mapToInt(CatShow::getPrizeSize)
                .sum());

        // Находим самую раннюю дату выставки
        LocalDate minDate = (catShowList.stream()
                .map(CatShow::getDate)
                .min(LocalDate::compareTo)
                .orElseThrow());

        // Находим самую позднюю дату выставки
        LocalDate maxDate = (catShowList.stream()
                .map(CatShow::getDate)
                .max(LocalDate::compareTo)
                .orElseThrow());


        Period period = Period.between(minDate, maxDate);   // вычисляем период проведения выставок(YY,MM,DD)
        long halfPeriodInDays = period.get(ChronoUnit.DAYS) / 2;   //вычисляем половину периода в днях

        LocalDate middleDate = minDate.plusDays(halfPeriodInDays);    // находим среднюю дату проведения выставок

        middleCatShow.setDate(middleDate);

        middleCatShow.setPlace(catShowList.stream()
                .map(CatShow::getPlace)
                .distinct()
                .reduce("", (a, b) -> a += getSubstringFromPlase(b).toUpperCase()));

        return middleCatShow;


    }

    // решение предыдущей задачи новым методом
    public static CatShow catShowReduser(CatShow previousObj, CatShow currentObj) {
        return new CatShow(
                previousObj.getShowName() + getSubstringFromName(currentObj),
                previousObj.getPlace() + getSubstringFromPlase(currentObj.getPlace()),
                getMergedDate(previousObj, currentObj),
                previousObj.getPrizeSize() + currentObj.getPrizeSize()
        );
    }

    private static LocalDate getMergedDate(CatShow previousObj, CatShow currentObj) {
        return previousObj.getDate().plusDays((Period.between(previousObj.getDate(),
                currentObj.getDate()).get(ChronoUnit.DAYS)) / 2);
    }

    private static String getSubstringFromPlase(String previousObj) {
        return previousObj.substring(previousObj.length() - 2);
    }

    private static String getSubstringFromName(CatShow previousObj) {
        return previousObj.getShowName().substring(0, 1);
    }

    public static CatShow analiticCatShow1(List<CatShow> catShowList) {

        return catShowList.stream()
                .reduce(new CatShow("", "", LocalDate.now(), 0), WorkingWithStreams::catShowReduser);
    }

    // лист,сет и мап. создать каждый, в каждый добавить 10 элементов
    // сет котов. удалить из сета всех котов с именем мурзик, вывести на консоль все элементы сета.
    // получить и вывести на консоль 5й элемент списка, удалить повторяющиеся элементы.
    // мапа. получить значение по ключу, получить из мапы отдельно список всех ключeй и список всех значений
    // положить в мапу значение по ключу, который уже существует. найти метод, который не положит и не выдаст ошибку

    public static Set<Cat> createSetCollections(List<Cat> catList) {
        Set<Cat> catSet = new HashSet<>(catList);
        catSet.add(new Cat("Brown", "üyg", "pers"));
        catSet.add(new Cat("pink", "shvaine"));
        catSet.add(new Cat("black", "pirate"));
        catSet.add(new Cat("white", "snowball"));

        catSet.stream()
                .filter(cat -> !cat.getName().equals("murzic"))
                .forEachOrdered(out::println);

        return catSet;
    }

    public static List<Cat> createCatList(Set<Cat> catSet) {
        return new ArrayList<>(catSet);
    }

    public static Map<String, Cat> createMap(List<Cat> catList) {

        Map<String, Cat> catMap = new HashMap<>();
        catList.forEach(cat -> catMap.putIfAbsent(cat.getName(), cat));

        return catMap;
    }
}
