package com.example.myframework;

import android.view.MotionEvent;
import android.view.View;

public class TouchListenerFW implements View.OnTouchListener{

    // куда пользователь нажал, координаты
    float touchX;
    float touchY;


    boolean isTouchDown; // трогает экран
    boolean isTouchUp; // НЕ трогает экран


    float sceneWigth;
    float sceneHight;

    public TouchListenerFW(View view, float sceneWigth, float sceneHight) {
        view.setOnTouchListener(this);
        this.sceneWigth = sceneWigth;
        this.sceneHight = sceneHight;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) { // event - отжал\нажал палец

        synchronized (this){
           isTouchDown = false;
           isTouchUp = false;

           switch (event.getAction()){ // вернет событие по нажатию
               case MotionEvent.ACTION_DOWN:
                   touchX = event.getX()*sceneWigth; // записываем координаты нажатия
                   touchY = event.getY()*sceneHight;
                   isTouchDown = true; // "Ну да, нажали"
                   isTouchUp = false;
                   break;

               case MotionEvent.ACTION_UP:
                   touchX = event.getX()*sceneWigth; // записываем координаты нажатия
                   touchY = event.getY()*sceneHight;
                   isTouchDown = false; // "Не нажали"
                   isTouchUp = true;
                   break;
           }

        }
        return true;
    }

    /**
     * Метод для проверки в каком месте человек коснулся
     * @param x координаты
     * @param y координаты
     * @param touchWidth ширина области
     * @param touchHeight высота области
     * @return
     */
    public boolean getTouchUp(int x, int y, int touchWidth, int touchHeight){
        if (isTouchUp){
            if (touchX >= x && touchX <= x + touchWidth-1 && touchY <= y && touchY >= y - (touchHeight-1)){
                isTouchUp = false;
                return true;
            }
        }
        return false;
    }

    public boolean getTouchDown(int x, int y, int touchWidth, int touchHeight){
        if (isTouchDown){
            if (touchX >= x && touchX <= x + touchWidth-1 && touchY <= y && touchY >= y - (touchHeight-1)){
                isTouchDown = false;
                return true;
            }
        }
        return false;
    }

}
