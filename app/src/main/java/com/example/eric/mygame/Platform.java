package com.example.eric.mygame;

import com.badlogic.gdx.physics.box2d.FixtureDef;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;

/**
 * Created by Eric on 26.08.2015.
 */
public abstract class Platform extends Sprite {

    ResourceManager resourceManager;

    public Platform(float pX, float pY, ITextureRegion pTextureRegion, ResourceManager resourceManager) {
        super(pX, pY, pTextureRegion, resourceManager.VBOManager);
        this.resourceManager = resourceManager;
    }

    public abstract void createBody(PhysicsWorld physicsWorld, FixtureDef fixtureDef);
}
