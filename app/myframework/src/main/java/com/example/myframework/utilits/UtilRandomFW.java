package com.example.myframework.utilits;

import java.util.Random;

public class UtilRandomFW {

    public static int getCasualNumber(int number){
        Random random = new Random();
        int casualNumber;
        casualNumber = random.nextInt(number);
        return casualNumber;
    }


    public static int getGap(int minNumber, int maxNumber){
        int gap = 0;
        gap = (int) ((Math.random()*++maxNumber) + minNumber); // Приведение типа к int
        // в данном случае необходимо из-за того, что метод Math.random()
        // возвращает значение типа double
        return gap;
    }

}
