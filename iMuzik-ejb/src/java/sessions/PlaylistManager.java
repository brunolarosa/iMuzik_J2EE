/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Playlist;
import entities.PlaylistItem;
import entities.Song;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dominiquec
 */
@Stateless
@LocalBean
@XmlRootElement(name="playlists")
public class PlaylistManager {
    
    @PersistenceContext(unitName = "iMuzik-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

   public void createPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        persist(playlist);
    }
   
    public Playlist getPlaylist(int id) {
        return em.find(Playlist.class, id);
    }
   
    public void addSongPlaylist(int playlistID, Song song) {
          Playlist playlist = getPlaylist(playlistID);
          playlist.getPlaylistItems().add(new PlaylistItem(song, playlist.getPlaylistItems().size()));
          em.merge(playlist);
    }

    public void delSongPlaylist(int playlistID, PlaylistItem playlistItem) {
        Playlist playlist = getPlaylist(playlistID);
          playlist.getPlaylistItems().remove(playlistItem);
          em.merge(playlist);
    }
    
    public void deletePlaylist(int playlistSelectedID) {
        em.remove(getPlaylist(playlistSelectedID)); 
    }
    
    public List<Playlist> getAllPlaylists() {
        Query q = em.createNamedQuery("Playlist.findAll");
        return q.getResultList();
    }
    
}
