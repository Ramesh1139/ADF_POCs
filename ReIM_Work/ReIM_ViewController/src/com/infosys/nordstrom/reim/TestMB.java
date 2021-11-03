package com.infosys.nordstrom.reim;

import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.jbo.Row;
import oracle.jbo.ViewObject;

public class TestMB {
    private Date currentDate;
    public TestMB() {
    }

    public void buttonAction(ActionEvent actionEvent) {
        // Add event code here...
        
        DCBindingContainer dcb = (DCBindingContainer)BindingContext.getCurrent().getCurrentBindingsEntry();
      DCIteratorBinding dci=  dcb.findIteratorBinding("maxId1Iterator");
    //ViewObject vo=  dci.getViewObject();
   Row[] rows= dci.getAllRowsInRange();
   for(Row r:rows)
   {
    int idMax=   Integer.parseInt(r.getAttribute("MaxId")+"");
       System.err.println(idMax);
       
   }
        
        
    }

   private String s;
   
    
    public void abc(ActionEvent action){
        java.util.Date Date= new Date();
        System.out.println(Date);
    }


    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public Date getCurrentDate() throws ParseException {
        java.util.Date Date= new Date();
        System.out.println(Date);
        DateFormat df=new SimpleDateFormat("dd-MMM-yy");
        s= df.format(Date);
        return df.parse(s);
       
    }
}
