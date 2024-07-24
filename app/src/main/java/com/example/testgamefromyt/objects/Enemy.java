package com.example.testgamefromyt.objects;

import android.graphics.Rect;

import com.example.myframework.AnimationFW;
import com.example.myframework.GraphicsFW;
import com.example.myframework.ObjectFW;
import com.example.myframework.utilits.UtilRandomFW;
import com.example.testgamefromyt.utilits.UtilResource;

public class Enemy extends ObjectFW {

    AnimationFW animEnemy;
    public Enemy(int maxScreenX, int maxScreenY, int minScreenY, int enemyType) {
        this.maxScreenX = maxScreenX;
        this.maxScreenY = maxScreenY - UtilResource.spriteEnemy.get(0).getHeight();
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        x = maxScreenX; // чтобы астероид появился справа и летел влево
        y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        radius = UtilResource.spriteEnemy.get(0).getWidth() / 2;

        switch (enemyType){
            case 1:
                speed = UtilRandomFW.getGap(2, 6);
                animEnemy = new AnimationFW(3,
                        UtilResource.spriteEnemy.get(0),
                        UtilResource.spriteEnemy.get(1),
                        UtilResource.spriteEnemy.get(2),
                        UtilResource.spriteEnemy.get(3));
                break;
            case 2:
                speed = UtilRandomFW.getGap(4, 9);
                break;
        }
    }

    public void update(double speedPlayer){
        x -= speed;
        x -= speedPlayer;
        if (x < minScreenX){
            x = maxScreenX;
            y = UtilRandomFW.getGap(minScreenY, maxScreenY);
        }
        animEnemy.runAnimation();

        hitBox = new Rect(x, y,
                UtilResource.spriteEnemy.get(0).getWidth(),
                UtilResource.spriteEnemy.get(0).getHeight());
    }

    public void drawing(GraphicsFW graphicsFW){
        animEnemy.drawAnimation(graphicsFW, x, y);
    }
}
