package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Paciente;
import visao.JanelaPrincipal;
import modelo.Medicamento;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class DAOPresc {
	
	public DAOPresc(){
		
		
		
		
		
	}
	
	
	public boolean consultaCPF(String c) {// Função que verifica a existencia do cpf na tabela paciente
		PreparedStatement prepS = null;
		String cpf = null;
		ResultSet res;
		String sql;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();

		sql = "Select * from Paciente where cpf like ?";

		try {
			prepS = con.prepareStatement(sql);
			prepS.setString(1, c);
			res = prepS.executeQuery();

			if (res.next())
			{
				ConexaoMySQL.fecharConexao();
				return true;
			}
				
			else
			{
				ConexaoMySQL.fecharConexao();
				return false;
			}
				

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	

	}
	
	public Paciente retornaPacienteCPF(Paciente p)  {// Função que recebe um objeto da classe paciente com apenas o valor de cpf preenchido. E pesquisa o cpf no banco de dados retornando os demais valores para o paciente para a comparação com as contra indicações dos medicamentos.
		
		PreparedStatement prepS = null;
		ResultSet res;
		String nome = null, unidade = null, datanasc = null, pglut = null, pfrut = null, pdipi = null, ppeni = null;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		String sql= "Select* from Paciente Where cpf like ?";
		
		
			try {
			
				prepS = con.prepareStatement(sql);
				prepS.setString(1, p.getCpf());
				res = prepS.executeQuery();
			
				while(res.next()) {
					nome = res.getString(2);
					unidade= res.getString(3);
					datanasc=res.getString(4);
					pglut=res.getString(5);
					pfrut= res.getString(6);
					pdipi= res.getString(7);
					ppeni= res.getString(8);
				}
				p.setNome(nome);
				p.setUnidade(unidade);
				p.setDatanasc(datanasc);
				p.setGlut(pglut);
				p.setFrut(pfrut);
				p.setDipi(pdipi);
				p.setPeni(ppeni);
			
			
			
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				ConexaoMySQL.fecharConexao();
			}
			ConexaoMySQL.fecharConexao();
			return p;
		
		
	}
	
	public boolean consultaMed(String c) {// função que verifica a existencia do codigo de medicamento na tabela medicamentos.
		PreparedStatement prepS = null;
		String codigo = null;
		ResultSet res;
		String sql;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();

		sql = "Select * from Medicamentos where codigo like ?";

		try {
			prepS = con.prepareStatement(sql);
			prepS.setString(1, c);
			res = prepS.executeQuery();

			if (res.next())
			{
				ConexaoMySQL.fecharConexao();
				return true;
			}
				
			else
			{
				ConexaoMySQL.fecharConexao();
				return false;
			}
				

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
	

	}
	
	public Medicamento retornaMedicamentoInfo(Medicamento m) {// Função que recebe um medicamento com apenas seu codigo atribuido. E então pesquisa no banco de dados, na tabela medicamentos, retornando os demais valores do medicamento para comparação com as alergias do paciente. 
		
		PreparedStatement prepS = null;
		ResultSet res;
		String nomeMed = null, mglut = null, mfrut = null, mdipi = null, mpeni = null;
		
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		String sql= "Select* from Medicamentos Where codigo like ?";
		
		
			try {
				prepS = con.prepareStatement(sql);
				prepS.setString(1, m.getCodigo());
				res = prepS.executeQuery();
			
				while(res.next()) {
					nomeMed = res.getString(2);
					mglut= res.getString(3);
					mfrut= res.getString(4);
					mdipi= res.getString(5);
					mpeni= res.getString(6);
				}
			
				m.setNomeMED(nomeMed);
				m.setGlut(mglut);
				m.setFrut(mfrut);
				m.setDipi(mdipi);
				m.setPeni(mpeni);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ConexaoMySQL.fecharConexao();
			}
			ConexaoMySQL.fecharConexao();
			return m;
		
		
	}
	
	public boolean salvaPresc(String cpf, String cod) {// Função responsavel pelo registro da prescrição no banco de dados.
		
		boolean flag=false;
		
		PreparedStatement prepS = null;
		
		String sql="Insert into Prescricao values(?,?,?)";
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		Date d = GregorianCalendar.getInstance().getTime();
		SimpleDateFormat format = new SimpleDateFormat();// instancia da variavel que apresentara a data e hora local
		
		
		try {
			prepS= con.prepareStatement(sql);
			prepS.setString(1, format.format(d));
			prepS.setString(2, cpf);
			prepS.setString(3, cod);
			int resp=prepS.executeUpdate();
			
			if(resp==1) 
				flag = true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
		return flag;
	}
	
	
}