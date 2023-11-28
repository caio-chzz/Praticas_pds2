package control;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt; // Certifique-se de importar a classe BCrypt

import modelo.Galos;

public class GaloDAO {

    public boolean inserir(Galos x) {
        Conexao c = Conexao.getInstancia();
        Connection cn = c.conectar();

        String query = "INSERT INTO galos (id_galo, raca_galo, poder_de_combate, names, life, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.setInt(1, x.getIdGalo());
            ps.setString(2, x.getRaca());
            ps.setInt(3, x.getPower());
            ps.setString(4, x.getName());
            ps.setInt(5, x.getLife());
            
            // Use BCrypt para criptografar a senha antes de armazenar no banco de dados
            String senhaCriptografada = BCrypt.hashpw(x.getSenha(), BCrypt.gensalt());
            ps.setString(6, senhaCriptografada);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } //finally {
            //c.fecharConexao();
        //}

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
                String name = rs.getString("names");
                int power = rs.getInt("poder_de_combate");
                int life = rs.getInt("life");
                String senha = rs.getString("senha"); 
                Galos g = new Galos();
                g.setIdGalo(idGalo);
                g.setRaca(raca);
                g.setName(name);
                g.setPower(power);
                g.setLife(life);
                g.setSenha(senha); 
                galons.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } //finally {
            //c.fecharConexao();
        //}

        //c.fecharConexao();

        return galons;
    }

    public boolean excluir(Galos g) {
        Conexao c = Conexao.getInstancia();
        Connection con = c.conectar();
        
        String query = "DELETE FROM galos WHERE id_galo = ? AND senha = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, g.getIdGalo());
            ps.setString(2, g.getSenha()); 
            ps.executeUpdate();
            
            //c.fecharConexao();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } //finally {
            //c.fecharConexao();
        //}
        
        return false;
    }

    public boolean atualizar(Galos g) {
        Conexao c = Conexao.getInstancia();
        Connection con = c.conectar();
        
        String query = "UPDATE galos SET raca_galo = ?, names = ?, poder_de_combate = ?, life = ?, senha = ? WHERE id_galo = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, g.getRaca());
            ps.setString(2, g.getName());
            ps.setInt(3, g.getPower());
            ps.setInt(4, g.getLife());
            
            // Use BCrypt para criptografar a nova senha antes de atualizar no banco de dados
            String senhaCriptografada = BCrypt.hashpw(g.getSenha(), BCrypt.gensalt());
            ps.setString(5, senhaCriptografada);
            
            ps.setInt(6, g.getIdGalo());
            ps.executeUpdate();
            
           //c.fecharConexao();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); 
        } //finally {
            //c.fecharConexao();
        //}
        
        return false;
    }
    public Galos buscarPorID(Galos g) {
        Conexao c = Conexao.getInstancia();
        Connection con = c.conectar();

        String query = "SELECT id_galo, raca_galo, poder_de_combate, names, life, senha FROM galos WHERE id_galo = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, g.getIdGalo());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Galos galo = new Galos();
                galo.setIdGalo(rs.getInt("id_galo"));
                galo.setRaca(rs.getString("raca_galo"));
                galo.setName(rs.getString("names"));
                galo.setPower(rs.getInt("poder_de_combate"));
                galo.setLife(rs.getInt("life"));
                galo.setSenha(rs.getString("senha")); 

                //c.fecharConexao();
                return galo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } //finally {
            //c.fecharConexao();
        //}

        return null;
    }

    public void excloitudo() {
        Conexao c = Conexao.getInstancia();
        Connection con = c.conectar();

        String query = "DELETE FROM galos";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();

            //c.fecharConexao();

        } catch (SQLException e) {
            e.printStackTrace();
        }//finally {
            //c.fecharConexao();
        //}
    }

}
