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
		
		String query = "INSERT INTO `galos` (`id_galo`, `raca_galo`, `poder_de_combate`) VALUES (?, ?, ?)";
				
		
		try {
			PreparedStatement ps = cn.prepareStatement(query);
			ps.setInt(1, x.getIdGalo());
			ps.setString(2,  x.getRaca());
			ps.setInt(1, x.getPower());
			
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return false;
	}
	
	public ArrayList<Galos> listar(){
		
		Conexao c = Conexao.getInstancia();
		
		Connection cn = c.conectar();
		
		ArrayList<Galos> galons = new ArrayList(); 
		
		String query = "SELECT * FROM GALOS";
				
		try {
			PreparedStatement ps = cn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int idGalo = rs.getInt("id_galo");
				
				String raca = rs.getString("raca_galo");
				
				int power = rs.getInt("poder_de_combate");
						
				Galos g = new Galos();
				g.setIdGalo(idGalo);
				g.setRaca(raca);
				g.setPower(power);
				
				galons.add(g);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		c.fecharConexao()
		
		return galons;
	}
	
	
	
	
	
	
	
	
	
}