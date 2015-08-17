package com.example.eric.mygame;

import android.app.Activity;

import com.example.eric.mygame.SceneManager.SceneType;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

/**
 * Created by Eric on 13.08.2015.
 */
public abstract class BaseScene extends Scene {

    protected Engine engine;
    protected Activity activity;
    protected VertexBufferObjectManager vbom;
    protected BoundCamera camera;
    protected ResourceManager resourceManager;

//---------------------------------------------
// CONSTRUCTOR
//---------------------------------------------

    public BaseScene(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.engine = resourceManager.engine;
        this.activity = resourceManager.gameActivity;
        this.vbom = resourceManager.VBOManager;
        this.camera = resourceManager.camera;
        createScene();
    }

//---------------------------------------------
// ABSTRACTION
//---------------------------------------------

    public abstract void createScene();

    public abstract void onBackKeyPressed();

    public abstract SceneType getSceneType();

    public abstract void disposeScene();

    public abstract String toString();
}