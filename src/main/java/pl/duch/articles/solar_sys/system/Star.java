package pl.duch.articles.solar_sys.system;

import pl.duch.articles.solar_sys.math.Matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Star extends AstralObject {

    private List<Orbit> satellite = new ArrayList<>();

    private float rotate = 0.0f;
    private float rotateSpeed = 360.0f/40.0f;

    private float[] starPoints;
    private float[] starPointsOut;

    public Star(float size) {
        super(size, 0xFFFF00);

        float angle = 60.0f / 360.0f;
        angle = 2.0f * 3.14f * angle;

        starPoints = new float[] {
                0.0f, -size / 2f, 1.0f,

                size * (float)Math.sin(angle) / 2.0f, size * (float)Math.cos(angle) / 2.0f, 1.0f,
                size * (float)Math.sin(-angle) / 2.0f,  size * (float)Math.cos(-angle) / 2.0f, 1.0f,

                0.0f, size / 2f, 1.0f,
                size * (float)Math.sin(angle) / 2.0f, -size * (float)Math.cos(angle) / 2.0f, 1.0f,
                size * (float)Math.sin(-angle) / 2.0f, -size * (float)Math.cos(-angle) / 2.0f, 1.0f,

        };

        starPointsOut = new float[3 * 6];

    }

    @Override
    public void drawImpl(Graphics2D g2, Matrix modelViewMatrix) {
        modelViewMatrix.mulVector(starPoints, 0, starPointsOut, 0, 6);

        g2.setColor(new Color(color));
        g2.fillPolygon(new int[] {
                (int)starPointsOut[0], (int)starPointsOut[3], (int)starPointsOut[6]
        },new int [] {
                (int)starPointsOut[1], (int)starPointsOut[4], (int)starPointsOut[7]
        }, 3);

        g2.fillPolygon(new int[] {
                (int)starPointsOut[9], (int)starPointsOut[12], (int)starPointsOut[15]
        },new int [] {
                (int)starPointsOut[10], (int)starPointsOut[13], (int)starPointsOut[16]
        }, 3);

        for(Orbit orbit : satellite) {
            orbit.draw(g2, modelViewMatrix);
        }
    }

    @Override
    public void update(float dt) {
        rotate += 2 * 3.14 * rotateSpeed * dt / (360 *1000.0f);
        modelMatrix.setRotate(rotate);

        for(Orbit orbit : satellite) {
            orbit.update(dt);
        }
    }

    public void addPlanet(float startAngle, float angleSpeed, float radius, Planet planet) {
        this.satellite.add(new Orbit(planet,
                2.0f * 3.14f * startAngle / 360.0f,
                2.0f * 3.14f * angleSpeed / 360.0f, radius));
    }

}
