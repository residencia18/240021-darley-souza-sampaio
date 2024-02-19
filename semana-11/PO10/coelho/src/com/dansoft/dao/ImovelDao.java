package com.dansoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dansoft.empresaCoelho.Cliente;
import com.dansoft.empresaCoelho.Imovel;

public class ImovelDao {
	public static boolean create(Imovel imovel, Cliente cliente, Connection conn) throws SQLException {
		final String query = "INSERT INTO Imovel (matricula, endereco, ultima_leitura, penultima_leitura, Cliente_id) VALUES (?, ?, ?, ?, ?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, imovel.getMatricula());
			ps.setString(2, imovel.getEndereco());
			ps.setString(3, imovel.getUltimaLeitura());
			ps.setString(4, imovel.getPenultimaLeitura());
			ps.setInt(5, cliente.getId());

			ps.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<Imovel> readAll(Connection conn) throws SQLException {
		final String query = "SELECT * FROM Imovel";
		ArrayList<Imovel> imoveis = new ArrayList<Imovel>();

		try {
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Imovel Imovel = new Imovel();

				Imovel.setMatricula(rs.getString("matricula"));
				Imovel.setEndereco(rs.getString("endereco"));
				Imovel.setUltimaLeitura(rs.getString("ultima_leitura"));
				Imovel.setPenultimaLeitura(rs.getString("penultima_leitura"));

				imoveis.add(Imovel);
			}
			return imoveis;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Imovel readUnique(String matricula, Connection conn) throws Exception {
		final String query = "SELECT * FROM Imovel WHERE matricula = ?";
		Imovel imovel = null;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, matricula);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				imovel = new Imovel();
				imovel.setId(rs.getInt("id"));
				imovel.setMatricula(rs.getString("matricula"));
				imovel.setEndereco(rs.getString("endereco"));
				imovel.setUltimaLeitura(rs.getString("ultima_leitura"));
				imovel.setPenultimaLeitura(rs.getString("penultima_leitura"));
			}

			return imovel;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ArrayList<Imovel> readAllPerClient(Cliente cliente, Connection conn) throws Exception {
		final String query = "SELECT * FROM Imovel WHERE Cliente_id = ?";
		ArrayList<Imovel> imoveis = new ArrayList<Imovel>();
		Imovel imovel = null;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, cliente.getId());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				imovel = new Imovel();
				imovel.setMatricula(rs.getString("matricula"));
				imovel.setEndereco(rs.getString("endereco"));
				imovel.setUltimaLeitura(rs.getString("ultima_leitura"));
				imovel.setPenultimaLeitura(rs.getString("penultima_leitura"));
				
				imoveis.add(imovel);
			}

			return imoveis;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean update(Imovel imovel, String alteracao, String tipoAlteracao, Connection conn)
			throws SQLException {
		final String queryEndereco = "UPDATE Imovel SET endereco = ? WHERE matricula = ?";
		final String queryMorador = "UPDATE Imovel SET Cliente_id = ? WHERE matricula = ?";
		try {
			if (tipoAlteracao.equals("1")) {

				PreparedStatement ps = conn.prepareStatement(queryEndereco);

				ps.setString(1, alteracao);
				ps.setString(2, imovel.getMatricula());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			} else {
				PreparedStatement ps = conn.prepareStatement(queryMorador);

				ps.setInt(1, Integer.parseInt(alteracao));
				ps.setString(2, imovel.getMatricula());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (Exception e) {	
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete(Imovel Imovel, Connection conn) throws SQLException {
		final String query = "DELETE FROM Imovel WHERE cpf = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, Imovel.getMatricula());
			int rowsAffected = ps.executeUpdate();

			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
