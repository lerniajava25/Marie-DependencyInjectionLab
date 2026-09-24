package org.example;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SpotifyMusicService implements MusicService {

    @Override
    public void play() {
        System.out.println("Playing music from Spotify");
    }
}
