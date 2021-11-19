package com.nescude.spoofy.interfaces;

import com.nescude.spoofy.model.musical.Playlist;
import com.nescude.spoofy.model.musical.Song;

public interface IPlayable {
    
    public boolean addSongToPlaylist(Playlist playlist, Song song);
    public boolean playSong(Song song);
}
