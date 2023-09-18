package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Galos;

public class galosDAO {
	
	public boolean inserir(Galos x) {
	    Conexao c = Conexao.getInstancia();
	    Connection cn = c.conectar();
	    
	    String query = "INSERT INTO `galos` (`name`, `raca_galo`, `poder_de_combate`, `life`) VALUES (?, ?, ?, ?)";
	    
	    try {
	        PreparedStatement ps = cn.prepareStatement(query);
	        ps.setString(1, x.getName());
	        ps.setString(2, x.getRaca());
	        ps.setInt(3, x.getPower());
	        ps.setInt(4, x.getLife());
	        
	        ps.executeUpdate();
	        
	        c.fecharConexao();
	        
	        return true;
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }  finally {
	        c.fecharConexao();
	    }
	     
	    return false;
	}

	public ArrayList<Galos> listar() {
	    Conexao c = Conexao.getInstancia();
	    Connection cn = c.conectar();
	    
	    ArrayList<Galos> galons = new ArrayList<>(); 
	    
	    String query = "SELECT * FROM galos";
	    
	    try {
	        PreparedStatement ps = cn.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int idGalo = rs.getInt("id_galo");
	            String raca = rs.getString("raca_galo");
	            String name = rs.getString("name");
	            int power = rs.getInt("poder_de_combate");
	            int life = rs.getInt("life");
	                    
	            Galos g = new Galos();
	            g.setIdGalo(idGalo);
	            g.setRaca(raca);
	            g.setName(name);
	            g.setPower(power);
	            g.setLife(life);
	            
	            galons.add(g);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        c.fecharConexao();
	    }
	    
	    c.fecharConexao();
	    
	    return galons;
	}

	public boolean excluir (Galos g) {
	    Conexao c = Conexao.getInstancia();
	    Connection con = c.conectar();
	    
	    String query = "DELETE FROM galos WHERE id_galo = ?";
	    
	    try {
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, g.getIdGalo());
	        ps.executeUpdate();
	        
	        c.fecharConexao();
	        
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        c.fecharConexao();
	    }
	    
	    return false;
	}

	public boolean atualizar(Galos g) {
	    Conexao c = Conexao.getInstancia();
	    Connection con = c.conectar();
	    
	    String query = "UPDATE galos SET raca_galo = ?, name = ?, poder_de_combate = ?, life = ? WHERE id_galo = ?";
	    
	    try {
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setString(1, g.getRaca());
	        ps.setString(2, g.getName());
	        ps.setInt(3, g.getPower());
	        ps.setInt(4, g.getLife());
	        ps.setInt(5, g.getIdGalo());
	        ps.executeUpdate();
	        
	        c.fecharConexao();
	        
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        c.fecharConexao();
	    }
	    
	    return false;
	}

