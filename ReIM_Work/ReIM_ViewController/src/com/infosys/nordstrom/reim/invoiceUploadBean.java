package com.infosys.nordstrom.reim;


import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;


import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;


import javax.faces.context.FacesContext;
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

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;
import oracle.jbo.RowIterator;
import oracle.jbo.ViewObject;

import org.apache.myfaces.trinidad.model.UploadedFile;

import weblogic.entitlement.expression.Empty;

public class invoiceUploadBean {
    private ArrayList<String> excelData_list=new ArrayList<String>();
    public invoiceUploadBean()
    {
        super();
    }
    private HSSFWorkbook wb;
    StringBuffer sb=new StringBuffer();

    public void uploadActionListener(ActionEvent actionEvent)
    {
        if(null != actionEvent)
        {
            DCBindingContainer dcb=(DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
           DCIteratorBinding dci= dcb.findIteratorBinding("Xxinvoice_vo1Iterator");
          ViewObject vo= dci.getViewObject();
          for(String s:excelData_list)
          {
              Row r=vo.createRow();
              String[] s1=s.split(",");
               
            
                  r.setAttribute("Id", s1[0]);
                  r.setAttribute("Name", !s1[1].equals("Empty")?s1[1]:"");
                  r.setAttribute("Effectivedate", s1[2]);
                  r.setAttribute("Status", s1[3]);
                  
              
              vo.insertRow(r);
              
           }
          OperationBinding oBinding=BindingContext.getCurrent().getCurrentBindingsEntry().getOperationBinding("Commit");
          oBinding.execute();
            
        }
    }

    public void browseVCL(ValueChangeEvent valueChangeEvent) throws IOException
    {
        // Add event code here...
        if (valueChangeEvent != null)
        {
           
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
                    excelData_list.clear();
                    //System.out.println("rows length : "+rows.);
                    int rowscount=0;
                   while(rows.hasNext())
                   {
                     
                      row = (HSSFRow)rows.next();
                      Iterator<Cell> cellIterator=   row.cellIterator();
                       sb.delete(0, sb.length());
                       sb.setLength(0);
                       System.out.println("string buffer value befre enter into loop:"+sb.toString());
                       
                       int length=4;
                     //  if(rowscount!=0)
                       //{
                                       for(int j=0;j<length;j++)
                                       {
                                          
                                          if(j!=(length-1))
                                          {
                                                   if(row.getCell(j)!=null)
                                                       sb.append(row.getCell(j).toString()).append(",");
                                                   else
                                                       sb.append("Empty").append(",");
                                          }
                                          else
                                          {
                                              if(row.getCell(j)!=null)
                                                  sb.append(row.getCell(j).toString());
                                              else
                                                  sb.append("Empty");
                                          }
                                          
                                           
                                       }
                                       System.out.println("string buffer value after  loop:"+sb.toString());
                                       String s= sb.toString();
                                       excelData_list.add(s);
                      // }
                       //rowscount=1;
                   }
                    
                }
            }
            catch(Exception e)
            {
            e.printStackTrace();    
            }
        }
    }

    public void setExcelData_list(ArrayList<String> excelData_list) {
        this.excelData_list = excelData_list;
    }

    public ArrayList<String> getExcelData_list() {
        return excelData_list;
    }

    public void generateExcel(FacesContext facesContext,
                              OutputStream outputStream) throws IOException 
    {
        // Add event code here...
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");
        DCBindingContainer dcb =
            (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
        DCIteratorBinding dci =
            dcb.findIteratorBinding("Xxinvoice_vo1Iterator");
        HSSFRow excelRow = null;
    // Get the all rows in Iterator
    Row[] rows=   dci.getAllRowsInRange();
    
    int i=0;
    for(Row r:rows)
    {
                   // Print Header Column Values in the Excel sheet
                    
                    if(i==0)
                    {
                      excelRow = sheet.createRow((short)i);
                      short j=0;
                    
                        for (String colName : r.getAttributeNames())
                        {
                          // Excel sheet Column Heading insertion
                            
                            HSSFCell cellAll=excelRow.createCell(j);
                            cellAll.setCellValue(colName);
                                j++;
                            
                        }
                       
                    }
                    
                    //print data from second row in excel
                    ++i;
                    short j = 0;
        
                    excelRow = sheet.createRow(i);
        
                    for(String columnName:r.getAttributeNames())
                    {
                      HSSFCell cell=excelRow.createCell(j);
                        
                        if (columnName.equalsIgnoreCase("Id"))
                        {
                             cell.setCellValue(r.getAttribute(columnName).toString());
                           // System.out.println("colName "+columnName+"row.getAttribute(colName).toString()"+r.getAttribute(columnName).toString());
                        }
                        if (columnName.equalsIgnoreCase("Name"))
                        {
                            if(null!=r.getAttribute(columnName))
                             cell.setCellValue(r.getAttribute(columnName).toString());
                            else
                            cell.setCellValue("Empty");
                           // System.out.println("colName "+columnName+"row.getAttribute(colName).toString()"+r.getAttribute(columnName).toString());
                        }
                        if (columnName.equalsIgnoreCase("Effectivedate"))
                        {
                             cell.setCellValue(r.getAttribute(columnName).toString());
                           // System.out.println("colName "+columnName+"r.getAttribute(colName).toString()"+r.getAttribute(columnName).toString());
                        }
                        if (columnName.equalsIgnoreCase("Status"))
                        {
                             cell.setCellValue(r.getAttribute(columnName).toString());
                           // System.out.println("colName "+columnName+"r.getAttribute(colName).toString()"+r.getAttribute(columnName).toString());
                        }
                        
                        j++;
                        
                        
                    }
                    sheet.createFreezePane(0, 1, 0, 1);
                    
    }
    wb.write(outputStream);
    outputStream.flush();
 }
    
   

}

