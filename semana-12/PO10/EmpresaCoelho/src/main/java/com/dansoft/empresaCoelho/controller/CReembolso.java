package com.dansoft.empresaCoelho.controller;

import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.dansoft.empresaCoelho.model.Pagamento;
import com.dansoft.empresaCoelho.model.Reembolso;
import com.dansoft.empresaCoelho.view.Main;

public class CReembolso {
	public static boolean create(Reembolso reembolso) throws SQLException {
		EntityManager em = Main.getEntityManager();

		em.getTransaction().begin();

		em.persist(reembolso);

		em.getTransaction().commit();

		em.close();
		return true;

	}

	public static Reembolso read(Pagamento pagamento) throws SQLException, Exception {
		EntityManager em = Main.getEntityManager();

		TypedQuery<Reembolso> query = em.createQuery("SELECT r FROM Reembolso r WHERE r.pagamento = :pagamento",
				Reembolso.class);
		query.setParameter("pagamento", pagamento);

		Reembolso reembolso = query.getSingleResult();;

		em.close();

		return reembolso;
	}

}
