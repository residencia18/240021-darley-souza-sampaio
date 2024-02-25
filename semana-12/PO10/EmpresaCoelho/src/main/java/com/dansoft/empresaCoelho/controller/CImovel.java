package com.dansoft.empresaCoelho.controller;

import java.sql.SQLException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.dansoft.empresaCoelho.model.Cliente;
import com.dansoft.empresaCoelho.model.Imovel;
import com.dansoft.empresaCoelho.view.Main;

public class CImovel {
	public static boolean create(Imovel imovel) throws SQLException {
		EntityManager em = Main.getEntityManager();

		em.getTransaction().begin();

		em.persist(imovel);

		em.getTransaction().commit();

		em.close();
		return true;

	}

	public static List<Imovel> readAll() throws SQLException {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Imovel> query = em.createQuery("SELECT i FROM Imovel i", Imovel.class);

		List<Imovel> imoveis = query.getResultList();

		em.close();

		return imoveis;
	}

	public static List<Imovel> readAllPerClient(Cliente cliente) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Imovel> query = em.createQuery("SELECT i FROM Imovel i WHERE i.cliente = :cliente", Imovel.class);
		query.setParameter("cliente", cliente);

		List<Imovel> imoveis = null;
		try {
			imoveis = query.getResultList();
		} catch (Exception ex) {
			throw ex;
		}

		em.close();

		return imoveis;
	}

	public static Imovel readUnique(String matricula) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Imovel> query = em.createQuery("SELECT i FROM Imovel i WHERE i.matricula = :matricula",
				Imovel.class);
		query.setParameter("matricula", matricula);

		Imovel imovel = null;

		imovel = query.getSingleResult();

		em.close();

		return imovel;
	}

	public static void update(Imovel imovel, String alteracao, String tipoAlteracao) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			if (tipoAlteracao.equals("1")) {
				imovel.setEndereco(alteracao);
			} else if (tipoAlteracao.equals("2")) {
				Cliente cliente = CCliente.readUnique(alteracao);
				imovel.setCliente(cliente);
			}
			em.merge(imovel);
			transaction.commit();
		} catch (SQLException e) {
			transaction.rollback();
			throw new SQLException("Erro ao executar a operação: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public static void updateAll(Imovel imovel) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			em.merge(imovel);
			transaction.commit();
		} finally {
			em.close();

		}
	}

	public static void delete(Imovel imovel) throws Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			Imovel imovelManaged = em.merge(imovel);
			em.remove(imovelManaged);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new Exception("Erro ao excluir o imóvel: " + ex.getMessage());
		} finally {
			em.close();
		}
	}

}
