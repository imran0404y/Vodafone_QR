package Libraries;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Common extends Driver {

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: AccountSearch
	 * Use 					: Account Search
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public void AccountSearch(String Account) {
		try {
			int Row_Count, Row = 2, Col;
			Driver.Continue.set(true);
			Browser.WebLink.waittillvisible("VQ_Account");
			Result.takescreenshot("Navigating to Accounts");
			waitforload();
			Browser.WebLink.click("VQ_Account");
			waitforload();
			Result.takescreenshot("Account Navigation done Successfully");
			Link_Select("My Accounts");
			scroll("Account_Search", "WebButton");
			Browser.WebButton.click("Account_Search");
			Col = Select_Cell("Account", "Account #");
			Browser.WebTable.SetData("Account", Row, Col, "Account_Number", Account);
			Col = Select_Cell("Account", "Name");
			Row_Count = Browser.WebTable.getRowCount("Account");
			if (Row_Count < 2) {
				Driver.Continue.set(false);
				System.out.println("No Records Matched in Account Table");
				System.exit(0);
			}
			Result.takescreenshot("");
			Browser.WebTable.clickL("Account", Row, Col);
			waitforload();
			// scroll(objname, ObjTyp);
			Browser.WebLink.click("Acc_Portal");
			Result.takescreenshot("Account Search Completed");
		} catch (Exception e) {
			System.out.println("Failed in Contact Search");
		}
	}

	public void ContactSearch(String LastName) {// ,String IDNumber){
		try {
			int Row = 2, ColL, Col;
			// Driver.Continue.set(true);
			// waitforload();
			waitforobj("VQ_Contact", "WebLink");
			Result.takescreenshot("Navigating to Contacts");
			// CO.waitmoreforload();
			Browser.WebLink.click("VQ_Contact");
			Browser.WebLink.waittillvisible("My_Contacts");
			Result.takescreenshot("Contact Navigation done Successfully");
			waitforobj("My_Contacts", "WebLink");
			Browser.WebLink.click("My_Contacts");
			waitforload();
			scroll("ContactSearch", "WebButton");
			Browser.WebButton.click("ContactSearch");
			ColL = Select_Cell("Contact", "Last_Name");
			Browser.WebTable.click("Contact", Row, ColL);
			Browser.WebTable.SetDataE("Contact", Row, ColL, "Last_Name", LastName);// ID_Number
			Col = Select_Cell("Contact", "ID_Number");
			Browser.WebTable.click("Contact", Row, Col);
			// Browser.WebTable.SetDataE("Contact", Row, Col, "ID_Number", IDNumber);
			Browser.WebButton.click("ContactGo");
			int Row_Count = Browser.WebTable.getRowCount("Contact");
			if (Row_Count < 2) {
				Driver.Continue.set(false);
				System.out.println("No Records Matched in Contact Table");
				System.exit(0);
			}
			Browser.WebTable.clickL("Contact", Row, ColL);
			isAlertExist();
		} catch (Exception e) {
			System.out.println("Failed in Contact Search");
		}
	}

	public void OrderSearch(String Orderid) {
		try {
			int Row = 2, Col;
			waitforobj("VQ_SalesOrder", "WebLink");
			Browser.WebLink.click("VQ_SalesOrder");
			Link_Select("My Sales Orders");
			Browser.WebButton.click("OrderSearch");
			Col = Select_Cell("SalesOrder", "Order #");
			Browser.WebTable.SetData("SalesOrder", Row, Col, "Order_Number", Orderid);
			int Row_Count = Browser.WebTable.getRowCount("SalesOrder");
			if (Row_Count < 2) {
				Driver.Continue.set(false);
				System.out.println("No Records Matched in SelesOrder Table");
				System.exit(0);
			}
			Browser.WebTable.click("SalesOrder", Row, Col + 1);
			Browser.WebTable.clickL("SalesOrder", Row, Col);
			Link_Select("Line Items");
			// Link_Select()
		} catch (Exception e) {
			System.out.println("Failed in Order Search");
		}

	}
	/*---------------------------------------------------------------------------------------------------------
	
	 * Method Name			: InstalledAssertChange
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public void InstalledAssertChange(String Text) {// need to be changed to
													// boolean
		try {
			if (Browser.WebButton.exist("Installed_Assert_Menu")) {
				scroll("Installed_Assert_Menu", "WebButton");
				Browser.WebButton.click("Installed_Assert_Menu");
			} else {
				scroll("Prod_Serv_Menu", "WebButton");
				Browser.WebButton.click("Prod_Serv_Menu");
			}

			String[] objprop = Utlities.FindObject("Menu_Selection", "WebButton");
			String cellXpath = objprop[0] + Text + "']";
			if (cDriver.get().findElement(By.xpath(cellXpath)).isDisplayed()) {
				WebElement scr1 = KeyWord.cDriver.get().findElement(By.xpath(cellXpath));
				((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
				KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
			} else
				Driver.Continue.set(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * if (True) To be conmpleted for the Status Stuff {} else {}
		 */
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: AlertExist
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public boolean AlertExist() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(cDriver.get(), 45);

		if (!(wait.until(ExpectedConditions.alertIsPresent()) == null)) {

			System.out.println((cDriver.get()).switchTo().alert().getText());
			Browser.alert.accept();
			Browser.Readystate();
			return true;

		}

		else {
			System.out.println("No Alert Exist");

			return false;
		}

	}

	public boolean isAlertExist() {

		try {
			WebDriverWait wait = new WebDriverWait(cDriver.get(), 25);
			if (!(wait.until(ExpectedConditions.alertIsPresent()) == null))
				System.out.println((cDriver.get()).switchTo().alert().getText());
			Browser.alert.accept();
			Browser.Readystate();
			return true;
		} catch (Exception e) {
			System.out.println("No Alert Exist");
			e.getMessage();
			return false;
		}
	}

	public static class WebTable {
		/*---------------------------------------------------------------------------------------------------------
		 * Method Name			: Selectcheck
		 * Use 					: Customizing the specific Plan is done
		 * Designed By			: Vinodhini
		 * Last Modified Date 	: 16-Feb-2017
		--------------------------------------------------------------------------------------------------------*/

		public void Selectcheck(String objname, int row, int col, int on_off) {
			try {
				String[] objprop = Utlities.FindObject(objname, "WebTable");
				String CellXpath;
				String cellXpath = objprop[0] + "//tr[" + row + "]" + "//td[" + col + "]";
				if (on_off == 1) {
					CellXpath = cellXpath + "//a[@class='ui-switch-on']";
				} else {
					CellXpath = cellXpath + "//a[@class='ui-switch-off']";
				}
				KeyWord.cDriver.get().findElement(By.xpath(CellXpath)).click();

			} catch (Exception e) {

			}
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: doubleClick
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public void doubleClick(WebElement element) {
		try {
			Actions action = new Actions(cDriver.get()).doubleClick(element);
			action.build().perform();

			System.out.println("Double clicked the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable " + e.getStackTrace());
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: GetName
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public String GetName(String Val) {

		String F = "First";
		String L = "Last";
		if (Val.equals(F)) {
			Dictionary<Integer, String> dict = new Hashtable<Integer, String>();

			// add elements in the hashtable

			dict.put(1, "Feroz");
			dict.put(2, "Gupta");
			dict.put(3, "Lauranz");
			dict.put(4, "Steve");
			dict.put(5, "Pancrias");
			dict.put(6, "Joshua");
			dict.put(7, "Louis");
			dict.put(8, "Collonel");
			dict.put(9, "Panckrias");
			dict.put(0, "Stuved");
			Random R = new Random();
			Object name = dict.get(R.nextInt(10));
			return (String) name;
		} else if (Val.equals(L)) {
			Dictionary<Integer, String> dict = new Hashtable<Integer, String>();
			// add elements in the hashtable
			dict.put(1, "Steven");
			dict.put(2, "Iris");
			dict.put(3, "Mark");
			dict.put(4, "Gabriel");
			dict.put(5, "James");
			dict.put(6, "Stephen");
			dict.put(7, "Jackob");
			dict.put(8, "Macbath");
			dict.put(9, "Moriss");
			dict.put(0, "Carris");
			Random R = new Random();
			Object name = dict.get(R.nextInt(10));
			return (String) name;
		}

		return "Vandee";
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: First
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public String First() {
		return GetName("First");
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Last
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public String Last() {
		return GetName("Last");
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: AName
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 16-Feb-2017
	--------------------------------------------------------------------------------------------------------*/

	public String AName() {
		String a, b, c;
		a = GetName("First");
		b = GetName("Last");
		c = a + "_" + b;
		return c;
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Assert_Search
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 7-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Assert_Search(String MSISDN, String Status, String Product) {
		try {
			waitforload();
			int Row = 2, Col, Col_Pro;
			String Prod = "Mobile Service Bundle";
			Browser.WebLink.waittillvisible("VQ_Assert");
			Browser.WebLink.click("VQ_Assert");
			Browser.WebLink.waittillvisible("Assert_Search");
			waitforload();
			scroll("Assert_Search", "WebLink");
			Browser.WebLink.click("Assert_Search");
			waitforload();

			// Installed_Assert
			Col = Select_Cell("Assert", "Service ID");
			Browser.WebTable.SetDataE("Assert", Row, Col, "Serial_Number", MSISDN);
			Col = Select_Cell("Assert", "Status");
			Browser.WebTable.SetDataE("Assert", Row, Col, "Status", Status);
			Col = Select_Cell("Assert", "Product");
			Browser.WebTable.SetDataE("Assert", Row, Col, "Product_Name", Product);
			Browser.WebButton.waitTillEnabled("Assert_Go");
			Browser.WebButton.click("Assert_Go");
			waitforload();
			Col = Select_Cell("Assert", "Account");
			int Assert_Row_Count = Browser.WebTable.getRowCount("Assert");
			if (Assert_Row_Count > 1)
				Browser.WebTable.clickL("Assert", Row, Col);
			else
				Driver.Continue.set(false);
			Browser.WebLink.waittillvisible("Acc_Portal");
			waitforload();
			Browser.WebLink.click("Acc_Portal");
			Browser.WebLink.waittillvisible("Inst_Assert_ShowMore");
			// Browser.WebLink.click("Inst_Assert_ShowMore");
			waitforload();
			int Inst_RowCount = Browser.WebTable.getRowCount("Installed_Assert");
			Col_Pro = Select_Cell("Installed_Assert", "Asset Description");
			Col = Select_Cell("Installed_Assert", "Service ID");
			for (int i = 1; i <= Inst_RowCount; i++)
				if (Browser.WebTable.getCellData("Installed_Assert", i, Col).equals(MSISDN)
						&& Browser.WebTable.getCellData("Installed_Assert", i, Col_Pro).equals(Prod)) {
					Result.takescreenshot("");
					Browser.WebTable.Expand("Installed_Assert", i, 1);
					Result.takescreenshot("");
				}

		} catch (Exception e) {

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: AddOnSelection
	 * Use 					: Customizing the specific Plan is done
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 9-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public void AddOnSelection(String Product, String Status) {
		try {
			int Length;
			String Prod_array[] = Product.split(",");
			Length = Prod_array.length;
			System.out.println(Length);
			if (Length > 1) {
				Thread.sleep(3000);
				Link_Select(Prod_array[0]);
				if (Status.contains("STAR")) {
					Thread.sleep(3000);
					Div_Select("Number Purchased Price");
					// scroll("Number_Purchase_Price", "WebEdit");
					Option_Sel(Prod_array[2]);
					Browser.WebEdit.click("Number_Purchase_Price");
					Browser.WebEdit.Set("Number_Purchase_Price", Prod_array[1]);
					// Browser.WebEdit.click("Discount_Reason");//,Prod_array[2]
					// );

				} else {
					if (Status.equals("Delete")) {
						System.out.println("-----------Delete addon-----------");
						Radio_None(Prod_array[1]);
					} else {
						System.out.println("-----------Add addon-----------");
						Radio_Select(Prod_array[1]);
					}
					if (Status.equals("Customise")) {
						// To be included
					}

				}
			}
		} catch (Exception e) {

		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Link_Select
	 * Use 					: To select a Link containing Specific text
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Link_Select(String Text) {
		String[] objprop = Utlities.FindObject("Link", "WebTable");
		String cellXpath = objprop[0] + Text + "']";
		KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Div_Select
	 * Use 					: To select a Link containing Specific text
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Div_Select(String Text) {

		String cellXpath = "//div[text()='" + Text + "']";
		KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Span_Sel
	 * Use 					: To click a span containing Specific text
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Span_Sel(String Text) {

		String cellXpath = "//span[text()='" + Text + "']";
		WebElement scr1 = KeyWord.cDriver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
	}

	public void Button_Sel(String Text) {
		String cellXpath = "//button[text()='" + Text + "']";
		WebElement scr1 = KeyWord.cDriver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
	}

	public void Option_Sel(String Text) {
		String cellXpath = "//option[text()='" + Text + "']";
		WebElement scr1 = KeyWord.cDriver.get().findElement(By.xpath(cellXpath));
		((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
		KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Radio_Select
	 * Use 					: To select a spectific Radio Button or check box
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Radio_Select(String Text) {

		/*
		 * List<WebElement> elements = KeyWord.cDriver.get().findElements(By.xpath(
		 * "//div[@class='siebui-ecfg-products']//div[1]//div[@class='siebui-ecfg-feature-group']"
		 * )); int Size=elements.size();
		 * 
		 * for(int i=0;i<Size;i++) {
		 */
		String cellXpath = "//input[@value='" + Text + "']";// "//div[@class='siebui-ecfg-products']//div[1]//div["+i+"]//div[1]//table//div[1]//div[1]//input[@value='"+Text+"']";

		if (cDriver.get().findElement(By.xpath(cellXpath)).isDisplayed()) {
			WebElement scr1 = KeyWord.cDriver.get().findElement(By.xpath(cellXpath));
			((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
			// break;}

		} else
			Driver.Continue.set(false);
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Radio_None
	 * Use 					: To select a spectific None Radio Button or to uncheck the check box
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Radio_None(String Text) {

		List<WebElement> elements = KeyWord.cDriver.get().findElements(
				By.xpath("//div[@class='siebui-ecfg-products']//div[1]//div[@class='siebui-ecfg-feature-group']"));
		int Size = elements.size();
		System.out.println(Size);
		boolean flag = false;
		for (int i = 1; i <= Size; i++) {
			List<WebElement> cellXpath = KeyWord.cDriver.get()
					.findElements(By.xpath("//div[@class='siebui-ecfg-products']//div[1]//div[" + i
							+ "]//div[1]//table//div[1]//div[1]//input"));
			waitforload();
			for (int t = 1; t < cellXpath.size(); t++) {
				if (cellXpath.get(t).getAttribute("value").equals(Text)) {
					if (cellXpath.get(t).getAttribute("type").equals("radio")) {
						// Radio Button
						((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)",
								cellXpath.get(0));
						waitforload();
						cellXpath.get(0).click();
						flag = true;
						break;
					} else {
						// Check box
						((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)",
								cellXpath.get(i));
						waitforload();
						cellXpath.get(i).click();
						flag = true;
						break;
					}
				}
			}

			if (flag) {
				break;
			}

		}

	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Custom
	 * Use 					: To select a spectific Radio Button
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Custom(String Text, String Custom) {

		List<WebElement> elements = KeyWord.cDriver.get().findElements(
				By.xpath("//div[@class='siebui-ecfg-products']//div[1]//div[@class='siebui-ecfg-feature-group']"));
		int Size = elements.size();

		for (int i = 0; i < Size; i++) {
			String cellXpath = "//div[@class='siebui-ecfg-products']//div[1]//div[" + i
					+ "]//div[1]//table//div[1]//div[1]//input[@value='" + Text + "']";

			if (cDriver.get().findElement(By.xpath(cellXpath)).isDisplayed()) {
				String cellXpath1 = "//div[@class='siebui-ecfg-products']//div[1]//div[" + i
						+ "]//div[1]//table//div[1]//div[1]//i[@class='siebui-icon-settings']";
				if (cDriver.get().findElement(By.xpath(cellXpath)).isDisplayed()) {
					KeyWord.cDriver.get().findElement(By.xpath(cellXpath1)).click();// click
																					// Customise
					Radio_Select(Custom);

				}
			}

		}

	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: upload
	 * Use 					: To select a Upload a specific file
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void upload(String Text, String File) {
		try {
			String[] objprop = Utlities.FindObject(Text, "WebButton");
			cDriver.get().findElement(By.xpath(objprop[0])).sendKeys(File);
			waitmoreforload();
			// Result.takescreenshot("File Uploaded");

		} catch (Exception e) {
			Driver.Continue.set(false);
			System.out.println("Failed to Upload");
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Plan_Select
	 * Use 					: To select a Upload a specific file
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 12-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void Plan_Select(String obj, String Val) {

		String[] objprop = Utlities.FindObject(obj, "WebEdit");
		cDriver.get().findElement(By.xpath(objprop[0])).sendKeys(Val);
		cDriver.get().findElement(By.xpath(objprop[0])).sendKeys(Keys.ENTER);

		// Result.takescreenshot("Plan Selected");

	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getscreenshot
	 * Use 					: To save the current screenshot as a png file
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void getscreenshot(String Path, String obj) // throws Exception
	{
		try {
			Actions action = new Actions(cDriver.get());
			action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
			if (Browser.WebButton.exist(obj) == true)
				action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
			waitmoreforload();
			File scrFile = ((TakesScreenshot) cDriver.get()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(Path));
			if (Browser.WebButton.exist(obj) == false)
				action.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).build().perform();
		} catch (Exception E) {
			Driver.Continue.set(false);
			System.out.println("Failed in saving the screen shot");
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitforload
	 * Use 					: It waits for the page to Load
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void waitforload() {
		try {
			cDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Thread.sleep(1500);
		} catch (Exception e) {
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: scroll
	 * Use 					: To scroll into  specific object
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void scroll(String objname, String ObjTyp) {
		try {
			String[] objprop = Utlities.FindObject(objname, ObjTyp);
			WebElement scr1 = KeyWord.cDriver.get().findElement(By.xpath(objprop[0]));
			// cDriver.get().executeScript("scroll(0, -250);");
			((RemoteWebDriver) cDriver.get()).executeScript("arguments[0].scrollIntoView(true)", scr1);
			Thread.sleep(1500);
			// cDriver.get().executeScript("arguments[0].scrollTop =
			// arguments[0].scrollHeight", scr1);
		} catch (Exception E) {
			Driver.Continue.set(false);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: scrollup
	 * Use 					: To scroll up of the web page
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void scrollup() {
		try {

			((RemoteWebDriver) cDriver.get()).executeScript("scroll(0, -250);");
		} catch (Exception E) {
			Driver.Continue.set(false);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: scrollup
	 * Use 					: To Set Data in a specific cell in a WebTable
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public void Set_Val(String objname, int r, String objTyp, String Val) {
		int Col = 1;
		String Expected = objTyp.replace('_', ' ');
		System.out.println(Expected);
		// String[] objprop = Driver.FindObject(objname,"WebTable");
		int Col_Count = Browser.WebTable.getColCount(objname);
		for (int i = 1; i < Col_Count; i++) {
			Col = i;
			String cellXpath = "//table//th[" + i + "]";
			String celldata = KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).getText();
			System.out.println(celldata);
			if (celldata.toLowerCase().contains(Expected.toLowerCase()))
				break;
		}
		Browser.WebTable.SetData(objname, r, Col, objTyp, Val);
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Select_Cell
	 * Use 					: To get a Particular Column Value with the Column Name
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public int Select_Cell(String objname, String objTyp) {
		int Col = 1;
		String Expected = objTyp;
		String[] obj = objTyp.split("_");
		if (obj.length > 1)
			Expected = objTyp.replace('_', ' ');
		// System.out.println(Expected);
		// String[] objprop = Driver.FindObject(objname,"WebTable");
		int Col_Count = Browser.WebTable.getColCount(objname);
		for (int i = 1; i < Col_Count; i++) {
			Col = i;
			String cellXpath = "//table//th[" + i + "]";
			String celldata = KeyWord.cDriver.get().findElements(By.xpath(cellXpath)).get(0).getText();
			// System.out.println(celldata);
			if (celldata.toLowerCase().contains(Expected.toLowerCase()))
				break;
		}
		return Col;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Actual_Cell
	 * Use 					: To get a Particular Column Value with the Column Name
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public int Actual_Cell(String objname, String objTyp) {
		int Col = 1, f = 0;
		String Expected = objTyp;
		String[] obj = objTyp.split("_");
		if (obj.length > 1)
			Expected = objTyp.replace('_', ' ');
		// System.out.println(Expected);
		// String[] objprop = Driver.FindObject(objname,"WebTable");
		int Col_Count = Browser.WebTable.getColCount(objname);
		waitforload();
		for (int i = 1; i < Col_Count; i++) {
			Col = i;
			String cellXpath = "//table//th[" + i + "]";
			String celldata = KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).getText();
			if (celldata.toLowerCase().contains(Expected.toLowerCase()))
				f = f + 1;
			if (f == 2)
				break;

		}
		return Col;
	}

	public String Col_Data(int i) {
		String cellXpath = "//table//th[" + i + "]";
		String celldata = KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).getText();
		return celldata;
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: scrollup
	 * Use 					: To Set Data in a specific cell in a WebTable
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public void SetDataVal(String objname, int rownum, String objTyp, String Val) {
		int Col = 1;
		int Col_Count = Browser.WebTable.getColCount(objname);
		for (int i = 1; i < Col_Count; i++) {
			Col = i;
			String cellXpath = "//table//th[" + i + "]";
			String celldata = KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).getText();
			if (celldata.toLowerCase().contains(objTyp.toLowerCase()))
				break;
		}
		try {
			String[] objprop = Utlities.FindObject(objname, "WebTable");
			String cellXpath = objprop[0] + "//tr[" + rownum + "]//td[" + Col + "]";
			KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
			String cellXpath1 = objprop[0] + "//tr[" + rownum + "]//td[" + Col + "]//input";
			cDriver.get().findElement(By.xpath(cellXpath1)).clear();
			String vis = "false";
			int countval = 1;
			while (vis == "false" || countval < 10000)
				if (KeyWord.cDriver.get().findElement(By.xpath(cellXpath1)).isDisplayed()) {
					KeyWord.cDriver.get().findElement(By.xpath(cellXpath1)).sendKeys(Val);
					if (Val.equals("Mobile"))
						System.out.println("Dropdown");
					else
						KeyWord.cDriver.get().findElement(By.xpath(cellXpath1)).sendKeys(Keys.ENTER);
					vis = "true";
					countval = 10000;
				} else {
					countval++;
					Thread.sleep(10);
				}
		} catch (Exception e) {
			System.out.println("Object Not Found");
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitmoreforload
	 * Use 					: It waits for the page to Load
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void waitmoreforload() {
		try {
			cDriver.get().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Thread.sleep(15000);

		} catch (Exception e) {
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitforobj
	 * Use 					: It waits for the obj to be loaded
	 * Arguments			: Object for which script waits
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public void waitforobj(String obj, String obj1) {
		try {
			int time = 1;
			if (obj1.equals("WebButton"))
				while (Browser.WebButton.exist(obj) == false) {
					Thread.sleep(2000);
					time++;
					if (Browser.WebButton.exist(obj) == true)
						break;
					if (time > 40)
						break;
				}
			if (obj1.equals("WebLink"))
				while (Browser.WebLink.exist(obj) == false) {
					Thread.sleep(1000);
					time++;
					if (Browser.WebLink.exist(obj) == true)
						break;
					if (time > 20)
						break;
				}
			if (obj1.equals("WebEdit"))
				while (Browser.WebEdit.exist(obj) == false) {
					Thread.sleep(2000);
					time++;
					if (Browser.WebEdit.exist(obj) == true)
						break;
					if (time > 40)
						break;
				}
			if (time > 40)
				Driver.Continue.set(false);
		} catch (Exception e) {
		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Col_Val
	 * Use 					: To get the specific Col
	 * Arguments			: Object for which script waits
	 * Designed By			: Vinodhini
	 * Last Modified Date 	: 19-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public int Get_Col(String objname, int rownum, String Expected) {
		int Col = 0, Col_Length = Browser.WebTable.getColCount(objname);
		for (int i = 1; i <= Col_Length; i++)
			if (Browser.WebTable.getCellData(objname, rownum, i).toLowerCase().equals(Expected.toLowerCase())) {
				Col = i;
				break;
			}

		return Col;

	}

	public void ResumeDate(String objname, int rownum, int columnnum) {
		try {
			String[] objprop = Utlities.FindObject(objname, "WebTable");
			String cellXpath = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]";
			KeyWord.cDriver.get().findElement(By.xpath(cellXpath)).click();
			String cellXpath1 = objprop[0] + "//tr[" + rownum + "]/td[" + columnnum + "]//span";
			KeyWord.cDriver.get().findElement(By.xpath(cellXpath1)).click();
			Button_Sel("Now");
			Button_Sel("Done");
		} catch (Exception e) {

		}
	}

	/*
	 * @SuppressWarnings("deprecation") public void halt_script(){ Thread
	 * Target_Thread = Thread.currentThread(); Target_Thread.destroy();
	 * 
	 * }
	 */
	public void Category_Select(String text, String text1) {
		try {
			String cellXpath, cellXpath1, cellXpathD, TxtVal, Txt = "";
			String[] objprop = Utlities.FindObject("Category_Plan", "WebTable");
			Thread.sleep(4000);
			for (int li_N = 1; li_N <= 6; li_N++) {
				cellXpath = objprop[0] + "[" + li_N + "]//a";// text;+"']";
				TxtVal = cDriver.get().findElement(By.xpath(cellXpath)).getAttribute("text");
				if (TxtVal.contains(text)) {
					cellXpath1 = objprop[0] + "[" + li_N + "]//i[1]";// text;+"']";
					KeyWord.cDriver.get().findElement(By.xpath(cellXpath1)).click();
					cellXpathD = objprop[0] + "[" + li_N + "]//ul//li[2]//a";// text;+"']";
					Txt = cDriver.get().findElement(By.xpath(cellXpathD)).getAttribute("text");
					if (Txt.contains(text1))
						cDriver.get().findElement(By.xpath(cellXpathD)).click();
					break;
				}

			}
		}

		catch (Exception e) {
		}
	}

	public boolean IsTableExist(String ObjName) {
		String[] objprop = Utlities.FindObject(ObjName, "WebTable");
		if (KeyWord.cDriver.get().findElement(By.xpath(objprop[0])).isDisplayed())
			return true;
		else
			return false;
	}

	public void TroubleTicketCheck() {
		try {
			scroll("TroubleTickets", "WebButton");
			Browser.WebButton.click("TroubleTickets");
			int Row = 2, Col = Select_Cell("Trouble_Ticket", "Ticket Id");
			if (Browser.WebTable.getRowCount("Trouble_Ticket") >= 2) {
				String TroubleTicketId = Browser.WebTable.getCellData("Trouble_Ticket", Row, Col);
				Utlities.StoreValue("Trouble_TicketID", TroubleTicketId);
				Result.takescreenshot("Trouble Ticket id " + TroubleTicketId);
				Browser.WebTable.clickL("Trouble_Ticket", Row, Col);
				Result.takescreenshot("Trouble Ticket Identified");
			} else
				System.out.println("Trouble ticket does not exist");
		} catch (Exception e) {
		}
	}

}
