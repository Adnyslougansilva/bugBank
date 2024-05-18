package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CadastroPage {

    WebDriver driver;
    public String conta;
    public String digito;


    public CadastroPage(WebDriver driver) {
        this.driver = driver;
    }

    public String btnRegistrar = "//button[contains(text(),'Registrar')]";
    public String campoEmail = "//body/div[@id='__next']/div[1]/div[2]/div[1]/div[2]/form[1]/div[2]/input[1]";

    public String campoNome = "//body/div[@id='__next']/div[1]/div[2]/div[1]/div[2]/form[1]/div[3]/input[1]";

    public String campoSenha = "//body/div[@id='__next']/div[1]/div[2]/div[1]/div[2]/form[1]/div[4]/div[1]/input[1]";

    public String campoConfirmacaoSenha = "//body/div[@id='__next']/div[1]/div[2]/div[1]/div[2]/form[1]/div[5]/div[1]/input[1]";

    public String campoContaComSaldoToogle = "//label[@id='toggleAddBalance']/..";

    public String btnCadastrar = "//button[contains(text(),'Cadastrar')]";

    public String btnFechar = "//a[@id='btnCloseModal']";

    public String mensagemContaCriada = "//p[@id='modalText']";

    public void preencherValorPorXpath(String elemento, String valor) {
        driver.findElement(By.xpath(elemento)).clear();
        driver.findElement(By.xpath(elemento)).sendKeys(valor);
    }

    public void clicarPorXpath(String elemento) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(By.xpath(elemento)).isDisplayed());
        driver.findElement(By.xpath(elemento)).click();
    }

    public void validarSeContaFoiCriadaComSucesso(){
        Assert.assertTrue(driver.getPageSource().contains("foi criada com sucesso"));
    }

    public void clicarToogleSaldo(){
        driver.findElement(By.xpath(campoContaComSaldoToogle)).click();
    }

    public void cadastrarNovaConta(String email, String nome, String senha) {
        clicarPorXpath(btnRegistrar);
        preencherValorPorXpath(campoEmail, email);
        preencherValorPorXpath(campoNome, nome);
        preencherValorPorXpath(campoSenha, senha);
        preencherValorPorXpath(campoConfirmacaoSenha, senha);
        clicarPorXpath(campoContaComSaldoToogle);
        clicarPorXpath(btnCadastrar);
        validarSeContaFoiCriadaComSucesso();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(By.xpath(mensagemContaCriada)).isDisplayed());
        String mensagemCadastro = driver.findElement(By.xpath(mensagemContaCriada)).getText();
        String[] numConta = mensagemCadastro.split("conta|foi");
        String txtContaEdigito = numConta[1].trim();
        String[] contaDigito = txtContaEdigito.split("-");
        this.conta = contaDigito[0];
        this.digito = contaDigito[1];
        clicarPorXpath(btnFechar);
    }

    public void cadastrarNovaContaSemSaldo(String email, String nome, String senha) {
        clicarPorXpath(btnRegistrar);
        preencherValorPorXpath(campoEmail, email);
        preencherValorPorXpath(campoNome, nome);
        preencherValorPorXpath(campoSenha, senha);
        preencherValorPorXpath(campoConfirmacaoSenha, senha);
        clicarPorXpath(btnCadastrar);
        validarSeContaFoiCriadaComSucesso();
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(By.xpath(mensagemContaCriada)).isDisplayed());
        String mensagemCadastro = driver.findElement(By.xpath(mensagemContaCriada)).getText();
        String[] numConta = mensagemCadastro.split("conta|foi");
        String txtContaEdigito = numConta[1].trim();
        String[] contaDigito = txtContaEdigito.split("-");
        this.conta = contaDigito[0];
        this.digito = contaDigito[1];
        clicarPorXpath(btnFechar);
    }

}