package com.nordstrom.rpm.comboboxImpl;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.ResourceBundle;

import oracle.adf.view.rich.model.ListOfValuesModel;
import oracle.adf.view.rich.model.QueryDescriptor;
import oracle.adf.view.rich.model.QueryModel;
import oracle.adf.view.rich.model.TableModel;

import org.apache.myfaces.trinidad.model.RowKeySet;

public class DropdownLOVModel extends ListOfValuesModel 
{
    private List<DropdownDataItem> listSource;
    private DropdownDataItem selectedRow;
    private DropdownSelectionListener selectionListener;
    
    public DropdownLOVModel(DropdownSelectionListener listener, List<DropdownDataItem> list)
    {
        super();
        this.listSource  = list;
        this.selectedRow = null; //initialize to null;
        this.selectionListener = listener;
    }

    public QueryDescriptor getQueryDescriptor()
    {
        //A null value indicates that a query component is not used for searching a value among a list of values. 
        return null;
    }

    public QueryModel getQueryModel()
    {
        //A null value indicates that a query component is not required for searching a value among a list of values. 
        return null;
    }

    public TableModel getTableModel()
    {
        return (new DropdownTableModelImpl(listSource));
    }

    public List<? extends Object> getItems()
    {
        return listSource;
    }

    public List<? extends Object> getRecentItems()
    {
        return null;
    }

    public boolean isAutoCompleteEnabled()
    {
        return false;
    }

    public void performQuery(QueryDescriptor queryDescriptor)
    {
        
    }

    public List<Object> autoCompleteValue(Object object)
    {
        return null;
    }

    public void valueSelected(Object selection)
    {
        if ( selection != null && selection instanceof RowKeySet)
        {
            Iterator iter = ((RowKeySet)selection).iterator();
            //single selection allowed only. only iterate over the first row
            if (iter.hasNext())
            {
                this.selectedRow = (DropdownDataItem)iter.next();
            }
        }
        else if ( selection != null && selection instanceof ArrayList)
        {
            this.selectedRow = ((ArrayList<DropdownDataItem>)selection).get(0);
        }
        
        if ( selectedRow != null )
        {
            setSelectedValue(selectedRow);
        }
        
        return;
    }
    private void setSelectedValue(DropdownDataItem selectedItem)
    {
        if ( this.selectionListener != null )
        {
            this.selectionListener.valueSelected(selectedItem);
        }
    }

    public void setSelectedRow(DropdownDataItem selectedRow)
    {
        this.selectedRow = selectedRow;
    }

    public Object getSelectedRow()
    {
        return selectedRow;
    }
    
    public Object getValueFromSelection(Object selectedRow)
    {
        //since there will always be a single item selected, get the first item only
        if ( selectedRow != null )
        {
            if ( selectedRow instanceof ArrayList )
            {
                DropdownDataItem item = ((ArrayList<DropdownDataItem>)selectedRow).get(0);
                return item.getDescription();
            }
            else if ( selectedRow instanceof RowKeySet)
            {
                Iterator iter = ((RowKeySet)selectedRow).iterator();
                //single selection allowed only. only iterate over the first row
                if (iter.hasNext())
                {
                    DropdownDataItem selection = (DropdownDataItem)iter.next();
                    return selection.getDescription();
                }
            }
        }
        return null;
    }
}
