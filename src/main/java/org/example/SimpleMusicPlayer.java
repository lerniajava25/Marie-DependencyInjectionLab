package org.example;

public class SimpleMusicPlayer implements MusicPlayer {

    private final MusicService musicService;

    public SimpleMusicPlayer(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public void start() {
        musicService.play();
    }
}
