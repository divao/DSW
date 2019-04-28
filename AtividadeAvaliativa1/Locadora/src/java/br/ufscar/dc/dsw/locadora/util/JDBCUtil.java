package br.ufscar.dc.dsw.locadora.util;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class JDBCUtil {

    private static DataSource dataSource;

    public static DataSource getDataSource() throws ClassNotFoundException {

        if (dataSource == null) {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/Locadora";
            String user = "root";
            String password = "root";
            dataSource = new DriverManagerDataSource(url, user, password);
        }

        return dataSource;
    }
}
