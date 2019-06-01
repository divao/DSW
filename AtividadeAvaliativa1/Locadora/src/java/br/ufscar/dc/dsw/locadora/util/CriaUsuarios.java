package br.ufscar.dc.dsw.locadora.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.sql.DataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriaUsuarios {

    public static void main(String[] args) throws ClassNotFoundException {

        try {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            DataSource ds = JDBCUtil.getDataSource();

            Connection conn = ds.getConnection();

            String INSERIR_CLIENTE = "insert into Cliente (cpf,nome,email,senha,telefone,dataDeNascimento,sexo,ativo) "
                    + "values (?,?,?,?,?,?,?,?)";
            
            String INSERIR_LOCADORA = "insert into Locadora (cnpj,nome,email,senha,cidade,ativo) "
                    + "values (?,?,?,?,?,?)";

            String INSERIR_PAPEL = "Insert into Papel (email, nome)"
                    + "values (?,?)";

            // Criando Cliente admin com papel ROLE_ADMIN
            
            PreparedStatement userStatement = conn.prepareStatement(INSERIR_CLIENTE);
            userStatement.setString(1, "1");
            userStatement.setString(2, "administrador");
            userStatement.setString(3, "admin@admin");
            userStatement.setString(4, encoder.encode("admin"));
            userStatement.setString(5, "11 1111 1111");
            userStatement.setString(6, "01/01/0001");
            userStatement.setString(7, "M");
            userStatement.setBoolean(8, true);
            userStatement.execute();

            PreparedStatement roleStatement = conn.prepareStatement(INSERIR_PAPEL);
            roleStatement.setString(1, "admin@admin");
            roleStatement.setString(2, "ROLE_ADMIN");
            roleStatement.execute();

            // Criando Cliente user com papel ROLE_USER
            userStatement.setString(1, "2");
            userStatement.setString(2, "usuario");
            userStatement.setString(3, "user@user");
            userStatement.setString(4, encoder.encode("user"));
            userStatement.setString(5, "11 1111 1111");
            userStatement.setString(6, "01/01/0001");
            userStatement.setString(7, "F");
            userStatement.setBoolean(8, true);
            userStatement.execute();

            roleStatement = conn.prepareStatement(INSERIR_PAPEL);
            roleStatement.setString(1, "user@user");
            roleStatement.setString(2, "ROLE_USER");
            roleStatement.execute();
            
            // Criando Locadora locadora com papel ROLE_LOCADORA
            PreparedStatement locadoraStatement = conn.prepareStatement(INSERIR_LOCADORA);
            locadoraStatement.setString(1, "1");
            locadoraStatement.setString(2, "locadora");
            locadoraStatement.setString(3, "locadora@locadora");
            locadoraStatement.setString(4, encoder.encode("locadora"));
            locadoraStatement.setString(5, "cidade");
            locadoraStatement.setBoolean(6, true);
            locadoraStatement.execute();

            roleStatement = conn.prepareStatement(INSERIR_PAPEL);
            roleStatement.setString(1, "locadora@locadora");
            roleStatement.setString(2, "ROLE_LOCADORA");
            roleStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
