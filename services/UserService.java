package com.nescude.spoofy.services;

import java.util.ArrayList;

import com.nescude.spoofy.model.musical.Song;
import com.nescude.spoofy.model.users.User;
import com.nescude.spoofy.repository.PlaylistRepository;
import com.nescude.spoofy.repository.SongRepository;
import com.nescude.spoofy.repository.UserRepository;
import com.nescude.spoofy.util.RandomN;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    PlaylistRepository playlistRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    SongRepository songRepo;

    public User getUser(int id){
        return userRepo.find(id);
    }

    public User getUser(String username){
        return userRepo.find(username);
    }

    public Iterable<User> getAllUser(){
        return userRepo.findAll();
    }

    public boolean deleteUser(int id){
        return userRepo.delete(id);
    }

    public boolean deleteUser(String username){
        return userRepo.delete(username);
    }

    public boolean postUser(User u){
        return userRepo.save(u);
    }

    public void playSong(String username, int songId){
        User u = userRepo.find(username);
        Song s = songRepo.find(songId);
        if (!u.playSong(s))
            this.shuffleSong(username); 
    }

    public void shuffleSong(String username){
        ArrayList<Song> songs = (ArrayList<Song>)songRepo.findAll();
        int i = RandomN.rng(songs.size());
        Song s = songs.get(i);
        User u = userRepo.find(username);
        u.playSong(s);
    }

    

}
