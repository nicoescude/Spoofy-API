package com.nescude.spoofy.model.users;
import java.util.Random;

import com.nescude.spoofy.model.musical.*;
import com.nescude.spoofy.interfaces.*;

public class Standard extends User{

    public Standard(String username, String nombreApellido) {
        super(username, nombreApellido,null);
        this.type = PLAN_TYPE.STANDARD;
    }

    public Standard(String username, String nombreApellido, String date ){
        super(username, nombreApellido,date);
        this.type = PLAN_TYPE.STANDARD;
    }
    

    @Override
    public boolean playSong(Song s) {
        return false;
    }

    @Override
    public boolean addSongToPlaylist(Playlist playlist,Song song){
        if (playlist.getSize() > 30){
            System.out.println("Error, no puede agregar más canciones a esta lista: "+playlist.getNombre());
            return true;
        }
        else
        {
            System.out.println("Exito, cancion agregada a la lista: "+playlist.getNombre());
            return false;
        }  
    }
}
