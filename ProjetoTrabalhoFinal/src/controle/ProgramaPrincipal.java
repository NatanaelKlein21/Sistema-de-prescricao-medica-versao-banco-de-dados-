package controle;

import visao.JanelaPrincipal;

import java.io.IOException;

import modelo.Medicamento;
import modelo.Paciente;
import modelo.Usuario;

public class ProgramaPrincipal {
	
public static void main(String[] args) throws IOException {
		
		JanelaPrincipal j= new JanelaPrincipal();
		Usuario u= new Usuario();
		j.setVisible(true);
		Paciente p= new Paciente();
		Medicamento m = new Medicamento();
		
		PrescControle pcon= new PrescControle(p,j,m); 
		MedicamentosControle mc= new MedicamentosControle(m,j);
		AltaControle ac= new AltaControle(p,j);
		PacienteControle pc= new PacienteControle (p,j);
		ListaControle lc = new ListaControle (p,j);	
		UsuarioControle uc= new UsuarioControle(j,u);
		
		
	}
}
