package com.example.testgamefromyt.generators;

import android.graphics.Color;

import com.example.myframework.GraphicsFW;
import com.example.testgamefromyt.objects.Star;

import java.util.ArrayList;

public class GeneratorBackground {
    public ArrayList<Star> starArrayList = new ArrayList<Star>();

    public GeneratorBackground(int sceneWidth, int sceneHeight, int minScreenY) {
        int starsSpeak = 50; // частицы звезды
        for (int i = 0; i < starsSpeak; i++) {
            Star star = new Star(sceneWidth, sceneHeight, minScreenY); // на каждой итерации пока не станет
            // равно коливеству частиц создаем экз класса Стар и передаем ему
            // высоту и ширину экрана чтобы ничего не сползло
            starArrayList.add(star);
        }

    }

    public void update(double speedPlayer){
        for (int i = 0; i < starArrayList.size(); i++) {
            starArrayList.get(i).update(speedPlayer);
        }
    }

    public void drawing(GraphicsFW graphicsFW){
        for (int i = 0; i < starArrayList.size(); i++) {
            graphicsFW.drawPixel(starArrayList.get(i).getX(),
                    starArrayList.get(i).getY(), Color.WHITE);
        }
        // В цикле: 1. Говорим графике рисовать пиксель - звезду
        // 2. В параметры передаем созданную точку и получаем по ее индексу ее координаты
    }

}
