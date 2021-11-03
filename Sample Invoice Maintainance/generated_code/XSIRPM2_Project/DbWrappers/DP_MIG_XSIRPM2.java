package DbWrappers;
import java.math.BigDecimal;

import java.sql.CallableStatement;
import java.sql.RowId;
import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Types;
import oracle.adf.share.logging.ADFLogger;
import java.sql.SQLException;

import oracle.jbo.JboException;
import oracle.jbo.server.DBTransaction;
import xts.formadf.model.exceptions.*;
public class DP_MIG_XSIRPM2 {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_MIG_XSIRPM2.class);
    public static class PL_INIT_VARIABLE1_results {
        String O_CUSCOD;
        String O_DPTCOD;
        public void setO_CUSCOD(String O_CUSCOD) {
            this.O_CUSCOD = O_CUSCOD;
        }

        public String getO_CUSCOD() {
            return O_CUSCOD;
        }

        public void setO_DPTCOD(String O_DPTCOD) {
            this.O_DPTCOD = O_DPTCOD;
        }

        public String getO_DPTCOD() {
            return O_DPTCOD;
        }

    }
    public static PL_INIT_VARIABLE1_results PL_INIT_VARIABLE1(DBTransaction dbt ,String I_W_DIV,String I_SIMS_INVNO_FROM)  {
        CallableStatement cs = null;
      PL_INIT_VARIABLE1_results results=new PL_INIT_VARIABLE1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_INVNO_FROM VARCHAR2(32000) :=?;\n" +
                        "  O_CUSCOD VARCHAR2(32000);\n" +
                        "  O_DPTCOD VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_INIT_VARIABLE1(I_W_DIV,I_SIMS_INVNO_FROM,O_CUSCOD,O_DPTCOD);\n" +
                        "  ?     := O_CUSCOD;\n" +
                        "  ?     := O_DPTCOD;\n" +
                        "END;\n", 0);
            if (I_W_DIV !=null) {
                cs.setString(1,I_W_DIV);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_INVNO_FROM !=null) {
                cs.setString(2,I_SIMS_INVNO_FROM);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_CUSCOD(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_CUSCOD(null);
            }
            results.setO_DPTCOD(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_DPTCOD(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_INIT_VARIABLE2_results {
        String O_INV_BACKGRD;
        public void setO_INV_BACKGRD(String O_INV_BACKGRD) {
            this.O_INV_BACKGRD = O_INV_BACKGRD;
        }

        public String getO_INV_BACKGRD() {
            return O_INV_BACKGRD;
        }

    }
    public static PL_INIT_VARIABLE2_results PL_INIT_VARIABLE2(DBTransaction dbt ,String I_INV_REPCODE)  {
        CallableStatement cs = null;
      PL_INIT_VARIABLE2_results results=new PL_INIT_VARIABLE2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_INV_REPCODE VARCHAR2(32000) :=?;\n" +
                        "  O_INV_BACKGRD VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_INIT_VARIABLE2(I_INV_REPCODE,O_INV_BACKGRD);\n" +
                        "  ?     := O_INV_BACKGRD;\n" +
                        "END;\n", 0);
            if (I_INV_REPCODE !=null) {
                cs.setString(1,I_INV_REPCODE);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_INV_BACKGRD(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_INV_BACKGRD(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_LOCK_SAMPLE_INVOICE1_results {
        BigDecimal O_SAMINVRUN;
        public void setO_SAMINVRUN(BigDecimal O_SAMINVRUN) {
            this.O_SAMINVRUN = O_SAMINVRUN;
        }

        public BigDecimal getO_SAMINVRUN() {
            return O_SAMINVRUN;
        }

    }
    public static PL_LOCK_SAMPLE_INVOICE1_results PL_LOCK_SAMPLE_INVOICE1(DBTransaction dbt ,String I_P_SIMS_INVNO_FROM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_LOCK_SAMPLE_INVOICE1_results results=new PL_LOCK_SAMPLE_INVOICE1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_P_SIMS_INVNO_FROM VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SAMINVRUN NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE1(I_P_SIMS_INVNO_FROM,I_G_DIVCOD,O_SAMINVRUN);\n" +
                        "  ?     := O_SAMINVRUN;\n" +
                        "END;\n", 0);
            if (I_P_SIMS_INVNO_FROM !=null) {
                cs.setString(1,I_P_SIMS_INVNO_FROM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SAMINVRUN(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_SAMINVRUN(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_LOCK_SAMPLE_INVOICE2_results {
        BigDecimal O_SAMINVRUN;
        public void setO_SAMINVRUN(BigDecimal O_SAMINVRUN) {
            this.O_SAMINVRUN = O_SAMINVRUN;
        }

        public BigDecimal getO_SAMINVRUN() {
            return O_SAMINVRUN;
        }

    }
    public static PL_LOCK_SAMPLE_INVOICE2_results PL_LOCK_SAMPLE_INVOICE2(DBTransaction dbt ,BigDecimal I_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_LOCK_SAMPLE_INVOICE2_results results=new PL_LOCK_SAMPLE_INVOICE2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SAMINVRUN NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE2(I_SAMINVRUN,I_G_DIVCOD,O_SAMINVRUN);\n" +
                        "  ?     := O_SAMINVRUN;\n" +
                        "END;\n", 0);
            if (I_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SAMINVRUN(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_SAMINVRUN(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_LOCK_SAMPLE_INVOICE3_results {
        String O_TEMP_NUM;
        public void setO_TEMP_NUM(String O_TEMP_NUM) {
            this.O_TEMP_NUM = O_TEMP_NUM;
        }

        public String getO_TEMP_NUM() {
            return O_TEMP_NUM;
        }

    }
    public static PL_LOCK_SAMPLE_INVOICE3_results PL_LOCK_SAMPLE_INVOICE3(DBTransaction dbt ,String I_G_DIVCOD,String I_G_LOCK_RUNNUM)  {
        CallableStatement cs = null;
      PL_LOCK_SAMPLE_INVOICE3_results results=new PL_LOCK_SAMPLE_INVOICE3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_LOCK_RUNNUM VARCHAR2(32000) :=?;\n" +
                        "  O_TEMP_NUM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_LOCK_SAMPLE_INVOICE3(I_G_DIVCOD,I_G_LOCK_RUNNUM,O_TEMP_NUM);\n" +
                        "  ?     := O_TEMP_NUM;\n" +
                        "END;\n", 0);
            if (I_G_DIVCOD !=null) {
                cs.setString(1,I_G_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_G_LOCK_RUNNUM !=null) {
                cs.setString(2,I_G_LOCK_RUNNUM);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_TEMP_NUM(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_TEMP_NUM(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_NFI1_results {
        String O_W_PRT;
        public void setO_W_PRT(String O_W_PRT) {
            this.O_W_PRT = O_W_PRT;
        }

        public String getO_W_PRT() {
            return O_W_PRT;
        }

    }
    public static PL_NFI1_results PL_NFI1(DBTransaction dbt ,String I_G_USERID,String I_G_MODULE)  {
        CallableStatement cs = null;
      PL_NFI1_results results=new PL_NFI1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_G_USERID VARCHAR2(32000) :=?;\n" +
                        "  I_G_MODULE VARCHAR2(32000) :=?;\n" +
                        "  O_W_PRT VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_NFI1(I_G_USERID,I_G_MODULE,O_W_PRT);\n" +
                        "  ?     := O_W_PRT;\n" +
                        "END;\n", 0);
            if (I_G_USERID !=null) {
                cs.setString(1,I_G_USERID);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_G_MODULE !=null) {
                cs.setString(2,I_G_MODULE);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_W_PRT(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_W_PRT(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_PRINT1_results {
        BigDecimal O_VCOUNT;
        public void setO_VCOUNT(BigDecimal O_VCOUNT) {
            this.O_VCOUNT = O_VCOUNT;
        }

        public BigDecimal getO_VCOUNT() {
            return O_VCOUNT;
        }

    }
    public static PL_PRINT1_results PL_PRINT1(DBTransaction dbt ,String I_SIMS_INVNO_FROM,String I_SIMS_INVNO_TO,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_PRINT1_results results=new PL_PRINT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_INVNO_FROM VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_INVNO_TO VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_VCOUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_PRINT1(I_SIMS_INVNO_FROM,I_SIMS_INVNO_TO,I_G_DIVCOD,O_VCOUNT);\n" +
                        "  ?     := O_VCOUNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_INVNO_FROM !=null) {
                cs.setString(1,I_SIMS_INVNO_FROM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_INVNO_TO !=null) {
                cs.setString(2,I_SIMS_INVNO_TO);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_VCOUNT(cs.getBigDecimal(4));
            if (cs.wasNull()) {
                results.setO_VCOUNT(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_PRINT_SAMINV1_results {
        String O_INV_REPCODE;
        public void setO_INV_REPCODE(String O_INV_REPCODE) {
            this.O_INV_REPCODE = O_INV_REPCODE;
        }

        public String getO_INV_REPCODE() {
            return O_INV_REPCODE;
        }

    }
    public static PL_PRINT_SAMINV1_results PL_PRINT_SAMINV1(DBTransaction dbt ,String I_INV_BACKGRD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_PRINT_SAMINV1_results results=new PL_PRINT_SAMINV1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_INV_BACKGRD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_INV_REPCODE VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_PRINT_SAMINV1(I_INV_BACKGRD,I_G_DIVCOD,O_INV_REPCODE);\n" +
                        "  ?     := O_INV_REPCODE;\n" +
                        "END;\n", 0);
            if (I_INV_BACKGRD !=null) {
                cs.setString(1,I_INV_BACKGRD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_INV_REPCODE(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_INV_REPCODE(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_PRINT_SAMINV2_results {
        String O_VLINFEED;
        public void setO_VLINFEED(String O_VLINFEED) {
            this.O_VLINFEED = O_VLINFEED;
        }

        public String getO_VLINFEED() {
            return O_VLINFEED;
        }

    }
    public static PL_PRINT_SAMINV2_results PL_PRINT_SAMINV2(DBTransaction dbt ,String I_W_DIV,String I_SIMS_CUSCOD)  {
        CallableStatement cs = null;
      PL_PRINT_SAMINV2_results results=new PL_PRINT_SAMINV2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  O_VLINFEED VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_PRINT_SAMINV2(I_W_DIV,I_SIMS_CUSCOD,O_VLINFEED);\n" +
                        "  ?     := O_VLINFEED;\n" +
                        "END;\n", 0);
            if (I_W_DIV !=null) {
                cs.setString(1,I_W_DIV);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(2,I_SIMS_CUSCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_VLINFEED(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_VLINFEED(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }
    public static class PL_PRINT_SAMINV3_results {
        String O_VLINFEED;
        public void setO_VLINFEED(String O_VLINFEED) {
            this.O_VLINFEED = O_VLINFEED;
        }

        public String getO_VLINFEED() {
            return O_VLINFEED;
        }

    }
    public static PL_PRINT_SAMINV3_results PL_PRINT_SAMINV3(DBTransaction dbt ,String I_W_DIV)  {
        CallableStatement cs = null;
      PL_PRINT_SAMINV3_results results=new PL_PRINT_SAMINV3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  O_VLINFEED VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIRPM2.PL_PRINT_SAMINV3(I_W_DIV,O_VLINFEED);\n" +
                        "  ?     := O_VLINFEED;\n" +
                        "END;\n", 0);
            if (I_W_DIV !=null) {
                cs.setString(1,I_W_DIV);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_VLINFEED(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_VLINFEED(null);
            }
        } catch (Exception e) {
            ExceptionMapper.mapException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
        return results;
    }


}
