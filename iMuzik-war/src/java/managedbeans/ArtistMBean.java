/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Artist;
import java.io.File;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import sessions.ArtistManager;


/**
 *
 * @author brunolarosa
 */
@Named(value = "artistMBean")
@SessionScoped
public class ArtistMBean implements Serializable {
    
    @EJB
    private ArtistManager artistManager;
    
    private Artist currentArtist;

    public Artist getCurrentArtist() {
        return currentArtist;
    }

    public void setCurrentArtist(Artist currentArtist) {
        this.currentArtist = currentArtist;
    }
    
    
    
    public List<Artist> getArtists() {
        
        
        return artistManager.getAllArtists();
    }
   
    public ArtistMBean() {
    }
    
    public String showArtistDetails(Artist artist) {
        this.currentArtist = artist;
        return "artistDetails?faces-redirect=true";
    }
}
