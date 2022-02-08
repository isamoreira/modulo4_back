package br.com.bellasviagens.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.bellasviagens.factory.ConnectionFactoryBellasViagens;
import br.com.bellasviagens.model.Pacotes;





public class PacotesDAO {
	
	public void save (Pacotes pacotes) {
		String sql = "INSERT INTO Pacotes(Destino, Valor, Datadeviagem, FK_Contato)" + "VALUES(?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactoryBellasViagens.createConnectionSQLServer();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString (1 , pacotes.getDestinoe());
			pstm.setInt (2 , pacotes.getValor());
			pstm.setDate (3 , new Date(pacotes.getDatadeviagem().getTime()));
			pstm.setInt (4 , pacotes.getFk_contato());
			
			
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

	public void update ( Pacotes pacotes) {
		
		String sql= " UPDATE pacotes SET destino = ?, valor=?, datadeviagem=?, fk_contato=" + "Where id=? ";
		
		Connection conn = null;
		PreparedStatement pstm = null;
try {
			
			conn = ConnectionFactoryBellasViagens.createConnectionSQLServer();
			
			pstm = conn.prepareStatement(sql);
			
			pstm.setString (1 , pacotes.getDestinoe());
			pstm.setInt (2 , pacotes.getValor());
			pstm.setDate (3 , new Date(pacotes.getDatadeviagem().getTime()));
			pstm.setInt (4 , pacotes.getFk_contato());
			
			
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

	public List<Pacotes> getPacotes() {
		
		String sql="SELECT * from pacotes";
		
		List<Pacotes> pacotes = new ArrayList<Pacotes>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
			try {
			
			conn = ConnectionFactoryBellasViagens.createConnectionSQLServer();
			pstm = conn.prepareStatement(sql);
			rset= pstm.executeQuery();
			
			while (rset.next()){
				
				Pacotes pacotess = new Pacotes();
				
				pacotess.setId(rset.getInt("id"));
				pacotess.setValor(rset.getInt("valor"));
				pacotess.setDestinoe(rset.getString("destino"));
				pacotess.setDatadeviagem(rset.getDate("datadeviagem"));
				pacotess.setFk_contato(rset.getInt("fk_contato"));
				
				pacotes.add(pacotess);
				
				
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
			return pacotes;
		
		
	}
}

