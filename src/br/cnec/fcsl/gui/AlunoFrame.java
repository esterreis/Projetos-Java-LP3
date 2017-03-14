package br.cnec.fcsl.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.cnec.fcsl.entidade.Aluno;
import br.cnec.fcsl.persistencia.AlunoCRUD;
import framework.componente.TTable;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class AlunoFrame extends JFrame implements ActionListener, CaretListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5578551194606514407L;
	private JPanel contentPane;
	private JPanel panel;
	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSair;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNome;
	private JTextField campoPesquisa;
	private TTable<Aluno> table;
	private Aluno aluno;
	private List<Aluno> listaAlunos = new ArrayList<Aluno>();
	private AlunoCRUD crud = new AlunoCRUD();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					AlunoFrame frame = new AlunoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public AlunoFrame() throws SQLException {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("Cadastro de Alunos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 497);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		panel = new JPanel();

		panel_1 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 463,
										Short.MAX_VALUE)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE))
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE).addContainerGap()));
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Pesquisar aluno", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.add(panel_2, BorderLayout.NORTH);

		lblNome = new JLabel("Nome:");

		campoPesquisa = new JTextField();
		campoPesquisa.addCaretListener(this);
		campoPesquisa.setColumns(10);

		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addContainerGap().addComponent(lblNome).addGap(18)
						.addComponent(campoPesquisa, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup().addGap(5)
						.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(lblNome).addComponent(
								campoPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))));
		panel_2.setLayout(gl_panel_2);

		table = new TTable<Aluno>(Aluno.class);
		table.setColumnName("id", "Código");
		listaAlunos = crud.consulta();
		table.setDados(listaAlunos);
		table.refresh();

		panel_1.add(table, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnNovo = new JButton("Novo");
		btnNovo.addActionListener(this);
		btnNovo.setIcon(new ImageIcon(AlunoFrame.class.getResource("/imagem/novo.png")));
		panel.add(btnNovo);

		btnAlterar = new JButton("Alterar");
		this.btnAlterar.addActionListener(this);
		btnAlterar.setIcon(new ImageIcon(AlunoFrame.class.getResource("/imagem/alterar.png")));
		panel.add(btnAlterar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(this);
		btnExcluir.setIcon(new ImageIcon(AlunoFrame.class.getResource("/imagem/excluir.png")));
		panel.add(btnExcluir);

		btnSair = new JButton("Sair");
		btnSair.addActionListener(this);
		btnSair.setIcon(new ImageIcon(AlunoFrame.class.getResource("/imagem/sair.png")));
		panel.add(btnSair);
		contentPane.setLayout(gl_contentPane);
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnExcluir) {

			try {
				do_btnExcluir_actionPerformed(arg0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (arg0.getSource() == this.btnAlterar) {

			do_btnAlterar_actionPerformed(arg0);

		}
		if (arg0.getSource() == btnSair) {
			do_btnSair_actionPerformed(arg0);
		}
		if (arg0.getSource() == btnNovo) {

			try {
				do_btnNovo_actionPerformed(arg0);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	protected void do_btnNovo_actionPerformed(ActionEvent arg0) throws SQLException {
		AlunoCadastroDialog dialog = new AlunoCadastroDialog();
		dialog.setVisible(true);

		listaAlunos = crud.consulta();
		table.setDados(listaAlunos);
		table.refresh();

	}

	protected void do_btnSair_actionPerformed(ActionEvent arg0) {
		if (JOptionPane.showConfirmDialog(null, "Deseja realmente sair da aplicação?", "Confirmação",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	protected void do_btnAlterar_actionPerformed(ActionEvent arg0) {
		Aluno alunoSelecionado = (Aluno) table.getSelecionado();
		AlunoCadastroDialog dialog = new AlunoCadastroDialog();
		dialog.setAluno(alunoSelecionado);
		dialog.setVisible(true);
		table.refresh();
	}

	protected void do_btnExcluir_actionPerformed(ActionEvent arg0) throws SQLException {
		Aluno alunoSelecionado = (Aluno) table.getSelecionado();
		crud.exclui(alunoSelecionado);
		listaAlunos = crud.consulta();
		table.setDados(listaAlunos);
		table.refresh();
	}

	public void caretUpdate(CaretEvent e) {
		if (e.getSource() == campoPesquisa) {

			try {
				do_campoPesquisa_caretUpdate(e);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	protected void do_campoPesquisa_caretUpdate(CaretEvent e) throws SQLException {

		listaAlunos = crud.pesquisaAvançada(campoPesquisa.getText());
		table.setDados(listaAlunos);
		table.refresh();

	}
}
