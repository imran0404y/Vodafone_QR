package Libraries;

import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class KeyWord extends Driver {

	static Common CO = new Common();
	static Method M = new Method();
	static Random R = new Random();
	public static String ApplicationId;
	public static String Bill_No, Order_No, Migrate_Bill, Account_No, SIMREFID, IDNumber;
	public static String Temp[];
	public static String Test_Input;
	public static String Test_OutPut, Status, Output;
	public static int COL_FUL_STATUS;

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Open browser
	 * Arguments			: None
	 * Use 					: Opens a New Browser and logins to the Siebel CRM application
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String OpenSiebelBrowser() {
		try {
			if(!(getdata("Browser").equals(""))) {
				browser.set(getdata("Browser"));
			}else {
				browser.set("Chrome");
			}
			
			Browser.OpenBrowser(browser.get(), getdata("URL"));
			
			Driver.resultdescription.set("Browser Opened Successfully");
			Result.takescreenshot("Opening Browser and navigating to the URL");
			Browser.WebEdit.waittillvisible("VQ_Login_User");
			Browser.WebEdit.Set("VQ_Login_User", getdata("VQ_Login_User"));
			Browser.WebEdit.Set("VQ_Login_Pswd", getdata("VQ_Login_Pswd"));
			Result.takescreenshot("Entering User Name and Password");
			Browser.WebButton.waittillvisible("VQ_Login");
			Browser.WebButton.click("VQ_Login");
			Browser.WebLink.waittillvisible("VQ_Contact");
			CO.waitforload();

			if (Driver.Continue.get() & Browser.WebLink.exist("VQ_Contact")) {
				Driver.resultdescription.set("Login Successfully with user" + getdata("VQ_Login_User"));
				Result.takescreenshot("Logged in Successfully");
				Status = "PASS";
			} else {
				Driver.resultdescription.set("Login Failed");
				Driver.Continue.set(false);
				Status = "FAIL";
			}
			Test_Input = "Siebel Login Event Details";
			Test_OutPut = "Siebel Login Event Details - Completed Successfully";
			//// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			Output = "STATUS  @@ " + readpropval("STATUS");
			return Output;

		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			e.printStackTrace();
			Test_Input = "Siebel Login Event Details";
			Test_OutPut = "Siebel Login Event Details - @@ Exception Occured";
			//// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			//// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STATUS  @@ " + readpropval("STATUS");
			return Output;
		}

	}

	/*----------------------------------------------------------------------------------------------------
	 * Method Name			: ContactCreation
	 * Arguments			: None
	 * Use 					: Creates a new contact with the specific data in Vanilla Journey
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String ContactCreation() {
		try {
			// Navigating to Contacts
			CO.waitforobj("VQ_Contact", "WebLink");
			Result.takescreenshot("Navigating to Contacts");
			CO.waitforload();
			Browser.WebLink.click("VQ_Contact");
			Browser.WebLink.waittillvisible("My_Contacts");
			Result.takescreenshot("Contact Navigation done Successfully");
			CO.waitforobj("My_Contacts", "WebLink");
			Browser.WebLink.click("My_Contacts");
			Random R = new Random();
			
			// Navigating to My Contacts
			Result.takescreenshot("Navigating to My Contacts");
			Browser.WebButton.waittillvisible("New_Contact");
			Browser.WebButton.click("New_Contact");
			Result.takescreenshot("Creating Contact");
			
			String Last_Name;
			if(!(getdata("LastName").equals(""))) {
				Last_Name = getdata("LastName") + R.nextInt(100);
			}else {
				Last_Name = "VFQA_Test" + R.nextInt(100);
			}
			CO.scroll("Last_Name", "WebEdit");
			Browser.WebEdit.Set("Last_Name", Last_Name);
			
			//CO.scroll("First_Name", "WebEdit");
			if(!(getdata("First_Name").equals(""))) {
				Browser.WebEdit.Set("First_Name", getdata("FirstName"));
			}else {
				Browser.WebEdit.Set("First_Name", "VFQA_Mav");
			}
			
			//CO.scroll("Mr/Ms", "ListBox");
			if(!(getdata("Mr/Ms").equals(""))) {
				Browser.ListBox.select("Mr/Ms", getdata("Mr/Ms"));
			}else {
				Browser.ListBox.select("Mr/Ms", "Mr.");
			}
			
			//CO.scroll("PrefLanguage", "ListBox");
			if(!(getdata("PrefLanguage").equals(""))) {
				Browser.ListBox.select("PrefLanguage", getdata("PrefLanguage"));
			}
			
			//CO.scroll("DOB", "WebEdit");
			if(!(getdata("DOB").equals(""))) {
				Browser.WebEdit.Set("DOB", getdata("DOB"));
			}else {
				Browser.WebEdit.Set("DOB", "02181980");
			}
			
			//CO.scroll("Gender", "ListBox");
			if(!(getdata("Gender").equals(""))) {
				Browser.ListBox.select("Gender", getdata("Gender"));
			}else {
				Browser.ListBox.select("Gender", "M");
			}
			

			//CO.scroll("Email", "WebEdit");
			if(!(getdata("Email").equals(""))) {
				Browser.WebEdit.Set("Email", getdata("Email"));
			}else {
				Browser.WebEdit.Set("Email", "nomail@vodafone.com");
			}
			
			//CO.scroll("ID_Type", "ListBox");
			if(!(getdata("ID_Type").equals(""))) {
				Browser.ListBox.select("ID_Type", getdata("ID_Type"));
			}else {
				Browser.ListBox.select("ID_Type", "Passport");
			}
			
			// To Generate Random Number
			if(!(getdata("ID_Number").equals(""))) {
				IDNumber = getdata("ID_Number") + R.nextInt(100000);
			}else {
				IDNumber = "VQFT13412" + R.nextInt(100000);
			}
			//CO.scroll("ID_Number", "WebEdit");
			Browser.WebEdit.Set("ID_Number", IDNumber);
			
			//CO.scroll("ID_ExpDate", "WebEdit");
			if(!(getdata("ID_ExpDate").equals(""))) {
				Browser.WebEdit.Set("ID_ExpDate", getdata("ID_ExpDate"));
			}else {
				Browser.WebEdit.Set("ID_ExpDate", "02142020");
			}
			
			//CO.scroll("Nationality", "ListBox");
			if(!(getdata("Nationality").equals(""))) {
				Browser.ListBox.select("Nationality", getdata("Nationality"));
			}else {
				Browser.ListBox.select("Nationality", "Icelander");
			}
			
			//CO.scroll("Phone", "WebEdit");
			if(!(getdata("Phone").equals(""))) {
				Browser.WebEdit.Set("Phone", getdata("Phone"));
			}else {
				Browser.WebEdit.Set("Phone", "97412412312");
			}
			
			Browser.WebLink.waittillvisible("Con_Link");
			//CO.scroll("Con_Link", "WebLink");
			Browser.WebLink.click("Con_Link");
			// Handles Alerts
			if (CO.isAlertExist())
				//CO.waitforload();
			/*if (CO.isAlertExist()) {
				if(!(getdata("Gender").equals(""))) {
					Browser.ListBox.select("Gender", getdata("Gender"));
				}else {
					Browser.ListBox.select("Gender", "M");
				}
				if(!(getdata("Nationality").equals(""))) {
					Browser.ListBox.select("Nationality", getdata("Nationality"));
				}else {
					Browser.ListBox.select("Nationality", "Icelander");
				}
				//CO.scroll("Phone", "WebEdit");
				if(!(getdata("Phone").equals(""))) {
					Browser.WebEdit.Set("Phone", getdata("Phone"));
				}else {
					Browser.WebEdit.Set("Phone", "97412412312");
				}
				//CO.scroll("Con_Link", "WebLink");
				Browser.WebLink.click("Con_Link");
				CO.AlertExist();
			}*/

			//Browser.WebButton.waittillvisible("Add_Address");

			if (Driver.Continue.get() & Browser.WebButton.exist("Add_Address")) {
				Status = "PASS";
				Utlities.StoreValue("Contact_LastName", Last_Name);
				Driver.resultdescription.set("Contact for " + Last_Name + "is created Successfully");
				Result.takescreenshot("Contact Creation is Successful");
			} else {
				Status = "FAIL";
				Driver.Continue.set(false);
				Driver.resultdescription.set("Contact Creation Failed");
			}
			Test_Input = "Contact Creation Event Details";
			Test_OutPut = "Contact Creation Event Details - Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Contact Creation Event Details";
			Test_OutPut = "Contact Creation Event Details - Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: AccountCreation
	 * Arguments			: None
	 * Use 					: Creates Account for the Contact created Earlier in Vanilla Journey
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String AccountCreation()

	{
		try {
			// Click on Add_Address
			String Address_Line;
			if (Browser.WebButton.exist("Add_Address"))
				System.out.println("Proceeding Account Creation");
			else {
				Driver.Continue.set(true);
				CO.ContactSearch(Utlities.FetchStoredValue("ContactCreation"));
			}
			//CO.waitforobj("Add_Address", "WebButton");
			//Browser.WebButton.waittillvisible("Add_Address");
			Browser.WebButton.click("Add_Address");

			// Search for Specific Address
			CO.waitforobj("Add_Go","WebButton");
			CO.scroll("Add_Go", "WebButton");
			//CO.waitforobj("Add_Go", "WebButton");
			
			if(!(getdata("Address").equals(""))) {
				Address_Line = getdata("Address");
			}else {
				Address_Line = "VFQTest";
			}
			Browser.WebEdit.Set("Add_Search",Address_Line );
			
			Browser.WebButton.click("Add_Go");
			Result.takescreenshot("Searching Address");

			// if no records found to be included
			CO.scroll("Add_OK", "WebButton");
			Browser.WebButton.click("Add_OK");
			Result.takescreenshot("Address Added");
			Browser.WebButton.waittillvisible("Create_A/c");
			Browser.WebButton.waitTillEnabled("Create_A/c");
			CO.waitforload();
			CO.waitforload();
			CO.waitforload();
			CO.waitforobj("Create_A/c", "WebButton");
			int Row_Count = Browser.WebTable.getRowCount("Address");
			if (Row_Count > 1) {
				CO.scroll("Create_A/c", "WebButton");
				CO.Span_Sel("Create A/c");
				Driver.Continue.set(true);
			} else {
				Driver.Continue.set(false);
				System.exit(0);
			}

			Result.takescreenshot("Account Created");

			Browser.ListBox.waittillvisible("CR_Type");
			if(!(getdata("CR_Type").equals(""))) {
				Browser.ListBox.select("CR_Type", getdata("CR_Type"));
			}else {
				Browser.ListBox.select("CR_Type", "Commercial License");
			}
			
			if(!(getdata("SpecialManagement").equals(""))) {
				Browser.ListBox.select("Spcl_Mang", getdata("SpecialManagement"));
			}else {
				Browser.ListBox.select("Spcl_Mang", "VFQ Test Account");
			}
			
			Random R = new Random();
			String CR = "12" + R.nextInt(100000);
			Browser.ListBox.select("CR_Number", CR);
			
			Account_No = Browser.WebEdit.gettext("Account_No");
			// Driver.storeinproertiesfile("Consumer_Account=" + Account_No);
			// Driver.storeinproertiesfile("Consumer_Account_Name=" + Account_No);
			System.out.println("Account Name: " + Account_No);

			if (Driver.Continue.get()) {
				Status = "PASS";
				Utlities.StoreValue("Account No", Account_No);
				Driver.resultdescription
						.set("Account Created with Address " + Address_Line + " Successfully");
				Result.takescreenshot("Account Creation is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("Account Creation Failed");
			}

			Test_Input = "Account Creation Event Details";
			Test_OutPut = "Account Creation Event Details - Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Account Creation Event Details";
			Test_OutPut = "Account Creation Event Details - Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Entp_AccountCreation
	 * Arguments			: None
	 * Use 					: Creates an Enterprise Account for Vanilla Journey
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Entp_AccountCreation() {
		try {
			// Navigating to Accounts
			CO.waitmoreforload();
			Browser.WebLink.waittillvisible("VQ_Account");
			Result.takescreenshot("Navigating to Accounts");
			CO.waitforload();
			Browser.WebLink.click("VQ_Account");
			// Browser.WebLink.waittillvisible("My_Account");
			CO.waitforload();
			Result.takescreenshot("Account Navigation done Successfully");
			CO.Link_Select("My Accounts");
			// Browser.WebLink.click("My_Account");

			CO.scroll("New_Account", "WebButton");
			Browser.WebButton.click("New_Account");
			String Acc ;
			if(!(getdata("Account_Name").equals(""))) {
				Acc = getdata("Account_Name");
			}else {
				Acc = Utlities.randname() + R.nextInt(100);
			}
			
			CO.scroll("Acc_Name", "WebEdit");
			Browser.WebEdit.Set("Acc_Name", Acc);
			if(!(getdata("CR_Type").equals(""))) {
				Browser.ListBox.select("CR_Type", getdata("CR_Type"));
			}else {
				Browser.ListBox.select("CR_Type", "Commercial License");
			}

			Account_No = Browser.WebEdit.gettext("Account_No");
			// Driver.storeinproertiesfile("Enterprise_Account=" + Account_No);
			Random R = new Random();
			Browser.ListBox.select("CR_Number", "1" + R.nextInt(1000000));

			if(!(getdata("SpecialManagement").equals(""))) {
				Browser.ListBox.select("Spcl_Mang", getdata("SpecialManagement"));
			}else {
				Browser.ListBox.select("Spcl_Mang", "VFQ Test Account");
			}
			
			/*
			 * Browser.ListBox.select("Acc_Class", getdata("Acc_Class"));
			 * Browser.ListBox.select("Acc_Type", getdata("Acc_Type"));
			 */
			CO.scroll("Customer_Segment", "ListBox");
			if(!(getdata("CustomerSegment").equals(""))) {
				Browser.ListBox.select("Customer_Segment", getdata("CustomerSegment"));
			}else {
				Browser.ListBox.select("Customer_Segment", "SME Strategic");
			}
			
			CO.waitforload();
			CO.scroll("Tier", "WebEdit");
			Browser.WebEdit.click("Tier");
			if(!(getdata("Tier").equals(""))) {
				Browser.WebEdit.Set("Tier", getdata("Tier"));
			}else {
				Browser.WebEdit.Set("Tier", "Tier3");
			}
			

			CO.Link_Select(Acc);
			CO.waitforload();
			Browser.WebLink.click("Acc_Portal");

			Browser.WebLink.waittillvisible("Acc_Contacts");
			if (Driver.Continue.get() & Browser.WebLink.exist("Acc_Contacts")) {
				Status = "PASS";
				Utlities.StoreValue("Account No", Account_No);
				Driver.resultdescription.set("Enterprise Account is created Successfully");
				Result.takescreenshot("Enterprise Account Creation is Successful");
			} else {
				Status = "FAIL";
				Driver.Continue.set(false);
				Driver.resultdescription.set("Enterprise Account Creation Failed");
			}
			Test_Input = "Enterprise Account Creation Event Details";
			Test_OutPut = "Enterprise Account Creation Event Details - Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Enterprise Account Creation Event Details";
			Test_OutPut = "Enterprise Account Creation Event Details - Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Entp_ContactCreation0
	 * Arguments			: None
	 * Use 					: Creates a Contact in the Enterprise Account for Vanilla Journey
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Entp_ContactCreation() {
		try {
			Browser.WebLink.waittillvisible("Acc_address");
			if (Browser.WebLink.exist("Acc_address"))
				System.out.println("Proceeding Entp_ContactCreation");
			else {
				Driver.Continue.set(true);
				CO.AccountSearch(Utlities.FetchStoredValue("Entp_AccountCreation"));
			}
			Browser.WebLink.click("Acc_address");
			Browser.WebButton.click("Acc_Add_Address");
			if(!(getdata("AddressStartingwith").equals(""))) {
				Browser.WebEdit.Set("Acc_Add_Address", getdata("AddressStartingwith"));
			}else {
				Browser.WebEdit.Set("Acc_Add_Address", "EmptyData");
			}
			
			Browser.WebButton.click("Acc_Add_Go");
			int Row = 2, Col;
			CO.scroll("Acc_Add_Ok", "WebButton");
			CO.waitmoreforload();
			int Add_Row = Browser.WebTable.getRowCount("Acc_Add_Address");
			System.out.println(Add_Row);
			if (Add_Row >= 2)
				Browser.WebButton.click("Acc_Add_Ok");
			else {
				CO.scroll("Acc_Add_New", "WebButton");
				Browser.WebButton.click("Acc_Add_New");
				
				String Add1 = Utlities.randname();
				String Add2 = Utlities.randname();
				Col = CO.Select_Cell("Acc_Address", "Address Line 1");
				if(!(getdata("Add_AddressLine1").equals(""))) {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "Street_Address", getdata("Add_AddressLine1"));
				}else {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "Street_Address", Add1);
				}
				
				Col = CO.Select_Cell("Acc_Address", "PO Box");
				if(!(getdata("Add_POBox").equals(""))) {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "VFQA_PO_Box", getdata("Add_POBox"));
				}else {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "VFQA_PO_Box", "12345");
				}
				
				Col = CO.Select_Cell("Acc_Address", "Postal Code");
				if(!(getdata("Add_Zip").equals(""))) {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "Postal_Code", getdata("Add_Zip"));
				}else {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "Postal_Code", "51232");
				}
				
				Col = CO.Select_Cell("Acc_Address", "Address Line 2");
				if(!(getdata("Add_AddressLine2").equals(""))) {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "Street_Address_2", getdata("Add_AddressLine2"));
				}else {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "Street_Address_2", Add2);
				}
				
				Col = CO.Select_Cell("Acc_Address", "Kahramaa ID");
				if(!(getdata("Add_Kahramaa_ID").equals(""))) {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "VFQA_Kahramaa_ID",getdata("Add_Kahramaa_ID"));
				}else {
					Browser.WebTable.SetDataE("Acc_Address", Row, Col, "VFQA_Kahramaa_ID",
							"1" + R.nextInt(100000) + R.nextInt(100));
				}
			}
			int x = 0;
			Browser.WebLink.click("Acc_Contacts");
			CO.waitforload();
			
			x = Browser.WebTable.getRowCount("Acc_Contact");
			if (x == 1) {
				Browser.WebButton.click("Acc_Add_Contact");
			}
			
			Col = CO.Select_Cell("Acc_Contact", "First Name");
			if(!(getdata("Cont_FirstName").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "First_Name", getdata("Cont_FirstName"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "First_Name", Utlities.randname());
			}
			
			Col = CO.Select_Cell("Acc_Contact", "Last Name");
			String Last; 
			if(!(getdata("Cont_LastName").equals(""))) {
				Last = getdata("Cont_LastName");
			}else {
				Last = Utlities.randname();
			}
			Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "Last_Name", Last);
			
			Col = CO.Select_Cell("Acc_Contact", "Mr/Ms");
			if(!(getdata("Cont_Mr/Ms").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "M_M", getdata("Cont_Mr/Ms"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "M_M", "Mr.");
			}
			
			// Browser.WebTable.SetData("Acc_Contact", 2, Col, "Job_Title",getdata(""));
			
			/*Col = CO.Select_Cell("Acc_Contact", "Work Phone #");
			if(!(getdata("Cont_WorkPhone").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "Work_Phone__", getdata("Cont_WorkPhone"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "Work_Phone__", "97498780980");
			}*/
			
			Col = CO.Select_Cell("Acc_Contact", "Email");
			if(!(getdata("Cont_Email").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "Email_Address", getdata("Cont_Email"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "Email_Address", "noname@vodafone.com");
			}
			
			Col = CO.Select_Cell("Acc_Contact", "Date of Birth");
			if(!(getdata("Cont_DOB").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_DOB", getdata("Cont_DOB"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_DOB", "02021990");
			}
			
			Col = CO.Select_Cell("Acc_Contact", "ID Expiration Date");
			if(!(getdata("Cont_IDExpiryDate").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_ID_Expiration_Date", getdata("Cont_IDExpiryDate"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_ID_Expiration_Date", "04042020");
			}
			
			Col = CO.Select_Cell("Acc_Contact", "ID Number");
			if(!(getdata("Cont_IDNumber").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_ID_Number", getdata("Cont_IDNumber"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_ID_Number", "9" + R.nextInt(100000000));
			}
			
			//CO.scroll("Contact_ACC", "WebTable");
	
			//Col = CO.Select_Cell("Acc_Contact", "ID Type");
			Col++;
			if(!(getdata("Cont_IDType").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_ID_Type", getdata("Cont_IDType"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_ID_Type", "Passport");
			}
			
			Col++;
			//Col = CO.Select_Cell("Acc_Contact", "Mobile Phone #");
			if(!(getdata("Cont_MobilePhone").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "Cellular_Phone__", getdata("Cont_MobilePhone"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "Cellular_Phone__", "97498098089");
			}
			
			Col++;
			//Col = CO.Select_Cell("Acc_Contact", "Nationality");
			if(!(getdata("Cont_Nationality").equals(""))) {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_Nationality", getdata("Cont_Nationality"));
			}else {
				Browser.WebTable.SetDataE("Acc_Contact", Row, Col, "VFQ_Nationality", "Qatari");
			}
			
			Col = Col + 4;
			//Col = CO.Select_Cell("Acc_Contact", "Gender");
			if(!(getdata("Cont_Gender").equals(""))) {
				Browser.WebTable.SetData("Acc_Contact", Row, Col, "VFQA_M_F", getdata("Cont_Gender"));
			}else {
				Browser.WebTable.SetData("Acc_Contact", Row, Col, "VFQA_M_F", "M");
			}
			
			Col = CO.Select_Cell("Acc_Contact", "Preferred Language");
			if(!(getdata("Cont_PrefLang").equals(""))) {
				Browser.WebTable.SetData("Acc_Contact", Row, Col, "VFQ_Preferred_Language", getdata("Cont_PrefLang"));
			}
			
			
			CO.waitforload();
			CO.scroll("Account360", "WebButton");
			CO.Link_Select("Addresses");
			if (CO.isAlertExist())
				if (CO.isAlertExist())
					CO.Link_Select("Addresses");

			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("Enterprise Contact " + Last + "is created Successfully");
				Result.takescreenshot("Enterprise Contact Creation is Successful");
			} else {
				Status = "FAIL";
				Driver.Continue.set(false);
				Driver.resultdescription.set("Enterprise Contact Creation Failed");
			}
			Test_Input = "Enterprise Contact Creation Event Details";
			Test_OutPut = "Enterprise Contact Creation Event Details - Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			e.printStackTrace();
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Enterprise Contact Creation Event Details";
			Test_OutPut = "Enterprise Contact Creation Event Details - Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: BillingProfileCreation
	 * Arguments			: None
	 * Use 					: Creates a Billing Profile in the existing Account for Vanilla Journey
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String BillingProfileCreation()

	{

		try {
			if (Browser.WebButton.exist("Profile"))
				System.out.println("Proceeds with BillingProfileCreation");
			else {
				Driver.Continue.set(true);
				Account_No = Utlities.FetchStoredValue("AccountCreation");
				CO.AccountSearch(Account_No);
			}
			
			CO.scroll("Profile", "WebButton");
			Browser.WebButton.click("Profile");
			cDriver.get().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			int Row = 2, Col_Val = 8, Row_Count;
			Result.takescreenshot("Billing Profile Creation");
			String Payment_Type;
			CO.waitforobj("Profile", "WebButton");
			Browser.WebButton.click("Profile");
			CO.waitforobj("Bill_Add", "WebButton");
			cDriver.get().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
			Row_Count = Browser.WebTable.getRowCount("Bill_Prof");
			if ((Row_Count < Row) || (getdata("Migrate").equals("Yes"))) {
				CO.scroll("Bill_Add", "WebButton");
				Browser.WebButton.click("Bill_Add");
			}
			Col_Val = CO.Select_Cell("Bill_Prof", "Payment Type");
			System.out.println(Col_Val);
			CO.scroll("Bill_Prof", "WebTable");
			if(!(getdata("Bill_PayType").equals(""))) {
				Payment_Type = getdata("Bill_PayType");
			}else {
				Payment_Type = "Postpaid";
			}
			
			Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Payment_Type", Payment_Type);

			
			Thread.sleep(2000);
			cDriver.get().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

			Col_Val = CO.Select_Cell("Bill_Prof", "Payment Method");
			if(!(getdata("Bill_PayMethod").equals(""))) {
				Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Payment_Method", getdata("Bill_PayMethod"));
			}else {
				Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Payment_Method", "Invoice");
			}
			

			if (Payment_Type.equals("Postpaid")) {
				Col_Val = CO.Select_Cell("Bill_Prof", "Bill Media");
				if(!(getdata("Bill_Media").equals(""))) {
					Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Media_Type", getdata("Bill_Media"));
				}else {
					Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Media_Type", "Email(PDF,XLS)");
				}

				Col_Val = CO.Select_Cell("Bill_Prof", "Bill Type");
				if(!(getdata("Bill_Type").equals(""))) {
					Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Bill_Type", getdata("Bill_Type"));
				}else {
					Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Bill_Type", "Detail");
				}

			}

			Col_Val = CO.Select_Cell("Bill_Prof", "Language");
			if(!(getdata("Bill_Lang").equals(""))) {
				Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Bank_Language_Code", getdata("Bill_Lang"));
			}else {
				Browser.WebTable.SetData("Bill_Prof", Row, Col_Val, "Bank_Language_Code", "ENG");
			}
			
			Col_Val = CO.Select_Cell("Bill_Prof", "Name");
			if (getdata("Migrate_Bill").equals("Yes")) {
				Migrate_Bill = Browser.WebTable.getCellData("Billing_Profile", Row, Col_Val);
				// Driver.storeinproertiesfile("Migration_Bill_NO" + "=" + Bill_No);
				Utlities.StoreValue("Migration_Bill_NO", Migrate_Bill);
			} else {
				Bill_No = Browser.WebTable.getCellData("Billing_Profile", Row, Col_Val);
				// Driver.storeinproertiesfile("Billing_NO" + "=" + Bill_No);
				Utlities.StoreValue("Billing_NO", Bill_No);
			}

			Browser.WebButton.waittillvisible("Account_Summary");

			if (Driver.Continue.get() & Browser.WebButton.exist("Account_Summary")) {
				Status = "PASS";
				Driver.resultdescription.set("Billing Profile Created for customer Successfully");
				Result.takescreenshot("Billing Profile Creation is Successful");
			} else {
				Status = "FAIL";
				Driver.Continue.set(false);
				Driver.resultdescription.set("Billing Profile Creation Failed");
			}

			Test_Input = "Billing Profile Creation Event Details";
			Test_OutPut = "Billing Profile Creation Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Billing Profile Creation Event Details";
			Test_OutPut = "Billing Profile Creation Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			Driver.Continue.set(false);
			return Output;

		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: SalesOrder
	 * Arguments			: None
	 * Use 					: Creates a Sales Order in the existing Account for Vanilla Journey
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String SalesOrder()

	{
		try {

			if (Browser.WebButton.exist("Account_Summary"))
				System.out.println("Proceeding with Sales Order");
			else if (!Utlities.FetchStoredValue("BillingProfileCreation").isEmpty()) {
				Driver.Continue.set(true);
				Account_No = Utlities.FetchStoredValue("AccountCreation");
				CO.AccountSearch(Account_No);
			}
			Browser.WebButton.waitTillEnabled("Account_Summary");
			Browser.WebButton.click("Account_Summary");
			if (CO.isAlertExist())
				Browser.WebButton.click("Account_Summary");
			int Row = 2, Col = 6;
			Browser.WebButton.waitTillEnabled("Order_New");
			CO.scroll("Order_New", "WebButton");
			Browser.WebButton.click("Order_New");
			Result.takescreenshot("Sales order Creation");

			Col = CO.Get_Col("Order_Table", Row, "Sales Order") + 1;

			Order_No = Browser.WebTable.getCellData("Order_Table", 2, Col);
			// Driver.storeinproertiesfile(getdata("Store_OrderID") + "=" + Order_No);

			Browser.WebTable.Link("Order_Table", Row, Col);
			System.out.println(readpropval(Order_No));
			Browser.WebButton.waittillvisible("LI_New");

			if (Driver.Continue.get() & Browser.WebButton.exist("LI_New")) {
				Status = "PASS";
				Utlities.StoreValue("Sales_Order", Order_No);
				Driver.resultdescription
						.set("Sales Order Created with" + readpropval(Order_No) + " Successfully");
				Result.takescreenshot("Sales Order Creation is Successful");
			} else {
				Status = "FAIL";
				Driver.Continue.set(false);
				Driver.resultdescription.set("Sales Order Creation Failed");
			}

			Test_Input = "Sales Order Creation Event Details";
			Test_OutPut = "Sales Order Creation Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Sales Order Creation Event Details";
			Test_OutPut = "Sales Order Creation Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			Driver.Continue.set(false);
			return Output;

		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: PlanSelection
	 * Arguments			: None
	 * Use 					: Specified Plan is selected for the Order in Vanilla Journey
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String PlanSelection()

	{
		try {
			Result.takescreenshot("Plan Selection");

			if (Browser.WebButton.exist("LI_New"))
				System.out.println("Proceeding Plan Selection");
			else {
				Driver.Continue.set(true);
				CO.OrderSearch(Utlities.FetchStoredValue("SalesOrder"));
			}
			CO.scroll("LI_New", "WebButton");
			Browser.WebButton.click("LI_New");
			int Row = 2, Col;
			Col = CO.Select_Cell("Line_Items", "Product");
			System.out.println(Col);
			Browser.WebTable.SetDataE("Line_Items", Row, Col, "Product", getdata("PlanName"));
			Browser.WebTable.click("Line_Items", Row, Col + 1);
			CO.waitforload();
			int Row_Count = Browser.WebTable.getRowCount("Line_Items");
			if (Driver.Continue.get() & (Row_Count >= 3)) {
				Status = "PASS";
				Utlities.StoreValue("Plan", getdata("PlanName"));
				Driver.resultdescription.set("Plan Selection for " + getdata("PlanName") + "is done Successfully");
				Result.takescreenshot("Plan Selection is Successful");
			} else {
				Status = "FAIL";
				Driver.Continue.set(false);
				Driver.resultdescription.set("Plan Selection Failed");
			}
			Test_Input = "Plan Selection  Event Details";
			Test_OutPut = "Plan Selection  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Plan Selection Event Details";
			Test_OutPut = "Plan Selection Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}

	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Installed_Assert
	 * Arguments			: None
	 * Use 					: Installed Assert for the specific data is fetched 
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Installed_Assert(String Status)

	{
		try {
			CO.Assert_Search(getdata("MSISDN"), Status, "*Service Bundle");
			// CO.Assert_Search(Driver.msisdn_exe.get(), Status, "*Service Bundle");
			String Prod = "Mobile Service Bundle";
			int Inst_RowCount = Browser.WebTable.getRowCount("Installed_Assert");
			for (int i = 1; i <= Inst_RowCount; i++)
				if (Browser.WebTable.getCellData("Installed_Assert", i, 2).equals(Prod)) {
					Result.takescreenshot("");
					Browser.WebTable.click("Installed_Assert", i, 1);
					Result.takescreenshot("");
				}
			if (Driver.Continue.get()) {
				Status = "PASS";
				// Driver.resultdescription
				// .set("Installed Assert Search for" + Driver.msisdn_exe.get() + "is done
				// Successfully");
				Driver.resultdescription
						.set("Installed Assert Search for" + getdata("MSISDN") + "is done Successfully");
				Result.takescreenshot("Assert Search done successfully");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("Customisation Failed");
			}
			Test_Input = "Installed Assert Search  Event Details";
			Test_OutPut = "Installed Assert Search  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Installed Assert Search Event Details";
			Test_OutPut = "Installed Assert Search Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			Driver.Continue.set(false);
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Modify
	 * Arguments			: None
	 * Use 					: Modification of Installed Assert is performed
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Modify() {
		try {
			System.out.println("Modification Intiated");
			Installed_Assert("Active");
			// System.out.println("Opened"+getdata("Addons_Add")+
			// getdata("Activate_Plan"));
			System.out.println("Assert Search Done");
			if (Browser.WebButton.exist("Assert_Modify")) {
				// CO.Link_Select("Account Summary");
				int Inst_RowCount = Browser.WebTable.getRowCount("Acc_Installed_Assert");
				int Col_P = CO.Select_Cell("Acc_Installed_Assert", "Product");// ,Col_SID=CO.Select_Cell("Acc_Installed_Assert",
																				// "Service
																				// ID");
				for (int i = 2; i <= Inst_RowCount; i++)
					if (Browser.WebTable.getCellData("Acc_Installed_Assert", i, Col_P)
							.equals("Mobile Service Bundle")) {// &&Browser.WebTable.getCellData("Acc_Installed_Assert",
																// i,
																// Col_SID).equals(Driver.msisdn_exe.get()))
																// {
						Browser.WebTable.click("Acc_Installed_Assert", i, Col_P + 1);
						break;
					}
				// CO.InstalledAssertChange("Modify");
				Browser.WebButton.click("Assert_Modify");
			} else {
				CO.InstalledAssertChange("Modify");
			}
			CO.waitforload();
			// CO.scroll("Due_Date_chicklet", "WebButton");

			Browser.WebButton.click("Due_Date_chicklet");
			Browser.WebButton.click("Date_Now");
			Browser.WebButton.click("Date_Done");
			if (Browser.WebEdit.gettext("Due_Date").equals("")) {
				Driver.Continue.set(false);
				System.exit(0);
			}
			// Browser.WebButton.click("Due_Date"); CO.waitforload();

			CO.scroll("Date_Continue", "WebButton");
			Browser.WebButton.click("Date_Continue");

			// To Perform Add Addon or Remove Addon

			// CO.waitmoreforload();
			Result.takescreenshot("");

			// To Perform SIM Change or MSISDN Change

			if (currKW.get().contains("Simchange") || currKW.get().contains("MSISDNChange")) {

				// if (!(Driver.ReservationToken.get().isEmpty()))
				// Browser.WebEdit.Set("NumberReservationToken", Driver.ReservationToken.get());
				if (!(getdata("ReservationToken").isEmpty()))
					Browser.WebEdit.Set("NumberReservationToken", getdata("ReservationToken"));
				CO.Button_Sel("Verify");
				CO.isAlertExist();
				CO.waitforload();
				CO.Button_Sel("Done");
				if (CO.isAlertExist()) {
					Driver.Continue.set(false);
					System.out.println("Error On Clicking Done Button");
					System.exit(0);
				}

				ChangeMSISDNSIM();
			} else {
				CO.AddOnSelection(getdata("Addons_Add"), "Add");
				Result.takescreenshot("");
				CO.waitforload();
				CO.AddOnSelection(getdata("Addons_Customise"), "Customise");
				Result.takescreenshot("");
				CO.waitforload();
				CO.AddOnSelection(getdata("Addons_Delete"), "Delete");
				CO.waitforload();
				CO.Button_Sel("Verify");
				CO.isAlertExist();
				CO.waitforload();
				CO.Button_Sel("Done");
				if (CO.isAlertExist()) {
					Driver.Continue.set(false);
					System.out.println("Error On Clicking Done Button");
					System.exit(0);
				}
				Result.takescreenshot("");
				Validate();
			}

			Result.takescreenshot("");
			OrderSubmission();
			Result.takescreenshot("");
			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("Modifying the Plan is done Successfully");
				Result.takescreenshot("Modifying is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("Customisation Failed");
			}
			Test_Input = "Modify  Event Details";
			Test_OutPut = "Modify  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Modify Event Details";
			Test_OutPut = "Modify Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Child
	 * Arguments			: None
	 * Use 					: To Perform child Provisioning for same account
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Child() {
		try {
			CO.Assert_Search(getdata("MSISDN_Parent"), "Active", "*Service Bundle");

			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("Child Provisioning is done Successfully");
				Result.takescreenshot("Child Provisioning is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("Child Provisioning Failed");
			}
			Test_Input = "Child Provisioning  Event Details";
			Test_OutPut = "Child Provisioning  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Child Provisioning Event Details";
			Test_OutPut = "Child Provisioning Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: PlanChange
	 * Arguments			: None
	 * Use 					: To Perform child Provisioning for same account
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String PlanChange() {
		try {
			CO.Assert_Search(getdata("MSISDN"), "Active", "*Service Bundle");
			String Prod = getdata("PlanChange_From"), Field;
			int Inst_RowCount = Browser.WebTable.getRowCount("Installed_Assert"), Row = 2, Col_F;
			for (int i = 1; i <= Inst_RowCount; i++)
				if (Browser.WebTable.getCellData("Installed_Assert", i, Row).equals(Prod)) {
					Result.takescreenshot("");
					Browser.WebTable.click("Installed_Assert", i, 1);
					Result.takescreenshot("");
				}
			CO.InstalledAssertChange("Upgrade Promotion");
			CO.waitforload();
			Browser.WebEdit.Set("Promotion_Upgrade", getdata("PlanChange_To"));
			String Path[] = Utlities.FindObject("Promotion_Upgrade", "WebEdit");
			cDriver.get().findElement(By.xpath(Path[0])).sendKeys(Keys.ENTER);
			CO.waitforload();
			if (Browser.WebTable.getRowCount("Promotion_Upgrades") >= 2)
				Browser.WebButton.click("Upgrade_OK");
			else {
				Driver.Continue.set(false);
				System.exit(0);
			}
			/*
			 * CO.Button_Sel("Verify"); CO.isAlertExist(); CO.waitforload();
			 * CO.Button_Sel("Done");
			 */

			CO.waitforload();
			CO.scroll("Ful_Status", "WebButton");
			Col_F = CO.Select_Cell("Line_Items", "Fulfillment Status");
			Field = (CO.Col_Data(Col_F).trim());
			System.out.println(Field);
			if (Field.toLowerCase().equals("fulfillment status"))
				COL_FUL_STATUS = Col_F;
			// Driver.storeinproertiesfile("COL_FUL_STATUS=" + COL_FUL_STATUS);
			CO.scroll("Service", "WebButton");

			Browser.WebButton.waittillvisible("Validate");
			Browser.WebButton.click("Validate");
			CO.waitforload();
			cDriver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Browser.WebButton.waittillvisible("Submit");
			OrderSubmission();
			Result.takescreenshot("");
			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("PlanChange is done Successfully");
				Result.takescreenshot("PlanChange is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("PlanChange Failed");
			}
			Test_Input = "PlanChange Event Details";
			Test_OutPut = "PlanChange Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "PlanChange Event Details";
			Test_OutPut = "PlanChange Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Suspend
	 * Arguments			: None
	 * Use 					: Suspend a Active Plan
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Suspend() {
		try {
			Installed_Assert("Active");
			String Field, Resume_Date;
			int Col_F, Col_Resume, Row = 2;
			if (Browser.WebButton.exist("Suspend")) {
				int Inst_RowCount = Browser.WebTable.getRowCount("Acc_Installed_Assert");
				int Col_P = CO.Select_Cell("Acc_Installed_Assert", "Product"),
						Col_SID = CO.Select_Cell("Acc_Installed_Assert", "Service ID");
				System.out.println(Col_P + "," + Col_SID);
				// System.out.println(Browser.WebTable.getCellData("Acc_Installed_Assert",
				// 3, Col_SID));
				System.out.println(Browser.WebTable.getCellData("Acc_Installed_Assert", 3, Col_P));
				for (int i = 2; i <= Inst_RowCount; i++)
					if (Browser.WebTable.getCellData("Acc_Installed_Assert", i, Col_P)
							.equals("Mobile Service Bundle")) {// &&Browser.WebTable.getCellData("Acc_Installed_Assert",
																// i,
																// Col_SID).equals(Driver.msisdn_exe.get()))
																// {
						Browser.WebTable.click("Acc_Installed_Assert", i, Col_P + 1);
						break;
					}

				// CO.InstalledAssertChange("Suspend");

				CO.scroll("Suspend", "WebButton");
				Browser.WebButton.click("Suspend");
			}

			CO.waitforload();
			CO.scroll("Due_Date_chicklet", "WebButton");
			Browser.WebButton.click("Due_Date_chicklet");
			Browser.WebButton.click("Date_Now");
			Browser.WebButton.click("Date_Done");
			if (Browser.WebEdit.gettext("Due_Date").equals(""))
				Driver.Continue.set(false);
			CO.scroll("Date_Continue", "WebButton");
			Browser.WebButton.click("Date_Continue");
			CO.waitmoreforload();
			Result.takescreenshot("");
			CO.scroll("Resume_Date", "WebButton");
			Col_Resume = CO.Select_Cell("Line_Items", "Resume Date");
			Browser.WebTable.click("Line_Items", Row, Col_Resume);
			DateFormat ResumeDate = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
			Calendar cals = Calendar.getInstance();
			cals.add(Calendar.MONTH, 1);
			System.out.println(ResumeDate.format(cals.getTime()).toString());
			Resume_Date = ResumeDate.format(cals.getTime()).toString();
			Browser.WebTable.SetDataE("Line_Items", Row, Col_Resume, "Scheduled_Ship_Date", Resume_Date);
			System.out.println(CO.Col_Data(Col_Resume).trim());
			Result.takescreenshot("");
			CO.scroll("Ful_Status", "WebButton");
			Col_F = CO.Select_Cell("Line_Items", "Fulfillment Status");
			Field = (CO.Col_Data(Col_F).trim());
			System.out.println(Field);
			if (Field.toLowerCase().equals("fulfillment status"))
				COL_FUL_STATUS = Col_F;
			// Driver.storeinproertiesfile("COL_FUL_STATUS=" + COL_FUL_STATUS);
			CO.scroll("Service", "WebButton");

			Browser.WebButton.waittillvisible("Validate");
			Browser.WebButton.click("Validate");
			CO.waitforload();
			cDriver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Browser.WebButton.waittillvisible("Submit");
			OrderSubmission();
			Result.takescreenshot("");
			CO.Assert_Search(getdata("MSISDN"), "Suspended", "*Service Bundle");
			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("Suspend the Plan is done Successfully");
				Result.takescreenshot("Suspend is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("Suspend Failed");
			}
			Test_Input = "Suspend  Event Details";
			Test_OutPut = "Suspend  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Suspend Event Details";
			Test_OutPut = "Suspend Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Resume
	 * Arguments			: None
	 * Use 					: Resume the Suspended Plan
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Resume() {
		try {
			Installed_Assert("Suspended");// Have to check
			String Field, Resume_Date;
			int Col_F, Col_Resume, Row = 2;
			if (Browser.WebButton.exist("Resume")) {
				int Inst_RowCount = Browser.WebTable.getRowCount("Acc_Installed_Assert");
				int Col_P = CO.Select_Cell("Acc_Installed_Assert", "Product");// ,Col_SID=CO.Select_Cell("Acc_Installed_Assert",
																				// "Service
																				// ID");
				for (int i = 2; i <= Inst_RowCount; i++)
					if (Browser.WebTable.getCellData("Acc_Installed_Assert", i, Col_P)
							.equals("Mobile Service Bundle")) {// &&Browser.WebTable.getCellData("Acc_Installed_Assert",
																// i,
																// Col_SID).equals(Driver.msisdn_exe.get()))
																// {
						Browser.WebTable.click("Acc_Installed_Assert", i, Col_P + 1);
						break;
					}

				// CO.InstalledAssertChange("Resume");

				CO.scroll("Resume", "WebButton");
				Browser.WebButton.click("Resume");
			}
			CO.waitforload();
			CO.scroll("Due_Date_chicklet", "WebButton");
			Browser.WebButton.click("Due_Date_chicklet");
			Browser.WebButton.click("Date_Now");
			Browser.WebButton.click("Date_Done");
			if (Browser.WebEdit.gettext("Due_Date").equals(""))
				Driver.Continue.set(false);
			CO.scroll("Date_Continue", "WebButton");
			Browser.WebButton.click("Date_Continue");
			CO.waitmoreforload();
			Result.takescreenshot("");
			CO.scroll("Resume_Date", "WebButton");
			Col_Resume = CO.Select_Cell("Line_Items", "Resume Date");
			Resume_Date = (CO.Col_Data(Col_Resume).trim());
			System.out.println(Resume_Date);
			if (Resume_Date.toLowerCase().equals("resume date"))
				Driver.Continue.set(true);
			else
				Driver.Continue.set(false);
			Browser.WebTable.click("Line_Items", Row, Col_Resume);
			CO.ResumeDate("Line_Items", Row, Col_Resume);
			Result.takescreenshot("");
			CO.scroll("Ful_Status", "WebButton");
			Col_F = CO.Select_Cell("Line_Items", "Fulfillment Status");
			Field = (CO.Col_Data(Col_F).trim());
			System.out.println(Field);
			if (Field.toLowerCase().equals("fulfillment status"))
				COL_FUL_STATUS = Col_F;
			// Driver.storeinproertiesfile("COL_FUL_STATUS=" + COL_FUL_STATUS);
			CO.scroll("Service", "WebButton");

			Browser.WebButton.waittillvisible("Validate");
			Browser.WebButton.click("Validate");
			CO.waitforload();
			cDriver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Browser.WebButton.waittillvisible("Submit");
			OrderSubmission();
			Result.takescreenshot("");
			CO.Assert_Search(getdata("MSISDN"), "Active", "*Service Bundle");
			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("Resume Plan is done Successfully");
				Result.takescreenshot("Resume is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("Resume Failed");
			}
			Test_Input = "Resume  Event Details";
			Test_OutPut = "Resume  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Resume Event Details";
			Test_OutPut = "Resume Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: MigrateBill
	 * Arguments			: None
	 * Use 					: Plan Upgradation
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String MigrateBill() {
		try {
			Installed_Assert("Active");
			BillingProfileCreation();
			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("MigrateBill is done Successfully");
				Result.takescreenshot("MigrateBill is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("MigrateBill Failed");
			}
			Test_Input = "MigrateBill  Event Details";
			Test_OutPut = "MigrateBill  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "MigrateBill Event Details";
			Test_OutPut = "MigrateBill Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: TransferOwner
	 * Arguments			: None
	 * Use 					: TransferOwner
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String TransferOwner() {
		try {
			// Enterprise_Account
			String New_Account = Browser.readpropval("Enterprise_Account"),
					Old_Account = Browser.readpropval("Consumer_Account"),
					Service_Account = Browser.readpropval("Consumer_Account_Name"), Field;
			int Col, Row_Count, Col_F;
			Result.takescreenshot("Transfer Ownership Initiated");
			CO.AccountSearch(New_Account);
			// Code to be included to point Mobile Service Bundle record
			CO.InstalledAssertChange("Modify");
			CO.waitforload();
			CO.scroll("Due_Date_chicklet", "WebButton");
			CO.scroll("Date_Continue", "WebButton");
			Browser.WebButton.click("Date_Continue");
			CO.waitforload();
			CO.Button_Sel("Verify");
			CO.isAlertExist();
			CO.waitforload();
			CO.Button_Sel("Done");
			CO.waitforload();
			Browser.WebEdit.Set("Service_Account", Service_Account);
			Result.takescreenshot("");
			CO.scroll("Service_Account", "WebButton");
			Col = CO.Select_Cell("Line_Items", "Service Account");
			Row_Count = Browser.WebTable.getRowCount("Line_Items");
			for (int Row = 2; Row <= Row_Count; Row++)
				Browser.WebTable.SetDataE("Line_Items", Row, Col, "Service_Account", Service_Account);
			CO.scroll("Ful_Status", "WebButton");
			Col_F = CO.Select_Cell("Line_Items", "Fulfillment Status");
			Field = (CO.Col_Data(Col_F).trim());
			System.out.println(Field);
			if (Field.toLowerCase().equals("fulfillment status"))
				COL_FUL_STATUS = Col_F;
			// Driver.storeinproertiesfile("COL_FUL_STATUS=" + COL_FUL_STATUS);
			CO.scroll("Service", "WebButton");
			Browser.WebButton.waittillvisible("Validate");
			Result.takescreenshot("");
			Browser.WebButton.click("Validate");
			CO.waitforload();
			cDriver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Browser.WebButton.waittillvisible("Submit");
			OrderSubmission();
			Result.takescreenshot("New Account Verification");
			CO.AccountSearch(New_Account);
			Result.takescreenshot("Old Account Verification");
			CO.AccountSearch(Old_Account);
			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("TransferOwner is done Successfully");
				Result.takescreenshot("TransferOwner is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("TransferOwner Failed");
			}
			Test_Input = "TransferOwner  Event Details";
			Test_OutPut = "TransferOwner  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "TransferOwner Event Details";
			Test_OutPut = "TransferOwner Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Upgradation
	 * Arguments			: None
	 * Use 					: Plan Upgradation
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Upgradation() {
		try {
			Installed_Assert("Active");
			String Field, Migration, GetData = "Mobile Service Bundle", Product = getdata("PlanChange_To");
			int Col_F, Col_Migrate, Row_Count, Col = CO.Select_Cell("Line_Items", "Product");
			PlanChange();
			CO.scroll("Ful_Status", "WebButton");
			Col_F = CO.Select_Cell("Line_Items", "Fulfillment Status");
			Field = (CO.Col_Data(Col_F).trim());
			System.out.println(Field);
			if (Field.toLowerCase().equals("fulfillment status"))
				COL_FUL_STATUS = Col_F;
			// Driver.storeinproertiesfile("COL_FUL_STATUS=" + COL_FUL_STATUS);
			CO.scroll("Service", "WebButton");
			Col_Migrate = CO.Select_Cell("Line_Items", "Billing Profile");
			Migration = (CO.Col_Data(Col_Migrate).trim());
			if (Migration.toLowerCase().equals("billing profile"))
				Browser.WebButton.click("Migrate_Billing_Profile");

			Row_Count = Browser.WebTable.getRowCount("Line_Items");

			for (int i = 2; i <= Row_Count; i++) {
				String LData = Browser.WebTable.getCellData("Line_Items", i, Col);
				if (GetData.equals(LData))
					Browser.WebTable.SetDataE("Line_Items", i, Col_Migrate, "Billing_Profile_Name", Migrate_Bill);
				if (Product.equals(LData))
					Browser.WebTable.SetDataE("Line_Items", i, Col_Migrate, "Billing_Profile_Name", Migrate_Bill);// getdata("Migration_BillingProfile"));
			}
			Browser.WebButton.waittillvisible("Validate");
			Browser.WebButton.click("Validate");
			CO.waitforload();
			cDriver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Browser.WebButton.waittillvisible("Submit");
			OrderSubmission();
			Result.takescreenshot("");
			CO.Assert_Search(getdata("SiebText_ServiceId"), "Active", "*Service Bundle");
			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("Upgradation is done Successfully");
				Result.takescreenshot("Upgradation is Successful");
			} else {
				Status = "FAIL";
				Driver.resultdescription.set("Upgradation Failed");
			}
			Test_Input = "Upgradation  Event Details";
			Test_OutPut = "Upgradation  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Upgradation Event Details";
			Test_OutPut = "Upgradation Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		}
	}
	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Customize
	 * Arguments			: None
	 * Use 					: Customizing the specific Plan is done with Number Reservation depending on data
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/

	public static String Customize()

	{
		try {

			if (Browser.WebTable.exist("Line_Items"))
				System.out.println("Proceding Customize");
			else {
				Driver.Continue.set(true);
				CO.OrderSearch(Utlities.FetchStoredValue("SalesOrder"));
			}
			cDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			int Row_Val = 3, Col = 3, Col_V, Row_Count, Len, Row = 2, COl_STyp, Col_Res;
			String Number, Reserve, Category, GetData = "Mobile Service Bundle";
			Col = CO.Select_Cell("Line_Items", "Product");
			// To select the Mobile Bundle
			Col_V = Col + 2;

			Browser.WebTable.click("Line_Items", Row_Val, Col_V);
			Row_Count = Browser.WebTable.getRowCount("Line_Items");

			for (int i = 2; i <= Row_Count; i++) {
				String LData = Browser.WebTable.getCellData("Line_Items", i, Col);
				if (GetData.equals(LData)) {
					Row_Val = i;
					break;
				}
			}
			Browser.WebTable.click("Line_Items", Row_Val, Col_V);

			Result.takescreenshot("Customizing Plan");
			if (getdata("ReservationToken") != "" || getdata("Addons_Add") != "" || getdata("Addons_Customise") != ""
					|| getdata("Addons_Delete") != "") {
				Browser.WebButton.click("Customize");
				if (getdata("ReservationToken") != "") {
					Browser.WebEdit.waittillvisible("NumberReservationToken");
					Browser.WebEdit.Set("NumberReservationToken", getdata("ReservationToken"));
					Result.takescreenshot("Providing Number Reservation Token");
				}
				CO.AddOnSelection(getdata("Addons_Add"), "Add");
				Result.takescreenshot("");
				CO.AddOnSelection(getdata("Addons_Customise"), "Customise");
				Result.takescreenshot("");
				CO.AddOnSelection(getdata("Addons_Delete"), "Delete");
				Result.takescreenshot("");
				CO.waitforload();
				CO.Button_Sel("Verify");
				CO.isAlertExist();
				CO.waitforload();
				CO.Button_Sel("Done");
				CO.waitforload();
				if (CO.isAlertExist())
					Driver.Continue.set(false);

			}

			if (getdata("ReservationToken") == "" && getdata("MSISDN") != "" && (getdata("PlanName1") != "")
					&& !(currKW.get().contains("MSISDNChange"))) {
				CO.waitforload();
				CO.scroll("Numbers", "WebLink");
				Browser.WebLink.click("Numbers");
				CO.waitforload();

				Row_Count = Browser.WebTable.getRowCount("Numbers");
				if (Row_Count == 1)
					Browser.WebButton.click("Number_Query");
				Browser.WebLink.click("Num_Manage");
				Number = getdata("MSISDN");
				Len = Number.length();
				Reserve = Number.substring(3, Len);
				Browser.WebButton.waitTillEnabled("Reserve");
				Browser.WebButton.waittillvisible("Reserve");
				System.out.println(Reserve);
				COl_STyp = CO.Select_Cell("Numbers", "Service Type");
				Col_Res = CO.Select_Cell("Numbers", "(Start) Number");
				System.out.println(COl_STyp + " " + Col_Res);
				Browser.WebTable.SetData("Numbers", Row, COl_STyp, "Service_Type", "Mobile");
				Browser.WebTable.SetData("Numbers", Row, Col_Res, "Service_Id", Reserve);
				// Browser.WebButton.click("Number_Go");
				Result.takescreenshot("Number Reservation");
				Browser.WebButton.waittillvisible("Reserve");
				Browser.WebButton.waitTillEnabled("Reserve");
				Category = Browser.WebTable.getCellData("Numbers", Row, COl_STyp + 1);
				System.out.println("Category " + Category);
				Browser.WebButton.click("Reserve");
				CO.waitforload();
				if (CO.isAlertExist())
					System.out.println("Alert Handled");
				Result.takescreenshot("Number Resered");
				Browser.WebLink.waittillvisible("Line_Items");
				Browser.WebLink.click("Line_Items");
				Browser.WebLink.click("LI_Totals");
				Row_Count = Browser.WebTable.getRowCount("Line_Items");
				if (Category.contains("STAR")) {
					Row_Count = Browser.WebTable.getRowCount("Line_Items");

					for (int i = 2; i <= Row_Count; i++) {
						String LData = Browser.WebTable.getCellData("Line_Items", i, Col);
						if (GetData.equals(LData)) {
							Row_Val = i;
							break;
						}
					}
					Browser.WebTable.click("Line_Items", Row_Val, Col_V);
					Browser.WebButton.click("Customize");
					CO.AddOnSelection(getdata("STAR_Number"), "STAR");
					CO.waitforload();
					CO.Button_Sel("Verify");
					CO.isAlertExist();
					Thread.sleep(1000);
					CO.Button_Sel("Done");
					// CO.waitforload();
					if (CO.isAlertExist()) {
						Driver.Continue.set(false);
						System.exit(0);
					}

				}
			}

			if (Driver.Continue.get() & (Row_Count >= 3)) {
				Status = "PASS";
				Utlities.StoreValue("CustomizeOrder", Utlities.FetchStoredValue("SalesOrder"));
				Driver.resultdescription
						.set("Customisation for " + getdata("NumberReservationToken") + "is done Successfully");
				Result.takescreenshot("Customisation is Successful");
			} else {
				Status = "FAIL";

				Driver.Continue.set(false);
				Driver.resultdescription.set("Customisation Failed");
			}

			Test_Input = "Customisation  Event Details";
			Test_OutPut = "Customisation  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Customisation Event Details";
			Test_OutPut = "Customisation Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}

	}

	/*------------------------------------------------------------------------------------------------------- 
	 * Method Name			: Validate
	 * Arguments			: None
	 * Use 					: Validating the Order with the required data
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String Validate() {
		try {

			if (Browser.WebTable.exist("Line_Items"))
				System.out.println("Proceeding Validation");
			else {
				Driver.Continue.set(true);
				CO.OrderSearch(Utlities.FetchStoredValue("Customize"));
			}
			cDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			int Row_Val = 0, Row_Count, Col, Col_S, Col_F;
			Col = CO.Select_Cell("Line_Items", "Product");
			String MData = "Mobile Service Bundle";
			String SData = "SIM Card";
			String Field;
			// Have to Include Providing SIM no and MSISDN no
			Browser.WebButton.waittillvisible("Expand");
			Browser.WebButton.click("Expand");
			Col_S = CO.Select_Cell("Line_Items", "Service Id");
			Field = (CO.Col_Data(Col_S).trim());
			if (Field.toLowerCase().equals("previous service id"))
				Col_S = CO.Actual_Cell("Line_Items", "Service Id");
			System.out.println(Field = (CO.Col_Data(Col_S).trim()));
			if (getdata("Ent_CreditLimit") != "")
				//Browser.WebEdit.click("Ent_CreditLimit");
				Browser.WebEdit.Set("Ent_CreditLimit", getdata("Ent_CreditLimit"));
			else
				Browser.WebEdit.click("Credit_Limit");
			Row_Count = Browser.WebTable.getRowCount("Line_Items");
			if (!currKW.get().contains("Simchange") && !currKW.get().contains("MSISDNChange"))
				if (getdata("Activate_Plan") != "") {
					for (int i = 2; i <= Row_Count; i++) {
						String LData = Browser.WebTable.getCellData("Line_Items", i, Col);
						if (MData.equals(LData)) {
							Row_Val = i;
						}
					}
					if (Row_Count <= 3) {
						Browser.WebButton.click("Expand");
					}

					if (getdata("ReservationToken") == "" & getdata("MSISDN") != "") {
						Browser.WebTable.click("Line_Items", Row_Val, Col_S + 1);
						// CO.scroll("Service_Id", "WebButton");
						Browser.WebTable.click("Line_Items", Row_Val, Col_S);
						Browser.WebTable.Popup("Line_Items", Row_Val, Col_S);
						Browser.WebButton.waittillvisible("Reserved_Ok");
						Browser.WebButton.waitTillEnabled("Reserved_Ok");
						Row_Count = Browser.WebTable.getRowCount("Number_Selection");
						if (Row_Count > 1)
							Browser.WebButton.click("Reserved_Ok");
						else
							Driver.Continue.set(false);
					} else if (!currKW.get().contains("Simchange")) {
						Browser.WebTable.click("Line_Items", Row_Val, Col_S);
						Browser.WebTable.SetData("Line_Items", Row_Val, Col_S, "Service_Id", getdata("MSISDN"));
					}
					// To Provide SIM No
					Row_Count = Browser.WebTable.getRowCount("Line_Items");
					Row_Val = Row_Val + 2;
					for (int i = 2; i <= Row_Count; i++) {
						String LData = Browser.WebTable.getCellData("Line_Items", i, Col);
						if (SData.equals(LData))
							Row_Val = i;
					}
					Browser.WebTable.click("Line_Items", Row_Val, Col_S);
					Browser.WebTable.SetData("Line_Items", Row_Val, Col_S, "Service_Id", getdata("SIM"));
					Result.takescreenshot("Providing MISDIN and SIM No");

				}

			// To get fulfillment status coloumn
			CO.scroll("Ful_Status", "WebButton");
			Col_F = CO.Select_Cell("Line_Items", "Fulfillment Status");
			Field = (CO.Col_Data(Col_F).trim());
			System.out.println(Field);
			if (Field.toLowerCase().equals("fulfillment status"))
				COL_FUL_STATUS = Col_F;
			// Driver.storeinproertiesfile("COL_FUL_STATUS=" + COL_FUL_STATUS);
			CO.scroll("Service", "WebButton");

			Browser.WebButton.waittillvisible("Validate");
			Browser.WebButton.click("Validate");
			CO.waitforload();
			CO.isAlertExist();
			cDriver.get().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			Browser.WebButton.waittillvisible("Submit");

			if (Driver.Continue.get() & (Browser.WebButton.exist("Submit"))) {
				Status = "PASS";
				Utlities.StoreValue("Validate_Order", Utlities.FetchStoredValue("Customize"));
				Driver.resultdescription.set("Validation with appropriate MISDIN and SIM No is done Successfully");
				Result.takescreenshot("Validation is Successful");
			} else {
				Driver.Continue.set(false);
				Status = "FAIL";
				Driver.resultdescription.set("Validation Failed");
			}
			Test_Input = "Validation  Event Details";
			Test_OutPut = "Validation  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Validation Event Details";
			Test_OutPut = "Validation Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: OrderSubmission
	 * Arguments			: None
	 * Use 					: Submits the Order, Waits for the Fulfillment Status and Displays the result 
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String OrderSubmission() {
		try {

			if (Browser.WebTable.exist("Line_Items"))
				System.out.println("Proceeding Order Submission");
			else {
				Driver.Continue.set(true);
				CO.OrderSearch(Utlities.FetchStoredValue("Validation"));
			}
			cDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			String Status;
			int Complete_Status = 0, R_S = 0, Wait = 0, Row = 2, Col;
			Col = COL_FUL_STATUS;
			String EStatus = "Complete", FStatus = "Failed";
			Thread.sleep(3000);
			Browser.WebButton.waittillvisible("Submit");
			Thread.sleep(3000);
			Browser.WebButton.waittillvisible("Submit");
			Thread.sleep(3000);
			Browser.WebButton.waitTillEnabled("Submit");
			Thread.sleep(3000);
			CO.waitmoreforload();
			CO.scroll("Submit", "WebButton");
			Browser.WebButton.click("Submit");
			CO.waitforload();
			CO.isAlertExist();

			int Row_Count = Browser.WebTable.getRowCount("Line_Items");
			// Col = COL_FUL_STATUS;
			Status = Browser.WebTable.getCellData("Line_Items", Row, Col);
			// System.out.println(Row_Count);
			if (Row_Count <= 3) {
				Browser.WebButton.waittillvisible("Expand");
				Browser.WebButton.click("Expand");
			}

			Browser.WebButton.waittillvisible("Submit");

			do {

				Complete_Status = 0;
				R_S = 0;
				CO.scroll("Submit", "WebButton");
				CO.scrollup();
				if (!(currKW.get().contains("Simchange") || currKW.get().contains("MSISDNChange"))) {
					if (getdata("Product_Type") != "")
						Browser.WebEdit.click("Ent_CreditLimit");
					else
						Browser.WebEdit.click("Credit_Limit");
				}
				CO.scroll("Ful_Status", "WebButton");

				for (int i = 2; i <= 3; i++) {
					System.out.println("Round" + (i - 1));
					Status = Browser.WebTable.getCellData("Line_Items", i, Col);
					System.out.println(Status);
					if (EStatus.equals(Status)) {// To Find the Complete Status
						Complete_Status = Complete_Status + 1;
						R_S++;
						Wait = Wait + 80;
						Driver.Continue.set(true);
					} else {
						if (FStatus.equals(Status)) {
							Driver.Continue.set(false);
							R_S = 2;
							Wait = 101;
						}
						// cDriver.get().findElement(By.xpath("//body")).sendKeys(Keys.F5);//To
						// refresh Page
						cDriver.get().navigate().refresh();
						Wait = Wait + 10;
						Browser.WebButton.waittillvisible("Submit");
						Thread.sleep(3000);
						CO.waitforload();
						Thread.sleep(3000);
						CO.waitforload();
						Thread.sleep(3000);
						CO.waitforload();
						Browser.WebButton.waittillvisible("Submit");
						CO.waitforload();
						cDriver.get().manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
					}
					Row_Count = Browser.WebTable.getRowCount("Line_Items");
					if (Row_Count <= 2 && Browser.WebButton.exist("Expand"))
						Browser.WebButton.click("Expand");
					System.out.println(Complete_Status);
					System.out.println(R_S);
					System.out.println((Row_Count - 1));
				}
			} while ((R_S < 2) || Wait < 100);
			Browser.WebButton.waittillvisible("Submit");
			// cDriver.get().navigate().refresh();
			CO.waitforload();
			CO.scrollup();
			Result.takescreenshot("");
			Row_Count = Browser.WebTable.getRowCount("Line_Items");
			CO.scroll("Submit", "WebButton");
			Status = Browser.WebTable.getCellData("Line_Items", Row, Col);
			// System.out.println(Row_Count);
			if (Row_Count <= 3) {
				Result.takescreenshot("");
				Browser.WebButton.waittillvisible("Expand");
				Browser.WebButton.click("Expand");
			}

			if (Status.equals(EStatus) || Complete_Status == 2) {
				Complete_Status = 2;
				System.out.println("Completed");
				Driver.Continue.set(true);
			} else
				CO.TroubleTicketCheck();
			if ((Complete_Status == 2) & Driver.Continue.get()) {
				Status = "PASS";
				Utlities.StoreValue("ÖrderSubmission", Utlities.FetchStoredValue("Validation"));
				Result.takescreenshot("Order Submission Passed");
				Driver.resultdescription.set("Order Submitted Successfully");
			} else {
				Status = "FAIL";
				Driver.Continue.set(false);
				Result.takescreenshot("Order Submission Failed");
				Driver.resultdescription.set("Order Submition Failed");
			}
			Test_Input = "Order Submission  Event Details";
			Test_OutPut = "Order Submission  Event Details @@ Successful!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Order Submission Event Details";
			Test_OutPut = "Order Submission Event Details @@ Exception Occured!!..";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STAUS @@ " + readpropval("STATUS");
			return Output;

		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: SiebLogout
	 * Arguments			: None
	 * Use 					: Log Out Siebel browser and close the browser window
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static String SiebApp_Logout() {
		try {
			CO.waitforobj("VQ_Acc_Logo", "WebButton");
			CO.scroll("VQ_Acc_Logo", "WebButton");
			Browser.WebButton.waittillvisible("VQ_Acc_Logo");
			Browser.WebButton.click("VQ_Acc_Logo");
			Result.takescreenshot("Siebel Application Logged out");
			CO.waitforobj("VQ_Logout", "WebButton");
			CO.scroll("VQ_Logout", "WebButton");
			Browser.WebButton.waittillvisible("VQ_Logout");
			Browser.WebButton.click("VQ_Logout");
			// System.out.println("Value "+Utlities.FetchStoredValue("OpenSiebelBrowser"));

			cDriver.get().close();
			// cDriver.get().quit();

			if (Driver.Continue.get()) {
				Status = "PASS";
				Driver.resultdescription.set("Sucessfully Logged out");
				// taskKill();
			} else {
				Status = "FAIL";
				// Driver.Continue.set(false);
				Driver.resultdescription.set("Siebel Log Out  Failed");
			}
			Test_Input = "Siebel Logout Event Details";
			Test_OutPut = "Siebel Logout Event Details - @@ SUCCESSFULL";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			Output = "STATUS  @@ " + readpropval("STATUS");
			return Output;
		} catch (Exception e) {
			Status = "FAIL";
			Driver.Continue.set(false);
			System.out.println("Exception occurred *** " + e.getMessage());
			Test_Input = "Siebel Logout Event Details";
			Test_OutPut = "Siebel Logout Event Details - @@ Exception Occured";
			// Driver.storeinproertiesfile(Test_Input + "=" + Test_OutPut);
			// Driver.storeinproertiesfile("STATUS=" + Status);
			Output = "STATUS  @@ " + readpropval("STATUS");
			return Output;
			// System.exit(0);
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: checkPageIsReady
	 * Arguments			: None
	 * Use 					: Checks whether the Page has been loaded completely
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static void checkPageIsReady() {

		JavascriptExecutor js = (JavascriptExecutor) cDriver.get();

		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;

		}

		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: Mouse_Over
	 * Arguments			: None
	 * Use 					: To Perform Move Over on a element
	 * Designed By			: Vinodhini Raviprasad
	 * Last Modified Date 	: 22-March-2017
	--------------------------------------------------------------------------------------------------------*/
	public static void Mouse_Over(WebElement e) {
		Actions builder = new Actions(cDriver.get());
		builder.moveToElement(e).click().build().perform();
	}

	public static void GuidedContactCreation() {

		try {

			// CO.waitforload();
			CO.waitforobj("SP_ID_Expiry", "WebEdit");
			CO.scroll("SP_ID_Expiry", "WebEdit");
			Browser.WebEdit.Set("SP_ID_Expiry", getdata("SP_ID_Expiry"));
			// CO.scroll("SP_Nationality", "WebEdit");
			Browser.WebEdit.Set("SP_Nationality", getdata("SP_Nationality"));
			// CO.scroll("SP_Gender", "WebEdit");
			Browser.WebEdit.Set("SP_Gender", getdata("SP_Gender"));
			// CO.scroll("SP_First", "WebEdit");
			Browser.WebEdit.Set("SP_First", getdata("SP_First"));
			// CO.scroll("SP_Last", "WebEdit");
			Browser.WebEdit.Set("SP_Last", getdata("SP_Last"));// +R.nextInt(100));
			// CO.scroll("SP_DOB", "WebEdit");
			Browser.WebEdit.Set("SP_DOB", getdata("SP_DOB"));
			// CO.scroll("SP_Job", "WebEdit");
			Browser.WebEdit.Set("SP_Job", getdata("SP_Job"));
			// CO.scroll("SP_Email", "WebEdit");
			Browser.WebEdit.Set("SP_Email", getdata("SP_Email"));
			// CO.scroll("SP_Mobile", "WebEdit");
			Browser.WebEdit.Set("SP_Mobile", getdata("SP_Mobile"));
			// CO.scroll("SP_Attachment", "WebButton");
			Result.takescreenshot("Contact created with " + getdata("SP_First") + " " + getdata("SP_Last"));
			if (browser.get().equalsIgnoreCase("android")) {
				CO.upload("SP_Attachment", "storage/self/primary/DCIM/Screenshots/Passport.png");
			} else {
				CO.upload("SP_Attachment", Templete_FLD.get() + "/Guided_Journey/passport.png");
				// CO.upload("SP_Attachment", getdata("Path"));
			}

			Browser.WebButton.waittillvisible("SP_Continue");
			CO.waitmoreforload();
			CO.waitforobj("SP_Continue", "WebButton");
			CO.scroll("SP_Continue", "WebButton");
			Browser.WebButton.click("SP_Continue");

		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	public static void ChangeMSISDNSIM() {
		try {
			cDriver.get().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			int Row_Val = 0, Row_Count, Col, Col_S, Col_F;
			Col = CO.Select_Cell("Line_Items", "Product");
			String MData = "Mobile Service Bundle";
			String SData = "SIM Card";
			String Field;
			// Have to Include Providing SIM no and MSISDN no
			Browser.WebButton.waittillvisible("Expand");
			Browser.WebButton.click("Expand");
			Col_S = CO.Select_Cell("Line_Items", "Service Id");
			Field = (CO.Col_Data(Col_S).trim());
			if (Field.toLowerCase().equals("previous service id"))
				Col_S = CO.Actual_Cell("Line_Items", "Service Id");
			System.out.println(Field = (CO.Col_Data(Col_S).trim()));
			if (currKW.get().contains("Simchange")) {
				Row_Count = Browser.WebTable.getRowCount("Line_Items");
				Row_Val = Row_Val + 2;
				for (int i = 2; i <= Row_Count; i++) {
					String LData = Browser.WebTable.getCellData("Line_Items", i, Col);
					if (SData.equals(LData))
						Row_Val = i;
				}
				Browser.WebTable.click("Line_Items", Row_Val, Col_S);
				Browser.WebTable.SetDataE("Line_Items", Row_Val, Col_S, "Service_Id", getdata("New_SIM"));
				Result.takescreenshot("Providing New SIM no");
			} else if (currKW.get().contains("MSISDNChange")) {
				if ((getdata("ReservationToken").isEmpty())) {
					int Len, COl_STyp, Col_Res, Row = 2;
					String Number, Reserve;
					Browser.WebLink.click("Numbers");
					Row_Count = Browser.WebTable.getRowCount("Numbers");
					if (Row_Count == 1)
						Browser.WebButton.click("Number_Query");
					Browser.WebLink.click("Num_Manage");
					Number = getdata("New_MSISDN");
					Len = Number.length();
					Reserve = Number.substring(3, Len);
					Browser.WebButton.waitTillEnabled("Reserve");
					Browser.WebButton.waittillvisible("Reserve");
					System.out.println(Reserve);
					COl_STyp = CO.Select_Cell("Numbers", "Service Type");
					Col_Res = CO.Select_Cell("Numbers", "(Start) Number");
					System.out.println(COl_STyp + " " + Col_Res);
					Browser.WebTable.SetData("Numbers", Row, COl_STyp, "Service_Type", "Mobile");
					Browser.WebTable.SetData("Numbers", Row, Col_Res, "Service_Id", Reserve);
					// Browser.WebButton.click("Number_Go");
					Result.takescreenshot("New MSISDN Number Reservation");
					Browser.WebButton.waittillvisible("Reserve");
					Browser.WebButton.waitTillEnabled("Reserve");
					Browser.WebButton.click("Reserve");
					CO.AlertExist();
					Result.takescreenshot("New MSISDN Number Resered");
					Browser.WebLink.waittillvisible("Line_Items");
					Browser.WebLink.click("Line_Items");
					Browser.WebLink.click("LI_Totals");

				}

				Browser.WebButton.waittillvisible("Expand");
				Browser.WebButton.click("Expand");
				Row_Count = Browser.WebTable.getRowCount("Line_Items");
				for (int i = 2; i <= Row_Count; i++) {
					String LData = Browser.WebTable.getCellData("Line_Items", i, Col);
					if (MData.equals(LData))
						Row_Val = i;
				}
				if (getdata("ReservationToken") == "") {
					Browser.WebTable.click("Line_Items", Row_Val, Col_S + 1);
					Browser.WebTable.click("Line_Items", Row_Val, Col_S);
					Browser.WebTable.Popup("Line_Items", Row_Val, Col_S);
					Row_Count = Browser.WebTable.getRowCount("Number_Selection");
					if (Row_Count > 1)
						Browser.WebButton.click("Reserved_Ok");
					else
						Driver.Continue.set(false);
				} else {
					Browser.WebTable.click("Line_Items", Row_Val, Col_S);
					Browser.WebTable.SetDataE("Line_Items", Row_Val, Col_S, "Service_Id", getdata("New_MSISDN"));
				}
			}

			// To get fulfillment status coloumn
			CO.scroll("Ful_Status", "WebButton");
			Col_F = CO.Select_Cell("Line_Items", "Fulfillment Status");
			Field = (CO.Col_Data(Col_F).trim());
			System.out.println(Field);
			if (Field.toLowerCase().equals("fulfillment status"))
				COL_FUL_STATUS = Col_F;
			// Driver.storeinproertiesfile("COL_FUL_STATUS=" + COL_FUL_STATUS);
			CO.scroll("Service", "WebButton");

			Browser.WebButton.waittillvisible("Validate");
			Browser.WebButton.click("Validate");

		} catch (Exception e) {
		}
	}

	// ----------XML-----------------//
	/*
	 * public void XML_NumtoDoller() { KX.XML_NumtoDoller(); }
	 */

}
