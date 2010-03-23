package br.com.alexegidio.persistence;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author Alex Egidio - alexegidio@yahoo.com.br
 */
public class HibernateUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static SessionFactory factory;

	static {
		Configuration conf = new AnnotationConfiguration();
		conf.configure();
		factory = conf.buildSessionFactory();
	}

	public static SessionFactory getSessionFactory() {
		return factory;
	}

	public static Session getSession() {
		return factory.openSession();
	}

}