package sistemadetestes.pageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProdutoPO extends BasePO {

	@FindBy(id = "btn-adicionar")
	public WebElement buttonCriar;

	@FindBy(id = "codigo")
	public WebElement inputCodigo;

	@FindBy(id = "nome")
	public WebElement inputNome;

	@FindBy(id = "quantidade")
	public WebElement inputQuantidade;

	@FindBy(id = "valor")
	public WebElement inputValor;

	@FindBy(id = "data")
	public WebElement inputData;

	@FindBy(id = "btn-salvar")
	public WebElement buttonSalvar;

	@FindBy(id = "btn-sair")
	public WebElement buttonSair;

	@FindBy(xpath = "/html/body/div/div[2]/table/tbody/tr/td[6]/button[1]")
	public WebElement buttonEditar;
	
	@FindBy(xpath = "/html/body/div/div[2]/table/tbody/tr/td[6]/button[2]")
	public WebElement buttonDeletar;
	
	@FindBy(css = "div.modal-body>div.alert>span")
	public WebElement spanMensagem;

	@FindBy(css = "table.table-hover")
	public WebElement tabela;

	@FindBy(css = "table.table-hover>tbody>tr>td")
	public WebElement codigo;
	
	@FindBy(xpath = "/html/body/div/div[2]/table/tbody/tr/td[4]")
	public WebElement valor;

	@FindBy(id = "cadastro-produto")
	public WebElement modalEditar;
	
	@FindBy(id = "cadastro-produto")
	public WebElement modalCadastrar;

	@FindBy(xpath = "//*[@id=\"cadastro-produto\"]/div/div/div[1]/button")
	public WebElement modalBotaoX;
	
	@FindBy(xpath = "//*[@id=\"collapsibleNavbar\"]/ul/li/a")
	public WebElement botaoVoltar;
	
	public ProdutoPO(WebDriver driver) {
		super(driver);
	}

	public void escrever(WebElement input, String texto) {
		input.clear();
		input.sendKeys(texto + Keys.TAB);
	}

	public String obterMensagem() {
		return this.spanMensagem.getText();
	}

	public String obterCodigo() {
		return this.codigo.getText();
	}
	
	public String obterValor() {
		return this.valor.getText();
	}
	
	public void executarAcaoDeCriarProduto(String codigo, String nome, String quantidade, String valor, String data) {
		buttonCriar.click();
		buttonCriar.click();
		
		escrever(inputCodigo, codigo);
		escrever(inputNome, nome);
		escrever(inputQuantidade, quantidade);
		escrever(inputValor, valor);
		escrever(inputData, data);

		buttonSalvar.click();
	}
}
