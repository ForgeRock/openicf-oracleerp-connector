/*
 * ====================
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * 
 * Copyright 2008-2009 Sun Microsystems, Inc. All rights reserved.     
 * 
 * The contents of this file are subject to the terms of the Common Development 
 * and Distribution License("CDDL") (the "License").  You may not use this file 
 * except in compliance with the License.
 * 
 * You can obtain a copy of the License at 
 * http://IdentityConnectors.dev.java.net/legal/license.txt
 * See the License for the specific language governing permissions and limitations 
 * under the License. 
 * 
 * When distributing the Covered Code, include this CDDL Header Notice in each file
 * and include the License file at identityconnectors/legal/license.txt.
 * If applicable, add the following below this CDDL Header, with the fields 
 * enclosed by brackets [] replaced by your own identifying information: 
 * "Portions Copyrighted [year] [name of copyright owner]"
 * ====================
 */
package org.identityconnectors.oracleerp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;

import org.identityconnectors.common.Assertions;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.dbcommon.SQLParam;
import org.identityconnectors.dbcommon.SQLUtil;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.objects.ObjectClass;

/**
 * @author Petr Jung
 * @version $Revision 1.0$
 * @since 1.0
 */
public class OracleERPUtil {


    /**
     * Setup logging.
     */
    static final Log log = Log.getLog(OracleERPUtil.class);

    static final String MSG = "Oracle ERP: ";    
    
    private OracleERPUtil() {
        throw new AssertionError();
    }



    // The predefined attribute names
    static final String USER_NAME = "user_name";
    static final String OWNER = "owner";
    static final String UNENCRYPT_PWD = "unencrypted_password";
    static final String SESS_NUM = "session_number";
    static final String START_DATE = "start_date";
    static final String END_DATE = "end_date";
    static final String LAST_LOGON_DATE = "last_logon_date";
    static final String DESCR = "description";
    static final String PWD_DATE = "password_date";
    static final String PWD_ACCESSES_LEFT = "password_accesses_left";
    static final String PWD_LIFE_ACCESSES = "password_lifespan_accesses";
    static final String PWD_LIFE_DAYS = "password_lifespan_days";
    static final String EMP_ID = "employee_id";
    static final String EMAIL = "email_address";
    static final String FAX = "fax";
    static final String CUST_ID = "customer_id";
    static final String SUPP_ID = "supplier_id";

    // The supported operations
    static final String CREATE_FNC = "CreateUser";
    static final String UPDATE_FNC = "UpdateUser";

    // Unrelated account attribute names
    static final String EMP_NUM = "employee_number";
    static final String PERSON_FULLNAME = "person_fullname";
    static final String NPW_NUM = "npw_number";
    static final String PERSON_ID = "person_id";    

    //Special attributes
    static final String USER_ID = "user_id";
    static final String PERSON_PARTY_ID = "person_party_id";
    static final String EXP_PWD = "expirePassword";

    // The container attributes 
    static final String RESP = "responsibilities";
    static final String RESPKEYS = "responsibilityKeys";
    static final String SEC_ATTRS = "securingAttrs";
    
    // static variable on update to set dates to local server time
    static final String SYSDATE = "sysdate";
    static final String NULL_DATE = "FND_USER_PKG.null_date";
    static final String NULL_CHAR = "FND_USER_PKG.null_char";
    static final String NULL_NUMBER = "FND_USER_PKG.null_number";
    static final String Q = "?";    


