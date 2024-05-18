package page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    public String elementoSaldo = "//p[@id='textBalance']";

    public String btnTransferencia = "//a[@id='btn-TRANSFERÊNCIA']";
    WebDriver driver;


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void validarSaldo(String valorEsperado) {
        String atual = driver.findElement(By.xpath(elementoSaldo)).getText();
        Assert.assertEquals(valorEsperado, atual);
    }

    public void clicarPorXpath(String elemento) {
        driver.findElement(By.xpath(elemento)).click();
    }
}