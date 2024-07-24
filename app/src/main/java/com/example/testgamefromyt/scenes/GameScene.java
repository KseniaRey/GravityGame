package com.example.testgamefromyt.scenes;

import android.graphics.Color;

import com.example.myframework.CoreFW;
import com.example.myframework.SceneFW;
import com.example.testgamefromyt.R;
import com.example.testgamefromyt.classes.GameManager;
import com.example.testgamefromyt.generators.GeneratorBackground;

public class GameScene extends SceneFW  {

    enum GameState{
        READY, RUNNING, PAUSE, GAME_OVER
    }

    GameState gameState;

    GameManager gameManager;

    public GameScene(CoreFW coreFW) {
        super(coreFW);
        gameState = GameState.READY;
        gameManager = new GameManager(coreFW, sceneWidth, sceneHeight);
    }

    // проверяем, в каком состояннии игра
    @Override
    public void update() {
        if (gameState == GameState.READY){
            updateStateReady();
        }
        else if (gameState == GameState.RUNNING){
            updateStateRunning();
        }
        else if (gameState == GameState.PAUSE){
            updateStatePause();
        }
        else if (gameState == GameState.GAME_OVER){
            updateStateGameOver();
        }
    }

    @Override
    public void drawing() {
        graphicsFW.clearScene(Color.BLACK);
       

        if (gameState == GameState.READY){
            drawingStateReady();
        }
        else if (gameState == GameState.RUNNING){
            drawingStateRunning();
        }
        else if (gameState == GameState.PAUSE){
            drawingStatePause();
        }
        else if (gameState == GameState.GAME_OVER){
            drawingStateGameOver();
        }
    }

    private void drawingStateGameOver() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText("Игра закончена", 230, 300, Color.WHITE, 60, null);

    }
    private void updateStateGameOver() {

    }

    private void drawingStatePause() {

    }
    private void updateStatePause() {

    }

    private void drawingStateRunning() {
        graphicsFW.clearScene(Color.BLACK);
        gameManager.drawing(coreFW, graphicsFW);
    }
    private void updateStateRunning() {
        gameManager.update();
        if (GameManager.gameOver){
            gameState = GameState.GAME_OVER;
        }
    }

    private void drawingStateReady() {
        graphicsFW.clearScene(Color.BLACK);
        graphicsFW.drawText(coreFW.getString(R.string.txt_gameScene_stateReady_ready),
                250, 300, Color.WHITE, 60, null);
    }
    private void updateStateReady() { // зачем там два раза высота? - теория: если нажмет в любое место жкрана, то пустит
        if (coreFW.getTouchListenerFW().getTouchUp(0, sceneHeight, sceneWidth, sceneHeight)){
            gameState = GameState.RUNNING;
        }
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
