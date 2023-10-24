package test;

import org.junit.Test;

import control.GaloDAO;
import modelo.Galos;

import static org.junit.Assert.assertTrue;

public class GaloDaoTest {

    @Test
    public void testegaloinsere() {
         
        GaloDAO galoDAO = new GaloDAO();

        
        Galos novoGalo = new Galos();
        novoGalo.setIdGalo(1);
        novoGalo.setRaca("GaloTeste");
        novoGalo.setPower(100);
        novoGalo.setName("GaloTesteNome");
        novoGalo.setLife(50);

     
        boolean insercaoSucesso = galoDAO.inserir(novoGalo);

      
        assertTrue(insercaoSucesso);
        assertTrue(!insercaoSucesso);
          
    }

  /*   @Test
    public void testegalolista() {

    }

    @Test
    public void testegaloatualiza() {

    }

    @Test
    public void testegaloexclui() {

    }

    @Test
    public void testegaloprocura() {

    }
*/
}
