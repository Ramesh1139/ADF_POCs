package xts.xnsupr.model.dbwrappers;
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
public class DP_MIG_XNSUPR {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_MIG_XNSUPR.class);
    public static class PL_APPROVE13_PRR1_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_APPROVE13_PRR1_results PL_APPROVE13_PRR1(DBTransaction dbt ,String I_MPPFC_FULNAM)  {
        CallableStatement cs = null;
      PL_APPROVE13_PRR1_results results=new PL_APPROVE13_PRR1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE13_PRR1(I_MPPFC_FULNAM,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_MPPFC_FULNAM !=null) {
                cs.setString(1,I_MPPFC_FULNAM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) 
            {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) 
        {
            throw new JboException(e);
        } finally
        {
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
    public static class PL_APPROVE13_PRR2_results {
        String O_MPPFC_SBMBY_FULNAM;
        public void setO_MPPFC_SBMBY_FULNAM(String O_MPPFC_SBMBY_FULNAM) {
            this.O_MPPFC_SBMBY_FULNAM = O_MPPFC_SBMBY_FULNAM;
        }

        public String getO_MPPFC_SBMBY_FULNAM() {
            return O_MPPFC_SBMBY_FULNAM;
        }

    }
    public static PL_APPROVE13_PRR2_results PL_APPROVE13_PRR2(DBTransaction dbt ,String I_MPPFC_SBMBY)  {
        CallableStatement cs = null;
      PL_APPROVE13_PRR2_results results=new PL_APPROVE13_PRR2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_SBMBY VARCHAR2(32000) :=?;\n" +
                        "  O_MPPFC_SBMBY_FULNAM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE13_PRR2(I_MPPFC_SBMBY,O_MPPFC_SBMBY_FULNAM);\n" +
                        "  ?     := O_MPPFC_SBMBY_FULNAM;\n" +
                        "END;\n", 0);
            if (I_MPPFC_SBMBY !=null) {
                cs.setString(1,I_MPPFC_SBMBY);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPPFC_SBMBY_FULNAM(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPPFC_SBMBY_FULNAM(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE13_PRR3_results {
        String O_MPPFC_SBMBY_DIVISION;
        public void setO_MPPFC_SBMBY_DIVISION(String O_MPPFC_SBMBY_DIVISION) {
            this.O_MPPFC_SBMBY_DIVISION = O_MPPFC_SBMBY_DIVISION;
        }

        public String getO_MPPFC_SBMBY_DIVISION() {
            return O_MPPFC_SBMBY_DIVISION;
        }

    }
    public static PL_APPROVE13_PRR3_results PL_APPROVE13_PRR3(DBTransaction dbt ,String I_MPPFC_SBMBY)  {
        CallableStatement cs = null;
      PL_APPROVE13_PRR3_results results=new PL_APPROVE13_PRR3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_SBMBY VARCHAR2(32000) :=?;\n" +
                        "  O_MPPFC_SBMBY_DIVISION VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE13_PRR3(I_MPPFC_SBMBY,O_MPPFC_SBMBY_DIVISION);\n" +
                        "  ?     := O_MPPFC_SBMBY_DIVISION;\n" +
                        "END;\n", 0);
            if (I_MPPFC_SBMBY !=null) {
                cs.setString(1,I_MPPFC_SBMBY);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPPFC_SBMBY_DIVISION(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPPFC_SBMBY_DIVISION(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE23_POQ1_results {
        BigDecimal O_COUNT_TOTAL;
        public void setO_COUNT_TOTAL(BigDecimal O_COUNT_TOTAL) {
            this.O_COUNT_TOTAL = O_COUNT_TOTAL;
        }

        public BigDecimal getO_COUNT_TOTAL() {
            return O_COUNT_TOTAL;
        }

    }
    public static PL_APPROVE23_POQ1_results PL_APPROVE23_POQ1(DBTransaction dbt ,String I_MPPFS_COD)  {
        CallableStatement cs = null;
      PL_APPROVE23_POQ1_results results=new PL_APPROVE23_POQ1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFS_COD VARCHAR2(32000) :=?;\n" +
                        "  O_COUNT_TOTAL NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE23_POQ1(I_MPPFS_COD,O_COUNT_TOTAL);\n" +
                        "  ?     := O_COUNT_TOTAL;\n" +
                        "END;\n", 0);
            if (I_MPPFS_COD !=null) {
                cs.setString(1,I_MPPFS_COD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_COUNT_TOTAL(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_COUNT_TOTAL(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE313_POQ1_results {
        BigDecimal O_COUNT_TOTAL;
        public void setO_COUNT_TOTAL(BigDecimal O_COUNT_TOTAL) {
            this.O_COUNT_TOTAL = O_COUNT_TOTAL;
        }

        public BigDecimal getO_COUNT_TOTAL() {
            return O_COUNT_TOTAL;
        }

    }
    public static PL_APPROVE313_POQ1_results PL_APPROVE313_POQ1(DBTransaction dbt ,String I_OLD_PRFCOD)  {
        CallableStatement cs = null;
      PL_APPROVE313_POQ1_results results=new PL_APPROVE313_POQ1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_OLD_PRFCOD VARCHAR2(32000) :=?;\n" +
                        "  O_COUNT_TOTAL NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE313_POQ1(I_OLD_PRFCOD,O_COUNT_TOTAL);\n" +
                        "  ?     := O_COUNT_TOTAL;\n" +
                        "END;\n", 0);
            if (I_OLD_PRFCOD !=null) {
                cs.setString(1,I_OLD_PRFCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_COUNT_TOTAL(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_COUNT_TOTAL(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE43_BUTAPPROVE_BP1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_APPROVE43_BUTAPPROVE_BP1_results PL_APPROVE43_BUTAPPROVE_BP1(DBTransaction dbt ,String I_MPPFC_CTYCOD)  {
        CallableStatement cs = null;
      PL_APPROVE43_BUTAPPROVE_BP1_results results=new PL_APPROVE43_BUTAPPROVE_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP1(I_MPPFC_CTYCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_MPPFC_CTYCOD !=null) {
                cs.setString(1,I_MPPFC_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_I(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static void PL_APPROVE43_BUTAPPROVE_BP10(DBTransaction dbt ,String I_MPPFC_PRFCOD)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_PRFCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP10(I_MPPFC_PRFCOD);\n" +
                        "END;\n", 0);
            if (I_MPPFC_PRFCOD !=null) {
                cs.setString(1,I_MPPFC_PRFCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.executeUpdate();
        } catch (Exception e) {
            throw new JboException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
    }
    public static class PL_APPROVE43_BUTAPPROVE_BP2_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_APPROVE43_BUTAPPROVE_BP2_results PL_APPROVE43_BUTAPPROVE_BP2(DBTransaction dbt ,String I_MPPFC_CTYCOD,String I_MPPFC_LOCCOD)  {
        CallableStatement cs = null;
      PL_APPROVE43_BUTAPPROVE_BP2_results results=new PL_APPROVE43_BUTAPPROVE_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_LOCCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP2(I_MPPFC_CTYCOD,I_MPPFC_LOCCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_MPPFC_CTYCOD !=null) {
                cs.setString(1,I_MPPFC_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_MPPFC_LOCCOD !=null) {
                cs.setString(2,I_MPPFC_LOCCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE43_BUTAPPROVE_BP3_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_APPROVE43_BUTAPPROVE_BP3_results PL_APPROVE43_BUTAPPROVE_BP3(DBTransaction dbt ,String I_MPPFC_CTYCOD)  {
        CallableStatement cs = null;
      PL_APPROVE43_BUTAPPROVE_BP3_results results=new PL_APPROVE43_BUTAPPROVE_BP3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP3(I_MPPFC_CTYCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_MPPFC_CTYCOD !=null) {
                cs.setString(1,I_MPPFC_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_I(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE43_BUTAPPROVE_BP4_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_APPROVE43_BUTAPPROVE_BP4_results PL_APPROVE43_BUTAPPROVE_BP4(DBTransaction dbt ,String I_MPPFC_PRFCOD)  {
        CallableStatement cs = null;
      PL_APPROVE43_BUTAPPROVE_BP4_results results=new PL_APPROVE43_BUTAPPROVE_BP4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_PRFCOD VARCHAR2(32000) :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP4(I_MPPFC_PRFCOD,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_MPPFC_PRFCOD !=null) {
                cs.setString(1,I_MPPFC_PRFCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE43_BUTAPPROVE_BP5_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_APPROVE43_BUTAPPROVE_BP5_results PL_APPROVE43_BUTAPPROVE_BP5(DBTransaction dbt ,String I_MPPFC_FULNAM)  {
        CallableStatement cs = null;
      PL_APPROVE43_BUTAPPROVE_BP5_results results=new PL_APPROVE43_BUTAPPROVE_BP5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP5(I_MPPFC_FULNAM,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_MPPFC_FULNAM !=null) {
                cs.setString(1,I_MPPFC_FULNAM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_APPROVE43_BUTAPPROVE_BP6_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_APPROVE43_BUTAPPROVE_BP6_results PL_APPROVE43_BUTAPPROVE_BP6(DBTransaction dbt ,String I_SHORT_NAME)  {
        CallableStatement cs = null;
      PL_APPROVE43_BUTAPPROVE_BP6_results results=new PL_APPROVE43_BUTAPPROVE_BP6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SHORT_NAME VARCHAR2(32000) :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP6(I_SHORT_NAME,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_SHORT_NAME !=null) {
                cs.setString(1,I_SHORT_NAME);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static void PL_APPROVE43_BUTAPPROVE_BP7(DBTransaction dbt ,BigDecimal I_MPPFC_RUNNUM,String I_MPPFC_PRFCOD,String I_MPPFC_FULNAM,String I_G_USERID)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_RUNNUM NUMBER :=?;\n" +
                        "  I_MPPFC_PRFCOD VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  I_G_USERID VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP7(I_MPPFC_RUNNUM,I_MPPFC_PRFCOD,I_MPPFC_FULNAM,I_G_USERID);\n" +
                        "END;\n", 0);
            if (I_MPPFC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_MPPFC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_MPPFC_PRFCOD !=null) {
                cs.setString(2,I_MPPFC_PRFCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_MPPFC_FULNAM !=null) {
                cs.setString(3,I_MPPFC_FULNAM);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (I_G_USERID !=null) {
                cs.setString(4,I_G_USERID);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            cs.executeUpdate();
        } catch (Exception e) {
            throw new JboException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
    }
    public static void PL_APPROVE43_BUTAPPROVE_BP8(DBTransaction dbt ,String I_MPPFC_PRFCOD,String I_MPPFC_FULNAM_LOCAL,String I_MPPFC_FULNAM,String I_SHORT_NAME,String I_MPPFC_MAN,String I_MPPFC_MAT,String I_MPPFC_SUP,String I_MPPFC_SHP,String I_MPPFC_ADR1_LOCAL,String I_MPPFC_CON,String I_MPPFC_CONSUBROL,String I_MPPFC_ADR4_LOCAL,String I_MPPFC_ADR2_LOCAL,String I_MPPFC_ADR3_LOCAL)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_PRFCOD VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_FULNAM_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  I_SHORT_NAME VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_MAN VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_MAT VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_SUP VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_SHP VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR1_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_CON VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_CONSUBROL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR4_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR2_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR3_LOCAL VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP8(I_MPPFC_PRFCOD,I_MPPFC_FULNAM_LOCAL,I_MPPFC_FULNAM,I_SHORT_NAME,I_MPPFC_MAN,I_MPPFC_MAT,I_MPPFC_SUP,I_MPPFC_SHP,I_MPPFC_ADR1_LOCAL,I_MPPFC_CON,I_MPPFC_CONSUBROL,I_MPPFC_ADR4_LOCAL,I_MPPFC_ADR2_LOCAL,I_MPPFC_ADR3_LOCAL);\n" +
                        "END;\n", 0);
            if (I_MPPFC_PRFCOD !=null) {
                cs.setString(1,I_MPPFC_PRFCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_MPPFC_FULNAM_LOCAL !=null) {
                cs.setString(2,I_MPPFC_FULNAM_LOCAL);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_MPPFC_FULNAM !=null) {
                cs.setString(3,I_MPPFC_FULNAM);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (I_SHORT_NAME !=null) {
                cs.setString(4,I_SHORT_NAME);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            if (I_MPPFC_MAN !=null) {
                cs.setString(5,I_MPPFC_MAN);
            } else {
                cs.setNull(5, Types.VARCHAR);
            }
            if (I_MPPFC_MAT !=null) {
                cs.setString(6,I_MPPFC_MAT);
            } else {
                cs.setNull(6, Types.VARCHAR);
            }
            if (I_MPPFC_SUP !=null) {
                cs.setString(7,I_MPPFC_SUP);
            } else {
                cs.setNull(7, Types.VARCHAR);
            }
            if (I_MPPFC_SHP !=null) {
                cs.setString(8,I_MPPFC_SHP);
            } else {
                cs.setNull(8, Types.VARCHAR);
            }
            if (I_MPPFC_ADR1_LOCAL !=null) {
                cs.setString(9,I_MPPFC_ADR1_LOCAL);
            } else {
                cs.setNull(9, Types.VARCHAR);
            }
            if (I_MPPFC_CON !=null) {
                cs.setString(10,I_MPPFC_CON);
            } else {
                cs.setNull(10, Types.VARCHAR);
            }
            if (I_MPPFC_CONSUBROL !=null) {
                cs.setString(11,I_MPPFC_CONSUBROL);
            } else {
                cs.setNull(11, Types.VARCHAR);
            }
            if (I_MPPFC_ADR4_LOCAL !=null) {
                cs.setString(12,I_MPPFC_ADR4_LOCAL);
            } else {
                cs.setNull(12, Types.VARCHAR);
            }
            if (I_MPPFC_ADR2_LOCAL !=null) {
                cs.setString(13,I_MPPFC_ADR2_LOCAL);
            } else {
                cs.setNull(13, Types.VARCHAR);
            }
            if (I_MPPFC_ADR3_LOCAL !=null) {
                cs.setString(14,I_MPPFC_ADR3_LOCAL);
            } else {
                cs.setNull(14, Types.VARCHAR);
            }
            cs.executeUpdate();
        } catch (Exception e) {
            throw new JboException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
    }
    public static void PL_APPROVE43_BUTAPPROVE_BP9(DBTransaction dbt ,String I_MPPFC_PRFCOD,String I_MPPFC_ADR1,String I_MPPFC_ADR3,String I_MPPFC_ADR2,String I_MPPFC_ADR1_LOCAL,String I_MPPFC_ADR4,String I_MPPFC_ADR3_LOCAL,String I_MPPFC_ADR2_LOCAL,String I_MPPFC_CTYCOD,String I_MPPFC_ADR4_LOCAL,String I_MPPFC_LOCCOD)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_PRFCOD VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR1 VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR3 VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR2 VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR1_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR4 VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR3_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR2_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_ADR4_LOCAL VARCHAR2(32000) :=?;\n" +
                        "  I_MPPFC_LOCCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE43_BUTAPPROVE_BP9(I_MPPFC_PRFCOD,I_MPPFC_ADR1,I_MPPFC_ADR3,I_MPPFC_ADR2,I_MPPFC_ADR1_LOCAL,I_MPPFC_ADR4,I_MPPFC_ADR3_LOCAL,I_MPPFC_ADR2_LOCAL,I_MPPFC_CTYCOD,I_MPPFC_ADR4_LOCAL,I_MPPFC_LOCCOD);\n" +
                        "END;\n", 0);
            if (I_MPPFC_PRFCOD !=null) {
                cs.setString(1,I_MPPFC_PRFCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_MPPFC_ADR1 !=null) {
                cs.setString(2,I_MPPFC_ADR1);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_MPPFC_ADR3 !=null) {
                cs.setString(3,I_MPPFC_ADR3);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (I_MPPFC_ADR2 !=null) {
                cs.setString(4,I_MPPFC_ADR2);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            if (I_MPPFC_ADR1_LOCAL !=null) {
                cs.setString(5,I_MPPFC_ADR1_LOCAL);
            } else {
                cs.setNull(5, Types.VARCHAR);
            }
            if (I_MPPFC_ADR4 !=null) {
                cs.setString(6,I_MPPFC_ADR4);
            } else {
                cs.setNull(6, Types.VARCHAR);
            }
            if (I_MPPFC_ADR3_LOCAL !=null) {
                cs.setString(7,I_MPPFC_ADR3_LOCAL);
            } else {
                cs.setNull(7, Types.VARCHAR);
            }
            if (I_MPPFC_ADR2_LOCAL !=null) {
                cs.setString(8,I_MPPFC_ADR2_LOCAL);
            } else {
                cs.setNull(8, Types.VARCHAR);
            }
            if (I_MPPFC_CTYCOD !=null) {
                cs.setString(9,I_MPPFC_CTYCOD);
            } else {
                cs.setNull(9, Types.VARCHAR);
            }
            if (I_MPPFC_ADR4_LOCAL !=null) {
                cs.setString(10,I_MPPFC_ADR4_LOCAL);
            } else {
                cs.setNull(10, Types.VARCHAR);
            }
            if (I_MPPFC_LOCCOD !=null) {
                cs.setString(11,I_MPPFC_LOCCOD);
            } else {
                cs.setNull(11, Types.VARCHAR);
            }
            cs.executeUpdate();
        } catch (Exception e) {
            throw new JboException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
    }
    public static void PL_APPROVE53_BUTCONFIRM_BP1(DBTransaction dbt ,BigDecimal I_MPPFC_RUNNUM,String I_DECLINE_REASON,String I_G_USERID)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_RUNNUM NUMBER :=?;\n" +
                        "  I_DECLINE_REASON VARCHAR2(32000) :=?;\n" +
                        "  I_G_USERID VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_APPROVE53_BUTCONFIRM_BP1(I_MPPFC_RUNNUM,I_DECLINE_REASON,I_G_USERID);\n" +
                        "END;\n", 0);
            if (I_MPPFC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_MPPFC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_DECLINE_REASON !=null) {
                cs.setString(2,I_DECLINE_REASON);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_USERID !=null) {
                cs.setString(3,I_G_USERID);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.executeUpdate();
        } catch (Exception e) {
            throw new JboException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
    }
    public static class PL_ASSIGN_BLK3_APPROVE_41_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_ASSIGN_BLK3_APPROVE_41_results PL_ASSIGN_BLK3_APPROVE_41(DBTransaction dbt ,String I_MPPFC_FULNAM)  {
        CallableStatement cs = null;
      PL_ASSIGN_BLK3_APPROVE_41_results results=new PL_ASSIGN_BLK3_APPROVE_41_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_41(I_MPPFC_FULNAM,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_MPPFC_FULNAM !=null) {
                cs.setString(1,I_MPPFC_FULNAM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_ASSIGN_BLK3_APPROVE_42_results {
        String O_MPPFC_SBMBY_FULNAM;
        public void setO_MPPFC_SBMBY_FULNAM(String O_MPPFC_SBMBY_FULNAM) {
            this.O_MPPFC_SBMBY_FULNAM = O_MPPFC_SBMBY_FULNAM;
        }

        public String getO_MPPFC_SBMBY_FULNAM() {
            return O_MPPFC_SBMBY_FULNAM;
        }

    }
    public static PL_ASSIGN_BLK3_APPROVE_42_results PL_ASSIGN_BLK3_APPROVE_42(DBTransaction dbt ,String I_MPPFC_SBMBY)  {
        CallableStatement cs = null;
      PL_ASSIGN_BLK3_APPROVE_42_results results=new PL_ASSIGN_BLK3_APPROVE_42_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_SBMBY VARCHAR2(32000) :=?;\n" +
                        "  O_MPPFC_SBMBY_FULNAM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_42(I_MPPFC_SBMBY,O_MPPFC_SBMBY_FULNAM);\n" +
                        "  ?     := O_MPPFC_SBMBY_FULNAM;\n" +
                        "END;\n", 0);
            if (I_MPPFC_SBMBY !=null) {
                cs.setString(1,I_MPPFC_SBMBY);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPPFC_SBMBY_FULNAM(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPPFC_SBMBY_FULNAM(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_ASSIGN_BLK3_APPROVE_43_results {
        String O_MPPFC_SBMBY_DIVISION;
        public void setO_MPPFC_SBMBY_DIVISION(String O_MPPFC_SBMBY_DIVISION) {
            this.O_MPPFC_SBMBY_DIVISION = O_MPPFC_SBMBY_DIVISION;
        }

        public String getO_MPPFC_SBMBY_DIVISION() {
            return O_MPPFC_SBMBY_DIVISION;
        }

    }
    public static PL_ASSIGN_BLK3_APPROVE_43_results PL_ASSIGN_BLK3_APPROVE_43(DBTransaction dbt ,String I_MPPFC_SBMBY)  {
        CallableStatement cs = null;
      PL_ASSIGN_BLK3_APPROVE_43_results results=new PL_ASSIGN_BLK3_APPROVE_43_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_SBMBY VARCHAR2(32000) :=?;\n" +
                        "  O_MPPFC_SBMBY_DIVISION VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_ASSIGN_BLK3_APPROVE_43(I_MPPFC_SBMBY,O_MPPFC_SBMBY_DIVISION);\n" +
                        "  ?     := O_MPPFC_SBMBY_DIVISION;\n" +
                        "END;\n", 0);
            if (I_MPPFC_SBMBY !=null) {
                cs.setString(1,I_MPPFC_SBMBY);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPPFC_SBMBY_DIVISION(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPPFC_SBMBY_DIVISION(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_BLK1_APPROVE_REC1_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_BLK1_APPROVE_REC1_results PL_BLK1_APPROVE_REC1(DBTransaction dbt ,BigDecimal I_MPPFC_RUNNUM)  
    {
        CallableStatement cs = null;
      PL_BLK1_APPROVE_REC1_results results=new PL_BLK1_APPROVE_REC1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_RUNNUM NUMBER :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_BLK1_APPROVE_REC1(I_MPPFC_RUNNUM,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_MPPFC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_MPPFC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_BLK1_DELETE1_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_BLK1_DELETE1_results PL_BLK1_DELETE1(DBTransaction dbt ,BigDecimal I_MPPFC_RUNNUM)  {
        CallableStatement cs = null;
      PL_BLK1_DELETE1_results results=new PL_BLK1_DELETE1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_RUNNUM NUMBER :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_BLK1_DELETE1(I_MPPFC_RUNNUM,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_MPPFC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_MPPFC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static void PL_BLK1_DELETE2(DBTransaction dbt ,BigDecimal I_MPPFC_RUNNUM)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_RUNNUM NUMBER :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_BLK1_DELETE2(I_MPPFC_RUNNUM);\n" +
                        "END;\n", 0);
            if (I_MPPFC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_MPPFC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.executeUpdate();
        } catch (Exception e) {
            throw new JboException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
    }
    public static class PL_BLK1_ENABLE_INSERT1_results {
        String O_V_ALLOW_EDIT;
        public void setO_V_ALLOW_EDIT(String O_V_ALLOW_EDIT) {
            this.O_V_ALLOW_EDIT = O_V_ALLOW_EDIT;
        }

        public String getO_V_ALLOW_EDIT() {
            return O_V_ALLOW_EDIT;
        }

    }
    public static PL_BLK1_ENABLE_INSERT1_results PL_BLK1_ENABLE_INSERT1(DBTransaction dbt ,String I_G_AUTGRP)  {
        CallableStatement cs = null;
      PL_BLK1_ENABLE_INSERT1_results results=new PL_BLK1_ENABLE_INSERT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_G_AUTGRP VARCHAR2(32000) :=?;\n" +
                        "  O_V_ALLOW_EDIT VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_BLK1_ENABLE_INSERT1(I_G_AUTGRP,O_V_ALLOW_EDIT);\n" +
                        "  ?     := O_V_ALLOW_EDIT;\n" +
                        "END;\n", 0);
            if (I_G_AUTGRP !=null) {
                cs.setString(1,I_G_AUTGRP);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_V_ALLOW_EDIT(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_V_ALLOW_EDIT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_BLK1_ENABLE_INSERT2_results {
        String O_V_ALLOW_EDIT;
        public void setO_V_ALLOW_EDIT(String O_V_ALLOW_EDIT) {
            this.O_V_ALLOW_EDIT = O_V_ALLOW_EDIT;
        }

        public String getO_V_ALLOW_EDIT() {
            return O_V_ALLOW_EDIT;
        }

    }
    public static PL_BLK1_ENABLE_INSERT2_results PL_BLK1_ENABLE_INSERT2(DBTransaction dbt ,String I_G_AUTGRP)  {
        CallableStatement cs = null;
      PL_BLK1_ENABLE_INSERT2_results results=new PL_BLK1_ENABLE_INSERT2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_G_AUTGRP VARCHAR2(32000) :=?;\n" +
                        "  O_V_ALLOW_EDIT VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_BLK1_ENABLE_INSERT2(I_G_AUTGRP,O_V_ALLOW_EDIT);\n" +
                        "  ?     := O_V_ALLOW_EDIT;\n" +
                        "END;\n", 0);
            if (I_G_AUTGRP !=null) {
                cs.setString(1,I_G_AUTGRP);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_V_ALLOW_EDIT(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_V_ALLOW_EDIT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_CHK_SITE1_results {
        BigDecimal O_V_PRMS_RUNNUM;
        public void setO_V_PRMS_RUNNUM(BigDecimal O_V_PRMS_RUNNUM) {
            this.O_V_PRMS_RUNNUM = O_V_PRMS_RUNNUM;
        }

        public BigDecimal getO_V_PRMS_RUNNUM() {
            return O_V_PRMS_RUNNUM;
        }

    }
    public static PL_CHK_SITE1_results PL_CHK_SITE1(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_CHK_SITE1_results results=new PL_CHK_SITE1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_V_PRMS_RUNNUM NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_CHK_SITE1(O_V_PRMS_RUNNUM);\n" +
                        "  ?     := O_V_PRMS_RUNNUM;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_PRMS_RUNNUM(cs.getBigDecimal(1));
            if (cs.wasNull()) {
                results.setO_V_PRMS_RUNNUM(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_CONTROL1_RESUBMIT_BP1_results {
        String O_NEW_FULNAM;
        String O_NEW_ROLE_SUPP;
        String O_NEW_ROLE_SHIP;
        String O_NEW_ROLE_MANU;
        String O_NEW_ROLE_MATE;
        String O_NEW_ADR1;
        String O_NEW_ADR2;
        String O_NEW_ADR3;
        String O_NEW_ADR4;
        String O_NEW_CTYCOD;
        String O_NEW_LOCCOD;
        public void setO_NEW_FULNAM(String O_NEW_FULNAM) {
            this.O_NEW_FULNAM = O_NEW_FULNAM;
        }

        public String getO_NEW_FULNAM() {
            return O_NEW_FULNAM;
        }

        public void setO_NEW_ROLE_SUPP(String O_NEW_ROLE_SUPP) {
            this.O_NEW_ROLE_SUPP = O_NEW_ROLE_SUPP;
        }

        public String getO_NEW_ROLE_SUPP() {
            return O_NEW_ROLE_SUPP;
        }

        public void setO_NEW_ROLE_SHIP(String O_NEW_ROLE_SHIP) {
            this.O_NEW_ROLE_SHIP = O_NEW_ROLE_SHIP;
        }

        public String getO_NEW_ROLE_SHIP() {
            return O_NEW_ROLE_SHIP;
        }

        public void setO_NEW_ROLE_MANU(String O_NEW_ROLE_MANU) {
            this.O_NEW_ROLE_MANU = O_NEW_ROLE_MANU;
        }

        public String getO_NEW_ROLE_MANU() {
            return O_NEW_ROLE_MANU;
        }

        public void setO_NEW_ROLE_MATE(String O_NEW_ROLE_MATE) {
            this.O_NEW_ROLE_MATE = O_NEW_ROLE_MATE;
        }

        public String getO_NEW_ROLE_MATE() {
            return O_NEW_ROLE_MATE;
        }

        public void setO_NEW_ADR1(String O_NEW_ADR1) {
            this.O_NEW_ADR1 = O_NEW_ADR1;
        }

        public String getO_NEW_ADR1() {
            return O_NEW_ADR1;
        }

        public void setO_NEW_ADR2(String O_NEW_ADR2) {
            this.O_NEW_ADR2 = O_NEW_ADR2;
        }

        public String getO_NEW_ADR2() {
            return O_NEW_ADR2;
        }

        public void setO_NEW_ADR3(String O_NEW_ADR3) {
            this.O_NEW_ADR3 = O_NEW_ADR3;
        }

        public String getO_NEW_ADR3() {
            return O_NEW_ADR3;
        }

        public void setO_NEW_ADR4(String O_NEW_ADR4) {
            this.O_NEW_ADR4 = O_NEW_ADR4;
        }

        public String getO_NEW_ADR4() {
            return O_NEW_ADR4;
        }

        public void setO_NEW_CTYCOD(String O_NEW_CTYCOD) {
            this.O_NEW_CTYCOD = O_NEW_CTYCOD;
        }

        public String getO_NEW_CTYCOD() {
            return O_NEW_CTYCOD;
        }

        public void setO_NEW_LOCCOD(String O_NEW_LOCCOD) {
            this.O_NEW_LOCCOD = O_NEW_LOCCOD;
        }

        public String getO_NEW_LOCCOD() {
            return O_NEW_LOCCOD;
        }

    }
    public static PL_CONTROL1_RESUBMIT_BP1_results PL_CONTROL1_RESUBMIT_BP1(DBTransaction dbt ,BigDecimal I_MPPFC_RUNNUM)  {
        CallableStatement cs = null;
      PL_CONTROL1_RESUBMIT_BP1_results results=new PL_CONTROL1_RESUBMIT_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_RUNNUM NUMBER :=?;\n" +
                        "  O_NEW_FULNAM VARCHAR2(32000);\n" +
                        "  O_NEW_ROLE_SUPP VARCHAR2(32000);\n" +
                        "  O_NEW_ROLE_SHIP VARCHAR2(32000);\n" +
                        "  O_NEW_ROLE_MANU VARCHAR2(32000);\n" +
                        "  O_NEW_ROLE_MATE VARCHAR2(32000);\n" +
                        "  O_NEW_ADR1 VARCHAR2(32000);\n" +
                        "  O_NEW_ADR2 VARCHAR2(32000);\n" +
                        "  O_NEW_ADR3 VARCHAR2(32000);\n" +
                        "  O_NEW_ADR4 VARCHAR2(32000);\n" +
                        "  O_NEW_CTYCOD VARCHAR2(32000);\n" +
                        "  O_NEW_LOCCOD VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_CONTROL1_RESUBMIT_BP1(I_MPPFC_RUNNUM,O_NEW_FULNAM,O_NEW_ROLE_SUPP,O_NEW_ROLE_SHIP,O_NEW_ROLE_MANU,O_NEW_ROLE_MATE,O_NEW_ADR1,O_NEW_ADR2,O_NEW_ADR3,O_NEW_ADR4,O_NEW_CTYCOD,O_NEW_LOCCOD);\n" +
                        "  ?     := O_NEW_FULNAM;\n" +
                        "  ?     := O_NEW_ROLE_SUPP;\n" +
                        "  ?     := O_NEW_ROLE_SHIP;\n" +
                        "  ?     := O_NEW_ROLE_MANU;\n" +
                        "  ?     := O_NEW_ROLE_MATE;\n" +
                        "  ?     := O_NEW_ADR1;\n" +
                        "  ?     := O_NEW_ADR2;\n" +
                        "  ?     := O_NEW_ADR3;\n" +
                        "  ?     := O_NEW_ADR4;\n" +
                        "  ?     := O_NEW_CTYCOD;\n" +
                        "  ?     := O_NEW_LOCCOD;\n" +
                        "END;\n", 0);
            if (I_MPPFC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_MPPFC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.registerOutParameter(6,Types.VARCHAR);
            cs.registerOutParameter(7,Types.VARCHAR);
            cs.registerOutParameter(8,Types.VARCHAR);
            cs.registerOutParameter(9,Types.VARCHAR);
            cs.registerOutParameter(10,Types.VARCHAR);
            cs.registerOutParameter(11,Types.VARCHAR);
            cs.registerOutParameter(12,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_NEW_FULNAM(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_NEW_FULNAM(null);
            }
            results.setO_NEW_ROLE_SUPP(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_NEW_ROLE_SUPP(null);
            }
            results.setO_NEW_ROLE_SHIP(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_NEW_ROLE_SHIP(null);
            }
            results.setO_NEW_ROLE_MANU(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_NEW_ROLE_MANU(null);
            }
            results.setO_NEW_ROLE_MATE(cs.getString(6));
            if (cs.wasNull()) {
                results.setO_NEW_ROLE_MATE(null);
            }
            results.setO_NEW_ADR1(cs.getString(7));
            if (cs.wasNull()) {
                results.setO_NEW_ADR1(null);
            }
            results.setO_NEW_ADR2(cs.getString(8));
            if (cs.wasNull()) {
                results.setO_NEW_ADR2(null);
            }
            results.setO_NEW_ADR3(cs.getString(9));
            if (cs.wasNull()) {
                results.setO_NEW_ADR3(null);
            }
            results.setO_NEW_ADR4(cs.getString(10));
            if (cs.wasNull()) {
                results.setO_NEW_ADR4(null);
            }
            results.setO_NEW_CTYCOD(cs.getString(11));
            if (cs.wasNull()) {
                results.setO_NEW_CTYCOD(null);
            }
            results.setO_NEW_LOCCOD(cs.getString(12));
            if (cs.wasNull()) {
                results.setO_NEW_LOCCOD(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_FL_CTYDESC1_results {
        String O_CTY_DESC;
        public void setO_CTY_DESC(String O_CTY_DESC) {
            this.O_CTY_DESC = O_CTY_DESC;
        }

        public String getO_CTY_DESC() {
            return O_CTY_DESC;
        }

    }
    public static PL_FL_CTYDESC1_results PL_FL_CTYDESC1(DBTransaction dbt ,String I_CTYCOD)  {
        CallableStatement cs = null;
      PL_FL_CTYDESC1_results results=new PL_FL_CTYDESC1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  O_CTY_DESC VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_FL_CTYDESC1(I_CTYCOD,O_CTY_DESC);\n" +
                        "  ?     := O_CTY_DESC;\n" +
                        "END;\n", 0);
            if (I_CTYCOD !=null) {
                cs.setString(1,I_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_CTY_DESC(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_CTY_DESC(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_FL_LOCDESC1_results {
        String O_LOC_DESC;
        public void setO_LOC_DESC(String O_LOC_DESC) {
            this.O_LOC_DESC = O_LOC_DESC;
        }

        public String getO_LOC_DESC() {
            return O_LOC_DESC;
        }

    }
    public static PL_FL_LOCDESC1_results PL_FL_LOCDESC1(DBTransaction dbt ,String I_CTYCOD,String I_LOCCOD)  {
        CallableStatement cs = null;
      PL_FL_LOCDESC1_results results=new PL_FL_LOCDESC1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  I_LOCCOD VARCHAR2(32000) :=?;\n" +
                        "  O_LOC_DESC VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_FL_LOCDESC1(I_CTYCOD,I_LOCCOD,O_LOC_DESC);\n" +
                        "  ?     := O_LOC_DESC;\n" +
                        "END;\n", 0);
            if (I_CTYCOD !=null) {
                cs.setString(1,I_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_LOCCOD !=null) {
                cs.setString(2,I_LOCCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_LOC_DESC(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_LOC_DESC(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_GET_USER_MESSAGE1_results {
        String O_V_DESC;
        public void setO_V_DESC(String O_V_DESC) {
            this.O_V_DESC = O_V_DESC;
        }

        public String getO_V_DESC() {
            return O_V_DESC;
        }

    }
    public static PL_GET_USER_MESSAGE1_results PL_GET_USER_MESSAGE1(DBTransaction dbt ,String I_P_MSGCOD)  {
        CallableStatement cs = null;
      PL_GET_USER_MESSAGE1_results results=new PL_GET_USER_MESSAGE1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_P_MSGCOD VARCHAR2(32000) :=?;\n" +
                        "  O_V_DESC VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_GET_USER_MESSAGE1(I_P_MSGCOD,O_V_DESC);\n" +
                        "  ?     := O_V_DESC;\n" +
                        "END;\n", 0);
            if (I_P_MSGCOD !=null) {
                cs.setString(1,I_P_MSGCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_V_DESC(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_V_DESC(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_BUTNEXT_BP1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_INSERT13_BUTNEXT_BP1_results PL_INSERT13_BUTNEXT_BP1(DBTransaction dbt ,String I_NEW_CTYCOD)  {
        CallableStatement cs = null;
      PL_INSERT13_BUTNEXT_BP1_results results=new PL_INSERT13_BUTNEXT_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_NEW_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP1(I_NEW_CTYCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_NEW_CTYCOD !=null) {
                cs.setString(1,I_NEW_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_I(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_BUTNEXT_BP2_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_INSERT13_BUTNEXT_BP2_results PL_INSERT13_BUTNEXT_BP2(DBTransaction dbt ,String I_NEW_CTYCOD,String I_NEW_LOCCOD)  {
        CallableStatement cs = null;
      PL_INSERT13_BUTNEXT_BP2_results results=new PL_INSERT13_BUTNEXT_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_NEW_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_LOCCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP2(I_NEW_CTYCOD,I_NEW_LOCCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_NEW_CTYCOD !=null) {
                cs.setString(1,I_NEW_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_NEW_LOCCOD !=null) {
                cs.setString(2,I_NEW_LOCCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_BUTNEXT_BP3_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_INSERT13_BUTNEXT_BP3_results PL_INSERT13_BUTNEXT_BP3(DBTransaction dbt ,String I_NEW_CTYCOD)  {
        CallableStatement cs = null;
      PL_INSERT13_BUTNEXT_BP3_results results=new PL_INSERT13_BUTNEXT_BP3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_NEW_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP3(I_NEW_CTYCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_NEW_CTYCOD !=null) {
                cs.setString(1,I_NEW_CTYCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_I(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_BUTNEXT_BP4_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_INSERT13_BUTNEXT_BP4_results PL_INSERT13_BUTNEXT_BP4(DBTransaction dbt ,String I_NEW_FULNAM)  {
        CallableStatement cs = null;
      PL_INSERT13_BUTNEXT_BP4_results results=new PL_INSERT13_BUTNEXT_BP4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_NEW_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP4(I_NEW_FULNAM,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_NEW_FULNAM !=null) {
                cs.setString(1,I_NEW_FULNAM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_BUTNEXT_BP5_results {
        String O_V_SPAL_SUPCOD;
        public void setO_V_SPAL_SUPCOD(String O_V_SPAL_SUPCOD) {
            this.O_V_SPAL_SUPCOD = O_V_SPAL_SUPCOD;
        }

        public String getO_V_SPAL_SUPCOD() {
            return O_V_SPAL_SUPCOD;
        }

    }
    public static PL_INSERT13_BUTNEXT_BP5_results PL_INSERT13_BUTNEXT_BP5(DBTransaction dbt ,String I_NEW_FULNAM)  {
        CallableStatement cs = null;
      PL_INSERT13_BUTNEXT_BP5_results results=new PL_INSERT13_BUTNEXT_BP5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_NEW_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  O_V_SPAL_SUPCOD VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP5(I_NEW_FULNAM,O_V_SPAL_SUPCOD);\n" +
                        "  ?     := O_V_SPAL_SUPCOD;\n" +
                        "END;\n", 0);
            if (I_NEW_FULNAM !=null) {
                cs.setString(1,I_NEW_FULNAM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_V_SPAL_SUPCOD(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_V_SPAL_SUPCOD(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_BUTNEXT_BP6_results {
        BigDecimal O_V_COUNT;
        public void setO_V_COUNT(BigDecimal O_V_COUNT) {
            this.O_V_COUNT = O_V_COUNT;
        }

        public BigDecimal getO_V_COUNT() {
            return O_V_COUNT;
        }

    }
    public static PL_INSERT13_BUTNEXT_BP6_results PL_INSERT13_BUTNEXT_BP6(DBTransaction dbt ,String I_NEW_FULNAM)  {
        CallableStatement cs = null;
      PL_INSERT13_BUTNEXT_BP6_results results=new PL_INSERT13_BUTNEXT_BP6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_NEW_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  O_V_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP6(I_NEW_FULNAM,O_V_COUNT);\n" +
                        "  ?     := O_V_COUNT;\n" +
                        "END;\n", 0);
            if (I_NEW_FULNAM !=null) {
                cs.setString(1,I_NEW_FULNAM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_COUNT(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_BUTNEXT_BP7_results {
        String O_V_MPPFC_ID;
        public void setO_V_MPPFC_ID(String O_V_MPPFC_ID) {
            this.O_V_MPPFC_ID = O_V_MPPFC_ID;
        }

        public String getO_V_MPPFC_ID() {
            return O_V_MPPFC_ID;
        }

    }
    public static PL_INSERT13_BUTNEXT_BP7_results PL_INSERT13_BUTNEXT_BP7(DBTransaction dbt ,String I_NEW_FULNAM)  {
        CallableStatement cs = null;
      PL_INSERT13_BUTNEXT_BP7_results results=new PL_INSERT13_BUTNEXT_BP7_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_NEW_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  O_V_MPPFC_ID VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_BUTNEXT_BP7(I_NEW_FULNAM,O_V_MPPFC_ID);\n" +
                        "  ?     := O_V_MPPFC_ID;\n" +
                        "END;\n", 0);
            if (I_NEW_FULNAM !=null) {
                cs.setString(1,I_NEW_FULNAM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_V_MPPFC_ID(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_V_MPPFC_ID(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT13_PRR1_results {
        String O_MSG_BOX;
        public void setO_MSG_BOX(String O_MSG_BOX) {
            this.O_MSG_BOX = O_MSG_BOX;
        }

        public String getO_MSG_BOX() {
            return O_MSG_BOX;
        }

    }
    public static PL_INSERT13_PRR1_results PL_INSERT13_PRR1(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_INSERT13_PRR1_results results=new PL_INSERT13_PRR1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_MSG_BOX VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT13_PRR1(O_MSG_BOX);\n" +
                        "  ?     := O_MSG_BOX;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MSG_BOX(cs.getString(1));
            if (cs.wasNull()) {
                results.setO_MSG_BOX(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT23_BUTFINISH_BP1_results {
        BigDecimal O_V_SEQ_MPEC_RUNNUM;
        public void setO_V_SEQ_MPEC_RUNNUM(BigDecimal O_V_SEQ_MPEC_RUNNUM) {
            this.O_V_SEQ_MPEC_RUNNUM = O_V_SEQ_MPEC_RUNNUM;
        }

        public BigDecimal getO_V_SEQ_MPEC_RUNNUM() {
            return O_V_SEQ_MPEC_RUNNUM;
        }

    }
    public static PL_INSERT23_BUTFINISH_BP1_results PL_INSERT23_BUTFINISH_BP1(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_INSERT23_BUTFINISH_BP1_results results=new PL_INSERT23_BUTFINISH_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_V_SEQ_MPEC_RUNNUM NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT23_BUTFINISH_BP1(O_V_SEQ_MPEC_RUNNUM);\n" +
                        "  ?     := O_V_SEQ_MPEC_RUNNUM;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_SEQ_MPEC_RUNNUM(cs.getBigDecimal(1));
            if (cs.wasNull()) {
                results.setO_V_SEQ_MPEC_RUNNUM(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static void PL_INSERT23_BUTFINISH_BP2(DBTransaction dbt ,BigDecimal I_V_SEQ_MPEC_RUNNUM,String I_NEW_FULNAM,String I_NEW_ROLE_SUPP,String I_NEW_ROLE_SHIP,String I_NEW_ROLE_MANU,String I_NEW_ROLE_MATE,String I_V_RMK,String I_NEW_ADR1,String I_NEW_ADR3,String I_NEW_ADR2,String I_NEW_CTYCOD,String I_NEW_ADR4,String I_NEW_LOCCOD,String I_G_USERID)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_V_SEQ_MPEC_RUNNUM NUMBER :=?;\n" +
                        "  I_NEW_FULNAM VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ROLE_SUPP VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ROLE_SHIP VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ROLE_MANU VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ROLE_MATE VARCHAR2(32000) :=?;\n" +
                        "  I_V_RMK VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ADR1 VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ADR3 VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ADR2 VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_CTYCOD VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_ADR4 VARCHAR2(32000) :=?;\n" +
                        "  I_NEW_LOCCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_USERID VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT23_BUTFINISH_BP2(I_V_SEQ_MPEC_RUNNUM,I_NEW_FULNAM,I_NEW_ROLE_SUPP,I_NEW_ROLE_SHIP,I_NEW_ROLE_MANU,I_NEW_ROLE_MATE,I_V_RMK,I_NEW_ADR1,I_NEW_ADR3,I_NEW_ADR2,I_NEW_CTYCOD,I_NEW_ADR4,I_NEW_LOCCOD,I_G_USERID);\n" +
                        "END;\n", 0);
            if (I_V_SEQ_MPEC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_V_SEQ_MPEC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_NEW_FULNAM !=null) {
                cs.setString(2,I_NEW_FULNAM);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_NEW_ROLE_SUPP !=null) {
                cs.setString(3,I_NEW_ROLE_SUPP);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (I_NEW_ROLE_SHIP !=null) {
                cs.setString(4,I_NEW_ROLE_SHIP);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            if (I_NEW_ROLE_MANU !=null) {
                cs.setString(5,I_NEW_ROLE_MANU);
            } else {
                cs.setNull(5, Types.VARCHAR);
            }
            if (I_NEW_ROLE_MATE !=null) {
                cs.setString(6,I_NEW_ROLE_MATE);
            } else {
                cs.setNull(6, Types.VARCHAR);
            }
            if (I_V_RMK !=null) {
                cs.setString(7,I_V_RMK);
            } else {
                cs.setNull(7, Types.VARCHAR);
            }
            if (I_NEW_ADR1 !=null) {
                cs.setString(8,I_NEW_ADR1);
            } else {
                cs.setNull(8, Types.VARCHAR);
            }
            if (I_NEW_ADR3 !=null) {
                cs.setString(9,I_NEW_ADR3);
            } else {
                cs.setNull(9, Types.VARCHAR);
            }
            if (I_NEW_ADR2 !=null) {
                cs.setString(10,I_NEW_ADR2);
            } else {
                cs.setNull(10, Types.VARCHAR);
            }
            if (I_NEW_CTYCOD !=null) {
                cs.setString(11,I_NEW_CTYCOD);
            } else {
                cs.setNull(11, Types.VARCHAR);
            }
            if (I_NEW_ADR4 !=null) {
                cs.setString(12,I_NEW_ADR4);
            } else {
                cs.setNull(12, Types.VARCHAR);
            }
            if (I_NEW_LOCCOD !=null) {
                cs.setString(13,I_NEW_LOCCOD);
            } else {
                cs.setNull(13, Types.VARCHAR);
            }
            if (I_G_USERID !=null) {
                cs.setString(14,I_G_USERID);
            } else {
                cs.setNull(14, Types.VARCHAR);
            }
            cs.executeUpdate();
        } catch (Exception e) {
            throw new JboException(e);
        } finally {
            try {
                if (cs != null && !cs.isClosed()) {
                    cs.close();
                }
            } catch (SQLException e) {
                _logger.warning("unable to close the connection");
            }
        }
    }
    public static class PL_INSERT23_BUTHELP_BP1_results {
        String O_V_MSG;
        public void setO_V_MSG(String O_V_MSG) {
            this.O_V_MSG = O_V_MSG;
        }

        public String getO_V_MSG() {
            return O_V_MSG;
        }

    }
    public static PL_INSERT23_BUTHELP_BP1_results PL_INSERT23_BUTHELP_BP1(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_INSERT23_BUTHELP_BP1_results results=new PL_INSERT23_BUTHELP_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_V_MSG VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT23_BUTHELP_BP1(O_V_MSG);\n" +
                        "  ?     := O_V_MSG;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_V_MSG(cs.getString(1));
            if (cs.wasNull()) {
                results.setO_V_MSG(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_INSERT23_POQ1_results {
        BigDecimal O_COUNT_TOTAL;
        public void setO_COUNT_TOTAL(BigDecimal O_COUNT_TOTAL) {
            this.O_COUNT_TOTAL = O_COUNT_TOTAL;
        }

        public BigDecimal getO_COUNT_TOTAL() {
            return O_COUNT_TOTAL;
        }

    }
    public static PL_INSERT23_POQ1_results PL_INSERT23_POQ1(DBTransaction dbt ,String I_OLD_PRFCOD)  {
        CallableStatement cs = null;
      PL_INSERT23_POQ1_results results=new PL_INSERT23_POQ1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_OLD_PRFCOD VARCHAR2(32000) :=?;\n" +
                        "  O_COUNT_TOTAL NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_INSERT23_POQ1(I_OLD_PRFCOD,O_COUNT_TOTAL);\n" +
                        "  ?     := O_COUNT_TOTAL;\n" +
                        "END;\n", 0);
            if (I_OLD_PRFCOD !=null) {
                cs.setString(1,I_OLD_PRFCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_COUNT_TOTAL(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_COUNT_TOTAL(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
    public static class PL_LOCK_RECORD1_results {
        BigDecimal O_V_MPPFC_RUNNUM;
        public void setO_V_MPPFC_RUNNUM(BigDecimal O_V_MPPFC_RUNNUM) {
            this.O_V_MPPFC_RUNNUM = O_V_MPPFC_RUNNUM;
        }

        public BigDecimal getO_V_MPPFC_RUNNUM() {
            return O_V_MPPFC_RUNNUM;
        }

    }
    public static PL_LOCK_RECORD1_results PL_LOCK_RECORD1(DBTransaction dbt ,BigDecimal I_MPPFC_RUNNUM)  {
        CallableStatement cs = null;
      PL_LOCK_RECORD1_results results=new PL_LOCK_RECORD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPPFC_RUNNUM NUMBER :=?;\n" +
                        "  O_V_MPPFC_RUNNUM NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XNSUPR.PL_LOCK_RECORD1(I_MPPFC_RUNNUM,O_V_MPPFC_RUNNUM);\n" +
                        "  ?     := O_V_MPPFC_RUNNUM;\n" +
                        "END;\n", 0);
            if (I_MPPFC_RUNNUM !=null) {
                cs.setBigDecimal(1,I_MPPFC_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_V_MPPFC_RUNNUM(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_V_MPPFC_RUNNUM(null);
            }
        } catch (Exception e) {
            throw new JboException(e);
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
