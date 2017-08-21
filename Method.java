package Libraries;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.w3c.dom.Document;
//import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/*---------------------------------------------------------------------------------------------------------
 * Class Name			: Method
 * Use 					: Has the functions to do operation on the objects on the web page 
 * Designed By			: AG
 * Last Modified Date 	: 25-Apr-2016
 --------------------------------------------------------------------------------------------------------*/
public class Method extends Driver {
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waitForPageToLoad
	 * Arguments			: webcDriver.get() and timeout in seconds
	 * Use 					: Checks for the browser ready state and waits for the page to load 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void waitForPageToLoad(WebDriver driver, int timeOutInSeconds) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) cDriver.get();
			String command = "return document.readyState";
			// Check the readyState before doing any waits
			if (js.executeScript(command).toString().equals("complete")) {
				return;
			}
			for (int i = 0; i < timeOutInSeconds; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
				if (js.executeScript(command).toString().equals("complete")) {
					break;
				}
			}
		} catch (Exception e) {
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: setdropvalue
	 * Arguments			: identifier and value
	 * Use 					: Enters value in the drop down 
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void setdropvalue(String[] identify, String val) {
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length; i++) {
			try {
				if (Driver.Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							cDriver.get().findElement(By.xpath(identify[0])).click();
							cDriver.get().findElement(By.xpath(identify[0])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = cDriver.get().findElement(By.xpath(identify[0])).getAttribute("value");
								if (brcode.contains(val)) {
									cDriver.get().findElement(By.xpath(identify[0])).click();
									brfound = "yes";
									break;
								} else {
									cDriver.get().findElement(By.xpath(identify[0])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							cDriver.get().findElement(By.name(identify[1])).click();
							cDriver.get().findElement(By.name(identify[1])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = cDriver.get().findElement(By.name(identify[1])).getAttribute("value");
								if (brcode.contains(val)) {
									cDriver.get().findElement(By.name(identify[1])).click();
									brfound = "yes";
									break;
								} else {
									cDriver.get().findElement(By.name(identify[1])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							cDriver.get().findElement(By.id(identify[2])).click();
							cDriver.get().findElement(By.id(identify[2])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = cDriver.get().findElement(By.id(identify[2])).getAttribute("value");
								if (brcode.contains(val)) {
									cDriver.get().findElement(By.id(identify[2])).click();
									brfound = "yes";
									break;
								} else {
									cDriver.get().findElement(By.id(identify[2])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							cDriver.get().findElement(By.className(identify[3])).click();
							cDriver.get().findElement(By.className(identify[3]))
									.sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = cDriver.get().findElement(By.className(identify[3]))
										.getAttribute("value");
								if (brcode.contains(val)) {
									cDriver.get().findElement(By.className(identify[3])).click();
									brfound = "yes";
									break;
								} else {
									cDriver.get().findElement(By.className(identify[3])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							cDriver.get().findElement(By.linkText(identify[4])).click();
							cDriver.get().findElement(By.linkText(identify[4])).sendKeys(String.valueOf(val.charAt(0)));
							String brfound = "no";
							while (brfound != "yes") {
								String brcode = cDriver.get().findElement(By.linkText(identify[4]))
										.getAttribute("value");
								if (brcode.contains(val)) {
									cDriver.get().findElement(By.linkText(identify[4])).click();
									brfound = "yes";
									break;
								} else {
									cDriver.get().findElement(By.linkText(identify[4])).sendKeys(Keys.ARROW_DOWN);
								}
							}
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Driver.Continue.set(true);
			}
		}
		if (i == identify.length) {
			Driver.Continue.set(false);

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: setTD
	 * Arguments			: identifier and value
	 * Use 					: Enters value in the Web Edit
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void setTD(String[] identify, String val) {
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Driver.Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							cDriver.get().findElement(By.xpath(identify[0])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							cDriver.get().findElement(By.name(identify[1])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							cDriver.get().findElement(By.id(identify[2])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							cDriver.get().findElement(By.className(identify[3])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							cDriver.get().findElement(By.linkText(identify[4])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Driver.Continue.set(true);
			}
		}
		if (i == identify.length) {
			Driver.Continue.set(false);

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: selectTD
	 * Arguments			: identifier and value
	 * Use 					: Selects value in the drop down
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void selectTD(String[] identify, String val) {
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Driver.Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							cDriver.get().findElement(By.xpath(identify[0])).sendKeys(val);// modified
																							// cDriver.get().findElement(By.xpath(identify[0])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							cDriver.get().findElement(By.name(identify[1])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							cDriver.get().findElement(By.id(identify[2])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							cDriver.get().findElement(By.className(identify[3])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							cDriver.get().findElement(By.linkText(identify[4])).sendKeys(val);
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Driver.Continue.set(true);
			}
		}
		if (i == identify.length) {
			Driver.Continue.set(false);

		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clickTD
	 * Arguments			: identifier
	 * Use 					: Click the Web element, Button or Link
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/

	public static void clickTD(String[] identify) {
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Driver.Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							cDriver.get().findElement(By.xpath(identify[0])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							cDriver.get().findElement(By.name(identify[1])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							cDriver.get().findElement(By.id(identify[2])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							cDriver.get().findElement(By.className(identify[3])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							cDriver.get().findElement(By.linkText(identify[4])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Driver.Continue.set(true);
			}
		}
		if (i == identify.length) {
			Driver.Continue.set(false);

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: clearTD
	 * Arguments			: identifier
	 * Use 					: Clears the value in the web edit or drop down
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void clearTD(String[] identify) {
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {

				if (Driver.Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							cDriver.get().findElement(By.xpath(identify[0])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							cDriver.get().findElement(By.name(identify[1])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							cDriver.get().findElement(By.id(identify[2])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							cDriver.get().findElement(By.className(identify[3])).clear();
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							cDriver.get().findElement(By.linkText(identify[4])).click();
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Driver.Continue.set(true);
			}
		}
		if (i == identify.length) {
			Driver.Continue.set(false);

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: getval
	 * Arguments			: identifier and value
	 * Use 					: reads value
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static String getval(String[] identify) {
		String TxtVal = null;
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				if (Driver.Continue.get() == true) {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							TxtVal = cDriver.get().findElement(By.xpath(identify[0])).getAttribute("value");
							break;
						} else {
							throw new Exception(Error);
						}
					case 1:
						if (identify[1] != "") {
							TxtVal = cDriver.get().findElement(By.name(identify[1])).getAttribute("value");
							break;
						} else {
							throw new Exception(Error);
						}
					case 2:
						if (identify[2] != "") {
							TxtVal = cDriver.get().findElement(By.id(identify[2])).getAttribute("value");
							break;
						} else {
							throw new Exception(Error);
						}
					case 3:
						if (identify[3] != "") {
							TxtVal = cDriver.get().findElement(By.className(identify[3])).getAttribute("value");
							break;
						} else {
							throw new Exception(Error);
						}
					case 4:
						if (identify[4] != "") {
							TxtVal = cDriver.get().findElement(By.linkText(identify[4])).getAttribute("value");
							break;
						} else {
							throw new Exception(Error);
						}
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
				Driver.Continue.set(true);
			}
		}
		if (i == identify.length) {
			Driver.Continue.set(false);

			return null;
		} else {
			return TxtVal;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waittillobjvisible
	 * Arguments			: identifier and value
	 * Use 					: waits till the object exists
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void waittillobjvisible(String[] identify) throws InterruptedException {
		String vis = "false";
		int countval = 1;
		while (vis == "false" || countval < 10000) {
			int i = 0;
			for (i = 0; i < identify.length;) {
				try {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							cDriver.get().findElement(By.xpath(identify[0])).isDisplayed();
							break;
						} else {
							throw new Exception();
						}
					case 1:
						if (identify[1] != "") {
							cDriver.get().findElement(By.name(identify[1])).isDisplayed();
							break;
						} else {
							throw new Exception();
						}
					case 2:
						if (identify[2] != "") {
							cDriver.get().findElement(By.id(identify[2])).isDisplayed();
							break;
						} else {
							throw new Exception();
						}
					case 3:
						if (identify[3] != "") {
							cDriver.get().findElement(By.className(identify[3])).isDisplayed();
							break;
						} else {
							throw new Exception();
						}
					case 4:
						if (identify[4] != "") {
							cDriver.get().findElement(By.linkText(identify[4])).isDisplayed();
							break;
						} else {
							throw new Exception();
						}
					}
					break;
				} catch (Exception e) {
					i++;
				}
			}
			if (i == identify.length) {
				vis = "false";
				countval++;
				Thread.sleep(1);
			} else {
				vis = "true";
				break;
			}
		}
	}

	public static boolean existobj(String[] identify) {
		int i = 0;
		Exception Error = null;
		for (i = 0; i < identify.length;) {
			try {
				switch (i) {
				case 0:
					if (identify[0] != "") {
						cDriver.get().findElement(By.xpath(identify[0])).isDisplayed();
						break;
					} else {
						throw new Exception(Error);
					}
				case 1:
					if (identify[1] != "") {
						cDriver.get().findElement(By.name(identify[1])).isDisplayed();
						break;
					} else {
						throw new Exception(Error);
					}
				case 2:
					if (identify[2] != "") {
						cDriver.get().findElement(By.id(identify[2])).isDisplayed();
						break;
					} else {
						throw new Exception(Error);
					}
				case 3:
					if (identify[3] != "") {
						cDriver.get().findElement(By.className(identify[3])).isDisplayed();
						break;
					} else {
						throw new Exception(Error);
					}
				case 4:
					if (identify[4] != "") {
						cDriver.get().findElement(By.linkText(identify[4])).isDisplayed();
						break;
					} else {
						throw new Exception(Error);
					}
				}
				break;
			} catch (Exception e) {
				i++;
				Error = e;
			}
		}
		if (i == identify.length) {
			// System.out.println("Object doesn't Exists:\n");
			// Error.printStackTrace();
			return false;
		} else {
			return true;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: waittillobjvisible
	 * Arguments			: identifier and value
	 * Use 					: waits till the object exists
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void Methodwaittillenabled(String[] identify) throws InterruptedException {
		String vis = "false";
		int countval = 1;
		while (vis == "false" || countval < 10000) {
			int i = 0;
			for (i = 0; i < identify.length;) {
				try {
					switch (i) {
					case 0:
						if (identify[0] != "") {
							cDriver.get().findElement(By.xpath(identify[0])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 1:
						if (identify[1] != "") {
							cDriver.get().findElement(By.name(identify[1])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 2:
						if (identify[2] != "") {
							cDriver.get().findElement(By.id(identify[2])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 3:
						if (identify[3] != "") {
							cDriver.get().findElement(By.className(identify[3])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					case 4:
						if (identify[4] != "") {
							cDriver.get().findElement(By.linkText(identify[4])).isEnabled();
							break;
						} else {
							throw new Exception();
						}
					}
					break;
				} catch (Exception e) {
					i++;
				}
			}
			if (i == identify.length) {
				vis = "false";
				countval++;
				Thread.sleep(1);
			} else {
				vis = "true";
				break;
			}
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Setvalue
	 * Arguments			: Update the tag value with the value in the testdata sheet
	 * Use 					: waits till the object exists
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static Document Setvalue(Document doc, String key, String val) {
		NodeList nList = doc.getElementsByTagName(key);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				nNode.setTextContent(val);
			}
		}
		return doc;
	}

}