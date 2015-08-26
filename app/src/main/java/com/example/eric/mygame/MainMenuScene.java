package com.example.eric.mygame;

import android.util.Log;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.util.GLState;

import static com.example.eric.mygame.SceneManager.SceneType;

/**
 * Created by Eric on 16.08.2015.
 */
public class MainMenuScene extends BaseScene implements MenuScene.IOnMenuItemClickListener {

    private MenuScene menuChildScene;
    private final int MENU_PLAY = 0;
    private final int MENU_OPTIONS = 1;
    private final int MENU_QUIT = 2;

    public MainMenuScene(ResourceManager resourceManager) {
        super(resourceManager);
    }

    private void createBackground() {
        attachChild(new Sprite(400, 240, this.resourceManager.menu_bg_region, this.resourceManager.VBOManager) {
            @Override
            protected void preDraw(GLState pGLState, Camera pCamera) {
                super.preDraw(pGLState, pCamera);
                pGLState.enableDither();
            }
        });
    }

    @Override
    public void createScene() {
        createBackground();
        createMenuScene();
    }

    private void createMenuScene() {
        this.menuChildScene = new MenuScene(resourceManager.camera);
        this.menuChildScene.setPosition(0, 0);
        final IMenuItem startMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, this.resourceManager.play_region, resourceManager.VBOManager), 0.85f, 1);
        final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, this.resourceManager.options_region, resourceManager.VBOManager), 0.85f, 1);
        final IMenuItem quitMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_QUIT, this.resourceManager.quit_region, resourceManager.VBOManager), 0.85f, 1);

        menuChildScene.addMenuItem(startMenuItem);
        menuChildScene.addMenuItem(optionsMenuItem);
        menuChildScene.addMenuItem(quitMenuItem);

        menuChildScene.buildAnimations();
        menuChildScene.setBackgroundEnabled(false);

        startMenuItem.setPosition(startMenuItem.getX(), startMenuItem.getY() - 20);
        Log.d("MainMenuScene", "startMenuItem X,Y :" + startMenuItem.getX() + " " + startMenuItem.getY());
        optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY() - 20);
        Log.d("MainMenuScene", "optionsMenuItem X,Y :" + optionsMenuItem.getX() + " " + optionsMenuItem.getY());
        quitMenuItem.setPosition(quitMenuItem.getX(), quitMenuItem.getY() - 20);
        Log.d("MainMenuScene", "quitMenuItem X,Y :" + quitMenuItem.getX() + " " + quitMenuItem.getY());

        attachChild(new Text(400, 425, resourceManager.font, "Run n' Jump!", resourceManager.VBOManager));
        menuChildScene.setOnMenuItemClickListener(this);
        setChildScene(menuChildScene);
    }

    @Override
    public void onBackKeyPressed() {
        System.exit(0);
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene() {

    }

    @Override
    public String toString() {
        return "MainMenuScene";
    }

    @Override
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY) {
        switch (pMenuItem.getID()) {
            case MENU_PLAY:
                MainActivity.setGameScene();
                break;
            case MENU_OPTIONS:
                return true;
            case MENU_QUIT:
                System.exit(0);
                return false;
        }
        return false;
    }
}
