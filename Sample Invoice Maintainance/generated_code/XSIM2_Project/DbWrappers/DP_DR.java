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
public class DP_DR {

private static ADFLogger _logger = ADFLogger.createADFLogger(DP_DR.class);
    public static String F_SPAL_FULNAM(DBTransaction dbt ,String SUPCOD)  {
        CallableStatement cs = null;
      String results=null;
        try {

            cs = dbt.createCallableStatement(
                        "DECLARE\n" +
                        "  DEFAULT_RETURN VARCHAR2(32000);\n" +
                        "  SUPCOD VARCHAR2(32000) :=?;\n" +
                        "BEGIN\n" +
                        "  DEFAULT_RETURN  := DP_DR.F_SPAL_FULNAM(SUPCOD);\n" +
                        "  ?     := DEFAULT_RETURN;\n" +
                        "END;\n", 0);
            if (SUPCOD !=null) {
                cs.setString(1,SUPCOD);
            } else {
                cs.setNull(1, Types.VARCHAR);
            }
            cs.registerOutParameter(2,Types.VARCHAR);
            cs.executeUpdate();
            results=cs.getString(2);
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
