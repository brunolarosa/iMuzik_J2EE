/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dominiquec
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Playlist.findAll", query = "SELECT p FROM Playlist p ORDER BY p.name"),
})
@XmlRootElement(name="playlist")
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
    
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    private List<PlaylistItem> playlistItems;
    
    
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private UserEntity user;
    
    private String name;

    public Playlist() {
    }

    public Playlist(String name) {
        this.name = name;
        this.playlistItems = new ArrayList<PlaylistItem>();
    }

    public Playlist(UserEntity user, String name) {
        this.user = user;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @XmlElementWrapper(name="items")
    @XmlElement(name="item")
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
