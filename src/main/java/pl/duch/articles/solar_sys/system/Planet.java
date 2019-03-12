package pl.duch.articles.solar_sys.system;

import pl.duch.articles.solar_sys.math.Matrix;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Planet extends AstralObject {

    private List<Orbit> satellite = new ArrayList<>();

    public Planet(float size, int color) {
        super(size, color);
    }

    @Override
    public void drawImpl(Graphics2D g2, Matrix modelViewMatrix) {
        modelViewMatrix.mulVector(center, 0, out , 0, 1);
        int x = (int)out[0];
        int y = (int)out[1];

        g2.setColor(new Color(color));
        int hs = (int)size;
        g2.fillArc(x - hs /2, y - hs / 2, hs, hs, 0, 360);

        for(Orbit orbit : satellite) {
            orbit.draw(g2, modelViewMatrix);
        }
    }

    @Override
    public void update(float dt) {
        for(Orbit orbit : satellite) {
            orbit.update(dt);
        }
    }

    public void addNaturalSatellite(float startAngle, float angleSpeed, float radius, float size) {
        NaturalSatellite naturalSatellite = new NaturalSatellite(size);
        this.satellite.add(new Orbit(naturalSatellite,
                2.0f * 3.14f * startAngle / 360.0f,
                      2.0f * 3.14f * angleSpeed / 360.0f, radius));
    }

}
