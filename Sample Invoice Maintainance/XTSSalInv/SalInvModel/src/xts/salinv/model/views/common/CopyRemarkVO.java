package xts.salinv.model.views.common;

import oracle.jbo.ViewObject;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Thu Sep 22 14:26:51 CST 2016
// ---------------------------------------------------------------------
public interface CopyRemarkVO extends ViewObject {
    void setCopyAttr(String loc, String rmkFld);

    void resetRow();


    void removeCopiedRemark();

    void processCopy(String loc);
}
