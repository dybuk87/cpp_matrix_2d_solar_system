package pl.duch.articles.solar_sys.system;

import pl.duch.articles.solar_sys.math.Matrix;

import java.awt.*;

public class NaturalSatellite extends AstralObject {

    private float rotate = 0.0f;
    private float rotateSpeed = 360.0f/1.0f;

    private float[] localShift;

    public NaturalSatellite(float size) {
        super(size, 0x999999);

        localShift = new float[] {
                size/2.f, 0.0f, 1.0f,
        };
    }

    @Override
    public void drawImpl(Graphics2D g2, Matrix modelViewMatrix) {
        modelViewMatrix.mulVector(center, 0, out , 0, 1);
        int x = (int)out[0];
        int y = (int)out[1];
        int hs = (int)size;

        g2.setColor(Color.MAGENTA);
        g2.drawArc(x - hs/2, y - hs/2, hs, hs, 0, 360);


        modelViewMatrix.mulVector(localShift, 0, out , 0, 1);
        x = (int)out[0];
        y = (int)out[1];

        g2.setColor(new Color(color));
        g2.fillRect(x - hs /2, y - hs / 2, hs, hs);


    }

    @Override
    public void update(float dt) {
        rotate += 2 * 3.14 * rotateSpeed * dt / (360 *1000.0f);
        modelMatrix.setRotate(rotate);

    }
}
