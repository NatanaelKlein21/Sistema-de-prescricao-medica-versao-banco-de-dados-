package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedWriter;
import modelo.Medicamento;

public class DAOMedicamento {
	
	public DAOMedicamento(){
		

		
	}
	
	public boolean cadastraMedicamento(Medicamento m) {// Função que cadastra o medicamento na Tabela medicamentos
		
		boolean flag=false;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		PreparedStatement prepS = null;
		
		String sql= "insert into Medicamentos values(?,?,?,?,?,?)";
		
		try {
			prepS = con.prepareStatement(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		
		
		try {
			
			prepS.setString(1, m.getCodigo());
			prepS.setString(2, m.getNomeMED());
			prepS.setString(3, m.getGlut());
			prepS.setString(4, m.getFrut());
			prepS.setString(5, m.getDipi());
			prepS.setString(6, m.getPeni());
			
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
	
	public boolean consultaCod(String c) {// Função que verifica se um medicamento existe na Tabela medicamentos
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
	public boolean consultaPresc(String c) { // Função que verifica se um codigo de medicamento existe na Tabela Prescrição
		PreparedStatement prepS = null;
		String codigo = null;
		ResultSet res;
		String sql;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();

		sql = "Select * from Prescricao where codigo like ?";

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
	
	
	public boolean removeMed(String c) {// Função que remove um medicamento na tabela medicamentos, recebendo apenas seu codigo correspondente
		
		boolean flag=false;
		int res;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		PreparedStatement prepS = null;
		
		String sql= "Delete from Medicamentos where codigo like ?";
		
		if(consultaPresc(c)) {// verifica se o medicamento esta na tabela prescrição, para que este não seja removido caso esteja.
			System.out.println("O medicamento não pode ser deletado, pois esta em uma prescrição");	
		}else{
			
			try {
				prepS = con.prepareStatement(sql);
				prepS.setString(1, c);
				res=prepS.executeUpdate();
				
				if(res==1)
					flag=true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ConexaoMySQL.fecharConexao();
			}
			
		}	
		ConexaoMySQL.fecharConexao();	
		return flag;	
		
		
	}
	
	
	public boolean atualizaMed(Medicamento m) {// Função que atualiza os valores de medicamentos na tabela medicamentos atravez do seu codigo
		
		boolean flag=false;
		int res;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		PreparedStatement prepS = null;
		
		String sql= "Update Medicamentos set mednome=?, mgluten=?, mfrutosdomar=?, mdipirona=?, mpenicilina=? where codigo like ?";
		
		if(consultaPresc(m.getCodigo())) {// verifica se o medicamento esta na tabela prescrição, para que este não seja atualizado caso esteja.
			System.out.println("O medicamento não pode ser atualizado, pois esta em uma prescrição");	
		}else{
			
			try {
				prepS = con.prepareStatement(sql);
				prepS.setString(1, m.getNomeMED());
				prepS.setString(2, m.getGlut());
				prepS.setString(3, m.getFrut());
				prepS.setString(4, m.getDipi());
				prepS.setString(5, m.getPeni());
				prepS.setString(6, m.getCodigo());
				res=prepS.executeUpdate();
				
				if(res==1)
					flag=true;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ConexaoMySQL.fecharConexao();
			}
			
		}	
		ConexaoMySQL.fecharConexao();	
		return flag;	
		
		
	}
	
	public Medicamento consultaMed(String c) {
		
		Medicamento m= new Medicamento();
		
		PreparedStatement prepS = null;
		ResultSet res;
		
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		String sql= "Select* from Medicamentos Where codigo like ?";
		m.setCodigo(c);
		
		try {
			prepS=con.prepareStatement(sql);
			prepS.setString(1, c);
			
			res = prepS.executeQuery();
			
			while(res.next()) {
				m.setNomeMED(res.getString(2));
				m.setGlut(res.getString(3));
				m.setFrut(res.getString(4));
				m.setDipi(res.getString(5));
				m.setPeni(res.getString(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
		
}
	
	
	
	


