package com.dansoft.empresaCoelhoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import com.dansoft.empresaCoelhoClasses.Cliente;

public class ClienteDao {
	public static boolean create(Cliente cliente, Connection conn) throws SQLException {
		final String query = "INSERT INTO Cliente (nome, cpf) VALUES (?, ?) ";

		try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());

            ps.execute();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            throw e;
        }

	}

	public static ArrayList<Cliente> readAll(Connection conn) throws SQLException, Exception {
		final String query = "SELECT * FROM Cliente";
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		PreparedStatement ps = conn.prepareStatement(query);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Cliente cliente = new Cliente();

			cliente.setId(rs.getInt("id"));
			cliente.setNome(rs.getString("nome"));
			cliente.setCpf(rs.getString("cpf"));

			clientes.add(cliente);
		}
		
		if (clientes.isEmpty())
			return null;
		return clientes;
	}

	public static Cliente readUnique(String cpf, Connection conn) throws SQLException, Exception {
		final String query = "SELECT * FROM Cliente WHERE cpf = ?";
		Cliente cliente = null;

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

	}

	public static boolean update(Cliente cliente, String alteracao, String tipoAlteracao, Connection conn)
			throws SQLException, Exception {
		final String queryName = "UPDATE Cliente SET nome = ? WHERE cpf = ?";
		final String queryCpf = "UPDATE Cliente SET cpf = ? WHERE cpf = ?";

		if (tipoAlteracao.equals("1")) {

			PreparedStatement ps = conn.prepareStatement(queryName);

			ps.setString(1, alteracao);
			ps.setString(2, cliente.getCpf());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0)
				return true;
			return false;
		} else {
			PreparedStatement ps = conn.prepareStatement(queryCpf);

			ps.setString(1, alteracao);
			ps.setString(2, cliente.getCpf());

			int rowsAffected = ps.executeUpdate();

			if (rowsAffected > 0)
				return true;
			return false;
		}
	}

	public static boolean delete(Cliente cliente, Connection conn) throws SQLException {
		final String query = "DELETE FROM Cliente WHERE cpf = ?";

		PreparedStatement ps = conn.prepareStatement(query);

		ps.setString(1, cliente.getCpf());
		int rowsAffected = ps.executeUpdate();

		if (rowsAffected > 0)
			return true;
		return false;

	}

}
