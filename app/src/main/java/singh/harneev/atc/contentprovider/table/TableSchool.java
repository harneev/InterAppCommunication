package singh.harneev.atc.contentprovider.table;

/**
 * Created by harneev on 22/06/17.
 */

public class TableSchool {

    public static final String TABLE_NAME = "listOfSchools";

    public static final String COLUMN_SCHOOL_ID = "schoolId";

    public static final String COLUMN_SCHOOL_NAME = "schoolName";
    public static final String COLUMN_SCHOOL_TYPE = "schoolType";
    public static final String COLUMN_FEE_CURRENCY = "feeCurrency";
    public static final String COLUMN_SCHOOL_ADD = "schoolAdd";
    public static final String COLUMN_SCHOOL_ADD_CITY = "schoolAddCity";
    public static final String COLUMN_SCHOOL_ADD_COUNTRY = "schoolAddCountry";
    public static final String COLUMN_SCHOOL_EMAIL = "schoolEmail";
    public static final String COLUMN_SCHOOL_PHONE = "schoolPhone";
    public static final String COLUMN_PRINCIPAL_NAME = "principalName";
    public static final String COLUMN_PRINCIPAL_PHONE = "principalPhone";
    public static final String COLUMN_OPENING_TIME = "openingTime";
    public static final String COLUMN_CLOSING_TIME = "closingTime";
    public static final String COLUMN_IS_ACTIVE = "isActive";

    // version v1.0.3
    public static final String COLUMN_TAGS_STRING = "tags_string";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + "("
            + COLUMN_SCHOOL_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_SCHOOL_NAME + " VARCHAR, "
            + COLUMN_SCHOOL_TYPE + " VARCHAR, "
            + COLUMN_FEE_CURRENCY + " VARCHAR, "
            + COLUMN_SCHOOL_ADD + " VARCHAR, "
            + COLUMN_SCHOOL_ADD_CITY + " VARCHAR, "
            + COLUMN_SCHOOL_ADD_COUNTRY + " VARCHAR, "
            + COLUMN_SCHOOL_EMAIL + " VARCHAR, "
            + COLUMN_SCHOOL_PHONE + " VARCHAR, "
            + COLUMN_PRINCIPAL_NAME + " VARCHAR, "
            + COLUMN_PRINCIPAL_PHONE + " VARCHAR, "
            + COLUMN_OPENING_TIME + " VARCHAR, "
            + COLUMN_CLOSING_TIME + " DATETIME, "
            + COLUMN_IS_ACTIVE + " VARCHAR)";
}
