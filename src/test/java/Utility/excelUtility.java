package Utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow rw;
	public static XSSFCell cl;
	public static CellStyle cs;
	String path;
	
	public excelUtility(String path) {
		this.path = path;
		
	}
	
	public int getRowCount( String xlsheet) throws IOException {
		
		//to get row count
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		int rowCount = sh.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
		
	
	}
	
	
	public int getCellCount( String xlsheet, int rownum) throws IOException {
		//get the cell count
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		rw = sh.getRow(rownum);
		int cellCount = rw.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData( String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		rw = sh.getRow(rownum);
		cl = rw.getCell(colnum);
	
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cl);//format the cell into into string
			
		}
		catch(Exception e){
			data ="";
		}
		
		wb.close();
		fi.close();
		
		return data;
	}
	
	
	public void setCellData( String xlsheet, int rownum, int colnum, String data) throws IOException{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		rw = sh.getRow(rownum);
		cl = rw.getCell(colnum);
		cl.setCellValue(data);
		
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
	}
	
	
	public void fillGreenColour( String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		rw = sh.getRow(rownum);
		cl = rw.getCell(colnum);
		cs = wb.createCellStyle();
		
		cs.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cl.setCellStyle(cs);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
		
		
	}
	
	
	public void fillRedColour( String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sh = wb.getSheet(xlsheet);
		rw = sh.getRow(rownum);
		cl = rw.getCell(colnum);
		cs = wb.createCellStyle();
		
		cs.setFillBackgroundColor(IndexedColors.RED.getIndex());
		cs.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cl.setCellStyle(cs);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		
		
		
	}

}
