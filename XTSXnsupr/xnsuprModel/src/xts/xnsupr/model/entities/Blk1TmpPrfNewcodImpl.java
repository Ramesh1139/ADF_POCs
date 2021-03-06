package xts.xnsupr.model.entities;

import java.math.BigDecimal;

import java.sql.Date;

import java.sql.Timestamp;

import oracle.jbo.Key;
import oracle.jbo.domain.RowID;
import oracle.jbo.server.EntityDefImpl;

import oracle.jbo.server.TransactionEvent;

import xts.common.model.framework.GenericEntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Wed Apr 12 14:20:14 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Blk1TmpPrfNewcodImpl extends GenericEntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        MppfcRunnum,
        MppfcFulnam,
        MppfcFulnamLocal,
        MppfcStscod,
        MppfcSup,
        MppfcShp,
        MppfcMan,
        MppfcMat,
        MppfcCon,
        MppfcConsubrol,
        MppfcGrp,
        MppfcSbmby,
        MppfcSbmdat,
        MppfcApvby,
        MppfcApvdat,
        MppfcAdr1,
        MppfcAdr2,
        MppfcAdr3,
        MppfcAdr4,
        MppfcAdr1Local,
        MppfcAdr2Local,
        MppfcAdr3Local,
        MppfcAdr4Local,
        Updby,
        Upddat,
        MppfcRmk,
        MppfcAdr,
        MppfcCtycod,
        MppfcLoccod,
        Rowid,
        PbId,
        PbFulnam,
        PbStscod,
        PbSup,
        PbShp,
        PbMan,
        PbMat,
        PbCon,
        PbGrp,
        PbSbmby,
        PbSbmdat,
        PbCfmby,
        PbCfmdat,
        PbUpdby,
        PbUpddat,
        PbRmk,
        PbAdr,
        MppfcId,
        MppfcStscodDesc,
        MppfcGrpDesc,
        Crtdat,
        MppfcTyp;
        private static AttributesEnum[] vals = null;
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


    public static final int MPPFCRUNNUM = AttributesEnum.MppfcRunnum.index();
    public static final int MPPFCFULNAM = AttributesEnum.MppfcFulnam.index();
    public static final int MPPFCFULNAMLOCAL = AttributesEnum.MppfcFulnamLocal.index();
    public static final int MPPFCSTSCOD = AttributesEnum.MppfcStscod.index();
    public static final int MPPFCSUP = AttributesEnum.MppfcSup.index();
    public static final int MPPFCSHP = AttributesEnum.MppfcShp.index();
    public static final int MPPFCMAN = AttributesEnum.MppfcMan.index();
    public static final int MPPFCMAT = AttributesEnum.MppfcMat.index();
    public static final int MPPFCCON = AttributesEnum.MppfcCon.index();
    public static final int MPPFCCONSUBROL = AttributesEnum.MppfcConsubrol.index();
    public static final int MPPFCGRP = AttributesEnum.MppfcGrp.index();
    public static final int MPPFCSBMBY = AttributesEnum.MppfcSbmby.index();
    public static final int MPPFCSBMDAT = AttributesEnum.MppfcSbmdat.index();
    public static final int MPPFCAPVBY = AttributesEnum.MppfcApvby.index();
    public static final int MPPFCAPVDAT = AttributesEnum.MppfcApvdat.index();
    public static final int MPPFCADR1 = AttributesEnum.MppfcAdr1.index();
    public static final int MPPFCADR2 = AttributesEnum.MppfcAdr2.index();
    public static final int MPPFCADR3 = AttributesEnum.MppfcAdr3.index();
    public static final int MPPFCADR4 = AttributesEnum.MppfcAdr4.index();
    public static final int MPPFCADR1LOCAL = AttributesEnum.MppfcAdr1Local.index();
    public static final int MPPFCADR2LOCAL = AttributesEnum.MppfcAdr2Local.index();
    public static final int MPPFCADR3LOCAL = AttributesEnum.MppfcAdr3Local.index();
    public static final int MPPFCADR4LOCAL = AttributesEnum.MppfcAdr4Local.index();
    public static final int UPDBY = AttributesEnum.Updby.index();
    public static final int UPDDAT = AttributesEnum.Upddat.index();
    public static final int MPPFCRMK = AttributesEnum.MppfcRmk.index();
    public static final int MPPFCADR = AttributesEnum.MppfcAdr.index();
    public static final int MPPFCCTYCOD = AttributesEnum.MppfcCtycod.index();
    public static final int MPPFCLOCCOD = AttributesEnum.MppfcLoccod.index();
    public static final int ROWID = AttributesEnum.Rowid.index();
    public static final int PBID = AttributesEnum.PbId.index();
    public static final int PBFULNAM = AttributesEnum.PbFulnam.index();
    public static final int PBSTSCOD = AttributesEnum.PbStscod.index();
    public static final int PBSUP = AttributesEnum.PbSup.index();
    public static final int PBSHP = AttributesEnum.PbShp.index();
    public static final int PBMAN = AttributesEnum.PbMan.index();
    public static final int PBMAT = AttributesEnum.PbMat.index();
    public static final int PBCON = AttributesEnum.PbCon.index();
    public static final int PBGRP = AttributesEnum.PbGrp.index();
    public static final int PBSBMBY = AttributesEnum.PbSbmby.index();
    public static final int PBSBMDAT = AttributesEnum.PbSbmdat.index();
    public static final int PBCFMBY = AttributesEnum.PbCfmby.index();
    public static final int PBCFMDAT = AttributesEnum.PbCfmdat.index();
    public static final int PBUPDBY = AttributesEnum.PbUpdby.index();
    public static final int PBUPDDAT = AttributesEnum.PbUpddat.index();
    public static final int PBRMK = AttributesEnum.PbRmk.index();
    public static final int PBADR = AttributesEnum.PbAdr.index();
    public static final int MPPFCID = AttributesEnum.MppfcId.index();
    public static final int MPPFCSTSCODDESC = AttributesEnum.MppfcStscodDesc.index();
    public static final int MPPFCGRPDESC = AttributesEnum.MppfcGrpDesc.index();
    public static final int CRTDAT = AttributesEnum.Crtdat.index();
    public static final int MPPFCTYP = AttributesEnum.MppfcTyp.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Blk1TmpPrfNewcodImpl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("xts.xnsupr.model.entities.Blk1TmpPrfNewcod");
    }


    /**
     * Gets the attribute value for MppfcRunnum, using the alias name MppfcRunnum.
     * @return the value of MppfcRunnum
     */
    public BigDecimal getMppfcRunnum() {
        return (BigDecimal) getAttributeInternal(MPPFCRUNNUM);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcRunnum.
     * @param value value to set the MppfcRunnum
     */
    public void setMppfcRunnum(BigDecimal value) {
        setAttributeInternal(MPPFCRUNNUM, value);
    }

    /**
     * Gets the attribute value for MppfcFulnam, using the alias name MppfcFulnam.
     * @return the value of MppfcFulnam
     */
    public String getMppfcFulnam() {
        return (String) getAttributeInternal(MPPFCFULNAM);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcFulnam.
     * @param value value to set the MppfcFulnam
     */
    public void setMppfcFulnam(String value) {
        setAttributeInternal(MPPFCFULNAM, value);
    }

    /**
     * Gets the attribute value for MppfcFulnamLocal, using the alias name MppfcFulnamLocal.
     * @return the value of MppfcFulnamLocal
     */
    public String getMppfcFulnamLocal() {
        return (String) getAttributeInternal(MPPFCFULNAMLOCAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcFulnamLocal.
     * @param value value to set the MppfcFulnamLocal
     */
    public void setMppfcFulnamLocal(String value) {
        setAttributeInternal(MPPFCFULNAMLOCAL, value);
    }

    /**
     * Gets the attribute value for MppfcStscod, using the alias name MppfcStscod.
     * @return the value of MppfcStscod
     */
    public String getMppfcStscod() {
        return (String) getAttributeInternal(MPPFCSTSCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcStscod.
     * @param value value to set the MppfcStscod
     */
    public void setMppfcStscod(String value) {
        setAttributeInternal(MPPFCSTSCOD, value);
    }

    /**
     * Gets the attribute value for MppfcSup, using the alias name MppfcSup.
     * @return the value of MppfcSup
     */
    public String getMppfcSup() {
        return (String) getAttributeInternal(MPPFCSUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcSup.
     * @param value value to set the MppfcSup
     */
    public void setMppfcSup(String value) {
        setAttributeInternal(MPPFCSUP, value);
    }

    /**
     * Gets the attribute value for MppfcShp, using the alias name MppfcShp.
     * @return the value of MppfcShp
     */
    public String getMppfcShp() {
        return (String) getAttributeInternal(MPPFCSHP);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcShp.
     * @param value value to set the MppfcShp
     */
    public void setMppfcShp(String value) {
        setAttributeInternal(MPPFCSHP, value);
    }

    /**
     * Gets the attribute value for MppfcMan, using the alias name MppfcMan.
     * @return the value of MppfcMan
     */
    public String getMppfcMan() {
        return (String) getAttributeInternal(MPPFCMAN);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcMan.
     * @param value value to set the MppfcMan
     */
    public void setMppfcMan(String value) {
        setAttributeInternal(MPPFCMAN, value);
    }

    /**
     * Gets the attribute value for MppfcMat, using the alias name MppfcMat.
     * @return the value of MppfcMat
     */
    public String getMppfcMat() {
        return (String) getAttributeInternal(MPPFCMAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcMat.
     * @param value value to set the MppfcMat
     */
    public void setMppfcMat(String value) {
        setAttributeInternal(MPPFCMAT, value);
    }

    /**
     * Gets the attribute value for MppfcCon, using the alias name MppfcCon.
     * @return the value of MppfcCon
     */
    public String getMppfcCon() {
        return (String) getAttributeInternal(MPPFCCON);
    }

    /**
     * Gets the attribute value for MppfcConsubrol, using the alias name MppfcConsubrol.
     * @return the value of MppfcConsubrol
     */
    public String getMppfcConsubrol() {
        return (String) getAttributeInternal(MPPFCCONSUBROL);
    }

    /**
     * Gets the attribute value for MppfcGrp, using the alias name MppfcGrp.
     * @return the value of MppfcGrp
     */
    public String getMppfcGrp() {
        return (String) getAttributeInternal(MPPFCGRP);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcGrp.
     * @param value value to set the MppfcGrp
     */
    public void setMppfcGrp(String value) {
        setAttributeInternal(MPPFCGRP, value);
    }

    /**
     * Gets the attribute value for MppfcSbmby, using the alias name MppfcSbmby.
     * @return the value of MppfcSbmby
     */
    public String getMppfcSbmby() {
        return (String) getAttributeInternal(MPPFCSBMBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcSbmby.
     * @param value value to set the MppfcSbmby
     */
    public void setMppfcSbmby(String value) {
        setAttributeInternal(MPPFCSBMBY, value);
    }

    /**
     * Gets the attribute value for MppfcSbmdat, using the alias name MppfcSbmdat.
     * @return the value of MppfcSbmdat
     */
    public Date getMppfcSbmdat() {
        return (Date) getAttributeInternal(MPPFCSBMDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcSbmdat.
     * @param value value to set the MppfcSbmdat
     */
    public void setMppfcSbmdat(Date value) {
        setAttributeInternal(MPPFCSBMDAT, value);
    }

    /**
     * Gets the attribute value for MppfcApvby, using the alias name MppfcApvby.
     * @return the value of MppfcApvby
     */
    public String getMppfcApvby() {
        return (String) getAttributeInternal(MPPFCAPVBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcApvby.
     * @param value value to set the MppfcApvby
     */
    public void setMppfcApvby(String value) {
        setAttributeInternal(MPPFCAPVBY, value);
    }

    /**
     * Gets the attribute value for MppfcApvdat, using the alias name MppfcApvdat.
     * @return the value of MppfcApvdat
     */
    public Date getMppfcApvdat() {
        return (Date) getAttributeInternal(MPPFCAPVDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcApvdat.
     * @param value value to set the MppfcApvdat
     */
    public void setMppfcApvdat(Date value) {
        setAttributeInternal(MPPFCAPVDAT, value);
    }

    /**
     * Gets the attribute value for MppfcAdr1, using the alias name MppfcAdr1.
     * @return the value of MppfcAdr1
     */
    public String getMppfcAdr1() {
        return (String) getAttributeInternal(MPPFCADR1);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr1.
     * @param value value to set the MppfcAdr1
     */
    public void setMppfcAdr1(String value) {
        setAttributeInternal(MPPFCADR1, value);
    }

    /**
     * Gets the attribute value for MppfcAdr2, using the alias name MppfcAdr2.
     * @return the value of MppfcAdr2
     */
    public String getMppfcAdr2() {
        return (String) getAttributeInternal(MPPFCADR2);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr2.
     * @param value value to set the MppfcAdr2
     */
    public void setMppfcAdr2(String value) {
        setAttributeInternal(MPPFCADR2, value);
    }

    /**
     * Gets the attribute value for MppfcAdr3, using the alias name MppfcAdr3.
     * @return the value of MppfcAdr3
     */
    public String getMppfcAdr3() {
        return (String) getAttributeInternal(MPPFCADR3);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr3.
     * @param value value to set the MppfcAdr3
     */
    public void setMppfcAdr3(String value) {
        setAttributeInternal(MPPFCADR3, value);
    }

    /**
     * Gets the attribute value for MppfcAdr4, using the alias name MppfcAdr4.
     * @return the value of MppfcAdr4
     */
    public String getMppfcAdr4() {
        return (String) getAttributeInternal(MPPFCADR4);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr4.
     * @param value value to set the MppfcAdr4
     */
    public void setMppfcAdr4(String value) {
        setAttributeInternal(MPPFCADR4, value);
    }

    /**
     * Gets the attribute value for MppfcAdr1Local, using the alias name MppfcAdr1Local.
     * @return the value of MppfcAdr1Local
     */
    public String getMppfcAdr1Local() {
        return (String) getAttributeInternal(MPPFCADR1LOCAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr1Local.
     * @param value value to set the MppfcAdr1Local
     */
    public void setMppfcAdr1Local(String value) {
        setAttributeInternal(MPPFCADR1LOCAL, value);
    }

    /**
     * Gets the attribute value for MppfcAdr2Local, using the alias name MppfcAdr2Local.
     * @return the value of MppfcAdr2Local
     */
    public String getMppfcAdr2Local() {
        return (String) getAttributeInternal(MPPFCADR2LOCAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr2Local.
     * @param value value to set the MppfcAdr2Local
     */
    public void setMppfcAdr2Local(String value) {
        setAttributeInternal(MPPFCADR2LOCAL, value);
    }

    /**
     * Gets the attribute value for MppfcAdr3Local, using the alias name MppfcAdr3Local.
     * @return the value of MppfcAdr3Local
     */
    public String getMppfcAdr3Local() {
        return (String) getAttributeInternal(MPPFCADR3LOCAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr3Local.
     * @param value value to set the MppfcAdr3Local
     */
    public void setMppfcAdr3Local(String value) {
        setAttributeInternal(MPPFCADR3LOCAL, value);
    }

    /**
     * Gets the attribute value for MppfcAdr4Local, using the alias name MppfcAdr4Local.
     * @return the value of MppfcAdr4Local
     */
    public String getMppfcAdr4Local() {
        return (String) getAttributeInternal(MPPFCADR4LOCAL);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr4Local.
     * @param value value to set the MppfcAdr4Local
     */
    public void setMppfcAdr4Local(String value) {
        setAttributeInternal(MPPFCADR4LOCAL, value);
    }

    /**
     * Gets the attribute value for Updby, using the alias name Updby.
     * @return the value of Updby
     */
    public String getUpdby() {
        return (String) getAttributeInternal(UPDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for Updby.
     * @param value value to set the Updby
     */
    public void setUpdby(String value) {
        setAttributeInternal(UPDBY, value);
    }

    /**
     * Gets the attribute value for Upddat, using the alias name Upddat.
     * @return the value of Upddat
     */
    public Date getUpddat() {
        return (Date) getAttributeInternal(UPDDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Upddat.
     * @param value value to set the Upddat
     */
    public void setUpddat(Date value) {
        setAttributeInternal(UPDDAT, value);
    }

    /**
     * Gets the attribute value for MppfcRmk, using the alias name MppfcRmk.
     * @return the value of MppfcRmk
     */
    public String getMppfcRmk() {
        return (String) getAttributeInternal(MPPFCRMK);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcRmk.
     * @param value value to set the MppfcRmk
     */
    public void setMppfcRmk(String value) {
        setAttributeInternal(MPPFCRMK, value);
    }

    /**
     * Gets the attribute value for MppfcAdr, using the alias name MppfcAdr.
     * @return the value of MppfcAdr
     */
    public String getMppfcAdr() {
        return (String) getAttributeInternal(MPPFCADR);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcAdr.
     * @param value value to set the MppfcAdr
     */
    public void setMppfcAdr(String value) {
        setAttributeInternal(MPPFCADR, value);
    }

    /**
     * Gets the attribute value for MppfcCtycod, using the alias name MppfcCtycod.
     * @return the value of MppfcCtycod
     */
    public String getMppfcCtycod() {
        return (String) getAttributeInternal(MPPFCCTYCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcCtycod.
     * @param value value to set the MppfcCtycod
     */
    public void setMppfcCtycod(String value) {
        setAttributeInternal(MPPFCCTYCOD, value);
    }

    /**
     * Gets the attribute value for MppfcLoccod, using the alias name MppfcLoccod.
     * @return the value of MppfcLoccod
     */
    public String getMppfcLoccod() {
        return (String) getAttributeInternal(MPPFCLOCCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcLoccod.
     * @param value value to set the MppfcLoccod
     */
    public void setMppfcLoccod(String value) {
        setAttributeInternal(MPPFCLOCCOD, value);
    }

    /**
     * Gets the attribute value for Rowid, using the alias name Rowid.
     * @return the value of Rowid
     */
    public RowID getRowid() {
        return (RowID) getAttributeInternal(ROWID);
    }

    /**
     * Gets the attribute value for PbId, using the alias name PbId.
     * @return the value of PbId
     */
    public String getPbId() {
        return (String) getAttributeInternal(PBID);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbId.
     * @param value value to set the PbId
     */
    public void setPbId(String value) {
        setAttributeInternal(PBID, value);
    }

    /**
     * Gets the attribute value for PbFulnam, using the alias name PbFulnam.
     * @return the value of PbFulnam
     */
    public String getPbFulnam() {
        return (String) getAttributeInternal(PBFULNAM);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbFulnam.
     * @param value value to set the PbFulnam
     */
    public void setPbFulnam(String value) {
        setAttributeInternal(PBFULNAM, value);
    }

    /**
     * Gets the attribute value for PbStscod, using the alias name PbStscod.
     * @return the value of PbStscod
     */
    public String getPbStscod() {
        return (String) getAttributeInternal(PBSTSCOD);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbStscod.
     * @param value value to set the PbStscod
     */
    public void setPbStscod(String value) {
        setAttributeInternal(PBSTSCOD, value);
    }

    /**
     * Gets the attribute value for PbSup, using the alias name PbSup.
     * @return the value of PbSup
     */
    public String getPbSup() {
        return (String) getAttributeInternal(PBSUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbSup.
     * @param value value to set the PbSup
     */
    public void setPbSup(String value) {
        setAttributeInternal(PBSUP, value);
    }

    /**
     * Gets the attribute value for PbShp, using the alias name PbShp.
     * @return the value of PbShp
     */
    public String getPbShp() {
        return (String) getAttributeInternal(PBSHP);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbShp.
     * @param value value to set the PbShp
     */
    public void setPbShp(String value) {
        setAttributeInternal(PBSHP, value);
    }

    /**
     * Gets the attribute value for PbMan, using the alias name PbMan.
     * @return the value of PbMan
     */
    public String getPbMan() {
        return (String) getAttributeInternal(PBMAN);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbMan.
     * @param value value to set the PbMan
     */
    public void setPbMan(String value) {
        setAttributeInternal(PBMAN, value);
    }

    /**
     * Gets the attribute value for PbMat, using the alias name PbMat.
     * @return the value of PbMat
     */
    public String getPbMat() {
        return (String) getAttributeInternal(PBMAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbMat.
     * @param value value to set the PbMat
     */
    public void setPbMat(String value) {
        setAttributeInternal(PBMAT, value);
    }

    /**
     * Gets the attribute value for PbCon, using the alias name PbCon.
     * @return the value of PbCon
     */
    public String getPbCon() {
        return (String) getAttributeInternal(PBCON);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbCon.
     * @param value value to set the PbCon
     */
    public void setPbCon(String value) {
        setAttributeInternal(PBCON, value);
    }

    /**
     * Gets the attribute value for PbGrp, using the alias name PbGrp.
     * @return the value of PbGrp
     */
    public String getPbGrp() {
        return (String) getAttributeInternal(PBGRP);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbGrp.
     * @param value value to set the PbGrp
     */
    public void setPbGrp(String value) {
        setAttributeInternal(PBGRP, value);
    }

    /**
     * Gets the attribute value for PbSbmby, using the alias name PbSbmby.
     * @return the value of PbSbmby
     */
    public String getPbSbmby() {
        return (String) getAttributeInternal(PBSBMBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbSbmby.
     * @param value value to set the PbSbmby
     */
    public void setPbSbmby(String value) {
        setAttributeInternal(PBSBMBY, value);
    }

    /**
     * Gets the attribute value for PbSbmdat, using the alias name PbSbmdat.
     * @return the value of PbSbmdat
     */
    public String getPbSbmdat() {
        return (String) getAttributeInternal(PBSBMDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbSbmdat.
     * @param value value to set the PbSbmdat
     */
    public void setPbSbmdat(String value) {
        setAttributeInternal(PBSBMDAT, value);
    }

    /**
     * Gets the attribute value for PbCfmby, using the alias name PbCfmby.
     * @return the value of PbCfmby
     */
    public String getPbCfmby() {
        return (String) getAttributeInternal(PBCFMBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbCfmby.
     * @param value value to set the PbCfmby
     */
    public void setPbCfmby(String value) {
        setAttributeInternal(PBCFMBY, value);
    }

    /**
     * Gets the attribute value for PbCfmdat, using the alias name PbCfmdat.
     * @return the value of PbCfmdat
     */
    public String getPbCfmdat() {
        return (String) getAttributeInternal(PBCFMDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbCfmdat.
     * @param value value to set the PbCfmdat
     */
    public void setPbCfmdat(String value) {
        setAttributeInternal(PBCFMDAT, value);
    }

    /**
     * Gets the attribute value for PbUpdby, using the alias name PbUpdby.
     * @return the value of PbUpdby
     */
    public String getPbUpdby() {
        return (String) getAttributeInternal(PBUPDBY);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbUpdby.
     * @param value value to set the PbUpdby
     */
    public void setPbUpdby(String value) {
        setAttributeInternal(PBUPDBY, value);
    }

    /**
     * Gets the attribute value for PbUpddat, using the alias name PbUpddat.
     * @return the value of PbUpddat
     */
    public String getPbUpddat() {
        return (String) getAttributeInternal(PBUPDDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbUpddat.
     * @param value value to set the PbUpddat
     */
    public void setPbUpddat(String value) {
        setAttributeInternal(PBUPDDAT, value);
    }

    /**
     * Gets the attribute value for PbRmk, using the alias name PbRmk.
     * @return the value of PbRmk
     */
    public String getPbRmk() {
        return (String) getAttributeInternal(PBRMK);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbRmk.
     * @param value value to set the PbRmk
     */
    public void setPbRmk(String value) {
        setAttributeInternal(PBRMK, value);
    }

    /**
     * Gets the attribute value for PbAdr, using the alias name PbAdr.
     * @return the value of PbAdr
     */
    public String getPbAdr() {
        return (String) getAttributeInternal(PBADR);
    }

    /**
     * Sets <code>value</code> as the attribute value for PbAdr.
     * @param value value to set the PbAdr
     */
    public void setPbAdr(String value) {
        setAttributeInternal(PBADR, value);
    }

    /**
     * Gets the attribute value for MppfcId, using the alias name MppfcId.
     * @return the value of MppfcId
     */
    public String getMppfcId() {
        return (String) getAttributeInternal(MPPFCID);
    }


    /**
     * Sets <code>value</code> as the attribute value for MppfcId.
     * @param value value to set the MppfcId
     */
    public void setMppfcId(String value) {
        setAttributeInternal(MPPFCID, value);
    }

    /**
     * Gets the attribute value for MppfcStscodDesc, using the alias name MppfcStscodDesc.
     * @return the value of MppfcStscodDesc
     */
    public String getMppfcStscodDesc() {
        return (String) getAttributeInternal(MPPFCSTSCODDESC);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcStscodDesc.
     * @param value value to set the MppfcStscodDesc
     */
    public void setMppfcStscodDesc(String value) {
        setAttributeInternal(MPPFCSTSCODDESC, value);
    }

    /**
     * Gets the attribute value for MppfcGrpDesc, using the alias name MppfcGrpDesc.
     * @return the value of MppfcGrpDesc
     */
    public String getMppfcGrpDesc() {
        return (String) getAttributeInternal(MPPFCGRPDESC);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcGrpDesc.
     * @param value value to set the MppfcGrpDesc
     */
    public void setMppfcGrpDesc(String value) {
        populateAttribute(MPPFCGRPDESC, value);
    }


    /**
     * Gets the attribute value for Crtdat, using the alias name Crtdat.
     * @return the value of Crtdat
     */
    public Timestamp getCrtdat() {
        return (Timestamp) getAttributeInternal(CRTDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for Crtdat.
     * @param value value to set the Crtdat
     */
    public void setCrtdat(Timestamp value) {
        setAttributeInternal(CRTDAT, value);
    }

    /**
     * Gets the attribute value for MppfcTyp, using the alias name MppfcTyp.
     * @return the value of MppfcTyp
     */
    public String getMppfcTyp() {
        return (String) getAttributeInternal(MPPFCTYP);
    }

    /**
     * Sets <code>value</code> as the attribute value for MppfcTyp.
     * @param value value to set the MppfcTyp
     */
    public void setMppfcTyp(String value) {
        setAttributeInternal(MPPFCTYP, value);
    }

    /**
     * @param mppfcRunnum key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(BigDecimal mppfcRunnum) {
        return new Key(new Object[] { mppfcRunnum });
    }

    @Override
    protected void doDML(int operation, TransactionEvent transactionEvent)
    {
        // TODO Implement this method
        
        if(operation == DML_INSERT)
        {
            System.out.println(" Before Insert the Query Logic ###### pri()");
        }
       
        if(operation== DML_UPDATE)
        {
            System.out.println(" Before Update the Query Logic ###### pru()");
        }
        if(operation== DML_DELETE)
        {
            System.out.println(" Before Delete the Query Logic ###### prd()");
        }
        
        super.doDML(operation, transactionEvent);
        
        if(operation == DML_INSERT)
        {
            System.out.println(" Post Insert the Query Logic ###### poi()");
        }
        
        if(operation== DML_UPDATE)
        {
            System.out.println(" Post Update the Query Logic ###### pou()");
        }
        if(operation== DML_DELETE)
        {
            System.out.println(" Post Delete the Query Logic ###### pod()");
        }
        
  
        
        
    }

}

