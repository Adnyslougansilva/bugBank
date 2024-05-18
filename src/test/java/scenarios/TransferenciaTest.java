package scenarios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.CadastroPage;
import page.HomePage;
import page.LoginPage;
import page.TransferenciaPage;

import java.time.Duration;

public class TransferenciaTest {

    WebDriver driver;
    LoginPage loginPage;
    CadastroPage cadastroPage;
    TransferenciaPage transferenciaPage;
    HomePage homePage;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        cadastroPage = new CadastroPage(driver);
        transferenciaPage = new TransferenciaPage(driver);
        homePage = new HomePage(driver);
        driver.get("http://localhost:3000/");
    }

    @Test
    public void testeTransferenciaComSucesso() {
        cadastroPage.cadastrarNovaConta("selena@gmail.com.br", "selena", "teste123");
        String conta1 = cadastroPage.conta;
        String digito1 = cadastroPage.digito;
        cadastroPage.cadastrarNovaContaSemSaldo("lyra@gmail.com.br", "lyra", "teste123");
        String conta2 = cadastroPage.conta;
        String digito2 = cadastroPage.digito;
        System.out.println(conta2 + digito2);

        loginPage.fazerLogin("selena@gmail.com.br", "teste123");
        homePage.clicarPorXpath(homePage.btnTransferencia);
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoNumeroDaConta, conta2);
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoDigitoConta, digito2);
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoValor, "500.00");
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoDescricao, "Automacao");
        transferenciaPage.clicarPorXpath(transferenciaPage.btnTransferir);
        transferenciaPage.validarTransferenciaSucesso();
    }

    @Test
    public void testeTransferenciaComErro() {
        cadastroPage.cadastrarNovaConta("caroline@gmail.com.br", "caroline", "teste123");
        String conta1 = cadastroPage.conta;
        String digito1 = cadastroPage.digito;
        cadastroPage.cadastrarNovaContaSemSaldo("fatima@gmail.com.br", "fatima", "teste123");
        String conta2 = cadastroPage.conta;
        String digito2 = cadastroPage.digito;
        System.out.println(conta2 + digito2);

        loginPage.fazerLogin("caroline@gmail.com.br", "teste123");
        homePage.clicarPorXpath(homePage.btnTransferencia);
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoNumeroDaConta, conta2);
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoDigitoConta, digito2);
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoValor, "1000.01");
        transferenciaPage.preencherValorPorXpath(transferenciaPage.campoDescricao, "Teste exercicio 04");
        transferenciaPage.clicarPorXpath(transferenciaPage.btnTransferir);
        transferenciaPage.validarMensagemErroNaTransferencia();

    }


    @After
    public void after(){
        driver.quit();
    }
}