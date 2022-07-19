package org.example;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;

@Data

public class CatShow {

    private final String showName;
    private final String place;
    private final LocalDate date;
    private final int prizeSize;

}
