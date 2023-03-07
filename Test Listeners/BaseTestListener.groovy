import java.text.SimpleDateFormat

import org.openqa.selenium.Capabilities
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.events.EventFiringWebDriver


import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

class BaseTestListener {
	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def BeforeTestSuite(TestSuiteContext testSuiteContext)
	{
		String execID = RunConfiguration.getExecutionSourceName()
		def Browser=GlobalVariable.G_Browser
		
		def filePath = (RunConfiguration.getProjectDir() + '/ExtentReports/'+execID+Browser+'.txt')
		def reportFliePath=RunConfiguration.getReportFolder()
		GlobalVariable.G_TestSuite=execID
		GlobalVariable.G_ReportFolder=reportFliePath
		GlobalVariable.G_ConfigFile=filePath
		
		def date = new Date()
		def sdf = new SimpleDateFormat("ddMMyyyy_HHmmss")
		def execTime = sdf.format(date)
		String execTag=Browser+'_'+execTime
		GlobalVariable.G_ReportName=execID+'_'+execTag
		println ("From Brefore Suite")
		println(RunConfiguration.getReportFolder())
	}

	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {

		WebUI.openBrowser('')
		WebUI.deleteAllCookies()
		WebUI.navigateToUrl(GlobalVariable.G_BaseUrl)
		WebUI.maximizeWindow()
		WebDriver driver = DriverFactory.getWebDriver()

		Capabilities caps =((RemoteWebDriver) (((EventFiringWebDriver) driver).getWrappedDriver())).getCapabilities()

		

		GlobalVariable.G_BrowserVersion = caps.getVersion()
		//WebDriver driver = new RemoteWebDriver(new URL("https://10.75.57.67:5556/wd/hub"), caps);
		println("============================================================")
		println (" browser - "+GlobalVariable.G_Browser)
		println (" version - "+GlobalVariable.G_BrowserVersion)
		println testCaseContext.getTestCaseId()
		println testCaseContext.getTestCaseVariables()
		println("============================================================")

	}
	@AfterTestSuite
	def AfterTestSuite() {
		println ("*****************************************************************")
		println("After Suite ")
		println ("*****************************************************************")
		def Browser=GlobalVariable.G_Browser
		def execID=GlobalVariable.G_TestSuite
		def reportFliePath=GlobalVariable.G_ReportFolder
		def filePath=GlobalVariable.G_ConfigFile
		println("Filepath - ---- " + filePath)
		String [] csvFP=reportFliePath.split('\\\\')
		def s=csvFP.size()
		String csvfileName=csvFP[s-1]+'.csv'
		String updatedPath = reportFliePath.replaceAll("\\\\","\\/");
		println ("---------:"+updatedPath)
		def ReportName=GlobalVariable.G_ReportName+".html"
		BufferedWriter brw = new BufferedWriter(new FileWriter(filePath, false))
		brw.write("Module="+execID)
		brw.newLine()
		brw.write("csvfile="+updatedPath+"/"+csvfileName)
		brw.newLine()
		brw.write("browser="+Browser)
		brw.newLine()
		brw.write("reportName="+ReportName)
		reportFliePath
		brw.close()
	}

}