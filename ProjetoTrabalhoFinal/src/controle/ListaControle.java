package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import dao.DAOLista;
import visao.JanelaPrincipal;
import modelo.Paciente;

public class ListaControle implements ActionListener {
	
	private Paciente p;
	private JanelaPrincipal j;
	private DAOLista lisdao;
	
	public ListaControle (Paciente p, JanelaPrincipal j) throws IOException {
		
		this.p=p;
		this.j=j;
		lisdao= new DAOLista();
		this.j.getPainelLista().getBtnListaCancelar().addActionListener(this);
		this.j.getMenuListaInternados().addActionListener(this);
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		//////////////////////////////////CANCELAR////////////////////////////////
		
			if(e.getActionCommand().equals("Cancelar")) {
				j.getPainelLista().cancelarLista();
				j.inicio();
				
			}
			
			if(e.getActionCommand().equals("Lista de Internados")){
				
				
					j.getPainelLista().cancelarLista();
					Paciente[] paciente= new Paciente[10]; // cria um array da classe Paciente para armazenar e exibir os pacientes.
					paciente = lisdao.completaLista();// Chamando a função dao que consulta o banco de dados e retorna a lista de pacientes que estão internados 
					/*---------------------------------CODIGO RESPONSAVEL PELA IMPRESSÃO NA LISTA DE PACIENTES----------------------------------*/
					for(int i=0; i<paciente.length;i++) {
						if(paciente[i]!=null) {
							
							j.getPainelLista().getTextAreaNome().append(" "+paciente[i].getNome()+"\n"+"\n"+"\n");
							j.getPainelLista().getTextAreaUnidade().append(" "+paciente[i].getUnidade()+"\n"+"\n"+"\n");
							j.getPainelLista().getTextAreaDataNasc().append(" "+paciente[i].getDatanasc()+"\n"+"\n"+"\n");
							if(paciente[i].getGlut().equals("S")) {
								j.getPainelLista().getTextAreaAlergias().append(" Gluten: S ");
							}else {
								j.getPainelLista().getTextAreaAlergias().append(" Gluten: N ");
							}
							if(paciente[i].getFrut().equals("S")) {
								j.getPainelLista().getTextAreaAlergias().append("Frut.do mar: S ");
							}else {
								j.getPainelLista().getTextAreaAlergias().append("Frut.do mar: N ");
							}
							
							if(paciente[i].getPeni().equals("S")) {
								j.getPainelLista().getTextAreaAlergias().append("\n Penicilina: S ");
							}else {
								j.getPainelLista().getTextAreaAlergias().append("\n Penicilina: N ");
							}
							if(paciente[i].getDipi().equals("S")) {
								j.getPainelLista().getTextAreaAlergias().append("Dipirona: S ");
							}else {
								j.getPainelLista().getTextAreaAlergias().append("Dipirona: N ");
							}
							j.getPainelLista().getTextAreaAlergias().append("\n\n");
							
						}else {
							
						}
					}
				
			}
			
	}
}
