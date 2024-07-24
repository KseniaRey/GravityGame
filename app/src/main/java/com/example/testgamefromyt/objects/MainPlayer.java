package com.example.testgamefromyt.objects;

import android.graphics.Rect;

import com.example.myframework.CoreFW;
import com.example.myframework.GraphicsFW;
import com.example.myframework.ObjectFW;
import com.example.myframework.utilits.UtilTimerDelay;
import com.example.testgamefromyt.classes.GameManager;
import com.example.testgamefromyt.utilits.UtilResource;
import com.example.myframework.AnimationFW;

public class MainPlayer extends ObjectFW {
    final int GRAVITY = -3;
    final int MAX_SPEED = 15;
    final int MIN_SPEED = 1; // никогда не остановиться
    AnimationFW animSpriteMainPlayer;
    AnimationFW animMainPlayerBoost;
    AnimationFW animExplosionPlayer;
    CoreFW coreFW; // для обработки нажатия

    UtilTimerDelay timerOnShieldHit;
    UtilTimerDelay timerOnGameOver;

    boolean boosting;
    private int shieldsPlayer;
    boolean hitEnemy;
    boolean isGameOver;

    public MainPlayer(CoreFW coreFW, int maxScreenX, int maxScreenY, int minScreenY) {
    x = 20;
    y = 200;
    speed = 3;
    shieldsPlayer = 3;
    boosting = false;
    hitEnemy = false;
    isGameOver = false;

    radius = UtilResource.spritePlayer.get(0).getWidth() / 4; // так мы получаем радиус объекта:
        // ширина объекта = 64 пикселя, по этой форме радиус 16. и если расстояние между объектами
        // превысит 64 пикселя (32 у одного и 32 у второго) - получим столкновение

    timerOnShieldHit = new UtilTimerDelay();
    timerOnGameOver = new UtilTimerDelay();

    this.coreFW = coreFW;
    this.maxScreenX = maxScreenX;
    this.maxScreenY = maxScreenY - UtilResource.spritePlayer.get(0).getHeight();
    this.minScreenY = minScreenY;
    // maxScreenY --> вот тут надо расписать. Если укажем как по иксу, то у нас игрок завалится за
        // экран. Изначально У у нас 600. В картинке точка отсчета - левый верхний угол. Если
        // сделаем привязку к экрану, персонаж уйдет за экран
    animSpriteMainPlayer = new AnimationFW(speed,
                UtilResource.spritePlayer.get(0),
                UtilResource.spritePlayer.get(1),
                UtilResource.spritePlayer.get(2),
                UtilResource.spritePlayer.get(3));

    animMainPlayerBoost = new AnimationFW(speed,
                UtilResource.spritePlayerBoost.get(0),
                UtilResource.spritePlayerBoost.get(1),
                UtilResource.spritePlayerBoost.get(2),
                UtilResource.spritePlayerBoost.get(3));

    animExplosionPlayer = new AnimationFW(speed,
            UtilResource.spriteExplosionPlayer.get(0),
            UtilResource.spriteExplosionPlayer.get(1),
            UtilResource.spriteExplosionPlayer.get(2),
            UtilResource.spriteExplosionPlayer.get(3));

    }


    public void update(){
        if (coreFW.getTouchListenerFW().getTouchDown(0, maxScreenY, maxScreenX, maxScreenY)){
            startBoosting();
        } else if (coreFW.getTouchListenerFW().getTouchUp(0, maxScreenY, maxScreenX, maxScreenY)){
            stopBoosting();
        }

        if (boosting){
            speed += 0.5;
        } else speed -= 3;

        if (speed > MAX_SPEED){
            speed = MAX_SPEED;
        } else if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY;
                if (y < minScreenY){
            y = minScreenY;
                } else if (y > maxScreenY){
            y = maxScreenY;
        }

        if (boosting){
            animMainPlayerBoost.runAnimation();
        } else {
            animSpriteMainPlayer.runAnimation();
        }

        hitBox = new Rect(x, y,
                UtilResource.spritePlayer.get(0).getWidth(),
                UtilResource.spritePlayer.get(0).getHeight());
                if (isGameOver){
                    animExplosionPlayer.runAnimation();
                }

    }

    private void stopBoosting() { // зачем метод? Почему в ифе не указать фолс бустингу?
        boosting = false;
    }

    private void startBoosting() {
        boosting = true;
    }

    public void drawing(GraphicsFW graphicsFW){

        if (!isGameOver){
            if (!hitEnemy){
                if (boosting){
                    animMainPlayerBoost.drawAnimation(graphicsFW, x, y);
                } else {
                    animSpriteMainPlayer.drawAnimation(graphicsFW, x, y);
                }
            } else {
                graphicsFW.drawTexture(UtilResource.shieldHitEnemy, x, y);
                if (timerOnShieldHit.timerDelay(0.4)){
                    hitEnemy = false;
                } else hitEnemy = true;
            }
        } else {
            animExplosionPlayer.drawAnimation(graphicsFW, x, y);
            if (timerOnGameOver.timerDelay(0.5)){
                GameManager.gameOver = true;
            }
        }






    }

    public double getSpeedPlayer(){
        return speed;
    }

    public int getShieldsPlayer(){
        return shieldsPlayer;
    }

    public void hitEnemy() {
        shieldsPlayer--;
        if (shieldsPlayer < 0){
            isGameOver = true;
            timerOnGameOver.startTimer();
        }
        hitEnemy = true;
        timerOnShieldHit.startTimer();
    }
}
