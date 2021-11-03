package xts.salinv.model.views;

import oracle.adf.share.logging.ADFLogger;

import xts.common.model.framework.GenericViewObjectImpl;

import xts.salinv.model.views.common.TciDesShpitmVO;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 30 17:44:40 CST 2016
// ---    Custom code may be added to this class.
// ---    Warning: Do not modify method signatures of generated methods.
// ---------------------------------------------------------------------
public class TciDesShpitmVOImpl extends GenericViewObjectImpl implements TciDesShpitmVO {

    private static final ADFLogger LOGGER = ADFLogger.createADFLogger(TciCusfldShpitmVOImpl.class);

    /**
     * This is the default constructor (do not remove).
     */
    public TciDesShpitmVOImpl() {
    }

    public void createInsertWhenNoRecord() {
        int rowCount = this.getRowCount();
        LOGGER.info("createInsertWhenNoRecord rowCount: " + rowCount);

        if (rowCount < 1) {
            TciDesShpitmVORowImpl row = (TciDesShpitmVORowImpl) this.createRow();
            this.insertRow(row);
        }
    }
}

