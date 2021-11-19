package com.nescude.spoofy.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nescude.spoofy.handlers.ConnectionConfig;
import com.nescude.spoofy.interfaces.IRepository;
import com.nescude.spoofy.model.musical.Playlist;
import com.nescude.spoofy.model.musical.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PlaylistRepository implements IRepository<Playlist>{

    @Autowired
    ConnectionConfig con;

    @Override
    public boolean save(Playlist t) {
        String query = "insert into playlist(masterId,name) values(?,?)";
        PreparedStatement ps;
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setString(1, t.getMaster());
            ps.setString(2, t.getNombre());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Iterable<Playlist> getUserPlaylists(String username){
        String query = "select * form playlist where ownerId = ?";
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Playlist> ar = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()){
                Playlist p = new Playlist(rs.getInt("id"), rs.getString("ownerId"),rs.getString("name"),null);
                ar.add(p);
            }
            return ar;
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Playlist find(int id) {
        String query = "select * from playlist p inner join playlistsong ps "
        +"on p.playlistId = ps.playlistId "+"where playlistId = ?";
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Song> songs = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while(rs.next()){
                Song s = new Song(rs.getInt("songId"), null, null);
                songs.add(s);
            }
            Playlist p = new Playlist(rs.getInt("playlistId"), rs.getString("masterId"),rs.getString("name"),songs);
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Playlist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean addSong(int playlistId, int songId){
        String query = "insert into playlistsong values(?,?)";
        PreparedStatement ps;
        try{
            ps = con.getConnection().prepareStatement(query);
            ps.setInt(1, songId);
            ps.setInt(2, playlistId);
            ps.execute();
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
}
