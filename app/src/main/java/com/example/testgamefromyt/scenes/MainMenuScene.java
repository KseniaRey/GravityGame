package com.example.testgamefromyt.scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;
import com.example.testgamefromyt.R;

public class MainMenuScene extends SceneFW {
    public MainMenuScene(CoreFW coreFW) {
        super(coreFW);
    }

    /**
     * Переключаемся по заданным координатам и отслеживаем нажатие на экран
     */
    @Override
    public void update() {
        // этот метод запускается и апдейтится 60 раз в секунду. на каждом апдейте надо проверить, было ли касание
        if (coreFW.getTouchListenerFW().getTouchUp(20, 300, 100, 50)){
            coreFW.setScene(new GameScene(coreFW));
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_nameGame), 100, 100, Color.BLUE, 60, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_newGame), 20, 300, Color.BLUE, 40, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_settings), 20, 350, Color.BLUE, 40, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_results), 20, 400, Color.BLUE, 40, null);
        graphicsFW.drawText(coreFW.getString(R.string.txt_mainMenu_exit), 20, 450, Color.BLUE, 40, null);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
