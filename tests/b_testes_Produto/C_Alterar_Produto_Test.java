package b_testes_Produto;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import persistence.ProdutoDAO;
import transference.Produto;
import aspects.Exceptions.DAOException;

public class C_Alterar_Produto_Test {

	@Test
	public void deveAlterarProduto()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {

		Produto p = new ProdutoDAO().pesquisar(1);
		p.setDescricao("ProdutoAlterado");
		new ProdutoDAO().alterar(p);
		Produto alterada = new ProdutoDAO().pesquisar(1);
		assertEquals("ProdutoAlterado", alterada.getDescricao());
	}

	@Test
	public void deveReotrnarErroPorCausaDaCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {

		Produto p = new ProdutoDAO().pesquisar(2);
		assertNotNull(p);
		p.setDescricao("ProdutoAlterado");
		p.setIdCategoria(-1);

		try {
			new ProdutoDAO().alterar(p);
			fail();
		} catch (DAOException expected) {
			assertEquals(DAOException.class, expected.getClass());
		}
	}

}
