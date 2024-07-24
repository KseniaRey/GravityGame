package com.example.myframework;

public abstract class SceneFW {
    // абстрактный класс сцены - все сцены в игре будут имплементировать этот класс и будут иметь его методы
    public CoreFW coreFW;
    public int sceneWidth;
    public int sceneHeight;
    public GraphicsFW graphicsFW;

    public SceneFW(CoreFW coreFW) {
        this.coreFW = coreFW;
        sceneWidth = coreFW.getGraphicsFW().getWidthFrameBuffer();
        sceneHeight = coreFW.getGraphicsFW().getHeightFrameBuffer();
        graphicsFW = coreFW.getGraphicsFW();

    }

    public abstract void update();
    public abstract void drawing();
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
