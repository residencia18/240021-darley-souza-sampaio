package com.dansoft.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dansoft.empresaCoelho.Pagamento;
import com.dansoft.empresaCoelho.Reembolso;

public class ReembolsoDao {
	public static boolean create(Reembolso reembolso, Pagamento pagamento, Connection conn) throws SQLException {
		final String queryReembolso = "INSERT INTO Reembolso (data, valor, Pagamento_id) VALUES (?, ?, ?)";
		try {
			PreparedStatement psR = conn.prepareStatement(queryReembolso);

			Date dataSql = new Date(reembolso.getData().getTime());

			psR.setDate(1, dataSql);
			psR.setDouble(2, reembolso.getValor());
			psR.setDouble(3, pagamento.getId());
			psR.execute();

			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public static Reembolso read(Pagamento pagamento, Connection conn) throws Exception {
		final String query = "SELECT * FROM Reembolso WHERE Pagamento_id = ?";
		try {
			Reembolso reembolso = null;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, pagamento.getId());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				reembolso = new Reembolso();
				reembolso.setData(rs.getDate("data"));
				reembolso.setValor(rs.getDouble("valor"));
			}

			return reembolso;
		} catch (SQLException e) {

			return null;
		}
	}
}
