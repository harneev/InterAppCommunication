package singh.harneev.atc.utils;

import singh.harneev.atc.BuildConfig;

/**
 * Created by harneev on 22/06/17.
 */

public class Constant {

    public static final String DATABASE_NAME = "HarneevDatabase.db";
    public static final int DATABASE_VERSION = BuildConfig.VERSION_CODE;

    /**
     * Content Provider variables
     */
    public static final String AUTHORITY = "singh.harneev.atc.contentprovider.HContentProvider";
    public static final String URL = "content://" + AUTHORITY + "";

    public static final String METHOD_EXECUTE_SQL = "execute_sql";
    public static final String METHOD_RAW_SQL = "raw_query";
    public static final String METHOD_GET_TABLE_COLUMNS = "get_table_columns";

    // Hash Map key name
    public static final String KEY_NAME_PARCEL = "parcel";
    public static final String VALUE_RESPONSE = "response"; // 0 or 1
}
