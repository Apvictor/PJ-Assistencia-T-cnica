package br.com.housetech.dal;
/*
 * @author Armando
 */
import java.sql.*;
import javax.swing.JOptionPane;

public class ModuloConexao {
    //Método responsável por estabelecer a conexão com o banco
    public static Connection conector() {
        Connection conexao = null;
        //Chamar o Driver
        String driver = "com.mysql.jdbc.Driver";
        //Armazenando informações referente ao banco
        String url = "jdbc:mysql://localhost:3306/projeto_assistencia";
        String user = "root";
        String password = "";
        //Estabelecendo a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (ClassNotFoundException | SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro de Conexão! \nErro:" + erro.getMessage(),"Conexão",JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}