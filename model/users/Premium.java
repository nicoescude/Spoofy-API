package com.nescude.spoofy.model.users;

import com.nescude.spoofy.model.musical.*;

public class Premium extends User{

    public Premium(String username, String nombreApellido) {
        super(username, nombreApellido,null);
        this.type = PLAN_TYPE.PREMIUM;
    }

    public Premium(String username, String nombreApellido, String date ){
        super(username, nombreApellido,date);
        this.type = PLAN_TYPE.PREMIUM;
    }
    

    @Override
    public boolean playSong(Song song) {
        System.out.println("Playing: -> "+song.getName()+" - by: "+song.getArtist());
        return true;
    }

    @Override
    public boolean addSongToPlaylist(Playlist playlist, Song song) {
        return true;
    }
    
    
}
