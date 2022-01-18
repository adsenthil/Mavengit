package org.adactin;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends BaseClass{
public Login() {
	PageFactory.initElements(driver, this);
	
}
@FindBy(id="username")
private WebElement userName;
@FindBy(name="password")
private WebElement passWord;
@FindBy(name="login")
private WebElement login;

public WebElement getuserName() {
	return userName;
}
public WebElement getpassWord() {
	return passWord;
}
public WebElement getlogin() {
	return login;
}


}
