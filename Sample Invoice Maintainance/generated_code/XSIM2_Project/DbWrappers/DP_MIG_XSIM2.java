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
public class DP_MIG_XSIM2 {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_MIG_XSIM2.class);
    public static class PL_BLK5_SAVE1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_BLK5_SAVE1_results PL_BLK5_SAVE1(DBTransaction dbt ,String I_SIIT_TYP)  {
        CallableStatement cs = null;
      PL_BLK5_SAVE1_results results=new PL_BLK5_SAVE1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_TYP VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_BLK5_SAVE1(I_SIIT_TYP,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_TYP !=null) {
                cs.setString(1,I_SIIT_TYP);
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
    public static class PL_BLK5_SAVE2_results {
        String O_STSCOD;
        Date O_CLSDAT;
        String O_ANNAPPROV;
        public void setO_STSCOD(String O_STSCOD) {
            this.O_STSCOD = O_STSCOD;
        }

        public String getO_STSCOD() {
            return O_STSCOD;
        }

        public void setO_CLSDAT(Date O_CLSDAT) {
            this.O_CLSDAT = O_CLSDAT;
        }

        public Date getO_CLSDAT() {
            return O_CLSDAT;
        }

        public void setO_ANNAPPROV(String O_ANNAPPROV) {
            this.O_ANNAPPROV = O_ANNAPPROV;
        }

        public String getO_ANNAPPROV() {
            return O_ANNAPPROV;
        }

    }
    public static PL_BLK5_SAVE2_results PL_BLK5_SAVE2(DBTransaction dbt ,String I_SIIT_SUPCOD)  {
        CallableStatement cs = null;
      PL_BLK5_SAVE2_results results=new PL_BLK5_SAVE2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_SUPCOD VARCHAR2(32000) :=?;\n" +
                        "  O_STSCOD VARCHAR2(32000);\n" +
                        "  O_CLSDAT DATE;\n" +
                        "  O_ANNAPPROV VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_BLK5_SAVE2(I_SIIT_SUPCOD,O_STSCOD,O_CLSDAT,O_ANNAPPROV);\n" +
                        "  ?     := O_STSCOD;\n" +
                        "  ?     := O_CLSDAT;\n" +
                        "  ?     := O_ANNAPPROV;\n" +
                        "END;\n", 0);
            if (I_SIIT_SUPCOD !=null) {
                cs.setString(1,I_SIIT_SUPCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.registerOutParameter(3,Types.DATE);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_STSCOD(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_STSCOD(null);
            }
            results.setO_CLSDAT(cs.getDate(3));
            if (cs.wasNull()) {
                results.setO_CLSDAT(null);
            }
            results.setO_ANNAPPROV(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_ANNAPPROV(null);
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
    public static class PL_BLK5_SAVE3_results {
        String O_STSMSG;
        public void setO_STSMSG(String O_STSMSG) {
            this.O_STSMSG = O_STSMSG;
        }

        public String getO_STSMSG() {
            return O_STSMSG;
        }

    }
    public static PL_BLK5_SAVE3_results PL_BLK5_SAVE3(DBTransaction dbt ,String I_STSCOD)  {
        CallableStatement cs = null;
      PL_BLK5_SAVE3_results results=new PL_BLK5_SAVE3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_STSCOD VARCHAR2(32000) :=?;\n" +
                        "  O_STSMSG VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_BLK5_SAVE3(I_STSCOD,O_STSMSG);\n" +
                        "  ?     := O_STSMSG;\n" +
                        "END;\n", 0);
            if (I_STSCOD !=null) {
                cs.setString(1,I_STSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_STSMSG(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_STSMSG(null);
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
    public static class PL_BLK5_SAVE4_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_BLK5_SAVE4_results PL_BLK5_SAVE4(DBTransaction dbt ,String I_SIIT_HARMONCOD)  {
        CallableStatement cs = null;
      PL_BLK5_SAVE4_results results=new PL_BLK5_SAVE4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_HARMONCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_BLK5_SAVE4(I_SIIT_HARMONCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_HARMONCOD !=null) {
                cs.setString(1,I_SIIT_HARMONCOD);
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
    public static class PL_BLK5_SAVE5_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_BLK5_SAVE5_results PL_BLK5_SAVE5(DBTransaction dbt ,String I_SIIT_UOM)  {
        CallableStatement cs = null;
      PL_BLK5_SAVE5_results results=new PL_BLK5_SAVE5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_UOM VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_BLK5_SAVE5(I_SIIT_UOM,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_UOM !=null) {
                cs.setString(1,I_SIIT_UOM);
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
    public static class PL_BLOCK_UPDATE_OK1_results {
        BigDecimal O_LOCK_RUNNUM;
        public void setO_LOCK_RUNNUM(BigDecimal O_LOCK_RUNNUM) {
            this.O_LOCK_RUNNUM = O_LOCK_RUNNUM;
        }

        public BigDecimal getO_LOCK_RUNNUM() {
            return O_LOCK_RUNNUM;
        }

    }
    public static PL_BLOCK_UPDATE_OK1_results PL_BLOCK_UPDATE_OK1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_BLOCK_UPDATE_OK1_results results=new PL_BLOCK_UPDATE_OK1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_LOCK_RUNNUM NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_BLOCK_UPDATE_OK1(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_LOCK_RUNNUM);\n" +
                        "  ?     := O_LOCK_RUNNUM;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_LOCK_RUNNUM(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_LOCK_RUNNUM(null);
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
    public static class PL_CONTROL1_COPY_BP1_results {
        String O_D_CLOSE;
        public void setO_D_CLOSE(String O_D_CLOSE) {
            this.O_D_CLOSE = O_D_CLOSE;
        }

        public String getO_D_CLOSE() {
            return O_D_CLOSE;
        }

    }
    public static PL_CONTROL1_COPY_BP1_results PL_CONTROL1_COPY_BP1(DBTransaction dbt ,String I_W_AUTGRP,String I_W_MODULE)  {
        CallableStatement cs = null;
      PL_CONTROL1_COPY_BP1_results results=new PL_CONTROL1_COPY_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_AUTGRP VARCHAR2(32000) :=?;\n" +
                        "  I_W_MODULE VARCHAR2(32000) :=?;\n" +
                        "  O_D_CLOSE VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_COPY_BP1(I_W_AUTGRP,I_W_MODULE,O_D_CLOSE);\n" +
                        "  ?     := O_D_CLOSE;\n" +
                        "END;\n", 0);
            if (I_W_AUTGRP !=null) {
                cs.setString(1,I_W_AUTGRP);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_W_MODULE !=null) {
                cs.setString(2,I_W_MODULE);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_D_CLOSE(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_D_CLOSE(null);
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
    public static class PL_CONTROL1_COPY_BP2_results {
        BigDecimal O_ROWCNT;
        public void setO_ROWCNT(BigDecimal O_ROWCNT) {
            this.O_ROWCNT = O_ROWCNT;
        }

        public BigDecimal getO_ROWCNT() {
            return O_ROWCNT;
        }

    }
    public static PL_CONTROL1_COPY_BP2_results PL_CONTROL1_COPY_BP2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL1_COPY_BP2_results results=new PL_CONTROL1_COPY_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_ROWCNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_COPY_BP2(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_ROWCNT);\n" +
                        "  ?     := O_ROWCNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_ROWCNT(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_ROWCNT(null);
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
    public static class PL_CONTROL1_COPY_BP3_results {
        BigDecimal O_ROWCNT;
        public void setO_ROWCNT(BigDecimal O_ROWCNT) {
            this.O_ROWCNT = O_ROWCNT;
        }

        public BigDecimal getO_ROWCNT() {
            return O_ROWCNT;
        }

    }
    public static PL_CONTROL1_COPY_BP3_results PL_CONTROL1_COPY_BP3(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL1_COPY_BP3_results results=new PL_CONTROL1_COPY_BP3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_ROWCNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_COPY_BP3(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_ROWCNT);\n" +
                        "  ?     := O_ROWCNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_ROWCNT(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_ROWCNT(null);
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
    public static class PL_CONTROL1_COPY_BP4_results {
        BigDecimal O_ROWCNT;
        public void setO_ROWCNT(BigDecimal O_ROWCNT) {
            this.O_ROWCNT = O_ROWCNT;
        }

        public BigDecimal getO_ROWCNT() {
            return O_ROWCNT;
        }

    }
    public static PL_CONTROL1_COPY_BP4_results PL_CONTROL1_COPY_BP4(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL1_COPY_BP4_results results=new PL_CONTROL1_COPY_BP4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_ROWCNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_COPY_BP4(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_ROWCNT);\n" +
                        "  ?     := O_ROWCNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_ROWCNT(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_ROWCNT(null);
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
    public static class PL_CONTROL1_COPY_BP5_results {
        BigDecimal O_ROWCNT;
        public void setO_ROWCNT(BigDecimal O_ROWCNT) {
            this.O_ROWCNT = O_ROWCNT;
        }

        public BigDecimal getO_ROWCNT() {
            return O_ROWCNT;
        }

    }
    public static PL_CONTROL1_COPY_BP5_results PL_CONTROL1_COPY_BP5(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL1_COPY_BP5_results results=new PL_CONTROL1_COPY_BP5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_ROWCNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_COPY_BP5(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_ROWCNT);\n" +
                        "  ?     := O_ROWCNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_ROWCNT(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_ROWCNT(null);
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
    public static class PL_CONTROL1_COPY_BP6_results {
        BigDecimal O_ROWCNT;
        public void setO_ROWCNT(BigDecimal O_ROWCNT) {
            this.O_ROWCNT = O_ROWCNT;
        }

        public BigDecimal getO_ROWCNT() {
            return O_ROWCNT;
        }

    }
    public static PL_CONTROL1_COPY_BP6_results PL_CONTROL1_COPY_BP6(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL1_COPY_BP6_results results=new PL_CONTROL1_COPY_BP6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_ROWCNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_COPY_BP6(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_ROWCNT);\n" +
                        "  ?     := O_ROWCNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_ROWCNT(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_ROWCNT(null);
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
    public static void PL_CONTROL1_DELETE_BP1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_DELETE_BP1(I_SIMS_SAMINVRUN);\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.executeUpdate();
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
    }
    public static void PL_CONTROL1_DELETE_BP2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_DELETE_BP2(I_SIMS_SAMINVRUN);\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.executeUpdate();
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
    }
    public static class PL_CONTROL1_EDIT_BP1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_CONTROL1_EDIT_BP1_results PL_CONTROL1_EDIT_BP1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL1_EDIT_BP1_results results=new PL_CONTROL1_EDIT_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL1_EDIT_BP1(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_CONTROL3_SAVE_BP1_results {
        String O_LSTCHG;
        public void setO_LSTCHG(String O_LSTCHG) {
            this.O_LSTCHG = O_LSTCHG;
        }

        public String getO_LSTCHG() {
            return O_LSTCHG;
        }

    }
    public static PL_CONTROL3_SAVE_BP1_results PL_CONTROL3_SAVE_BP1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_CONTROL3_SAVE_BP1_results results=new PL_CONTROL3_SAVE_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_LSTCHG VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL3_SAVE_BP1(I_SIMS_SAMINVRUN,O_LSTCHG);\n" +
                        "  ?     := O_LSTCHG;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_LSTCHG(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_LSTCHG(null);
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
    public static class PL_CONTROL3_SAVE_BP2_results {
        String O_LSTBY;
        public void setO_LSTBY(String O_LSTBY) {
            this.O_LSTBY = O_LSTBY;
        }

        public String getO_LSTBY() {
            return O_LSTBY;
        }

    }
    public static PL_CONTROL3_SAVE_BP2_results PL_CONTROL3_SAVE_BP2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_CONTROL3_SAVE_BP2_results results=new PL_CONTROL3_SAVE_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_LSTBY VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL3_SAVE_BP2(I_SIMS_SAMINVRUN,O_LSTBY);\n" +
                        "  ?     := O_LSTBY;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_LSTBY(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_LSTBY(null);
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
    public static class PL_CONTROL5_OK_BP1_results {
        String O_SIMS_TOTAMT;
        BigDecimal O_INV_AMT;
        public void setO_SIMS_TOTAMT(String O_SIMS_TOTAMT) {
            this.O_SIMS_TOTAMT = O_SIMS_TOTAMT;
        }

        public String getO_SIMS_TOTAMT() {
            return O_SIMS_TOTAMT;
        }

        public void setO_INV_AMT(BigDecimal O_INV_AMT) {
            this.O_INV_AMT = O_INV_AMT;
        }

        public BigDecimal getO_INV_AMT() {
            return O_INV_AMT;
        }

    }
    public static PL_CONTROL5_OK_BP1_results PL_CONTROL5_OK_BP1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL5_OK_BP1_results results=new PL_CONTROL5_OK_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIMS_TOTAMT VARCHAR2(32000);\n" +
                        "  O_INV_AMT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL5_OK_BP1(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_SIMS_TOTAMT,O_INV_AMT);\n" +
                        "  ?     := O_SIMS_TOTAMT;\n" +
                        "  ?     := O_INV_AMT;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SIMS_TOTAMT(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIMS_TOTAMT(null);
            }
            results.setO_INV_AMT(cs.getBigDecimal(4));
            if (cs.wasNull()) {
                results.setO_INV_AMT(null);
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
    public static class PL_CONTROL5_OK_BP2_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_CONTROL5_OK_BP2_results PL_CONTROL5_OK_BP2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL5_OK_BP2_results results=new PL_CONTROL5_OK_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL5_OK_BP2(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_CONTROL5_SIITITMNUM_PC1_results {
        String O_SIIT_HARMONCOD;
        public void setO_SIIT_HARMONCOD(String O_SIIT_HARMONCOD) {
            this.O_SIIT_HARMONCOD = O_SIIT_HARMONCOD;
        }

        public String getO_SIIT_HARMONCOD() {
            return O_SIIT_HARMONCOD;
        }

    }
    public static PL_CONTROL5_SIITITMNUM_PC1_results PL_CONTROL5_SIITITMNUM_PC1(DBTransaction dbt ,String I_SIIT_ITMNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_CONTROL5_SIITITMNUM_PC1_results results=new PL_CONTROL5_SIITITMNUM_PC1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_ITMNUM VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_HARMONCOD VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL5_SIITITMNUM_PC1(I_SIIT_ITMNUM,I_G_DIVCOD,O_SIIT_HARMONCOD);\n" +
                        "  ?     := O_SIIT_HARMONCOD;\n" +
                        "END;\n", 0);
            if (I_SIIT_ITMNUM !=null) {
                cs.setString(1,I_SIIT_ITMNUM);
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
            results.setO_SIIT_HARMONCOD(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIIT_HARMONCOD(null);
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
    public static class PL_CONTROL6_PRTEXCEL_BP1_results {
        String O_LINE_BUFFER;
        public void setO_LINE_BUFFER(String O_LINE_BUFFER) {
            this.O_LINE_BUFFER = O_LINE_BUFFER;
        }

        public String getO_LINE_BUFFER() {
            return O_LINE_BUFFER;
        }

    }
    public static PL_CONTROL6_PRTEXCEL_BP1_results PL_CONTROL6_PRTEXCEL_BP1(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_CONTROL6_PRTEXCEL_BP1_results results=new PL_CONTROL6_PRTEXCEL_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_LINE_BUFFER VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP1(O_LINE_BUFFER);\n" +
                        "  ?     := O_LINE_BUFFER;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_LINE_BUFFER(cs.getString(1));
            if (cs.wasNull()) {
                results.setO_LINE_BUFFER(null);
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
    public static class PL_CONTROL6_PRTEXCEL_BP2_results {
        String O_TEMP_STRING;
        public void setO_TEMP_STRING(String O_TEMP_STRING) {
            this.O_TEMP_STRING = O_TEMP_STRING;
        }

        public String getO_TEMP_STRING() {
            return O_TEMP_STRING;
        }

    }
    public static PL_CONTROL6_PRTEXCEL_BP2_results PL_CONTROL6_PRTEXCEL_BP2(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_CONTROL6_PRTEXCEL_BP2_results results=new PL_CONTROL6_PRTEXCEL_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_TEMP_STRING VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP2(O_TEMP_STRING);\n" +
                        "  ?     := O_TEMP_STRING;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_TEMP_STRING(cs.getString(1));
            if (cs.wasNull()) {
                results.setO_TEMP_STRING(null);
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
    public static class PL_CONTROL6_PRTEXCEL_BP3_results {
        BigDecimal O_COL_USED;
        public void setO_COL_USED(BigDecimal O_COL_USED) {
            this.O_COL_USED = O_COL_USED;
        }

        public BigDecimal getO_COL_USED() {
            return O_COL_USED;
        }

    }
    public static PL_CONTROL6_PRTEXCEL_BP3_results PL_CONTROL6_PRTEXCEL_BP3(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_CONTROL6_PRTEXCEL_BP3_results results=new PL_CONTROL6_PRTEXCEL_BP3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_COL_USED NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CONTROL6_PRTEXCEL_BP3(O_COL_USED);\n" +
                        "  ?     := O_COL_USED;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_COL_USED(cs.getBigDecimal(1));
            if (cs.wasNull()) {
                results.setO_COL_USED(null);
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
    public static class PL_CRITERIA2_LOVPORTD_BP1_results {
        String O_MPCP_NAM_D;
        public void setO_MPCP_NAM_D(String O_MPCP_NAM_D) {
            this.O_MPCP_NAM_D = O_MPCP_NAM_D;
        }

        public String getO_MPCP_NAM_D() {
            return O_MPCP_NAM_D;
        }

    }
    public static PL_CRITERIA2_LOVPORTD_BP1_results PL_CRITERIA2_LOVPORTD_BP1(DBTransaction dbt ,String I_SIMS_PORT_D)  {
        CallableStatement cs = null;
      PL_CRITERIA2_LOVPORTD_BP1_results results=new PL_CRITERIA2_LOVPORTD_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_D VARCHAR2(32000) :=?;\n" +
                        "  O_MPCP_NAM_D VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CRITERIA2_LOVPORTD_BP1(I_SIMS_PORT_D,O_MPCP_NAM_D);\n" +
                        "  ?     := O_MPCP_NAM_D;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_D !=null) {
                cs.setString(1,I_SIMS_PORT_D);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPCP_NAM_D(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPCP_NAM_D(null);
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
    public static class PL_CRITERIA2_MPCPNAMD_POT1_results {
        String O_SIMS_PORT_D;
        public void setO_SIMS_PORT_D(String O_SIMS_PORT_D) {
            this.O_SIMS_PORT_D = O_SIMS_PORT_D;
        }

        public String getO_SIMS_PORT_D() {
            return O_SIMS_PORT_D;
        }

    }
    public static PL_CRITERIA2_MPCPNAMD_POT1_results PL_CRITERIA2_MPCPNAMD_POT1(DBTransaction dbt ,String I_MPCP_NAM_D)  {
        CallableStatement cs = null;
      PL_CRITERIA2_MPCPNAMD_POT1_results results=new PL_CRITERIA2_MPCPNAMD_POT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPCP_NAM_D VARCHAR2(32000) :=?;\n" +
                        "  O_SIMS_PORT_D VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CRITERIA2_MPCPNAMD_POT1(I_MPCP_NAM_D,O_SIMS_PORT_D);\n" +
                        "  ?     := O_SIMS_PORT_D;\n" +
                        "END;\n", 0);
            if (I_MPCP_NAM_D !=null) {
                cs.setString(1,I_MPCP_NAM_D);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_PORT_D(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIMS_PORT_D(null);
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
    public static class PL_CRITERIA2_MPSMDES_POT1_results {
        String O_SIMS_SHPMODCOD;
        public void setO_SIMS_SHPMODCOD(String O_SIMS_SHPMODCOD) {
            this.O_SIMS_SHPMODCOD = O_SIMS_SHPMODCOD;
        }

        public String getO_SIMS_SHPMODCOD() {
            return O_SIMS_SHPMODCOD;
        }

    }
    public static PL_CRITERIA2_MPSMDES_POT1_results PL_CRITERIA2_MPSMDES_POT1(DBTransaction dbt ,String I_MPSM_DES)  {
        CallableStatement cs = null;
      PL_CRITERIA2_MPSMDES_POT1_results results=new PL_CRITERIA2_MPSMDES_POT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPSM_DES VARCHAR2(32000) :=?;\n" +
                        "  O_SIMS_SHPMODCOD VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_CRITERIA2_MPSMDES_POT1(I_MPSM_DES,O_SIMS_SHPMODCOD);\n" +
                        "  ?     := O_SIMS_SHPMODCOD;\n" +
                        "END;\n", 0);
            if (I_MPSM_DES !=null) {
                cs.setString(1,I_MPSM_DES);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_SHPMODCOD(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIMS_SHPMODCOD(null);
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
    public static class PL_FL_CREATE_TEXT1_results {
        BigDecimal O_LOCK_RUNNUM;
        public void setO_LOCK_RUNNUM(BigDecimal O_LOCK_RUNNUM) {
            this.O_LOCK_RUNNUM = O_LOCK_RUNNUM;
        }

        public BigDecimal getO_LOCK_RUNNUM() {
            return O_LOCK_RUNNUM;
        }

    }
    public static PL_FL_CREATE_TEXT1_results PL_FL_CREATE_TEXT1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_FL_CREATE_TEXT1_results results=new PL_FL_CREATE_TEXT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_LOCK_RUNNUM NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_FL_CREATE_TEXT1(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_LOCK_RUNNUM);\n" +
                        "  ?     := O_LOCK_RUNNUM;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_LOCK_RUNNUM(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_LOCK_RUNNUM(null);
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
    public static void PL_FL_CREATE_TEXT2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_FL_CREATE_TEXT2(I_SIMS_SAMINVRUN,I_G_DIVCOD);\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.executeUpdate();
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
                        "   DP_MIG_XSIM2.PL_GET_USER_MESSAGE1(I_P_MSGCOD,O_V_DESC);\n" +
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
    public static class PL_INIT_VARIABLE1_results {
        BigDecimal O_W_MDATE;
        public void setO_W_MDATE(BigDecimal O_W_MDATE) {
            this.O_W_MDATE = O_W_MDATE;
        }

        public BigDecimal getO_W_MDATE() {
            return O_W_MDATE;
        }

    }
    public static PL_INIT_VARIABLE1_results PL_INIT_VARIABLE1(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_INIT_VARIABLE1_results results=new PL_INIT_VARIABLE1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_W_MDATE NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_INIT_VARIABLE1(O_W_MDATE);\n" +
                        "  ?     := O_W_MDATE;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_W_MDATE(cs.getBigDecimal(1));
            if (cs.wasNull()) {
                results.setO_W_MDATE(null);
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
    public static class PL_ITMRMKITMRMK_REMARKOK_BP1_results {
        String O_SIIT_ITMDES;
        public void setO_SIIT_ITMDES(String O_SIIT_ITMDES) {
            this.O_SIIT_ITMDES = O_SIIT_ITMDES;
        }

        public String getO_SIIT_ITMDES() {
            return O_SIIT_ITMDES;
        }

    }
    public static PL_ITMRMKITMRMK_REMARKOK_BP1_results PL_ITMRMKITMRMK_REMARKOK_BP1(DBTransaction dbt ,String I_MPRM_RMKCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_ITMRMKITMRMK_REMARKOK_BP1_results results=new PL_ITMRMKITMRMK_REMARKOK_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPRM_RMKCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_ITMDES VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_ITMRMKITMRMK_REMARKOK_BP1(I_MPRM_RMKCOD,I_G_DIVCOD,O_SIIT_ITMDES);\n" +
                        "  ?     := O_SIIT_ITMDES;\n" +
                        "END;\n", 0);
            if (I_MPRM_RMKCOD !=null) {
                cs.setString(1,I_MPRM_RMKCOD);
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
            results.setO_SIIT_ITMDES(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIIT_ITMDES(null);
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
    public static class PL_LOCK_SI1_results {
        BigDecimal O_SI_RUN;
        public void setO_SI_RUN(BigDecimal O_SI_RUN) {
            this.O_SI_RUN = O_SI_RUN;
        }

        public BigDecimal getO_SI_RUN() {
            return O_SI_RUN;
        }

    }
    public static PL_LOCK_SI1_results PL_LOCK_SI1(DBTransaction dbt ,String I_DIVCOD,BigDecimal I_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_LOCK_SI1_results results=new PL_LOCK_SI1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SI_RUN NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_LOCK_SI1(I_DIVCOD,I_SAMINVRUN,O_SI_RUN);\n" +
                        "  ?     := O_SI_RUN;\n" +
                        "END;\n", 0);
            if (I_DIVCOD !=null) {
                cs.setString(1,I_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SAMINVRUN !=null) {
                cs.setBigDecimal(2,I_SAMINVRUN);
            } else {
                cs.setNull(2, Types.NUMERIC);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SI_RUN(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_SI_RUN(null);
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
        String O_W_AUTGRP;
        public void setO_W_AUTGRP(String O_W_AUTGRP) {
            this.O_W_AUTGRP = O_W_AUTGRP;
        }

        public String getO_W_AUTGRP() {
            return O_W_AUTGRP;
        }

    }
    public static PL_NFI1_results PL_NFI1(DBTransaction dbt ,String I_W_USERID)  {
        CallableStatement cs = null;
      PL_NFI1_results results=new PL_NFI1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_USERID VARCHAR2(32000) :=?;\n" +
                        "  O_W_AUTGRP VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_NFI1(I_W_USERID,O_W_AUTGRP);\n" +
                        "  ?     := O_W_AUTGRP;\n" +
                        "END;\n", 0);
            if (I_W_USERID !=null) {
                cs.setString(1,I_W_USERID);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_W_AUTGRP(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_W_AUTGRP(null);
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
    public static class PL_NFI2_results {
        String O_W_NEW;
        String O_W_EDIT;
        String O_W_ENQ;
        String O_W_DEL;
        public void setO_W_NEW(String O_W_NEW) {
            this.O_W_NEW = O_W_NEW;
        }

        public String getO_W_NEW() {
            return O_W_NEW;
        }

        public void setO_W_EDIT(String O_W_EDIT) {
            this.O_W_EDIT = O_W_EDIT;
        }

        public String getO_W_EDIT() {
            return O_W_EDIT;
        }

        public void setO_W_ENQ(String O_W_ENQ) {
            this.O_W_ENQ = O_W_ENQ;
        }

        public String getO_W_ENQ() {
            return O_W_ENQ;
        }

        public void setO_W_DEL(String O_W_DEL) {
            this.O_W_DEL = O_W_DEL;
        }

        public String getO_W_DEL() {
            return O_W_DEL;
        }

    }
    public static PL_NFI2_results PL_NFI2(DBTransaction dbt ,String I_W_AUTGRP,String I_W_MODULE)  {
        CallableStatement cs = null;
      PL_NFI2_results results=new PL_NFI2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_AUTGRP VARCHAR2(32000) :=?;\n" +
                        "  I_W_MODULE VARCHAR2(32000) :=?;\n" +
                        "  O_W_NEW VARCHAR2(32000);\n" +
                        "  O_W_EDIT VARCHAR2(32000);\n" +
                        "  O_W_ENQ VARCHAR2(32000);\n" +
                        "  O_W_DEL VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_NFI2(I_W_AUTGRP,I_W_MODULE,O_W_NEW,O_W_EDIT,O_W_ENQ,O_W_DEL);\n" +
                        "  ?     := O_W_NEW;\n" +
                        "  ?     := O_W_EDIT;\n" +
                        "  ?     := O_W_ENQ;\n" +
                        "  ?     := O_W_DEL;\n" +
                        "END;\n", 0);
            if (I_W_AUTGRP !=null) {
                cs.setString(1,I_W_AUTGRP);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_W_MODULE !=null) {
                cs.setString(2,I_W_MODULE);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.registerOutParameter(6,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_W_NEW(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_W_NEW(null);
            }
            results.setO_W_EDIT(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_W_EDIT(null);
            }
            results.setO_W_ENQ(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_W_ENQ(null);
            }
            results.setO_W_DEL(cs.getString(6));
            if (cs.wasNull()) {
                results.setO_W_DEL(null);
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
    public static class PL_NFI3_results {
        String O_W_PRT;
        public void setO_W_PRT(String O_W_PRT) {
            this.O_W_PRT = O_W_PRT;
        }

        public String getO_W_PRT() {
            return O_W_PRT;
        }

    }
    public static PL_NFI3_results PL_NFI3(DBTransaction dbt ,String I_G_USERID,String I_G_MODULE)  {
        CallableStatement cs = null;
      PL_NFI3_results results=new PL_NFI3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_G_USERID VARCHAR2(32000) :=?;\n" +
                        "  I_G_MODULE VARCHAR2(32000) :=?;\n" +
                        "  O_W_PRT VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_NFI3(I_G_USERID,I_G_MODULE,O_W_PRT);\n" +
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
    public static class PL_PRINTPRINT_PRINTOK_BP1_results {
        BigDecimal O_VCOUNT;
        public void setO_VCOUNT(BigDecimal O_VCOUNT) {
            this.O_VCOUNT = O_VCOUNT;
        }

        public BigDecimal getO_VCOUNT() {
            return O_VCOUNT;
        }

    }
    public static PL_PRINTPRINT_PRINTOK_BP1_results PL_PRINTPRINT_PRINTOK_BP1(DBTransaction dbt ,String I_W_DIV,String I_SIMS_INVNO_FROM,String I_SIMS_INVNO_TO,String I_SIMS_CLSDAT)  {
        CallableStatement cs = null;
      PL_PRINTPRINT_PRINTOK_BP1_results results=new PL_PRINTPRINT_PRINTOK_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_INVNO_FROM VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_INVNO_TO VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CLSDAT VARCHAR2(32000) :=?;\n" +
                        "  O_VCOUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_PRINTPRINT_PRINTOK_BP1(I_W_DIV,I_SIMS_INVNO_FROM,I_SIMS_INVNO_TO,I_SIMS_CLSDAT,O_VCOUNT);\n" +
                        "  ?     := O_VCOUNT;\n" +
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
            if (I_SIMS_INVNO_TO !=null) {
                cs.setString(3,I_SIMS_INVNO_TO);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (I_SIMS_CLSDAT !=null) {
                cs.setString(4,I_SIMS_CLSDAT);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            cs.registerOutParameter(5,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_VCOUNT(cs.getBigDecimal(5));
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
        String O_VLINFEED;
        public void setO_VLINFEED(String O_VLINFEED) {
            this.O_VLINFEED = O_VLINFEED;
        }

        public String getO_VLINFEED() {
            return O_VLINFEED;
        }

    }
    public static PL_PRINT_SAMINV1_results PL_PRINT_SAMINV1(DBTransaction dbt ,String I_W_DIV,String I_SIMS_CUSCOD)  {
        CallableStatement cs = null;
      PL_PRINT_SAMINV1_results results=new PL_PRINT_SAMINV1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  O_VLINFEED VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_PRINT_SAMINV1(I_W_DIV,I_SIMS_CUSCOD,O_VLINFEED);\n" +
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
    public static class PL_PRINT_SAMINV2_results {
        String O_VLINFEED;
        public void setO_VLINFEED(String O_VLINFEED) {
            this.O_VLINFEED = O_VLINFEED;
        }

        public String getO_VLINFEED() {
            return O_VLINFEED;
        }

    }
    public static PL_PRINT_SAMINV2_results PL_PRINT_SAMINV2(DBTransaction dbt ,String I_W_DIV)  {
        CallableStatement cs = null;
      PL_PRINT_SAMINV2_results results=new PL_PRINT_SAMINV2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  O_VLINFEED VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_PRINT_SAMINV2(I_W_DIV,O_VLINFEED);\n" +
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
    public static class PL_REMARKREMARK_REMARKOK_BP1_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP1_results PL_REMARKREMARK_REMARKOK_BP1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP1_results results=new PL_REMARKREMARK_REMARKOK_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP1(I_SIMS_SAMINVRUN,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIDS_DES(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_REMARKREMARK_REMARKOK_BP2_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP2_results PL_REMARKREMARK_REMARKOK_BP2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP2_results results=new PL_REMARKREMARK_REMARKOK_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP2(I_SIMS_SAMINVRUN,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIDS_DES(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_REMARKREMARK_REMARKOK_BP3_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP3_results PL_REMARKREMARK_REMARKOK_BP3(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP3_results results=new PL_REMARKREMARK_REMARKOK_BP3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP3(I_SIMS_SAMINVRUN,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIDS_DES(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_REMARKREMARK_REMARKOK_BP4_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP4_results PL_REMARKREMARK_REMARKOK_BP4(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP4_results results=new PL_REMARKREMARK_REMARKOK_BP4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP4(I_SIMS_SAMINVRUN,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIDS_DES(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_REMARKREMARK_REMARKOK_BP5_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP5_results PL_REMARKREMARK_REMARKOK_BP5(DBTransaction dbt ,String I_MPRM_RMKCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP5_results results=new PL_REMARKREMARK_REMARKOK_BP5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPRM_RMKCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP5(I_MPRM_RMKCOD,I_G_DIVCOD,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_MPRM_RMKCOD !=null) {
                cs.setString(1,I_MPRM_RMKCOD);
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
            results.setO_SIDS_DES(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_REMARKREMARK_REMARKOK_BP6_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP6_results PL_REMARKREMARK_REMARKOK_BP6(DBTransaction dbt ,String I_MPRM_RMKCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP6_results results=new PL_REMARKREMARK_REMARKOK_BP6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPRM_RMKCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP6(I_MPRM_RMKCOD,I_G_DIVCOD,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_MPRM_RMKCOD !=null) {
                cs.setString(1,I_MPRM_RMKCOD);
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
            results.setO_SIDS_DES(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_REMARKREMARK_REMARKOK_BP7_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP7_results PL_REMARKREMARK_REMARKOK_BP7(DBTransaction dbt ,String I_MPRM_RMKCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP7_results results=new PL_REMARKREMARK_REMARKOK_BP7_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPRM_RMKCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP7(I_MPRM_RMKCOD,I_G_DIVCOD,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_MPRM_RMKCOD !=null) {
                cs.setString(1,I_MPRM_RMKCOD);
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
            results.setO_SIDS_DES(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_REMARKREMARK_REMARKOK_BP8_results {
        String O_SIDS_DES;
        public void setO_SIDS_DES(String O_SIDS_DES) {
            this.O_SIDS_DES = O_SIDS_DES;
        }

        public String getO_SIDS_DES() {
            return O_SIDS_DES;
        }

    }
    public static PL_REMARKREMARK_REMARKOK_BP8_results PL_REMARKREMARK_REMARKOK_BP8(DBTransaction dbt ,String I_MPRM_RMKCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_REMARKREMARK_REMARKOK_BP8_results results=new PL_REMARKREMARK_REMARKOK_BP8_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_MPRM_RMKCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIDS_DES LONG;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_REMARKREMARK_REMARKOK_BP8(I_MPRM_RMKCOD,I_G_DIVCOD,O_SIDS_DES);\n" +
                        "  ?     := O_SIDS_DES;\n" +
                        "END;\n", 0);
            if (I_MPRM_RMKCOD !=null) {
                cs.setString(1,I_MPRM_RMKCOD);
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
            results.setO_SIDS_DES(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIDS_DES(null);
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
    public static class PL_RITERIA2_OVSIMSDIVCOD_BP1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_RITERIA2_OVSIMSDIVCOD_BP1_results PL_RITERIA2_OVSIMSDIVCOD_BP1(DBTransaction dbt ,String I_SIMS_DPTCOD,String I_ONE)  {
        CallableStatement cs = null;
      PL_RITERIA2_OVSIMSDIVCOD_BP1_results results=new PL_RITERIA2_OVSIMSDIVCOD_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  I_ONE VARCHAR2(32000) :=?;\n" +
                        "  O_I INTEGER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_RITERIA2_OVSIMSDIVCOD_BP1(I_SIMS_DPTCOD,I_ONE,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_DPTCOD !=null) {
                cs.setString(1,I_SIMS_DPTCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_ONE !=null) {
                cs.setString(2,I_ONE);
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
    public static class PL_SAMSAM_SAMOK_BP1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_SAMSAM_SAMOK_BP1_results PL_SAMSAM_SAMOK_BP1(DBTransaction dbt ,String I_SAM_DPTCOD,String I_SIMS_DIVCOD,String I_G_DIVCOD,String I_G_USERID)  {
        CallableStatement cs = null;
      PL_SAMSAM_SAMOK_BP1_results results=new PL_SAMSAM_SAMOK_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SAM_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_USERID VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_SAMSAM_SAMOK_BP1(I_SAM_DPTCOD,I_SIMS_DIVCOD,I_G_DIVCOD,I_G_USERID,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SAM_DPTCOD !=null) {
                cs.setString(1,I_SAM_DPTCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_DIVCOD !=null) {
                cs.setString(2,I_SIMS_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (I_G_USERID !=null) {
                cs.setString(4,I_G_USERID);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            cs.registerOutParameter(5,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(5));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_SAMSAM_SAMOK_BP2_results {
        String O_SAM_YEAR;
        public void setO_SAM_YEAR(String O_SAM_YEAR) {
            this.O_SAM_YEAR = O_SAM_YEAR;
        }

        public String getO_SAM_YEAR() {
            return O_SAM_YEAR;
        }

    }
    public static PL_SAMSAM_SAMOK_BP2_results PL_SAMSAM_SAMOK_BP2(DBTransaction dbt ,String I_SAM_YEAR)  {
        CallableStatement cs = null;
      PL_SAMSAM_SAMOK_BP2_results results=new PL_SAMSAM_SAMOK_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SAM_YEAR VARCHAR2(32000) :=?;\n" +
                        "  O_SAM_YEAR VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_SAMSAM_SAMOK_BP2(I_SAM_YEAR,O_SAM_YEAR);\n" +
                        "  ?     := O_SAM_YEAR;\n" +
                        "END;\n", 0);
            if (I_SAM_YEAR !=null) {
                cs.setString(1,I_SAM_YEAR);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SAM_YEAR(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SAM_YEAR(null);
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
    public static class PL_SET_CUST_DEFAULT1_results {
        String O_CNDSALCOD;
        String O_CNDSALDES;
        String O_PAYTRMCOD;
        String O_PAYTRMDES;
        String O_SIMS_NAM;
        String O_SIMS_CARCOD;
        String O_SIMS_ADRCOD;
        String O_SIMS_ADR1;
        String O_SIMS_ADR2;
        String O_SIMS_ADR3;
        String O_SIMS_ADR4;
        String O_V_CPMS_INVDEFCSG;
        public void setO_CNDSALCOD(String O_CNDSALCOD) {
            this.O_CNDSALCOD = O_CNDSALCOD;
        }

        public String getO_CNDSALCOD() {
            return O_CNDSALCOD;
        }

        public void setO_CNDSALDES(String O_CNDSALDES) {
            this.O_CNDSALDES = O_CNDSALDES;
        }

        public String getO_CNDSALDES() {
            return O_CNDSALDES;
        }

        public void setO_PAYTRMCOD(String O_PAYTRMCOD) {
            this.O_PAYTRMCOD = O_PAYTRMCOD;
        }

        public String getO_PAYTRMCOD() {
            return O_PAYTRMCOD;
        }

        public void setO_PAYTRMDES(String O_PAYTRMDES) {
            this.O_PAYTRMDES = O_PAYTRMDES;
        }

        public String getO_PAYTRMDES() {
            return O_PAYTRMDES;
        }

        public void setO_SIMS_NAM(String O_SIMS_NAM) {
            this.O_SIMS_NAM = O_SIMS_NAM;
        }

        public String getO_SIMS_NAM() {
            return O_SIMS_NAM;
        }

        public void setO_SIMS_CARCOD(String O_SIMS_CARCOD) {
            this.O_SIMS_CARCOD = O_SIMS_CARCOD;
        }

        public String getO_SIMS_CARCOD() {
            return O_SIMS_CARCOD;
        }

        public void setO_SIMS_ADRCOD(String O_SIMS_ADRCOD) {
            this.O_SIMS_ADRCOD = O_SIMS_ADRCOD;
        }

        public String getO_SIMS_ADRCOD() {
            return O_SIMS_ADRCOD;
        }

        public void setO_SIMS_ADR1(String O_SIMS_ADR1) {
            this.O_SIMS_ADR1 = O_SIMS_ADR1;
        }

        public String getO_SIMS_ADR1() {
            return O_SIMS_ADR1;
        }

        public void setO_SIMS_ADR2(String O_SIMS_ADR2) {
            this.O_SIMS_ADR2 = O_SIMS_ADR2;
        }

        public String getO_SIMS_ADR2() {
            return O_SIMS_ADR2;
        }

        public void setO_SIMS_ADR3(String O_SIMS_ADR3) {
            this.O_SIMS_ADR3 = O_SIMS_ADR3;
        }

        public String getO_SIMS_ADR3() {
            return O_SIMS_ADR3;
        }

        public void setO_SIMS_ADR4(String O_SIMS_ADR4) {
            this.O_SIMS_ADR4 = O_SIMS_ADR4;
        }

        public String getO_SIMS_ADR4() {
            return O_SIMS_ADR4;
        }

        public void setO_V_CPMS_INVDEFCSG(String O_V_CPMS_INVDEFCSG) {
            this.O_V_CPMS_INVDEFCSG = O_V_CPMS_INVDEFCSG;
        }

        public String getO_V_CPMS_INVDEFCSG() {
            return O_V_CPMS_INVDEFCSG;
        }

    }
    public static PL_SET_CUST_DEFAULT1_results PL_SET_CUST_DEFAULT1(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_SET_CUST_DEFAULT1_results results=new PL_SET_CUST_DEFAULT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_CNDSALCOD VARCHAR2(32000);\n" +
                        "  O_CNDSALDES VARCHAR2(32000);\n" +
                        "  O_PAYTRMCOD VARCHAR2(32000);\n" +
                        "  O_PAYTRMDES VARCHAR2(32000);\n" +
                        "  O_SIMS_NAM VARCHAR2(32000);\n" +
                        "  O_SIMS_CARCOD VARCHAR2(32000);\n" +
                        "  O_SIMS_ADRCOD VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR1 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR2 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR3 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR4 VARCHAR2(32000);\n" +
                        "  O_V_CPMS_INVDEFCSG VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_SET_CUST_DEFAULT1(I_SIMS_CUSCOD,I_G_DIVCOD,O_CNDSALCOD,O_CNDSALDES,O_PAYTRMCOD,O_PAYTRMDES,O_SIMS_NAM,O_SIMS_CARCOD,O_SIMS_ADRCOD,O_SIMS_ADR1,O_SIMS_ADR2,O_SIMS_ADR3,O_SIMS_ADR4,O_V_CPMS_INVDEFCSG);\n" +
                        "  ?     := O_CNDSALCOD;\n" +
                        "  ?     := O_CNDSALDES;\n" +
                        "  ?     := O_PAYTRMCOD;\n" +
                        "  ?     := O_PAYTRMDES;\n" +
                        "  ?     := O_SIMS_NAM;\n" +
                        "  ?     := O_SIMS_CARCOD;\n" +
                        "  ?     := O_SIMS_ADRCOD;\n" +
                        "  ?     := O_SIMS_ADR1;\n" +
                        "  ?     := O_SIMS_ADR2;\n" +
                        "  ?     := O_SIMS_ADR3;\n" +
                        "  ?     := O_SIMS_ADR4;\n" +
                        "  ?     := O_V_CPMS_INVDEFCSG;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
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
            cs.registerOutParameter(13,Types.VARCHAR);
            cs.registerOutParameter(14,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_CNDSALCOD(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_CNDSALCOD(null);
            }
            results.setO_CNDSALDES(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_CNDSALDES(null);
            }
            results.setO_PAYTRMCOD(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_PAYTRMCOD(null);
            }
            results.setO_PAYTRMDES(cs.getString(6));
            if (cs.wasNull()) {
                results.setO_PAYTRMDES(null);
            }
            results.setO_SIMS_NAM(cs.getString(7));
            if (cs.wasNull()) {
                results.setO_SIMS_NAM(null);
            }
            results.setO_SIMS_CARCOD(cs.getString(8));
            if (cs.wasNull()) {
                results.setO_SIMS_CARCOD(null);
            }
            results.setO_SIMS_ADRCOD(cs.getString(9));
            if (cs.wasNull()) {
                results.setO_SIMS_ADRCOD(null);
            }
            results.setO_SIMS_ADR1(cs.getString(10));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR1(null);
            }
            results.setO_SIMS_ADR2(cs.getString(11));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR2(null);
            }
            results.setO_SIMS_ADR3(cs.getString(12));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR3(null);
            }
            results.setO_SIMS_ADR4(cs.getString(13));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR4(null);
            }
            results.setO_V_CPMS_INVDEFCSG(cs.getString(14));
            if (cs.wasNull()) {
                results.setO_V_CPMS_INVDEFCSG(null);
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
    public static class PL_SET_CUST_DEFAULT2_results {
        String O_SIMS_NAM;
        String O_SIMS_ADR1;
        String O_SIMS_ADR2;
        String O_SIMS_ADR3;
        String O_SIMS_ADR4;
        public void setO_SIMS_NAM(String O_SIMS_NAM) {
            this.O_SIMS_NAM = O_SIMS_NAM;
        }

        public String getO_SIMS_NAM() {
            return O_SIMS_NAM;
        }

        public void setO_SIMS_ADR1(String O_SIMS_ADR1) {
            this.O_SIMS_ADR1 = O_SIMS_ADR1;
        }

        public String getO_SIMS_ADR1() {
            return O_SIMS_ADR1;
        }

        public void setO_SIMS_ADR2(String O_SIMS_ADR2) {
            this.O_SIMS_ADR2 = O_SIMS_ADR2;
        }

        public String getO_SIMS_ADR2() {
            return O_SIMS_ADR2;
        }

        public void setO_SIMS_ADR3(String O_SIMS_ADR3) {
            this.O_SIMS_ADR3 = O_SIMS_ADR3;
        }

        public String getO_SIMS_ADR3() {
            return O_SIMS_ADR3;
        }

        public void setO_SIMS_ADR4(String O_SIMS_ADR4) {
            this.O_SIMS_ADR4 = O_SIMS_ADR4;
        }

        public String getO_SIMS_ADR4() {
            return O_SIMS_ADR4;
        }

    }
    public static PL_SET_CUST_DEFAULT2_results PL_SET_CUST_DEFAULT2(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_V_CPMS_INVDEFCSG,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_SET_CUST_DEFAULT2_results results=new PL_SET_CUST_DEFAULT2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_V_CPMS_INVDEFCSG VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIMS_NAM VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR1 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR2 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR3 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR4 VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_SET_CUST_DEFAULT2(I_SIMS_CUSCOD,I_V_CPMS_INVDEFCSG,I_G_DIVCOD,O_SIMS_NAM,O_SIMS_ADR1,O_SIMS_ADR2,O_SIMS_ADR3,O_SIMS_ADR4);\n" +
                        "  ?     := O_SIMS_NAM;\n" +
                        "  ?     := O_SIMS_ADR1;\n" +
                        "  ?     := O_SIMS_ADR2;\n" +
                        "  ?     := O_SIMS_ADR3;\n" +
                        "  ?     := O_SIMS_ADR4;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_V_CPMS_INVDEFCSG !=null) {
                cs.setString(2,I_V_CPMS_INVDEFCSG);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.registerOutParameter(6,Types.VARCHAR);
            cs.registerOutParameter(7,Types.VARCHAR);
            cs.registerOutParameter(8,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_NAM(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIMS_NAM(null);
            }
            results.setO_SIMS_ADR1(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR1(null);
            }
            results.setO_SIMS_ADR2(cs.getString(6));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR2(null);
            }
            results.setO_SIMS_ADR3(cs.getString(7));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR3(null);
            }
            results.setO_SIMS_ADR4(cs.getString(8));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR4(null);
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
    public static class PL_SET_CUST_DEFAULT3_results {
        String O_MPCA_CARNAM;
        public void setO_MPCA_CARNAM(String O_MPCA_CARNAM) {
            this.O_MPCA_CARNAM = O_MPCA_CARNAM;
        }

        public String getO_MPCA_CARNAM() {
            return O_MPCA_CARNAM;
        }

    }
    public static PL_SET_CUST_DEFAULT3_results PL_SET_CUST_DEFAULT3(DBTransaction dbt ,String I_SIMS_CARCOD)  {
        CallableStatement cs = null;
      PL_SET_CUST_DEFAULT3_results results=new PL_SET_CUST_DEFAULT3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CARCOD VARCHAR2(32000) :=?;\n" +
                        "  O_MPCA_CARNAM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_SET_CUST_DEFAULT3(I_SIMS_CARCOD,O_MPCA_CARNAM);\n" +
                        "  ?     := O_MPCA_CARNAM;\n" +
                        "END;\n", 0);
            if (I_SIMS_CARCOD !=null) {
                cs.setString(1,I_SIMS_CARCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPCA_CARNAM(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPCA_CARNAM(null);
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
    public static class PL_SHOW_INVNO1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_SHOW_INVNO1_results PL_SHOW_INVNO1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_SHOW_INVNO1_results results=new PL_SHOW_INVNO1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_I INTEGER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_SHOW_INVNO1(I_SIMS_SAMINVRUN,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_I(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_SHOW_INVNO2_results {
        String O_SIMS_INVNO;
        public void setO_SIMS_INVNO(String O_SIMS_INVNO) {
            this.O_SIMS_INVNO = O_SIMS_INVNO;
        }

        public String getO_SIMS_INVNO() {
            return O_SIMS_INVNO;
        }

    }
    public static PL_SHOW_INVNO2_results PL_SHOW_INVNO2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_SHOW_INVNO2_results results=new PL_SHOW_INVNO2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SIMS_INVNO VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_SHOW_INVNO2(I_SIMS_SAMINVRUN,O_SIMS_INVNO);\n" +
                        "  ?     := O_SIMS_INVNO;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_INVNO(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIMS_INVNO(null);
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
    public static class PL_TSIITM5_CASENO_POT1_results {
        BigDecimal O_SIIT_PR_RUNNUM;
        public void setO_SIIT_PR_RUNNUM(BigDecimal O_SIIT_PR_RUNNUM) {
            this.O_SIIT_PR_RUNNUM = O_SIIT_PR_RUNNUM;
        }

        public BigDecimal getO_SIIT_PR_RUNNUM() {
            return O_SIIT_PR_RUNNUM;
        }

    }
    public static PL_TSIITM5_CASENO_POT1_results PL_TSIITM5_CASENO_POT1(DBTransaction dbt ,String I_CASE_NO,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_CASENO_POT1_results results=new PL_TSIITM5_CASENO_POT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_CASE_NO VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_PR_RUNNUM NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_CASENO_POT1(I_CASE_NO,I_G_DIVCOD,O_SIIT_PR_RUNNUM);\n" +
                        "  ?     := O_SIIT_PR_RUNNUM;\n" +
                        "END;\n", 0);
            if (I_CASE_NO !=null) {
                cs.setString(1,I_CASE_NO);
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
            results.setO_SIIT_PR_RUNNUM(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_SIIT_PR_RUNNUM(null);
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
    public static class PL_TSIITM5_LOVSIITSUPCOD_BP1_results {
        String O_SIIT_SUPITM;
        public void setO_SIIT_SUPITM(String O_SIIT_SUPITM) {
            this.O_SIIT_SUPITM = O_SIIT_SUPITM;
        }

        public String getO_SIIT_SUPITM() {
            return O_SIIT_SUPITM;
        }

    }
    public static PL_TSIITM5_LOVSIITSUPCOD_BP1_results PL_TSIITM5_LOVSIITSUPCOD_BP1(DBTransaction dbt ,String I_SIIT_ITMNUM,String I_SIIT_SUPCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_LOVSIITSUPCOD_BP1_results results=new PL_TSIITM5_LOVSIITSUPCOD_BP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_ITMNUM VARCHAR2(32000) :=?;\n" +
                        "  I_SIIT_SUPCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_SUPITM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_LOVSIITSUPCOD_BP1(I_SIIT_ITMNUM,I_SIIT_SUPCOD,I_G_DIVCOD,O_SIIT_SUPITM);\n" +
                        "  ?     := O_SIIT_SUPITM;\n" +
                        "END;\n", 0);
            if (I_SIIT_ITMNUM !=null) {
                cs.setString(1,I_SIIT_ITMNUM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIIT_SUPCOD !=null) {
                cs.setString(2,I_SIIT_SUPCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIIT_SUPITM(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPITM(null);
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
    public static class PL_TSIITM5_LOVSIITSUPCOD_BP2_results {
        String O_SIIT_SUPITM;
        public void setO_SIIT_SUPITM(String O_SIIT_SUPITM) {
            this.O_SIIT_SUPITM = O_SIIT_SUPITM;
        }

        public String getO_SIIT_SUPITM() {
            return O_SIIT_SUPITM;
        }

    }
    public static PL_TSIITM5_LOVSIITSUPCOD_BP2_results PL_TSIITM5_LOVSIITSUPCOD_BP2(DBTransaction dbt ,String I_SIIT_ITMNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_LOVSIITSUPCOD_BP2_results results=new PL_TSIITM5_LOVSIITSUPCOD_BP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_ITMNUM VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_SUPITM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_LOVSIITSUPCOD_BP2(I_SIIT_ITMNUM,I_G_DIVCOD,O_SIIT_SUPITM);\n" +
                        "  ?     := O_SIIT_SUPITM;\n" +
                        "END;\n", 0);
            if (I_SIIT_ITMNUM !=null) {
                cs.setString(1,I_SIIT_ITMNUM);
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
            results.setO_SIIT_SUPITM(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPITM(null);
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
    public static class PL_TSIITM5_POQ1_results {
        String O_CASE_NO;
        public void setO_CASE_NO(String O_CASE_NO) {
            this.O_CASE_NO = O_CASE_NO;
        }

        public String getO_CASE_NO() {
            return O_CASE_NO;
        }

    }
    public static PL_TSIITM5_POQ1_results PL_TSIITM5_POQ1(DBTransaction dbt ,BigDecimal I_SIIT_PR_RUNNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_POQ1_results results=new PL_TSIITM5_POQ1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_PR_RUNNUM NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_CASE_NO VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_POQ1(I_SIIT_PR_RUNNUM,I_G_DIVCOD,O_CASE_NO);\n" +
                        "  ?     := O_CASE_NO;\n" +
                        "END;\n", 0);
            if (I_SIIT_PR_RUNNUM !=null) {
                cs.setBigDecimal(1,I_SIIT_PR_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_CASE_NO(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_CASE_NO(null);
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
    public static class PL_TSIITM5_PRI1_results {
        BigDecimal O_SIIT_ITMSEQ;
        public void setO_SIIT_ITMSEQ(BigDecimal O_SIIT_ITMSEQ) {
            this.O_SIIT_ITMSEQ = O_SIIT_ITMSEQ;
        }

        public BigDecimal getO_SIIT_ITMSEQ() {
            return O_SIIT_ITMSEQ;
        }

    }
    public static PL_TSIITM5_PRI1_results PL_TSIITM5_PRI1(DBTransaction dbt ,String I_SIIT_DIVCOD,BigDecimal I_SIIT_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRI1_results results=new PL_TSIITM5_PRI1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIIT_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SIIT_ITMSEQ NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRI1(I_SIIT_DIVCOD,I_SIIT_SAMINVRUN,O_SIIT_ITMSEQ);\n" +
                        "  ?     := O_SIIT_ITMSEQ;\n" +
                        "END;\n", 0);
            if (I_SIIT_DIVCOD !=null) {
                cs.setString(1,I_SIIT_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIIT_SAMINVRUN !=null) {
                cs.setBigDecimal(2,I_SIIT_SAMINVRUN);
            } else {
                cs.setNull(2, Types.NUMERIC);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SIIT_ITMSEQ(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_SIIT_ITMSEQ(null);
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
    public static class PL_TSIITM5_PRI2_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIITM5_PRI2_results PL_TSIITM5_PRI2(DBTransaction dbt ,String I_SIIT_TYP)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRI2_results results=new PL_TSIITM5_PRI2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_TYP VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRI2(I_SIIT_TYP,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_TYP !=null) {
                cs.setString(1,I_SIIT_TYP);
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
    public static class PL_TSIITM5_PRI3_results {
        String O_STSCOD;
        Date O_CLSDAT;
        String O_ANNAPPROV;
        public void setO_STSCOD(String O_STSCOD) {
            this.O_STSCOD = O_STSCOD;
        }

        public String getO_STSCOD() {
            return O_STSCOD;
        }

        public void setO_CLSDAT(Date O_CLSDAT) {
            this.O_CLSDAT = O_CLSDAT;
        }

        public Date getO_CLSDAT() {
            return O_CLSDAT;
        }

        public void setO_ANNAPPROV(String O_ANNAPPROV) {
            this.O_ANNAPPROV = O_ANNAPPROV;
        }

        public String getO_ANNAPPROV() {
            return O_ANNAPPROV;
        }

    }
    public static PL_TSIITM5_PRI3_results PL_TSIITM5_PRI3(DBTransaction dbt ,String I_SIIT_SUPCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRI3_results results=new PL_TSIITM5_PRI3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_SUPCOD VARCHAR2(32000) :=?;\n" +
                        "  O_STSCOD VARCHAR2(32000);\n" +
                        "  O_CLSDAT DATE;\n" +
                        "  O_ANNAPPROV VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRI3(I_SIIT_SUPCOD,O_STSCOD,O_CLSDAT,O_ANNAPPROV);\n" +
                        "  ?     := O_STSCOD;\n" +
                        "  ?     := O_CLSDAT;\n" +
                        "  ?     := O_ANNAPPROV;\n" +
                        "END;\n", 0);
            if (I_SIIT_SUPCOD !=null) {
                cs.setString(1,I_SIIT_SUPCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.registerOutParameter(3,Types.DATE);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_STSCOD(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_STSCOD(null);
            }
            results.setO_CLSDAT(cs.getDate(3));
            if (cs.wasNull()) {
                results.setO_CLSDAT(null);
            }
            results.setO_ANNAPPROV(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_ANNAPPROV(null);
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
    public static class PL_TSIITM5_PRI4_results {
        String O_STSMSG;
        public void setO_STSMSG(String O_STSMSG) {
            this.O_STSMSG = O_STSMSG;
        }

        public String getO_STSMSG() {
            return O_STSMSG;
        }

    }
    public static PL_TSIITM5_PRI4_results PL_TSIITM5_PRI4(DBTransaction dbt ,String I_STSCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRI4_results results=new PL_TSIITM5_PRI4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_STSCOD VARCHAR2(32000) :=?;\n" +
                        "  O_STSMSG VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRI4(I_STSCOD,O_STSMSG);\n" +
                        "  ?     := O_STSMSG;\n" +
                        "END;\n", 0);
            if (I_STSCOD !=null) {
                cs.setString(1,I_STSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_STSMSG(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_STSMSG(null);
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
    public static class PL_TSIITM5_PRI5_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIITM5_PRI5_results PL_TSIITM5_PRI5(DBTransaction dbt ,String I_SIIT_HARMONCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRI5_results results=new PL_TSIITM5_PRI5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_HARMONCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRI5(I_SIIT_HARMONCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_HARMONCOD !=null) {
                cs.setString(1,I_SIIT_HARMONCOD);
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
    public static class PL_TSIITM5_PRI6_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIITM5_PRI6_results PL_TSIITM5_PRI6(DBTransaction dbt ,String I_SIIT_UOM)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRI6_results results=new PL_TSIITM5_PRI6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_UOM VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRI6(I_SIIT_UOM,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_UOM !=null) {
                cs.setString(1,I_SIIT_UOM);
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
    public static class PL_TSIITM5_PRU1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIITM5_PRU1_results PL_TSIITM5_PRU1(DBTransaction dbt ,String I_SIIT_TYP)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRU1_results results=new PL_TSIITM5_PRU1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_TYP VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRU1(I_SIIT_TYP,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_TYP !=null) {
                cs.setString(1,I_SIIT_TYP);
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
    public static class PL_TSIITM5_PRU2_results {
        String O_STSCOD;
        Date O_CLSDAT;
        String O_ANNAPPROV;
        public void setO_STSCOD(String O_STSCOD) {
            this.O_STSCOD = O_STSCOD;
        }

        public String getO_STSCOD() {
            return O_STSCOD;
        }

        public void setO_CLSDAT(Date O_CLSDAT) {
            this.O_CLSDAT = O_CLSDAT;
        }

        public Date getO_CLSDAT() {
            return O_CLSDAT;
        }

        public void setO_ANNAPPROV(String O_ANNAPPROV) {
            this.O_ANNAPPROV = O_ANNAPPROV;
        }

        public String getO_ANNAPPROV() {
            return O_ANNAPPROV;
        }

    }
    public static PL_TSIITM5_PRU2_results PL_TSIITM5_PRU2(DBTransaction dbt ,String I_SIIT_SUPCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRU2_results results=new PL_TSIITM5_PRU2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_SUPCOD VARCHAR2(32000) :=?;\n" +
                        "  O_STSCOD VARCHAR2(32000);\n" +
                        "  O_CLSDAT DATE;\n" +
                        "  O_ANNAPPROV VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRU2(I_SIIT_SUPCOD,O_STSCOD,O_CLSDAT,O_ANNAPPROV);\n" +
                        "  ?     := O_STSCOD;\n" +
                        "  ?     := O_CLSDAT;\n" +
                        "  ?     := O_ANNAPPROV;\n" +
                        "END;\n", 0);
            if (I_SIIT_SUPCOD !=null) {
                cs.setString(1,I_SIIT_SUPCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.registerOutParameter(3,Types.DATE);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_STSCOD(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_STSCOD(null);
            }
            results.setO_CLSDAT(cs.getDate(3));
            if (cs.wasNull()) {
                results.setO_CLSDAT(null);
            }
            results.setO_ANNAPPROV(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_ANNAPPROV(null);
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
    public static class PL_TSIITM5_PRU3_results {
        String O_STSMSG;
        public void setO_STSMSG(String O_STSMSG) {
            this.O_STSMSG = O_STSMSG;
        }

        public String getO_STSMSG() {
            return O_STSMSG;
        }

    }
    public static PL_TSIITM5_PRU3_results PL_TSIITM5_PRU3(DBTransaction dbt ,String I_STSCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRU3_results results=new PL_TSIITM5_PRU3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_STSCOD VARCHAR2(32000) :=?;\n" +
                        "  O_STSMSG VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRU3(I_STSCOD,O_STSMSG);\n" +
                        "  ?     := O_STSMSG;\n" +
                        "END;\n", 0);
            if (I_STSCOD !=null) {
                cs.setString(1,I_STSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_STSMSG(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_STSMSG(null);
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
    public static class PL_TSIITM5_PRU4_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIITM5_PRU4_results PL_TSIITM5_PRU4(DBTransaction dbt ,String I_SIIT_HARMONCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRU4_results results=new PL_TSIITM5_PRU4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_HARMONCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRU4(I_SIIT_HARMONCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_HARMONCOD !=null) {
                cs.setString(1,I_SIIT_HARMONCOD);
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
    public static class PL_TSIITM5_PRU5_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIITM5_PRU5_results PL_TSIITM5_PRU5(DBTransaction dbt ,String I_SIIT_UOM)  {
        CallableStatement cs = null;
      PL_TSIITM5_PRU5_results results=new PL_TSIITM5_PRU5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_UOM VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_PRU5(I_SIIT_UOM,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIIT_UOM !=null) {
                cs.setString(1,I_SIIT_UOM);
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
    public static class PL_TSIITM5_SIITITMNUM_PC1_results {
        BigDecimal O_V_IPMS_RUNNUM;
        String O_SIIT_HARMONCOD;
        String O_SIIT_UOM;
        public void setO_V_IPMS_RUNNUM(BigDecimal O_V_IPMS_RUNNUM) {
            this.O_V_IPMS_RUNNUM = O_V_IPMS_RUNNUM;
        }

        public BigDecimal getO_V_IPMS_RUNNUM() {
            return O_V_IPMS_RUNNUM;
        }

        public void setO_SIIT_HARMONCOD(String O_SIIT_HARMONCOD) {
            this.O_SIIT_HARMONCOD = O_SIIT_HARMONCOD;
        }

        public String getO_SIIT_HARMONCOD() {
            return O_SIIT_HARMONCOD;
        }

        public void setO_SIIT_UOM(String O_SIIT_UOM) {
            this.O_SIIT_UOM = O_SIIT_UOM;
        }

        public String getO_SIIT_UOM() {
            return O_SIIT_UOM;
        }

    }
    public static PL_TSIITM5_SIITITMNUM_PC1_results PL_TSIITM5_SIITITMNUM_PC1(DBTransaction dbt ,String I_SIIT_ITMNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITITMNUM_PC1_results results=new PL_TSIITM5_SIITITMNUM_PC1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_ITMNUM VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_V_IPMS_RUNNUM NUMBER;\n" +
                        "  O_SIIT_HARMONCOD VARCHAR2(32000);\n" +
                        "  O_SIIT_UOM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC1(I_SIIT_ITMNUM,I_G_DIVCOD,O_V_IPMS_RUNNUM,O_SIIT_HARMONCOD,O_SIIT_UOM);\n" +
                        "  ?     := O_V_IPMS_RUNNUM;\n" +
                        "  ?     := O_SIIT_HARMONCOD;\n" +
                        "  ?     := O_SIIT_UOM;\n" +
                        "END;\n", 0);
            if (I_SIIT_ITMNUM !=null) {
                cs.setString(1,I_SIIT_ITMNUM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_V_IPMS_RUNNUM(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_V_IPMS_RUNNUM(null);
            }
            results.setO_SIIT_HARMONCOD(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIIT_HARMONCOD(null);
            }
            results.setO_SIIT_UOM(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_SIIT_UOM(null);
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
    public static class PL_TSIITM5_SIITITMNUM_PC2_results {
        String O_SIIT_CUSITM;
        BigDecimal O_SIIT_ITMPRC;
        public void setO_SIIT_CUSITM(String O_SIIT_CUSITM) {
            this.O_SIIT_CUSITM = O_SIIT_CUSITM;
        }

        public String getO_SIIT_CUSITM() {
            return O_SIIT_CUSITM;
        }

        public void setO_SIIT_ITMPRC(BigDecimal O_SIIT_ITMPRC) {
            this.O_SIIT_ITMPRC = O_SIIT_ITMPRC;
        }

        public BigDecimal getO_SIIT_ITMPRC() {
            return O_SIIT_ITMPRC;
        }

    }
    public static PL_TSIITM5_SIITITMNUM_PC2_results PL_TSIITM5_SIITITMNUM_PC2(DBTransaction dbt ,String I_SIMS_CURCOD,BigDecimal I_V_IPMS_RUNNUM,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITITMNUM_PC2_results results=new PL_TSIITM5_SIITITMNUM_PC2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CURCOD VARCHAR2(32000) :=?;\n" +
                        "  I_V_IPMS_RUNNUM NUMBER :=?;\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_CUSITM VARCHAR2(32000);\n" +
                        "  O_SIIT_ITMPRC NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC2(I_SIMS_CURCOD,I_V_IPMS_RUNNUM,I_SIMS_CUSCOD,I_G_DIVCOD,O_SIIT_CUSITM,O_SIIT_ITMPRC);\n" +
                        "  ?     := O_SIIT_CUSITM;\n" +
                        "  ?     := O_SIIT_ITMPRC;\n" +
                        "END;\n", 0);
            if (I_SIMS_CURCOD !=null) {
                cs.setString(1,I_SIMS_CURCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_V_IPMS_RUNNUM !=null) {
                cs.setBigDecimal(2,I_V_IPMS_RUNNUM);
            } else {
                cs.setNull(2, Types.NUMERIC);
            }
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(3,I_SIMS_CUSCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(4,I_G_DIVCOD);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.registerOutParameter(6,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SIIT_CUSITM(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_SIIT_CUSITM(null);
            }
            results.setO_SIIT_ITMPRC(cs.getBigDecimal(6));
            if (cs.wasNull()) {
                results.setO_SIIT_ITMPRC(null);
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
    public static class PL_TSIITM5_SIITITMNUM_PC3_results {
        String O_SIIT_CUSITM;
        BigDecimal O_SIIT_ITMPRC;
        public void setO_SIIT_CUSITM(String O_SIIT_CUSITM) {
            this.O_SIIT_CUSITM = O_SIIT_CUSITM;
        }

        public String getO_SIIT_CUSITM() {
            return O_SIIT_CUSITM;
        }

        public void setO_SIIT_ITMPRC(BigDecimal O_SIIT_ITMPRC) {
            this.O_SIIT_ITMPRC = O_SIIT_ITMPRC;
        }

        public BigDecimal getO_SIIT_ITMPRC() {
            return O_SIIT_ITMPRC;
        }

    }
    public static PL_TSIITM5_SIITITMNUM_PC3_results PL_TSIITM5_SIITITMNUM_PC3(DBTransaction dbt ,String I_SIMS_CURCOD,BigDecimal I_V_IPMS_RUNNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITITMNUM_PC3_results results=new PL_TSIITM5_SIITITMNUM_PC3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CURCOD VARCHAR2(32000) :=?;\n" +
                        "  I_V_IPMS_RUNNUM NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_CUSITM VARCHAR2(32000);\n" +
                        "  O_SIIT_ITMPRC NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC3(I_SIMS_CURCOD,I_V_IPMS_RUNNUM,I_G_DIVCOD,O_SIIT_CUSITM,O_SIIT_ITMPRC);\n" +
                        "  ?     := O_SIIT_CUSITM;\n" +
                        "  ?     := O_SIIT_ITMPRC;\n" +
                        "END;\n", 0);
            if (I_SIMS_CURCOD !=null) {
                cs.setString(1,I_SIMS_CURCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_V_IPMS_RUNNUM !=null) {
                cs.setBigDecimal(2,I_V_IPMS_RUNNUM);
            } else {
                cs.setNull(2, Types.NUMERIC);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SIIT_CUSITM(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIIT_CUSITM(null);
            }
            results.setO_SIIT_ITMPRC(cs.getBigDecimal(5));
            if (cs.wasNull()) {
                results.setO_SIIT_ITMPRC(null);
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
    public static class PL_TSIITM5_SIITITMNUM_PC4_results {
        String O_SIIT_SUPCOD;
        String O_SIIT_SUPITM;
        public void setO_SIIT_SUPCOD(String O_SIIT_SUPCOD) {
            this.O_SIIT_SUPCOD = O_SIIT_SUPCOD;
        }

        public String getO_SIIT_SUPCOD() {
            return O_SIIT_SUPCOD;
        }

        public void setO_SIIT_SUPITM(String O_SIIT_SUPITM) {
            this.O_SIIT_SUPITM = O_SIIT_SUPITM;
        }

        public String getO_SIIT_SUPITM() {
            return O_SIIT_SUPITM;
        }

    }
    public static PL_TSIITM5_SIITITMNUM_PC4_results PL_TSIITM5_SIITITMNUM_PC4(DBTransaction dbt ,BigDecimal I_V_IPMS_RUNNUM,String I_SIIT_SUPCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITITMNUM_PC4_results results=new PL_TSIITM5_SIITITMNUM_PC4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_V_IPMS_RUNNUM NUMBER :=?;\n" +
                        "  I_SIIT_SUPCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_SUPCOD VARCHAR2(32000);\n" +
                        "  O_SIIT_SUPITM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC4(I_V_IPMS_RUNNUM,I_SIIT_SUPCOD,I_G_DIVCOD,O_SIIT_SUPCOD,O_SIIT_SUPITM);\n" +
                        "  ?     := O_SIIT_SUPCOD;\n" +
                        "  ?     := O_SIIT_SUPITM;\n" +
                        "END;\n", 0);
            if (I_V_IPMS_RUNNUM !=null) {
                cs.setBigDecimal(1,I_V_IPMS_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_SIIT_SUPCOD !=null) {
                cs.setString(2,I_SIIT_SUPCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIIT_SUPCOD(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPCOD(null);
            }
            results.setO_SIIT_SUPITM(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPITM(null);
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
    public static class PL_TSIITM5_SIITITMNUM_PC5_results {
        String O_SIIT_SUPCOD;
        String O_SIIT_SUPITM;
        public void setO_SIIT_SUPCOD(String O_SIIT_SUPCOD) {
            this.O_SIIT_SUPCOD = O_SIIT_SUPCOD;
        }

        public String getO_SIIT_SUPCOD() {
            return O_SIIT_SUPCOD;
        }

        public void setO_SIIT_SUPITM(String O_SIIT_SUPITM) {
            this.O_SIIT_SUPITM = O_SIIT_SUPITM;
        }

        public String getO_SIIT_SUPITM() {
            return O_SIIT_SUPITM;
        }

    }
    public static PL_TSIITM5_SIITITMNUM_PC5_results PL_TSIITM5_SIITITMNUM_PC5(DBTransaction dbt ,BigDecimal I_V_IPMS_RUNNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITITMNUM_PC5_results results=new PL_TSIITM5_SIITITMNUM_PC5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_V_IPMS_RUNNUM NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_SUPCOD VARCHAR2(32000);\n" +
                        "  O_SIIT_SUPITM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC5(I_V_IPMS_RUNNUM,I_G_DIVCOD,O_SIIT_SUPCOD,O_SIIT_SUPITM);\n" +
                        "  ?     := O_SIIT_SUPCOD;\n" +
                        "  ?     := O_SIIT_SUPITM;\n" +
                        "END;\n", 0);
            if (I_V_IPMS_RUNNUM !=null) {
                cs.setBigDecimal(1,I_V_IPMS_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIIT_SUPCOD(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPCOD(null);
            }
            results.setO_SIIT_SUPITM(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPITM(null);
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
    public static class PL_TSIITM5_SIITITMNUM_PC6_results {
        String O_SIIT_ITMDES;
        public void setO_SIIT_ITMDES(String O_SIIT_ITMDES) {
            this.O_SIIT_ITMDES = O_SIIT_ITMDES;
        }

        public String getO_SIIT_ITMDES() {
            return O_SIIT_ITMDES;
        }

    }
    public static PL_TSIITM5_SIITITMNUM_PC6_results PL_TSIITM5_SIITITMNUM_PC6(DBTransaction dbt ,BigDecimal I_V_IPMS_RUNNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITITMNUM_PC6_results results=new PL_TSIITM5_SIITITMNUM_PC6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_V_IPMS_RUNNUM NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_ITMDES VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITITMNUM_PC6(I_V_IPMS_RUNNUM,I_G_DIVCOD,O_SIIT_ITMDES);\n" +
                        "  ?     := O_SIIT_ITMDES;\n" +
                        "END;\n", 0);
            if (I_V_IPMS_RUNNUM !=null) {
                cs.setBigDecimal(1,I_V_IPMS_RUNNUM);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(2,I_G_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIIT_ITMDES(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIIT_ITMDES(null);
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
    public static class PL_TSIITM5_SIITSUPCOD_POT1_results {
        String O_SIIT_SUPITM;
        public void setO_SIIT_SUPITM(String O_SIIT_SUPITM) {
            this.O_SIIT_SUPITM = O_SIIT_SUPITM;
        }

        public String getO_SIIT_SUPITM() {
            return O_SIIT_SUPITM;
        }

    }
    public static PL_TSIITM5_SIITSUPCOD_POT1_results PL_TSIITM5_SIITSUPCOD_POT1(DBTransaction dbt ,String I_SIIT_ITMNUM,String I_SIIT_SUPCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITSUPCOD_POT1_results results=new PL_TSIITM5_SIITSUPCOD_POT1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_ITMNUM VARCHAR2(32000) :=?;\n" +
                        "  I_SIIT_SUPCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_SUPITM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITSUPCOD_POT1(I_SIIT_ITMNUM,I_SIIT_SUPCOD,I_G_DIVCOD,O_SIIT_SUPITM);\n" +
                        "  ?     := O_SIIT_SUPITM;\n" +
                        "END;\n", 0);
            if (I_SIIT_ITMNUM !=null) {
                cs.setString(1,I_SIIT_ITMNUM);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIIT_SUPCOD !=null) {
                cs.setString(2,I_SIIT_SUPCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIIT_SUPITM(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPITM(null);
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
    public static class PL_TSIITM5_SIITSUPCOD_POT2_results {
        String O_SIIT_SUPITM;
        public void setO_SIIT_SUPITM(String O_SIIT_SUPITM) {
            this.O_SIIT_SUPITM = O_SIIT_SUPITM;
        }

        public String getO_SIIT_SUPITM() {
            return O_SIIT_SUPITM;
        }

    }
    public static PL_TSIITM5_SIITSUPCOD_POT2_results PL_TSIITM5_SIITSUPCOD_POT2(DBTransaction dbt ,String I_SIIT_ITMNUM,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIITM5_SIITSUPCOD_POT2_results results=new PL_TSIITM5_SIITSUPCOD_POT2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIIT_ITMNUM VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIIT_SUPITM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIITM5_SIITSUPCOD_POT2(I_SIIT_ITMNUM,I_G_DIVCOD,O_SIIT_SUPITM);\n" +
                        "  ?     := O_SIIT_SUPITM;\n" +
                        "END;\n", 0);
            if (I_SIIT_ITMNUM !=null) {
                cs.setString(1,I_SIIT_ITMNUM);
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
            results.setO_SIIT_SUPITM(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIIT_SUPITM(null);
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
    public static class PL_TSIMST1_POQ1_results {
        String O_MPSM_DES;
        public void setO_MPSM_DES(String O_MPSM_DES) {
            this.O_MPSM_DES = O_MPSM_DES;
        }

        public String getO_MPSM_DES() {
            return O_MPSM_DES;
        }

    }
    public static PL_TSIMST1_POQ1_results PL_TSIMST1_POQ1(DBTransaction dbt ,String I_SIMS_SHPMODCOD)  {
        CallableStatement cs = null;
      PL_TSIMST1_POQ1_results results=new PL_TSIMST1_POQ1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SHPMODCOD VARCHAR2(32000) :=?;\n" +
                        "  O_MPSM_DES VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST1_POQ1(I_SIMS_SHPMODCOD,O_MPSM_DES);\n" +
                        "  ?     := O_MPSM_DES;\n" +
                        "END;\n", 0);
            if (I_SIMS_SHPMODCOD !=null) {
                cs.setString(1,I_SIMS_SHPMODCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPSM_DES(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPSM_DES(null);
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
    public static class PL_TSIMST3_POQ1_results {
        String O_LSTCHG;
        public void setO_LSTCHG(String O_LSTCHG) {
            this.O_LSTCHG = O_LSTCHG;
        }

        public String getO_LSTCHG() {
            return O_LSTCHG;
        }

    }
    public static PL_TSIMST3_POQ1_results PL_TSIMST3_POQ1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_TSIMST3_POQ1_results results=new PL_TSIMST3_POQ1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_LSTCHG VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_POQ1(I_SIMS_SAMINVRUN,O_LSTCHG);\n" +
                        "  ?     := O_LSTCHG;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_LSTCHG(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_LSTCHG(null);
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
    public static class PL_TSIMST3_POQ2_results {
        String O_LSTBY;
        public void setO_LSTBY(String O_LSTBY) {
            this.O_LSTBY = O_LSTBY;
        }

        public String getO_LSTBY() {
            return O_LSTBY;
        }

    }
    public static PL_TSIMST3_POQ2_results PL_TSIMST3_POQ2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_TSIMST3_POQ2_results results=new PL_TSIMST3_POQ2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_LSTBY VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_POQ2(I_SIMS_SAMINVRUN,O_LSTBY);\n" +
                        "  ?     := O_LSTBY;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_LSTBY(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_LSTBY(null);
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
    public static void PL_TSIMST3_PRD1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_SIMS_DIVCOD)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_SIMS_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRD1(I_SIMS_SAMINVRUN,I_SIMS_DIVCOD);\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_SIMS_DIVCOD !=null) {
                cs.setString(2,I_SIMS_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.executeUpdate();
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
    }
    public static void PL_TSIMST3_PRD2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_SIMS_DIVCOD)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_SIMS_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRD2(I_SIMS_SAMINVRUN,I_SIMS_DIVCOD);\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_SIMS_DIVCOD !=null) {
                cs.setString(2,I_SIMS_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.executeUpdate();
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
    }
    public static void PL_TSIMST3_PRD3(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_SIMS_DIVCOD)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_SIMS_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRD3(I_SIMS_SAMINVRUN,I_SIMS_DIVCOD);\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_SIMS_DIVCOD !=null) {
                cs.setString(2,I_SIMS_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.executeUpdate();
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
    }
    public static void PL_TSIMST3_PRD4(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_SIMS_DIVCOD)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_SIMS_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRD4(I_SIMS_SAMINVRUN,I_SIMS_DIVCOD);\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            if (I_SIMS_DIVCOD !=null) {
                cs.setString(2,I_SIMS_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.executeUpdate();
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
    }
    public static void PL_TSIMST3_PRD5(DBTransaction dbt ,String I_SIMS_DIVCOD,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRD5(I_SIMS_DIVCOD,I_SIMS_SAMINVRUN);\n" +
                        "END;\n", 0);
            if (I_SIMS_DIVCOD !=null) {
                cs.setString(1,I_SIMS_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(2,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(2, Types.NUMERIC);
            }
            cs.executeUpdate();
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
    }
    public static class PL_TSIMST3_PRI1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI1_results PL_TSIMST3_PRI1(DBTransaction dbt ,String I_SIMS_DPTCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI1_results results=new PL_TSIMST3_PRI1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI1(I_SIMS_DPTCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_DPTCOD !=null) {
                cs.setString(1,I_SIMS_DPTCOD);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRI10_results {
        String O_SIMS_YEAR;
        public void setO_SIMS_YEAR(String O_SIMS_YEAR) {
            this.O_SIMS_YEAR = O_SIMS_YEAR;
        }

        public String getO_SIMS_YEAR() {
            return O_SIMS_YEAR;
        }

    }
    public static PL_TSIMST3_PRI10_results PL_TSIMST3_PRI10(DBTransaction dbt ,String I_INFO_DIV,String I_SIMS_DPTCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI10_results results=new PL_TSIMST3_PRI10_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_INFO_DIV VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIMS_YEAR VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI10(I_INFO_DIV,I_SIMS_DPTCOD,O_SIMS_YEAR);\n" +
                        "  ?     := O_SIMS_YEAR;\n" +
                        "END;\n", 0);
            if (I_INFO_DIV !=null) {
                cs.setString(1,I_INFO_DIV);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_DPTCOD !=null) {
                cs.setString(2,I_SIMS_DPTCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_YEAR(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIMS_YEAR(null);
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
    public static class PL_TSIMST3_PRI11_results {
        String O_SIMS_SERNUM;
        public void setO_SIMS_SERNUM(String O_SIMS_SERNUM) {
            this.O_SIMS_SERNUM = O_SIMS_SERNUM;
        }

        public String getO_SIMS_SERNUM() {
            return O_SIMS_SERNUM;
        }

    }
    public static PL_TSIMST3_PRI11_results PL_TSIMST3_PRI11(DBTransaction dbt ,String I_SIMS_TYP,String I_SIMS_DPTCOD,String I_SIMS_YEAR)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI11_results results=new PL_TSIMST3_PRI11_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_TYP VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_YEAR VARCHAR2(32000) :=?;\n" +
                        "  O_SIMS_SERNUM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI11(I_SIMS_TYP,I_SIMS_DPTCOD,I_SIMS_YEAR,O_SIMS_SERNUM);\n" +
                        "  ?     := O_SIMS_SERNUM;\n" +
                        "END;\n", 0);
            if (I_SIMS_TYP !=null) {
                cs.setString(1,I_SIMS_TYP);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_DPTCOD !=null) {
                cs.setString(2,I_SIMS_DPTCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_SIMS_YEAR !=null) {
                cs.setString(3,I_SIMS_YEAR);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_SERNUM(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIMS_SERNUM(null);
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
    public static class PL_TSIMST3_PRI12_results {
        BigDecimal O_SIMS_SAMINVRUN;
        public void setO_SIMS_SAMINVRUN(BigDecimal O_SIMS_SAMINVRUN) {
            this.O_SIMS_SAMINVRUN = O_SIMS_SAMINVRUN;
        }

        public BigDecimal getO_SIMS_SAMINVRUN() {
            return O_SIMS_SAMINVRUN;
        }

    }
    public static PL_TSIMST3_PRI12_results PL_TSIMST3_PRI12(DBTransaction dbt )  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI12_results results=new PL_TSIMST3_PRI12_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  O_SIMS_SAMINVRUN NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI12(O_SIMS_SAMINVRUN);\n" +
                        "  ?     := O_SIMS_SAMINVRUN;\n" +
                        "END;\n", 0);
            cs.registerOutParameter(1,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_SIMS_SAMINVRUN(cs.getBigDecimal(1));
            if (cs.wasNull()) {
                results.setO_SIMS_SAMINVRUN(null);
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
    public static class PL_TSIMST3_PRI2_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI2_results PL_TSIMST3_PRI2(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI2_results results=new PL_TSIMST3_PRI2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI2(I_SIMS_CUSCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRI3_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI3_results PL_TSIMST3_PRI3(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_SIMS_CONPERCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI3_results results=new PL_TSIMST3_PRI3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CONPERCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI3(I_SIMS_CUSCOD,I_SIMS_CONPERCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_CONPERCOD !=null) {
                cs.setString(2,I_SIMS_CONPERCOD);
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
            results.setO_I(cs.getBigDecimal(4));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRI4_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI4_results PL_TSIMST3_PRI4(DBTransaction dbt ,String I_SIMS_PORT_L)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI4_results results=new PL_TSIMST3_PRI4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_L VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI4(I_SIMS_PORT_L,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_L !=null) {
                cs.setString(1,I_SIMS_PORT_L);
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
    public static class PL_TSIMST3_PRI5_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI5_results PL_TSIMST3_PRI5(DBTransaction dbt ,String I_SIMS_VIA)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI5_results results=new PL_TSIMST3_PRI5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_VIA VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI5(I_SIMS_VIA,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_VIA !=null) {
                cs.setString(1,I_SIMS_VIA);
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
    public static class PL_TSIMST3_PRI6_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI6_results PL_TSIMST3_PRI6(DBTransaction dbt ,String I_SIMS_PORT_D)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI6_results results=new PL_TSIMST3_PRI6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_D VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI6(I_SIMS_PORT_D,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_D !=null) {
                cs.setString(1,I_SIMS_PORT_D);
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
    public static class PL_TSIMST3_PRI7_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI7_results PL_TSIMST3_PRI7(DBTransaction dbt ,String I_SIMS_ADRCOD,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI7_results results=new PL_TSIMST3_PRI7_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_ADRCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI7(I_SIMS_ADRCOD,I_SIMS_CUSCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_ADRCOD !=null) {
                cs.setString(1,I_SIMS_ADRCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(2,I_SIMS_CUSCOD);
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
            results.setO_I(cs.getBigDecimal(4));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRI8_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI8_results PL_TSIMST3_PRI8(DBTransaction dbt ,String I_SIMS_CARCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI8_results results=new PL_TSIMST3_PRI8_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CARCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI8(I_SIMS_CARCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CARCOD !=null) {
                cs.setString(1,I_SIMS_CARCOD);
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
    public static class PL_TSIMST3_PRI9_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRI9_results PL_TSIMST3_PRI9(DBTransaction dbt ,String I_SIMS_SHPMODCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRI9_results results=new PL_TSIMST3_PRI9_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SHPMODCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRI9(I_SIMS_SHPMODCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_SHPMODCOD !=null) {
                cs.setString(1,I_SIMS_SHPMODCOD);
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
    public static class PL_TSIMST3_PRU1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU1_results PL_TSIMST3_PRU1(DBTransaction dbt ,String I_SIMS_DPTCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU1_results results=new PL_TSIMST3_PRU1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU1(I_SIMS_DPTCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_DPTCOD !=null) {
                cs.setString(1,I_SIMS_DPTCOD);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRU2_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU2_results PL_TSIMST3_PRU2(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU2_results results=new PL_TSIMST3_PRU2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU2(I_SIMS_CUSCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRU3_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU3_results PL_TSIMST3_PRU3(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_SIMS_CONPERCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU3_results results=new PL_TSIMST3_PRU3_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CONPERCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU3(I_SIMS_CUSCOD,I_SIMS_CONPERCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_CONPERCOD !=null) {
                cs.setString(2,I_SIMS_CONPERCOD);
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
            results.setO_I(cs.getBigDecimal(4));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRU4_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU4_results PL_TSIMST3_PRU4(DBTransaction dbt ,String I_SIMS_PORT_L)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU4_results results=new PL_TSIMST3_PRU4_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_L VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU4(I_SIMS_PORT_L,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_L !=null) {
                cs.setString(1,I_SIMS_PORT_L);
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
    public static class PL_TSIMST3_PRU5_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU5_results PL_TSIMST3_PRU5(DBTransaction dbt ,String I_SIMS_VIA)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU5_results results=new PL_TSIMST3_PRU5_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_VIA VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU5(I_SIMS_VIA,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_VIA !=null) {
                cs.setString(1,I_SIMS_VIA);
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
    public static class PL_TSIMST3_PRU6_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU6_results PL_TSIMST3_PRU6(DBTransaction dbt ,String I_SIMS_PORT_D)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU6_results results=new PL_TSIMST3_PRU6_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_D VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU6(I_SIMS_PORT_D,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_D !=null) {
                cs.setString(1,I_SIMS_PORT_D);
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
    public static class PL_TSIMST3_PRU7_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU7_results PL_TSIMST3_PRU7(DBTransaction dbt ,String I_SIMS_ADRCOD,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU7_results results=new PL_TSIMST3_PRU7_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_ADRCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU7(I_SIMS_ADRCOD,I_SIMS_CUSCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_ADRCOD !=null) {
                cs.setString(1,I_SIMS_ADRCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(2,I_SIMS_CUSCOD);
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
            results.setO_I(cs.getBigDecimal(4));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_TSIMST3_PRU8_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU8_results PL_TSIMST3_PRU8(DBTransaction dbt ,String I_SIMS_CARCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU8_results results=new PL_TSIMST3_PRU8_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CARCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU8(I_SIMS_CARCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CARCOD !=null) {
                cs.setString(1,I_SIMS_CARCOD);
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
    public static class PL_TSIMST3_PRU9_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_TSIMST3_PRU9_results PL_TSIMST3_PRU9(DBTransaction dbt ,String I_SIMS_SHPMODCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_PRU9_results results=new PL_TSIMST3_PRU9_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SHPMODCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_PRU9(I_SIMS_SHPMODCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_SHPMODCOD !=null) {
                cs.setString(1,I_SIMS_SHPMODCOD);
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
    public static class PL_TSIMST3_SIMSCURCOD_VI1_results {
        BigDecimal O_N_COUNT;
        public void setO_N_COUNT(BigDecimal O_N_COUNT) {
            this.O_N_COUNT = O_N_COUNT;
        }

        public BigDecimal getO_N_COUNT() {
            return O_N_COUNT;
        }

    }
    public static PL_TSIMST3_SIMSCURCOD_VI1_results PL_TSIMST3_SIMSCURCOD_VI1(DBTransaction dbt ,String I_SIMS_CURCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_SIMSCURCOD_VI1_results results=new PL_TSIMST3_SIMSCURCOD_VI1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CURCOD VARCHAR2(32000) :=?;\n" +
                        "  O_N_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_SIMSCURCOD_VI1(I_SIMS_CURCOD,O_N_COUNT);\n" +
                        "  ?     := O_N_COUNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_CURCOD !=null) {
                cs.setString(1,I_SIMS_CURCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results.setO_N_COUNT(cs.getBigDecimal(2));
            if (cs.wasNull()) {
                results.setO_N_COUNT(null);
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
    public static class PL_TSIMST3_SIMSCURCOD_VI2_results {
        BigDecimal O_N_COUNT;
        public void setO_N_COUNT(BigDecimal O_N_COUNT) {
            this.O_N_COUNT = O_N_COUNT;
        }

        public BigDecimal getO_N_COUNT() {
            return O_N_COUNT;
        }

    }
    public static PL_TSIMST3_SIMSCURCOD_VI2_results PL_TSIMST3_SIMSCURCOD_VI2(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_TSIMST3_SIMSCURCOD_VI2_results results=new PL_TSIMST3_SIMSCURCOD_VI2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_N_COUNT NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_TSIMST3_SIMSCURCOD_VI2(I_SIMS_SAMINVRUN,I_G_DIVCOD,O_N_COUNT);\n" +
                        "  ?     := O_N_COUNT;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
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
            results.setO_N_COUNT(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_N_COUNT(null);
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
    public static class PL_UPDLST6_POQ1_results {
        String O_SIMS_INVNO;
        public void setO_SIMS_INVNO(String O_SIMS_INVNO) {
            this.O_SIMS_INVNO = O_SIMS_INVNO;
        }

        public String getO_SIMS_INVNO() {
            return O_SIMS_INVNO;
        }

    }
    public static PL_UPDLST6_POQ1_results PL_UPDLST6_POQ1(DBTransaction dbt ,BigDecimal I_SIMS_SAMINVRUN)  {
        CallableStatement cs = null;
      PL_UPDLST6_POQ1_results results=new PL_UPDLST6_POQ1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SAMINVRUN NUMBER :=?;\n" +
                        "  O_SIMS_INVNO VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_UPDLST6_POQ1(I_SIMS_SAMINVRUN,O_SIMS_INVNO);\n" +
                        "  ?     := O_SIMS_INVNO;\n" +
                        "END;\n", 0);
            if (I_SIMS_SAMINVRUN !=null) {
                cs.setBigDecimal(1,I_SIMS_SAMINVRUN);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_INVNO(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SIMS_INVNO(null);
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
    public static class PL_VALIDATE_ADRCOD1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_ADRCOD1_results PL_VALIDATE_ADRCOD1(DBTransaction dbt ,String I_SIMS_ADRCOD,String I_SIMS_CUSCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_ADRCOD1_results results=new PL_VALIDATE_ADRCOD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_ADRCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_ADRCOD1(I_SIMS_ADRCOD,I_SIMS_CUSCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_ADRCOD !=null) {
                cs.setString(1,I_SIMS_ADRCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(2,I_SIMS_CUSCOD);
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
    public static class PL_VALIDATE_ADRCOD2_results {
        String O_SIMS_ADR1;
        String O_SIMS_ADR2;
        String O_SIMS_ADR3;
        String O_SIMS_ADR4;
        public void setO_SIMS_ADR1(String O_SIMS_ADR1) {
            this.O_SIMS_ADR1 = O_SIMS_ADR1;
        }

        public String getO_SIMS_ADR1() {
            return O_SIMS_ADR1;
        }

        public void setO_SIMS_ADR2(String O_SIMS_ADR2) {
            this.O_SIMS_ADR2 = O_SIMS_ADR2;
        }

        public String getO_SIMS_ADR2() {
            return O_SIMS_ADR2;
        }

        public void setO_SIMS_ADR3(String O_SIMS_ADR3) {
            this.O_SIMS_ADR3 = O_SIMS_ADR3;
        }

        public String getO_SIMS_ADR3() {
            return O_SIMS_ADR3;
        }

        public void setO_SIMS_ADR4(String O_SIMS_ADR4) {
            this.O_SIMS_ADR4 = O_SIMS_ADR4;
        }

        public String getO_SIMS_ADR4() {
            return O_SIMS_ADR4;
        }

    }
    public static PL_VALIDATE_ADRCOD2_results PL_VALIDATE_ADRCOD2(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_SIMS_ADRCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_ADRCOD2_results results=new PL_VALIDATE_ADRCOD2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_ADRCOD VARCHAR2(32000) :=?;\n" +
                        "  O_SIMS_ADR1 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR2 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR3 VARCHAR2(32000);\n" +
                        "  O_SIMS_ADR4 VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_ADRCOD2(I_SIMS_CUSCOD,I_SIMS_ADRCOD,O_SIMS_ADR1,O_SIMS_ADR2,O_SIMS_ADR3,O_SIMS_ADR4);\n" +
                        "  ?     := O_SIMS_ADR1;\n" +
                        "  ?     := O_SIMS_ADR2;\n" +
                        "  ?     := O_SIMS_ADR3;\n" +
                        "  ?     := O_SIMS_ADR4;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_ADRCOD !=null) {
                cs.setString(2,I_SIMS_ADRCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.registerOutParameter(6,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SIMS_ADR1(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR1(null);
            }
            results.setO_SIMS_ADR2(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR2(null);
            }
            results.setO_SIMS_ADR3(cs.getString(5));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR3(null);
            }
            results.setO_SIMS_ADR4(cs.getString(6));
            if (cs.wasNull()) {
                results.setO_SIMS_ADR4(null);
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
    public static class PL_VALIDATE_CARCOD1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_CARCOD1_results PL_VALIDATE_CARCOD1(DBTransaction dbt ,String I_SIMS_CARCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_CARCOD1_results results=new PL_VALIDATE_CARCOD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CARCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CARCOD1(I_SIMS_CARCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CARCOD !=null) {
                cs.setString(1,I_SIMS_CARCOD);
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
    public static class PL_VALIDATE_CARCOD2_results {
        String O_MPCA_CARNAM;
        public void setO_MPCA_CARNAM(String O_MPCA_CARNAM) {
            this.O_MPCA_CARNAM = O_MPCA_CARNAM;
        }

        public String getO_MPCA_CARNAM() {
            return O_MPCA_CARNAM;
        }

    }
    public static PL_VALIDATE_CARCOD2_results PL_VALIDATE_CARCOD2(DBTransaction dbt ,String I_SIMS_CARCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_CARCOD2_results results=new PL_VALIDATE_CARCOD2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CARCOD VARCHAR2(32000) :=?;\n" +
                        "  O_MPCA_CARNAM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CARCOD2(I_SIMS_CARCOD,O_MPCA_CARNAM);\n" +
                        "  ?     := O_MPCA_CARNAM;\n" +
                        "END;\n", 0);
            if (I_SIMS_CARCOD !=null) {
                cs.setString(1,I_SIMS_CARCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPCA_CARNAM(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPCA_CARNAM(null);
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
    public static class PL_VALIDATE_CHRTYP1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_CHRTYP1_results PL_VALIDATE_CHRTYP1(DBTransaction dbt ,String I_SIMS_CHRTYP)  {
        CallableStatement cs = null;
      PL_VALIDATE_CHRTYP1_results results=new PL_VALIDATE_CHRTYP1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CHRTYP VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CHRTYP1(I_SIMS_CHRTYP,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CHRTYP !=null) {
                cs.setString(1,I_SIMS_CHRTYP);
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
    public static class PL_VALIDATE_CHRTYP2_results {
        String O_SICT_DES;
        public void setO_SICT_DES(String O_SICT_DES) {
            this.O_SICT_DES = O_SICT_DES;
        }

        public String getO_SICT_DES() {
            return O_SICT_DES;
        }

    }
    public static PL_VALIDATE_CHRTYP2_results PL_VALIDATE_CHRTYP2(DBTransaction dbt ,String I_SIMS_CHRTYP)  {
        CallableStatement cs = null;
      PL_VALIDATE_CHRTYP2_results results=new PL_VALIDATE_CHRTYP2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CHRTYP VARCHAR2(32000) :=?;\n" +
                        "  O_SICT_DES VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CHRTYP2(I_SIMS_CHRTYP,O_SICT_DES);\n" +
                        "  ?     := O_SICT_DES;\n" +
                        "END;\n", 0);
            if (I_SIMS_CHRTYP !=null) {
                cs.setString(1,I_SIMS_CHRTYP);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_SICT_DES(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_SICT_DES(null);
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
    public static class PL_VALIDATE_CONPERCOD1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_CONPERCOD1_results PL_VALIDATE_CONPERCOD1(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_CONPERCOD1_results results=new PL_VALIDATE_CONPERCOD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CONPERCOD1(I_SIMS_CUSCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_VALIDATE_CONPERCOD2_results {
        String O_CPCP_CONPERNAM;
        public void setO_CPCP_CONPERNAM(String O_CPCP_CONPERNAM) {
            this.O_CPCP_CONPERNAM = O_CPCP_CONPERNAM;
        }

        public String getO_CPCP_CONPERNAM() {
            return O_CPCP_CONPERNAM;
        }

    }
    public static PL_VALIDATE_CONPERCOD2_results PL_VALIDATE_CONPERCOD2(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_SIMS_CONPERCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_CONPERCOD2_results results=new PL_VALIDATE_CONPERCOD2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_SIMS_CONPERCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_CPCP_CONPERNAM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CONPERCOD2(I_SIMS_CUSCOD,I_SIMS_CONPERCOD,I_G_DIVCOD,O_CPCP_CONPERNAM);\n" +
                        "  ?     := O_CPCP_CONPERNAM;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_SIMS_CONPERCOD !=null) {
                cs.setString(2,I_SIMS_CONPERCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (I_G_DIVCOD !=null) {
                cs.setString(3,I_G_DIVCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_CPCP_CONPERNAM(cs.getString(4));
            if (cs.wasNull()) {
                results.setO_CPCP_CONPERNAM(null);
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
    public static class PL_VALIDATE_CUSCOD1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_CUSCOD1_results PL_VALIDATE_CUSCOD1(DBTransaction dbt ,String I_SIMS_CUSCOD,String I_G_DIVCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_CUSCOD1_results results=new PL_VALIDATE_CUSCOD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  I_G_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CUSCOD1(I_SIMS_CUSCOD,I_G_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
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
            results.setO_I(cs.getBigDecimal(3));
            if (cs.wasNull()) {
                results.setO_I(null);
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
    public static class PL_VALIDATE_CUSCOD2_results {
        String O_CPAL_NAM;
        public void setO_CPAL_NAM(String O_CPAL_NAM) {
            this.O_CPAL_NAM = O_CPAL_NAM;
        }

        public String getO_CPAL_NAM() {
            return O_CPAL_NAM;
        }

    }
    public static PL_VALIDATE_CUSCOD2_results PL_VALIDATE_CUSCOD2(DBTransaction dbt ,String I_SIMS_CUSCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_CUSCOD2_results results=new PL_VALIDATE_CUSCOD2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  O_CPAL_NAM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_CUSCOD2(I_SIMS_CUSCOD,O_CPAL_NAM);\n" +
                        "  ?     := O_CPAL_NAM;\n" +
                        "END;\n", 0);
            if (I_SIMS_CUSCOD !=null) {
                cs.setString(1,I_SIMS_CUSCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_CPAL_NAM(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_CPAL_NAM(null);
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
    public static class PL_VALIDATE_DIVCOD1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_DIVCOD1_results PL_VALIDATE_DIVCOD1(DBTransaction dbt ,String I_SIMS_DIVCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_DIVCOD1_results results=new PL_VALIDATE_DIVCOD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_DIVCOD1(I_SIMS_DIVCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_DIVCOD !=null) {
                cs.setString(1,I_SIMS_DIVCOD);
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
    public static class PL_VALIDATE_DPTCOD1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_DPTCOD1_results PL_VALIDATE_DPTCOD1(DBTransaction dbt ,String I_SIMS_DPTCOD,String I_W_DIV)  {
        CallableStatement cs = null;
      PL_VALIDATE_DPTCOD1_results results=new PL_VALIDATE_DPTCOD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_DPTCOD1(I_SIMS_DPTCOD,I_W_DIV,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_DPTCOD !=null) {
                cs.setString(1,I_SIMS_DPTCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_W_DIV !=null) {
                cs.setString(2,I_W_DIV);
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
    public static class PL_VALIDATE_DPTCOD2_results {
        String O_OPDP_NAM;
        public void setO_OPDP_NAM(String O_OPDP_NAM) {
            this.O_OPDP_NAM = O_OPDP_NAM;
        }

        public String getO_OPDP_NAM() {
            return O_OPDP_NAM;
        }

    }
    public static PL_VALIDATE_DPTCOD2_results PL_VALIDATE_DPTCOD2(DBTransaction dbt ,String I_SIMS_DPTCOD,String I_W_DIV)  {
        CallableStatement cs = null;
      PL_VALIDATE_DPTCOD2_results results=new PL_VALIDATE_DPTCOD2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  I_W_DIV VARCHAR2(32000) :=?;\n" +
                        "  O_OPDP_NAM VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_DPTCOD2(I_SIMS_DPTCOD,I_W_DIV,O_OPDP_NAM);\n" +
                        "  ?     := O_OPDP_NAM;\n" +
                        "END;\n", 0);
            if (I_SIMS_DPTCOD !=null) {
                cs.setString(1,I_SIMS_DPTCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (I_W_DIV !=null) {
                cs.setString(2,I_W_DIV);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_OPDP_NAM(cs.getString(3));
            if (cs.wasNull()) {
                results.setO_OPDP_NAM(null);
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
    public static class PL_VALIDATE_PORT_D1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_PORT_D1_results PL_VALIDATE_PORT_D1(DBTransaction dbt ,String I_SIMS_PORT_D)  {
        CallableStatement cs = null;
      PL_VALIDATE_PORT_D1_results results=new PL_VALIDATE_PORT_D1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_D VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_PORT_D1(I_SIMS_PORT_D,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_D !=null) {
                cs.setString(1,I_SIMS_PORT_D);
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
    public static class PL_VALIDATE_PORT_D2_results {
        String O_MPCP_NAM_D;
        public void setO_MPCP_NAM_D(String O_MPCP_NAM_D) {
            this.O_MPCP_NAM_D = O_MPCP_NAM_D;
        }

        public String getO_MPCP_NAM_D() {
            return O_MPCP_NAM_D;
        }

    }
    public static PL_VALIDATE_PORT_D2_results PL_VALIDATE_PORT_D2(DBTransaction dbt ,String I_SIMS_PORT_D)  {
        CallableStatement cs = null;
      PL_VALIDATE_PORT_D2_results results=new PL_VALIDATE_PORT_D2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_D VARCHAR2(32000) :=?;\n" +
                        "  O_MPCP_NAM_D VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_PORT_D2(I_SIMS_PORT_D,O_MPCP_NAM_D);\n" +
                        "  ?     := O_MPCP_NAM_D;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_D !=null) {
                cs.setString(1,I_SIMS_PORT_D);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPCP_NAM_D(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPCP_NAM_D(null);
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
    public static class PL_VALIDATE_PORT_L1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_PORT_L1_results PL_VALIDATE_PORT_L1(DBTransaction dbt ,String I_SIMS_PORT_L)  {
        CallableStatement cs = null;
      PL_VALIDATE_PORT_L1_results results=new PL_VALIDATE_PORT_L1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_L VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_PORT_L1(I_SIMS_PORT_L,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_L !=null) {
                cs.setString(1,I_SIMS_PORT_L);
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
    public static class PL_VALIDATE_PORT_L2_results {
        String O_MPCP_NAM_L;
        public void setO_MPCP_NAM_L(String O_MPCP_NAM_L) {
            this.O_MPCP_NAM_L = O_MPCP_NAM_L;
        }

        public String getO_MPCP_NAM_L() {
            return O_MPCP_NAM_L;
        }

    }
    public static PL_VALIDATE_PORT_L2_results PL_VALIDATE_PORT_L2(DBTransaction dbt ,String I_SIMS_PORT_L)  {
        CallableStatement cs = null;
      PL_VALIDATE_PORT_L2_results results=new PL_VALIDATE_PORT_L2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_PORT_L VARCHAR2(32000) :=?;\n" +
                        "  O_MPCP_NAM_L VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_PORT_L2(I_SIMS_PORT_L,O_MPCP_NAM_L);\n" +
                        "  ?     := O_MPCP_NAM_L;\n" +
                        "END;\n", 0);
            if (I_SIMS_PORT_L !=null) {
                cs.setString(1,I_SIMS_PORT_L);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPCP_NAM_L(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPCP_NAM_L(null);
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
    public static class PL_VALIDATE_SHPMOD1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_SHPMOD1_results PL_VALIDATE_SHPMOD1(DBTransaction dbt ,String I_SIMS_SHPMODCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_SHPMOD1_results results=new PL_VALIDATE_SHPMOD1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SHPMODCOD VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_SHPMOD1(I_SIMS_SHPMODCOD,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_SHPMODCOD !=null) {
                cs.setString(1,I_SIMS_SHPMODCOD);
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
    public static class PL_VALIDATE_SHPMOD2_results {
        String O_MPSM_DES;
        public void setO_MPSM_DES(String O_MPSM_DES) {
            this.O_MPSM_DES = O_MPSM_DES;
        }

        public String getO_MPSM_DES() {
            return O_MPSM_DES;
        }

    }
    public static PL_VALIDATE_SHPMOD2_results PL_VALIDATE_SHPMOD2(DBTransaction dbt ,String I_SIMS_SHPMODCOD)  {
        CallableStatement cs = null;
      PL_VALIDATE_SHPMOD2_results results=new PL_VALIDATE_SHPMOD2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_SHPMODCOD VARCHAR2(32000) :=?;\n" +
                        "  O_MPSM_DES VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_SHPMOD2(I_SIMS_SHPMODCOD,O_MPSM_DES);\n" +
                        "  ?     := O_MPSM_DES;\n" +
                        "END;\n", 0);
            if (I_SIMS_SHPMODCOD !=null) {
                cs.setString(1,I_SIMS_SHPMODCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPSM_DES(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPSM_DES(null);
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
    public static class PL_VALIDATE_VIA1_results {
        BigDecimal O_I;
        public void setO_I(BigDecimal O_I) {
            this.O_I = O_I;
        }

        public BigDecimal getO_I() {
            return O_I;
        }

    }
    public static PL_VALIDATE_VIA1_results PL_VALIDATE_VIA1(DBTransaction dbt ,String I_SIMS_VIA)  {
        CallableStatement cs = null;
      PL_VALIDATE_VIA1_results results=new PL_VALIDATE_VIA1_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_VIA VARCHAR2(32000) :=?;\n" +
                        "  O_I NUMBER;\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_VIA1(I_SIMS_VIA,O_I);\n" +
                        "  ?     := O_I;\n" +
                        "END;\n", 0);
            if (I_SIMS_VIA !=null) {
                cs.setString(1,I_SIMS_VIA);
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
    public static class PL_VALIDATE_VIA2_results {
        String O_MPCP_NAM_VIA;
        public void setO_MPCP_NAM_VIA(String O_MPCP_NAM_VIA) {
            this.O_MPCP_NAM_VIA = O_MPCP_NAM_VIA;
        }

        public String getO_MPCP_NAM_VIA() {
            return O_MPCP_NAM_VIA;
        }

    }
    public static PL_VALIDATE_VIA2_results PL_VALIDATE_VIA2(DBTransaction dbt ,String I_SIMS_VIA)  {
        CallableStatement cs = null;
      PL_VALIDATE_VIA2_results results=new PL_VALIDATE_VIA2_results();
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  I_SIMS_VIA VARCHAR2(32000) :=?;\n" +
                        "  O_MPCP_NAM_VIA VARCHAR2(32000);\n" +
                        "BEGIN\n" +
                        "   DP_MIG_XSIM2.PL_VALIDATE_VIA2(I_SIMS_VIA,O_MPCP_NAM_VIA);\n" +
                        "  ?     := O_MPCP_NAM_VIA;\n" +
                        "END;\n", 0);
            if (I_SIMS_VIA !=null) {
                cs.setString(1,I_SIMS_VIA);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results.setO_MPCP_NAM_VIA(cs.getString(2));
            if (cs.wasNull()) {
                results.setO_MPCP_NAM_VIA(null);
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
