package com.example.eric.mygame;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;

/**
 * Created by Eric on 26.08.2015.
 */
public class PlatformStandard extends Platform {


    public PlatformStandard(float pX, float pY, ResourceManager resourceManager) {
        super(pX, pY, resourceManager.platform_region, resourceManager);
    }

    @Override
    public void createBody(PhysicsWorld physicsWorld, FixtureDef fixtureDef) {
        PhysicsFactory.createBoxBody(physicsWorld, this, BodyDef.BodyType.StaticBody, fixtureDef).setUserData("platformStandard");
    }
}
