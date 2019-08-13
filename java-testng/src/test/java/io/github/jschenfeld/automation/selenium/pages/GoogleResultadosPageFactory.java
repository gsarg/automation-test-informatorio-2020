package io.github.jschenfeld.automation.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleResultadosPageFactory {

	private Integer cont = 0;

	@FindBy(className = "result--more__btn")
	private WebElement btnMore;

	private WebDriver webDriver;

	public GoogleResultadosPageFactory(WebDriver driver) {
		PageFactory.initElements(driver, this);
		webDriver = driver;
	}

	public void abrirLaPaginaDelPoloIt() {
		System.out.println(btnMore);

		try {
			cont++;
			WebElement poloItlink = webDriver.findElement(By.xpath("//h2/a[text()='Peppo lanzo las inscripciones para el curso gratuito de ..."
					+ "']"));
			poloItlink.click();
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
			btnMore.click();
			if (cont < 3) {
				this.abrirLaPaginaDelPoloIt();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	

	}
}
