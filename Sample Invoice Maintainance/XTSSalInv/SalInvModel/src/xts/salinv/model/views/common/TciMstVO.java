package xts.salinv.model.views.common;

import oracle.jbo.ViewObject;
import oracle.jbo.domain.Date;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Fri Sep 02 16:10:09 CST 2016
// ---------------------------------------------------------------------
public interface TciMstVO extends ViewObject {
    void createTciMst(String divcod);


    void removeCreatedTciMst();

    void setSearchDivcodVar(String divcod);

    void commitSi();

    void ediComdatClr();

    String getAttachmentAccessRight();

    void chkAttachmentAccess(String accessRight, String callModule);

    void insertRecentUseLogAutonomous();

    boolean changeStatus(String changeTo, String deleteReason);

    Long insertTciMst();


    String plAddamtTpldefChkAll(String viewInstance, Long cimsRunnum);


    String paytrmcodDesExists();


    String applyDefault(Long cimsRunnum);

    void setCustomEditMode(String edit);

    void delRecentUseLogAutonomous();

    String plPrintSi();

    void cuscodChgActions();

    void prepareDisable();

    void reExeCusfld();

    void reexeQuery();

    void validateAllBlocks();

    String selectAllValueChange(boolean isSelect);
}

