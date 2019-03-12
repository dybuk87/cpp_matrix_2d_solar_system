package pl.duch.articles.solar_sys.system;

import pl.duch.articles.solar_sys.math.Matrix;

import java.awt.*;


public class Orbit extends AstralObject {
    private AstralObject object;

    private float angle;
    private float angleSpeed;

    private Matrix shift;
    private Matrix rotation;

    public Orbit(AstralObject object, float angle, float angleSpeed, float radius) {
        super(radius * 2.0f, 0x999999);
        this.object = object;
        this.angle = angle;
        this.angleSpeed = angleSpeed;

        this.rotation = new Matrix();
        this.shift = new Matrix();

        this.shift.setTranslate(0.0f, radius);
    }

    public void update(float dt) {
        angle += angleSpeed * dt / 1000.0f;
        rotation.setRotate(angle);

        Matrix.mul(rotation, shift, modelMatrix);

        this.object.update(dt);
    }

    @Override
    public void draw(Graphics2D g2, Matrix parentMatrix) {
        g2.setColor(new Color(color));

        int radius = (int)size/2;
        parentMatrix.mulVector(center, 0, out , 0, 1);

        int x = (int)out[0];
        int y = (int)out[1];

        g2.drawArc(x - radius, y - radius, (int)radius * 2, (int)radius * 2, 0, 360);
        super.draw(g2, parentMatrix);
    }

    @Override
    public void drawImpl(Graphics2D g2, Matrix modelViewMatrix) {
        object.draw(g2, modelViewMatrix);
    }


}
