package com.training.dataproviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;
import com.training.readexcel.ReadExcel;


public class LoginDataProviders 
{
	private static Properties properties;

	@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}
	
	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"neha@gmail.com", "23445"},
			{"neha", "neha"},
			{"neha", "sharma"},
			{"neha2", "neha2"},
		};
	}
	
	
	

	@DataProvider(name = "excel-inputs")
	public Object[][] getExcelData() throws IOException
	{
		//initialize properties file
		properties = new Properties();
		//Read properties file from given path
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		//load data from properties file
		properties.load(inStream);
		String fileName=properties.getProperty("registrationexcelpath");
			
		return new ApachePOIExcelRead().getExcelContent(fileName); 
		
	}
	
	
	
		
	@DataProvider(name = "xls-inputs")
	public Object[][] getXLSData() throws IOException{
		
		//initialize properties file
				properties = new Properties();
				//Read properties file from given path
				FileInputStream inStream = new FileInputStream("./resources/others.properties");
				//load data from properties file
				properties.load(inStream);
				String fileName=properties.getProperty("registrationexcelpath");
				String sheetName=properties.getProperty("sheet2");
		// ensure you will have the title as first line in the file 
		return new ReadExcel().getExcelData(fileName, sheetName); 
	}
}
