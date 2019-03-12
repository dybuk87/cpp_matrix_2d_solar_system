package pl.duch.articles.solar_sys.system;

import pl.duch.articles.solar_sys.math.Matrix;

import java.awt.*;

public abstract class AstralObject {
    protected final float size;
    protected final int color;
    protected Matrix modelMatrix;
    protected Matrix modelViewMatrix;

    protected float[] center = new float[] { 0.0f, 0.0f, 1.0f };
    protected float[] out = new float[] {0.0f, 0.0f, 0.0f };

    public AstralObject(float size, int color) {
        this.size = size;
        this.color = color;
        this.modelMatrix = new Matrix();
        this.modelViewMatrix = new Matrix();
    }

    public abstract void drawImpl(Graphics2D g2, Matrix modelViewMatrix);

    public abstract void update(float dt);

    public void draw(Graphics2D g2, Matrix parentMatrix) {
        calculateModelViewMatrix(parentMatrix);
        drawImpl(g2, modelViewMatrix);
    }

    public Matrix calculateModelViewMatrix(Matrix viewMatrix) {
        Matrix.mul(viewMatrix, modelMatrix, modelViewMatrix);
        return modelViewMatrix;
    }

}
