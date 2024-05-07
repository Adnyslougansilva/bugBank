package scenarios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.CadastroPage;
import java.time.Duration;

public class CadastroTest {

    WebDriver driver;
    CadastroPage cadastroPage;

    @Before
    public void before() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        cadastroPage = new CadastroPage(driver);
        driver.get("http://localhost:3000/");
    }

    @Test
    public void testeCadastro() {
        cadastroPage.clicarPorXpath(cadastroPage.btnRegistrar);
        cadastroPage.preencherValorPorXpath(cadastroPage.campoEmail, "adnyslougan@gmail.com");
        cadastroPage.preencherValorPorXpath(cadastroPage.campoNome, "Adnys Lougan");
        cadastroPage.preencherValorPorXpath(cadastroPage.campoSenha, "QAACADEMY");
        cadastroPage.preencherValorPorXpath(cadastroPage.campoConfirmacaoSenha, "QAACADEMY");
        cadastroPage.clicarPorXpath(cadastroPage.campoContaComSaldoToogle);
        cadastroPage.clicarPorXpath(cadastroPage.btnCadastrar);
        cadastroPage.validarSeContaFoiCriadaComSucesso();
    }

    @After
    public void after() {
        driver.quit();
    }
}