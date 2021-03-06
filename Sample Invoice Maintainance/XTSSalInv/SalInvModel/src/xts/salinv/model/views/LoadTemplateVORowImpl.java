package xts.salinv.model.views;

import java.math.BigDecimal;

import java.sql.Timestamp;

import xts.common.model.framework.GenericPLSQLEntityImpl;
import xts.common.model.framework.GenericViewRowImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 23 15:50:53 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class LoadTemplateVORowImpl extends GenericViewRowImpl {


    public static final int ENTITY_TMPADDAMTTPLDTL = 0;

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        MpaddDivcod,
        MpaddTplcod,
        MpaddSeq,
        MpaddCharge,
        MpaddChrtyp,
        MpaddDes,
        MpaddRat,
        MpaddItmamt,
        MpaddAmt,
        MpaddCntcom,
        MpaddInccom,
        MpaddPrShw,
        MpaddPmShw,
        MpaddIvShw,
        Crtdat,
        Crtby,
        Upddat,
        Updby,
        MpaddSoShw,
        MpaddPoShw,
        MpaddSiShw,
        MpaddPiShw,
        MpaddRaton,
        MpaddFulamt,
        Location;
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


    public static final int MPADDDIVCOD = AttributesEnum.MpaddDivcod.index();
    public static final int MPADDTPLCOD = AttributesEnum.MpaddTplcod.index();
    public static final int MPADDSEQ = AttributesEnum.MpaddSeq.index();
    public static final int MPADDCHARGE = AttributesEnum.MpaddCharge.index();
    public static final int MPADDCHRTYP = AttributesEnum.MpaddChrtyp.index();
    public static final int MPADDDES = AttributesEnum.MpaddDes.index();
    public static final int MPADDRAT = AttributesEnum.MpaddRat.index();
    public static final int MPADDITMAMT = AttributesEnum.MpaddItmamt.index();
    public static final int MPADDAMT = AttributesEnum.MpaddAmt.index();
    public static final int MPADDCNTCOM = AttributesEnum.MpaddCntcom.index();
    public static final int MPADDINCCOM = AttributesEnum.MpaddInccom.index();
    public static final int MPADDPRSHW = AttributesEnum.MpaddPrShw.index();
    public static final int MPADDPMSHW = AttributesEnum.MpaddPmShw.index();
    public static final int MPADDIVSHW = AttributesEnum.MpaddIvShw.index();
    public static final int CRTDAT = AttributesEnum.Crtdat.index();
    public static final int CRTBY = AttributesEnum.Crtby.index();
    public static final int UPDDAT = AttributesEnum.Upddat.index();
    public static final int UPDBY = AttributesEnum.Updby.index();
    public static final int MPADDSOSHW = AttributesEnum.MpaddSoShw.index();
    public static final int MPADDPOSHW = AttributesEnum.MpaddPoShw.index();
    public static final int MPADDSISHW = AttributesEnum.MpaddSiShw.index();
    public static final int MPADDPISHW = AttributesEnum.MpaddPiShw.index();
    public static final int MPADDRATON = AttributesEnum.MpaddRaton.index();
    public static final int MPADDFULAMT = AttributesEnum.MpaddFulamt.index();
    public static final int LOCATION = AttributesEnum.Location.index();

    /**
     * This is the default constructor (do not remove).
     */
    public LoadTemplateVORowImpl() {
    }

    /**
     * Gets TmpAddamtTpldtl entity object.
     * @return the TmpAddamtTpldtl
     */
    public GenericPLSQLEntityImpl getTmpAddamtTpldtl() {
        return (GenericPLSQLEntityImpl) getEntity(ENTITY_TMPADDAMTTPLDTL);
    }

    /**
     * Gets the attribute value for MPADD_DIVCOD using the alias name MpaddDivcod.
     * @return the MPADD_DIVCOD
     */
    public String getMpaddDivcod() {
        return (String) getAttributeInternal(MPADDDIVCOD);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_DIVCOD using the alias name MpaddDivcod.
     * @param value value to set the MPADD_DIVCOD
     */
    public void setMpaddDivcod(String value) {
        setAttributeInternal(MPADDDIVCOD, value);
    }

    /**
     * Gets the attribute value for MPADD_TPLCOD using the alias name MpaddTplcod.
     * @return the MPADD_TPLCOD
     */
    public String getMpaddTplcod() {
        return (String) getAttributeInternal(MPADDTPLCOD);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_TPLCOD using the alias name MpaddTplcod.
     * @param value value to set the MPADD_TPLCOD
     */
    public void setMpaddTplcod(String value) {
        setAttributeInternal(MPADDTPLCOD, value);
    }

    /**
     * Gets the attribute value for MPADD_SEQ using the alias name MpaddSeq.
     * @return the MPADD_SEQ
     */
    public Integer getMpaddSeq() {
        return (Integer) getAttributeInternal(MPADDSEQ);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_SEQ using the alias name MpaddSeq.
     * @param value value to set the MPADD_SEQ
     */
    public void setMpaddSeq(Integer value) {
        setAttributeInternal(MPADDSEQ, value);
    }

    /**
     * Gets the attribute value for MPADD_CHARGE using the alias name MpaddCharge.
     * @return the MPADD_CHARGE
     */
    public String getMpaddCharge() {
        return (String) getAttributeInternal(MPADDCHARGE);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_CHARGE using the alias name MpaddCharge.
     * @param value value to set the MPADD_CHARGE
     */
    public void setMpaddCharge(String value) {
        setAttributeInternal(MPADDCHARGE, value);
    }

    /**
     * Gets the attribute value for MPADD_CHRTYP using the alias name MpaddChrtyp.
     * @return the MPADD_CHRTYP
     */
    public String getMpaddChrtyp() {
        return (String) getAttributeInternal(MPADDCHRTYP);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_CHRTYP using the alias name MpaddChrtyp.
     * @param value value to set the MPADD_CHRTYP
     */
    public void setMpaddChrtyp(String value) {
        setAttributeInternal(MPADDCHRTYP, value);
    }

    /**
     * Gets the attribute value for MPADD_DES using the alias name MpaddDes.
     * @return the MPADD_DES
     */
    public String getMpaddDes() {
        return (String) getAttributeInternal(MPADDDES);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_DES using the alias name MpaddDes.
     * @param value value to set the MPADD_DES
     */
    public void setMpaddDes(String value) {
        setAttributeInternal(MPADDDES, value);
    }

    /**
     * Gets the attribute value for MPADD_RAT using the alias name MpaddRat.
     * @return the MPADD_RAT
     */
    public BigDecimal getMpaddRat() {
        return (BigDecimal) getAttributeInternal(MPADDRAT);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_RAT using the alias name MpaddRat.
     * @param value value to set the MPADD_RAT
     */
    public void setMpaddRat(BigDecimal value) {
        setAttributeInternal(MPADDRAT, value);
    }

    /**
     * Gets the attribute value for MPADD_ITMAMT using the alias name MpaddItmamt.
     * @return the MPADD_ITMAMT
     */
    public BigDecimal getMpaddItmamt() {
        return (BigDecimal) getAttributeInternal(MPADDITMAMT);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_ITMAMT using the alias name MpaddItmamt.
     * @param value value to set the MPADD_ITMAMT
     */
    public void setMpaddItmamt(BigDecimal value) {
        setAttributeInternal(MPADDITMAMT, value);
    }

    /**
     * Gets the attribute value for MPADD_AMT using the alias name MpaddAmt.
     * @return the MPADD_AMT
     */
    public BigDecimal getMpaddAmt() {
        return (BigDecimal) getAttributeInternal(MPADDAMT);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_AMT using the alias name MpaddAmt.
     * @param value value to set the MPADD_AMT
     */
    public void setMpaddAmt(BigDecimal value) {
        setAttributeInternal(MPADDAMT, value);
    }

    /**
     * Gets the attribute value for MPADD_CNTCOM using the alias name MpaddCntcom.
     * @return the MPADD_CNTCOM
     */
    public String getMpaddCntcom() {
        return (String) getAttributeInternal(MPADDCNTCOM);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_CNTCOM using the alias name MpaddCntcom.
     * @param value value to set the MPADD_CNTCOM
     */
    public void setMpaddCntcom(String value) {
        setAttributeInternal(MPADDCNTCOM, value);
    }

    /**
     * Gets the attribute value for MPADD_INCCOM using the alias name MpaddInccom.
     * @return the MPADD_INCCOM
     */
    public String getMpaddInccom() {
        return (String) getAttributeInternal(MPADDINCCOM);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_INCCOM using the alias name MpaddInccom.
     * @param value value to set the MPADD_INCCOM
     */
    public void setMpaddInccom(String value) {
        setAttributeInternal(MPADDINCCOM, value);
    }

    /**
     * Gets the attribute value for MPADD_PR_SHW using the alias name MpaddPrShw.
     * @return the MPADD_PR_SHW
     */
    public String getMpaddPrShw() {
        return (String) getAttributeInternal(MPADDPRSHW);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_PR_SHW using the alias name MpaddPrShw.
     * @param value value to set the MPADD_PR_SHW
     */
    public void setMpaddPrShw(String value) {
        setAttributeInternal(MPADDPRSHW, value);
    }

    /**
     * Gets the attribute value for MPADD_PM_SHW using the alias name MpaddPmShw.
     * @return the MPADD_PM_SHW
     */
    public String getMpaddPmShw() {
        return (String) getAttributeInternal(MPADDPMSHW);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_PM_SHW using the alias name MpaddPmShw.
     * @param value value to set the MPADD_PM_SHW
     */
    public void setMpaddPmShw(String value) {
        setAttributeInternal(MPADDPMSHW, value);
    }

    /**
     * Gets the attribute value for MPADD_IV_SHW using the alias name MpaddIvShw.
     * @return the MPADD_IV_SHW
     */
    public String getMpaddIvShw() {
        return (String) getAttributeInternal(MPADDIVSHW);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_IV_SHW using the alias name MpaddIvShw.
     * @param value value to set the MPADD_IV_SHW
     */
    public void setMpaddIvShw(String value) {
        setAttributeInternal(MPADDIVSHW, value);
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
     * Gets the attribute value for MPADD_SO_SHW using the alias name MpaddSoShw.
     * @return the MPADD_SO_SHW
     */
    public String getMpaddSoShw() {
        return (String) getAttributeInternal(MPADDSOSHW);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_SO_SHW using the alias name MpaddSoShw.
     * @param value value to set the MPADD_SO_SHW
     */
    public void setMpaddSoShw(String value) {
        setAttributeInternal(MPADDSOSHW, value);
    }

    /**
     * Gets the attribute value for MPADD_PO_SHW using the alias name MpaddPoShw.
     * @return the MPADD_PO_SHW
     */
    public String getMpaddPoShw() {
        return (String) getAttributeInternal(MPADDPOSHW);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_PO_SHW using the alias name MpaddPoShw.
     * @param value value to set the MPADD_PO_SHW
     */
    public void setMpaddPoShw(String value) {
        setAttributeInternal(MPADDPOSHW, value);
    }

    /**
     * Gets the attribute value for MPADD_SI_SHW using the alias name MpaddSiShw.
     * @return the MPADD_SI_SHW
     */
    public String getMpaddSiShw() {
        return (String) getAttributeInternal(MPADDSISHW);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_SI_SHW using the alias name MpaddSiShw.
     * @param value value to set the MPADD_SI_SHW
     */
    public void setMpaddSiShw(String value) {
        setAttributeInternal(MPADDSISHW, value);
    }

    /**
     * Gets the attribute value for MPADD_PI_SHW using the alias name MpaddPiShw.
     * @return the MPADD_PI_SHW
     */
    public String getMpaddPiShw() {
        return (String) getAttributeInternal(MPADDPISHW);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_PI_SHW using the alias name MpaddPiShw.
     * @param value value to set the MPADD_PI_SHW
     */
    public void setMpaddPiShw(String value) {
        setAttributeInternal(MPADDPISHW, value);
    }

    /**
     * Gets the attribute value for MPADD_RATON using the alias name MpaddRaton.
     * @return the MPADD_RATON
     */
    public String getMpaddRaton() {
        return (String) getAttributeInternal(MPADDRATON);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_RATON using the alias name MpaddRaton.
     * @param value value to set the MPADD_RATON
     */
    public void setMpaddRaton(String value) {
        setAttributeInternal(MPADDRATON, value);
    }

    /**
     * Gets the attribute value for MPADD_FULAMT using the alias name MpaddFulamt.
     * @return the MPADD_FULAMT
     */
    public String getMpaddFulamt() {
        return (String) getAttributeInternal(MPADDFULAMT);
    }

    /**
     * Sets <code>value</code> as attribute value for MPADD_FULAMT using the alias name MpaddFulamt.
     * @param value value to set the MPADD_FULAMT
     */
    public void setMpaddFulamt(String value) {
        setAttributeInternal(MPADDFULAMT, value);
    }

    /**
     * Gets the attribute value for the calculated attribute Location.
     * @return the Location
     */
    public String getLocation() {
        return (String) getAttributeInternal(LOCATION);
    }

    /**
     * Sets <code>value</code> as the attribute value for the calculated attribute Location.
     * @param value value to set the  Location
     */
    public void setLocation(String value) {
        setAttributeInternal(LOCATION, value);
    }
}

