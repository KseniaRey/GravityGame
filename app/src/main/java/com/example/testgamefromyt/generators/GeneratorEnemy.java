package com.example.testgamefromyt.generators;

import com.example.myframework.GraphicsFW;
import com.example.testgamefromyt.objects.Enemy;

import java.util.ArrayList;

public class GeneratorEnemy {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    public ArrayList<Enemy> enemyArrayList;

    public GeneratorEnemy(int sceneWidth, int sceneHeight, int minScreenY) {
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        this.minScreenY = minScreenY;
        this.minScreenX = 0;
        enemyArrayList = new ArrayList<>();
    }

    public void update(double speedPlayer){

        if (enemyArrayList.size() < 3){
            addEnemy(speedPlayer, 3);
        }
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).update(speedPlayer);
        }
    }

    /**
     * Добавляем врага и указываем его тип
     * @param speedPlayer скорость игрока
     * @param amountEnemy сколько врагов
     */
    private void addEnemy(double speedPlayer, int amountEnemy) {
        for (int i = 0; i < amountEnemy; i++) {
            enemyArrayList.add(new Enemy(maxScreenX, maxScreenY, minScreenY, 1));
        }
    }


    public void drawing(GraphicsFW graphicsFW){
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.get(i).drawing(graphicsFW);
        }
    }

    public void hitPlayer(Enemy enemy) {
        for (int i = 0; i < enemyArrayList.size(); i++) {
            enemyArrayList.remove(enemy);
        }
    }
}
