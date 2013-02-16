/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Category;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import sessions.CategoryManager;

/**
 *
 * @author brunolarosa
 */
@Named(value = "categoryMBean")
@SessionScoped
public class CategoryMBean implements Serializable {
    
    @EJB
    private CategoryManager categoryManager;
    
    public List<Category> getCategories() {
        return categoryManager.getAllCategories();
    }
    
    
    public CategoryMBean() {
    }
}
