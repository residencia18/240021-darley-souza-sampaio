package com.dansoft.empresaCoelho.controller;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import com.dansoft.empresaCoelho.model.Pagamento;
import com.dansoft.empresaCoelho.model.Fatura;
import com.dansoft.empresaCoelho.view.Main;

public class CPagamento {
	public static boolean create(Pagamento pagamento) throws SQLException {
		EntityManager em = Main.getEntityManager();

		em.getTransaction().begin();

		em.persist(pagamento);

		em.getTransaction().commit();

		em.close();
		
		return true;

	}

	public static List<Pagamento> readAll(Fatura fatura) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Pagamento> query = em.createQuery("SELECT p FROM Pagamento p WHERE p.fatura = :fatura",
				Pagamento.class);
		query.setParameter("fatura", fatura);

		List<Pagamento> pagamentos = null;
		try {
			pagamentos = query.getResultList();
		} catch (Exception ex) {
			throw ex;
		}

		em.close();

		return pagamentos;
	}

	public static Pagamento readUnique(String matricula) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Pagamento> query = em.createQuery("SELECT p FROM Pagamento p WHERE p.matricula = :matricula",
				Pagamento.class);
		query.setParameter("matricula", matricula);

		Pagamento pagamento = null;
		try {
			pagamento = query.getSingleResult();
		} catch (NoResultException ex) {
			throw ex;
		} catch (Exception ex) {
			System.out.println("Erro ao ler o pagamento da fatura: " + ex.getMessage());
			throw ex;
		}

		em.close();

		return pagamento;
	}

	public static void update(Pagamento pagamento) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			em.merge(pagamento);
			transaction.commit();
		} finally {
			em.close();
		}
	}

}
