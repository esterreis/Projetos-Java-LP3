package br.cnec.fcsl.entidade;

public class Aluno {
	private Long id;
	private String nome;
	private Double nota;
	private Integer faltas;

	public Aluno() {
		
	}

	public Aluno(Long id, String nome, Double nota, Integer faltas) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.faltas = faltas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getNota() {
		return nota;
	}

	public void setNota(Double nota) {
		this.nota = nota;
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

}
