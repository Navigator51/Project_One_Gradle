package org.example;

import lombok.Data;

import java.time.LocalDate;

@Data

public class CatShow {

    private String showName;
    private String place;
    private LocalDate date;
    private int prizeSize;


    public CatShow(String showName, String place, LocalDate date, int prizeSize) {
        this.showName = showName;
        this.place = place;
        this.date = date;
        this.prizeSize = prizeSize;
    }

    public CatShow() {

    }
}
