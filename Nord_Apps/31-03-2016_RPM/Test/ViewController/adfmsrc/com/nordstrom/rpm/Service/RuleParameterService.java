package com.nordstrom.rpm.Service;

import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.event.DialogEvent;
import oracle.adf.view.rich.event.QueryEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.event.SortEvent;

public interface RuleParameterService {
    
    public void saveRuleParameter();
    
    public void fetchRuleParameter();
    
    public void onParameterTableQuery(QueryEvent queryEvent);    

    public void cancelChangesDialogListener(DialogEvent dialogEvent);
    
    public void ParameterTableSort(SortEvent sortEvent);
    
    public void wareHousecolorChange(ValueChangeEvent valueChangeEvent);
    
    public void ATWLGr8DogsColor(ValueChangeEvent valueChangeEvent);
    
    public void StoreSOHAttributes(ValueChangeEvent valueChangeEvent);
    
    public void TltSellThruDOGsAttributes(ValueChangeEvent valueChangeEvent);
    
    public void ATSWSAgeAttributes(ValueChangeEvent valueChangeEvent);
    
    public void ATSWLessDOGs(ValueChangeEvent valueChangeEvent);
    
    public void ATSWPerformance(ValueChangeEvent valueChangeEvent);
    
    public void TltSellThruPerformanceAttributes(ValueChangeEvent valueChangeEvent);
    
    public void OnOrderAgeAttributes(ValueChangeEvent valueChangeEvent);
    
    public void WarehouseOnOrderAttributes(ValueChangeEvent valueChangeEvent);
    
    public void StoreOnOrderAttributes(ValueChangeEvent valueChangeEvent);
    
    public void SOHGreaterBrokenAttributes(ValueChangeEvent valueChangeEvent);
    
    public void SOHLessBrokenAttributes(ValueChangeEvent valueChangeEvent);
    
    public void OnOrderBrokenAttributes(ValueChangeEvent valueChangeEvent);
    
    public void BaseRetailAttributes(ValueChangeEvent valueChangeEvent);
    
    public void CurrentRetailAttributes(ValueChangeEvent valueChangeEvent);
    
    public void ATSWLAgeAttributes(ValueChangeEvent valueChangeEvent);
    
}
