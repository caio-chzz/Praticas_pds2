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

    private GaloDAO galoDAO;

    @Before
    public void setUp() {
        galoDAO = new GaloDAO();
    }

    @After
    public void tearDown() {
        // Limpe a tabela de galos no banco de dados após cada teste
        Conexao c = Conexao.getInstancia();
        Connection cn = c.conectar();

        String query = "DELETE FROM galos";

        try {
            PreparedStatement ps = cn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c.fecharConexao();
        }
    }

    @Test
    public void testInserirGaloComSucesso() {
        // Crie um objeto Galos para inserção
        Galos galo = new Galos();
        galo.setIdGalo(1);
        galo.setRaca("Galo1");
        galo.setPower(100);
        galo.setName("NomeGalo1");
        galo.setLife(100);

        // Tente inserir o objeto no banco de dados
        boolean resultado = galoDAO.inserir(galo);

        // Verifique se a inserção foi bem-sucedida
        assertTrue(resultado);
    }

    @Test
    public void testInserirGaloComFalha() {
        // Crie um objeto Galos com dados inválidos
        Galos galo = new Galos();
        galo.setIdGalo(2); // ID repetido, deve falhar a inserção
        galo.setRaca("Galo2");
        galo.setPower(200);
        galo.setName("NomeGalo2");
        galo.setLife(200);

        // Tente inserir o objeto no banco de dados
        boolean resultado = galoDAO.inserir(galo);

        // Verifique se a inserção falhou (deve retornar falso)
        assertFalse(resultado);
    }

    @Test
    public void testListarGalosComSucesso() {
        // Tente listar os galos do banco de dados
        ArrayList<Galos> galos = galoDAO.listar();

        // Verifique se a lista não está vazia
        assertTrue(galos.size() > 0);
    }

    @Test
    public void testListarGalosVazia() {
        // Limpe a tabela de galos no banco de dados para simular uma lista vazia
        // Isso deve ser feito antes do teste, caso contrário, a lista não estará vazia
        // Certifique-se de que essa ação é reversível após o teste.

        // Tente listar os galos do banco de dados
        ArrayList<Galos> galos = galoDAO.listar();

        // Verifique se a lista está vazia
        assertTrue(galos.isEmpty());
    }

    @Test
    public void testExcluirGaloComSucesso() {
        // Insira um galo no banco de dados para excluir posteriormente
        Galos galo = new Galos();
        galo.setIdGalo(3); // ID do galo a ser excluído
        galo.setRaca("Galo3");
        galo.setPower(300);
        galo.setName("NomeGalo3");
        galo.setLife(300);
        galoDAO.inserir(galo);

        // Tente excluir o galo do banco de dados
        boolean resultado = galoDAO.excluir(galo);

        // Verifique se a exclusão foi bem-sucedida
        assertTrue(resultado);
    }

    @Test
    public void testExcluirGaloInexistente() {
        // Tente excluir um galo que não existe no banco de dados
        Galos galoInexistente = new Galos();
        galoInexistente.setIdGalo(4); // ID de um galo que não existe
        galoInexistente.setRaca("Galo4");
        galoInexistente.setPower(400);
        galoInexistente.setName("NomeGalo4");
        galoInexistente.setLife(400);

        // Tente excluir o galo inexistente do banco de dados
        boolean resultado = galoDAO.excluir(galoInexistente);

        // Verifique se a exclusão falhou (deve retornar falso)
        assertFalse(resultado);
    }

    @Test
    public void testAtualizarGaloComSucesso() {
        // Insira um galo no banco de dados para atualizá-lo posteriormente
        Galos galo = new Galos();
        galo.setIdGalo(5); // ID do galo a ser atualizado
        galo.setRaca("Galo5");
        galo.setPower(500);
        galo.setName("NomeGalo5");
        galo.setLife(500);
        galoDAO.inserir(galo);

        // Atualize os dados do galo
        galo.setRaca("NovaRaca");
        galo.setName("NovoNome");
        galo.setPower(1000);
        galo.setLife(1000);

        // Tente atualizar o galo no banco de dados
        boolean resultado = galoDAO.atualizar(galo);

        // Verifique se a atualização foi bem-sucedida
        assertTrue(resultado);
    }

    @Test
    public void testAtualizarGaloInexistente() {
        // Tente atualizar um galo que não existe no banco de dados
        Galos galoInexistente = new Galos();
        galoInexistente.setIdGalo(6); // ID de um galo que não existe
        galoInexistente.setRaca("Galo6");
        galoInexistente.setPower(600);
        galoInexistente.setName("NomeGalo6");
        galoInexistente.setLife(600);

        // Tente atualizar o galo inexistente no banco de dados
        boolean resultado = galoDAO.atualizar(galoInexistente);

        // Verifique se a atualização falhou (deve retornar falso)
        assertFalse(resultado);
    }

    @Test
    public void testBuscarGaloExistente() {
        // Insira um galo no banco de dados para buscar posteriormente
        Galos galo = new Galos();
        galo.setIdGalo(7); // ID do galo a ser buscado
        galo.setRaca("Galo7");
        galo.setPower(700);
        galo.setName("NomeGalo7");
        galo.setLife(700);
        galoDAO.inserir(galo);

        // Tente buscar o galo pelo ID
        Galos galoEncontrado = galoDAO.buscarPorID(galo);

        // Verifique se o galo foi encontrado e seus dados estão corretos
        assertNotNull(galoEncontrado);
        assertEquals(galo.getIdGalo(), galoEncontrado.getIdGalo());
        assertEquals(galo.getRaca(), galoEncontrado.getRaca());
        assertEquals(galo.getPower(), galoEncontrado.getPower());
        assertEquals(galo.getName(), galoEncontrado.getName());
        assertEquals(galo.getLife(), galoEncontrado.getLife());
    }

    @Test
    public void testBuscarGaloInexistente() {
        // Tente buscar um galo que não existe no banco de dados
        Galos galoInexistente = new Galos();
        galoInexistente.setIdGalo(8); // ID de um galo que não existe
        galoInexistente.setRaca("Galo8");
        galoInexistente.setPower(800);
        galoInexistente.setName("NomeGalo8");
        galoInexistente.setLife(800);

        // Tente buscar o galo inexistente pelo ID
        Galos galoEncontrado = galoDAO.buscarPorID(galoInexistente);

        // Verifique se o galo não foi encontrado (deve ser null)
        assertNull(galoEncontrado);
    }
}
