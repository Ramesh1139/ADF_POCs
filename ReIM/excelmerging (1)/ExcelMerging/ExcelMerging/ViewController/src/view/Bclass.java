package view;

import javax.faces.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import javax.faces.event.ActionEvent;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class Bclass {
    public Bclass() {
    }

    public void ttt(ActionEvent actionEvent) {
      
                System.out.println("in Action Method..");
                ArrayList<String> strArr=new ArrayList<String>();
                strArr.add("D://a.xls");
                strArr.add("D://b.xls");
                int rownum=0;
                HSSFWorkbook workbook= new HSSFWorkbook();
                HSSFSheet    firstSheet=workbook.createSheet("FIRST SHEET");
                int i=0;
                FileOutputStream fos=null;
                try
                {
                        for(String s:strArr)
                        {
                                System.out.println("-----------------"+s+"----------------");
                                InputStream excelFileRead=new FileInputStream(s);
                                HSSFWorkbook wb = new HSSFWorkbook(excelFileRead);
                                HSSFSheet sheet = wb.getSheetAt(0);
                                HSSFRow row;
                                HSSFCell cell;
                                Iterator rows=sheet.rowIterator();
                                int j=0;
                                while(rows.hasNext())
                                {
                                        row=(HSSFRow)rows.next();
//                                        Iterator cells=row.cellIterator();
//                                        Row r=null;
                                        Iterator<Cell> cellIterator = row.cellIterator();
                                        Row r= firstSheet.createRow(rownum);
                                        if(i==1&&j==0)
                                        {
                                            j++;
                                            continue;
                                        }
                                        while(cellIterator.hasNext())
                                        {
                                                cell=(HSSFCell)cellIterator.next();
                                                System.out.println(cell.getColumnIndex()+"\t"+cell.toString());
                                                Cell ccell=r.createCell(cell.getColumnIndex());
                                                ccell.setCellValue(cell.toString());
                                           
                                        }
                                        rownum++;
                                        j++;
                                        System.out.println();
                                }
                                i++;
                        }
        
                        fos=new FileOutputStream(new File("D://file.xls"));
                        HSSFCellStyle hsfstyle=workbook.createCellStyle();
                        hsfstyle.setBorderBottom((short) 1);
                        hsfstyle.setFillBackgroundColor((short)245);
                        workbook.write(fos);
                        fos.close();
                }
                catch(Exception e)
                {
                        e.printStackTrace();
                }                
        
    }
}
