package com.nescude.spoofy.controllers;

import java.util.HashMap;
import java.util.Map;

import com.nescude.spoofy.handlers.ConnectionConfig;
import com.nescude.spoofy.model.musical.Song;
import com.nescude.spoofy.services.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/song")
public class SongController {
    
    @Autowired
    private SongService service;

    @Autowired
    ConnectionConfig con;

    @GetMapping("/{songid}")
    public Song getUsuario(@PathVariable int songid){
        Song s = service.getSong(songid);
        return s;
    }

    @GetMapping("/all")
    public Iterable<Song> getUsuario(){ 
        return service.getAllSongs();
    }

    @DeleteMapping("/{songid}")
    public boolean songUsuario(@PathVariable int songid){
        return service.deleteSong(songid);       
    }

    @PostMapping(value="/add")
    public boolean postMethodName(@RequestBody Song song) {
        return service.postSong(song);
    }
    
}
