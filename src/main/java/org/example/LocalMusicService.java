package org.example;

public class LocalMusicService implements MusicService {

    @Override
    public void play() {
        System.out.println("Playing music locally");
    }
}
