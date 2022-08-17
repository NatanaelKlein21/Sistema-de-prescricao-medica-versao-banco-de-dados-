package controle;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import dao.DAOPaciente;
import modelo.Paciente;
import visao.JanelaPrincipal;



public class AltaControle implements ActionListener  {
	
	private Paciente p;
	private JanelaPrincipal j;
	private DAOPaciente pacdao;
	
	public AltaControle (Paciente p, JanelaPrincipal j) throws IOException {
		
		this.p=p;
		this.j=j;
		pacdao = new DAOPaciente();
		this.j.getPainelAlta().getBtnAltaCancelar().addActionListener(this);
		this.j.getPainelAlta().getBtnAltaSalvar().addActionListener(this);	
	}
	
	public boolean listaExcecoes() {
		if (j.getPainelAlta().getFieldAltaCPF().getText().isEmpty())
		{
			System.out.println("Campo CPF obrigat√≥rio");
			return true;
		}
			else if (!Paciente.isCPF(p.getCpf())) 
			{
				System.out.println("CPF Inv√°lido");
				return true;
			}
		if (j.getPainelAlta().getBoxAltaMotivo().getSelectedItem() == "") 
		{
			System.out.println("Campo MOTIVO obrigat√≥rio");
			return true;
		}
		return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Salvar")){
			
			p.setCpf(j.getPainelAlta().getFieldAltaCPF().getText());
			p.setMotivoAlta(j.getPainelAlta().getBoxAltaMotivo().getSelectedItem().toString());
			
			if (listaExcecoes()) {
                return;
			}
			
			if(pacdao.consultaCPF(p.getCpf())) {// Primeiro Verifica se o cpf digitado existe na tabela paciente
				if(pacdao.consultaAlta(p.getCpf())) { //se retornar true existe um cpf correspondente na tabela paciente, ent„o verifica-se se o cpf j· n„o foi cadastrado na tabela alta
					System.out.println("Este paciente ja recebeu Alta");// se retornar true, o paciente ja foi cadastrado na tabela alta, ent„o exibe-se uma mensagem
				}else {// se retornar false o paciente n„o recebeu alta
					if(pacdao.cadastraAlta(p)){//por fim È cadastrada a alta do paciente.
						System.out.println("Alta realizada com sucesso!");
					}else {
						System.out.println("Erro ao realizar a Alta!");
					}
				}	
			}else {
				System.out.println("cpf n„o encontrado!");
			}
			
			//pacdao.retornaPacienteAlta(p);
			j.getPainelAlta().cancelarAlta();
			//pacdao.apagaPaciente(p);
		}
		
		if(e.getActionCommand().equals("Cancelar")) {
			j.getPainelAlta().cancelarAlta();
			j.inicio();
		}
	}

	
}