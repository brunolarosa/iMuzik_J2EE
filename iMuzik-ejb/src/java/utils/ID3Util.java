/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import de.umass.lastfm.Track;
import entities.Album;
import entities.Artist;
import entities.Category;
import entities.Song;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.view.facelets.TagException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.farng.mp3.MP3File;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.id3.ID3v2_2;
import sessions.AlbumManager;
import sessions.ArtistManager;
import sessions.CategoryManager;

/**
 *
 * @author brunolarosa
 */
public class ID3Util {

    private static ArtistManager artistManager = lookupArtistManager();
    private static AlbumManager albumManager = lookupAlbumManager();
    private static CategoryManager categoryManager = lookupCategoryManager();
    private static String lastFMKey = "564ddd75dbc03f61d54eec39d95b4b97";

    public static void readID3Tag(MP3File mp3File, Song song) {


        if (null != mp3File) {

            if (mp3File.hasID3v1Tag()) {

                ID3v1 id3v1 = mp3File.getID3v1Tag();

                    Track track = Track.getInfo(id3v1.getArtist(), id3v1.getTitle(), lastFMKey);

                    song.setTitle(id3v1.getTitle());


                    /*
                     * ARTISTS
                     */
                    String artistName = track.getArtist();

                    if (artistName.trim().equals("")) {
                        artistName = "Inconnu";
                    }



                    Artist artist = artistManager.getByName(artistName);

                    if (null == artist) {
                        artist = new Artist(artistName);
                        LastFMUtil.readArtist(artist);
                    }
                    
                    song.setArtist(artist);
                    
                    
                    
                    /* ALBUM */
                    String albumTitle = track.getAlbum();
                    
                    
                    if(null == albumTitle) {
                        
                        if (id3v1.getAlbum().trim().equals("")) {
                            albumTitle = "Inconnu";
                        } else {
                            albumTitle = id3v1.getAlbum().trim();
                            
                        }
                    }
                    

                    Album album = albumManager.getAlbum(albumTitle, artist);

                    if (null == album) {
                        
                        album = new Album(albumTitle, artist);
                        
                        System.out.println(track.toString()+ "----------"+track.getAlbum());
                        if(null != track.getAlbum()){
                            LastFMUtil.readAlbum(album);
                            
                        }
                        artist.getAlbums().add(album);
                    }
                    album.getSongs().add(song);


                    song.setAlbum(album);


                    String categoryName = id3v1.getSongGenre();

                    System.out.println(categoryName);

                    if (categoryName.equals("")) {
                        categoryName = "Inconnu";
                    }

                    Category category = categoryManager.getCategoryByName(categoryName);
                    if (null == category) {
                        category = new Category(categoryName);
                    }

                    song.setCategory(category);






                }

        }
    }

    private static ArtistManager lookupArtistManager() {
        try {

            Context context = new InitialContext();
            return (ArtistManager) context.lookup("java:global/iMuzik/iMuzik-ejb/ArtistManager");

        } catch (NamingException ex) {
            Logger.getLogger(ID3Util.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static AlbumManager lookupAlbumManager() {
        try {

            Context context = new InitialContext();
            return (AlbumManager) context.lookup("java:global/iMuzik/iMuzik-ejb/AlbumManager");

        } catch (NamingException ex) {
            Logger.getLogger(ID3Util.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    private static CategoryManager lookupCategoryManager() {
        try {

            Context context = new InitialContext();
            return (CategoryManager) context.lookup("java:global/iMuzik/iMuzik-ejb/CategoryManager");

        } catch (NamingException ex) {
            Logger.getLogger(ID3Util.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
