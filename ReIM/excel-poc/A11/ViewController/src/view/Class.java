package view;

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

class Class
{
		public static void readCreate()
		{
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
					HSSFSheet sheet = workbook.getSheetAt(0);
					HSSFRow row;
					HSSFCell cell;
					Iterator rows=sheet.rowIterator();
					int j=0;
					while(rows.hasNext())
					{
						row=(HSSFRow)rows.next();
						Iterator cells=row.cellIterator();
						Row r;
						int k=0;
						if(i==1&&j==0)
					    {
							j++;
							continue;							
						}
						if(i==1 && j>0)
						{
							int temp=rownum-1;
							r=firstSheet.createRow(temp);
						}
						else
						{
							r=firstSheet.createRow(rownum);
						}
						while(cells.hasNext())
						{
							cell=(HSSFCell)cells.next();
							System.out.println(cell.toString()+"\n");
							Cell ccell=r.createCell(k);
							ccell.setCellValue(cell.toString());
							k++;
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
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
//		public Static void main(String args[])
//		{
//			try
//			{
//				ArrayList<String> strArr=new ArrayList<String>();
//				strArr.add("D://a.xls")
//				strArr.add("D://b.xls")
//				readCreate(strArr);
//			}
//			catch(Exception e)
//			{
//				System.out.println("Exception :"+e);
//			}
//		}

    public void hi(ActionEvent actionEvent) 
    {
       System.out.println("in Action Method..");
//        ArrayList<String> strArr=new ArrayList<String>();
//        strArr.add("D://a.xls");
//        strArr.add("D://b.xls");
//        int rownum=0;
//        HSSFWorkbook workbook= new HSSFWorkbook();
//        HSSFSheet    firstSheet=workbook.createSheet("FIRST SHEET");
//        int i=0;
//        FileOutputStream fos=null;
//        try
//        {
//                for(String s:strArr)
//                {
//                        System.out.println("-----------------"+s+"----------------");
//                        InputStream excelFileRead=new FileInputStream(s);
//                        HSSFWorkbook wb = new HSSFWorkbook(excelFileRead);
//                        HSSFSheet sheet = workbook.getSheetAt(0);
//                        HSSFRow row;
//                        HSSFCell cell;
//                        Iterator rows=sheet.rowIterator();
//                        int j=0;
//                        while(rows.hasNext())
//                        {
//                                row=(HSSFRow)rows.next();
//                                Iterator cells=row.cellIterator();
//                                Row r;
//                                int k=0;
//                                if(i==1&&j==0)
//                            {
//                                        j++;
//                                        continue;                                                       
//                                }
//                                if(i==1 && j>0)
//                                {
//                                        int temp=rownum-1;
//                                        r=firstSheet.createRow(temp);
//                                }
//                                else
//                                {
//                                        r=firstSheet.createRow(rownum);
//                                }
//                                while(cells.hasNext())
//                                {
//                                        cell=(HSSFCell)cells.next();
//                                        System.out.println(cell.toString()+"\n");
//                                        Cell ccell=r.createCell(k);
//                                        ccell.setCellValue(cell.toString());
//                                        k++;
//                                }
//                                rownum++;
//                                j++;
//                                System.out.println();
//                        }
//                        i++;
//                }
//                
//                fos=new FileOutputStream(new File("D://file.xls"));
//                HSSFCellStyle hsfstyle=workbook.createCellStyle();
//                hsfstyle.setBorderBottom((short) 1);
//                hsfstyle.setFillBackgroundColor((short)245);
//                workbook.write(fos);
//        }
//        catch(Exception e)
//        {
//                e.printStackTrace();
//        }
        }
}

