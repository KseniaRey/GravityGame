package com.example.myframework;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import java.io.IOException;
import java.io.InputStream;

public class GraphicsFW { // для работы с графикой, наш кастомный класс
    // AssetManager - класс, позволяющий работать с файловой системой смартфона

    private AssetManager assetManagerGame;
    private Bitmap frameBufferGame; // для специального компонента, картинка, которую потом будем накладывать
    // на фреймбуффер, а не отрисовывать на канве как обычно
    private Canvas canvasGame;
    private Paint paintGame; // стили, размер текста, текстуры - и тд
    private Bitmap textureGame; // --> текстуры

    public GraphicsFW(AssetManager assetManagerGame, Bitmap frameBufferGame) {
        this.assetManagerGame = assetManagerGame;
        this.frameBufferGame = frameBufferGame;
        this.canvasGame = new Canvas(frameBufferGame); // чтобы не рисовать на самом холсте, даем то, на чем будем рисовать
        this.paintGame = new Paint();
    }

    public void clearScene(int colorRGB){ // очищает сцену, чтобы сделать ее определенного цвета
        canvasGame.drawRGB(colorRGB, colorRGB, colorRGB); // drawRGB по умолчанию просит 3 аргумента, но так как нам
        // надо, чтобы цвет был сплошным, мы передаем 3 раза один цвет. Мы не удаляем, а перекрашиваем пиксели

    }

    /**
     * Метод для отрисовки пикселя
     * @param x координаты
     * @param y координаты
     * @param color цвет
     */
    public void drawPixel(int x, int y, int color){ // аргументы: х и у --> координаты, в каком месте рисуется пиксель
        paintGame.setColor(color);
        canvasGame.drawPoint(x, y, paintGame);
    }

    /**
     * Метод для отрисовки линии
     * @param startX координаты линии
     * @param startY
     * @param stopX
     * @param stopY
     * @param color
     */
    public void drawLine(int startX, int startY, int stopX,  int stopY, int color){ // аргументы: х и у --> координаты, в каком месте рисуется line
        paintGame.setColor(color);
        canvasGame.drawLine(startX, startY, stopX, stopY, paintGame);
    }

    /**
     * Метод для написания текста
     * @param text текст
     * @param x координаты
     * @param y координаты
     * @param color цвет текста
     * @param sizeText размер
     * @param font щрифт
     */
    public void drawText(String text, int x, int y, int color, int sizeText, Typeface font){
        paintGame.setColor(color);
        paintGame.setTextSize(sizeText);
        paintGame.setTypeface(font);
        canvasGame.drawText(text, x, y, paintGame);
    }

    public void drawTexture(Bitmap textureGame, int x, int y){
        canvasGame.drawBitmap(textureGame, x, y, null);
    }

    public int getWidthFrameBuffer(){
        return frameBufferGame.getWidth(); // это стандартный метод битмапа
    }

    public int getHeightFrameBuffer(){
        return frameBufferGame.getHeight(); // это стандартный метод битмапа
    }


    /**
     * Открываем поток, позволяющий открывать файлы в файловой системе
     * @param fileName -> инициализируем его файл менеджером, открываем файл
     * Передаем в текстуру(а это у нас Битмап) поток
     * Проверяем, если пустой, бросаем исключение
     * @return
     */
    public Bitmap newTexture(String fileName){ // принимает название файла из папки assets
        InputStream inputStream = null;
        try {
            inputStream = assetManagerGame.open(fileName); // сарраундим трай кечем
            textureGame = BitmapFactory.decodeStream(inputStream);
            // Битмап фактори декодит наш поток. Поток, по сути, это файл нейм (кладем его в поток в строке выше)
            if (textureGame==null){
                throw new RuntimeException("Невозможно загрузить файл" + fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Невозможно загрузить файл" + fileName);
        }
        if (inputStream!=null){
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return textureGame; // после всего этого надругательства отдаем текстур гейм обратно
    }

    /**
     * Метод принимает атлас текстур и по передаваемым координатам получим картинку, которую и ретерним
     * @param textureAtlas
     * @param x
     * @param y
     * @param widthSprite
     * @param heightSprite
     * @return
     */
    public Bitmap newSprite(Bitmap textureAtlas, int x, int y, int widthSprite, int heightSprite){
        Bitmap newSprite = Bitmap.createBitmap(textureAtlas, x, y, widthSprite, heightSprite);
        return newSprite;
    }

}
