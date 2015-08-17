package com.example.eric.mygame;

import android.util.Log;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;

public class ResourceManager {

    public final Engine engine;
    public final BaseGameActivity gameActivity;
    public final BoundCamera camera;
    public final VertexBufferObjectManager VBOManager;

    public ITextureRegion splash_region;
    private BitmapTextureAtlas splashTextureAtlas;

    public ITextureRegion menu_bg_region;
    public ITextureRegion play_region;
    public ITextureRegion options_region;
    public ITextureRegion quit_region;
    private BuildableBitmapTextureAtlas menuTextureAtlas;

    public ResourceManager(Engine engine, MainActivity gameActivity, BoundCamera camera, VertexBufferObjectManager VBOManager) {
        this.engine = engine;
        this.gameActivity = gameActivity;
        this.camera = camera;
        this.VBOManager = VBOManager;
    }

    public void loadMenuResources() {
        loadMenuGraphics();
        loadMenuAudio();
    }

    public void loadGameResources() {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }

    private void loadMenuGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
        this.menuTextureAtlas = new BuildableBitmapTextureAtlas(gameActivity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.menu_bg_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, gameActivity.getResources().getString(R.string.mainMenu));
        this.play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, gameActivity.getResources().getString(R.string.buttonPlay));
        this.options_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, gameActivity.getResources().getString(R.string.buttonOptions));
        this.quit_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, gameActivity, gameActivity.getResources().getString(R.string.buttonQuit));

        try {
            this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.menuTextureAtlas.load();
            Log.d("ResourceManagee", " Menu Loaded, " + menuTextureAtlas.toString());
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Log.e("ResourceManager", " " + e.toString());
        }
    }

    private void loadMenuAudio() {

    }

    private void loadGameGraphics() {

    }

    private void loadGameFonts() {
    }

    private void loadGameAudio() {

    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        splashTextureAtlas = new BitmapTextureAtlas(gameActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, gameActivity, gameActivity.getResources().getString(R.string.splashLogo), 0, 0);
        splashTextureAtlas.load();

        Log.d("ResourceManager", "SplashRegion loaded");
    }

    public void unloadSplashScreen() {
        this.splashTextureAtlas.unload();
        this.splash_region = null;
    }
}