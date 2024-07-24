package com.example.testgamefromyt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myframework.CoreFW;
import com.example.myframework.LoopFW;
import com.example.myframework.SceneFW;
import com.example.testgamefromyt.classes.LoaderAssets;
import com.example.testgamefromyt.scenes.MainMenuScene;

public class Main extends CoreFW {

    public SceneFW getStartScene(){
        LoaderAssets LoaderAssets = new LoaderAssets(this, this.getGraphicsFW()); // сначала подгружаем
        return new MainMenuScene(this); // потом запускаем
    }


}