package org.adactin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookingConfirm extends BaseClass{
public BookingConfirm() {
	PageFactory.initElements(driver, this);
	
}
@FindBy(id="order_no")
private WebElement order_no;
public WebElement getOrder_no() {
	return order_no;
}
@FindBy(id="my_itinerary")
private WebElement my_itinerary;
public WebElement getMy_itinerary() {
	return my_itinerary;
}


}
