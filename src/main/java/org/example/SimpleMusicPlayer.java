package org.example;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SimpleMusicPlayer implements MusicPlayer {

    private final MusicService musicService;

    @Inject
    public SimpleMusicPlayer(MusicService musicService) {

        this.musicService = musicService;
    }

    @Override
    public void start() {
        musicService.play();
    }
}
