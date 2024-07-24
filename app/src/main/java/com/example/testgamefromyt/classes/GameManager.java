package com.example.testgamefromyt.classes;

import com.example.myframework.CollisionDetect;
import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.testgamefromyt.generators.GeneratorBackground;
import com.example.testgamefromyt.generators.GeneratorEnemy;
import com.example.testgamefromyt.objects.Hud;
import com.example.testgamefromyt.objects.MainPlayer;

public class GameManager {
    private int maxScreenX;
    private int maxScreenY;
    private int minScreenX;
    private int minScreenY;

    private int passedDistance;
    private int currentSpeedPlayer;
    private int currentShieldsPlayer;

    public static boolean gameOver;

    MainPlayer mainPlayer;
    GeneratorBackground generatorBackground;
    GeneratorEnemy generatorEnemy;
    Hud hud;

    public GameManager(CoreFW coreFW, int sceneWidth, int sceneHeight) {
        this.hud = new Hud(coreFW);
        this.maxScreenX = sceneWidth;
        this.maxScreenY = sceneHeight;
        minScreenY = hud.getHEIGHT_HUG(); // мин значение считается от края полоски
        minScreenX = 0;
        mainPlayer = new MainPlayer(coreFW, maxScreenX, maxScreenY, minScreenY);
        generatorBackground = new GeneratorBackground(sceneWidth, sceneHeight,minScreenY);
        generatorEnemy = new GeneratorEnemy(sceneWidth, sceneHeight, minScreenY);
        gameOver = false;

    }

    public void update(){
        generatorBackground.update(mainPlayer.getSpeedPlayer());
        mainPlayer.update();
        generatorEnemy.update(mainPlayer.getSpeedPlayer());

        passedDistance += mainPlayer.getSpeedPlayer(); // скорость будет прибавляться к пройденному пути
        // чтобы при ускорении пройденная дистанция быстрее считалась
        currentSpeedPlayer = (int) mainPlayer.getSpeedPlayer() * 60;
        currentShieldsPlayer = mainPlayer.getShieldsPlayer();

        hud.update(passedDistance, currentSpeedPlayer, currentShieldsPlayer);

        checkHit();

    }

    private void checkHit() {
        for (int i = 0; i < generatorEnemy.enemyArrayList.size(); i++) {
            if (CollisionDetect.collisionDetect(mainPlayer, generatorEnemy.enemyArrayList.get(i))){
                mainPlayer.hitEnemy();
                generatorEnemy.hitPlayer(generatorEnemy.enemyArrayList.get(i));
            }
        }
    }

    public void drawing(CoreFW coreFW, GraphicsFW graphicsFW){
        mainPlayer.drawing(graphicsFW);
        generatorBackground.drawing(graphicsFW);
        generatorEnemy.drawing(graphicsFW);

        hud.drawing(graphicsFW);
    }
}
