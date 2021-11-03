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
public class DP_DEF {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_DEF.class);
    public static Date GET_SYS_DATE(DBTransaction dbt ,BigDecimal P_DAYS)  {
        CallableStatement cs = null;
      Date results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN DATE;\n" +
                        "  P_DAYS NUMBER :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_DEF.GET_SYS_DATE(P_DAYS);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (P_DAYS !=null) {
                cs.setBigDecimal(1,P_DAYS);
            } else {
                cs.setNull(1, Types.NUMERIC);
            }
            cs.registerOutParameter(2,Types.DATE);
            cs.executeUpdate();
            results=cs.getDate(2);
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
