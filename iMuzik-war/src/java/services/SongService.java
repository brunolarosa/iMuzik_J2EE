/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Song;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import sessions.SongManager;

/**
 *
 * @author brunolarosa
 */
@Path("songs")
@Stateless
public class SongService {
    
    @EJB
    private SongManager songManager;
    
    @GET
    @Produces("application/xml")
    @Path("/all")
    public List<Song> getAllSongs() {
        return this.songManager.getAllSongs();
    }
    
}
