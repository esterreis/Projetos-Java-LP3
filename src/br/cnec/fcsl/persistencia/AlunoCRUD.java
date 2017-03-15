package br.cnec.fcsl.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.cnec.fcsl.entidade.Aluno;

public class AlunoCRUD {
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public void inserir(Aluno aluno) throws SQLException{
		conexao = Conexao.conector(); 
		
		String sql = "INSERT INTO aluno (nome, nota, faltas) VALUES (?, ?, ?)";
		
		pst = conexao.prepareStatement(sql);
		pst.setString(1, aluno.getNome());
		pst.setDouble(2,  aluno.getNota());
		pst.setInt(3, aluno.getFaltas());
		pst.executeUpdate();
		conexao.close();
	}
	
	public List<Aluno> listarAlunos() throws SQLException {
		conexao = Conexao.conector();
		
		List<Aluno> lista = new ArrayList<Aluno>();
		
		String sql = "SELECT * FROM aluno";
		
		pst = conexao.prepareStatement(sql);
		rs = pst.executeQuery();
		while(rs.next()){
			lista.add(new Aluno(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
		}
		conexao.close();
		return lista;
	}
	
	public void atualiza(Aluno aluno) throws SQLException{
		conexao = Conexao.conector();
		
		String sql = "UPDATE aluno SET nome = ?, nota = ?, faltas = ? WHERE id = ?";
		
		pst = conexao.prepareStatement(sql);
		pst.setString(1, aluno.getNome());
		pst.setDouble(2, aluno.getNota());
		pst.setInt(3, aluno.getFaltas());
		pst.setLong(4, aluno.getId());
		pst.executeUpdate();
		conexao.close();
	}
	
	public void exclui(Aluno aluno) throws SQLException{
		conexao = Conexao.conector();
		
		String sql = "DELETE FROM aluno WHERE id = ?";
		
		pst = conexao.prepareStatement(sql);
		pst.setLong(1, aluno.getId());
		pst.executeUpdate();
		conexao.close();
	}
	
	public List<Aluno> pesquisaAvançada(String nome) throws SQLException{
		conexao = Conexao.conector();
		
		List<Aluno> lista = new ArrayList<Aluno>();
		
		String sql = "SELECT * FROM aluno WHERE nome LIKE ? ";
		pst = conexao.prepareStatement(sql);
		pst.setString(1, "%" + nome + "%");
		rs = pst.executeQuery();
		while(rs.next()){
			lista.add(new Aluno(rs.getLong(1), rs.getString(2), rs.getDouble(3), rs.getInt(4)));
		}
		conexao.close();
		return lista;
	}
}
