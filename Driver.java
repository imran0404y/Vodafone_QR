package Libraries;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class Driver {

	static Calendar cal = Calendar.getInstance();
	public static ThreadLocal<String> WorkingDir = new ThreadLocal<String>();
	public static ThreadLocal<String> Base_Path = new ThreadLocal<String>();
	public static ThreadLocal<String> Storage_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> OR_File = new ThreadLocal<String>();
	public static ThreadLocal<String> StoreDB_File = new ThreadLocal<String>();
	public static ThreadLocal<String> DataBase_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> TestDataDB_File = new ThreadLocal<String>();
	public static ThreadLocal<String> Result_FLD = new ThreadLocal<String>();
	public static ThreadLocal<String> Templete_FLD = new ThreadLocal<String>();

	public static int passUC = 0;
	public static int failUC = 0;
	public static int totalUC = 0;

	public static ThreadLocal<Boolean> Continue = new ThreadLocal<Boolean>();
	public static ThreadLocal<String> ExecutionStarttimestr = new ThreadLocal<String>();
	public static ThreadLocal<String> UseCaseName = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseN = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseData = new ThreadLocal<String>();
	public static ThreadLocal<String> TestCaseDes = new ThreadLocal<String>();
	public static ThreadLocal<String> currUCstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> currKWstatus = new ThreadLocal<String>();
	public static ThreadLocal<String> currKW = new ThreadLocal<String>();
	public static ThreadLocal<String> currKW_DB = new ThreadLocal<String>();
	public static ThreadLocal<String> currKW_Des = new ThreadLocal<String>();
	public static ThreadLocal<String> browser = new ThreadLocal<String>();
	public static ThreadLocal<String> keywordstartdate = new ThreadLocal<String>();
	public static ThreadLocal<String> resultdescription = new ThreadLocal<String>();

	@SuppressWarnings("rawtypes")
	public static ThreadLocal<Dictionary> TestData = new ThreadLocal<Dictionary>();
	protected static ThreadLocal<WebDriver> cDriver = new ThreadLocal<WebDriver>();

	public static void main(String[] args) throws IOException {
		System.out.println("Intialization");
		killexeTask();

		WorkingDir.set(System.getProperty("user.dir").replace("\\", "/"));
		Base_Path.set(WorkingDir.get() + "/Framework");
		Storage_FLD.set(Base_Path.get() + "/Storage");
		OR_File.set(Storage_FLD.get() + "/ObjectRepository.xlsx");
		StoreDB_File.set(Storage_FLD.get() + "/StoreDB.xlsx");
		DataBase_FLD.set(Base_Path.get() + "/DataBase");
		TestDataDB_File.set(DataBase_FLD.get() + "/TestDataDB.xlsx");
		Result_FLD.set(Base_Path.get() + "/Results");
		Templete_FLD.set(Base_Path.get() + "/Templates");
		File resfold = new File(Result_FLD.get());
		if ((!resfold.exists()))
			resfold.mkdir();
		//Continue.set(true);

		DateFormat ExecutionStarttime = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		ExecutionStarttimestr.set(ExecutionStarttime.format(cal.getTime()).toString());
		System.out.println("Execution initiated at ---> " + ExecutionStarttime.format(cal.getTime()));

		ArrayList<String[]> addUsecase = Utlities.floadUseCases();
		String[] totUseCases = addUsecase.get(0);
		String[] totTestCases = addUsecase.get(1);
		String[] totUseCases_data = addUsecase.get(2);
		String[] totTestcase_Des = addUsecase.get(3);
		totalUC = totUseCases.length;

		for (int currUseCase = 0; currUseCase < totalUC; currUseCase++) {
			Continue.set(true);
			UseCaseName.set(totUseCases[currUseCase]);
			TestCaseN.set(totTestCases[currUseCase]);
			TestCaseData.set(totUseCases_data[currUseCase]);
			TestCaseDes.set(totTestcase_Des[currUseCase]);
			Result.fCreateReportFiles(UseCaseName.get());
			ArrayList<String[]> addresses = Utlities.floadKeywords(UseCaseName.get());
			String totKeywords[] = addresses.get(0);
			String DataBinding[] = addresses.get(1);
			String Description[] = addresses.get(2);

			System.out.println("No of Kaywords to be executed in " + UseCaseName.get() + ":" + totKeywords.length);
			currUCstatus.set("Pass");
			Result.createTCScreenshotFold();

			for (int currKeyword = 0; currKeyword < totKeywords.length; currKeyword++) {
				if (Continue.get() == true) {
					DateFormat currkeywordstartdate = new SimpleDateFormat("dd-MMM-yyyy");
					keywordstartdate.set(currkeywordstartdate.format(cal.getTime()).toString());

					currKW.set(totKeywords[currKeyword]);
					currKW_DB.set(DataBinding[currKeyword]);
					currKW_Des.set(Description[currKeyword]);
					System.out.println("Current Keyword -> " + currKW.get());
					currKWstatus.set("Pass");
					if (currKW_DB.get().toString().equalsIgnoreCase("Data")) {
						TestData.set((Dictionary<?, ?>) Utlities.freaddata(TestCaseData.get()));
					} else {
						TestData.set((Dictionary<?, ?>) Utlities.freaddata_diff(currKW_DB.get()));
					}

					Class<?> noparams[] = {};
					Class<?> cls;

					try {
						cls = Class.forName("Libraries.KeyWord");
						Object obj = cls.newInstance();
						java.lang.reflect.Method method = cls.getDeclaredMethod(currKW.get(), noparams);
						method.invoke(obj);
					} catch (Exception e) {
						e.printStackTrace();
					}

					System.out.println("----->" + currKW.get() + "  :" + currKWstatus.get());
					if (Continue.get() == true) {
						currKWstatus.set("Pass");
						Continue.set(true);
					} else {
						currKWstatus.set("Fail");
						Continue.set(false);
					}
				}
				
			}
			if (currKWstatus.get().equalsIgnoreCase("Fail")) {
				failUC = failUC + 1;
				currUCstatus.set("Fail");
			} else {
				passUC = passUC + 1;
			}
			Result.fcreateMasterHTML();
		}
	}

	public static String getdata(String colname) {
		String c = "";
		try {
			c = KeyWord.TestData.get().get(colname).toString();
			return c;
		} catch (Exception e) {
			return c;
		}

	}

	public static String readpropval(String propname) {
		try {
			String fpropname = "";
			// if (currtcite.get() > 1) {
			fpropname = UseCaseName.get() + "_" + propname + "_" + currKW.get();
			/*
			 * } else { fpropname = UseCaseName.get() + "_" + propname; }
			 */
			String storepth = Storage_FLD.get() + "/Store.properties";
			String fstorepth = storepth;
			FileReader reader = new FileReader(fstorepth);
			Properties properties = new Properties();
			properties.load(reader);
			String propval = properties.getProperty(fpropname);
			return propval;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void killexeTask() {
		try {
			Runtime.getRuntime().exec("taskkill /im chrome.exe /f");
			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			Runtime.getRuntime().exec("taskkill /im conhost.exe /f");
			Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
			Runtime.getRuntime().exec("taskkill /im IEDriverServer.exe /f");
			Runtime.getRuntime().exec("taskkill /im iexplore.exe /f");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
