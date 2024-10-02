package Integrales_Diferencias;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import DataDrivenTesting.ReadExcelFile;
import Constantes.*;

public class Cases_458_463 {
	
	int secDelay = 1000;
	private WebDriver driver;
	private ReadExcelFile readFile;
	private Process ffmpegProcess;

	@Before
	public void setUp() throws Exception {
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		readFile = new ReadExcelFile();
		driver.get(Constantes.URL_CENTRAL);
				
	}

	@Test
	public void test() throws IOException, InterruptedException {
		
			//----------  Saldar Diferencia Faltante - Cargo de Cuenta, Contra Gasto, Contra Castigo, Reposicion de Efectivo, Cheque mismo Banco, Cheque de otros Bancos- AG / CDA ----------//	
		
			// Excel - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
																
			String filepath =  "" + Constantes.RUTA_EXCEL + "\\MatrizIntegrales.xlsx";
			String TipoUnidadReceptora = readFile.getCellValue(filepath, "Cases", 14, 3);
			String UnidadReceptora = readFile.getCellValue(filepath, "Cases", 14, 4);
			String TipoUnidadCierre = readFile.getCellValue(filepath, "General", 9, 1);
			String UnidadCierre = readFile.getCellValue(filepath, "General", 9, 2);
			String TipoUnidadDiferencia = readFile.getCellValue(filepath, "General", 9, 1);
			String UnidadDiferencia = readFile.getCellValue(filepath, "General", 9, 2);
			String Banco = readFile.getCellValue(filepath, "General", 11, 2);
						
			// Modalidad de la diferencia- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		
		 	String Modalidad ="//li[text()='Cargo en Cuenta']";
		 	String Modalidad2 ="//li[text()='Contra Gasto']";
		 	String Modalidad3 ="//li[text()='Contra Castigo']";
		 	String Modalidad4 ="//li[text()='Reposicion de Efectivo']";
		 	String Modalidad5 ="//li[text()='Cheque mismo Banco']";
		 	String Modalidad6 ="//li[text()='Cheque de otros Bancos']";
		 	
		 	// Cambio de Pestaña - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		 	
		 	JavascriptExecutor js = (JavascriptExecutor) driver;	
			String Agencia = Constantes.URL_AGENCIA;
			String Central = Constantes.URL_CENTRAL;
			
			//--------------Guardar la fecha y hora - Darle un formato - Volverlo un string para guardarlo----------------//

			LocalDate fechaActual = LocalDate.now();
	    	LocalTime horaActual = LocalTime.now();
	    	DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    	String fechaFormateada = fechaActual.format(formatoFecha);
	    	DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH-mm-ss");
	    	String horaFormateada = horaActual.format(formatoHora);
	    	String nombreArchivo = fechaFormateada.replace("/", "-") + "_" + horaFormateada + ".avi";
			System.out.println("Fecha y Hora (formato dd/MM/yyyy/HH/mm/ss): " + nombreArchivo);
			
			// Obtener Fecha Actual - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
			
			int DiaActual = LocalDate.now().getDayOfMonth();			
	        String DiaActualString = (DiaActual < 10) ? "0" + DiaActual : String.valueOf(DiaActual);        
	        System.out.println(DiaActualString);
			
			//RUTA PARA GRABAR LA PANTALLA--------------------------------------------------------------------
			
			String outputFile = ""+Constantes.RUTA_CAPTURES+"\\Integrales_Diferencia\\Cases_458_463\\"+nombreArchivo+"";
			String ffmpegCommand = String.format("ffmpeg -y -f gdigrab -i desktop -framerate 60 -video_size 19820x1080 -vf "+Constantes.ConfigFFMPEG+" -offset_x 0 -offset_y 0 -codec:v libx264 %s", outputFile);
			ffmpegProcess = Runtime.getRuntime().exec(ffmpegCommand);
			
		 	// Captura - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	        
		 	String directorioCapturas = "" + Constantes.RUTA_CAPTURES +"\\Integrales_Diferencia\\Cases_458_463";
		 	
		 	String nombreArchivo1 = "Cierre Inicial.png";
		 	String captura1 = directorioCapturas + "/" + nombreArchivo1;
		 	String nombreArchivo2 = "Cierre Cargo en cuenta.png";
		 	String captura2 = directorioCapturas + "/" + nombreArchivo2;
		 	String nombreArchivo3 = "Cierre Constra Gastos.png";
		 	String captura3 = directorioCapturas + "/" + nombreArchivo3;
		 	String nombreArchivo4 = "Cierre Constra Castigo.png";
		 	String captura4 = directorioCapturas + "/" + nombreArchivo4;
		 	String nombreArchivo5 = "Cierre Reposicion de Efectivo.png";
		 	String captura5 = directorioCapturas + "/" + nombreArchivo5;
		 	String nombreArchivo6 = "Cierre Cheque mismo Banco.png";
		 	String captura6 = directorioCapturas + "/" + nombreArchivo6;
		 	String nombreArchivo7 = "Cierre Cheque de otros Bancos.png";
		 	String captura7 = directorioCapturas + "/" + nombreArchivo7;

		 	// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

		 	WebDriverWait ewait = new WebDriverWait(driver,20); //Espera Explícita
		 		        
	        WebElement login = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
	        login.sendKeys(Constantes.USUARIO);
	        WebElement password = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
	        password.sendKeys(Constantes.CONTRASEÑA);
	        WebElement enter = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imagenOk")));
	        enter.click();
	        
	        // Cierre de Unidades - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	        
	        WebElement Logistica = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='./images/menu/logistica.jpg']")));
	        Logistica.click();
	        WebElement AdministracionEfectivo = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("administracionEfectivo")));
	        AdministracionEfectivo.click();
	        WebElement CierreUnidades = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Image1")));
	        CierreUnidades.click();
	        
	        WebElement DesplegarTipoUnidadCierre = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen47")));
	        DesplegarTipoUnidadCierre.click();  
	        WebElement SeleccionarTipoUnidadCierre = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + TipoUnidadCierre + "']")));
	        SeleccionarTipoUnidadCierre.click();
	        WebElement DesplegarUnidadCierre = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen60")));
	        DesplegarUnidadCierre.click();
	        WebElement SeleccionarUnidadCierre = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + UnidadCierre + "']")));
	        SeleccionarUnidadCierre.click();
	        WebElement DesplegarDivisaCierre = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen73")));
	        DesplegarDivisaCierre.click();
	        WebElement SeleccionarDivisaCierre = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+Constantes.DIVISA+"']")));
	        SeleccionarDivisaCierre.click();
	        WebElement ConsultarCierre = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen86")));
	        ConsultarCierre.click();
	        
	        WebElement SeleccionarDía = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='sameMonth x-unselectable'][text()='"+  DiaActualString +"']")));
	        SeleccionarDía.click();
	        WebElement CerrarDía = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCerrarDia")));
	        CerrarDía.click();
	        WebElement MensajeConfirmacion = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1069")));
	        MensajeConfirmacion.click();	 Thread.sleep(secDelay);
	             	                                  	     
            WebElement EnviosEntrada = driver.findElement(By.id("enviosEntradaCierre"));
    		String EnvioEntradaString = EnviosEntrada.getAttribute("value");
    		EnvioEntradaString = EnvioEntradaString.replace("." , "").replace("," , ".");
    		double EnvioEntradaDouble = Double.parseDouble(EnvioEntradaString);
   	     	System.out.println(EnvioEntradaDouble);
   	     	
	   	    WebElement EnviosDiferenciasFaltante = driver.findElement(By.id("enviosDifSalidaCierre"));
	   	    String EnviosDiferenciasFaltanteString = EnviosDiferenciasFaltante.getAttribute("value");
	   	    EnviosDiferenciasFaltanteString = EnviosDiferenciasFaltanteString.replace("." , "").replace("," , ".");
	   	 	double EnviosDiferenciasFaltanteDouble = Double.parseDouble(EnviosDiferenciasFaltanteString);
	     	System.out.println(EnviosDiferenciasFaltanteDouble);
	     	
	     	WebElement EnviosRegularizacionesFaltante = driver.findElement(By.id("regularizacionEntradaCierre"));
	   	    String EnviosRegularizacionesFaltanteString = EnviosRegularizacionesFaltante.getAttribute("value");
	   	    EnviosRegularizacionesFaltanteString = EnviosRegularizacionesFaltanteString.replace("." , "").replace("," , ".");
	   	 	double EnviosRegularizacionesFaltanteDouble = Double.parseDouble(EnviosRegularizacionesFaltanteString);
	     	System.out.println(EnviosRegularizacionesFaltanteDouble);
	 
	        File archivo = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(archivo, new File(captura1)); 	        Thread.sleep(secDelay);
	        	   	            
	        // Cambio Agencia - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	        
		    js.executeScript("window.open(arguments[0]);", Agencia);	  
		    Set<String> handles = driver.getWindowHandles();
		    String PestañaAgencia = (String) handles.toArray()[handles.size() - 1];
		    driver.switchTo().window(PestañaAgencia);
		    
		    WebElement loginAgencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
		    loginAgencia.sendKeys(Constantes.USUARIO);
		    WebElement passwordAgencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		    passwordAgencia.sendKeys(Constantes.CONTRASEÑA); 		    Thread.sleep(secDelay);	 
		    WebElement enterAgencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imagenOk")));
		    enterAgencia.click();
	        
		    WebElement ControlRemesas = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("remesas")));
		    ControlRemesas.click();
		    WebElement Envios = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Envios")));
		    Envios.click();
		    
		    //Ingresamos en el modulo de central-----------------------------------------------------------------------
		    
		    js.executeScript("window.open(arguments[0]);", Central);	Thread.sleep(secDelay);
		    Set<String> handles2 = driver.getWindowHandles();
		    String PestañaCentral = (String) handles2.toArray()[handles2.size() - 1];
		    driver.switchTo().window(PestañaCentral);
		    
		    WebElement loginCentral = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
	        loginCentral.sendKeys(Constantes.USUARIO);
	        WebElement passwordCentral = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
	        passwordCentral.sendKeys(Constantes.CONTRASEÑA); Thread.sleep(secDelay);
	        WebElement enterCentral = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("imagenOk")));
	        enterCentral.click();     
	        
	        WebElement logistica = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='./images/menu/logistica.jpg']")));
	        logistica.click();
	        WebElement administracionEfectivo = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("administracionEfectivo")));
	        administracionEfectivo.click();
	        WebElement enviosCentral = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("envio")));
	        enviosCentral.click();
	        
	   	 	//Ingresamos en el modulo de agencia y procedemos a crear las remesa - - - - - - - - - - - - - - - - - - - - - - -
			
		    for (int i = 0; i < 6; i++) {
		    	
	    	String PestañaAgencia2 = (String) handles.toArray()[handles.size() - 1];
		    driver.switchTo().window(PestañaAgencia2);
		    driver.navigate().refresh();	Thread.sleep(secDelay);
		    
	 	    WebElement CrearEnvio = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionNuevo")));
		    CrearEnvio.click();
		    WebElement cTipoEnviar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("emisorCrear-inputEl")));
		    cTipoEnviar.click();
		    
		    WebElement cDesplegarTipoUnidadReceptora1 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoUnidadCrear-trigger-picker")));
		    cDesplegarTipoUnidadReceptora1.click();
		    List<WebElement> options = driver.findElements(By.xpath("//ul[@class='x-list-plain']/li"));
	        for (WebElement option : options) {
	            if (option.getText().equals(TipoUnidadReceptora)) {
	                option.click();
	                break;
	            
	                }
	            }		Thread.sleep(secDelay);
		    	
		    WebElement cDesplegarUnidadReceptoraEnvio = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unidadCrear-trigger-picker"))); 
		    cDesplegarUnidadReceptoraEnvio.click();
		    cDesplegarUnidadReceptoraEnvio.click();
		    List<WebElement> options3 = driver.findElements(By.xpath("//ul[@class='x-list-plain']/li")); 
	        for (WebElement option : options3) {
	            if (option.getText().equals(UnidadReceptora)) {
	                option.click();
	                break;
	            
	                }
	            }		Thread.sleep(secDelay);
	            
		    WebElement cDesplegarTipoEnvio = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoEnvioCrear-trigger-picker")));
		    cDesplegarTipoEnvio.click();
		    List<WebElement> options4 = driver.findElements(By.xpath("//ul[@class='x-list-plain']/li"));
	        for (WebElement option : options4) {
	            if (option.getText().equals("Normal - Urbano")) {
	                option.click();
	                break;
	            
	                }
	            }		Thread.sleep(secDelay);
		    
		    WebElement cBotonSiguiente = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("siguiente1Crear-btnWrap")));
		    cBotonSiguiente.click();     Thread.sleep(secDelay);
		    
		    WebElement cDesplegarDivisa = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("monedaCrear-trigger-picker")));
		    cDesplegarDivisa.click();	 Thread.sleep(secDelay);	  
		    WebElement cElementoDolar = driver.findElement(By.xpath("//li[text()='"+Constantes.DIVISA+"']"));
		    cElementoDolar.click();
		        
		    Random rand = new Random();
		    int numeroAleatorio = rand.nextInt(1000000) + 1000000000;
		    String numeroTexto = Integer.toString(numeroAleatorio);
		    WebElement numeroServicioAleatorio = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nroServicioCrear-inputEl"))); 
		    numeroServicioAleatorio.sendKeys(String.valueOf(numeroTexto)); 
		    System.out.println("El Cataporte es: " + numeroTexto);		    Thread.sleep(secDelay);
		     		     
		    WebElement cBotonIncluir = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirRemesasCrear-btnWrap")));
	        cBotonIncluir.click(); 	   Thread.sleep(secDelay);
	        
	        WebElement cDesplegarTipoEnvase = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoEnvaseCrear-trigger-picker")));
	        cDesplegarTipoEnvase.click();
	        WebElement cSeleccionarTipoEnvase = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Bolsa']")));
	        cSeleccionarTipoEnvase.click();
	        WebElement cDesplegarTipoPieza = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoPiezaCrear-trigger-picker")));
	        cDesplegarTipoPieza.click();
	        WebElement cSeleccionarTipoPieza = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Billete']")));
	        cSeleccionarTipoPieza.click();
	        WebElement cColocarCantidadEnvases = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cantidadEnvaseCrear-inputEl")));
	        cColocarCantidadEnvases.sendKeys("1");
	        WebElement cBotonIncluirEnvases = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirEnvasesCrear-btnWrap")));
	        cBotonIncluirEnvases.click();
	        WebElement cColocarNumeroPlomos = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("numeroCrear-inputEl")));
	        cColocarNumeroPlomos.sendKeys("1");
	        WebElement cBotonIncluirPlomos = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirPlomosCrear-btnIconEl")));
	        cBotonIncluirPlomos.click(); 	 Thread.sleep(secDelay);  
	        
	        WebElement cElementoCantidad = driver.findElement(By.cssSelector("td[data-columnid='gridcolumn-1115']")); 
	        Actions doble = new Actions(driver);
	        doble.doubleClick(cElementoCantidad).sendKeys("2").sendKeys(Keys.ENTER).perform();		Thread.sleep(secDelay);	        
	        WebElement cAceptarCreacionRemesa = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1133-btnWrap")));
	        cAceptarCreacionRemesa.click(); 	Thread.sleep(secDelay);
	   	        
	        WebElement cMensajeConfirmacionRemesa = driver.findElement(By.id("container-1003-innerCt"));
        	String texto = cMensajeConfirmacionRemesa.getText();
        	System.out.println("La creacion Fue: " + texto); 	        Thread.sleep(secDelay);
	        
	        WebElement MensajeCreacionRemesa = driver.findElement(By.id("messagebox-1001-msg"));
        	String ObtenerMensaje = MensajeCreacionRemesa.getText().trim();
        	System.out.println(ObtenerMensaje);
        	String ExpectativaTexto = "Registro creado exitosamente";
        	Assert.assertEquals(ObtenerMensaje, ExpectativaTexto);
	   	   
	        WebElement cAceptarMensajeInformativo = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnWrap")));
	        cAceptarMensajeInformativo.click();
	   
	        driver.navigate().refresh();
	       		
		    // Cambio Pestaña - Cemtral - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -		     
		    
		    Set<String> handles3 = driver.getWindowHandles();
		    String ModuloCentral = (String) handles3.toArray()[handles3.size() - 1];
	    	driver.switchTo().window(ModuloCentral);
	        
	        // Avance Aprobado Remesa Central - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
		    
		    WebElement filtrosCentral = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filtrosEnvio-btnWrap")));
	        filtrosCentral.click();
	        WebElement campoNroServicioCentral = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nroServicioConsulta-inputEl")));
	        campoNroServicioCentral.sendKeys(numeroTexto); 	        Thread.sleep(secDelay);
	        WebElement consultarFiltrosCentral = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1139-btnWrap")));
	        consultarFiltrosCentral.click();		        Thread.sleep(secDelay);
	        
	        WebElement seleccionarRemesa = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1065']")));
	        seleccionarRemesa.click();     Thread.sleep(secDelay);
	        WebElement botonAvanzarEstadoAprobado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarNormal-btnInnerEl")));
	        botonAvanzarEstadoAprobado.click();     Thread.sleep(secDelay);
	        WebElement AvanceEstadoAprobado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarAvanzarNomal-btnInnerEl")));
	        AvanceEstadoAprobado.click();     Thread.sleep(secDelay);

	        WebElement mensajeConfirmacionAprobado = driver.findElement(By.id("messagebox-1027-msg"));
        	String MensajeAprobado = mensajeConfirmacionAprobado.getText();
        	System.out.println("Avance: " + MensajeAprobado);
	        	
		    WebElement InformacionAvanceAprobado = driver.findElement(By.id("messagebox-1027-msg"));
        	String ObtenerMensajeAprobado = InformacionAvanceAprobado.getText().trim();
        	System.out.println(ObtenerMensajeAprobado);
        	String ExpectativaTextoAprobado = "Registro modificado exitosamente";
        	Assert.assertEquals(ObtenerMensajeAprobado, ExpectativaTextoAprobado);	
	        		               
	        WebElement AceptarAvanceEstadoAprobado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1031-btnInnerEl")));
	        AceptarAvanceEstadoAprobado.click();    
	        	
	        // Cambio Agencia - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	        	
	        String AgenciaDespacho = (String) handles3.toArray()[handles3.size() - 2];
     		driver.switchTo().window(AgenciaDespacho);	
	     		
	        // Buscar Remesa Agencia - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	     		
	     	driver.navigate().refresh();	Thread.sleep(secDelay);
	     	   		               
	        WebElement Filtros = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("filtrosEnvio-btnWrap")));
	        Filtros.click();
	        WebElement CampoNroServicio = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nroServicioConsulta-inputEl")));
	        CampoNroServicio.sendKeys(numeroTexto); 	        Thread.sleep(secDelay);
	        WebElement ConsultarFiltros = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1086-btnWrap")));
	        ConsultarFiltros.click(); 	        Thread.sleep(secDelay);	
	        
	        WebElement SeleccionarRemesaAprobado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1034']")));
	        SeleccionarRemesaAprobado.click();     Thread.sleep(secDelay);
	        WebElement BotonAvanzarEstadoDespachado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarNormal-btnInnerEl")));
	        BotonAvanzarEstadoDespachado.click();   	 Thread.sleep(secDelay);
	        WebElement AvanceEstadoDespachado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarAvanzarNomal-btnInnerEl")));
	        AvanceEstadoDespachado.click();     Thread.sleep(secDelay);
	        WebElement ConfirmarAvanceEstadoDespachado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1006-btnWrap")));
	        ConfirmarAvanceEstadoDespachado.click();     Thread.sleep(secDelay);
	          
	        WebElement MensajeConfirmacionDespachado = driver.findElement(By.id("messagebox-1001-msg"));
        	String MensajeDespachado = MensajeConfirmacionDespachado.getText();
        	System.out.println("Avance: " + MensajeDespachado);
	        	
			WebElement InformacionAvanceDespachado = driver.findElement(By.id("messagebox-1001-msg"));
        	String ObtenerMensajeDespachado = InformacionAvanceDespachado.getText().trim();
        	System.out.println(ObtenerMensajeDespachado);
        	String ExpectativaTextoDespachado = "Registro modificado exitosamente";
        	Assert.assertEquals(ObtenerMensajeDespachado, ExpectativaTextoDespachado);	
	          
	        WebElement AceptarAvanceEstadoDespachado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnWrap")));
	        AceptarAvanceEstadoDespachado.click();     Thread.sleep(secDelay);
	        // Cambio Central - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	        
	        String CentralRecibido = (String) handles3.toArray()[handles3.size() - 1];
 			driver.switchTo().window(CentralRecibido);	
     			
     		// Avance Recibido - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     			
 			WebElement retro = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tool-1113-toolEl")));
            retro.click();	   Thread.sleep(secDelay);		
     			
            WebElement Consultar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("buscarFiltro-btnWrap")));
            Consultar.click();	   Thread.sleep(secDelay);

            WebElement SeleccionarRemesaDespachado = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1065']")));
            SeleccionarRemesaDespachado.click();     Thread.sleep(secDelay);
            WebElement BotonAvanzarEstadoRecibido = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarNormal-btnInnerEl")));
            BotonAvanzarEstadoRecibido.click();     Thread.sleep(secDelay);
            WebElement AvanceEstadoRecibido = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarAvanzarNomal-btnInnerEl")));
            AvanceEstadoRecibido.click();     Thread.sleep(secDelay);
            
            WebElement MensajeConfirmacionRecibido = driver.findElement(By.id("messagebox-1027-msg"));
        	String MensajeRecibido = MensajeConfirmacionRecibido.getText();
        	System.out.println("Avance: " + MensajeRecibido);
            	
            WebElement InformacionAvanceRecibido = driver.findElement(By.id("messagebox-1027-msg"));
        	String ObtenerMensajeRecibido = InformacionAvanceRecibido.getText().trim();
        	System.out.println(ObtenerMensajeRecibido);
        	String ExpectativaTextoRecibido = "Registro modificado exitosamente";
        	Assert.assertEquals(ObtenerMensajeRecibido, ExpectativaTextoRecibido);	
          	    	 
            WebElement AceptarAvanceEstadoRecibido = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1031-btnInnerEl")));
            AceptarAvanceEstadoRecibido.click();
	                 
            // Avamce Faltamte - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
            
            WebElement SeleccionarRemesaAvanveAlternoFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1065']")));
            SeleccionarRemesaAvanveAlternoFaltante.click();     Thread.sleep(secDelay); 
            
            WebElement BotonAvanceAlternoRemesaFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("avanzarAlterno-btnInnerEl")));
            BotonAvanceAlternoRemesaFaltante.click();           
            WebElement DesplegarAvanzarEstadoAlternoFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nodoAlterno-trigger-picker")));
            DesplegarAvanzarEstadoAlternoFaltante.click();         
            WebElement SeleccionarAvanzarEstadoAlternoFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='x-boundlist-item x-boundlist-item-over'][text()='Confirmado Ajuste Faltante']")));
            SeleccionarAvanzarEstadoAlternoFaltante.click();
            WebElement AceptarAvanzarEstadoAlternoFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarAvanzarAlterno-btnInnerEl")));
            AceptarAvanzarEstadoAlternoFaltante.click();     Thread.sleep(secDelay);
            
            WebElement DesplegarDenominacionFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("denominacionFaltantesAjuste-trigger-picker")));
            DesplegarDenominacionFaltante.click();     Thread.sleep(secDelay);
            
            List<WebElement> SeleccionarDenominacionFaltante = driver.findElements(By.xpath("//li[text()='"+Constantes.DENOMINACION_DIFERENCIA+"' or text()='BILLETES - 100,00000000']"));          
            for (WebElement SeleccionarDenominacionFaltantes : SeleccionarDenominacionFaltante ) {
        	SeleccionarDenominacionFaltantes.click();     Thread.sleep(secDelay);
            }
            
            WebElement DesplegarClasificaciónFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clasificacionFaltantesAjuste-trigger-picker")));
            DesplegarClasificaciónFaltante.click();
            WebElement SeleccionarClasificacionFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='"+Constantes.APTOS+"']")));
            SeleccionarClasificacionFaltante.click();
            WebElement EscribirCantidadFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cantidadFaltantesAjuste-inputEl")));
            EscribirCantidadFaltante.sendKeys("1");
            WebElement IncluirFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirFaltantesAjuste-btnInnerEl")));
            IncluirFaltante.click();
            
            WebElement AceptarFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarAjuste-btnInnerEl")));
            AceptarFaltante.click();     Thread.sleep(secDelay);
            
            WebElement MensajeConfirmacionFaltante = driver.findElement(By.id("messagebox-1027-msg"));
        	String MensajeFaltante = MensajeConfirmacionFaltante.getText();
        	System.out.println("Avance: " + MensajeFaltante);
            	
    		WebElement InformacionAvanceFaltante = driver.findElement(By.id("messagebox-1027-msg"));
        	String ObtenerMensajeFaltante = InformacionAvanceFaltante.getText().trim();
        	System.out.println(ObtenerMensajeFaltante);
        	String ExpectativaTextoFaltante = "Registro modificado exitosamente";
        	Assert.assertEquals(ObtenerMensajeFaltante, ExpectativaTextoFaltante);	
           
            WebElement AceptarAvanceAlternoFaltante = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1031-btnInnerEl")));
            AceptarAvanceAlternoFaltante.click();
            
            driver.navigate().refresh();
        	
            System.out.println("Iteración: " + (i + 1));  
            	
		    }    Thread.sleep(secDelay);
		    
		    // Cambio Pestaña - Cemtral - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
	    	
		    js.executeScript("window.open(arguments[0]);", Constantes.URL_DIFERENCIAS);  
		    Set<String> handle1 = driver.getWindowHandles();
		    String PestañaAgencia2 = (String) handle1.toArray()[handle1.size() - 1];
		    driver.switchTo().window(PestañaAgencia2);
      
	        WebElement DesplegarTipoUnidadDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoUnidad-trigger-picker")));
	        DesplegarTipoUnidadDiferencia.click(); 	   Thread.sleep(secDelay);
	        WebElement SeleccionarTipoUnidadDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='" + TipoUnidadDiferencia + "']")));
	        SeleccionarTipoUnidadDiferencia.click();
	        WebElement DesplegarUnidadDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unidadEmisora-trigger-picker")));
	        DesplegarUnidadDiferencia.click(); 	   Thread.sleep(secDelay);
	        WebElement SeleccionarUnidadDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='" + UnidadDiferencia + "']")));
	        SeleccionarUnidadDiferencia.click();
	        WebElement DesplegarTipoConsultaDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tipoConsulta-trigger-picker")));
	        DesplegarTipoConsultaDiferencia.click();	 Thread.sleep(secDelay);
	        WebElement SeleccionarTipoConsultaDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Pendiente']")));
	        SeleccionarTipoConsultaDiferencia.click();
	        WebElement DesplegarTipoDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("estado-trigger-picker")));
	        DesplegarTipoDiferencia.click(); 	 Thread.sleep(secDelay);
	        WebElement SeleccionarTipoDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='Faltante']")));
	        SeleccionarTipoDiferencia.click();
	        WebElement DesplegarOrigenDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("origen-trigger-picker")));
	        DesplegarOrigenDiferencia.click(); 	   Thread.sleep(secDelay);
	        WebElement SeleccionarOrigenDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[text()='REMESA']")));
	        SeleccionarOrigenDiferencia.click();
	        
	        WebElement ConsultarDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("consultar-btnWrap")));
	        ConsultarDiferencia.click();
	        
	        WebElement SeleccionarDiferencia = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1011']")));
	        SeleccionarDiferencia.click();
	        WebElement BotonSaldar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saldar-btnInnerEl")));
	        BotonSaldar.click(); 	 Thread.sleep(secDelay);

	        WebElement DesplegarModalidad = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadRegularizacion-trigger-picker")));
	        DesplegarModalidad.click();
	        WebElement SeleccionarModalidad = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Modalidad)));
	        SeleccionarModalidad.click();
	        WebElement MontoSaldar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("montoSaldarRegularizacion-inputEl")));
	        MontoSaldar.sendKeys("100");
	        WebElement Descripción = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descripcionRegularizacion-inputEl")));
	        Descripción.sendKeys("prueba");
	        
	        //Saldar la Diferencia con Cargo de Cuenta- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
  	        
	        WebElement NroCuenta = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nCuentaCuen-inputEl")));
	        NroCuenta.sendKeys("02147850");
	        WebElement NombreCuenta = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreCuentaCuen-inputEl")));
	        NombreCuenta.sendKeys("Prueba Automatizada");	              	        
	        WebElement PresionarIncluir = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirRegularizacionCuen-btnWrap")));
	        PresionarIncluir.click();	  Thread.sleep(secDelay);     

	        WebElement PresionarAceptar = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarReg-btnWrap")));
	        PresionarAceptar.click();     Thread.sleep(secDelay);
	        
    		WebElement InformacionDiferenciaSaldada = driver.findElement(By.id("messagebox-1001-msg"));
    		String ObtenerMensajeDiferencia = InformacionDiferenciaSaldada.getText().trim();
    		System.out.println(ObtenerMensajeDiferencia);
    		String ExpectativaTextoDiferencia = "Operación exitosa,";
    		Assert.assertEquals(ObtenerMensajeDiferencia, ExpectativaTextoDiferencia);
	        
	        WebElement ConfirmarOperacion = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	        ConfirmarOperacion.click();
	        
	        // Validar Cierre de Unidades Cargo En Cuenta - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -      	      
        	
	        js.executeScript("window.open(arguments[0]);", Constantes.URL_CENTRAL_CIERRE_DE_UNIDADES);	  
		    Set<String> handles3 = driver.getWindowHandles();
		    String PestañaCentral2 = (String) handles3.toArray()[handles3.size() - 1];
		    driver.switchTo().window(PestañaCentral2);
	        
	        WebElement DesplegarTipoUnidadCierre_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen47")));
	        DesplegarTipoUnidadCierre_Final.click();
	        WebElement SeleccionarTipoUnidadCierre_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + TipoUnidadCierre + "']")));
	        SeleccionarTipoUnidadCierre_Final.click();
	        WebElement DesplegarUnidadCierre_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen60")));
	        DesplegarUnidadCierre_Final.click();
	        WebElement SeleccionarUnidadCierre_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + UnidadCierre + "']")));
	        SeleccionarUnidadCierre_Final.click();
	        WebElement DesplegarDivisaCierre_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen73")));
	        DesplegarDivisaCierre_Final.click();
	        WebElement SeleccionarDivisaCierre_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+Constantes.DIVISA+"']")));
	        SeleccionarDivisaCierre_Final.click();
	        WebElement ConsultarCierr_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen86")));
	        ConsultarCierr_Final.click();
	        WebElement SeleccionarDía_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='sameMonth x-unselectable'][text()='"+  DiaActualString +"']")));
	        SeleccionarDía_Final.click();
	        WebElement CerrarDía_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCerrarDia")));
	        CerrarDía_Final.click();
	        WebElement MensajeConfirmacion_Final = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1069")));
	        MensajeConfirmacion_Final.click(); 	   Thread.sleep(secDelay);
	        
	        WebElement EnviosEntradaCargoEnCuenta = driver.findElement(By.id("enviosEntradaCierre"));
    		String EnvioEntradaStringCargoEnCuenta = EnviosEntradaCargoEnCuenta.getAttribute("value");
    		EnvioEntradaStringCargoEnCuenta = EnvioEntradaStringCargoEnCuenta.replace("." , "").replace("," , ".");
    		double EnvioEntradaDoubleCargoEnCuenta = Double.parseDouble(EnvioEntradaStringCargoEnCuenta);
   	     	System.out.println(EnvioEntradaDoubleCargoEnCuenta);
   	     	Assert.assertEquals(EnvioEntradaDoubleCargoEnCuenta, (EnvioEntradaDouble + 1200), 0.000001);
   	     	
   	     	WebElement EnviosDiferenciasFaltanteCargoEnCuenta = driver.findElement(By.id("enviosDifSalidaCierre"));
	   	    String EnviosDiferenciasFaltanteStringCargoEnCuenta = EnviosDiferenciasFaltanteCargoEnCuenta.getAttribute("value");
	   	    EnviosDiferenciasFaltanteStringCargoEnCuenta = EnviosDiferenciasFaltanteStringCargoEnCuenta.replace("." , "").replace("," , ".");
	   	 	double EnviosDiferenciasFaltanteDoubleCargoEnCuenta = Double.parseDouble(EnviosDiferenciasFaltanteStringCargoEnCuenta);
	     	System.out.println(EnviosDiferenciasFaltanteDoubleCargoEnCuenta);
	     	Assert.assertEquals(EnviosDiferenciasFaltanteDoubleCargoEnCuenta, (EnviosDiferenciasFaltanteDouble + 600), 0.000001);
   	     	
	        File archivo2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        	FileUtils.copyFile(archivo2, new File(captura2));
	        
	        //Saldamos la diferencia con Contra Gastos------------------------------------------------------------------------------------------------------
	        	
        	Set<String> handle2 = driver.getWindowHandles();
		    
		    String PestañaCentral3 = (String) handle2.toArray()[handle2.size() - 2];
		    driver.switchTo().window(PestañaCentral3);
		    
		    WebElement SeleccionarDiferencia2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1011']")));
	        SeleccionarDiferencia2.click();
	        WebElement BotonSaldar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saldar-btnInnerEl")));
	        BotonSaldar2.click(); 	 Thread.sleep(secDelay);

	        WebElement DesplegarModalidad2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadRegularizacion-trigger-picker")));
	        DesplegarModalidad2.click();
	        WebElement SeleccionarModalidad2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Modalidad2)));
	        SeleccionarModalidad2.click();
	        WebElement MontoSaldar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("montoSaldarRegularizacion-inputEl")));
	        MontoSaldar2.sendKeys("100");
	        WebElement Descripción2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descripcionRegularizacion-inputEl")));
	        Descripción2.sendKeys("prueba");
	        WebElement NroCuenta2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nCuentaCuen-inputEl")));
	        NroCuenta2.sendKeys("02147850");
	        WebElement NombreCuenta2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreCuentaCuen-inputEl")));
	        NombreCuenta2.sendKeys("Prueba Automatizada");	              	        
	        WebElement PresionarIncluir2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirRegularizacionCuen-btnWrap")));
	        PresionarIncluir2.click();	  Thread.sleep(secDelay);     

	        WebElement PresionarAceptar2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarReg-btnWrap")));
	        PresionarAceptar2.click();     Thread.sleep(secDelay);
	        
    		WebElement InformacionDiferenciaSaldada2 = driver.findElement(By.id("messagebox-1001-msg"));
    		String ObtenerMensajeDiferencia2 = InformacionDiferenciaSaldada2.getText().trim();
    		System.out.println(ObtenerMensajeDiferencia2);
    		String ExpectativaTextoDiferencia2 = "Operación exitosa,";

    		Assert.assertEquals(ObtenerMensajeDiferencia2, ExpectativaTextoDiferencia2);
	        
	        WebElement ConfirmarOperacion2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	        ConfirmarOperacion2.click(); 	 Thread.sleep(secDelay);
	        
	        //Validamos el Cierre de Unidades con Contra Gastos--------------------------------------------------------------------------------------
	        
	        js.executeScript("window.open(arguments[0]);", Constantes.URL_CENTRAL_CIERRE_DE_UNIDADES);	  
		    Set<String> handles4 = driver.getWindowHandles();
		    String PestañaCentral4 = (String) handles4.toArray()[handles4.size() - 1];
		    driver.switchTo().window(PestañaCentral4);
	        
	        WebElement DesplegarTipoUnidadCierre_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen47")));
	        DesplegarTipoUnidadCierre_Final2.click();
	        WebElement SeleccionarTipoUnidadCierre_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + TipoUnidadCierre + "']")));
	        SeleccionarTipoUnidadCierre_Final2.click();
	        WebElement DesplegarUnidadCierre_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen60")));
	        DesplegarUnidadCierre_Final2.click();
	        WebElement SeleccionarUnidadCierre_Fina2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + UnidadCierre + "']")));
	        SeleccionarUnidadCierre_Fina2.click();
	        WebElement DesplegarDivisaCierre_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen73")));
	        DesplegarDivisaCierre_Final2.click();
	        WebElement SeleccionarDivisaCierre_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+Constantes.DIVISA+"']")));
	        SeleccionarDivisaCierre_Final2.click();
	        WebElement ConsultarCierr_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen86")));
	        ConsultarCierr_Final2.click();
	        WebElement SeleccionarDía_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='sameMonth x-unselectable'][text()='"+  DiaActualString +"']")));
	        SeleccionarDía_Final2.click();
	        WebElement CerrarDía_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCerrarDia")));
	        CerrarDía_Final2.click();
	        WebElement MensajeConfirmacion_Final2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1069")));
	        MensajeConfirmacion_Final2.click(); 	   Thread.sleep(secDelay);
	        
	        WebElement EnviosEntradaContraGastos = driver.findElement(By.id("enviosEntradaCierre"));
    		String EnvioEntradaStringContraGastos = EnviosEntradaContraGastos.getAttribute("value");
    		EnvioEntradaStringContraGastos = EnvioEntradaStringContraGastos.replace("." , "").replace("," , ".");
    		double EnvioEntradaDoubleContraGastos = Double.parseDouble(EnvioEntradaStringContraGastos);
   	     	System.out.println(EnvioEntradaDoubleContraGastos);
   	     	Assert.assertEquals(EnvioEntradaDoubleContraGastos, (EnvioEntradaDouble + 1200), 0.000001);
   	     	
   	     	WebElement EnviosDiferenciasFaltanteCOntraGastos = driver.findElement(By.id("enviosDifSalidaCierre"));
	   	    String EnviosDiferenciasFaltanteStringContraGastos = EnviosDiferenciasFaltanteCOntraGastos.getAttribute("value");
	   	    EnviosDiferenciasFaltanteStringContraGastos = EnviosDiferenciasFaltanteStringContraGastos.replace("." , "").replace("," , ".");
	   	 	double EnviosDiferenciasFaltanteDoubleContraGastos = Double.parseDouble(EnviosDiferenciasFaltanteStringContraGastos);
	     	System.out.println(EnviosDiferenciasFaltanteDoubleContraGastos);
	     	Assert.assertEquals(EnviosDiferenciasFaltanteDoubleContraGastos, (EnviosDiferenciasFaltanteDouble + 600), 0.000001);

	        File archivo3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(archivo3, new File(captura3));
        	
        	//Saldamos la diferencia con Contra Castigo--------------------------------------------------------------------------------------------
        	
        	Set<String> handle3 = driver.getWindowHandles();
		    String PestañaCentral5 = (String) handle3.toArray()[handle3.size() - 3];
		    driver.switchTo().window(PestañaCentral5);
		    
		    WebElement SeleccionarDiferencia3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1011']")));
	        SeleccionarDiferencia3.click();
	        WebElement BotonSaldar3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saldar-btnInnerEl")));
	        BotonSaldar3.click(); 	 Thread.sleep(secDelay);

	        WebElement DesplegarModalidad3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadRegularizacion-trigger-picker")));
	        DesplegarModalidad3.click();
	        WebElement SeleccionarModalidad3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Modalidad3)));
	        SeleccionarModalidad3.click();
	        WebElement MontoSaldar3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("montoSaldarRegularizacion-inputEl")));
	        MontoSaldar3.sendKeys("100");
	        WebElement Descripción3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descripcionRegularizacion-inputEl")));
	        Descripción3.sendKeys("prueba");
	        WebElement NroCuenta3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nCuentaCuen-inputEl")));
	        NroCuenta3.sendKeys("02147850");
	        WebElement NombreCuenta3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreCuentaCuen-inputEl")));
	        NombreCuenta3.sendKeys("Prueba Automatizada");	              	        
	        WebElement PresionarIncluir3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirRegularizacionCuen-btnWrap")));
	        PresionarIncluir3.click();	  Thread.sleep(secDelay);     

	        WebElement PresionarAceptar3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarReg-btnWrap")));
	        PresionarAceptar3.click();     Thread.sleep(secDelay);
	        
    		WebElement InformacionDiferenciaSaldada3 = driver.findElement(By.id("messagebox-1001-msg"));
    		String ObtenerMensajeDiferencia3 = InformacionDiferenciaSaldada3.getText().trim();
    		System.out.println(ObtenerMensajeDiferencia3);
    		String ExpectativaTextoDiferencia3 = "Operación exitosa,";
    		Assert.assertEquals(ObtenerMensajeDiferencia3, ExpectativaTextoDiferencia3);
	        
	        WebElement ConfirmarOperacion3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	        ConfirmarOperacion3.click(); 	 Thread.sleep(secDelay);
	        
	        //Validamos el Cierre de Unidades con Contra Castigo------------------------------------------------------------------------------------------------
	        
	        js.executeScript("window.open(arguments[0]);", Constantes.URL_CENTRAL_CIERRE_DE_UNIDADES);	  
		    Set<String> handles5 = driver.getWindowHandles();
		    String PestañaCentral6 = (String) handles5.toArray()[handles5.size() - 1];
		    driver.switchTo().window(PestañaCentral6);
	        
	        WebElement DesplegarTipoUnidadCierre_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen47")));
	        DesplegarTipoUnidadCierre_Final3.click();
	        WebElement SeleccionarTipoUnidadCierre_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + TipoUnidadCierre + "']")));
	        SeleccionarTipoUnidadCierre_Final3.click();
	        WebElement DesplegarUnidadCierre_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen60")));
	        DesplegarUnidadCierre_Final3.click();
	        WebElement SeleccionarUnidadCierre_Fina3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + UnidadCierre + "']")));
	        SeleccionarUnidadCierre_Fina3.click();
	        WebElement DesplegarDivisaCierre_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen73")));
	        DesplegarDivisaCierre_Final3.click();
	        WebElement SeleccionarDivisaCierre_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+Constantes.DIVISA+"']")));
	        SeleccionarDivisaCierre_Final3.click();
	        WebElement ConsultarCierr_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen86")));
	        ConsultarCierr_Final3.click();
	        WebElement SeleccionarDía_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='sameMonth x-unselectable'][text()='"+  DiaActualString +"']")));
	        SeleccionarDía_Final3.click();
	        WebElement CerrarDía_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCerrarDia")));
	        CerrarDía_Final3.click();
	        WebElement MensajeConfirmacion_Final3 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1069")));
	        MensajeConfirmacion_Final3.click(); 	   Thread.sleep(secDelay);
	        
	        WebElement EnviosEntradaContraCastigo = driver.findElement(By.id("enviosEntradaCierre"));
    		String EnvioEntradaStringContraCastigo = EnviosEntradaContraCastigo.getAttribute("value");
    		EnvioEntradaStringContraCastigo = EnvioEntradaStringContraCastigo.replace("." , "").replace("," , ".");
    		double EnvioEntradaDoubleContraCastigo = Double.parseDouble(EnvioEntradaStringContraCastigo);
   	     	System.out.println(EnvioEntradaDoubleContraCastigo);
   	     	Assert.assertEquals(EnvioEntradaDoubleContraCastigo, (EnvioEntradaDouble + 1200), 0.000001);
   	     	
   	     	WebElement EnviosDiferenciasFaltanteContraCastigo = driver.findElement(By.id("enviosDifSalidaCierre"));
	   	    String EnviosDiferenciasFaltanteStringContraCastigo = EnviosDiferenciasFaltanteContraCastigo.getAttribute("value");
	   	    EnviosDiferenciasFaltanteStringContraCastigo = EnviosDiferenciasFaltanteStringContraCastigo.replace("." , "").replace("," , ".");
	   	 	double EnviosDiferenciasFaltanteDoubleContraCastigo = Double.parseDouble(EnviosDiferenciasFaltanteStringContraCastigo);
	     	System.out.println(EnviosDiferenciasFaltanteDoubleContraCastigo);
	     	Assert.assertEquals(EnviosDiferenciasFaltanteDoubleContraCastigo, (EnviosDiferenciasFaltanteDouble + 600), 0.000001);

	        File archivo4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(archivo4, new File(captura4));
        	
        	//Saldamos la diferencia con Reposicion de Efectivo-----------------------------------------------------------------------------------------
        	
        	Set<String> handle4 = driver.getWindowHandles();
		    String PestañaCentral7 = (String) handle4.toArray()[handle4.size() - 4];
		    driver.switchTo().window(PestañaCentral7);
		    
		    WebElement SeleccionarDiferencia4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1011']")));
	        SeleccionarDiferencia4.click();
	        WebElement BotonSaldar4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saldar-btnInnerEl")));
	        BotonSaldar4.click(); 	 Thread.sleep(secDelay);

	        WebElement DesplegarModalidad4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadRegularizacion-trigger-picker")));
	        DesplegarModalidad4.click();
	        WebElement SeleccionarModalidad4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Modalidad4)));
	        SeleccionarModalidad4.click();
	        WebElement MontoSaldar4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("montoSaldarRegularizacion-inputEl")));
	        MontoSaldar4.sendKeys("100");
	        WebElement Descripción4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descripcionRegularizacion-inputEl")));
	        Descripción4.sendKeys("prueba");
	        
	        WebElement Denominacion = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("denominacionEfec-trigger-picker")));
	        Denominacion.click();
	        List<WebElement> options2 = driver.findElements(By.xpath("//ul[@class='x-list-plain']/li"));
	        for (WebElement option : options2) {
	            if (option.getText().equals(Constantes.DENOMINACION_DIFERENCIA)) {
	                option.click();
	                break;
	            
	                }
	            }		Thread.sleep(secDelay);
	            
	        WebElement Cantidad = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cantidadEfec-inputEl")));
	        Cantidad.sendKeys("1");
	        
	        WebElement Clasificacion = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("clasificacionEfec-trigger-picker")));
	        Clasificacion.click();
	        List<WebElement> options3 = driver.findElements(By.xpath("//ul[@class='x-list-plain']/li"));
	        for (WebElement option : options3) {
	            if (option.getText().equals(Constantes.APTOS)) {
	                option.click();
	                break;
	                }
	            }		Thread.sleep(secDelay);
	        
	        WebElement PresionarIncluir4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirRegularizacionEfec-btnInnerEl")));
	        PresionarIncluir4.click();	  Thread.sleep(secDelay);     

	        WebElement PresionarAceptar4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarReg-btnInnerEl")));
	        PresionarAceptar4.click();     Thread.sleep(secDelay);
	        
    		WebElement InformacionDiferenciaSaldada4 = driver.findElement(By.id("messagebox-1001-msg"));
    		String ObtenerMensajeDiferencia4 = InformacionDiferenciaSaldada4.getText().trim();
    		System.out.println(ObtenerMensajeDiferencia4);
    		String ExpectativaTextoDiferencia4 = "Operación exitosa,";

    		Assert.assertEquals(ObtenerMensajeDiferencia4, ExpectativaTextoDiferencia4);
	        
	        WebElement ConfirmarOperacion4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	        ConfirmarOperacion4.click(); 	 Thread.sleep(secDelay);
	        
	        //Validamos el Cierre de Unidades con Reposicion de Efectivo-----------------------------------------------------------------------------
	        
	        js.executeScript("window.open(arguments[0]);", Constantes.URL_CENTRAL_CIERRE_DE_UNIDADES);	  
		    Set<String> handles6 = driver.getWindowHandles();
		    String PestañaCentral8 = (String) handles6.toArray()[handles6.size() - 1];
		    driver.switchTo().window(PestañaCentral8);
	        
	        WebElement DesplegarTipoUnidadCierre_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen47")));
	        DesplegarTipoUnidadCierre_Final4.click();
	        WebElement SeleccionarTipoUnidadCierre_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + TipoUnidadCierre + "']")));
	        SeleccionarTipoUnidadCierre_Final4.click();
	        WebElement DesplegarUnidadCierre_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen60")));
	        DesplegarUnidadCierre_Final4.click();
	        WebElement SeleccionarUnidadCierre_Fina4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + UnidadCierre + "']")));
	        SeleccionarUnidadCierre_Fina4.click();
	        WebElement DesplegarDivisaCierre_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen73")));
	        DesplegarDivisaCierre_Final4.click();
	        WebElement SeleccionarDivisaCierre_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+Constantes.DIVISA+"']")));
	        SeleccionarDivisaCierre_Final4.click();
	        WebElement ConsultarCierr_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen86")));
	        ConsultarCierr_Final4.click();
	        WebElement SeleccionarDía_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='sameMonth x-unselectable'][text()='"+  DiaActualString +"']")));
	        SeleccionarDía_Final4.click();
	        WebElement CerrarDía_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCerrarDia")));
	        CerrarDía_Final4.click();
	        WebElement MensajeConfirmacion_Final4 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1069")));
	        MensajeConfirmacion_Final4.click(); 	   Thread.sleep(secDelay);
	        
	        WebElement EnviosEntradaReposicionDeEfectivo = driver.findElement(By.id("enviosEntradaCierre"));
    		String EnvioEntradaStringReposicionDeEfectivo = EnviosEntradaReposicionDeEfectivo.getAttribute("value");
    		EnvioEntradaStringReposicionDeEfectivo = EnvioEntradaStringReposicionDeEfectivo.replace("." , "").replace("," , ".");
    		double EnvioEntradaDoubleReposicionDeEfectivo = Double.parseDouble(EnvioEntradaStringReposicionDeEfectivo);
   	     	System.out.println(EnvioEntradaDoubleReposicionDeEfectivo);
   	     	Assert.assertEquals(EnvioEntradaDoubleReposicionDeEfectivo, (EnvioEntradaDouble + 1200), 0.000001);
   	     	
   	     	WebElement EnviosDiferenciasFaltanteReposicionDeEfectivo = driver.findElement(By.id("enviosDifSalidaCierre"));
	   	    String EnviosDiferenciasFaltanteStringReposicionDeEfectivo = EnviosDiferenciasFaltanteReposicionDeEfectivo.getAttribute("value");
	   	    EnviosDiferenciasFaltanteStringReposicionDeEfectivo = EnviosDiferenciasFaltanteStringReposicionDeEfectivo.replace("." , "").replace("," , ".");
	   	 	double EnviosDiferenciasFaltanteDoubleReposicionDeEfectivo = Double.parseDouble(EnviosDiferenciasFaltanteStringReposicionDeEfectivo);
	     	System.out.println(EnviosDiferenciasFaltanteDoubleReposicionDeEfectivo);
	     	Assert.assertEquals(EnviosDiferenciasFaltanteDoubleReposicionDeEfectivo, (EnviosDiferenciasFaltanteDouble + 600), 0.000001);
	     	
	     	WebElement EnviosReposicionDeEfectivoFaltanteRegularizacion = driver.findElement(By.id("regularizacionEntradaCierre"));
	   	    String EnviosReposicionDeEfectivoFaltanteStringRegularizacion = EnviosReposicionDeEfectivoFaltanteRegularizacion.getAttribute("value");
	   	    EnviosReposicionDeEfectivoFaltanteStringRegularizacion = EnviosReposicionDeEfectivoFaltanteStringRegularizacion.replace("." , "").replace("," , ".");
	   	 	double EnviosReposicionDeEfectivoFaltanteDoubleRegularizacion = Double.parseDouble(EnviosReposicionDeEfectivoFaltanteStringRegularizacion);
	     	System.out.println(EnviosReposicionDeEfectivoFaltanteDoubleRegularizacion);
	     	Assert.assertEquals(EnviosReposicionDeEfectivoFaltanteDoubleRegularizacion, (EnviosRegularizacionesFaltanteDouble + 100), 0.000001);

	        File archivo5 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(archivo5, new File(captura5));
        	
        	//Saldamos la diferencia con Cheque mismo Banco--------------------------------------------------------------------------------------------------
        	
        	Set<String> handle6 = driver.getWindowHandles();
		    String PestañaCentral9 = (String) handle6.toArray()[handle6.size() - 5];
		    driver.switchTo().window(PestañaCentral9);
		    
		    WebElement SeleccionarDiferencia5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1011']")));
	        SeleccionarDiferencia5.click();
	        WebElement BotonSaldar5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saldar-btnInnerEl")));
	        BotonSaldar5.click(); 	 Thread.sleep(secDelay);

	        WebElement DesplegarModalidad5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadRegularizacion-trigger-picker")));
	        DesplegarModalidad5.click();
	        WebElement SeleccionarModalidad5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Modalidad5)));
	        SeleccionarModalidad5.click();
	        WebElement MontoSaldar5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("montoSaldarRegularizacion-inputEl")));
	        MontoSaldar5.sendKeys("100");
	        WebElement Descripción5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descripcionRegularizacion-inputEl")));
	        Descripción5.sendKeys("prueba");
	        
	        WebElement Banco1 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("banco-trigger-picker")));
	        Banco1.click();
	        List<WebElement> options4 = driver.findElements(By.xpath("//ul[@class='x-list-plain']/li"));
	        for (WebElement option : options4) {
	            if (option.getText().equals(Banco)) {
	                option.click();
	                break;
	            
	                }
	            }		Thread.sleep(secDelay);
	            
	        WebElement NroDeCuenta = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nCuenta-inputEl")));
	        NroDeCuenta.sendKeys("1000");
	        WebElement Serial = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serial-inputEl")));
	        Serial.sendKeys("1000");
	        WebElement NombreDeLaCuenta = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreCuenta-inputEl")));
	        NombreDeLaCuenta.sendKeys("1000");  Thread.sleep(secDelay); 
	        WebElement PresionarIncluir5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirRegularizacion-btnInnerEl")));
	        PresionarIncluir5.click();	  Thread.sleep(secDelay);     
	        WebElement PresionarAceptar5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarReg-btnInnerEl")));
	        PresionarAceptar5.click();     Thread.sleep(secDelay);
	       
    		WebElement InformacionDiferenciaSaldada5 = driver.findElement(By.id("messagebox-1001-msg"));
    		String ObtenerMensajeDiferencia5 = InformacionDiferenciaSaldada5.getText().trim();
    		System.out.println(ObtenerMensajeDiferencia5);
    		String ExpectativaTextoDiferencia5 = "Operación exitosa,";

    		Assert.assertEquals(ObtenerMensajeDiferencia5, ExpectativaTextoDiferencia5);
	        
	        WebElement ConfirmarOperacion5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	        ConfirmarOperacion5.click(); 	 Thread.sleep(secDelay);
	        
	        //Validamos el Cierre de Unidades con Saldo Mismos Bancos-----------------------------------------------------------------------------------
	        
	        js.executeScript("window.open(arguments[0]);", Constantes.URL_CENTRAL_CIERRE_DE_UNIDADES);	  
		    Set<String> handles7 = driver.getWindowHandles();
		    String PestañaCentral10 = (String) handles7.toArray()[handles7.size() - 1];
		    driver.switchTo().window(PestañaCentral10);
	        
	        WebElement DesplegarTipoUnidadCierre_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen47")));
	        DesplegarTipoUnidadCierre_Final5.click();
	        WebElement SeleccionarTipoUnidadCierre_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + TipoUnidadCierre + "']")));
	        SeleccionarTipoUnidadCierre_Final5.click();
	        WebElement DesplegarUnidadCierre_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen60")));
	        DesplegarUnidadCierre_Final5.click();
	        WebElement SeleccionarUnidadCierre_Fina5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + UnidadCierre + "']")));
	        SeleccionarUnidadCierre_Fina5.click();
	        WebElement DesplegarDivisaCierre_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen73")));
	        DesplegarDivisaCierre_Final5.click();
	        WebElement SeleccionarDivisaCierre_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+Constantes.DIVISA+"']")));
	        SeleccionarDivisaCierre_Final5.click();
	        WebElement ConsultarCierr_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen86")));
	        ConsultarCierr_Final5.click();
	        WebElement SeleccionarDía_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='sameMonth x-unselectable'][text()='"+  DiaActualString +"']")));
	        SeleccionarDía_Final5.click();
	        WebElement CerrarDía_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCerrarDia")));
	        CerrarDía_Final5.click();
	        WebElement MensajeConfirmacion_Final5 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1069")));
	        MensajeConfirmacion_Final5.click(); 	   Thread.sleep(secDelay);
	        
	        WebElement EnviosEntradaMismosBancos = driver.findElement(By.id("enviosEntradaCierre"));
    		String EnvioEntradaStringMismosBancos = EnviosEntradaMismosBancos.getAttribute("value");
    		EnvioEntradaStringMismosBancos = EnvioEntradaStringMismosBancos.replace("." , "").replace("," , ".");
    		double EnvioEntradaDoubleMismosBancos = Double.parseDouble(EnvioEntradaStringMismosBancos);
   	     	System.out.println(EnvioEntradaDoubleMismosBancos);
   	     	Assert.assertEquals(EnvioEntradaDoubleMismosBancos, (EnvioEntradaDouble + 1200), 0.000001);
   	     	
   	     	WebElement EnviosDiferenciasFaltanteMismosBancos = driver.findElement(By.id("enviosDifSalidaCierre"));
	   	    String EnviosDiferenciasFaltanteStringMismosBancos = EnviosDiferenciasFaltanteMismosBancos.getAttribute("value");
	   	    EnviosDiferenciasFaltanteStringMismosBancos = EnviosDiferenciasFaltanteStringMismosBancos.replace("." , "").replace("," , ".");
	   	 	double EnviosDiferenciasFaltanteDoubleMismosBancos = Double.parseDouble(EnviosDiferenciasFaltanteStringMismosBancos);
	     	System.out.println(EnviosDiferenciasFaltanteDoubleMismosBancos);
	     	Assert.assertEquals(EnviosDiferenciasFaltanteDoubleMismosBancos, (EnviosDiferenciasFaltanteDouble + 600), 0.000001);

	        File archivo6 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(archivo6, new File(captura6));
        	
        	//Saldamos la diferencia con Cheque de otros Bancos---------------------------------------------------------------------------------------------
        	
        	Set<String> handle7 = driver.getWindowHandles();
		    String PestañaCentral11 = (String) handle7.toArray()[handle7.size() - 6];
		    driver.switchTo().window(PestañaCentral11);
		    
		    WebElement SeleccionarDiferencia6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td[data-columnid='gridcolumn-1011']")));
	        SeleccionarDiferencia6.click();
	        WebElement BotonSaldar6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("saldar-btnInnerEl")));
	        BotonSaldar6.click(); 	 Thread.sleep(secDelay);

	        WebElement DesplegarModalidad6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modalidadRegularizacion-trigger-picker")));
	        DesplegarModalidad6.click();
	        WebElement SeleccionarModalidad6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Modalidad6)));
	        SeleccionarModalidad6.click();
	        WebElement MontoSaldar6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("montoSaldarRegularizacion-inputEl")));
	        MontoSaldar6.sendKeys("100");
	        WebElement Descripción6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("descripcionRegularizacion-inputEl")));
	        Descripción6.sendKeys("prueba");
	        
	        WebElement Banco2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("banco-trigger-picker")));
	        Banco2.click();
	        List<WebElement> options5 = driver.findElements(By.xpath("//ul[@class='x-list-plain']/li"));
	        for (WebElement option : options5) {
	            if (option.getText().equals(Banco)) {
	                option.click();
	                break;
	            
	                }
	            }		Thread.sleep(secDelay);
	            
	        WebElement NroDeCuenta2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nCuenta-inputEl")));
	        NroDeCuenta2.sendKeys("1000");
	        WebElement Serial2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("serial-inputEl")));
	        Serial2.sendKeys("1000");
	        WebElement NombreDeLaCuenta2 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nombreCuenta-inputEl")));
	        NombreDeLaCuenta2.sendKeys("1000");  Thread.sleep(secDelay); 
	        WebElement PresionarIncluir6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("incluirRegularizacion-btnInnerEl")));
	        PresionarIncluir6.click();	  Thread.sleep(secDelay);     
	        WebElement PresionarAceptar6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aceptarReg-btnInnerEl")));
	        PresionarAceptar6.click();     Thread.sleep(secDelay);
	       
    		WebElement InformacionDiferenciaSaldada6 = driver.findElement(By.id("messagebox-1001-msg"));
    		String ObtenerMensajeDiferencia6 = InformacionDiferenciaSaldada6.getText().trim();
    		System.out.println(ObtenerMensajeDiferencia6);
    		String ExpectativaTextoDiferencia6 = "Operación exitosa,";

    		Assert.assertEquals(ObtenerMensajeDiferencia6, ExpectativaTextoDiferencia6);
	        
	        WebElement ConfirmarOperacion6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("button-1005-btnInnerEl")));
	        ConfirmarOperacion6.click(); 	 Thread.sleep(secDelay);
	        
	        //Validamos el Cierre de Unidades con Cheque de otros Bancos-------------------------------------------------------------------------------------------
	        
	        js.executeScript("window.open(arguments[0]);", Constantes.URL_CENTRAL_CIERRE_DE_UNIDADES);	  
		    Set<String> handles8 = driver.getWindowHandles();
		    String PestañaCentral12 = (String) handles8.toArray()[handles8.size() - 1];
		    driver.switchTo().window(PestañaCentral12);
	        
	        WebElement DesplegarTipoUnidadCierre_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen47")));
	        DesplegarTipoUnidadCierre_Final6.click();
	        WebElement SeleccionarTipoUnidadCierre_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + TipoUnidadCierre + "']")));
	        SeleccionarTipoUnidadCierre_Final6.click();
	        WebElement DesplegarUnidadCierre_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen60")));
	        DesplegarUnidadCierre_Final6.click();
	        WebElement SeleccionarUnidadCierre_Fina6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='" + UnidadCierre + "']")));
	        SeleccionarUnidadCierre_Fina6.click();
	        WebElement DesplegarDivisaCierre_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen73")));
	        DesplegarDivisaCierre_Final6.click();
	        WebElement SeleccionarDivisaCierre_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+Constantes.DIVISA+"']")));
	        SeleccionarDivisaCierre_Final6.click();
	        WebElement ConsultarCierr_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-gen86")));
	        ConsultarCierr_Final6.click();
	        WebElement SeleccionarDía_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='sameMonth x-unselectable'][text()='"+  DiaActualString +"']")));
	        SeleccionarDía_Final6.click();
	        WebElement CerrarDía_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("opcionCerrarDia")));
	        CerrarDía_Final6.click();
	        WebElement MensajeConfirmacion_Final6 = ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ext-comp-1069")));
	        MensajeConfirmacion_Final6.click(); 	   Thread.sleep(secDelay);
	        
	        WebElement EnviosEntradaOtrosBancos = driver.findElement(By.id("enviosEntradaCierre"));
    		String EnvioEntradaStringOtrosBancos = EnviosEntradaOtrosBancos.getAttribute("value");
    		EnvioEntradaStringOtrosBancos = EnvioEntradaStringOtrosBancos.replace("." , "").replace("," , ".");
    		double EnvioEntradaDoubleOtrosBancos = Double.parseDouble(EnvioEntradaStringOtrosBancos);
   	     	System.out.println(EnvioEntradaDoubleOtrosBancos);
   	     	Assert.assertEquals(EnvioEntradaDoubleOtrosBancos, (EnvioEntradaDouble + 1200), 0.000001);
   	     	
   	     	WebElement EnviosDiferenciasFaltanteOtrosBancos = driver.findElement(By.id("enviosDifSalidaCierre"));
	   	    String EnviosDiferenciasFaltanteStringOtrosBancos = EnviosDiferenciasFaltanteOtrosBancos.getAttribute("value");
	   	    EnviosDiferenciasFaltanteStringOtrosBancos = EnviosDiferenciasFaltanteStringOtrosBancos.replace("." , "").replace("," , ".");
	   	 	double EnviosDiferenciasFaltanteDoubleOtrosBancos = Double.parseDouble(EnviosDiferenciasFaltanteStringOtrosBancos);
	     	System.out.println(EnviosDiferenciasFaltanteDoubleOtrosBancos);
	     	Assert.assertEquals(EnviosDiferenciasFaltanteDoubleOtrosBancos, (EnviosDiferenciasFaltanteDouble + 600), 0.000001);

	        File archivo7 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        	FileUtils.copyFile(archivo7, new File(captura7));       	
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