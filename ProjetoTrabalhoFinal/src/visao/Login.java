package visao;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JPanel {

	private JTextField fieldUsuario;
	private JTextField fieldSenha;
	private JButton buttonAtutenticar;
	private JButton buttonSair;

	/**
	 * Create the panel.
	 */
	public Login() {
		setLayout(new MigLayout("", "[][][][][][53.00][53.00][87.00][44.00][100.00][][][][]", "[][][][][][][][]"));
		
		JLabel labelTitulo = new JLabel("TELA DE LOGIN");
		labelTitulo.setFont(new Font("Tahoma", Font.BOLD, 25));
		add(labelTitulo, "cell 5 2 6 1");
		
		JLabel labelImagem = new JLabel("");
		labelImagem.setIcon(new ImageIcon(Login.class.getResource("/figuras/login.png")));
		add(labelImagem, "cell 0 2 5 6");
		
		JLabel labelUsuario = new JLabel("Usuario");
		add(labelUsuario, "flowx,cell 5 4");
		
		JLabel labelSenha = new JLabel("Senha");
		add(labelSenha, "flowx,cell 5 5");
		
		buttonAtutenticar = new JButton("Autenticar");
		add(buttonAtutenticar, "cell 5 6 2 1,growx");
		
		buttonSair = new JButton("Sair");
		add(buttonSair, "cell 7 6 2 1,growx");
		
		fieldUsuario = new JTextField();
		add(fieldUsuario, "cell 6 4 3 1,growx");
		fieldUsuario.setColumns(10);
		
		fieldSenha = new JTextField();
		add(fieldSenha, "cell 6 5 3 1,growx");
		fieldSenha.setColumns(10);

	}

	public JTextField getFieldUsuario() {
		return fieldUsuario;
	}

	public void setFieldUsuario(JTextField fieldUsuario) {
		this.fieldUsuario = fieldUsuario;
	}

	public JTextField getFieldSenha() {
		return fieldSenha;
	}

	public void setFieldSenha(JTextField fieldSenha) {
		this.fieldSenha = fieldSenha;
	}

	public JButton getButtonAtutenticar() {
		return buttonAtutenticar;
	}

	public void setButtonAtutenticar(JButton buttonAtutenticar) {
		this.buttonAtutenticar = buttonAtutenticar;
	}

	public JButton getButtonSair() {
		return buttonSair;
	}

	public void setButtonLimpar(JButton buttonSair) {
		this.buttonSair = buttonSair;
	}

	public void limpaTela() {
		getFieldSenha().setText("");
		getFieldUsuario().setText(""); 
		
	}

}
