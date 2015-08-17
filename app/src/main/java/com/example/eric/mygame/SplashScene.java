package com.example.eric.mygame;

import android.util.Log;

import com.example.eric.mygame.SceneManager.SceneType;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

public class SplashScene extends BaseScene {
    private Sprite splash;

    public SplashScene(ResourceManager resourceManager) {
        super(resourceManager);
    }

    @Override
    public void createScene() {
        Log.d("Splash-Scene", "Region: " + resourceManager.toString());
        splash = new Sprite(0, 0, resourceManager.splash_region, vbom) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        };

        splash.setScale(1.5f);
        splash.setPosition(400, 240);
        attachChild(splash);
    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene() {
        this.splash.detachSelf();
        this.splash.dispose();
        this.detachSelf();
        this.dispose();
    }

    @Override
    public String toString() {
        return "SplashScene";
    }
}