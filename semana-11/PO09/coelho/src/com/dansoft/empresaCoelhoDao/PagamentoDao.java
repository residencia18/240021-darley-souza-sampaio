package com.dansoft.empresaCoelhoDao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dansoft.empresaCoelhoClasses.Fatura;
import com.dansoft.empresaCoelhoClasses.Pagamento;

public class PagamentoDao {
	public static boolean create(Pagamento pagamento, Fatura fatura, boolean quitado, Connection conn)
			throws SQLException {
		final String queryPagamento = "INSERT INTO Pagamento (matricula, data, valor, Fatura_id) VALUES (?, ?, ?, ?)";
		final String queryFatura = "UPDATE Fatura SET quitado = ? WHERE matricula = ?";
		try {
			PreparedStatement psI = conn.prepareStatement(queryPagamento);

			Date dataSql = new Date(pagamento.getData().getTime());

			psI.setString(1, pagamento.getMatricula());
			psI.setDate(2, dataSql);
			psI.setDouble(3, pagamento.getValor());
			psI.setInt(4, fatura.getId());
			psI.execute();

			if (quitado) {
				PreparedStatement psF = conn.prepareStatement(queryFatura);
				psF.setBoolean(1, true);
				psF.setString(2, fatura.getMatricula());
				psF.executeUpdate();
			}

			return true;
		} catch (Exception e) {

			return false;
		}
	}

	public static Pagamento readUnique(Pagamento pagamentoR, Connection conn) throws Exception {
		final String query = "SELECT * FROM Pagamento WHERE matricula = ?";
		try {
			Pagamento pagamento = null;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, pagamentoR.getMatricula());

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				pagamento = new Pagamento();
				pagamento.setId(rs.getInt("id"));
				pagamento.setMatricula(rs.getString("matricula"));
				pagamento.setData(rs.getDate("data"));
				pagamento.setValor(rs.getDouble("valor"));
			}

			return pagamento;
		} catch (SQLException e) {

			return null;
		}
	}

	public static ArrayList<Pagamento> readAll(Fatura fatura, Connection conn) throws Exception {
		final String query = "SELECT * FROM Pagamento WHERE Fatura_id = ?";
		try {
			ArrayList<Pagamento> pagamentos = new ArrayList<Pagamento>();
			Pagamento pagamento = null;
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, fatura.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				pagamento = new Pagamento();
				pagamento.setId(rs.getInt("id"));
				pagamento.setMatricula(rs.getString("matricula"));
				pagamento.setData(rs.getDate("data"));
				pagamento.setValor(rs.getDouble("valor"));
				pagamentos.add(pagamento);
			}

			return pagamentos;
		} catch (SQLException e) {

			return null;
		}
	}
}
