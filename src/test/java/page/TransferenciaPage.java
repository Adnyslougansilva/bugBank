package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferenciaPage {
    public String campoNumeroDaConta = "//body/div[@id='__next']/div[1]/div[3]/form[1]/div[1]/div[1]/input[1]";
    public String campoDigitoConta = "//body/div[@id='__next']/div[1]/div[3]/form[1]/div[1]/div[2]/input[1]";
    public String campoValor = "//body/div[@id='__next']/div[1]/div[3]/form[1]/div[2]/input[1]";
    public String campoDescricao = "//body/div[@id='__next']/div[1]/div[3]/form[1]/div[3]/input[1]";
    public String btnTransferir = "//button[contains(text(),'Transferir agora')]";

    WebDriver driver;

    public TransferenciaPage(WebDriver driver) {
        this.driver = driver;
    }

    public void preencherValorPorXpath(String elemento, String valor) {
        driver.findElement(By.xpath(elemento)).sendKeys(valor);
    }

    public void clicarPorXpath(String elemento) {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.findElement(By.xpath(elemento)).isDisplayed());
        driver.findElement(By.xpath(elemento)).click();
    }

    public void validarTransferenciaSucesso() {
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> d.getPageSource().contains("Transferencia realizada com sucesso"));
        Assert.assertTrue("Erro ao validar a transferencia!", driver.getPageSource().contains("Transferencia realizada com sucesso"));

    }


}