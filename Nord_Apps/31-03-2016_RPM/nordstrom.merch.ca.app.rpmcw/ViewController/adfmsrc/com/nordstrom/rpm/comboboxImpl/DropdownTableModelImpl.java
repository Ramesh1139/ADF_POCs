package com.nordstrom.rpm.comboboxImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import oracle.adf.view.rich.model.ColumnDescriptor;
import oracle.adf.view.rich.model.TableModel;

import org.apache.myfaces.trinidad.model.CollectionModel;

public class DropdownTableModelImpl extends TableModel 
{
    List<DropdownDataItem> list;
    public DropdownTableModelImpl(List<DropdownDataItem> dataList)
    {
        super();
        this.list = dataList;
    }

    public CollectionModel getCollectionModel()
    {
        return new DropdownCollectionModel(list);
    }

    public List<ColumnDescriptor> getColumnDescriptors()
    {
        List<ColumnDescriptor> descriptors = new ArrayList<ColumnDescriptor>();
        
        descriptors.add(new DropdownColumnDescriptorImpl("description", null));
        
        return descriptors;
    }
}
