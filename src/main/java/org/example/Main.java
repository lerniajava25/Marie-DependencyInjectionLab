package org.example;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main(String[] args) {

        MusicService spotify = new SpotifyMusicService();
        MusicPlayer spotifyPlayer = new SimpleMusicPlayer(spotify);
        spotifyPlayer.start();

        MusicService local = new LocalMusicService();
        MusicPlayer localPlayer = new SimpleMusicPlayer(local);
        localPlayer.start();
    }
}