    static final String RESP_NAMES = "responsibilityNames";
    static final String USER_MENU_NAMES = "userMenuNames";
    static final String MENU_IDS = "menuIds";
    static final String USER_FUNCTION_NAMES = "userFunctionNames";
    static final String FUNCTION_IDS = "functionIds";
    static final String FORM_IDS = "formIds";
    static final String FORM_NAMES = "formNames";
    static final String FUNCTION_NAMES = "functionNames";
    static final String USER_FORM_NAMES = "userFormNames";
    static final String READ_ONLY_FORM_IDS = "readOnlyFormIds";
    static final String READ_WRITE_ONLY_FORM_IDS = "readWriteOnlyFormIds";
    static final String READ_ONLY_FORM_NAMES = "readOnlyFormNames";
    static final String READ_ONLY_FUNCTION_NAMES = "readOnlyFunctionNames";
    static final String READ_ONLY_USER_FORM_NAMES = "readOnlyUserFormNames";
    static final String READ_ONLY_FUNCTIONS_IDS = "readOnlyFunctionIds";
    static final String READ_WRITE_ONLY_FORM_NAMES = "readWriteOnlyFormNames";
    static final String READ_WRITE_ONLY_USER_FORM_NAMES = "readWriteOnlyUserFormNames";
    static final String READ_WRITE_ONLY_FUNCTION_NAMES = "readWriteOnlyFunctionNames";
    static final String READ_WRITE_ONLY_FUNCTION_IDS = "readWriteOnlyFunctionIds";

    // format flags for processing responsibilities strings, used by getResp() and getResps() methods
    static final int RESP_FMT_KEYS = 0; // return responsibilities with keys only.
    static final int RESP_FMT_NORMALIZE_DATES = 1; // return whole responsibilities with time data removed from date columns.
    static final int ORA_01403 = 1403;
    
    // Validate messages constants
    static final String MSG_NAME_BLANK = "name.blank";
    static final String MSG_PWD_BLANK = "pwd.blank";    
    static final String MSG_USER_BLANK = "user.blank";
    static final String MSG_USER_MODEL_BLANK = "user.model.blank";
    static final String MSG_USER_MODEL_NOT_FOUND="user.model.not.found";
    static final String MSG_PASSWORD_BLANK = "password.blank";
    static final String MSG_HOST_BLANK = "host.blank";
    static final String MSG_PORT_BLANK = "port.blank";
    static final String MSG_DATABASE_BLANK = "database.blank";
    static final String MSG_JDBC_DRIVER_BLANK = "jdbc.driver.blank";
    static final String MSG_JDBC_DRIVER_NOT_FOUND = "jdbc.driver.not.found";
    static final String MSG_ACCOUNT_OBJECT_CLASS_REQUIRED = "acount.object.class.required";
    static final String MSG_AUTHENTICATE_OP_NOT_SUPPORTED = "auth.op.not.supported";
    static final String MSG_AUTH_FAILED = "auth.op.failed";
    static final String MSG_INVALID_ATTRIBUTE_SET = "invalid.attribute.set";
    static final String MSG_UID_BLANK = "uid.blank";
    static final String MSG_RESULT_HANDLER_NULL = "result.handler.null";    
    
    /**
     * object class name definitions
     * responsibilities, responsibilityNames, applications, securityGroups, auditorResps
     */
    
    static final String RESPS = RESP;
    public static final ObjectClass RESP_OC = new ObjectClass(RESP);    

    static final String DIRECT_RESPS = "directResponsibilities";
    static final String DIRECT_RESP = DIRECT_RESPS;
    public static final ObjectClass DIRECT_RESP_OC = new ObjectClass(DIRECT_RESP);    

    static final String INDIRECT_RESPS = "indirectResponsibilities";
    static final String INDIRECT_RESP = INDIRECT_RESPS;
    public static final ObjectClass INDIRECT_RESP_OC = new ObjectClass(INDIRECT_RESP); 

    static final String RESP_NAME = RESP_NAMES;
    public static final ObjectClass RESP_NAMES_OC = new ObjectClass(RESP_NAMES);        
    
    static final String APPS = "applications";
    static final String APP = "application";
    public static final ObjectClass APP_OC = new ObjectClass(APP);    
    
    static final String SEC_GROUPS = "securityGroups";
    static final String SEC_GROUP = "securityGroup";
    public static final ObjectClass SEC_GROUP_OC = new ObjectClass(SEC_GROUP);    
    
