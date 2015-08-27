package com.example.eric.mygame;

import android.util.Log;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.input.touch.TouchEvent;

/**
 * Created by Eric on 26.08.2015.
 */
public class LevelCompleteWindow extends Sprite {

    private ResourceManager resourceManager;
    private TiledSprite star1;
    private TiledSprite star2;
    private TiledSprite star3;
    //private MenuScene menuScene;
    //private IMenuItem buttonRetry;
    //private IMenuItem buttonMenu;
    private Sprite buttonRetry;
    private Sprite buttonMenu;

    /*@Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()) {
            case 0:
                MainActivity.setMainMenuScene();
                break;
            case 1:
                MainActivity.setGameScene();
                break;
        }
        return false;
    }*/

    public enum StarsCount {
        ZERO,
        ONE,
        TWO,
        THREE,
        ERROR
    }

    public LevelCompleteWindow(ResourceManager resourceManager) {
        super(0, 0, 650, 400, resourceManager.complete_window_region, resourceManager.VBOManager);
        this.resourceManager = resourceManager;
        this.buildWindow();
    }

    private void buildWindow() {
        star1 = new TiledSprite(150, 200, resourceManager.rating_region, resourceManager.VBOManager);
        star2 = new TiledSprite(325, 180, resourceManager.rating_region, resourceManager.VBOManager);
        star3 = new TiledSprite(500, 200, resourceManager.rating_region, resourceManager.VBOManager);

       /* this.menuScene = new MenuScene(resourceManager.camera);
        this.buttonMenu = new ScaleMenuItemDecorator(new SpriteMenuItem(0, resourceManager.menu_region, resourceManager.VBOManager), 0.90f, 1f);
        this.buttonRetry = new ScaleMenuItemDecorator(new SpriteMenuItem(1, resourceManager.retry_region, resourceManager.VBOManager), 0.90f, 1f);

        this.menuScene.addMenuItem(buttonMenu);
        this.menuScene.addMenuItem(buttonRetry);
        this.menuScene.buildAnimations();
        this.menuScene.setBackgroundEnabled(false);

        this.buttonMenu.setPosition(150, 100);
        this.buttonRetry.setPosition(500, 100);
        this.menuScene.setOnMenuItemClickListener(this);*/

        buttonRetry = new Sprite(150, 100, resourceManager.retry_region, resourceManager.VBOManager) {
            @Override
            public boolean onAreaTouched(TouchEvent touchEvent, float x, float y) {
                if (touchEvent.isActionDown()) {
                    MainActivity.setGameScene();
                }
                return true;
            }
        };
        buttonMenu = new Sprite(500, 100, resourceManager.menu_region, resourceManager.VBOManager) {
            @Override
            public boolean onAreaTouched(TouchEvent touchEvent, float x, float y) {
                if (touchEvent.isActionDown()) {
                    MainActivity.setMainMenuScene();
                }
                return true;
            }
        };

        //attachChild(buttonMenu);
        //attachChild(buttonRetry);
        attachChild(star1);
        attachChild(star2);
        attachChild(star3);
        //attachChild(menuScene);
    }

    public void calculateScore(int points, int maxScore, Scene scene) {
        StarsCount starsCount = StarsCount.ERROR;
        if (maxScore != 0) {
            double score = ((double) points) / ((double) maxScore);

            Log.d("LevelCompleteWindow", "Score: " + score + ", achieved points: " + points + " maxPoints: " + maxScore);
            if (score <= 0.33) {
                starsCount = StarsCount.ZERO;
            } else if (score <= 0.5) {
                starsCount = StarsCount.ONE;
            } else if (score <= 0.75) {
                starsCount = StarsCount.TWO;
            } else if (score == 1) {
                starsCount = StarsCount.THREE;
            }
        }
        if (starsCount.equals(StarsCount.ERROR)) {
            Log.d("LevelCompleteWindow", " :::::: ERROR at calculating Score");
        }
        this.display(starsCount, scene);
    }

    public void display(StarsCount starsCount, Scene scene) {
        switch (starsCount) {
            case ZERO:
                star1.setCurrentTileIndex(1);
                star2.setCurrentTileIndex(1);
                star3.setCurrentTileIndex(1);
                break;
            case ONE:
                star1.setCurrentTileIndex(0);
                star2.setCurrentTileIndex(1);
                star3.setCurrentTileIndex(1);
                break;
            case TWO:
                star1.setCurrentTileIndex(0);
                star2.setCurrentTileIndex(0);
                star3.setCurrentTileIndex(1);
                break;
            case THREE:
                star1.setCurrentTileIndex(0);
                star2.setCurrentTileIndex(0);
                star3.setCurrentTileIndex(0);
                break;
            case ERROR:
                star1.setCurrentTileIndex(0);
                star2.setCurrentTileIndex(1);
                star3.setCurrentTileIndex(0);
                break;
        }
        resourceManager.camera.getHUD().setVisible(false);
        resourceManager.camera.setChaseEntity(null);
        setPosition(resourceManager.camera.getCenterX(), resourceManager.camera.getCenterY());
        scene.attachChild(this);
    }
}
