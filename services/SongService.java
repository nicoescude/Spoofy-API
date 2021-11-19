package com.nescude.spoofy.services;

import com.nescude.spoofy.model.musical.Song;
import com.nescude.spoofy.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SongService {
    
    @Autowired
    SongRepository repo;

    public Song getSong(int id){
        return repo.find(id);
    }

    public Iterable<Song> getAllSongs(){
        return repo.findAll();
    }

    public boolean deleteSong(int id){
        return repo.delete(id);
    }
    
    public boolean postSong(Song s){
        return repo.save(s);
    }

}
