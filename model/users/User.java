package com.nescude.spoofy.model.users;
import java.util.ArrayList;
import java.util.Date;

import com.nescude.spoofy.interfaces.*;
import com.nescude.spoofy.model.musical.Playlist;
import com.nescude.spoofy.model.musical.Song;
import com.nescude.spoofy.util.RandomN;
import lombok.*;

@Data
@NoArgsConstructor
public abstract class User implements IPlayable{

    private String username;
    private String fullName;
    private String date;
    protected PLAN_TYPE type;
    private ArrayList<Playlist> list;
    
    public enum PLAN_TYPE{
        STANDARD("ST"), PREMIUM("PM");
        String plan;
        private PLAN_TYPE (String s) { this.plan = s; }
        public String value(){ return this.plan; }
    }

    public User(String username, String fullname, String date){
        this.username = username;
        this.fullName = fullname;
        this.date = date;
    } 

    
    
    protected Playlist findPlaylist(int playlist){
        for (Playlist p : this.getList()){
            if (p.getId() == playlist)
                return p;
        }
        return null;
    }

    protected Song findSongInPlaylist(int playlistId, int songId){
        Playlist p = findPlaylist(playlistId);
        for (Song s : p.getLista()){
            if (s.getId() == songId)
                return s;
        }
        return null;
    }
}
