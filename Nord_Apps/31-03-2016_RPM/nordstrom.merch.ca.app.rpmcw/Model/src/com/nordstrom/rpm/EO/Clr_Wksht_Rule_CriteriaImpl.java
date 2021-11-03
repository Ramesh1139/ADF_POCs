package com.nordstrom.rpm.EO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Map;

import oracle.adf.share.ADFContext;

import oracle.jbo.AttributeList;
import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.domain.Date;
import oracle.jbo.domain.Number;
import oracle.jbo.server.AttributeDefImpl;
import oracle.jbo.server.EntityDefImpl;
import oracle.jbo.server.EntityImpl;
import oracle.jbo.server.SequenceImpl;
import oracle.jbo.server.TransactionEvent;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Dec 08 16:02:55 IST 2015
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Clr_Wksht_Rule_CriteriaImpl extends EntityImpl {
    private static EntityDefImpl mDefinitionObject;

    /**
     * Add locking logic here.
     */
    public void lock() {
        super.lock();
    }

    /**
     * Custom DML update/insert/delete logic here.
     * @param operation the operation type
     * @param e the transaction event
     */
    protected void doDML(int operation, TransactionEvent e) {
        //we are converting the MM/dd/yyyy date format to dd-MMM-yyyy
        String dateCriteria = getCriteriaValue();
        if(dateCriteria!=null){
        if (dateCriteria.contains("/")) {
                if (operation == DML_INSERT || operation == DML_UPDATE) {
                    
                        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy");
                        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
                        java.util.Date utilDate;
                        java.sql.Date javaSqlDate;

                        try {
                            utilDate = format1.parse(dateCriteria);
                            String formattedDate=format2.format(utilDate);
                            setCriteriaValue(formattedDate);
                        } catch (ParseException f) {
                            f.printStackTrace();
                        }

                    }
                }
        }
        super.doDML(operation, e);
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    public enum AttributesEnum {
        CriteriaId {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getCriteriaId();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setCriteriaId((Number)value);
            }
        }
        ,
        RuleId {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getRuleId();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setRuleId((Number)value);
            }
        }
        ,
        CriteriaColumnName {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getCriteriaColumnName();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setCriteriaColumnName((String)value);
            }
        }
        ,
        CriteriaOperator {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getCriteriaOperator();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setCriteriaOperator((String)value);
            }
        }
        ,
        CriteriaValue {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getCriteriaValue();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setCriteriaValue((String)value);
            }
        }
        ,
        ParamId {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getParamId();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setParamId((Number)value);
            }
        }
        ,
        CreateId {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getCreateId();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setCreateId((String)value);
            }
        }
        ,
        CreateDatetime {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getCreateDatetime();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setCreateDatetime((Date)value);
            }
        }
        ,
        LastUpdateId {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getLastUpdateId();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setLastUpdateId((String)value);
            }
        }
        ,
        LastUpdateDatetime {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getLastUpdateDatetime();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setLastUpdateDatetime((Date)value);
            }
        }
        ,
        SeqNo {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getSeqNo();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setSeqNo((Number)value);
            }
        }
        ,
        Clr_Wksht_Rule1 {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getClr_Wksht_Rule1();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setClr_Wksht_Rule1((Clr_Wksht_RuleImpl)value);
            }
        }
        ,
        Clr_Wksht_Rule {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getClr_Wksht_Rule();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setClr_Wksht_Rule((Clr_Wksht_RuleImpl)value);
            }
        }
        ,
        Clr_Wksht_Criteria_Fields {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getClr_Wksht_Criteria_Fields();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setClr_Wksht_Criteria_Fields((EntityImpl)value);
            }
        }
        ,
        Clr_Wksht_Rule_Param_Head {
            public Object get(Clr_Wksht_Rule_CriteriaImpl obj) {
                return obj.getClr_Wksht_Rule_Param_Head();
            }

            public void put(Clr_Wksht_Rule_CriteriaImpl obj, Object value) {
                obj.setClr_Wksht_Rule_Param_Head((EntityImpl)value);
            }
        }
        ;
        private static AttributesEnum[] vals = null;
        private static final int firstIndex = 0;

        public abstract Object get(Clr_Wksht_Rule_CriteriaImpl object);

        public abstract void put(Clr_Wksht_Rule_CriteriaImpl object,
                                 Object value);

        public int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        public static final int firstIndex() {
            return firstIndex;
        }

        public static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        public static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }
    public static final int sequenceId =0;


    public static final int CRITERIAID = AttributesEnum.CriteriaId.index();
    public static final int RULEID = AttributesEnum.RuleId.index();
    public static final int CRITERIACOLUMNNAME = AttributesEnum.CriteriaColumnName.index();
    public static final int CRITERIAOPERATOR = AttributesEnum.CriteriaOperator.index();
    public static final int CRITERIAVALUE = AttributesEnum.CriteriaValue.index();
    public static final int PARAMID = AttributesEnum.ParamId.index();
    public static final int CREATEID = AttributesEnum.CreateId.index();
    public static final int CREATEDATETIME = AttributesEnum.CreateDatetime.index();
    public static final int LASTUPDATEID = AttributesEnum.LastUpdateId.index();
    public static final int LASTUPDATEDATETIME = AttributesEnum.LastUpdateDatetime.index();
    public static final int SEQNO = AttributesEnum.SeqNo.index();
    public static final int CLR_WKSHT_RULE1 = AttributesEnum.Clr_Wksht_Rule1.index();
    public static final int CLR_WKSHT_RULE = AttributesEnum.Clr_Wksht_Rule.index();
    public static final int CLR_WKSHT_CRITERIA_FIELDS = AttributesEnum.Clr_Wksht_Criteria_Fields.index();
    public static final int CLR_WKSHT_RULE_PARAM_HEAD = AttributesEnum.Clr_Wksht_Rule_Param_Head.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Clr_Wksht_Rule_CriteriaImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        if (mDefinitionObject == null) {
            mDefinitionObject = EntityDefImpl.findDefObject("com.nordstrom.rpm.EO.Clr_Wksht_Rule_Criteria");
        }
        return mDefinitionObject;
    }

    /**
     * Gets the attribute value for CriteriaId, using the alias name CriteriaId.
     * @return the CriteriaId
     */
    public Number getCriteriaId() {
        return (Number)getAttributeInternal(CRITERIAID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CriteriaId.
     * @param value value to set the CriteriaId
     */
    public void setCriteriaId(Number value) {
        setAttributeInternal(CRITERIAID, value);
    }

    /**
     * Gets the attribute value for RuleId, using the alias name RuleId.
     * @return the RuleId
     */
    public Number getRuleId() {
        return (Number)getAttributeInternal(RULEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for RuleId.
     * @param value value to set the RuleId
     */
    public void setRuleId(Number value) {
        setAttributeInternal(RULEID, value);
    }

    /**
     * Gets the attribute value for CriteriaColumnName, using the alias name CriteriaColumnName.
     * @return the CriteriaColumnName
     */
    public String getCriteriaColumnName() {
        return (String)getAttributeInternal(CRITERIACOLUMNNAME);
    }

    /**
     * Sets <code>value</code> as the attribute value for CriteriaColumnName.
     * @param value value to set the CriteriaColumnName
     */
    public void setCriteriaColumnName(String value) {
        setAttributeInternal(CRITERIACOLUMNNAME, value);
    }

    /**
     * Gets the attribute value for CriteriaOperator, using the alias name CriteriaOperator.
     * @return the CriteriaOperator
     */
    public String getCriteriaOperator() {
        return (String)getAttributeInternal(CRITERIAOPERATOR);
    }

    /**
     * Sets <code>value</code> as the attribute value for CriteriaOperator.
     * @param value value to set the CriteriaOperator
     */
    public void setCriteriaOperator(String value) {
        setAttributeInternal(CRITERIAOPERATOR, value);
    }

    /**
     * Gets the attribute value for CriteriaValue, using the alias name CriteriaValue.
     * @return the CriteriaValue
     */
    public String getCriteriaValue() {
        return (String)getAttributeInternal(CRITERIAVALUE);
    }

    /**
     * Sets <code>value</code> as the attribute value for CriteriaValue.
     * @param value value to set the CriteriaValue
     */
    public void setCriteriaValue(String value) {
        setAttributeInternal(CRITERIAVALUE, value);
    }

    /**
     * Gets the attribute value for ParamId, using the alias name ParamId.
     * @return the ParamId
     */
    public Number getParamId() {
        return (Number)getAttributeInternal(PARAMID);
    }

    /**
     * Sets <code>value</code> as the attribute value for ParamId.
     * @param value value to set the ParamId
     */
    public void setParamId(Number value) {
        setAttributeInternal(PARAMID, value);
    }

    /**
     * Gets the attribute value for CreateId, using the alias name CreateId.
     * @return the CreateId
     */
    public String getCreateId() {
        return (String)getAttributeInternal(CREATEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreateId.
     * @param value value to set the CreateId
     */
    public void setCreateId(String value) {
        setAttributeInternal(CREATEID, value);
    }

    /**
     * Gets the attribute value for CreateDatetime, using the alias name CreateDatetime.
     * @return the CreateDatetime
     */
    public Date getCreateDatetime() {
        return (Date)getAttributeInternal(CREATEDATETIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for CreateDatetime.
     * @param value value to set the CreateDatetime
     */
    public void setCreateDatetime(Date value) {
        setAttributeInternal(CREATEDATETIME, value);
    }

    /**
     * Gets the attribute value for LastUpdateId, using the alias name LastUpdateId.
     * @return the LastUpdateId
     */
    public String getLastUpdateId() {
        return (String)getAttributeInternal(LASTUPDATEID);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdateId.
     * @param value value to set the LastUpdateId
     */
    public void setLastUpdateId(String value) {
        setAttributeInternal(LASTUPDATEID, value);
    }

    /**
     * Gets the attribute value for LastUpdateDatetime, using the alias name LastUpdateDatetime.
     * @return the LastUpdateDatetime
     */
    public Date getLastUpdateDatetime() {
        return (Date)getAttributeInternal(LASTUPDATEDATETIME);
    }

    /**
     * Sets <code>value</code> as the attribute value for LastUpdateDatetime.
     * @param value value to set the LastUpdateDatetime
     */
    public void setLastUpdateDatetime(Date value) {
        setAttributeInternal(LASTUPDATEDATETIME, value);
    }

    /**
     * Gets the attribute value for SeqNo, using the alias name SeqNo.
     * @return the SeqNo
     */
    public Number getSeqNo() {
        return (Number)getAttributeInternal(SEQNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for SeqNo.
     * @param value value to set the SeqNo
     */
    public void setSeqNo(Number value) {
        setAttributeInternal(SEQNO, value);
    }

    /**
     * getAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param attrDef the attribute

     * @return the attribute value
     * @throws Exception
     */
    protected Object getAttrInvokeAccessor(int index,
                                           AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            return AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].get(this);
        }
        return super.getAttrInvokeAccessor(index, attrDef);
    }

    /**
     * setAttrInvokeAccessor: generated method. Do not modify.
     * @param index the index identifying the attribute
     * @param value the value to assign to the attribute
     * @param attrDef the attribute

     * @throws Exception
     */
    protected void setAttrInvokeAccessor(int index, Object value,
                                         AttributeDefImpl attrDef) throws Exception {
        if ((index >= AttributesEnum.firstIndex()) && (index < AttributesEnum.count())) {
            AttributesEnum.staticValues()[index - AttributesEnum.firstIndex()].put(this, value);
            return;
        }
        super.setAttrInvokeAccessor(index, value, attrDef);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getClr_Wksht_Rule_Param_Head() {
        return (EntityImpl)getAttributeInternal(CLR_WKSHT_RULE_PARAM_HEAD);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setClr_Wksht_Rule_Param_Head(EntityImpl value) {
        setAttributeInternal(CLR_WKSHT_RULE_PARAM_HEAD, value);
    }

    /**
     * @param criteriaId key constituent
     * @param ruleId key constituent
     * @param seqNo key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(Number criteriaId, Number ruleId, Number seqNo) {
        return new Key(new Object[]{criteriaId, ruleId, seqNo});
    }

    /**
     * @return the associated entity Clr_Wksht_RuleImpl.
     */
    public Clr_Wksht_RuleImpl getClr_Wksht_Rule1() {
        return (Clr_Wksht_RuleImpl)getAttributeInternal(CLR_WKSHT_RULE1);
    }

    /**
     * Sets <code>value</code> as the associated entity Clr_Wksht_RuleImpl.
     */
    public void setClr_Wksht_Rule1(Clr_Wksht_RuleImpl value) {
        setAttributeInternal(CLR_WKSHT_RULE1, value);
    }

    /**
     * @return the associated entity Clr_Wksht_RuleImpl.
     */
    public Clr_Wksht_RuleImpl getClr_Wksht_Rule() {
        return (Clr_Wksht_RuleImpl)getAttributeInternal(CLR_WKSHT_RULE);
    }

    /**
     * Sets <code>value</code> as the associated entity Clr_Wksht_RuleImpl.
     */
    public void setClr_Wksht_Rule(Clr_Wksht_RuleImpl value) {
        setAttributeInternal(CLR_WKSHT_RULE, value);
    }

    /**
     * @return the associated entity oracle.jbo.server.EntityImpl.
     */
    public EntityImpl getClr_Wksht_Criteria_Fields() {
        return (EntityImpl)getAttributeInternal(CLR_WKSHT_CRITERIA_FIELDS);
    }

    /**
     * Sets <code>value</code> as the associated entity oracle.jbo.server.EntityImpl.
     */
    public void setClr_Wksht_Criteria_Fields(EntityImpl value) {
        setAttributeInternal(CLR_WKSHT_CRITERIA_FIELDS, value);
    }


    /**
     * Add attribute defaulting logic in this method.
     * @param attributeList list of attribute names/values to initialize the row
     */
    protected void create(AttributeList attributeList)
    {
//        Map sessionScope = ADFContext.getCurrent().getSessionScope();
//        String userName = (String)sessionScope.get("userName");
//        Date d=new Date();
//        this.setCreateId(userName);
//        this.setCreateDatetime((Date)d.getCurrentDate());
//        this.setLastUpdateId(userName);
//        this.setLastUpdateDatetime((Date)d.getCurrentDate());
        super.create(attributeList);
    }
    
    public Number getSeq(String seq_str) 
      {
          
            Number seq=new Number(0);
            
        
            if(seq_str!=null && !seq_str.equals("")) 
            {
               SequenceImpl simpl=new SequenceImpl(seq_str,getDBTransaction());
                seq=simpl.getSequenceNumber();
            }                        
     return seq;
            
           
        }
    
}
