package br.cnec.fcsl.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conexao {
	public static Connection conector(){
        java.sql.Connection conexao = null;   
        try {
            Class.forName(ParametrosMySql.DRIVER);
            conexao = DriverManager.getConnection(ParametrosMySql.URL, ParametrosMySql.USUARIO, ParametrosMySql.SENHA);
            return conexao;
        } catch (Exception e) {
            //a linha abaixo serve de apoio para esclarecer o erro
            JOptionPane.showMessageDialog(null, "Não há conexão com o Banco de Dados!");
            return null;
        }
}
}