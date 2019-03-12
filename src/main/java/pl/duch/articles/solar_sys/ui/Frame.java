package pl.duch.articles.solar_sys.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame {

    private SolarCanvas solarCanvas;

    private long lastTime;

    public Frame() {
        solarCanvas = new SolarCanvas();
        solarCanvas.setPreferredSize(new Dimension(1200, 800));
        add(solarCanvas);
        pack();
        start();
    }

    private void start() {
        lastTime = System.currentTimeMillis();
        Timer timer = new Timer(33, event -> {
                long oldTime = lastTime;
                lastTime = System.currentTimeMillis();
                long dt = lastTime - oldTime;
                if (dt >= 20) {
                    loop(dt);
                }
                solarCanvas.repaint();
            }
        );
        timer.start();
    }

    private void loop(long dt) {
        solarCanvas.loop(dt);
    }

}
