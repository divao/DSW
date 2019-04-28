/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.dsw.locadora.dao;

import br.ufscar.dc.dsw.locadora.bean.Cliente;
import br.ufscar.dc.dsw.locadora.util.JDBCUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Jackson Victor
 */
public class ClienteDAO {
    

    
    public ClienteDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Locadora", "root", "root");
    }

    public void insert(Cliente cliente) {
        String sql = "insert into Cliente (cpf,nome,email,senha,telefone,dataDeNascimento,sexo,ativo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            DataSource ds = JDBCUtil.getDataSource();
            Connection conn = ds.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getCpf());
            statement.setString(2, cliente.getNome());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, encoder.encode(cliente.getSenha()));
            statement.setString(5, cliente.getTelefone());
            statement.setString(6, cliente.getDataNascimento());
            statement.setString(7, Character.toString(cliente.getSexo()));
            statement.setBoolean(8, cliente.isAtivo());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cliente> getAll() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String dataNascimento = resultSet.getString("dataDeNascimento");
                char sexo = resultSet.getString("sexo").charAt(0);
                boolean ativo = resultSet.getBoolean("ativo");
                
                Cliente cliente = new Cliente(cpf, nome, email, senha, telefone, dataNascimento, sexo, ativo);
                listaClientes.add(cliente);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaClientes;
    }

    public void delete(String cpf) {
        String sql = "DELETE FROM Cliente where cpf = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cpf);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET nome = ?, email = ?, senha = ?, telefone = ?, dataDeNascimento = ?, sexo = ?, ativo = ?";
        sql += " WHERE cpf = ?";
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            DataSource ds = JDBCUtil.getDataSource();
            Connection conn = ds.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getNome());
            statement.setString(2, cliente.getEmail());
            statement.setString(3, encoder.encode(cliente.getSenha()));
            statement.setString(4, cliente.getTelefone());
            statement.setString(5, cliente.getDataNascimento());
            statement.setString(6, Character.toString(cliente.getSexo()));
            statement.setBoolean(7, cliente.isAtivo());
            statement.setString(8, cliente.getCpf());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente get(String cpf) {
        Cliente cliente = null;
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String telefone = resultSet.getString("telefone");
                String dataNascimento = resultSet.getString("dataDeNascimento");
                char sexo = resultSet.getString("sexo").charAt(0);
                boolean ativo = resultSet.getBoolean("ativo");
                cliente = new Cliente(cpf, nome, email, senha, telefone, dataNascimento, sexo, ativo);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}
