/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author dominiquec
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "UserEntity.findByEmail", query = "SELECT u FROM UserEntity u WHERE u.email = :email"),
        @NamedQuery(name = "UserEntity.findAll", query = "SELECT u FROM UserEntity u ORDER BY u.lastName"),
})
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String password;
    
    private boolean admin;
    
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY, mappedBy="User")
    private List<Playlist> myPlaylists; 

    public UserEntity() {
    }

    public UserEntity(String email, String firstName, String lastName, String password) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.admin = false;
        this.myPlaylists = new ArrayList<Playlist>();
        this.myPlaylists.add(new Playlist(this, "Favoris"));
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Playlist> getMyPlaylists() {
        return myPlaylists;
    }

    public void setMyPlaylists(List<Playlist> myPlaylists) {
        this.myPlaylists = myPlaylists;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserEntity)) {
            return false;
        }
        UserEntity other = (UserEntity) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.User[ id=" + id + " ]";
    }
    
}
