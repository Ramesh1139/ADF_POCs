package xts.salinv.model.views;

import java.math.BigDecimal;

import java.sql.Timestamp;
import java.sql.Types;

import oracle.adf.share.ADFContext;

import oracle.jbo.JboException;
import oracle.jbo.RowSet;
import oracle.jbo.RowSetIterator;

import xts.common.model.framework.GenericEntityImpl;
import xts.common.model.framework.GenericPLSQLEntityImpl;
import xts.common.model.framework.GenericViewRowImpl;
import xts.common.model.framework.beans.DisabledLogicContainerModel;
import xts.common.model.framework.db.PLSQLCall;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Sep 21 12:04:03 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TciAddAmtVORowImpl extends GenericViewRowImpl {


    public static final int ENTITY_TCIADDAMT = 0;

    @Override
    public Boolean calculateDisabled(String attrName) {
        boolean disabled = super.calculateDisabled(attrName);

        if (!disabled) {
            if (ADFContext.getCurrent().isJEE()) {
                DisabledLogicContainerModel disabledControlModel = (DisabledLogicContainerModel) ADFContext.getCurrent()
                                                                                                           .getPageFlowScope()
                                                                                                           .get("XTSDisabledLogicContainerBean");

                String cimsStscod =
                    (String) disabledControlModel.getData("TCI_MST:" + this.getCiaaDivcod() + "|" + this.getCiaaRunnum().toString(), "CimsStscod");
                switch (cimsStscod) {
                case "DE":
                    return true;
                case "AC":
                    return true;
                default:
                    break;
                }
            }
        }

        return super.calculateDisabled(attrName);
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        CiaaDivcod,
        CiaaRunnum,
        CiaaFnlcus,
        CiaaInvseq,
        CiaaSeq,
        CiaaChrtyp,
        CiaaDes,
        CiaaRat,
        CiaaAmt,
        Crtdat,
        Crtby,
        Upddat,
        Updby,
        CiaaClsdat,
        CiaaClsby,
        CiaaAmtTotal,
        TacChrtypmstLovVO1;
        private static AttributesEnum[] vals = null; ;
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


    public static final int CIAADIVCOD = AttributesEnum.CiaaDivcod.index();
    public static final int CIAARUNNUM = AttributesEnum.CiaaRunnum.index();
    public static final int CIAAFNLCUS = AttributesEnum.CiaaFnlcus.index();
    public static final int CIAAINVSEQ = AttributesEnum.CiaaInvseq.index();
    public static final int CIAASEQ = AttributesEnum.CiaaSeq.index();
    public static final int CIAACHRTYP = AttributesEnum.CiaaChrtyp.index();
    public static final int CIAADES = AttributesEnum.CiaaDes.index();
    public static final int CIAARAT = AttributesEnum.CiaaRat.index();
    public static final int CIAAAMT = AttributesEnum.CiaaAmt.index();
    public static final int CRTDAT = AttributesEnum.Crtdat.index();
    public static final int CRTBY = AttributesEnum.Crtby.index();
    public static final int UPDDAT = AttributesEnum.Upddat.index();
    public static final int UPDBY = AttributesEnum.Updby.index();
    public static final int CIAACLSDAT = AttributesEnum.CiaaClsdat.index();
    public static final int CIAACLSBY = AttributesEnum.CiaaClsby.index();
    public static final int CIAAAMTTOTAL = AttributesEnum.CiaaAmtTotal.index();
    public static final int TACCHRTYPMSTLOVVO1 = AttributesEnum.TacChrtypmstLovVO1.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TciAddAmtVORowImpl() {
    }

    /**
     * Gets TciAddamt entity object.
     * @return the TciAddamt
     */
    public GenericPLSQLEntityImpl getTciAddamt() {
        return (GenericPLSQLEntityImpl) getEntity(ENTITY_TCIADDAMT);
    }

    /**
     * Gets the attribute value for CIAA_DIVCOD using the alias name CiaaDivcod.
     * @return the CIAA_DIVCOD
     */
    public String getCiaaDivcod() {
        return (String) getAttributeInternal(CIAADIVCOD);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_DIVCOD using the alias name CiaaDivcod.
     * @param value value to set the CIAA_DIVCOD
     */
    public void setCiaaDivcod(String value) {
        setAttributeInternal(CIAADIVCOD, value);
    }

    /**
     * Gets the attribute value for CIAA_RUNNUM using the alias name CiaaRunnum.
     * @return the CIAA_RUNNUM
     */
    public Long getCiaaRunnum() {
        return (Long) getAttributeInternal(CIAARUNNUM);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_RUNNUM using the alias name CiaaRunnum.
     * @param value value to set the CIAA_RUNNUM
     */
    public void setCiaaRunnum(Long value) {
        setAttributeInternal(CIAARUNNUM, value);
    }

    /**
     * Gets the attribute value for CIAA_FNLCUS using the alias name CiaaFnlcus.
     * @return the CIAA_FNLCUS
     */
    public String getCiaaFnlcus() {
        return (String) getAttributeInternal(CIAAFNLCUS);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_FNLCUS using the alias name CiaaFnlcus.
     * @param value value to set the CIAA_FNLCUS
     */
    public void setCiaaFnlcus(String value) {
        setAttributeInternal(CIAAFNLCUS, value);
    }

    /**
     * Gets the attribute value for CIAA_INVSEQ using the alias name CiaaInvseq.
     * @return the CIAA_INVSEQ
     */
    public Integer getCiaaInvseq() {
        return (Integer) getAttributeInternal(CIAAINVSEQ);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_INVSEQ using the alias name CiaaInvseq.
     * @param value value to set the CIAA_INVSEQ
     */
    public void setCiaaInvseq(Integer value) {
        setAttributeInternal(CIAAINVSEQ, value);
    }

    /**
     * Gets the attribute value for CIAA_SEQ using the alias name CiaaSeq.
     * @return the CIAA_SEQ
     */
    public Integer getCiaaSeq() {
        return (Integer) getAttributeInternal(CIAASEQ);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_SEQ using the alias name CiaaSeq.
     * @param value value to set the CIAA_SEQ
     */
    public void setCiaaSeq(Integer value) {
        setAttributeInternal(CIAASEQ, value);
    }

    /**
     * Gets the attribute value for CIAA_CHRTYP using the alias name CiaaChrtyp.
     * @return the CIAA_CHRTYP
     */
    public String getCiaaChrtyp() {
        return (String) getAttributeInternal(CIAACHRTYP);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_CHRTYP using the alias name CiaaChrtyp.
     * @param value value to set the CIAA_CHRTYP
     */
    public void setCiaaChrtyp(String value) {
        setAttributeInternal(CIAACHRTYP, value);
    }

    /**
     * Gets the attribute value for CIAA_DES using the alias name CiaaDes.
     * @return the CIAA_DES
     */
    public String getCiaaDes() {
        return (String) getAttributeInternal(CIAADES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_DES using the alias name CiaaDes.
     * @param value value to set the CIAA_DES
     */
    public void setCiaaDes(String value) {
        setAttributeInternal(CIAADES, value);
    }

    /**
     * Gets the attribute value for CIAA_RAT using the alias name CiaaRat.
     * @return the CIAA_RAT
     */
    public BigDecimal getCiaaRat() {
        return (BigDecimal) getAttributeInternal(CIAARAT);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_RAT using the alias name CiaaRat.
     * @param value value to set the CIAA_RAT
     */
    public void setCiaaRat(BigDecimal value) {
        if (value != null)
            setAttributeInternal(CIAARAT, value);
        else
            setAttributeInternal(CIAARAT, BigDecimal.ZERO);

        if (this.getCiaaRat() != null && this.getCiaaRat().compareTo(BigDecimal.ZERO) != 0) {
            try {
                PLSQLCall dc = new PLSQLCall("? := DP_SI.F_GET_CIAA_AMT", this.getDBTransaction());
                dc.addRet("ret", Types.DOUBLE);
                dc.addIn(this.getCiaaRunnum());
                dc.addIn(this.getCiaaRat());
                dc.execute();
                Object ret = dc.getObj("ret");
                if (ret != null)
                    this.setCiaaAmt(BigDecimal.valueOf((Double) ret));

            } catch (JboException e) {
                ((GenericEntityImpl) this.getEntityForAttribute("CiaaRunnum")).convertException(e);
            }
        }
    }

    /**
     * Gets the attribute value for CIAA_AMT using the alias name CiaaAmt.
     * @return the CIAA_AMT
     */
    public BigDecimal getCiaaAmt() {
        return (BigDecimal) getAttributeInternal(CIAAAMT);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_AMT using the alias name CiaaAmt.
     * @param value value to set the CIAA_AMT
     */
    public void setCiaaAmt(BigDecimal value) {
        setAttributeInternal(CIAAAMT, value);
    }

    /**
     * Gets the attribute value for CRTDAT using the alias name Crtdat.
     * @return the CRTDAT
     */
    public Timestamp getCrtdat() {
        return (Timestamp) getAttributeInternal(CRTDAT);
    }

    /**
     * Sets <code>value</code> as attribute value for CRTDAT using the alias name Crtdat.
     * @param value value to set the CRTDAT
     */
    public void setCrtdat(Timestamp value) {
        setAttributeInternal(CRTDAT, value);
    }

    /**
     * Gets the attribute value for CRTBY using the alias name Crtby.
     * @return the CRTBY
     */
    public String getCrtby() {
        return (String) getAttributeInternal(CRTBY);
    }

    /**
     * Sets <code>value</code> as attribute value for CRTBY using the alias name Crtby.
     * @param value value to set the CRTBY
     */
    public void setCrtby(String value) {
        setAttributeInternal(CRTBY, value);
    }

    /**
     * Gets the attribute value for UPDDAT using the alias name Upddat.
     * @return the UPDDAT
     */
    public Timestamp getUpddat() {
        return (Timestamp) getAttributeInternal(UPDDAT);
    }

    /**
     * Sets <code>value</code> as attribute value for UPDDAT using the alias name Upddat.
     * @param value value to set the UPDDAT
     */
    public void setUpddat(Timestamp value) {
        setAttributeInternal(UPDDAT, value);
    }

    /**
     * Gets the attribute value for UPDBY using the alias name Updby.
     * @return the UPDBY
     */
    public String getUpdby() {
        return (String) getAttributeInternal(UPDBY);
    }

    /**
     * Sets <code>value</code> as attribute value for UPDBY using the alias name Updby.
     * @param value value to set the UPDBY
     */
    public void setUpdby(String value) {
        setAttributeInternal(UPDBY, value);
    }

    /**
     * Gets the attribute value for CIAA_CLSDAT using the alias name CiaaClsdat.
     * @return the CIAA_CLSDAT
     */
    public Timestamp getCiaaClsdat() {
        return (Timestamp) getAttributeInternal(CIAACLSDAT);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_CLSDAT using the alias name CiaaClsdat.
     * @param value value to set the CIAA_CLSDAT
     */
    public void setCiaaClsdat(Timestamp value) {
        setAttributeInternal(CIAACLSDAT, value);
    }

    /**
     * Gets the attribute value for CIAA_CLSBY using the alias name CiaaClsby.
     * @return the CIAA_CLSBY
     */
    public String getCiaaClsby() {
        return (String) getAttributeInternal(CIAACLSBY);
    }

    /**
     * Sets <code>value</code> as attribute value for CIAA_CLSBY using the alias name CiaaClsby.
     * @param value value to set the CIAA_CLSBY
     */
    public void setCiaaClsby(String value) {
        setAttributeInternal(CIAACLSBY, value);
    }

    /**
     * Gets the attribute value for the calculated attribute CiaaAmtTotal.
     * @return the CiaaAmtTotal
     */
    public BigDecimal getCiaaAmtTotal() {
        RowSetIterator rsi = this.getViewObject().createRowSetIterator(null);
        BigDecimal rowVal = new BigDecimal(0);
        BigDecimal total = new BigDecimal(0);

        while (rsi.hasNext()) {
            TciAddAmtVORowImpl row = (TciAddAmtVORowImpl) rsi.next();
            rowVal = row.getCiaaAmt();
            if (rowVal == null) {
                rowVal = BigDecimal.ZERO;
            }
            total = total.add(rowVal);
        }
        rsi.reset();
        rsi.closeRowSetIterator();

        return total;
    }

    /**
     * Gets the view accessor <code>RowSet</code> TacChrtypmstLovVO1.
     */
    public RowSet getTacChrtypmstLovVO1() {
        return (RowSet) getAttributeInternal(TACCHRTYPMSTLOVVO1);
    }

}

