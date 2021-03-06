package xts.salinv.model.views;

import java.sql.Timestamp;

import oracle.adf.share.ADFContext;

import xts.common.model.framework.GenericPLSQLEntityImpl;
import xts.common.model.framework.GenericViewRowImpl;
import xts.common.model.framework.beans.DisabledLogicContainerModel;

import xts.salinv.model.services.XTSSalInvAMImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 30 17:44:39 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TciDesShpitmVORowImpl extends GenericViewRowImpl {


    public static final int ENTITY_TCIDES = 0;

    @Override
    public Boolean calculateDisabled(String attrName) {
        boolean disabled = super.calculateDisabled(attrName);

        if (!disabled) {
            if (ADFContext.getCurrent().isJEE()) {
                DisabledLogicContainerModel disabledControlModel = (DisabledLogicContainerModel) ADFContext.getCurrent()
                                                                                                           .getPageFlowScope()
                                                                                                           .get("XTSDisabledLogicContainerBean");
                XTSSalInvAMImpl am = (XTSSalInvAMImpl) this.getApplicationModule();
                TciMstVORowImpl mstRow = (TciMstVORowImpl) am.getTciMstVO().getCurrentRow();
                String mstKey = "TCI_MST:" + mstRow.getCimsDivcod() + "|" + mstRow.getCimsRunnum().toString();

                String sts = (String) disabledControlModel.getData(mstKey, "CimsStscod");
                if (sts.equalsIgnoreCase("DE"))
                    return true;
            }
        }

        return super.calculateDisabled(attrName);
    }

    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        CidsDivcod,
        CidsRunnum,
        CidsSoRunnum,
        CidsShiseq,
        CidsItmseq,
        CidsTopdes,
        CidsBotdes,
        CidsShpdes,
        CidsItmShpdes,
        CidsItmDesdes,
        Crtdat,
        Crtby,
        Upddat,
        Updby,
        CidsClsdat,
        CidsClsby,
        CidsRm1des,
        CidsRm2des;
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


    public static final int CIDSDIVCOD = AttributesEnum.CidsDivcod.index();
    public static final int CIDSRUNNUM = AttributesEnum.CidsRunnum.index();
    public static final int CIDSSORUNNUM = AttributesEnum.CidsSoRunnum.index();
    public static final int CIDSSHISEQ = AttributesEnum.CidsShiseq.index();
    public static final int CIDSITMSEQ = AttributesEnum.CidsItmseq.index();
    public static final int CIDSTOPDES = AttributesEnum.CidsTopdes.index();
    public static final int CIDSBOTDES = AttributesEnum.CidsBotdes.index();
    public static final int CIDSSHPDES = AttributesEnum.CidsShpdes.index();
    public static final int CIDSITMSHPDES = AttributesEnum.CidsItmShpdes.index();
    public static final int CIDSITMDESDES = AttributesEnum.CidsItmDesdes.index();
    public static final int CRTDAT = AttributesEnum.Crtdat.index();
    public static final int CRTBY = AttributesEnum.Crtby.index();
    public static final int UPDDAT = AttributesEnum.Upddat.index();
    public static final int UPDBY = AttributesEnum.Updby.index();
    public static final int CIDSCLSDAT = AttributesEnum.CidsClsdat.index();
    public static final int CIDSCLSBY = AttributesEnum.CidsClsby.index();
    public static final int CIDSRM1DES = AttributesEnum.CidsRm1des.index();
    public static final int CIDSRM2DES = AttributesEnum.CidsRm2des.index();

    /**
     * This is the default constructor (do not remove).
     */
    public TciDesShpitmVORowImpl() {
    }

    /**
     * Gets TciDes entity object.
     * @return the TciDes
     */
    public GenericPLSQLEntityImpl getTciDes() {
        return (GenericPLSQLEntityImpl) getEntity(ENTITY_TCIDES);
    }

    /**
     * Gets the attribute value for CIDS_DIVCOD using the alias name CidsDivcod.
     * @return the CIDS_DIVCOD
     */
    public String getCidsDivcod() {
        return (String) getAttributeInternal(CIDSDIVCOD);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_DIVCOD using the alias name CidsDivcod.
     * @param value value to set the CIDS_DIVCOD
     */
    public void setCidsDivcod(String value) {
        setAttributeInternal(CIDSDIVCOD, value);
    }

    /**
     * Gets the attribute value for CIDS_RUNNUM using the alias name CidsRunnum.
     * @return the CIDS_RUNNUM
     */
    public Long getCidsRunnum() {
        return (Long) getAttributeInternal(CIDSRUNNUM);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_RUNNUM using the alias name CidsRunnum.
     * @param value value to set the CIDS_RUNNUM
     */
    public void setCidsRunnum(Long value) {
        setAttributeInternal(CIDSRUNNUM, value);
    }

    /**
     * Gets the attribute value for CIDS_SO_RUNNUM using the alias name CidsSoRunnum.
     * @return the CIDS_SO_RUNNUM
     */
    public Long getCidsSoRunnum() {
        return (Long) getAttributeInternal(CIDSSORUNNUM);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_SO_RUNNUM using the alias name CidsSoRunnum.
     * @param value value to set the CIDS_SO_RUNNUM
     */
    public void setCidsSoRunnum(Long value) {
        setAttributeInternal(CIDSSORUNNUM, value);
    }

    /**
     * Gets the attribute value for CIDS_SHISEQ using the alias name CidsShiseq.
     * @return the CIDS_SHISEQ
     */
    public Integer getCidsShiseq() {
        return (Integer) getAttributeInternal(CIDSSHISEQ);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_SHISEQ using the alias name CidsShiseq.
     * @param value value to set the CIDS_SHISEQ
     */
    public void setCidsShiseq(Integer value) {
        setAttributeInternal(CIDSSHISEQ, value);
    }

    /**
     * Gets the attribute value for CIDS_ITMSEQ using the alias name CidsItmseq.
     * @return the CIDS_ITMSEQ
     */
    public Integer getCidsItmseq() {
        return (Integer) getAttributeInternal(CIDSITMSEQ);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_ITMSEQ using the alias name CidsItmseq.
     * @param value value to set the CIDS_ITMSEQ
     */
    public void setCidsItmseq(Integer value) {
        setAttributeInternal(CIDSITMSEQ, value);
    }

    /**
     * Gets the attribute value for CIDS_TOPDES using the alias name CidsTopdes.
     * @return the CIDS_TOPDES
     */
    public String getCidsTopdes() {
        return (String) getAttributeInternal(CIDSTOPDES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_TOPDES using the alias name CidsTopdes.
     * @param value value to set the CIDS_TOPDES
     */
    public void setCidsTopdes(String value) {
        setAttributeInternal(CIDSTOPDES, value);
    }

    /**
     * Gets the attribute value for CIDS_BOTDES using the alias name CidsBotdes.
     * @return the CIDS_BOTDES
     */
    public String getCidsBotdes() {
        return (String) getAttributeInternal(CIDSBOTDES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_BOTDES using the alias name CidsBotdes.
     * @param value value to set the CIDS_BOTDES
     */
    public void setCidsBotdes(String value) {
        setAttributeInternal(CIDSBOTDES, value);
    }

    /**
     * Gets the attribute value for CIDS_SHPDES using the alias name CidsShpdes.
     * @return the CIDS_SHPDES
     */
    public String getCidsShpdes() {
        return (String) getAttributeInternal(CIDSSHPDES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_SHPDES using the alias name CidsShpdes.
     * @param value value to set the CIDS_SHPDES
     */
    public void setCidsShpdes(String value) {
        setAttributeInternal(CIDSSHPDES, value);
    }

    /**
     * Gets the attribute value for CIDS_ITM_SHPDES using the alias name CidsItmShpdes.
     * @return the CIDS_ITM_SHPDES
     */
    public String getCidsItmShpdes() {
        return (String) getAttributeInternal(CIDSITMSHPDES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_ITM_SHPDES using the alias name CidsItmShpdes.
     * @param value value to set the CIDS_ITM_SHPDES
     */
    public void setCidsItmShpdes(String value) {
        setAttributeInternal(CIDSITMSHPDES, value);
    }

    /**
     * Gets the attribute value for CIDS_ITM_DESDES using the alias name CidsItmDesdes.
     * @return the CIDS_ITM_DESDES
     */
    public String getCidsItmDesdes() {
        return (String) getAttributeInternal(CIDSITMDESDES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_ITM_DESDES using the alias name CidsItmDesdes.
     * @param value value to set the CIDS_ITM_DESDES
     */
    public void setCidsItmDesdes(String value) {
        setAttributeInternal(CIDSITMDESDES, value);
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
     * Gets the attribute value for CIDS_CLSDAT using the alias name CidsClsdat.
     * @return the CIDS_CLSDAT
     */
    public Timestamp getCidsClsdat() {
        return (Timestamp) getAttributeInternal(CIDSCLSDAT);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_CLSDAT using the alias name CidsClsdat.
     * @param value value to set the CIDS_CLSDAT
     */
    public void setCidsClsdat(Timestamp value) {
        setAttributeInternal(CIDSCLSDAT, value);
    }

    /**
     * Gets the attribute value for CIDS_CLSBY using the alias name CidsClsby.
     * @return the CIDS_CLSBY
     */
    public String getCidsClsby() {
        return (String) getAttributeInternal(CIDSCLSBY);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_CLSBY using the alias name CidsClsby.
     * @param value value to set the CIDS_CLSBY
     */
    public void setCidsClsby(String value) {
        setAttributeInternal(CIDSCLSBY, value);
    }

    /**
     * Gets the attribute value for CIDS_RM1DES using the alias name CidsRm1des.
     * @return the CIDS_RM1DES
     */
    public String getCidsRm1des() {
        return (String) getAttributeInternal(CIDSRM1DES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_RM1DES using the alias name CidsRm1des.
     * @param value value to set the CIDS_RM1DES
     */
    public void setCidsRm1des(String value) {
        setAttributeInternal(CIDSRM1DES, value);
    }

    /**
     * Gets the attribute value for CIDS_RM2DES using the alias name CidsRm2des.
     * @return the CIDS_RM2DES
     */
    public String getCidsRm2des() {
        return (String) getAttributeInternal(CIDSRM2DES);
    }

    /**
     * Sets <code>value</code> as attribute value for CIDS_RM2DES using the alias name CidsRm2des.
     * @param value value to set the CIDS_RM2DES
     */
    public void setCidsRm2des(String value) {
        setAttributeInternal(CIDSRM2DES, value);
    }
}

