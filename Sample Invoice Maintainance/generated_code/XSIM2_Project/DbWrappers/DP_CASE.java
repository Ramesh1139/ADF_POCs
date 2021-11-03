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
public class DP_CASE {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_CASE.class);
    public static String CHECK_DPT_SETCOD(DBTransaction dbt ,String PI_DIVCOD,String PI_DPTCOD,String PI_CUSCOD,String PI_SETCOD)  {
        CallableStatement cs = null;
      String results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN VARCHAR2(32000);\n" +
                        "  PI_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  PI_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  PI_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  PI_SETCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_CASE.CHECK_DPT_SETCOD(PI_DIVCOD,PI_DPTCOD,PI_CUSCOD,PI_SETCOD);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (PI_DIVCOD !=null) {
                cs.setString(1,PI_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (PI_DPTCOD !=null) {
                cs.setString(2,PI_DPTCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (PI_CUSCOD !=null) {
                cs.setString(3,PI_CUSCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            if (PI_SETCOD !=null) {
                cs.setString(4,PI_SETCOD);
            } else {
                cs.setNull(4, Types.VARCHAR);
            }
            cs.registerOutParameter(5,Types.VARCHAR);
            cs.executeUpdate();
            results=cs.getString(5);
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
    public static String CHECK_SETCOD(DBTransaction dbt ,String PI_DIVCOD,String PI_CUSCOD,String PI_SETCOD)  {
        CallableStatement cs = null;
      String results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN VARCHAR2(32000);\n" +
                        "  PI_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  PI_CUSCOD VARCHAR2(32000) :=?;\n" +
                        "  PI_SETCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_CASE.CHECK_SETCOD(PI_DIVCOD,PI_CUSCOD,PI_SETCOD);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (PI_DIVCOD !=null) {
                cs.setString(1,PI_DIVCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (PI_CUSCOD !=null) {
                cs.setString(2,PI_CUSCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (PI_SETCOD !=null) {
                cs.setString(3,PI_SETCOD);
            } else {
                cs.setNull(3, Types.VARCHAR);
            }
            cs.registerOutParameter(4,Types.VARCHAR);
            cs.executeUpdate();
            results=cs.getString(4);
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
