/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author brunolarosa
 */
@Named(value = "loginMBean")
@SessionScoped
public class LoginMBean implements Serializable {

    private boolean connected = false;

    /*
     * GETTERS AND SETTERS
     */
    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Creates a new instance of LoginMBean
     */
    public LoginMBean() {
    }
    
    public void deconnexion() {
        connected = false;
    }
    
    public void connexion() {
        connected = true;
    }
    
    
}
