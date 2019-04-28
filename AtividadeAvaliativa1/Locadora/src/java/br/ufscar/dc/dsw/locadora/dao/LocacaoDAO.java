
package br.ufscar.dc.dsw.locadora.dao;


import br.ufscar.dc.dsw.locadora.bean.Locacao;
import br.ufscar.dc.dsw.locadora.util.JDBCUtil;
import java.sql.Connection;
import java.sql.Date;
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


public class LocacaoDAO {
    public LocacaoDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:derby://localhost:1527/Locadora", "root", "root");
    }

    public void insert(Locacao locacao) {
        String sql = "insert into Locacao (cpf,cnpj,dataLocacao,horario) VALUES (?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;
            statement = conn.prepareStatement(sql);
            statement.setString(1, locacao.getCpf());
            statement.setString(2, locacao.getCnpj());
            statement.setDate(3, (Date) locacao.getData());
            statement.setInt(4, locacao.getHorario());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }

    public List<Locacao> getAll(String cpf) {
        List<Locacao> listaLocacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacao WHERE CPF = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,cpf);
            
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String cpf1 = resultSet.getString("cpf");
                String cnpj = resultSet.getString("cnpj");
                Date data = resultSet.getDate("dataLocacao");
                int horario = resultSet.getInt("horario");
                
                Locacao locacao = new Locacao(cpf1,cnpj,data,horario);        
                listaLocacoes.add(locacao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocacoes;
    }

    public void delete(Locacao locacao) {
        String sql = "DELETE FROM Locacao where cpf = ? and cnpj = ? and dataLocacao = ? and horario = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, locacao.getCpf());
            statement.setString(2, locacao.getCnpj());
            statement.setDate(3, (Date) locacao.getData());
            statement.setInt(4, locacao.getHorario());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Locacao locacao) {
        String sql = "UPDATE Locacao SET cpf = ?, cnpj = ?, dataLocacao = ?, horario = ?";
        sql += " WHERE cpf = ? and cnpj = ? and dataLocacao = ? and horario = ?";
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            DataSource ds = JDBCUtil.getDataSource();
            Connection conn = ds.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, locacao.getCpf());
            statement.setString(2, locacao.getCnpj());
            statement.setDate(3, (Date) locacao.getData());
            statement.setInt(4, locacao.getHorario());
            statement.setString(5, locacao.getCpf());
            statement.setString(6, locacao.getCnpj());
            statement.setDate(7, (Date) locacao.getData());
            statement.setInt(8, locacao.getHorario());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Locacao> getPorCpf(String cpf) {
        List<Locacao> listaLocacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacao WHERE cpf = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf1 = resultSet.getString("cpf");
                String cnpj = resultSet.getString("cnpj");
                java.util.Date data = resultSet.getDate("dataLocacao");
                int horario = resultSet.getInt("telefone");
                Locacao locacao = new Locacao(cpf1,cnpj,data,horario);
                listaLocacoes.add(locacao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocacoes;
    }
    
    public List<Locacao> getPorCnpj(String cnpj) {
        List<Locacao> listaLocacoes = new ArrayList<>();
        String sql = "SELECT * FROM Locacao WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String cnpj1 = resultSet.getString("cnpj");
                java.util.Date data = resultSet.getDate("dataLocacao");
                int horario = resultSet.getInt("horario");
                Locacao locacao = new Locacao(cpf,cnpj1,data,horario);
                listaLocacoes.add(locacao);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocacoes;
    }
}
