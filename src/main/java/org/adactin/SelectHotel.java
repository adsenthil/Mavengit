package org.adactin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectHotel extends BaseClass{
public SelectHotel() {
	PageFactory.initElements(driver, this);
	
}
@FindBy(id="radiobutton_0")
private WebElement radiobutton_0;
public WebElement getRadiobutton_0() {
	return radiobutton_0;
}
@FindBy(id="continue")
private WebElement continue1;
public WebElement continue1() {
	return continue1;
}
}