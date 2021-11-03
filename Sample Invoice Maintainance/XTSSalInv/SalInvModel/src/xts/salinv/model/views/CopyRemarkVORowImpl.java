package xts.salinv.model.views;

import java.math.BigDecimal;

import oracle.jbo.RowSet;

import xts.common.model.framework.GenericViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 22 11:20:06 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class CopyRemarkVORowImpl extends GenericViewRowImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        SiNo,
        Shiseq,
        Itmseq,
        ItemNo,
        SoNo,
        MprmRmkcod,
        Loc,
        Fldnam,
        CimsRunnum,
        SomsRunnum,
        SiNoLovSwitcher,
        Divcod,
        TciMstLovVO1,
        TciActshpitmLovVO1,
        TmpRmkmstLovVO1;
        private static AttributesEnum[] vals = null;
        ;
        private static final int firstIndex = 0;

        protected int index() {
            return AttributesEnum.firstIndex() + ordinal();
        }

        protected static final int firstIndex() {
            return firstIndex;
        }

        protected static int count() {
            return AttributesEnum.firstIndex() + AttributesEnum.staticValues().length;
        }

        protected static final AttributesEnum[] staticValues() {
            if (vals == null) {
                vals = AttributesEnum.values();
            }
            return vals;
        }
    }


    public static final int SINO = AttributesEnum.SiNo.index();
    public static final int SHISEQ = AttributesEnum.Shiseq.index();
    public static final int ITMSEQ = AttributesEnum.Itmseq.index();
    public static final int ITEMNO = AttributesEnum.ItemNo.index();
    public static final int SONO = AttributesEnum.SoNo.index();
    public static final int MPRMRMKCOD = AttributesEnum.MprmRmkcod.index();
    public static final int LOC = AttributesEnum.Loc.index();
    public static final int FLDNAM = AttributesEnum.Fldnam.index();
    public static final int CIMSRUNNUM = AttributesEnum.CimsRunnum.index();
    public static final int SOMSRUNNUM = AttributesEnum.SomsRunnum.index();
    public static final int SINOLOVSWITCHER = AttributesEnum.SiNoLovSwitcher.index();
    public static final int DIVCOD = AttributesEnum.Divcod.index();
    public static final int TCIMSTLOVVO1 = AttributesEnum.TciMstLovVO1.index();
    public static final int TCIACTSHPITMLOVVO1 = AttributesEnum.TciActshpitmLovVO1.index();
    public static final int TMPRMKMSTLOVVO1 = AttributesEnum.TmpRmkmstLovVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public CopyRemarkVORowImpl() {
    }


    /**
     * Gets the attribute value for the calculated attribute SiNo.
     * @return the SiNo
     */
    public String getSiNo() {
        return (String) getAttributeInternal(SINO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SiNo.
     * @param value value to set the  SiNo
     */
    public void setSiNo(String value) {
        setAttributeInternal(SINO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Shiseq.
     * @return the Shiseq
     */
    public BigDecimal getShiseq() {
        return (BigDecimal) getAttributeInternal(SHISEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Shiseq.
     * @param value value to set the  Shiseq
     */
    public void setShiseq(BigDecimal value) {
        setAttributeInternal(SHISEQ, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Itmseq.
     * @return the Itmseq
     */
    public BigDecimal getItmseq() {
        return (BigDecimal) getAttributeInternal(ITMSEQ);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Itmseq.
     * @param value value to set the  Itmseq
     */
    public void setItmseq(BigDecimal value) {
        setAttributeInternal(ITMSEQ, value);
    }

    /**
     * Gets the attribute value for the calculated attribute ItemNo.
     * @return the ItemNo
     */
    public String getItemNo() {
        return (String) getAttributeInternal(ITEMNO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute ItemNo.
     * @param value value to set the  ItemNo
     */
    public void setItemNo(String value) {
        setAttributeInternal(ITEMNO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SoNo.
     * @return the SoNo
     */
    public String getSoNo() {
        return (String) getAttributeInternal(SONO);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SoNo.
     * @param value value to set the  SoNo
     */
    public void setSoNo(String value) {
        setAttributeInternal(SONO, value);
    }

    /**
     * Gets the attribute value for the calculated attribute MprmRmkcod.
     * @return the MprmRmkcod
     */
    public String getMprmRmkcod() {
        return (String) getAttributeInternal(MPRMRMKCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute MprmRmkcod.
     * @param value value to set the  MprmRmkcod
     */
    public void setMprmRmkcod(String value) {
        setAttributeInternal(MPRMRMKCOD, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Loc.
     * @return the Loc
     */
    public String getLoc() {
        return (String) getAttributeInternal(LOC);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Loc.
     * @param value value to set the  Loc
     */
    public void setLoc(String value) {
        setAttributeInternal(LOC, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Fldnam.
     * @return the Fldnam
     */
    public String getFldnam() {
        return (String) getAttributeInternal(FLDNAM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Fldnam.
     * @param value value to set the  Fldnam
     */
    public void setFldnam(String value) {
        setAttributeInternal(FLDNAM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CimsRunnum.
     * @return the CimsRunnum
     */
    public Long getCimsRunnum() {
        return (Long) getAttributeInternal(CIMSRUNNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute CimsRunnum.
     * @param value value to set the  CimsRunnum
     */
    public void setCimsRunnum(Long value) {
        setAttributeInternal(CIMSRUNNUM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SomsRunnum.
     * @return the SomsRunnum
     */
    public Long getSomsRunnum() {
        return (Long) getAttributeInternal(SOMSRUNNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute SomsRunnum.
     * @param value value to set the  SomsRunnum
     */
    public void setSomsRunnum(Long value) {
        setAttributeInternal(SOMSRUNNUM, value);
    }

    /**
     * Gets the attribute value for the calculated attribute SiNoLovSwitcher.
     * @return the SiNoLovSwitcher
     */
    public String getSiNoLovSwitcher() {
        return (String) getAttributeInternal(SINOLOVSWITCHER);
    }


    /**
     * Gets the attribute value for the calculated attribute Divcod.
     * @return the Divcod
     */
    public String getDivcod() {
        return (String) getAttributeInternal(DIVCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Divcod.
     * @param value value to set the  Divcod
     */
    public void setDivcod(String value) {
        setAttributeInternal(DIVCOD, value);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TciMstLovVO1.
     */
    public RowSet getTciMstLovVO1() {
        return (RowSet) getAttributeInternal(TCIMSTLOVVO1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TciActshpitmLovVO1.
     */
    public RowSet getTciActshpitmLovVO1() {
        return (RowSet) getAttributeInternal(TCIACTSHPITMLOVVO1);
    }

    /**
     * Gets the view accessor <code>RowSet</code> TmpRmkmstLovVO1.
     */
    public RowSet getTmpRmkmstLovVO1() {
        return (RowSet) getAttributeInternal(TMPRMKMSTLOVVO1);
    }
}

