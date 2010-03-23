/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.alexegidio.persistence;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * 
 * @author alexegidio@yahoo.com.br
 *
 */
public class JpaUtil implements Serializable {

	private static EntityManagerFactory factory;

	static {
		factory = Persistence.createEntityManagerFactory("persistence");
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
