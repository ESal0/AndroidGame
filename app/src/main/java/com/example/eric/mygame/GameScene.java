package com.example.eric.mygame;

import android.util.Log;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;

import org.andengine.engine.camera.hud.HUD;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.LoopEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.ParallaxBackground;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.extension.physics.box2d.FixedStepPhysicsWorld;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.util.SAXUtils;
import org.andengine.util.adt.align.HorizontalAlign;
import org.andengine.util.level.EntityLoader;
import org.andengine.util.level.constants.LevelConstants;
import org.andengine.util.level.simple.SimpleLevelEntityLoaderData;
import org.andengine.util.level.simple.SimpleLevelLoader;
import org.xml.sax.Attributes;

import java.io.IOException;

import static com.example.eric.mygame.SceneManager.SceneType;

/**
 * Created by Eric on 17.08.2015.
 */
public class GameScene extends BaseScene implements IOnSceneTouchListener {

    private static final String TAG_ENTITY = "entity";
    private static final String TAG_ENTITY_ATTR_X = "x";
    private static final String TAG_ENTITY_ATTR_Y = "y";
    private static final String TAG_ENTITY_ATTR_TYPE = "type";
    private static final String TAG_LEVEL = "level";

    private static final Object TAG_ENTITY_ATTR_TYPE_VALUE_PLATFORM_STANDARD = "platformStandard";
    private static final Object TAG_ENTITY_ATTR_TYPE_VALUE_PLATFORM_MOVING = "platformMoving";
    private static final Object TAG_ENTITY_ATTR_TYPE_VALUE_PLATFORM_BROKEN = "platformBroken";
    private static final Object TAG_ENTITY_ATTR_TYPE_VALUE_COIN = "coin";
    private static final Object TAG_ENTITY_ATTR_TYPE_VALUE_PLAYER = "player";
    private static final Object TAG_ENTITY_ATTR_TYPE_VALUE_FINISH = "finish";


    private boolean firstTouch = true;
    private Player player;
    private HUD hud;
    private Text scoreText;
    private Text gameOverText;
    private boolean gameOver = false;
    private int scorePoints = 0;
    private int scorePerCoin = 250;
    private int maxScore;
    private PhysicsWorld physicsWorld;
    private LevelCompleteWindow levelCompleteWindow;

    public GameScene(ResourceManager resourceManager) {
        super(resourceManager);
    }

    @Override
    public void createScene() {
        this.setOnSceneTouchListener(this);
        this.scorePerCoin = 250;
        this.createPhysics();
        this.createBackground();
        this.createHud();
        this.createGameOverText();
        this.levelCompleteWindow = new LevelCompleteWindow(this.resourceManager);
        loadLevel(1);
    }

    private void createPhysics() {
        this.physicsWorld = new FixedStepPhysicsWorld(60, new Vector2(0, -17), false);
        this.physicsWorld.setContactListener(this.setContactListener());
        this.registerUpdateHandler(this.physicsWorld);
    }

    @Override
    public void onBackKeyPressed() {
        MainActivity.loadMenuScene();
    }

