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
@XmlRootElement(name = "artists")
public class ArtistManager {
    @PersistenceContext(unitName = "iMuzik-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    public void update(Artist artist) {
        em.merge(artist);
    }
    public void createArtist(Artist artist) {
        em.persist(artist);
    }
    
    public List<Artist> getAllArtists() {

        Query query = em.createNamedQuery("Artist.findAll");
        return query.getResultList();
    }
    
    public Artist getByName(String name) {

        if (null != name) {
            Query query = em.createNamedQuery("Artist.findByName");
            query.setParameter("name", name);
            List<Artist> artists = query.getResultList();

            if (artists.size() == 1) {
                return artists.get(0);
            }
        }
        return null;
    }
    
}
