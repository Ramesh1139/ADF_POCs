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
public class CHECK_DPT {

private static ADFLogger _logger = ADFLogger.createADFLogger(CHECK_DPT.class);
    public static String CHECK_DPT(DBTransaction dbt ,String P_DPTCOD,String P_DIVCOD,String P_USERID)  {
        CallableStatement cs = null;
      String results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN VARCHAR2(32000);\n" +
                        "  P_DPTCOD VARCHAR2(32000) :=?;\n" +
                        "  P_DIVCOD VARCHAR2(32000) :=?;\n" +
                        "  P_USERID VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := CHECK_DPT(P_DPTCOD,P_DIVCOD,P_USERID);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (P_DPTCOD !=null) {
                cs.setString(1,P_DPTCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            if (P_DIVCOD !=null) {
                cs.setString(2,P_DIVCOD);
            } else {
                cs.setNull(2, Types.VARCHAR);
            }
            if (P_USERID !=null) {
                cs.setString(3,P_USERID);
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
