package com.java.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Bill {
    private long customerNum;
    private double netAmount, prevReading, currReading, unitConsumed;
    private Date billDate;
}
