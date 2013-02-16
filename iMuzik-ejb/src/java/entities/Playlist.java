/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author dominiquec
 */
@Entity
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
 
    private HashMap<Song, Integer> playlist;
    
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<PlaylistItem> playlistItems;
    
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private UserEntity user;
    private String name;

    public Playlist() {
    }

    public Playlist(String name) {
        this.name = name;
        this.playlist = new HashMap<Song, Integer>();
        this.playlistItems = new ArrayList<PlaylistItem>();
    }

    public Playlist(UserEntity user, String name) {
        this.user = user;
        this.name = name;
        this.playlist = new HashMap<Song, Integer>();
    }

    public HashMap<Song, Integer> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(HashMap<Song, Integer> playlist) {
        this.playlist = playlist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PlaylistItem> getPlaylistItems() {
        return playlistItems;
    }

    public void setPlaylistItems(List<PlaylistItem> playlistItems) {
        this.playlistItems = playlistItems;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Playlist[ id=" + id + " ]";
    }
    
}
