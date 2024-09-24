package criticos_agencia;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Constantes.*;
import DataDrivenTesting.ReadExcelFile;
import java.io.IOException;
import java.util.List;


public class Cases_153 {

	int secDelay = 2000;

	private WebDriver driver;
	private ReadExcelFile readFile;
	private Process ffmpegProcess;
	
	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(Constantes.URL_CENTRAL);
		readFile = new ReadExcelFile();
		
	}

	@Test
	public void test() throws InterruptedException, IOException {
		
//		-------------------------------------------Avanzar Pases de Bóveda en Agencia / Cajas / Pases de Bóveda 2.2.--------------------------------------------
		
		//RUTA PARA GRABAR LA PANTALLA--------------------------------------------------------------------
		
		String outputFile = ""+Constantes.RUTA_CAPTURES+"\\Criticos_Agencia\\Cases_153\\153.avi";
		String ffmpegCommand = String.format("ffmpeg -y -f gdigrab -i desktop -framerate 60 -video_size 19820x1080 -vf "+Constantes.ConfigFFMPEG+" -offset_x 0 -offset_y 0 -codec:v libx264 %s", outputFile);
		ffmpegProcess = Runtime.getRuntime().exec(ffmpegCommand);
		
		//BLOQUE DE CODIGO PARA SELECCIONAR EL DIA ACTUAL EN EL CALENDARIO--------------------
		
		String filepath = ""+Constantes.RUTA_EXCELJ+"\\MatrizCriticosAgencia.xlsx";		
		String ATM = readFile.getCellValue(filepath, "CriticosAgencia", 6, 2);
		WebDriverWait ewait = new WebDriverWait(driver,20); //Espera Explícita
		
		//INGRESAMOS EN EL AMBIENTE DE CENTRAL------------------------------------------------
		
		WebElement login = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
		login.sendKeys(Constantes.USUARIO);
		WebElement password = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		password.sendKeys(Constantes.CONTRASEÑA);	Thread.sleep(secDelay);
		WebElement logear = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imagenOk")));
		logear.click();	 Thread.sleep(secDelay);
		
		//CAMBIAMOS EL PARAMETRO WF------------------------------------------------------------
		
		WebElement Configuracion = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='./images/menu/configuracion.jpg']")));
    	Configuracion.click(); 	
        WebElement Generales = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("generales")));
        Generales.click();
        WebElement ParametrosGenerales = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("parametroGeneral")));
        ParametrosGenerales.click();
        WebElement modificables = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tab-1026")));
        modificables.click();	  Thread.sleep(secDelay);
        WebElement ParametroWF = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='PASE_WF']")));
  		ParametroWF.click();
 		WebElement ModificarParametroWF = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionModificar")));
		ModificarParametroWF.click();
		WebElement ValorParametroWF = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("valorModificar-inputEl")));
        Actions doble = new Actions(driver);
        doble.doubleClick(ValorParametroWF).perform();	
		ValorParametroWF.sendKeys("0");
		WebElement AceptarModificarParametroWF = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1035-btnInnerEl")));
		AceptarModificarParametroWF.click();
		WebElement ConfirmarModificarParametroWF = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1006-btnEl")));
		ConfirmarModificarParametroWF.click();	   Thread.sleep(secDelay);
		
		WebElement Mensaje = driver.findElement(By.id("messagebox-1001-msg"));
        String ObtenerMensaje = Mensaje.getText().trim();
        System.out.println(ObtenerMensaje);
        String ExpectativaTexto = "Registro modificado exitosamente";
        Assert.assertEquals(ObtenerMensaje, ExpectativaTexto);
		
		WebElement AceptarMensajeParametroWF = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnEl")));
		AceptarMensajeParametroWF.click();
		
		//INGRESAMOS EN EL MODULO DE AGENCIA---------------------------------------------------------
			
		((JavascriptExecutor)driver).executeScript("window.open()");
        String secondTab = driver.getWindowHandles().stream().skip(1).findFirst().get();
        driver.switchTo().window(secondTab);
        driver.get(Constantes.URL_AGENCIA);
 
		WebElement login2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
		login2.sendKeys(Constantes.USUARIO);
		WebElement password2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		password2.sendKeys(Constantes.CONTRASEÑA);	Thread.sleep(secDelay);
		WebElement logear2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imagenOk")));
		logear2.click();		Thread.sleep(secDelay);
		
		//INGRESAMOS EN EL APARTADO DE CAJAS-----------------------------------------------------------
		
		WebElement IngresarEnCaja = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("taquilla")));
		IngresarEnCaja.click();
		WebElement IngresarPasesDeBoveda = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pasesDeBoveda")));
		IngresarPasesDeBoveda.click();
		WebElement OpcionCrear = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCrear")));
		OpcionCrear.click();
		
		//PASE DE CAJA A BOVEDA-------------------------------------------------------------------------
		
		try {
		WebElement DespTipoDivisa = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divisaCP-trigger-picker")));
		DespTipoDivisa.click();		Thread.sleep(secDelay);
		WebElement Divisa = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='"+Constantes.DIVISA+"']")));
		Divisa.click();
		WebElement DespCaja = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("terminalCP-trigger-picker")));
		DespCaja.click();	  Thread.sleep(secDelay);
		WebElement Caja = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='"+ATM+"']")));
		Caja.click();
		WebElement DespModalidad = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadCP-trigger-picker")));
	    DespModalidad.click();	   Thread.sleep(secDelay);
		WebElement Modalidad = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Normal']")));
	    Modalidad.click();
		WebElement DesplegarTipoPase = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoPaseCP-trigger-picker")));
		DesplegarTipoPase.click(); 	   Thread.sleep(secDelay);
		WebElement TipoPase = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Pase de Caja a Boveda']")));
		TipoPase.click();	  Thread.sleep(secDelay);		
		WebElement elementoCantidad100 = driver.findElement(By.cssSelector("td[data-columnid='gridcolumn-1040']")); 
	    Actions doble2 = new Actions(driver);
	    doble2.doubleClick(elementoCantidad100).perform();	Thread.sleep(secDelay);
	    List<WebElement> elementos = driver.findElements(By.cssSelector("[id^='ext-element-'][id$='16'], [id^='ext-element-'][id$='17'], [id^='ext-element-'][id$='18'], [id^='ext-element-'][id$='19'], [id^='ext-element-'][id$='20'], [id^='ext-element-'][id$='21'], [id^='ext-element-'][id$='22'], [id^='ext-element-'][id$='23'], [id^='ext-element-'][id$='24'], [id^='ext-element-'][id$='25'], [id^='ext-element-'][id$='26'], [id^='ext-element-'][id$='27'], [id^='ext-element-'][id$='28'], [id^='ext-element-'][id$='29'], [id^='ext-element-'][id$='30'], [id^='ext-element-'][id$='31']"));
	    for (WebElement elemento : elementos) {
	        if (elemento.isEnabled()) {
	            Actions actions = new Actions(driver);
	            actions.click(elemento).sendKeys("1").sendKeys(Keys.ENTER).perform();
	        }
	    }
		WebElement AceptarPase = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1059-btnInnerEl")));
	    AceptarPase.click();	 Thread.sleep(secDelay);
	    
	    WebElement Mensaje2 = driver.findElement(By.id("messagebox-1001-msg"));
        String ObtenerMensaje2 = Mensaje2.getText().trim();
        System.out.println(ObtenerMensaje2);
        String ExpectativaTexto2 = "Registro creado exitosamente";
        Assert.assertEquals(ObtenerMensaje2, ExpectativaTexto2);
	    
		WebElement ConfirmarAceptar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	    ConfirmarAceptar.click();
		} catch (Exception e) {Thread.sleep(secDelay);
		WebElement Modalidad2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Apertura']")));
	    Modalidad2.click();
	    WebElement AceptarPase2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1059-btnInnerEl")));
	    AceptarPase2.click();
	    Thread.sleep(secDelay);
	    WebElement ConfirmarAceptar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	    ConfirmarAceptar2.click();
	    WebElement Estado = driver.findElement(By.id("gridcolumn-1013-titleEl")); 
    	Actions EstadoDoble = new Actions(driver);
    	EstadoDoble.doubleClick(Estado).perform();
		WebElement SeleccionarPase = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1013']")));
		SeleccionarPase.click();
		WebElement Avanzar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarPase-btnInnerEl")));
		Avanzar.click();
		WebElement AceptarAvanzar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1076-btnInnerEl")));
		AceptarAvanzar.click();
		WebElement AceptarMensaje = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
		AceptarMensaje.click();
		WebElement Estado2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gridcolumn-1013-titleEl")));
		Estado2.click();	 Thread.sleep(secDelay);
		WebElement SeleccionarPase2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1013']")));
		SeleccionarPase2.click();
		WebElement Avanzar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarPase-btnInnerEl")));
		Avanzar2.click();
		WebElement AceptarAvanzar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1076-btnInnerEl")));
		AceptarAvanzar2.click();	 Thread.sleep(secDelay);
		
		WebElement Mensaje2 = driver.findElement(By.id("messagebox-1001-msg"));
		Thread.sleep(secDelay);
        String ObtenerMensaje2 = Mensaje2.getText().trim();
        System.out.println(ObtenerMensaje2);
        String ExpectativaTexto2 = "Registro modificado exitosamente";
        Assert.assertEquals(ObtenerMensaje2, ExpectativaTexto2);
		
		WebElement AceptarMensaje2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
		AceptarMensaje2.click();	 Thread.sleep(secDelay);
	    
		//PASE DE CAJA A BOVEDA--------------------------------------------------------------------------------------
	    
		WebElement OpcionCrear2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCrear")));
		OpcionCrear2.click();		
  		WebElement DespTipoDivisa3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divisaCP-trigger-picker")));
		DespTipoDivisa3.click();	 Thread.sleep(secDelay);
  		WebElement Divisa3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='"+Constantes.DIVISA+"']")));
		Divisa3.click();
  		WebElement DespCaja3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("terminalCP-trigger-picker")));
		DespCaja3.click();	   Thread.sleep(secDelay);
  		WebElement Caja3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='"+ATM+"']")));
		Caja3.click();
  		WebElement DespModalidad3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadCP-trigger-picker")));
	    DespModalidad3.click();		Thread.sleep(secDelay);
  		WebElement Modalidad3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Normal']")));
	    Modalidad3.click();
  		WebElement DesplegarTipoPase2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoPaseCP-trigger-picker")));
		DesplegarTipoPase2.click();  	Thread.sleep(secDelay);
  		WebElement TipoPase2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Pase de Caja a Boveda']")));
		TipoPase2.click();	   Thread.sleep(secDelay);
  		WebElement elementoCantidad100 = driver.findElement(By.cssSelector("td[data-columnid='gridcolumn-1040']")); 
  	    Actions doble2 = new Actions(driver);
  	    doble2.doubleClick(elementoCantidad100).perform();	Thread.sleep(secDelay);
  	    List<WebElement> elementos = driver.findElements(By.cssSelector("[id^='ext-element-'][id$='16'], [id^='ext-element-'][id$='17'], [id^='ext-element-'][id$='18'], [id^='ext-element-'][id$='19'], [id^='ext-element-'][id$='20'], [id^='ext-element-'][id$='21'], [id^='ext-element-'][id$='22'], [id^='ext-element-'][id$='23'], [id^='ext-element-'][id$='24'], [id^='ext-element-'][id$='25'], [id^='ext-element-'][id$='26'], [id^='ext-element-'][id$='27'], [id^='ext-element-'][id$='28'], [id^='ext-element-'][id$='29'], [id^='ext-element-'][id$='30'], [id^='ext-element-'][id$='31'][id^='ext-element-'][id$='32'], [id^='ext-element-'][id$='33'], [id^='ext-element-'][id$='34'], [id^='ext-element-'][id$='35'], [id^='ext-element-'][id$='36'], [id^='ext-element-'][id$='37'][id^='ext-element-'][id$='38'], [id^='ext-element-'][id$='39'], [id^='ext-element-'][id$='40'], [id^='ext-element-'][id$='41'], [id^='ext-element-'][id$='42'], [id^='ext-element-'][id$='43'][id^='ext-element-'][id$='44'], [id^='ext-element-'][id$='45'], [id^='ext-element-'][id$='46'], [id^='ext-element-'][id$='47'], [id^='ext-element-'][id$='48'], [id^='ext-element-'][id$='49'][id^='ext-element-'][id$='50'], [id^='ext-element-'][id$='51'], [id^='ext-element-'][id$='52']"));
  	    for (WebElement elemento : elementos) {
  	        if (elemento.isEnabled()) {
  	            Actions actions = new Actions(driver);
  	            Thread.sleep(secDelay);
  	            actions.click(elemento).sendKeys("1").sendKeys(Keys.ENTER).perform();
  	        }
  	    }
	  	
  		WebElement AceptarPase3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1059-btnInnerEl")));
	    AceptarPase3.click();
  		WebElement ConfirmarAceptar3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	    ConfirmarAceptar3.click();
	    WebElement Estado3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gridcolumn-1013-titleEl")));
		Estado3.click();	 Thread.sleep(secDelay);
		}
		
		WebElement Estado = driver.findElement(By.id("gridcolumn-1013-titleEl")); 
    	Actions EstadoDoble = new Actions(driver);
    	EstadoDoble.doubleClick(Estado).perform();
		WebElement SeleccionarPase = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1013']")));
		SeleccionarPase.click();
		WebElement Avanzar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarPase-btnInnerEl")));
		Avanzar.click();
	  			
  		try {
  			WebElement AceptarAvanzar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1076-btnEl")));
  			AceptarAvanzar.click();	
		} catch (Exception e) {
			WebElement AceptarAvanzar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1092-btnEl")));
  			AceptarAvanzar.click();
		}	
			
		WebElement AceptarMensaje = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
		AceptarMensaje.click();
		WebElement Estado2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gridcolumn-1013-titleEl")));
		Estado2.click();
		WebElement SeleccionarPase2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1013']")));
		SeleccionarPase2.click();
		WebElement Avanzar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarPase-btnInnerEl")));
		Avanzar2.click();
  			
  		try {
  			WebElement AceptarAvanzar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1076-btnEl")));
  			AceptarAvanzar2.click();	
		} catch (Exception e) {
			WebElement AceptarAvanzar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1092-btnEl")));
  			AceptarAvanzar2.click();
		}	Thread.sleep(secDelay);
		
  		WebElement Mensaje2 = driver.findElement(By.id("messagebox-1001-msg"));
        String ObtenerMensaje2 = Mensaje2.getText().trim();
        System.out.println(ObtenerMensaje2);
        String ExpectativaTexto2 = "Registro modificado exitosamente";
        Assert.assertEquals(ObtenerMensaje2, ExpectativaTexto2);
  		
		WebElement AceptarMensaje2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
		AceptarMensaje2.click();	
	}
	
	@After
	public void tearDown() throws Exception {
		Thread.sleep(3000);
	    if (driver != null) {
	        driver.quit();
	    }
	    Thread.sleep(2000); 
	    if (ffmpegProcess != null) {
	        ffmpegProcess.destroy();
	        ffmpegProcess.waitFor();
	    }
	}

}
