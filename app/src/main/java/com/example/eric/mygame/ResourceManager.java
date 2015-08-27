package com.example.eric.mygame;

import android.util.Log;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.util.adt.color.Color;

public class ResourceManager {

    public final Engine engine;
    public final BaseGameActivity gameActivity;
    public final BoundCamera camera;
    public final VertexBufferObjectManager VBOManager;

    public ITextureRegion splash_region;
    private BitmapTextureAtlas splashTextureAtlas;
    private BuildableBitmapTextureAtlas menuTextureAtlas;
    private BuildableBitmapTextureAtlas gameTextureAtlas;

    public ITextureRegion menu_bg_region;
    public ITextureRegion play_region;
    public ITextureRegion options_region;
    public ITextureRegion quit_region;
    public ITextureRegion coin_region;
    public ITextureRegion platform_region;
    public ITextureRegion platformMoving_region;
    public ITextureRegion platformBrittle_region;
    public ITextureRegion complete_window_region;
    public ITextureRegion platformFinish_region;
    public ITextureRegion retry_region;
    public ITextureRegion menu_region;
    public ITextureRegion background_region;
    public ITiledTextureRegion rating_region;
    public ITiledTextureRegion player_region;

    public Font font;

    public ResourceManager(Engine engine, MainActivity gameActivity, BoundCamera camera, VertexBufferObjectManager VBOManager) {
        this.engine = engine;
        this.gameActivity = gameActivity;
        this.camera = camera;
        this.VBOManager = VBOManager;
    }

    public void loadMenuResources() {
        loadMenuGraphics();
        loadFonts();
        loadMenuAudio();
    }

    public void loadGameResources() {
        loadGameGraphics();
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
            Log.d("ResourceManager", " Menu Loaded, " + menuTextureAtlas.toString());
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Log.e("ResourceManager", " " + e.toString());
        }
    }

    private void loadMenuAudio() {

    }

    private void loadGameGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
        this.gameTextureAtlas = new BuildableBitmapTextureAtlas(gameActivity.getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.coin_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "coin.png");
        this.platform_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "platformStandard.png");
        this.platformMoving_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "platformMoving.png");
        this.platformBrittle_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "platformMovingBrittle.png");
        this.player_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, this.gameActivity, "playerSample.png", 3, 1);
        this.rating_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, this.gameActivity, "stars.png", 2, 1);
        this.complete_window_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "levelComplete.png");
        this.retry_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "buttonretry.png");
        this.menu_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "buttonmenu.png");
        this.platformFinish_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "finish.png");
        this.background_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, this.gameActivity, "background.png");

        try {
            this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
            this.gameTextureAtlas.load();
        } catch (ITextureAtlasBuilder.TextureAtlasBuilderException e) {
            Log.d("ResourceManager", e.getMessage());
        }
    }

    private void loadFonts() {
        FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(gameActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.font = FontFactory.createStrokeFromAsset(gameActivity.getFontManager(), mainFontTexture, gameActivity.getAssets(), "BebasNeue.otf", 50, true, Color.WHITE.getABGRPackedInt(), 2, Color.BLACK.getABGRPackedInt());
        this.font.load();
    }

    private void loadGameAudio() {

    }

    public void loadSplashScreen() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        this.splashTextureAtlas = new BitmapTextureAtlas(gameActivity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
        this.splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, gameActivity, gameActivity.getResources().getString(R.string.splashLogo), 0, 0);
        this.splashTextureAtlas.load();

        Log.d("ResourceManager", "SplashRegion loaded");
    }

    public void unloadSplashScreen() {
        this.splashTextureAtlas.unload();
        this.splash_region = null;
    }

    public void loadMenuTextures() {
        this.menuTextureAtlas.load();
    }

    public void unloadMenuTextures() {
        this.menuTextureAtlas.unload();
    }

    public void loadGameTextures() {
        this.gameTextureAtlas.load();
    }

    public void unloadGameTextures() {
        this.gameTextureAtlas.unload();
    }

    public void loadGameScene() {

    }
}