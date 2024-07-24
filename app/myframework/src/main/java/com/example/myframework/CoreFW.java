package com.example.myframework;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CoreFW extends AppCompatActivity {

    private final float FRAME_BUFFER_WIDTH = 800;
    private final float FRAME_BUFFER_HEIGHT = 600;

    private LoopFW loopFW;
    private GraphicsFW graphicsFW;
    private TouchListenerFW touchListenerFW;

    private Display display;
    private Point sizeDisplay; // what is point here? i don't get it
    private Bitmap frameBuffer;
    private SceneFW sceneFW;
    private float sceneWidth;
    private float sceneHeight;

    private boolean stateOnPause;
    private boolean stateOnResume;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); // запрещаем андроиду переходить в
        // спящий режим, пока запущено это приложение. Также требует разрешения в манифесте

        sizeDisplay = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(sizeDisplay);

        // задаем размеры и качество картинки
        frameBuffer = Bitmap.createBitmap((int) FRAME_BUFFER_WIDTH, (int) FRAME_BUFFER_HEIGHT, Bitmap.Config.ARGB_8888);
        sceneWidth = FRAME_BUFFER_WIDTH / sizeDisplay.x; // ширина
        sceneHeight = FRAME_BUFFER_HEIGHT / sizeDisplay.y; // высота

        loopFW = new LoopFW(this, frameBuffer);
        graphicsFW = new GraphicsFW(getAssets(), frameBuffer); // аргументы такие потому что класс графики их принимает
        touchListenerFW = new TouchListenerFW(loopFW, sceneWidth, sceneHeight); // урок 4, иницавлизируем объект тач листенера

        sceneFW = getStartScene();
        stateOnPause = false;
        stateOnResume = false;
        setContentView(loopFW);

    }

    public CoreFW() {

    }

    public void onResume(){
        super.onResume();// что это за запись такакя? разве не рекурсия? Зачем?
        sceneFW.resume();
        loopFW.startGame();

    }

    public void onPause(){
        super.onPause();
        sceneFW.pause();
        loopFW.stopGame();
        stateOnPause = true;
        if (isFinishing()){
            sceneFW.dispose(); // если закончено, удаляем сцену
        }
    }

    public GraphicsFW getGraphicsFW(){
        return graphicsFW;
    }

    public TouchListenerFW getTouchListenerFW(){
        return touchListenerFW;
    }

    public void setScene(SceneFW sceneFW){
        if (sceneFW == null){
            throw new IllegalArgumentException("Невозможно загрузить сцену");
        }
        // если сцена не нулл, то ставим на паузу, удаляем, переоткрываем, обновляем - зачем?
        this.sceneFW.pause();
        this.sceneFW.dispose();
        sceneFW.resume();
        sceneFW.update();
        this.sceneFW = sceneFW;
    }

    public SceneFW getCurrentScene(){
        return sceneFW;
    }

    public SceneFW getStartScene(){
        return sceneFW;
    }
}
