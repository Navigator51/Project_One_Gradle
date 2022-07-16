package org.example;

import lombok.Data;

import java.time.LocalDate;

@Data

public class CatShow {

    private final String showName;
    private final String place;
    private final LocalDate date;

}
