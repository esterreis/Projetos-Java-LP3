package br.cnec.fcsl.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import br.cnec.fcsl.entidade.Aluno;
import br.cnec.fcsl.persistencia.AlunoCRUD;

public class AlunoCadastroDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6094800202735629149L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblNome;
	private JTextField campoNome;
	private JLabel lblNota;
	private JTextField campoNota;
	private JLabel lblFaltas;
	private JSpinner campoFaltas;
	private JButton btnCancelar;
	private JButton btnSalvar;
	private JPanel panel;

	private Aluno aluno;
	private AlunoCRUD crud = new AlunoCRUD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AlunoCadastroDialog dialog = new AlunoCadastroDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AlunoCadastroDialog() {
		setTitle("Cadastro de aluno");
		setModal(true);
		setBounds(100, 100, 450, 256);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCadastroDeAluno = new JLabel("Cadastro de Aluno");
		lblCadastroDeAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastroDeAluno.setForeground(Color.BLUE);
		lblCadastroDeAluno.setFont(new Font("Tahoma", Font.BOLD, 14));

		lblNome = new JLabel("Nome:");

		campoNome = new JTextField();
		campoNome.setColumns(10);

		lblNota = new JLabel("Nota:");

		campoNota = new JTextField();
		campoNota.setColumns(10);

		lblFaltas = new JLabel("Faltas:");

		campoFaltas = new JSpinner();

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(
						gl_contentPanel
								.createParallelGroup(
										Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup().addContainerGap()
										.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
												.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 404,
														Short.MAX_VALUE)
												.addComponent(lblCadastroDeAluno, GroupLayout.DEFAULT_SIZE, 404,
														Short.MAX_VALUE)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblFaltas)
																.addGroup(gl_contentPanel
																		.createParallelGroup(Alignment.TRAILING, false)
																		.addComponent(lblNota, Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(lblNome, Alignment.LEADING,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
														.addGap(18)
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(campoNome, Alignment.TRAILING,
																		GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
																.addComponent(campoNota, Alignment.TRAILING,
																		GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
																.addComponent(campoFaltas, GroupLayout.DEFAULT_SIZE,
																		353, Short.MAX_VALUE))))
										.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap().addComponent(lblCadastroDeAluno).addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNome).addComponent(
						campoNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblNota).addComponent(
						campoNota, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblFaltas).addComponent(
						campoFaltas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18).addComponent(panel, GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE).addContainerGap()));

		btnSalvar = new JButton("Salvar");
		this.btnSalvar.addActionListener(this);
		panel.add(btnSalvar);
		btnSalvar.setIcon(new ImageIcon(AlunoCadastroDialog.class.getResource("/imagem/salvar.png")));

		btnCancelar = new JButton("Fechar");
		btnCancelar.addActionListener(this);
		panel.add(btnCancelar);
		btnCancelar.setIcon(new ImageIcon(AlunoCadastroDialog.class.getResource("/imagem/sair.png")));
		contentPanel.setLayout(gl_contentPanel);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.btnSalvar) {
			
				try {
					do_btnSalvar_actionPerformed(arg0);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
		
		}
		if (arg0.getSource() == btnCancelar) {
			do_btnCancelar_actionPerformed(arg0);
		}
	}

	protected void do_btnCancelar_actionPerformed(ActionEvent arg0) {
		dispose();
	}

	protected void do_btnSalvar_actionPerformed(ActionEvent arg0) throws SQLException {
		if (aluno == null) { // Novo
			aluno = new Aluno();
		}

		if(aluno.getId() == null){
			aluno.setNome(campoNome.getText());
			aluno.setNota(Double.parseDouble(campoNota.getText()));
			aluno.setFaltas((Integer) campoFaltas.getValue());
			crud.inserir(aluno);
			
		} else {
		
			aluno.setNome(campoNome.getText());
			aluno.setNota(Double.parseDouble(campoNota.getText()));
			aluno.setFaltas((Integer) campoFaltas.getValue());
			crud.atualizar(aluno);
		}

		dispose();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
		atualizarTela(aluno);
	}

	private void atualizarTela(Aluno aluno) {
	
		campoNome.setText(aluno.getNome());
		campoNota.setText(aluno.getNota().toString());
		campoFaltas.setValue(aluno.getFaltas());		
	
	}

}
