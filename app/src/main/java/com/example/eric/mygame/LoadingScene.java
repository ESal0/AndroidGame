package com.example.eric.mygame;

import org.andengine.entity.scene.background.Background;
import org.andengine.entity.text.Text;

import static com.example.eric.mygame.SceneManager.SceneType;

/**
 * Created by Eric on 17.08.2015.
 */
public class LoadingScene extends BaseScene {
    public LoadingScene(ResourceManager resourceManager) {
        super(resourceManager);
    }

    @Override
    public void createScene() {
        setBackground(new Background(0f, 0f, 10f));
        attachChild(new Text(100, 430, resourceManager.font, "loading....", resourceManager.VBOManager));
    }

    @Override
    public void onBackKeyPressed() {
        return;
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene() {

    }

    @Override
    public String toString() {
        return null;
    }
}
