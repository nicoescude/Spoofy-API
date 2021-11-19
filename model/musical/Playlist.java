package com.nescude.spoofy.model.musical;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Playlist {

    private int id;
    private String nombre, master;
    private ArrayList<Song> lista;

    public Playlist(String master, String nombre) {
        this.master = master;
        this.lista = new ArrayList<>();
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getMaster() {
        return this.master;
    }

    public int getSize() {
        return this.lista.size();
    }

    public boolean isEmpty() {
        return this.lista.isEmpty();
    }

    public void addCancion(Song cancion) {
        if (cancion != null) {
            this.lista.add(cancion);
        }
    }

    public ArrayList<Song> getLista() {
        return lista;
    }

}