    static final String PATTERN = "searchPattern";
   

    // Auditor Data Object
    static final String AUDITOR_RESPS = "auditorResps";
    static final String AUDITOR_RESP = "auditorResp";
    /**
     * Auditor responsibilities has menus, forms, functions, 
     * Auditor attributes: activeRespsOnly
     */
    public static final ObjectClass AUDITOR_RESP_OC =  new ObjectClass(AUDITOR_RESP);

    static final String MENUS = "menus";
    static final String MENU = "menu";
    /**
     * Menu has forms
     * Menu attributes: id, name, userMenu
     */
    public static final ObjectClass MENU_OC =  new ObjectClass(MENU);

    static final String FORMS = "forms";
    static final String FORM = "form";
    /**
     * Form has functions
     * Form attributes: id, name, writable, userForm 
     */
    public static final ObjectClass FORM_OC =  new ObjectClass(FORM);
    
    static final String FUNCTIONS = "functions";
    static final String FUNCTION = "function";
    /**
     * Function
     * Function attributes: id, name, writeble, userFunction
     */
    public static final ObjectClass FUNCTION_OC =  new ObjectClass(FUNCTION);
    
    
    static final String ACTIVE_RESPS_ONLY = "activeRespsOnly";
    static final String SOB_NAME = "setOfBooksName";
    static final String SOB_ID = "setOfBooksId";
    static final String OU_NAME = "organizationalUnitName";
    static final String OU_ID = "organizationalUnitId";
    
    static final String DEFAULT_USER_NAME = "APPL"; // Default user name    
    
    
    
    // new version 11.5.10 does not use responsibility table, it uses 2 new views
    static final String RESPS_TABLE = "fnd_user_resp_groups";    
    static final String RESPS_DIRECT_VIEW = "fnd_user_resp_groups_direct";
    static final String RESPS_INDIRECT_VIEW = "fnd_user_resp_groups_indirect";
    static final String RESPS_ALL_VIEW = "fnd_user_resp_groups_all";    
    
    

    /**
     * 
     */
    public static final int ORACLE_TIMEOUT = 1800;

    static final String CURLY_BEGIN = "{ ";
    static final String CURLY_END = " }";    

    /**
     * @param userName
     * @return The UserId string value
     * @throws SQLException
     */
    static String getUserId(OracleERPConnector con, String userName) {
        final OracleERPConnection conn = con.getConn();

        String ret = null;
            log.info("get UserId for {0}", userName);        
            final String sql = "select "+USER_ID+" from "+con.app()+"FND_USER where upper(user_name) = ?";
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                ps = conn.prepareStatement(sql);
                ps.setString(1, userName.toUpperCase());
                rs = ps.executeQuery();
                if (rs != null) {
                    if ( rs.next()) {
                        ret = rs.getString(1);
                    }
                    // rs closed in finally below
                }
            } catch (SQLException e) {
                log.error(e, sql);
                throw ConnectorException.wrap(e);
            } finally {
                SQLUtil.closeQuietly(ps);
                SQLUtil.closeQuietly(rs);
            }
            
