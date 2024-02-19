package com.dansoft.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dansoft.empresaCoelho.Fatura;
import com.dansoft.empresaCoelho.Imovel;

public class FaturaDao {
	public static boolean create(Fatura fatura, Imovel imovel, Connection conn) throws SQLException {
		final String queryFatura = "INSERT INTO Fatura (matricula, data, ultima_leitura, penultima_leitura, valor, quitado, Imovel_id) VALUES (?, ?, ?, ?, ?, ?, ?) ";
		final String queryImovel = "UPDATE Imovel SET ultima_Leitura = ?, penultima_leitura = ? WHERE matricula = ?";
		try {
			PreparedStatement psI = conn.prepareStatement(queryFatura);
			PreparedStatement psF = conn.prepareStatement(queryImovel);

			Date dataSql = new Date(fatura.getData().getTime());

			psI.setString(1, fatura.getMatricula());
			psI.setDate(2, dataSql);
			psI.setString(3, fatura.getUltimaLeitura());
			psI.setString(4, fatura.getPenultimaLeitura());
			psI.setDouble(5, fatura.getValor());
			psI.setBoolean(6, fatura.getQuitado());
			psI.setInt(7, imovel.getId());

			psF.setString(1, imovel.getUltimaLeitura());
			psF.setString(2, imovel.getPenultimaLeitura());
			psF.setString(3, imovel.getMatricula());

			psI.execute();
			psF.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<Fatura> readAll(Imovel imovel, Connection conn) throws Exception {
		final String query = "SELECT * FROM Fatura WHERE Imovel_id = ?";
		ArrayList<Fatura> faturas = new ArrayList<Fatura>();
		Fatura fatura = null;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, imovel.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				fatura = new Fatura();
				fatura.setMatricula(rs.getString("matricula"));
				fatura.setData(rs.getDate("data"));
				fatura.setUltimaLeitura(rs.getString("ultima_leitura"));
				fatura.setPenultimaLeitura(rs.getString("penultima_leitura"));
				fatura.setValor(rs.getDouble("valor"));
				fatura.setQuitado(rs.getBoolean("quitado"));

				faturas.add(fatura);
			}

			return faturas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Fatura readUnique(String matricula, Connection conn) throws Exception {
		final String query = "SELECT * FROM Fatura WHERE matricula = ?";
		Fatura fatura = null;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, matricula);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				fatura = new Fatura();
				fatura.setId(rs.getInt("id"));
				fatura.setMatricula(rs.getString("matricula"));
				fatura.setData(rs.getDate("data"));
				fatura.setUltimaLeitura(rs.getString("ultima_leitura"));
				fatura.setPenultimaLeitura(rs.getString("penultima_leitura"));
				fatura.setValor(rs.getDouble("valor"));
				fatura.setQuitado(rs.getBoolean("quitado"));
			}

			return fatura;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean update(Fatura fatura, Connection conn) throws SQLException {
		final String queryData = "UPDATE Fatura SET data = ? WHERE matricula = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(queryData);

			Date dataSql = new Date(fatura.getData().getTime());

			ps.setDate(1, dataSql);
			ps.setString(2, fatura.getMatricula());

			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
