LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := andenginephysicsbox2dextension
LOCAL_SRC_FILES := \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Android.mk \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Application.mk \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\build.bat \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\build.sh \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Android.mk \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Body.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\CircleShape.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Contact.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\ContactImpulse.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\DistanceJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Fixture.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\FrictionJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\GearJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Joint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\LineJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Manifold.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\MouseJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\PolygonShape.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\PrismaticJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\PulleyJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\RevoluteJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Shape.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\World.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\b2BroadPhase.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\b2CollideCircle.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\b2CollidePolygon.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\b2Collision.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\b2Distance.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\b2DynamicTree.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\b2TimeOfImpact.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\Shapes\b2CircleShape.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Collision\Shapes\b2PolygonShape.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Common\b2BlockAllocator.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Common\b2Math.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Common\b2Settings.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Common\b2StackAllocator.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\b2Body.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\b2ContactManager.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\b2Fixture.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\b2Island.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\b2World.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\b2WorldCallbacks.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Contacts\b2CircleContact.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Contacts\b2Contact.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Contacts\b2ContactSolver.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Contacts\b2PolygonAndCircleContact.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Contacts\b2PolygonContact.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Contacts\b2TOISolver.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2DistanceJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2FrictionJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2GearJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2Joint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2LineJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2MouseJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2PrismaticJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2PulleyJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2RevoluteJoint.cpp \
	C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni\Box2D\Dynamics\Joints\b2WeldJoint.cpp \

LOCAL_C_INCLUDES += C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\main\jni
LOCAL_C_INCLUDES += C:\Users\Eric\AndroidStudioProjects\MyGame\andEnginePhysicsBox2DExtension\src\release\jni

include $(BUILD_SHARED_LIBRARY)
