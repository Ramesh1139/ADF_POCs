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
public class DP_UTL {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_UTL.class);
    public static String F_WRAP_LONG(DBTransaction dbt ,String P_LONG_IN,BigDecimal P_LINE_LEN)  {
        CallableStatement cs = null;
      String results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN VARCHAR2(32000);\n" +
                        "  P_LONG_IN VARCHAR2(32000) :=?;\n" +
                        "  P_LINE_LEN NUMBER :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_UTL.F_WRAP_LONG(P_LONG_IN,P_LINE_LEN);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (P_LONG_IN !=null) {
                cs.setString(1,P_LONG_IN);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (P_LINE_LEN !=null) {
                cs.setBigDecimal(2,P_LINE_LEN);
            } else {
                cs.setNull(2, Types.NUMERIC);
            }
            cs.registerOutParameter(3,Types.VARCHAR);
            cs.executeUpdate();
            results=cs.getString(3);
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
    public static void P_EXECUTE_SQL(DBTransaction dbt ,String STR)  {
        CallableStatement cs = null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  STR VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "   DP_UTL.P_EXECUTE_SQL(STR);\n" +
                        "END;\n", 0);
            if (STR !=null) {
                cs.setString(1,STR);
            } else {
                cs.setNull(1, Types.VARCHAR);
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


}
