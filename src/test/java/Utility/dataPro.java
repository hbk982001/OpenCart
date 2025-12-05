package Utility;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class dataPro {
	
	//name should be unique for each data provider
			@DataProvider(name="testLogin")
			public String [][] getData() throws IOException{
				String path =".\\testData\\testInput.xlsx"; //taking excel from test data
				
				excelUtility ex = new excelUtility(path);//create object of excelutility class and pass the path
				
				int cellCount = ex.getCellCount("Sheet1", 1);
				int rowCount = ex.getRowCount("Sheet1");
				
				String newData[][] = new String [rowCount] [cellCount];//definng the length of the array
				
				for(int i=1;i<=cellCount;i++) {
					
					for(int j=0;j<rowCount;j++) {
						
						newData[i-1] [j]= ex.getCellData("Sheet1", i, j); //getting the data from excel
					}
				}
				return newData;
			}
}
