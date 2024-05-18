package scenarios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.CadastroPage;
import page.HomePage;
import page.LoginPage;

import java.time.Duration;

public class TesteSaldoInicial {
    WebDriver driver;
    LoginPage loginPage;
    CadastroPage cadastroPage;
    HomePage homePage;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        loginPage = new LoginPage(driver);
        cadastroPage = new CadastroPage(driver);
        homePage = new HomePage(driver);

        driver.get("http://localhost:3000/#");

    }

    @Test
    public void testeSaldoInicial() {

        cadastroPage.clicarPorXpath(cadastroPage.btnRegistrar);
        cadastroPage.preencherValorPorXpath(cadastroPage.campoEmail, "adnyslougan@gmail.com");
        cadastroPage.preencherValorPorXpath(cadastroPage.campoNome, "Adnys");
        cadastroPage.preencherValorPorXpath(cadastroPage.campoSenha, "teste123");
        cadastroPage.preencherValorPorXpath(cadastroPage.campoConfirmacaoSenha, "teste123");
        cadastroPage.clicarPorXpath(cadastroPage.campoContaComSaldoToogle);
        cadastroPage.clicarPorXpath(cadastroPage.btnCadastrar);
        cadastroPage.validarSeContaFoiCriadaComSucesso();
        cadastroPage.clicarPorXpath(cadastroPage.btnFechar);

        loginPage.preencherCampo(loginPage.campoEmail, "adnyslougan@gmail.com");
        loginPage.preencherCampo(loginPage.campoSenha, "teste123");
        loginPage.clicarPorXpath(loginPage.btnAcessar);
        loginPage.validarLogin();

        homePage.validarSaldo("Saldo em conta R$ 1.000,00");

    }

    @After
    public void after() {
        driver.quit();
    }
}