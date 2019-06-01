package br.ufscar.dc.dsw.locadora.dao;

import br.ufscar.dc.dsw.locadora.bean.Locadora;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LocadoraDAO {

    public LocadoraDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Locadora", "root", "root");
    }

    public void insert(Locadora locadora) {
        String sql = "INSERT INTO Locadora (cnpj, nome, email, senha, cidade) VALUES (?, ?, ?, ?, ?)";
        String INSERIR_PAPEL = "Insert into Papel (email, nome) values (?,?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement = conn.prepareStatement(sql);
            statement.setString(1, locadora.getCnpj());
            statement.setString(2, locadora.getNome());
            statement.setString(3, locadora.getEmail());
            statement.setString(4, new BCryptPasswordEncoder().encode(locadora.getSenha()));
            statement.setString(5, locadora.getCidade());
            statement.executeUpdate();
            statement.close();
            
            
            PreparedStatement roleStatement = conn.prepareStatement(INSERIR_PAPEL);
            roleStatement.setString(1, locadora.getEmail());
            roleStatement.setString(2, "ROLE_LOCADORA");
            roleStatement.executeUpdate();
            
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Locadora> getAll() {
        List<Locadora> listaLocadoras = new ArrayList<>();
        String sql = "SELECT * FROM Locadora";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                boolean ativo = resultSet.getBoolean("ativo");
                Locadora locadora = new Locadora(cnpj, nome, email, senha, cidade,ativo);
                listaLocadoras.add(locadora);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocadoras;
    }

    public void delete(String cnpj) {
        String sql = "DELETE FROM Locadora where cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Locadora locadora) {
        String sql = "UPDATE Locadora SET nome = ?, email = ?, senha = ?, cidade = ?";
        sql += " WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, locadora.getNome());
            statement.setString(2, locadora.getEmail());
            statement.setString(3, new BCryptPasswordEncoder().encode(locadora.getSenha()));
            statement.setString(4, locadora.getCidade());
            statement.setString(5, locadora.getCnpj());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public Locadora getPorEmail(String email) {
        Locadora locadora = null;
        String sql = "SELECT * FROM locadora WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cnpj = resultSet.getString("cnpj");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                boolean ativo = resultSet.getBoolean("ativo");
                locadora = new Locadora(cnpj, nome, email, senha, cidade, ativo);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locadora;
    }

    public Locadora get(String cnpj) {
        Locadora locadora = null;
        String sql = "SELECT * FROM locadora WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cidade = resultSet.getString("cidade");
                boolean ativo = resultSet.getBoolean("ativo");
                locadora = new Locadora(cnpj, nome, email, senha, cidade, ativo);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locadora;
    }
}

