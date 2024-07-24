package com.example.myframework.utilits;

public class UtilTimerDelay {
    double startTime;
    double nowTime;
    double elapsedTime;
    final double SECOND = 1000000000; // в 1 сек 1 млрд наносек, джава работает с наносеками

    public void startTimer(){
        startTime = System.nanoTime() / SECOND;
    }


    public boolean timerDelay(double second){
        nowTime = System.nanoTime() / SECOND;
        elapsedTime = nowTime - startTime;
        if (elapsedTime > second){
            return true;
        }
        return false;
    }
}
