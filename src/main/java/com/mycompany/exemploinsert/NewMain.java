/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exemploinsert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author marce
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Connection connection;
        try {
            String url = "jdbc:mysql://127.0.0.1:3307/test";
            String user = "root";
            String pass = "usbw";
            connection = DriverManager.getConnection(url,user,pass);
            
            String comando = "CREATE TABLE IF NOT EXISTS `usuarios` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `nome` varchar(30) NOT NULL,\n" +
                "  `usuario` varchar(30) DEFAULT NULL,\n" +
                "  `salario` decimal(14,2) DEFAULT NULL,\n" +
                "  `nascimento` date DEFAULT NULL,\n" +
                "  `dataCadastro` datetime DEFAULT NULL,\n" +
                "  `ativo` varchar(5) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;";
            
            Statement stmt = connection.createStatement();
            stmt.execute(comando);
            
            String comando2 = "insert into usuarios ("
                    + "nome,"
                    + "usuario,"
                    + "salario,"
                    + "nascimento,"
                    + "dataCadastro,"
                    + "ativo)"
                    + "values ("
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?);";
            PreparedStatement pstmt = connection.prepareStatement(comando2);
            
            Date nascimento = (Date) new DateTime(1979,11,21,0,0).toDate();
            pstmt.setString(1,"Marcelo Telles");
            pstmt.setString(2,"marcelot");
            pstmt.setFloat(3, 20000.50f);
            pstmt.setDate(4, new java.sql.Date(nascimento.getTime()));
            pstmt.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            pstmt.setInt(6, 1);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
