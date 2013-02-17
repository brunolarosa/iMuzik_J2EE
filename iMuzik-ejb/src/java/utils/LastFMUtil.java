/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.ImageSize;

/**
 *
 * @author brunolarosa
 */
public class LastFMUtil {

    private static String lastFMKey = "564ddd75dbc03f61d54eec39d95b4b97";

    public static void readArtist(entities.Artist artist) {
        Artist artistFM = Artist.getInfo(artist.getName(), lastFMKey);
        
        //Suppression des tags HTML dans la bio
        String bio = artistFM.getWikiSummary().replaceAll("\\<.*?\\>", "");
        
        //Suppression de la derniere ligne Last.fm de la bio
        bio = bio.split("Read more about .*? on Last.fm.")[0];
        artist.setBio(bio);
        
        artist.setImageURL(artistFM.getImageURL(ImageSize.LARGE));
    }

    public static void readAlbum(entities.Album album) {
        System.out.println("artist :"+album.getArtist().getName()+" album :"+album.getTitle());
        Album albumFM = Album.getInfo(album.getArtist().getName(), album.getTitle(), lastFMKey);
        if (null != albumFM) {
            String urlImage = null;
            ImageSize[] imageSizes = {
                ImageSize.EXTRALARGE,
                ImageSize.HUGE,
                ImageSize.LARGE,
                ImageSize.LARGESQUARE,
                ImageSize.MEDIUM,
                ImageSize.MEGA,
                ImageSize.ORIGINAL,
                ImageSize.SMALL };
            
            String url = null;
            
            int i = 0;
            while(i < imageSizes.length && url == null) {
                url = albumFM.getImageURL(imageSizes[i]);
                System.out.println("url : "+url);
                i++;
            }
            
            
            album.setImageURL((null == url) ? "test" : url);
            album.setReleaseDate(albumFM.getReleaseDate());
        }
    }
}
