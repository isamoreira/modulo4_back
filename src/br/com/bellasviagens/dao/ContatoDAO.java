package br.com.bellasviagens.dao;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.bellasviagens.factory.ConnectionFactoryBellasViagens;
import br.com.bellasviagens.model.Contato;






public class ContatoDAO {
	
	public void save (Contato contato) {
		String sql = "INSERT INTO Contatos(Nome, Email, Telefone)" + "VALUES(?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactoryBellasViagens.createConnectionSQLServer();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString (1 , contato.getNome());
			pstm.setString (2 , contato.getEmail());
			pstm.setString (3 , contato.getTelefone());
			pstm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null ) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public void removeById(int id) {
		String sql = "DELETE FROM contatos WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactoryBellasViagens.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			pstm.setInt (1, id);
			pstm.execute();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}


public void update ( Contato contato) {
	
	String sql= " UPDATE contato SET nome = ?, email=?, telefone=?" + "Where id=? ";
	
	Connection conn = null;
	PreparedStatement pstm = null;
try {
		
		conn = ConnectionFactoryBellasViagens.createConnectionSQLServer();
		
		pstm = conn.prepareStatement(sql);
		
		pstm.setString (1 , contato.getNome());
		pstm.setString (2 , contato.getEmail());
		pstm.setString (2 , contato.getTelefone());
		
		
		
		pstm.execute();
		
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (pstm != null ) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


public List<Contato> getContato() {
	
	String sql="SELECT * from contato";
	
	List<Contato> contato = new ArrayList<Contato>();
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;
	
		try {
		
		conn = ConnectionFactoryBellasViagens.createConnectionSQLServer();
		pstm = conn.prepareStatement(sql);
		rset= pstm.executeQuery();
		
		while (rset.next()){
			
			Contato contatos= new Contato();
			
			contatos.setId(rset.getInt("id"));
			contatos.setNome(rset.getString("nome"));
			contatos.setEmail(rset.getString("Email"));
			contatos.setTelefone(rset.getString("telefone"));
			
			contato.add(contatos);
			
			
		}
	

		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (pstm != null ) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		return contato;
}
}
	





