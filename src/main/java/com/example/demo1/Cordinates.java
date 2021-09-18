package com.example.demo1;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Cordinates implements Serializable {

    private Double cor_1;
    private Double cor_2;
    private Double cor_3;
    private Double cor_4;

    public Cordinates(Double cor_1, Double cor_2, Double cor_3, Double cor_4) {
        this.cor_1 = cor_1;
        this.cor_2 = cor_2;
        this.cor_3 = cor_3;
        this.cor_4 = cor_4;
    }
// other class members
}
