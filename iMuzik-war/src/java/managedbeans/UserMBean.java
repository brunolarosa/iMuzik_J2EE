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
import java.util.List;
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
    private int playlistSelectedID = -1;
    private String namePlaylist;
    
     private Playlist playlistSelected;

    public Playlist getPlaylistSelected() {
        return playlistSelected;
    }

    public void setPlaylistSelected(Playlist playlistSelected) {
        this.playlistSelected = playlistSelected;
        System.out.println(playlistSelected.getName());
    }

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

    public String createUser() {
        userManager.createUser(email, firstName, lastName, password);
        cleanFieldLog();
        return red_index();
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
        updateUser();
        cleanFieldLog();
    }

    public void disconnect() {
        userLogged = null;
        playlistSelectedID = -1;

    }

    public String signup() {
        return "signup?faces-redirect=true";
    }
    
     public String red_index() {
        return "index?faces-redirect=true";
    }

    public void createPlaylist() {
        Playlist playlist = new Playlist(userLogged, namePlaylist);
        userManager.addPlaylist(userLogged, playlist);
        updateUser();
        namePlaylist = "";
    }

    public void addSongPlaylist(Song song) {
        if (playlistSelectedID == -1) {
            playlistSelectedID = userLogged.getMyPlaylists().get(0).getId();
        }
        userManager.addSongPlaylist(userLogged, playlistSelectedID, song);
        updateUser();
    }

    public void delSongPlaylist(int playlistSelectedID, PlaylistItem playlistItem) {
        playlistManager.delSongPlaylist(playlistSelectedID, playlistItem);
        updateUser();
    }
    
      public void deletePlaylist(int playlistSelectedID) {
        userManager.deletePlaylist(userLogged.getId(),playlistSelectedID);
        updateUser();
    }

    public void setPlaylist(int id) {
        System.out.println(id);
        playlistSelectedID = id;
        System.out.println(playlistSelectedID);

    }

    public void updateUser() {
        userLogged = userManager.getUser(userLogged.getEmail(), userLogged.getPassword());
    }

    public List<UserEntity> getUserEntities() {
        List<UserEntity> allUserEntities = userManager.getAllUserEntities();
        System.out.println(allUserEntities.size());
        return allUserEntities;
    }

    public boolean userIsLogged() {
        if (userLogged != null) {
            return true;
        }
        return false;
    }

    public boolean userIsAdmin() {
        if (userLogged.isAdmin()) {
            return true;
        }
        return false;
    }

    public void cleanFieldLog() {
        email = "";
        password = "";
        firstName = "";
        lastName = "";
    }
}