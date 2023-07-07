package sistemadetestes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

/**
 * Classe de teste criada para verificar coberturas sobre a página de produtos
 * 
 * @author Diego Morais dos Santos Terra
 * @date 05/07/2023
 * 
 */

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;

import sistemadetestes.pageObject.ProdutoPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest extends BaseTest {

	private static ProdutoPO produtoPage;

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void prepararTestes() {
		produtoPage = new ProdutoPO(driver);
	}

	@Test
	public void TC001_naoDeveCriarProdutosComCamposEmBranco() {

		produtoPage.executarAcaoDeCriarProduto("", "", "", "", "");

		String mensagem = produtoPage.obterMensagem();

		// Verificação e análise - assert
		assertEquals(mensagem, "Todos os campos são obrigatórios para o cadastro!");
	}

	@Test
	public void TC002_criacaoDeProdutosComSucesso() {

		produtoPage.executarAcaoDeCriarProduto("001", "Mouse", "10", "100", "2023-07-04");
		produtoPage.buttonSair.click();

		String codigo = produtoPage.obterCodigo();

		assertEquals("001", codigo);
	}

	@Test
	public void TC003_naoDeveCriarProdutosComCampoCodigoEmBranco() {

		produtoPage.executarAcaoDeCriarProduto("", "Mouse", "10", "100", "2023-07-04");

		String mensagem = produtoPage.obterMensagem();

		assertEquals(mensagem, "Todos os campos são obrigatórios para o cadastro!");
	}

	@Test
	public void TC004_deveFecharTelaCadastroAoClicarSair() {
		produtoPage.buttonCriar.click();
		produtoPage.buttonCriar.click();
		
		produtoPage.buttonSair.click();
		produtoPage.buttonSair.click();

		assertFalse(produtoPage.modalCadastrar.isDisplayed());
	}
	
	//Deve sair da mensagem de erro ao clicar no ícone X
	@Test
	public void TC005_deveFecharTelaCadastroAoClicarSair() {
		produtoPage.buttonCriar.click();
		produtoPage.buttonCriar.click();
		
		produtoPage.modalBotaoX.click();

		assertFalse(produtoPage.modalCadastrar.isDisplayed());
	}
	
	@Test
	public void TC006_deveExibirMensagemDeErroPorValorInvalido() {
		produtoPage.executarAcaoDeCriarProduto("1", "Macarrão", "50", "valor-invalido", "07-07-2023");

		String mensagem = produtoPage.obterMensagem();

		assertEquals(mensagem, "Valor inválido para o preço do produto!");
	}

	//	O campo valor deve ser valor flutuante positivo
	@Test
	public void TC007_deveCadastrarValorComoFlutuantePositivo() {
		String valor = "3.30";

		produtoPage.executarAcaoDeCriarProduto("1", "Macarrão", "50", valor, "07-07-2023");
		
		assertEquals(valor, produtoPage.obterValor());
	}
	
	//	Ao clicar no botão voltar a página deveria retornar a tela de login
	@Test
	public void TC008_deveRetornarATelaDeLogin() {
		produtoPage.botaoVoltar.click();
		
		String[] caminho = this.driver.getCurrentUrl().split("/");
		String pagina = caminho[caminho.length-1];
		
		assertEquals("login.html", pagina);
	}
	
	//	Ao clicar em editar um item na lista deveria abrir modal de edição
	@Test
	public void TC009_deveAbrirTelaEdicaoAoClicarEmEditar() {
		produtoPage.executarAcaoDeCriarProduto("001", "Mouse", "10", "100", "2023-07-04");
		produtoPage.buttonSair.click();

		String codigo = produtoPage.obterCodigo();

		assertEquals("001", codigo);
		
		produtoPage.buttonEditar.click();

		assertTrue(produtoPage.modalEditar.isDisplayed());
	}
	
	//	Ao clicar em excluir um item na lista deveria exibir uma mensagem de confirmação
	@Test(expected = Exception.class)
	public void TC0010_deveDeleterProdutoNaLista() {
		produtoPage.executarAcaoDeCriarProduto("001", "Mouse", "10", "100", "2023-07-04");
		produtoPage.buttonSair.click();

		String codigo = produtoPage.obterCodigo();

		assertEquals("001", codigo);
		
		produtoPage.buttonDeletar.click();
		produtoPage.obterCodigo();
		
		thrown.expect(org.openqa.selenium.NoSuchElementException.class);
	}
}
