package br.com.alexegidio.test;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.model.Usuario;

public class TesteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GenericDaoHibernateImpl<Usuario> usuDao = new GenericDaoHibernateImpl<Usuario>(
				Usuario.class);

		System.out.println(usuDao.getDomainClass());
	}

}
