package generateReports
import java.awt.*
import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.relevantcodes.extentreports.ExtentReports;

import internal.GlobalVariable


public class GenerateReport {

	@Keyword
	def create(String ReportName , String BrowserName , String BrowserVersion){
		def filePath = (RunConfiguration.getProjectDir() + '/ExtentReports/')
		def date = new Date()
		def path = filePath+ReportName
		println("file name from keyword " +path)
		def extent = new ExtentReports(path,false)
		extent.addSystemInfo("BrowserName", BrowserName.capitalize())
		extent.addSystemInfo("BrowserVersion", BrowserVersion)
		return extent
	}


	@Keyword
	def getDateWala() {
		def date = new Date()
		def sdf = new SimpleDateFormat("dd_MM_yyyy_HHmmss")
		def start_time = sdf.format(date)
		println start_time
	}
}
