package org.example;

public class Main {
    static void main(String[] args) {

        DIContainer container = new DIContainer();

        container.bind(MusicService.class, SpotifyMusicService.class);

        MusicPlayer musicPlayer = container.getInstance(SimpleMusicPlayer.class);

        musicPlayer.start();
    }
}
