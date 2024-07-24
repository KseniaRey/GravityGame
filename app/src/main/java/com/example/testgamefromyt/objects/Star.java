package com.example.testgamefromyt.objects;

import com.example.myframework.ObjectFW;
import com.example.myframework.utilits.UtilRandomFW;

public class Star extends ObjectFW{
    public Star(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenX = 0;
        this.minScreenY = minScreenY;
        this.speed = 2;
        this.x = UtilRandomFW.getCasualNumber(maxScreenX);
        this.y = UtilRandomFW.getGap(minScreenY, maxScreenY);// вернет случайное число от 0 до 600 для генерации позиций звезд по y
    }

    public void update (double speedPlayer){ // как только по х появится первая звезда, она должна двигаться по иксу
        x -= speedPlayer; // чем больше скорость, тем быстрее звезды по иксу перемещаются
        x -= speed;
        if (x < 0){ // если звезда вышла за край экрана, то возвращаем ее назад
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
    }

    /**
     * типа геттеры
     * @return
     */
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
