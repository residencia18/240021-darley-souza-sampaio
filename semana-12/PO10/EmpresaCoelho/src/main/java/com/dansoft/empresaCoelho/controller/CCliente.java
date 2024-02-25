package com.dansoft.empresaCoelho.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.dansoft.empresaCoelho.model.Cliente;
import com.dansoft.empresaCoelho.view.Main;

public class CCliente {
	public static boolean create(Cliente cliente) throws SQLException {
		EntityManager em = Main.getEntityManager();

		em.getTransaction().begin();

		em.persist(cliente);

		em.getTransaction().commit();

		em.close();
		return true;
	}

	public static List<Cliente> readAll() throws SQLException {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c", Cliente.class);

		List<Cliente> clientes = query.getResultList();

		em.close();

		return clientes;
	}

	public static Cliente readUnique(String cpf) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class);
		query.setParameter("cpf", cpf);

		Cliente cliente = null;
		try {
			cliente = query.getSingleResult();
		} catch (Exception ex) {
			System.out.println("Cliente não encontrado ou múltiplos clientes encontrados.");
		}

		em.close();

		return cliente;
	}

	public static void update(Cliente cliente, String alteracao, String tipoAlteracao) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			if (tipoAlteracao.equals("1")) {
				cliente.setNome(alteracao);
			} else if (tipoAlteracao.equals("2")) {
				cliente.setCpf(alteracao);
			}
			em.merge(cliente);
			transaction.commit();
		} catch (SQLIntegrityConstraintViolationException e) {
			transaction.rollback();
			throw new SQLIntegrityConstraintViolationException("Erro: Cliente com mesmo CPF já cadastrado.");
		} catch (SQLException e) {
			transaction.rollback();
			throw new SQLException("Erro ao executar a operação: " + e.getMessage());
		} finally {
			em.close();
		}
	}

	public static void delete(Cliente cliente) throws Exception {
		EntityManager em = Main.getEntityManager();
		EntityTransaction transaction = em.getTransaction();

		transaction.begin();

		try {
			Cliente clienteManaged = em.merge(cliente);
			em.remove(clienteManaged);
			transaction.commit();
		} catch (Exception ex) {
			transaction.rollback();
			throw new Exception("Erro ao excluir o cliente: " + ex.getMessage());
		} finally {
			em.close();
		}
	}

}
