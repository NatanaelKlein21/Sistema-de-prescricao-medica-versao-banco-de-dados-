package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Usuario;

public class DAOLogin {
	
	public DAOLogin()
	{
		
	}
	
	public boolean autenticar(Usuario u)
	//Funcao que verifica o usuario no banco de dados e retorna true se for encontrado
	{
		PreparedStatement prepS;
		ResultSet res;
		Connection con= ConexaoMySQL.abrirConexaoMySQL();
		
		String sql="select * from usuario where usuario like ? and senha like ?";
		
		try {
			prepS=con.prepareStatement(sql);
			prepS.setString(1, u.getUsuario());
			prepS.setString(2, u.getSenha());
			
			res=prepS.executeQuery();
			
			while(res.next())
			{
				return true;
			}
			
			return false;
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ConexaoMySQL.fecharConexao();
			return false;
		}
		finally {
			ConexaoMySQL.fecharConexao();
		}
		
		
		
		
	}
	
}
