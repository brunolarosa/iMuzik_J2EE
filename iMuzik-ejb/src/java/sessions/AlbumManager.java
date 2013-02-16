/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Artist;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brunolarosa
 */
@Stateless
@LocalBean
@XmlRootElement(name = "albums")
public class AlbumManager {
    @PersistenceContext(unitName = "iMuzik-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public void createAlbum(entities.Album album) {
        persist(album);
    }
    
    public List<entities.Album> getAlbumsForArtist(Artist artist) {
        Query query = em.createNamedQuery("Album.findAlbumsForArtist");
        query.setParameter("artist", artist);
        return query.getResultList();
    }
    
    public entities.Album getAlbum(String title, Artist artist) {
        Query query = em.createNamedQuery("Album.findAlbumByName");
        query.setParameter("title", title);
        query.setParameter("artist", artist);
        
        if(query.getResultList().size() == 1) {
            return (entities.Album) query.getSingleResult();
        } else {
            return null;
        }
    }
    
    
}
