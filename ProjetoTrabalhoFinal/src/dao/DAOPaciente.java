package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Paciente;
import visao.JanelaPrincipal;

public class DAOPaciente {
	
	
	public DAOPaciente() {
		
	}
	
	
	public boolean cadastraPaciente(Paciente p)  {// função que cadastra um paciente no banco de dados
		
		boolean flag=false;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		PreparedStatement prepS = null;
		
		String sql= "insert into Paciente values(?,?,?,?,?,?,?,?)";
		
		try {
			prepS = con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		
		try {
			prepS.setString(1, p.getCpf());
			prepS.setString(2, p.getNome());
			prepS.setString(3, p.getUnidade());
			prepS.setString(4, p.getDatanasc());
			prepS.setString(5, p.getGlut());
			prepS.setString(6, p.getFrut());
			prepS.setString(7, p.getDipi());
			prepS.setString(8, p.getPeni());
			
		int resp=prepS.executeUpdate();
		
		if(resp==1)
			flag=true;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		finally
		{
			ConexaoMySQL.fecharConexao();
		}
		
		return flag;
		
	}
	
	public boolean consultaCPF(String c) {// Função que verifica a existencia de um cpf na tabela paciente
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
	
	public boolean consultaAlta(String c) {// função que verifica se um determinado cpf ja existe na tabela Alta
		
		PreparedStatement prepS = null;
		String cpf = null;
		ResultSet res;
		String sql;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();

		sql = "Select * from Alta where cpf like ?";

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
	
	public boolean cadastraAlta(Paciente p) {// função que cadastra uma Alta 
		
		boolean flag = false;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		PreparedStatement prepS = null;
		
		String sql= "insert into Alta values(?,?)";
		
		try {
			prepS= con.prepareStatement(sql);
			prepS.setString(1, p.getCpf());
			prepS.setString(2, p.getMotivoAlta());
			int resp=prepS.executeUpdate();
			
			if(resp==1)
				flag=true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			ConexaoMySQL.fecharConexao();
		}
		
		
		
		return flag;
	}
	
	
	
	

}
				

		

	

