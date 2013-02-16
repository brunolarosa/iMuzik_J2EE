/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Song;
import java.io.File;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.annotation.XmlRootElement;
import org.farng.mp3.MP3File;
import utils.ID3Util;

/**
 *
 * @author brunolarosa
 */
@Stateless
@LocalBean
@XmlRootElement(name="songs")
public class SongManager {
    @PersistenceContext(unitName = "iMuzik-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    public void createSong(Song song) {
        persist(song);        
    }
    
    public Song getSong(int id) {
        return em.find(Song.class, id);
    }
    
    public void createSongWithFile(File file) {
        
        
        String nameFile = file.getName();
        String absolutePath = file.getAbsolutePath();
        
        try {
            if (nameFile.endsWith("mp3")) {

                MP3File mp3File;
                try {
                    mp3File = new MP3File(file);
                } catch (javax.faces.view.facelets.TagException e) {
                    mp3File = null;
                }

                if (null != mp3File) {
                    
                    Song song = new Song(absolutePath);
                    
                    ID3Util.readID3Tag(mp3File, song);
                    
                    createSong(song);


                }

            }
        } catch (Exception e) {
             
        }  
        
    }
    
    
}
