package org.example;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
    static void main(String[] args) {

        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        MusicPlayer musicPlayer = container.select(SimpleMusicPlayer.class).get();
        musicPlayer.start();

        weld.shutdown();
    }
}
