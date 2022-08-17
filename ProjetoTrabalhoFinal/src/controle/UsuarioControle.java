package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.DAOLogin;
import modelo.Usuario;
import visao.JanelaPrincipal;

public class UsuarioControle implements ActionListener {
	
	private JanelaPrincipal jan;
	private Usuario u;
	private DAOLogin udao;
	
	public UsuarioControle(JanelaPrincipal j, Usuario u)
	{
		jan=j;
		this.u=u;
		udao= new DAOLogin();
		jan.getPainelLog().getButtonAtutenticar().addActionListener(this);
		jan.getPainelLog().getButtonSair().addActionListener(this);
		
	}
	
	public void autenticar()
	{
		u.setUsuario(jan.getPainelLog().getFieldUsuario().getText());
		u.setSenha(jan.getPainelLog().getFieldSenha().getText());
		
		
		
		if(udao.autenticar(new Usuario(u.getUsuario(),u.getSenha())))// Fun��o que verifica se o usuario e senha digitados nos campos est�o no banco de dados
		{
			//Se for autenticado:
			JOptionPane.showMessageDialog(jan.getContentPane(), "Usu�rio aunteticado com sucesso!");// uma mensagem � exibida 
			jan.getPainelLog().limpaTela();
			jan.getMenuPrescricao().setEnabled(true);// os menus s�o ativados para a intera��o
			jan.getMenuPacientes().setEnabled(true);
			jan.setBounds(100, 100, 640, 480); // as bordas s�o alteradas para o tamanho 640x480
			jan.getCard().show(jan.getPainelCards(), "P1");// o programa inicia com o painel card vazio.
			
		}
		else//Se n�o estiverem uma mensagem � exibida na tela, e os campos s�o limpos
		{
			JOptionPane.showMessageDialog(jan.getContentPane(), "Erro na autentica��o do usu�rio!");
			jan.getPainelLog().limpaTela();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("Autenticar"))
		{
			autenticar();//chama a funcao autenticar
		}
		
		if(e.getActionCommand().equals("Sair"))
		{
			System.exit(0);//Fecha o programa
		}
		
	}

}