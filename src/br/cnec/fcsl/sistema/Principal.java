package br.cnec.fcsl.sistema;

import java.sql.SQLException;

import br.cnec.fcsl.gui.AlunoFrame;

public class Principal {

	public static void main(String[] args) throws SQLException {
		new AlunoFrame().setVisible(true);
	}
	
}