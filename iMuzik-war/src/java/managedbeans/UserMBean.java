/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import entities.Playlist;
import entities.PlaylistItem;
import entities.Song;
import entities.UserEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import sessions.PlaylistManager;
import sessions.UserManager;

/**
 *
 * @author dominiquec
 */
@Named(value = "userMBean")
@SessionScoped
public class UserMBean implements Serializable {

    @EJB
    private UserManager userManager;
    
       @EJB
    private PlaylistManager playlistManager;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private UserEntity userLogged;
    private int playlistSelectedID;
    private String namePlaylist;

    public int getPlaylistSelectedID() {
        return playlistSelectedID;
    }

    public void setPlaylistSelectedID(int playlistSelectedID) {
        this.playlistSelectedID = playlistSelectedID;
    }
    
    
    
    public UserEntity getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(UserEntity userLogged) {
        this.userLogged = userLogged;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void createUser() {
        userManager.createUser(email, password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNamePlaylist() {
        return namePlaylist;
    }

    public void setNamePlaylist(String namePlaylist) {
        this.namePlaylist = namePlaylist;
    }

    public UserMBean() {
    }

    public void connect() {
        userLogged = userManager.getUser(email, password);
    }

    public void disconnect() {
        userLogged = null;
    }

    public String signup() {
        return "signup?faces-redirect=true";
    }

    public void createPlaylist() {
        Playlist playlist = new Playlist(userLogged, namePlaylist);
        userManager.addPlaylist(userLogged, playlist);
    }

     public void addSongPlaylist(Song song) {
            System.out.println(song.getTitle() + " " + userLogged.getMyPlaylists().get(0));
            playlistManager.addSongPlaylist(playlistSelectedID, song);
    }

    public void delSongPlaylist(PlaylistItem playlistItem) {
        System.out.println(playlistItem.getSong().getTitle() + " " + userLogged.getMyPlaylists().get(0));
        playlistManager.delSongPlaylist(playlistSelectedID, playlistItem);
    }
    
       public void setPlaylist(int id){
           System.out.println(id);
       playlistSelectedID = id;
       System.out.println(playlistSelectedID);
       
    }

}
