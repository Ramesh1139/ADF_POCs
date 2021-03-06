package xts.xnsupr.model.entities;

import java.math.BigDecimal;

import java.sql.Date;

import java.sql.Timestamp;

import oracle.jbo.Key;
import oracle.jbo.domain.DBSequence;
import oracle.jbo.domain.RowID;
import oracle.jbo.server.EntityDefImpl;

import xts.common.model.framework.GenericEntityImpl;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Apr 18 14:52:29 IST 2017
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class Blk3Approve31Impl extends GenericEntityImpl {
    /**
     * AttributesEnum: generated enum for identifying attributes and accessors. DO NOT MODIFY.
     */
    protected enum AttributesEnum {
        OldPrfcod,
        OldFulnam,
        OldFlagSup,
        OldFlagShp,
        OldFlagFty,
        SpalClsdat,
        Rowid,
        OldFlagMat,
        OldFlagCon;
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


    public static final int OLDPRFCOD = AttributesEnum.OldPrfcod.index();
    public static final int OLDFULNAM = AttributesEnum.OldFulnam.index();
    public static final int OLDFLAGSUP = AttributesEnum.OldFlagSup.index();
    public static final int OLDFLAGSHP = AttributesEnum.OldFlagShp.index();
    public static final int OLDFLAGFTY = AttributesEnum.OldFlagFty.index();
    public static final int SPALCLSDAT = AttributesEnum.SpalClsdat.index();
    public static final int ROWID = AttributesEnum.Rowid.index();
    public static final int OLDFLAGMAT = AttributesEnum.OldFlagMat.index();
    public static final int OLDFLAGCON = AttributesEnum.OldFlagCon.index();

    /**
     * This is the default constructor (do not remove).
     */
    public Blk3Approve31Impl() {
    }

    /**
     * @return the definition object for this instance class.
     */
    public static synchronized EntityDefImpl getDefinitionObject() {
        return EntityDefImpl.findDefObject("xts.xnsupr.model.entities.Blk3Approve31");
    }


    /**
     * Gets the attribute value for OldPrfcod, using the alias name OldPrfcod.
     * @return the value of OldPrfcod
     */
    public String getOldPrfcod() {
        return (String) getAttributeInternal(OLDPRFCOD);
    }

    /**
     * Gets the attribute value for OldFulnam, using the alias name OldFulnam.
     * @return the value of OldFulnam
     */
    public String getOldFulnam() {
        return (String) getAttributeInternal(OLDFULNAM);
    }

    /**
     * Gets the attribute value for OldFlagSup, using the alias name OldFlagSup.
     * @return the value of OldFlagSup
     */
    public String getOldFlagSup() {
        return (String) getAttributeInternal(OLDFLAGSUP);
    }

    /**
     * Sets <code>value</code> as the attribute value for OldFlagSup.
     * @param value value to set the OldFlagSup
     */
    public void setOldFlagSup(String value) {
        setAttributeInternal(OLDFLAGSUP, value);
    }

    /**
     * Gets the attribute value for OldFlagShp, using the alias name OldFlagShp.
     * @return the value of OldFlagShp
     */
    public String getOldFlagShp() {
        return (String) getAttributeInternal(OLDFLAGSHP);
    }

    /**
     * Sets <code>value</code> as the attribute value for OldFlagShp.
     * @param value value to set the OldFlagShp
     */
    public void setOldFlagShp(String value) {
        setAttributeInternal(OLDFLAGSHP, value);
    }

    /**
     * Gets the attribute value for OldFlagFty, using the alias name OldFlagFty.
     * @return the value of OldFlagFty
     */
    public String getOldFlagFty() {
        return (String) getAttributeInternal(OLDFLAGFTY);
    }

    /**
     * Sets <code>value</code> as the attribute value for OldFlagFty.
     * @param value value to set the OldFlagFty
     */
    public void setOldFlagFty(String value) {
        setAttributeInternal(OLDFLAGFTY, value);
    }


    /**
     * Gets the attribute value for Rowid, using the alias name Rowid.
     * @return the value of Rowid
     */
    public RowID getRowid() {
        return (RowID) getAttributeInternal(ROWID);
    }

    /**
     * Gets the attribute value for SpalMat, using the alias name SpalMat.
     * @return the value of SpalMat
     */
    public String getOldFlagMat() {
        return (String) getAttributeInternal(OLDFLAGMAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SpalMat.
     * @param value value to set the SpalMat
     */
    public void setOldFlagMat(String value) {
        setAttributeInternal(OLDFLAGMAT, value);
    }

    /**
     * Gets the attribute value for SpalCon, using the alias name SpalCon.
     * @return the value of SpalCon
     */
    public String getOldFlagCon() {
        return (String) getAttributeInternal(OLDFLAGCON);
    }

    /**
     * Sets <code>value</code> as the attribute value for SpalCon.
     * @param value value to set the SpalCon
     */
    public void setOldFlagCon(String value) {
        setAttributeInternal(OLDFLAGCON, value);
    }

    /**
     * @param oldPrfcod key constituent

     * @return a Key object based on given key constituents.
     */
    public static Key createPrimaryKey(String oldPrfcod) {
        return new Key(new Object[] { oldPrfcod });
    }

    /**
     * Gets the attribute value for SpalClsdat, using the alias name SpalClsdat.
     * @return the value of SpalClsdat
     */
    public Date getSpalClsdat() {
        return (Date) getAttributeInternal(SPALCLSDAT);
    }

    /**
     * Sets <code>value</code> as the attribute value for SpalClsdat.
     * @param value value to set the SpalClsdat
     */
    public void setSpalClsdat(Date value) {
        setAttributeInternal(SPALCLSDAT, value);
    }


}

