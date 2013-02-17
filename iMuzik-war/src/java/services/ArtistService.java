/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Artist;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import sessions.ArtistManager;

/**
 *
 * @author brunolarosa
 */
@Path("artists")
@Stateless
public class ArtistService {
    
    @EJB
    private ArtistManager artistManager;

    
    
    @GET
    @Produces("application/xml")
    @Path("/all")
    public List<Artist> getAllArtists() {
        return this.artistManager.getAllArtists();
    }
    
    
    
}
