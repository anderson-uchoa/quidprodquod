package br.com.alexegidio.test;

import java.util.List;

import org.hibernate.Session;

import br.com.alexegidio.dao.GenericDaoHibernateImpl;
import br.com.alexegidio.jsf.util.Criptography;
import br.com.alexegidio.model.Usuario;
import br.com.alexegidio.persistence.HibernateUtil;

public class TesteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Session session = HibernateUtil.getSession();
		List<Usuario> usuarios = session.createCriteria(Usuario.class).list();

		for (Usuario u : usuarios) {

			System.out.println("senha: " + u.getSenha() + "| Senha cripto: "
					+ Criptography.encryptString(u.getSenha()));
		}

	}

}
