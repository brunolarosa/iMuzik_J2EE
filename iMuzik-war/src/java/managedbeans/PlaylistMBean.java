/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Playlist;
import entities.PlaylistItem;
import entities.Song;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import sessions.PlaylistManager;

/**
 *
 * @author dominiquec
 */
@Named(value = "playlistMBean")
@Dependent
public class PlaylistMBean {

    @EJB
    private PlaylistManager playlistManager;
    private Playlist playlistSelected;
    private int playlistSelectedID;

    public Playlist getPlaylistSelected() {
        return playlistSelected;
    }

    public int getPlaylistSelectedID() {
        return playlistSelectedID;
    }

    public void setPlaylistSelectedID(int playlistSelectedID) {
        this.playlistSelectedID = playlistSelectedID;
    }

    public void setPlaylist(Playlist playlistSelected) {
        System.out.println(playlistSelected.getName());
        this.playlistSelected = playlistSelected;
    }

    public void setPlaylistSelected(Playlist playlistSelected) {
        this.playlistSelected = playlistSelected;
    }

    public PlaylistMBean() {
    }

    public PlaylistManager getPlaylistManager() {
        return playlistManager;
    }

    public void setPlaylistManager(PlaylistManager playlistManager) {
        this.playlistManager = playlistManager;
    }

    public void addSongPlaylist(Song song) {
        if (playlistSelected != null) {
            System.out.println(song.getTitle() + " " + playlistSelected.getName());
            playlistManager.addSongPlaylist(playlistSelectedID, song);

        }
    }

    public void delSongPlaylist(PlaylistItem playlistItem) {
        playlistManager.delSongPlaylist(playlistSelectedID, playlistItem);
    }

    public void action() {
        System.out.println("Selected Foo item: " + playlistSelectedID);
    }
}
