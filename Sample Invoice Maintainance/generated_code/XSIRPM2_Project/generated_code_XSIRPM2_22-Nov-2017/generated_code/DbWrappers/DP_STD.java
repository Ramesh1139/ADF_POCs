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
public class DP_STD {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_STD.class);
    public static BigDecimal DP_OPDV_DIVCOD_N(DBTransaction dbt ,String P_OPDV_DIVCOD,String P_DIVCOD,String RESERVE1)  {
        CallableStatement cs = null;
      BigDecimal results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN NUMBER;\n" +
                        "  P_OPDV_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  P_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  RESERVE1 VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_STD.DP_OPDV_DIVCOD_N(P_OPDV_DIVCOD,P_DIVCOD,RESERVE1);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (P_OPDV_DIVCOD !=null) {
                cs.setString(1,P_OPDV_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (P_DIVCOD !=null) {
                cs.setString(2,P_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (RESERVE1 !=null) {
                cs.setString(3,RESERVE1);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.NUMERIC);
            cs.executeUpdate();
            results=cs.getBigDecimal(4);
            if (cs.wasNull()) {
                results=null;
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
    public static BigDecimal DP_OPDV_DIVCOD_N(DBTransaction dbt ,String P_OPDV_DIVCOD,String P_DIVCOD)  {
        CallableStatement cs = null;
      BigDecimal results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN NUMBER;\n" +
                        "  P_OPDV_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  P_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_STD.DP_OPDV_DIVCOD_N(P_OPDV_DIVCOD,P_DIVCOD);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (P_OPDV_DIVCOD !=null) {
                cs.setString(1,P_OPDV_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (P_DIVCOD !=null) {
                cs.setString(2,P_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            cs.registerOutParameter(3,Types.NUMERIC);
            cs.executeUpdate();
            results=cs.getBigDecimal(3);
            if (cs.wasNull()) {
                results=null;
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
    public static BigDecimal DP_OPDV_DIVCOD_N(DBTransaction dbt ,String P_OPDV_DIVCOD)  {
        CallableStatement cs = null;
      BigDecimal results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN NUMBER;\n" +
                        "  P_OPDV_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_STD.DP_OPDV_DIVCOD_N(P_OPDV_DIVCOD);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (P_OPDV_DIVCOD !=null) {
                cs.setString(1,P_OPDV_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.NUMERIC);
            cs.executeUpdate();
            results=cs.getBigDecimal(2);
            if (cs.wasNull()) {
                results=null;
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
