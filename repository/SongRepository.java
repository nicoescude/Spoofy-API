package com.nescude.spoofy.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.nescude.spoofy.handlers.ConnectionConfig;
import com.nescude.spoofy.interfaces.IRepository;
import com.nescude.spoofy.model.musical.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class SongRepository implements IRepository<Song>{

    @Autowired
    ConnectionConfig con;

    @Override
    public boolean save(Song u) {
        String query = "insert into song(name,artist) values(?,?)";
        PreparedStatement ps;
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getArtist());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Song find(int id) {
        String query = "select * from song where id = ?";
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                Song s = new Song(rs.getInt("id"),rs.getString("name"),rs.getString("artist"));
                return s;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Song> findAll() {
        String query = "select * from song";
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Song> songs = new ArrayList<>();
        try {
            ps = con.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                Song s = new Song(rs.getInt("id"),rs.getString("name"),rs.getString("artist"));
                songs.add(s);
            }
            return songs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        String query = "delete from song where id = ?";
        PreparedStatement ps;
        try {
            ps = con.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
