//package com.example.demo1;
//
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.io.Serializable;
//
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
//public class Cordinates implements Serializable {
//
//    private int cor_1;
//    private int cor_2;
//    private int cor_3;
//    private int cor_4;
//    @JsonCreator
//    public Cordinates(
//            @JsonProperty("cor_1") int cor_1,
//            @JsonProperty("cor_2") int cor_2,
//            @JsonProperty("cor_3") int cor_3,
//            @JsonProperty("cor_4") int cor_4)
//    {
//        this.cor_1 = cor_1;
//        this.cor_2 = cor_2;
//        this.cor_3 = cor_3;
//        this.cor_4 = cor_4;
//
//    }
//
//
//}
//
