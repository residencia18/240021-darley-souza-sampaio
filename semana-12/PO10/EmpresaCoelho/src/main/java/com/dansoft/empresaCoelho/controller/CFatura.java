package com.dansoft.empresaCoelho.controller;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import com.dansoft.empresaCoelho.model.Fatura;
import com.dansoft.empresaCoelho.model.Imovel;
import com.dansoft.empresaCoelho.view.Main;

public class CFatura {
	public static boolean create(Fatura fatura) throws SQLException {
		EntityManager em = Main.getEntityManager();

		em.getTransaction().begin();

		em.persist(fatura);

		em.getTransaction().commit();

		em.close();
		return true;

	}

	public static List<Fatura> readAll(Imovel imovel) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Fatura> query = em.createQuery("SELECT f FROM Fatura f WHERE f.imovel = :imovel", Fatura.class);
		query.setParameter("imovel", imovel);

		List<Fatura> faturas = null;
		try {
			faturas = query.getResultList();
		} catch (Exception ex) {
			throw ex;
		}

		em.close();

		return faturas;
	}

	public static Fatura readUnique(String matricula) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Fatura> query = em.createQuery("SELECT f FROM Fatura f WHERE f.matricula = :matricula",
				Fatura.class);
		query.setParameter("matricula", matricula);

		Fatura fatura = null;
		try {
			fatura = query.getSingleResult();
		} catch (NoResultException ex) {
			throw ex;
		} catch (Exception ex) {
			System.out.println("Erro ao ler a fatura do imóvel: " + ex.getMessage());
			throw ex;
		}

		em.close();

		return fatura;
	}

	public static void update(Fatura fatura) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			em.merge(fatura);
			transaction.commit();
		} finally {
			em.close();
		}
	}

	public static void delete(Fatura fatura) throws Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			Fatura imovelManaged = em.merge(fatura);
			em.remove(imovelManaged);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new Exception("Erro ao excluir a fatura: " + ex.getMessage());
		} finally {
			em.close();
		}
	}

}
