/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alexegidio.persistence;

import javax.persistence.EntityManager;

/**
 * 
 * @author alexegidio@yahoo.com.br
 *
 */
public class JpaPersistence {

    private static JpaPersistence instance = new JpaPersistence();

    public static JpaPersistence getInstance() {
        return instance;
    }

    /*
     * T é uma classe generics você pode p
     * assar qualquer classe para ela
     */
  /*  public <T> T save(T objeto) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            objeto = em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        return objeto;
    }*/
    
    /*
    * T é uma classe generics você pode p
    * assar qualquer classe para ela
    */
    public boolean save(Object objeto) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            objeto = em.merge(objeto);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        return objeto != null;
    }


    /*
     * T é uma classe generics você pode p
     * assar qualquer classe para ela
     */
    public void remove(Object objeto) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        try {
            Object obj = em.merge(objeto);
            em.remove(obj);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
}
