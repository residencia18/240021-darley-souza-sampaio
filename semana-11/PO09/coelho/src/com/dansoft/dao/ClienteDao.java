package com.dansoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dansoft.empresaCoelho.Cliente;

public class ClienteDao {
	public static boolean create(Cliente cliente, Connection conn) throws SQLException {
		final String query = "INSERT INTO Cliente (nome, cpf) VALUES (?, ?) ";
		try {
			PreparedStatement ps = conn.prepareStatement(query);

			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCpf());

			ps.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ArrayList<Cliente> readAll(Connection conn) throws SQLException {
		final String query = "SELECT * FROM Cliente";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		try {
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Cliente cliente = new Cliente();
				

				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				
				clientes.add(cliente);
			}
			return clientes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Cliente readUnique(String cpf, Connection conn) throws Exception {
		final String query = "SELECT * FROM Cliente WHERE cpf = ?";
		Cliente cliente = null;

		try {
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, cpf);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
			}

			return cliente;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean update(Cliente cliente, String alteracao, String tipoAlteracao, Connection conn)
			throws SQLException {
		final String queryName = "UPDATE Cliente SET nome = ? WHERE cpf = ?";
		final String queryCpf = "UPDATE Cliente SET cpf = ? WHERE cpf = ?";
		try {
			if (tipoAlteracao.equals("1")) {

				PreparedStatement ps = conn.prepareStatement(queryName);

				ps.setString(1, alteracao);
				ps.setString(2, cliente.getCpf());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			} else {
				PreparedStatement ps = conn.prepareStatement(queryCpf);

				ps.setString(1, alteracao);
				ps.setString(2, cliente.getCpf());

				int rowsAffected = ps.executeUpdate();

				return rowsAffected > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean delete(Cliente cliente, Connection conn) throws SQLException {
		final String query = "DELETE FROM Cliente WHERE cpf = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, cliente.getCpf());
			int rowsAffected = ps.executeUpdate();
			
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
