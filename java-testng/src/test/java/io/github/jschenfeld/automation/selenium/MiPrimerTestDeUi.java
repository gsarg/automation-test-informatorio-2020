package io.github.jschenfeld.automation.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.jschenfeld.automation.selenium.pages.GoogleBusquedaPageFactory;
import io.github.jschenfeld.automation.selenium.pages.GoogleBusquedaPageObject;
import io.github.jschenfeld.automation.selenium.pages.GoogleResultadosPageFactory;

public class MiPrimerTestDeUi {
	
	private WebDriver driver;

	@BeforeMethod
	public void inicializarBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		WebDriverManager.firefoxdriver().setup();
//		driver = new FirefoxDriver();
	}

	@Test(enabled = false)
	public void busquedaEnGoogle() {
		driver.get("https://duckduckgo.com/");

		GoogleBusquedaPageObject googleBusquedaPage = new GoogleBusquedaPageObject(driver);
		googleBusquedaPage.ingresarBusqueda("Informatorio Chaco");
		googleBusquedaPage.clickBotonBuscar();

		System.out.println(driver.getCurrentUrl());

	}

	@Test(enabled = true, threadPoolSize = 5, invocationCount = 3)
	public void busquedaEnGoogleUsandoPageFactory() {
		driver.get("https://duckduckgo.com");

		GoogleBusquedaPageFactory googleBusquedaPage = new GoogleBusquedaPageFactory(driver);
		googleBusquedaPage.buscar("Informatorio Chaco");
	
		GoogleResultadosPageFactory googleResultadosPageFactory = new GoogleResultadosPageFactory(driver);
		googleResultadosPageFactory.abrirLaPaginaDelPoloIt();
		System.out.println( );
		System.out.println("La url devuelta es: " + driver.getCurrentUrl());
		System.out.println( );
		Assert.assertEquals("http://www.actualidadchaco.com/vernota.asp?id_noticia=120379", driver.getCurrentUrl());
	}

	@AfterMethod(alwaysRun = true)
	public void cerrarBrowser() {
		if (null != driver) {
			driver.quit();
		}
	}
}