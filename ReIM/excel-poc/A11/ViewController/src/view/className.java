package com.khadeer.view;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class  className {
	
public static void main(String[] args) throws BiffException, IOException, WriteException {
	
		
		//reading excel sheet by using Workbook. here we need jxl.jar file
		  Workbook workbook = Workbook.getWorkbook(new File("D:/file.xls"));
		
		//finding sheet number in excel sheet. ex: sheet1  
	      Sheet sheet = workbook.getSheet(0);
	      
	      int firstNamePosition = 0;
	      int lastNamePosition = 0;
	   
	    //Finding FirstName Position   
	      while(!sheet.getCell(firstNamePosition, 0).getContents().equalsIgnoreCase("FirstName")){
		    	 firstNamePosition++;
		  }
		  
	    ////Finding SecondName Position  
		  while(!sheet.getCell(lastNamePosition, 0).getContents().equalsIgnoreCase("LastName")){
		    	 lastNamePosition++;
		  }
		 
		 //Finding no.of Rows for Running the loop 
	      int rows = sheet.getRows();
		
	    // Running Loop start from '1' position. because headings there at '0' position. employee data starts from second row.  
		  for(int i=1;i<rows;i++){
			  
		   //reading FirstName, LastName content every time. because creating files with these names	  
			String FirstName =  sheet.getCell(firstNamePosition, i).getContents();
			String LastName =  sheet.getCell(lastNamePosition, i).getContents();
		
	      //Creating files in D:/Files folder. This folder must have in D: drive		
			File file = new File("D:/Files/"+FirstName+"_"+LastName+".xls");
    		try{
    			
    			// if file doesn't exists, then create it
    			if (!file.exists()) {
    				file.createNewFile();
    			}
    			
    			 //This return all the header values in employee excel file
    				Cell[] columnNumber = sheet.getRow(0);
    				
    			 //This return all the details of employee in each iteration. 
    				Cell[] empData = sheet.getRow(i);
    				
    			 //Creating WritableWorkbook by passing input file as newly created file for employee	
    				WritableWorkbook writableWorkbook =  Workbook.createWorkbook(file);
    			
    			 //Creating Sheet in that excel file	
    				WritableSheet writableSheet = writableWorkbook.createSheet("sheet1", 0);
        			
        			for(int x=0;x<sheet.getRow(0).length;x++){
        			  
        			 // Reading header values
        				Label tableHeadings = new Label(0, x, columnNumber[x].getContents());
        			 // Reading employee values in each iteration	
        				Label emp = new Label(1, x, empData[x].getContents());
        			
        			 //add header values into excel sheet	
        				writableSheet.addCell(tableHeadings);
        			 //add employee data into excel sheet	
            			writableSheet.addCell(emp);
        				
        			}
        	// This write() method write all the above header data and employee data into current excel sheet.		
        		writableWorkbook.write();
        	// Closing the Book	
        		writableWorkbook.close();

        	        
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
			
			 
		}
		  
		  System.out.println("Done...! ");
		  System.out.println("Open D:/Files folder to view all emp Sheets..");

		  
   }     

}