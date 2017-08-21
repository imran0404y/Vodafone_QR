package Libraries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Result {

	public static ThreadLocal<String> logfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> masterrephtml = new ThreadLocal<String>();
	public static ThreadLocal<String> UCscreenfilepth = new ThreadLocal<String>();
	public static ThreadLocal<String> TCscreenfile = new ThreadLocal<String>();
	public static String updatelogmsg = "";

	public static void fCreateReportFiles(String Usecase) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Calendar cal = Calendar.getInstance();
		try {

			File resfold = new File(Driver.Result_FLD.get() + "/" + dateFormat.format(cal.getTime()));
			if ((!resfold.exists()))
				resfold.mkdir();

			String timefold = Driver.ExecutionStarttimestr.get().replace(":", "-").replace(" ", "_");
			File tresfold = new File(resfold + "/" + timefold);
			if ((!tresfold.exists()))
				tresfold.mkdir();

			UCscreenfilepth.set(tresfold + "/" + Usecase);
			File bthresfold = new File(tresfold + "/" + Usecase);
			if ((!bthresfold.exists()))
				bthresfold.mkdir();

			File scriptfold = new File(tresfold + "/Scripts");
			if ((!scriptfold.exists()))

				scriptfold.mkdir();

			String tempref = Driver.Templete_FLD.get();
			File scriptrepsource = new File(tempref + "/Scripts");

			FileUtils.copyDirectory(scriptrepsource, scriptfold);

			logfilepth.set(bthresfold + "/Logs.txt");

			File logfile = new File(logfilepth.get());
			if (!logfile.exists()) {
				logfile.createNewFile();
			}

			File masterhtml = new File(tempref + "/MasterReport.HTML");

			FileUtils.copyFileToDirectory(masterhtml, tresfold);

			masterrephtml.set(tresfold.toString() + "/MasterReport.HTML");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void fUpdateLog(String logmessage) throws IOException {
		File logfile = new File(logfilepth.get());
		FileWriter fw = new FileWriter(logfile.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(logmessage + "\r\n");
		bw.close();
	}

	/*---------------------------------------------------------------------------------------------------------
	 * Method Name			: takescreenshot
	 * Arguments			: 
	 * Use 					: take screenshot and save it in screen shots folder
	 * Designed By			: AG
	 * Last Modified Date 	: 25-Apr-2016
	--------------------------------------------------------------------------------------------------------*/
	public static void takescreenshot(String LogMessage) {
		try {
			// Driver.scrnno.set(Driver.scrnno.get() + 1);
			File scrFile = ((TakesScreenshot) KeyWord.cDriver.get()).getScreenshotAs(OutputType.FILE);
			CustomXWPFDocument document = new CustomXWPFDocument(new FileInputStream(new File(TCscreenfile.get())));
			FileOutputStream fos = new FileOutputStream(new File(TCscreenfile.get()));
			String id = document.addPictureData(new FileInputStream(new File(scrFile.toString())),
					Document.PICTURE_TYPE_PNG);
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText(LogMessage);
			document.createPicture(id, document.getNextPicNameNumber(Document.PICTURE_TYPE_PNG), 640, 420);
			document.write(fos);
			fos.flush();
			fos.close();
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * -----------------------------------------------------------------------------
	 * --------------------------- Method Name : fcreateMasterHTML Arguments : Use :
	 * Create HTML Master report Designed By : AG Last Modified Date : 25-Apr-2016
	 * -----------------------------------------------------------------------------
	 * ---------------------------
	 */
	public static void fcreateMasterHTML() throws IOException {
		File logfile = new File(masterrephtml.get());
		FileWriter fw = new FileWriter(logfile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		String logmessage = "<!-- saved from url=(0016)http://localhost -->" + "<html>" + "<head>"
				+ "<title>Execution Results</title>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"
				+ "<link rel=\"stylesheet\" href=\"" + "Scripts\\style.css\" type=\"text/css\">" + "<script src=\""
				+ "Scripts\\amcharts.js\" type=\"text/javascript\"></script>" + "<style>" + "table {font-size: 12px;"
				+ "background:#E6E6E6;" + "}" + "</style>" + "<script>" + "var chart;" + "var chartData = [{"
				+ "Status: \"Pass\"," + "Count:" + Driver.passUC + "}, {" + "Status: \"Fail\"," + "Count:"
				+ Driver.failUC + "}];" + "AmCharts.ready(function () {" + "chart = new AmCharts.AmPieChart();" +
				// "chart.addTitle(\"Execution Status\", 16);"+
				"chart.dataProvider = chartData;" + "chart.titleField = \"Status\";" + "chart.valueField = \"Count\";"
				+ "chart.sequencedAnimation = true;" + "chart.startEffect = \"elastic\";"
				+ "chart.innerRadius = \"30%\";" + "chart.startDuration = 2;" + "chart.labelRadius = 5;"
				+ "chart.depth3D = 12;" + "chart.angle = 20;" + "chart.write(\"chartdiv\");" + "});" + "</script>" +
				// "</script>"+
				"</head>" + "<body bgcolor = \"green\">" + "<div id = \"lastres\">" + "<table width='100%' border=2>"
				+ "<tr>" + "<td border='0'>" + "<img src ='" + "Scripts\\"
				+ "Client-logo.jpg' height = 30% width = 100%>" + "</td>" + "<td width =70% Style=\"color:green\">"
				+ "<center><h1> Master Report </h1>  </center>" + "</td>" + "<td border='0'>" + "<img src ='"
				+ "Scripts\\" + "maveric-logo.jpg' height = 30% width = 100%>" + "</td>" + "</tr>"
				+ "<table width='100%' border=2>" + "<tr>"
				+ "<td align=\"center\" width='50%' colspan=2><h3>Execution overview </h3></td>"
				+ "<td align=\"center\" width='50%' colspan=2><h3>Execution status </h3></td>" + "</tr>" + "<tr>"
				+ "<td width='50%' align=\"center\" colspan = 2><div id=\"chartdiv\" style=\"width:450px; height:150px;\"></div></td>"
				+ "<td valign ='top'>" + "<table border =1 width = 100%>" + "<tr>"
				+ "<td align=\"center\"><b>Total</b></td>" + "<td align=\"center\"><b>Pass</b></td>"
				+ "<td align=\"center\"><b>Fail</b></td>" + "</tr>" + "<tr>" + "<td align=\"center\" id=\"tot\">"
				+ Driver.totalUC + "</td>" + "<td align=\"center\" id=\"totpass\">" + Driver.passUC + "</td>"
				+ "<td align=\"center\" id=\"totfail\">" + Driver.failUC + "</td>" + "</tr>" + "</table>" + "</tr>"
				+ "</table>" + "<table width='100%' border=2>" + "<tr>"
				+ "<td align=\"center\" width='50%' colspan=3 Style=\"color:blue\"><h3> Detail Summary Report </h3></td>"
				+ "</tr>" + "<table border =1 width = 100%>" + "<tr>"
				+ "<td width = 15%><b><center>Usecase</center></b></td>"
				+ "<td width = 15%><b><center>TestCase Num</center></b></td>"
				+ "<td width = 45%><b><center>TestCase Data</center></b></td>"
				+ "<td width = 7%><b><center>Status</center></b></td>"
				+ "<td width = 18%><b><center>Remarks</center></b></td>" + "</tr>";
		bw.write(logmessage + "\r\n");
		logmessage = "";
		updatelogmsg = updatelogmsg + "<tr>" + "<td width = 15%><center>" + Driver.UseCaseName.get() + "</center></td>";
		updatelogmsg = updatelogmsg + "<td width = 15%><center><a href = .\\" + Driver.UseCaseName.get()+"/"+Driver.TestCaseN.get()+ ".docx" + ">"
				+ Driver.TestCaseN.get() + "</a></center></td>";
		updatelogmsg = updatelogmsg + "<td width = 45%>" + Driver.TestCaseData.get() + "</td>";
		if (Driver.currUCstatus.get().equals("Pass")) {
			updatelogmsg = updatelogmsg + "<td width = 7% Style=\"color:green\"><b><center>Pass</center></b></td>";
			updatelogmsg = updatelogmsg + "<td width = 18%><center>Successfully Executed<center>" + "" + "</center></td></tr>";
		} else if (Driver.currUCstatus.get().equals("Fail")) {
			updatelogmsg = updatelogmsg + "<td width = 7% Style=\"color:Red\"><b><center>Fail</center></b></td>";
			updatelogmsg = updatelogmsg + "<td width = 18%><center>" + "Failed at " + Driver.currKW_Des.get()
					+ "</center></td></tr>";
		}

		bw.write(updatelogmsg);
		bw.close();
	}

	public static void createTCScreenshotFold() {
		File tcscreenfold = new File(UCscreenfilepth.get() + "/" + Driver.TestCaseN.get() + ".docx");
		TCscreenfile.set(tcscreenfold.toString());
		try {
			@SuppressWarnings("resource")
			XWPFDocument document = new XWPFDocument();
			// Write the Document in file system
			FileOutputStream out = new FileOutputStream(new File(TCscreenfile.get()));
			document.write(out);
			out.close();
		} catch (Exception e) {

		}
	}
}
