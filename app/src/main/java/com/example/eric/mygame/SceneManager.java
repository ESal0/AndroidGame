package com.example.eric.mygame;

import org.andengine.engine.Engine;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;


/**
 * @author Mateusz Mysliwiec
 * @author www.matim-dev.com
 * @version 1.0
 */
public class SceneManager {
    private BaseScene splashScene;
    private BaseScene loadingScene;
    private BaseScene gameScene;
    private BaseScene menuScene;

    private SceneType currentSceneType;
    private BaseScene currentScene;
    private ResourceManager resourceManager;
    private Engine engine;

    public SceneManager(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
        this.engine = resourceManager.engine;
    }

    public enum SceneType {
        SCENE_SPLASH,
        SCENE_LOADING,
        SCENE_MENU,
        SCENE_GAME
    }

    public void setSceneType(SceneType sceneType) {
        switch (sceneType) {
            case SCENE_GAME:
                this.setScene(gameScene);
                break;
            case SCENE_LOADING:
                this.setScene(loadingScene);
                break;
            case SCENE_MENU:
                this.setScene(menuScene);
                break;
            case SCENE_SPLASH:
                this.setScene(splashScene);
        }
    }

    private void setScene(BaseScene scene) {
        engine.setScene(scene);
        //Log.d("SceneManager", " Scene:" + scene.toString());
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }

    public SceneType getCurrentSceneType() {
        return this.currentSceneType;
    }

    public BaseScene getCurrentScene() {
        return this.currentScene;
    }

    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback) {
        this.resourceManager.loadSplashScreen();
        splashScene = new SplashScene(this.resourceManager);
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    public void disposeSplashScene() {
        this.resourceManager.unloadSplashScreen();
        this.splashScene.disposeScene();
        splashScene = null;
    }

    public void createMenuScene() {
        this.resourceManager.loadMenuResources();
        this.menuScene = new MainMenuScene(this.resourceManager);
        this.setScene(menuScene);
        this.disposeSplashScene();
    }
}