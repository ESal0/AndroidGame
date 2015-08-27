package com.example.eric.mygame;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

/**
 * Created by Eric on 25.08.2015.
 */
public abstract class Player extends AnimatedSprite {

    private Body body;
    private boolean canRun = false;
    final long[] PLAYER_ANIMATE = new long[]{100, 100, 100};
    private int groundContactCounter = 0;


    public Player(float pX, float pY, VertexBufferObjectManager vbo, Camera camera, PhysicsWorld physicsWorld, ResourceManager resourceManager) {
        super(pX, pY, resourceManager.player_region, vbo);
        this.createPhysics(camera, physicsWorld);
        camera.setChaseEntity(this);
    }

    public abstract void onDie();

    private void createPhysics(final Camera camera, PhysicsWorld physicsWorld) {
        this.body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));
        this.body.setUserData("player");
        this.body.setFixedRotation(true);

        physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, false) {
            @Override
            public void onUpdate(float pSecondsElapsed) {
                super.onUpdate(pSecondsElapsed);
                camera.onUpdate(0.1f);
                if (getY() <= 0) {
                    onDie();
                }

                if (canRun) {
                    body.setLinearVelocity(new Vector2(5.5f, body.getLinearVelocity().y));
                }
            }
        });
    }

    public void setRunning() {
        this.canRun = true;
        this.animate(PLAYER_ANIMATE, 0, 2, true);
    }

    public void stopRunning() {
        this.canRun = false;
        body.setLinearVelocity(new Vector2(0, 0));
        this.stopAnimation();
    }

    public void jump() {
        if (this.groundContactCounter > 0) {
            this.body.setLinearVelocity(new Vector2(this.body.getLinearVelocity().x, 12));
        }
    }

    public void increaseContactCounter() {
        this.groundContactCounter++;
    }

    public void decreaseContactCounter() {
        this.groundContactCounter--;
    }
}
