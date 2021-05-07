package neves.jesus.felipe.wesley;

import static org.junit.Assert.assertThat;

import java.util.concurrent.TimeUnit;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	@Test
	public void deveSalvarTarefaComSucesso() throws InterruptedException {
		WebDriver driver = acessarAplicacao();
		try {
			//		Actions action = new Actions(driver);
			WebElement addTodoButton = driver.findElement(By.id("addTodo"));
			Thread.sleep(2000l);
			addTodoButton.click();
			//		action.clickAndHold(addTodoButton).build().perform();
			driver.findElement(By.id("task")).sendKeys("Task automática2");
			driver.findElement(By.id("dueDate")).sendKeys("07/05/2021");
			Thread.sleep(2000l);
			driver.findElement(By.id("saveButton")).submit();
			String mensagemRetornada = driver.findElement(By.id("message")).getText();
			assertThat("Success!", CoreMatchers.is(mensagemRetornada));
		} finally {
			driver.quit();
		} 
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws InterruptedException {
		WebDriver driver = acessarAplicacao();
		try {
			//		Actions action = new Actions(driver);
			WebElement addTodoButton = driver.findElement(By.id("addTodo"));
			Thread.sleep(2000l);
			addTodoButton.click();
			//		action.clickAndHold(addTodoButton).build().perform();
			driver.findElement(By.id("dueDate")).sendKeys("07/05/2021");
			Thread.sleep(2000l);
			driver.findElement(By.id("saveButton")).submit();
			String mensagemRetornada = driver.findElement(By.id("message")).getText();
			assertThat("Fill the task description", CoreMatchers.is(mensagemRetornada));
		} finally {
			driver.quit();
		} 
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws InterruptedException {
		WebDriver driver = acessarAplicacao();
		try {
			//		Actions action = new Actions(driver);
			WebElement addTodoButton = driver.findElement(By.id("addTodo"));
			Thread.sleep(2000l);
			addTodoButton.click();
			//		action.clickAndHold(addTodoButton).build().perform();
			driver.findElement(By.id("task")).sendKeys("Task automática2");
			Thread.sleep(2000l);
			driver.findElement(By.id("saveButton")).submit();
			String mensagemRetornada = driver.findElement(By.id("message")).getText();
			assertThat("Fill the due date", CoreMatchers.is(mensagemRetornada));
		} finally {
			driver.quit();
		} 
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws InterruptedException {
		WebDriver driver = acessarAplicacao();
		try {
			//		Actions action = new Actions(driver);
			WebElement addTodoButton = driver.findElement(By.id("addTodo"));
			Thread.sleep(2000l);
			addTodoButton.click();
			//		action.clickAndHold(addTodoButton).build().perform();
			driver.findElement(By.id("task")).sendKeys("Task automática2");
			driver.findElement(By.id("dueDate")).sendKeys("07/05/2010");
			Thread.sleep(2000l);
			driver.findElement(By.id("saveButton")).submit();
			String mensagemRetornada = driver.findElement(By.id("message")).getText();
			assertThat("Due date must not be in past", CoreMatchers.is(mensagemRetornada));
		} finally {
			driver.quit();
		} 
	}

	private WebDriver acessarAplicacao() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	} 	

}
