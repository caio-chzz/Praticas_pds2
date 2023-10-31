package controle;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import control.Conexao;
import control.GaloDAO;
import modelo.Galos;

public class GaloDAOTest {
 //arrumar essa jossa
    private GaloDAO galoDAO;

    @Before
    public void setUp() {
        galoDAO = new GaloDAO();
    }

    @After
    public void tearDown() {
        
    }

    @Test
    public void testInserirGaloComSucesso() {
        
        Galos galo = new Galos();
        galo.setIdGalo(1);
        galo.setRaca("Galo1");
        galo.setPower(100);
        galo.setName("NomeGalo1");
        galo.setLife(100);

        
        boolean resultado = galoDAO.inserir(galo);

        
        assertTrue(resultado);
    }

    @Test
    public void testInserirGaloComFalha() {
        
        Galos galo = new Galos();
        galo.setIdGalo(2); 
        galo.setRaca("Galo2");
        galo.setPower(200);
        galo.setName("NomeGalo2");
        galo.setLife(200);

        
        boolean resultado = galoDAO.inserir(galo);
         //arrumar essa jossa
       
        assertFalse(resultado);
    }

    @Test
    public void testListarGalosComSucesso() {
       
        ArrayList<Galos> galos = galoDAO.listar();

        
        assertTrue(galos.size() > 0);
    }

    @Test
    public void testListarGalosVazia() {
     
        galoDAO.excloitudo();

        ArrayList<Galos> galos = galoDAO.listar();

         //arrumar essa jossa
        assertTrue(galos.isEmpty());
    }

    @Test
    public void testExcluirGaloComSucesso() {
        
        Galos galo = new Galos();
        galo.setIdGalo(3); 
        galo.setRaca("Galo3");
        galo.setPower(300);
        galo.setName("NomeGalo3");
        galo.setLife(300);
        galoDAO.inserir(galo);

       
        boolean resultado = galoDAO.excluir(galo);

        assertTrue(resultado);
    }

    @Test
    public void testExcluirGaloInexistente() {
       
        Galos galoInexistente = new Galos();
        galoInexistente.setIdGalo(4); 
        galoInexistente.setRaca("Galo4");
        galoInexistente.setPower(400);
        galoInexistente.setName("NomeGalo4");
        galoInexistente.setLife(400);

        
        boolean resultado = galoDAO.excluir(galoInexistente);
         //arrumar essa jossa
      
        assertFalse(resultado);
    }

    @Test
    public void testAtualizarGaloComSucesso() {
     
        Galos galo = new Galos();
        galo.setIdGalo(5); 
        galo.setRaca("Galo5");
        galo.setPower(500);
        galo.setName("NomeGalo5");
        galo.setLife(500);
        galoDAO.inserir(galo);

      
        galo.setRaca("NovaRaca");
        galo.setName("NovoNome");
        galo.setPower(1000);
        galo.setLife(1000);

        boolean resultado = galoDAO.atualizar(galo);

      
        assertTrue(resultado);
    }

    @Test
    public void testAtualizarGaloInexistente() {
        
        Galos galoInexistente = new Galos();
        galoInexistente.setIdGalo(6);
        galoInexistente.setRaca("Galo6");
        galoInexistente.setPower(600);
        galoInexistente.setName("NomeGalo6");
        galoInexistente.setLife(600);

        boolean resultado = galoDAO.atualizar(galoInexistente);
        //arrumar essa jossa
        assertFalse(resultado);
    }

    @Test
    public void testBuscarGaloExistente() {
        Galos galo = new Galos();
        galo.setIdGalo(7); 
        galo.setRaca("Galo7");
        galo.setPower(700);
        galo.setName("NomeGalo7");
        galo.setLife(700);
        galoDAO.inserir(galo);

        Galos galoEncontrado = galoDAO.buscarPorID(galo);

        assertNotNull(galoEncontrado);
        assertEquals(galo.getIdGalo(), galoEncontrado.getIdGalo());
        assertEquals(galo.getRaca(), galoEncontrado.getRaca());
        assertEquals(galo.getPower(), galoEncontrado.getPower());
        assertEquals(galo.getName(), galoEncontrado.getName());
        assertEquals(galo.getLife(), galoEncontrado.getLife());
    }

    @Test
    public void testBuscarGaloInexistente() {
        Galos galoInexistente = new Galos();
        galoInexistente.setIdGalo(8); 
        galoInexistente.setRaca("Galo8");
        galoInexistente.setPower(800);
        galoInexistente.setName("NomeGalo8");
        galoInexistente.setLife(800);

        Galos galoEncontrado = galoDAO.buscarPorID(galoInexistente);

        assertNull(galoEncontrado);
    }
}
