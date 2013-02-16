/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author brunolarosa
 */
@Stateless
@LocalBean
@XmlRootElement(name = "categories")
public class CategoryManager {
    @PersistenceContext(unitName = "iMuzik-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public List<Category> getAllCategories() {
        Query query = em.createNamedQuery("Category.findAll");
        return query.getResultList();
    }
    
    public Category getCategoryByName(String name) {
        Query query = em.createNamedQuery("Category.findByName");
        query.setParameter("name", name);
        
        if(query.getResultList().size() == 1) {
            return (Category) query.getSingleResult();
        } else {
            return null;
        }
    }
    
}
