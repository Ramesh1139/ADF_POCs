package com.nordstrom.rpm.comboboxImpl;

import java.util.List;

import org.apache.myfaces.trinidad.model.CollectionModel;

public class DropdownCollectionModel extends CollectionModel 
{
    DropdownDataItem row = null;
    int rowIndex = -1;

    List<DropdownDataItem> list;
    public DropdownCollectionModel(List<DropdownDataItem> list)
    {
        super();
        this.list = list;
    }

    public Object getRowKey()
    {
        return row;
        //if ( row != null )
        //    return row.getId();
        //return null;
    }

    public void setRowKey(Object rowKey)
    {
        if ( rowKey == null || list == null )
        {
            row = null;
            return;
        }
        
        //TODO candidate for optimization. Use a Hashmap to store row IDs identified by rowkey.
        int index = -1;
        for ( int i=0 ; i<list.size(); i++)
        {
            String rowId = list.get(i).getId();
            if ( rowId != null && rowId.equals(rowKey) )
            {
                index = i;
            }
            break;
        }
        setRowIndex(index);
    }

    public boolean isRowAvailable()
    {
        return row != null;
    }

    public int getRowCount()
    {
        return list != null? list.size() : 0 ;
    }

    public Object getRowData()
    {
        return row;
    }
    
    public Object getRowDat(int rowIndex)
    {
        int oldIndex = getRowIndex();
        try
        {
            setRowIndex(rowIndex);
            return getRowData();
        }
        finally
        {
            setRowIndex(oldIndex);
        }
    }

    public int getRowIndex()
    {
        return rowIndex;
    }

    public void setRowIndex(int i)
    {
        if ( list == null )
        {
            row = null;
            rowIndex = -1;
        }
        else 
        {
            int listSize = list.size();
            if ( i < 0 || i > listSize || listSize==0 )
            {
                row = null;
                rowIndex = -1;
            }
            else
            {
                row = list.get(i);
                rowIndex = i;
            }
        }
    }

    public Object getWrappedData()
    {
        return DropdownCollectionModel.this;
    }

    public void setWrappedData(Object object)
    {
    }
}