    @Override
    public SceneType getSceneType() {
        return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene() {
        this.camera.setHUD(null);
        this.camera.setChaseEntity(null);
        this.camera.setCenter(400, 240);
        this.physicsWorld.dispose();
        this.resourceManager.unloadGameTextures();
    }

    @Override
    public String toString() {
        return "GameScene";
    }

    public void createBackground() {
        ParallaxBackground background = new ParallaxBackground(0, 0, 0);
        background.attachParallaxEntity(new ParallaxBackground.ParallaxEntity(0, new Sprite(camera.getCenterX(), camera.getCenterY(), resourceManager.background_region, vbom)));
        this.setBackground(background);
    }

    private void createHud() {
        this.hud = new HUD();

        this.scoreText = new Text(20, 420, resourceManager.font, "Score: 0123456789", new TextOptions(HorizontalAlign.LEFT), this.resourceManager.VBOManager);
        this.scoreText.setAnchorCenter(0, 0);
        this.scoreText.setText("Score: " + this.scorePoints);
        this.hud.attachChild(this.scoreText);
        this.camera.setHUD(this.hud);
    }

    public void addScore(int points) {
        this.scorePoints += points;
        this.scoreText.setText("Score: " + this.scorePoints);
    }

    private void loadLevel(final int levelID) {
        final SimpleLevelLoader levelLoader = new SimpleLevelLoader(this.vbom);
        final FixtureDef fixtureDef = PhysicsFactory.createFixtureDef(0, 0.01f, 0.5f);

        levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(LevelConstants.TAG_LEVEL) {

            @Override
            public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, SimpleLevelEntityLoaderData pEntityLoaderData) throws IOException {
                final int width = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH);
                final int height = SAXUtils.getIntAttributeOrThrow(pAttributes, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT);

                camera.setBounds(0, 0, width, height);
                camera.setBoundsEnabled(true);

                return GameScene.this;
            }
        });

        levelLoader.registerEntityLoader(new EntityLoader<SimpleLevelEntityLoaderData>(TAG_ENTITY) {
            @Override
            public IEntity onLoadEntity(String pEntityName, IEntity pParent, Attributes pAttributes, SimpleLevelEntityLoaderData pEntityLoaderData) throws IOException {

                final int posX = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTR_X);
                final int posY = SAXUtils.getIntAttributeOrThrow(pAttributes, TAG_ENTITY_ATTR_Y);
                final String type = SAXUtils.getAttributeOrThrow(pAttributes, TAG_ENTITY_ATTR_TYPE);

                Sprite levelObject;

                if (type.equals(TAG_ENTITY_ATTR_TYPE_VALUE_PLATFORM_STANDARD)) {
                    PlatformStandard platform = new PlatformStandard(posX, posY, resourceManager);
                    platform.createBody(physicsWorld, fixtureDef);
                    levelObject = platform;
                } else if (type.equals(TAG_ENTITY_ATTR_TYPE_VALUE_PLATFORM_MOVING)) {
                    levelObject = new Sprite(posX, posY, resourceManager.platformMoving_region, vbom);
                    final Body body = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyDef.BodyType.StaticBody, fixtureDef);
                    body.setUserData("platformMoving");
                    physicsWorld.registerPhysicsConnector(new PhysicsConnector(levelObject, body, true, false));
                } else if (type.equals(TAG_ENTITY_ATTR_TYPE_VALUE_PLATFORM_BROKEN)) {
                    levelObject = new Sprite(posX, posY, resourceManager.platformBrittle_region, vbom);
                    final Body body = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyDef.BodyType.StaticBody, fixtureDef);
                    body.setUserData("platformBroken");
                    physicsWorld.registerPhysicsConnector(new PhysicsConnector(levelObject, body, true, false));

                } else if (type.equals(TAG_ENTITY_ATTR_TYPE_VALUE_COIN)) {
                    increaseMaxScore();
                    levelObject = new Sprite(posX, posY, resourceManager.coin_region, vbom) {
                        @Override
                        protected void onManagedUpdate(float pSecondsElapsed) {
                            super.onManagedUpdate(pSecondsElapsed);
                            if (player.collidesWith(this)) {
                                addScore(scorePerCoin);
                                this.setVisible(false);
                                this.setIgnoreUpdate(true);
                            }
                        }
                    };
                    levelObject.registerEntityModifier(new LoopEntityModifier(new ScaleModifier(1, 1, 1)));

                } else if (type.equals(TAG_ENTITY_ATTR_TYPE_VALUE_PLAYER)) {
                    player = new Player(posX, posY, vbom, camera, physicsWorld, resourceManager) {
                        @Override
                        public void onDie() {
                            if (!gameOver) {
                                displayGameOver();
                            }
                        }
                    };
                    player.setUserData("player");
                    levelObject = player;
                } else if (type.equals(TAG_ENTITY_ATTR_TYPE_VALUE_FINISH)) {
                    levelObject = new Sprite(posX, posY, resourceManager.platformFinish_region, vbom) {
                        @Override
                        protected void onManagedUpdate(float pSecondsElapsed) {
                            super.onManagedUpdate(pSecondsElapsed);
                            if (player.collidesWith(this)) {

                                player.stopRunning();
                                this.setIgnoreUpdate(true);
                                levelCompleteWindow.calculateScore(scorePoints, maxScore, GameScene.this);
                            }
                        }
                    };
                    final Body body = PhysicsFactory.createBoxBody(physicsWorld, levelObject, BodyDef.BodyType.StaticBody, fixtureDef);
                    body.setUserData("finish");
                    levelObject.registerEntityModifier(new LoopEntityModifier(new ScaleModifier(2f, 1, 1.3f)));
                    physicsWorld.registerPhysicsConnector(new PhysicsConnector(levelObject, body));
                } else {
                    throw new IllegalArgumentException();
                }
                levelObject.setCullingEnabled(true);
                return levelObject;
            }
        });
        levelLoader.loadLevelFromAsset(activity.getAssets(), "level/" + levelID + ".lvl");
    }

    @Override
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        if (pSceneTouchEvent.isActionDown()) {
            if (firstTouch) {
                firstTouch = !firstTouch;
                this.player.setRunning();
            } else {
                this.player.jump();
            }
        }
        return false;
    }

    private void createGameOverText() {
        this.gameOverText = new Text(0, 0, resourceManager.font, "Game Over", vbom, DrawType.STATIC);
    }

    private void displayGameOver() {
        camera.setChaseEntity(null);
        gameOverText.setPosition(camera.getCenterX(), camera.getCenterY());
        attachChild(this.gameOverText);
        this.gameOver = true;
    }

    private ContactListener setContactListener() {
        ContactListener contactListener = new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                final Fixture fixture1 = contact.getFixtureA();
                final Fixture fixture2 = contact.getFixtureB();

                Object userDataFix1 = fixture1.getBody().getUserData();
                Object userDataFix2 = fixture2.getBody().getUserData();


                if (userDataFix1 != null && userDataFix2 != null) {
                    Log.d("GameScene", "BEGIN CONTACT  fix1 userData = " + fixture1.getBody().getUserData() + " --- fix2 userData = " + fixture2.getBody().getUserData());

                    if (userDataFix1.equals("player") || userDataFix2.equals("player")) {
                        player.increaseContactCounter();
                    }

                    if ((userDataFix1.equals("player") || userDataFix2.equals("player")) && (userDataFix2.equals("platformMoving") || userDataFix1.equals("platformMoving"))) {
                        if (userDataFix2.equals("platformMoving")) {
                            fixture2.getBody().setType(BodyDef.BodyType.DynamicBody);
                        } else {
                            fixture1.getBody().setType(BodyDef.BodyType.DynamicBody);
                        }
                    } else if ((userDataFix1.equals("player") || userDataFix2.equals("player")) && (userDataFix2.equals("platformBroken") || userDataFix1.equals("platformBroken"))) {
                        if (userDataFix2.equals("platformBroken")) {
                            engine.registerUpdateHandler(new TimerHandler(0.5f, new ITimerCallback() {
                                @Override
                                public void onTimePassed(TimerHandler pTimerHandler) {
                                    pTimerHandler.reset();
                                    engine.unregisterUpdateHandler(pTimerHandler);
                                    fixture2.getBody().setType(BodyDef.BodyType.DynamicBody);
                                }
                            }));
                        } else {
                            engine.registerUpdateHandler(new TimerHandler(0.5f, new ITimerCallback() {
                                @Override
                                public void onTimePassed(TimerHandler pTimerHandler) {
                                    pTimerHandler.reset();
                                    engine.unregisterUpdateHandler(pTimerHandler);
                                    fixture1.getBody().setType(BodyDef.BodyType.DynamicBody);
                                }
                            }));
                        }
                    }
                }
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixture1 = contact.getFixtureA();
                Fixture fixture2 = contact.getFixtureB();

                Object userDataFix1 = fixture1.getBody().getUserData();
                Object userDataFix2 = fixture2.getBody().getUserData();

                if (userDataFix1 != null && userDataFix2 != null) {
                    Log.d("GameScene", " END CONTACT  fix1 userData = " + fixture1.getBody().getUserData() + " --- fix2 userData = " + fixture2.getBody().getUserData());
                    if (userDataFix1.equals("player") || userDataFix2.equals("player")) {
                        player.decreaseContactCounter();
                    }
                }
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        };

        return contactListener;
    }

    private void increaseMaxScore() {
        maxScore += scorePerCoin;
        Log.d("GameScene", "MaxScore: " + maxScore + " added " + scorePerCoin);
    }
}
