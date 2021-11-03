package com.infosys.nordstrom.reim;


import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.Iterator;


import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class invoiceUploadBean {
    public invoiceUploadBean() {
        super();
    }
    private HSSFWorkbook wb;

    public void uploadActionListener(ActionEvent actionEvent)
    {
        if(null != actionEvent)
        {
            
        }
    }

    public void browseVCL(ValueChangeEvent valueChangeEvent) throws IOException
    {
        // Add event code here...
        if (valueChangeEvent != null)
        {
            int i=0;
            UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
            wb = new HSSFWorkbook(file.getInputStream());
            try 
            {
                if(wb !=null)
                {
                    HSSFSheet sheet=wb.getSheet("Sheet1");
                    HSSFCell cell;
                    HSSFRow row;
                   Iterator rows = sheet.rowIterator();
                   while(rows.hasNext())
                   {
                       if(i == 0)
                       {
                          rows.next();
                          i=1;
                          continue;
                       }
                      row = (HSSFRow)rows.next();
                      Iterator<Cell> cellIterator=   row.cellIterator();
                       while(cellIterator.hasNext())
                       {
                         cell = (HSSFCell)cellIterator.next();
                        
                          int columnIndex=cell.getColumnIndex();
                           String valueofCell =cell.toString();
                           System.err.println(columnIndex + "/t" +valueofCell);
                       }
                   }
                    
                }
            }
            catch(Exception e)
            {
            e.printStackTrace();    
            }
        }
    }
}

