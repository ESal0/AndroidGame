package org.andengine.entity.sprite.vbo;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.LowMemoryVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;

import java.nio.FloatBuffer;

/**
 * (c) 2012 Zynga Inc.
 *
 * @author Nicolas Gramlich <ngramlich@zynga.com>
 * @since 18:40:47 - 28.03.2012
 */
public class LowMemorySpriteVertexBufferObject extends LowMemoryVertexBufferObject implements ISpriteVertexBufferObject {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public LowMemorySpriteVertexBufferObject(final VertexBufferObjectManager pVertexBufferObjectManager, final int pCapacity, final DrawType pDrawType, final boolean pAutoDispose, final VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
        super(pVertexBufferObjectManager, pCapacity, pDrawType, pAutoDispose, pVertexBufferObjectAttributes);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onUpdateColor(final Sprite pSprite) {
        final FloatBuffer bufferData = this.mFloatBuffer;

        final float packedColor = pSprite.getColor().getABGRPackedFloat();

        bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.COLOR_INDEX, packedColor);
        bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.COLOR_INDEX, packedColor);
        bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.COLOR_INDEX, packedColor);
        bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.COLOR_INDEX, packedColor);

        this.setDirtyOnHardware();
    }

    @Override
    public void onUpdateVertices(final Sprite pSprite) {
        final FloatBuffer bufferData = this.mFloatBuffer;

        final float width = pSprite.getWidth(); // TODO Optimize with field access?
        final float height = pSprite.getHeight(); // TODO Optimize with field access?

        bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, 0);
        bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, 0);

        bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, 0);
        bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, height);

        bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, width);
        bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, 0);

        bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_X, width);
        bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.VERTEX_INDEX_Y, height);

        this.setDirtyOnHardware();
    }

    @Override
    public void onUpdateTextureCoordinates(final Sprite pSprite) {
        final FloatBuffer bufferData = this.mFloatBuffer;

        final ITextureRegion textureRegion = pSprite.getTextureRegion(); // TODO Optimize with field access?

        final float u;
        final float v;
        final float u2;
        final float v2;

        if (pSprite.isFlippedVertical()) { // TODO Optimize with field access?
            if (pSprite.isFlippedHorizontal()) { // TODO Optimize with field access?
                u = textureRegion.getU2();
                u2 = textureRegion.getU();
                v = textureRegion.getV2();
                v2 = textureRegion.getV();
            } else {
                u = textureRegion.getU();
                u2 = textureRegion.getU2();
                v = textureRegion.getV2();
                v2 = textureRegion.getV();
            }
        } else {
            if (pSprite.isFlippedHorizontal()) { // TODO Optimize with field access?
                u = textureRegion.getU2();
                u2 = textureRegion.getU();
                v = textureRegion.getV();
                v2 = textureRegion.getV2();
            } else {
                u = textureRegion.getU();
                u2 = textureRegion.getU2();
                v = textureRegion.getV();
                v2 = textureRegion.getV2();
            }
        }

        if (textureRegion.isRotated()) {
            bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u);
            bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v);

            bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u2);
            bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v);

            bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u);
            bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v2);

            bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u2);
            bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v2);
        } else {
            bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u);
            bufferData.put(0 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v2);

            bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u);
            bufferData.put(1 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v);

            bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u2);
            bufferData.put(2 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v2);

            bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_U, u2);
            bufferData.put(3 * Sprite.VERTEX_SIZE + Sprite.TEXTURECOORDINATES_INDEX_V, v);
        }

        this.setDirtyOnHardware();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================
}