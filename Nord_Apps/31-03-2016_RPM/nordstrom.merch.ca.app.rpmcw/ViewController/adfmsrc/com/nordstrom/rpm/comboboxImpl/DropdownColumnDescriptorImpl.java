package com.nordstrom.rpm.comboboxImpl;

import java.util.Collections;
import java.util.Set;

import oracle.adf.view.rich.model.AttributeDescriptor;
import oracle.adf.view.rich.model.ColumnDescriptor;

public class DropdownColumnDescriptorImpl extends ColumnDescriptor 
{
    private String columnName;    
    private Integer columnLength;
    public DropdownColumnDescriptorImpl(String columnName, Integer length)
    {
        super();
        this.columnName = columnName;
        columnLength = length;
    }

    public int getWidth()
    {
        if ( columnLength != null )
        {
            return columnLength.intValue();
        }
        return 10;
    }

    public String getAlign()
    {
        return null;
    }

    public AttributeDescriptor.ComponentType getComponentType()
    {
        return AttributeDescriptor.ComponentType.inputText;
    }

    public String getDescription()
    {
        return null;
    }

    public String getLabel()
    {
        return columnName;
    }

    public int getLength()
    {
        return 0;
    }

    public int getMaximumLength()
    {
        return 0;
    }

    public Object getModel()
    {
        return null;
    }

    public String getName()
    {
        return columnName;
    }

    public Set<AttributeDescriptor.Operator> getSupportedOperators()
    {
        return Collections.emptySet();
    }

    public Class getType()
    {
        return String.class;
    }

    public boolean isReadOnly()
    {
        return true;
    }

    public boolean isRequired()
    {
        return false;
    }
}
