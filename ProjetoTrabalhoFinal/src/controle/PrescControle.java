package controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import dao.DAOPresc;
import modelo.Medicamento;
import modelo.Paciente;
import visao.JanelaPrincipal;

public class PrescControle implements ActionListener, KeyListener {

	private Paciente p;
	private JanelaPrincipal j;
	private Medicamento m;
	private DAOPresc predao;
	private Paciente pac;
	private Medicamento med;
	private String str;
	private String glu = " ";
	private String fru = " ";
	private String dip = " ";
	private String pen = " ";
	private String paccpf;
	private String medcod;
	
	
	public PrescControle (Paciente p, JanelaPrincipal j, Medicamento m) throws IOException {
		
		this.p=p;
		this.j=j;
		this.m=m;
		this.predao= new DAOPresc();
		
		j.getPainelPresc().getBtnPrescricaoCancelar().addActionListener(this);
		j.getPainelPresc().getBtnPrescricaoSalvar().addActionListener(this);
		j.getPainelPresc().getFieldPrescricaoCPF().addKeyListener(this);
		j.getPainelPresc().getFieldPrescricaoMed1().addKeyListener(this);
	}
	
	public boolean listaExcecoes() {

        if (j.getPainelPresc().getFieldPrescricaoCPF().getText().isEmpty())
        {
            System.out.println("Campo CPF obrigatÃ³rio");
            return true;
        }

        if (j.getPainelPresc().getFieldPrescricaoMed1().getText().isEmpty())
        {
            System.out.println("Campo MEDICAMENTO obrigatÃ³rio");
            return true;
        }
        if (pac.getGlut().equals("S")) {
            if(med.getGlut().equals("S")) {
                System.out.println("Risco de alergia para GLUTEN");
                return true;
            }
        }
        if (pac.getFrut().equals("S")) {
            if(med.getFrut().equals("S")) {
                System.out.println("Risco de alergia para FRUTOS DO MAR");
                return true;
            }
        }
        if (pac.getDipi().equals("S")) {
            if(med.getDipi().equals("S")) {
                System.out.println("Risco de alergia para DIPIRONA");
                return true;
            }
        }
        if (pac.getPeni().equals("S")) {
            if(med.getPeni().equals("S")) {
                System.out.println("Risco de alergia para PENICILINA");
                return true;
            }
        }
        return false;

    }
	
	public void actionPerformed(ActionEvent e) {		
		
		if(e.getActionCommand().equals("Salvar")) {
			
			paccpf = j.getPainelPresc().getFieldPrescricaoCPF().getText();
			medcod = j.getPainelPresc().getFieldPrescricaoMed1().getText();
			
			if (listaExcecoes())
			{
				return;
			}
			
			if(predao.salvaPresc(paccpf,medcod)) {// se todas a exceções forem atendidas, chama a função que cadastra a prescrição no banco de dados.
				System.out.println("Prescricao cadastrada");
				j.getPainelPresc().cancelarPresc();
			}
			
		}
		
		
		if(e.getActionCommand().equals("Cancelar")) {
			j.getPainelPresc().cancelarPresc();
			
			glu= " ";
            fru= " ";
            dip = " ";
            pen = " ";
			
			j.inicio();
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode()==KeyEvent.VK_ENTER) {// Ao pressionar enter os demais dados do paciente e do medicamento são amostrados.
			
			p.setCpf(j.getPainelPresc().getFieldPrescricaoCPF().getText());
			m.setCodigo(j.getPainelPresc().getFieldPrescricaoMed1().getText());
			
			if(predao.consultaCPF(p.getCpf())) {// verifica se o cpf digitado existe na tabela paciente
				
				pac = predao.retornaPacienteCPF(p);// se existir chama a função que retorna seus demais valores para exibição
				
				//------------------------------------------EXIBIÇÃO DOS VALORES DO PACIENTE---------------------------------------
				
				j.getPainelPresc().getFieldPrescricaoNome().setText(pac.getNome());
				j.getPainelPresc().getFieldPrescricaoDataNasc().setText(pac.getDatanasc());
						
				if(pac.getGlut().equals("S")) {
				    j.getPainelPresc().getChckbxPrescricaoGluten().setSelected(true);
				}else if(pac.getGlut().equals("N")) {
				    j.getPainelPresc().getChckbxPrescricaoGluten().setSelected(false);
				}
				if(pac.getFrut().equals("S")) {
				    j.getPainelPresc().getChckbxPrescricaoFrutosdoMar().setSelected(true);
				}else if(pac.getFrut().equals("N")) {
				    j.getPainelPresc().getChckbxPrescricaoFrutosdoMar().setSelected(false);
				}
				if(pac.getPeni().equals("S")) {
				    j.getPainelPresc().getChckbxPrescricaoPenicilina().setSelected(true);
				}else if(pac.getPeni().equals("N")) {
				    j.getPainelPresc().getChckbxPrescricaoPenicilina().setSelected(false);
				}
				if(pac.getDipi().equals("S")) {
				    j.getPainelPresc().getChckbxPrescricaoDipirona().setSelected(true);
				}else if(pac.getDipi().equals("N")) {
				    j.getPainelPresc().getChckbxPrescricaoDipirona().setSelected(false);
				}
				
			}else{// Se não existir emite um aviso
				System.out.println("Paciente não encontrado!");
			}
			
			
			if(predao.consultaMed(m.getCodigo())) {//verifica se o codigo digitado existe na tabela medicamentos
				
				med = predao.retornaMedicamentoInfo(m);// se existir chama a função que retorna seus demais valores para exibição
				
				//------------------------------------------EXIBIÇÃO DOS VALORES DO MEDICAMENTO---------------------------------------
				if(med.getGlut().equals("S")) {
				    glu = ("Gluten, ");
				}else if(med.getGlut().equals("N")){
				    glu=(" ");
				}
				if(med.getFrut().equals("S")) {
				    fru = ("fruts. do Mar, ");
				}else if(med.getFrut().equals("N")){
				    fru=(" ");
				}
				if(med.getPeni().equals("S")) {
				    pen = ("Penicilina, ");
				}else if(med.getPeni().equals("N")){
				    pen=(" ");
				}
				if(med.getDipi().equals("S")) {
				    dip = ("Dipirona, ");
				}else if(med.getDipi().equals("N")){
				    dip=(" ");
				}
				str = med.getNomeMED()+". Alergias: "+glu+fru+pen+dip;
				
				j.getPainelPresc().getFieldPrescricaoMed2().setText(str);
			}else {// Se não existir emite um aviso
				System.out.println("Medicamento não encontrado!");
			}
			
			
			 
			
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
