/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author brunolarosa
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a ORDER BY a.title"),
        @NamedQuery(name = "Album.findAlbumsForArtist", query = "SELECT a FROM Album a WHERE a.artist = :artist"),
        @NamedQuery(name = "Album.findAlbumByName", query = "SELECT a FROM Album a WHERE a.title = :title AND a.artist= :artist"),
})
@XmlRootElement(name = "album")
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    /*
     * PRIVATE FIELDS
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String title;
    private String imageURL;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date releaseDate;
    @ManyToOne
    private Artist artist;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, mappedBy = "album")
    @OrderBy("number")
    private List<Song> songs;

    /*
     * GETTERS AND SETERS
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlTransient
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @XmlTransient
    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    
    /* CONSTRUCTORS */
    public Album() {}
    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        this.songs = new ArrayList<Song>();
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
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Album[ id=" + id + " ]";
    }
}
