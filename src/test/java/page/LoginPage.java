package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public String campoEmail = "//body/div[@id='__next']/div[1]/div[2]/div[1]/div[1]/form[1]/div[1]/input[1]";
    public String campoSenha = "//body/div[@id='__next']/div[1]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/input[1]";
    public String btnAcessar = "//button[contains(text(),'Acessar')]";

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clicarPorXpath(String elemento) {
        driver.findElement(By.xpath(elemento)).click();
    }

    public void preencherCampo(String elemento, String valor) {
        driver.findElement(By.xpath(elemento)).sendKeys(valor);
    }

    public void validarLogin() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.getCurrentUrl().contains("/home"));
        Assert.assertTrue("Erro na validação da homepage", driver.getCurrentUrl().equals("http://localhost:3000/home"));
    }

    public void fazerLogin(String email, String senha){
        preencherCampo(campoEmail, email);
        preencherCampo(campoSenha, senha);
        clicarPorXpath(btnAcessar);
        validarLogin();
    }
}