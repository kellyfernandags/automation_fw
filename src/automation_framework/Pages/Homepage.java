package automation_framework.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage {
	
	private WebDriver webdriver;
	private WebElement textbox_username;
	private WebElement textbox_password;
	private WebElement button_submit;
	private WebElement login_failure_msg;
	
	public Homepage(WebDriver webdriver) {
		this.webdriver = webdriver;
	}

	public WebElement getTextbox_username() {
		String name = "username";
		textbox_username = webdriver.findElement(By.name(name));
		return textbox_username;
	}

	public WebElement getTextbox_password() {
		String name = "password";
		textbox_password = webdriver.findElement(By.name(name));
		return textbox_password;
	}

	public WebElement getButton_submit() {
		String id = "btnLogin";
		button_submit = webdriver.findElement(By.id(id));
		return button_submit;
	}	
	
	public WebElement getLogin_failure_msgt() {
		String xpath = "//div[@class='alert alert-danger mt-3']";
		try {
			login_failure_msg = webdriver.findElement(By.xpath(xpath));
		}
		catch(Exception e){ 
			login_failure_msg = null;
			System.out.println ("No error in login");
		}
		return login_failure_msg;
	}	
	
	public void fill_textbox_username(String username) {
		getTextbox_username().sendKeys(username);
	}

	public void fill_textbox_password(String password) {
		getTextbox_password().sendKeys(password);
	}

	public void click_button_submit() {
		getButton_submit().click();
	}
	


}
