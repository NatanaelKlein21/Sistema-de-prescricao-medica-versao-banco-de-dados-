package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Paciente;


public class DAOLista {
	
	public DAOLista() 
	{
		
		
	}
	
	public boolean consultaAlta(String c) { /*----------------------------- Função que confere se algum paciente esta na tabela de Alta ---------------------------*/
		
		PreparedStatement prepS = null;
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
	
	
	
	public Paciente[] completaLista(){/*-------------------------------Função que retorna o array de pacientes que seram exibidos na lista de internados--------------------------------*/
		
		ResultSet res;
		Paciente[] lista = new Paciente[10];
		
		int i=0;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		PreparedStatement prepS = null;
		
		String sql= "Select * from Paciente";
		
		
		
		try {
				prepS = con.prepareStatement(sql);
				res = prepS.executeQuery();
				
				while(res.next()) {
					
						Paciente p= new Paciente();
						p.setCpf(res.getString(1));
						if(consultaAlta(p.getCpf())) {// chamando a função para verificar se o cpf esta na tabela alta e se estiver, não faz nenhuma ação
							
						}else {// se o cpf não estiver na tabela alta os dados do paciente são armazenados no array lista para serem retornados
							p.setNome(res.getString(2));
							p.setUnidade(res.getString(3));
							p.setDatanasc(res.getString(4));
							p.setGlut(res.getString(5));
							p.setFrut(res.getString(6));
							p.setDipi(res.getString(7));
							p.setPeni(res.getString(8));
							lista[i]=p;
							i++;//incremento da variavel i para alterar o index do array.
						}
						
				}
			
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConexaoMySQL.fecharConexao();
		}
		
		ConexaoMySQL.fecharConexao();
		return lista;// retorna lista
		
	}
	
}
