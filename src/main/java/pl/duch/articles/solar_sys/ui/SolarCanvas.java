package pl.duch.articles.solar_sys.ui;

import pl.duch.articles.solar_sys.math.Matrix;
import pl.duch.articles.solar_sys.system.Planet;
import pl.duch.articles.solar_sys.system.Star;

import javax.swing.*;
import java.awt.*;

public class SolarCanvas extends JPanel {

    private final Star star;

    private Matrix matrix;

    public SolarCanvas() {
// create first green planet with size of 15 and add two natural satellites
        // first satellite have size of 12, starting rotation 45 degree, full rotation in 5 seconds, radius 50, rotating clockwise
        // Second satellite have size of 8, starting rotation 30, full rotation in 2 second radius 20, rotating counter clockwise
        Planet planet1 = new Planet(15.0f, 0x009900);
        planet1.addNaturalSatellite(45.f, 360.0f/5f, 50.0f, 12.0f);
        planet1.addNaturalSatellite(30.f, -360.0f/2f, 20.0f, 8.0f);

        // create second blue planet size 60 with one natural satellite
        // Natural satellite start at -30 degree, full rotation in 10 seconds, radius 60, size 15 rotating clockwise
        Planet planet2 = new Planet(60.0f, 0x000099);
        planet2.addNaturalSatellite(-30.f, 360.0f/10f, 60.0f, 15.0f);

        // create third red planet size 30 with one natural satellite
        // Natural satellite start at -30 degree, full rotation in 10 seconds, radius 50, size 15 rotating clockwise
        Planet planet3 = new Planet(30.0f, 0x990000);
        planet3.addNaturalSatellite(-30.f, 360.0f/10f, 50.0f, 15.0f);

        // create star and add three planets
        // first(green) start at 0 degree, rotate in 15 seconds, radius 100, rotate clockwise
        // second(blue) start at 220 degree, rotate in 30 seconds, radius 220, rotate counter clockwise
        // third(red) start at 120 degree, rotate in 20 seconds, radius 350, rotate clockwise
        star = new Star(80.0f);
        star.addPlanet(0.0f, 360/15.0f, 100.0f, planet1);
        star.addPlanet(220.0f, -360/30.0f, 220.0f , planet2);
        star.addPlanet(120.0f, 360/20.0f, 350.0f, planet3);

        matrix = new Matrix();
    }


    public void loop(long dt) {
        matrix.setTranslate(getWidth() / 2.0f, getHeight()/2.0f);
        star.update(dt);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());

        star.draw(g2, matrix);


        g2.dispose();
    }
}
