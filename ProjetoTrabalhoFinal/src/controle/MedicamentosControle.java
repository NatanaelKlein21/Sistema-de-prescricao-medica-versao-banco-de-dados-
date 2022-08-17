package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import dao.DAOMedicamento;
import modelo.Medicamento;
import visao.JanelaPrincipal;

public class MedicamentosControle implements ActionListener, KeyListener {

	private Medicamento m;
	private JanelaPrincipal j;
	private DAOMedicamento meddao;
	
	public MedicamentosControle(Medicamento m, JanelaPrincipal j)throws IOException {
		
		this.m=m;
		this.j=j;
		meddao = new DAOMedicamento();
		j.getPainelMed().getFieldMedCod().addKeyListener(this);
		j.getPainelMed().getBtnMedCancelar().addActionListener(this);
		j.getPainelMed().getBtnMedSalvar().addActionListener(this);
		j.getPainelMed().getBtnMedRemover().addActionListener(this);
		j.getPainelMed().getBtnMedAtualizar().addActionListener(this);
		

		
	}
	
	public boolean mediVazio() {
		
		if (j.getPaineladm().getFieldAdmiNome().getText().isEmpty()||
			j.getPaineladm().getFieldAdmiCPF().getText().isEmpty()||
			j.getPaineladm().getFieldAdmiDataNasc().getText().isEmpty()||
			j.getPaineladm().getBoxAdmiUnidade().getSelectedItem().toString().isEmpty()
			) {
			
			return true;
		}
		return false;
	}
	
	public boolean listaExcecoes() {
		if (j.getPainelMed().getFieldMedCod().getText().isEmpty())
		{
			System.out.println("Campo CODIGO DE BARRA obrigatório");
			return true;
		}
			else if (j.getPainelMed().getFieldMedCod().getText().length() > 20)
			{
				System.out.println("maximo 20 caracteres no campo CODIGO DE BARRA");
				return true;
			}
			
		if(j.getPainelMed().getFieldMedNome().getText().isEmpty())
		{
			System.out.println("Campo NOME obrigatório");
			return true;
		}
			else if (j.getPainelMed().getFieldMedNome().getText().length() > 50)
			{
				System.out.println("no maximo 50 caracteres no campo NOME");
				return true;
			}
			else if (j.getPainelMed().getFieldMedNome().getText().length() < 5)
			{
				System.out.println("no minimo 5 caracteres no campo NOME");
				return true;
			}
			else if (Medicamento.isAlfanumerico(m.getNomeMED()))
			{
				System.out.println("Apenas caracteres alfanuméricos no campo NOME");
				return true;
			}
		return false;
	}
	
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		///////////////////////////////CANCELAR////////////////////////////////////
		if(e.getActionCommand().equals("Cancelar")) {
				j.getPainelMed().cancelarMed();
				j.inicio();
			}
		
		////////////////////////////////////SALVAR////////////////////////////////////////
		if(e.getActionCommand().equals("Salvar")){

			m.setCodigo(j.getPainelMed().getFieldMedCod().getText());
			m.setNomeMED(j.getPainelMed().getFieldMedNome().getText());
			m.setGluten(j.getPainelMed().getChckbxMedGluten().isSelected());
			m.setFrutosdomar(j.getPainelMed().getChckbxMedFrutosdoMar().isSelected());
			m.setPenicilina(j.getPainelMed().getChckbxMedPenicilina().isSelected());
			m.setDipirona(j.getPainelMed().getChckbxMedDipirona().isSelected());
			
			if (listaExcecoes()) {
                return;
			}
			
			if(meddao.consultaCod(m.getCodigo())){//Verifica se ja existe um medicamento com o codigo digitado
				System.out.println("Erro! O codigo do medicamento já existe");
			}else {
				if(meddao.cadastraMedicamento(m))// caso não exista o medicamento é cadastrado
				{
					System.out.println("medicamento cadastrado com sucesso");
					j.getPainelMed().cancelarMed();					
				}else{
					System.out.println("erro ao cadastrar");
				}
			}
					
				
		}
		if(e.getActionCommand().equals("Remover")){
			
			m.setCodigo(j.getPainelMed().getFieldMedCod().getText());
			m.setNomeMED(j.getPainelMed().getFieldMedNome().getText());
			m.setGluten(j.getPainelMed().getChckbxMedGluten().isSelected());
			m.setFrutosdomar(j.getPainelMed().getChckbxMedFrutosdoMar().isSelected());
			m.setPenicilina(j.getPainelMed().getChckbxMedPenicilina().isSelected());
			m.setDipirona(j.getPainelMed().getChckbxMedDipirona().isSelected());
			
			if (j.getPainelMed().getFieldMedCod().getText().isEmpty())
			{
				System.out.println("Campo CODIGO DE BARRA obrigatório");

			}
				else if (j.getPainelMed().getFieldMedCod().getText().length() > 20)
				{
					System.out.println("maximo 20 caracteres no campo CODIGO DE BARRA");
				}
			
			if(meddao.consultaCod(m.getCodigo())) {// Verifica se o codigo do medicamento existe na tabela medicamentos
				if(meddao.removeMed(m.getCodigo())) {// remove o medicamento da tabela medicamentos caso este não esteja na prescrição
					System.out.println("medicamento removido com sucesso!");
					j.getPainelMed().cancelarMed();
				}else {
					System.out.println("Falha ao remover medicamento!");
					j.getPainelMed().cancelarMed();
				}
			}else {
				System.out.println("Codigo de medicamento não encontrado!");
				j.getPainelMed().cancelarMed();
			}
			
			
		}
		
