/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Artist;
import entities.Song;
import entities.UserEntity;
import java.io.File;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.FileUtils;
import org.farng.mp3.MP3File;
import utils.ID3Util;

/**
 *
 * @author brunolarosa
 */
@Singleton
@LocalBean
@Startup
public class InitializedManager {
    
    @PersistenceContext(unitName = "iMuzik-ejbPU")
    private EntityManager em;

    
    
    @PostConstruct
    public void init() {
        
        UserEntity user = new UserEntity("admin","admin","admin", "123456");
        user.setAdmin(true);
        em.persist(user);
        
        
        File root = new File("/Users/brunolarosa/Desktop/MusicTest");
        String[] extensions = {"mp3"};
        
        Collection<File> files = FileUtils.listFiles(root, extensions, true);


        for (File file : files) {

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

                        persist(song);


                    }

                }
            } catch (Exception e) {
            }

        }
    }

    public void persist(Object object) {
        em.persist(object);
    }
    
}