            // pstmt closed in finally below
            Assertions.nullCheck(ret, "userId");       
        log.info("UserId is :{0}, for :{0}", ret, userName);
        return ret;
    }    

    
    /**
     * Get The personId from employeNumber or NPW number
     * @param empNum employeNumber or null 
     * @param npwNum mpw number or null
     * @return
     */
    static String getPersonId(OracleERPConnector con, final Integer empNum, final Integer npwNum) {

        String ret = null;
        String columnName = "";
        int number;
        if (empNum != null) {
            columnName = EMP_NUM;
            number = empNum;
        } else if ( npwNum != null) {
            columnName = NPW_NUM;
            number = npwNum;
        } else {
            return null;
        }
         
        final String sql = "select "+PERSON_ID+" from "+con.app()+"PER_PEOPLE_F where "+columnName+" = ?";
        ResultSet rs = null; // SQL query on person_id
        PreparedStatement ps = null; // statement that generates the query
        log.ok(sql);
        try {
            ps = con.getConn().prepareStatement(sql);
            ps.setInt(1, number);
            ps.setQueryTimeout(OracleERPUtil.ORACLE_TIMEOUT);
            rs = ps.executeQuery();
            if (rs.next()) {
                ret = rs.getString(1);
            }
        } catch (SQLException e) {
            log.error(e, sql);
            throw ConnectorException.wrap(e);
        } finally {
            SQLUtil.closeQuietly(rs);
            rs = null;
            SQLUtil.closeQuietly(ps);
            ps = null;
        }
        Assertions.nullCheck(ret, PERSON_ID);
        return ret;
    }
    
    
    
    /**
     * @param dateString
     * @return the timestamp
     */
    public static Timestamp stringToTimestamp(final String dateString) {
        Timestamp tms;
        try {
            tms = Timestamp.valueOf(dateString);
        } catch (IllegalArgumentException expected) {
            tms = new Timestamp(new Long(dateString));
        }
        return tms;
    }
    
    /**
     * @param sqlSelect 
     * @param whereAnd
     * @return
     */
    public static String whereAnd(String sqlSelect, String whereAnd) {
        int iofw = sqlSelect.toUpperCase().indexOf("WHERE");
        return (iofw == -1) ? sqlSelect + " WHERE " + whereAnd : sqlSelect.substring(0, iofw) + "WHERE ("+sqlSelect.substring(iofw + 5) +") AND ( " + whereAnd + " )";
    }
    
    /**
     * Only accounts where clause
     */
    public static final String ACTIVE_ACCOUNTS_ONLY_WHERE_CLAUSE =
        "(START_DATE - SYSDATE <= 0) AND ((END_DATE IS NULL) OR (END_DATE - SYSDATE > 0))";
    

    /**
     * Get a string from a result set, trimming trailing blanks.
     * 
     * @param result
     * @param col
     * @return the resulted string
     * @throws java.sql.SQLException
     */
    public static String getColumn(ResultSet result, int col) throws java.sql.SQLException {
        String s = result.getString(col);
        if (s != null)
            s = s.trim();
        return s;
    }
    


    /**
     * Takes a date in string format and returns a normalized version of the date i.e., removing time data. null dates
     * are returned as string "null".
     * @param strDate string date
     * @return normalized date
     */
    public static String normalizeStrDate(String strDate) {
        String retDate = strDate;
        if ((strDate == null) || strDate.equalsIgnoreCase("null")) {
            retDate = "null";
        } else if (strDate.length() == 10) {
            retDate = strDate;
        } else if (strDate.length() > 10) {
            retDate = strDate.substring(0, 10); // truncate to only date i.e.,yyyy-mm-dd 
        }
        return retDate;
    } // normalizeStrDate()
    
    /**
     * 
     * @return
     */
    public static java.sql.Date getCurrentDate() {
        Calendar rightNow = Calendar.getInstance();
        java.util.Date utilDate = rightNow.getTime();
        return new java.sql.Date(utilDate.getTime());
    }
    

    /**
     * Add a quoted string to a SQL statement we're building in a buffer. If the attribute might be an integer, then
     * call addAttributeValue() instead, which factors in the syntax of the attribute when determining whether or not to
     * quote the value.
     */
    public static void addQuoted(StringBuffer b, String s) {
        b.append("'");
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (ch == '\'')
                    b.append("''");
                else
                    b.append(ch);
            }
        }
        b.append("'");
    }

    /**
     * @param userParamMap map
     * @param paramName param Name
     * @return the String parameter
     */
    public static String getStringParamValue(final Map<String, SQLParam> userParamMap, final String paramName) {
        final SQLParam param = userParamMap.get(paramName);
        if (param == null)
            return null;
        final String ret = (String) param.getValue();
        return ret;
    }
        
}