		if(e.getActionCommand().equals("Atualizar")) {
			
			m.setCodigo(j.getPainelMed().getFieldMedCod().getText());
			m.setNomeMED(j.getPainelMed().getFieldMedNome().getText());
			m.setGluten(j.getPainelMed().getChckbxMedGluten().isSelected());
			m.setFrutosdomar(j.getPainelMed().getChckbxMedFrutosdoMar().isSelected());
			m.setPenicilina(j.getPainelMed().getChckbxMedPenicilina().isSelected());
			m.setDipirona(j.getPainelMed().getChckbxMedDipirona().isSelected());
			
			if (listaExcecoes()) {
                return;
			}
			
			if(meddao.consultaCod(m.getCodigo())) {// Verifica se o codigo do medicamento existe na tabela medicamentos
				if(meddao.atualizaMed(m)) {// atualiza o medicamento da tabela medicamentos caso este não esteja na prescrição
					System.out.println("medicamento atualizado com sucesso!");
					j.getPainelMed().cancelarMed();
				}else {
					System.out.println("Falha ao atualizar medicamento!");
					j.getPainelMed().cancelarMed();
				}
			}else {
				System.out.println("Codigo de medicamento não encontrado!");
				j.getPainelMed().cancelarMed();
				
			}
			
			
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {// Ao completar o campo do codigo do medicamento e apertar enter o programa entra neste if
			
			m.setCodigo(j.getPainelMed().getFieldMedCod().getText());
			if(meddao.consultaCod(m.getCodigo())) {// Verifica se ha um medicamento existente e se haver, chama a função que consultara o banco de dados e retornara os valores para completar os demais campos
				
				m=meddao.consultaMed(m.getCodigo());
				
				j.getPainelMed().getFieldMedNome().setText(m.getNomeMED());
				
				if(m.getGlut().equals("S")) {
				    j.getPainelMed().getChckbxMedGluten().setSelected(true);
				}else if(m.getGlut().equals("N")){
					j.getPainelMed().getChckbxMedGluten().setSelected(false);
				}
				if(m.getFrut().equals("S")) {
					j.getPainelMed().getChckbxMedFrutosdoMar().setSelected(true);
				}else if(m.getFrut().equals("N")){
					j.getPainelMed().getChckbxMedFrutosdoMar().setSelected(false);
				}
				if(m.getPeni().equals("S")) {
				    j.getPainelMed().getChckbxMedPenicilina().setSelected(true);
				}else if(m.getPeni().equals("N")){
					j.getPainelMed().getChckbxMedPenicilina().setSelected(false);
				}
				if(m.getDipi().equals("S")) {
				    j.getPainelMed().getChckbxMedDipirona().setSelected(true);
				}else if(m.getDipi().equals("N")){
					j.getPainelMed().getChckbxMedDipirona().setSelected(false);
				}
				
			}else {
				System.out.println("Erro! O codigo pesquisado não foi encontrado!");
			}
			
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
