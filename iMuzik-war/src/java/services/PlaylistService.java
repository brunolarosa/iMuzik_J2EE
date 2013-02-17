/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Playlist;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import sessions.PlaylistManager;

/**
 *
 * @author brunolarosa
 */
@Path("playlists")
@Stateless
public class PlaylistService {
    @EJB
    private PlaylistManager playlistManager;
    
    
    
    @GET
    @Produces("application/xml")
    @Path("/all")
    public List<Playlist> getAllPlaylists() {
        return this.playlistManager.getAllPlaylists();
    }
    
}
