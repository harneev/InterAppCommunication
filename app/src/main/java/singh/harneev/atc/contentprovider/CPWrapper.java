package singh.harneev.atc.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import singh.harneev.atc.app.ATCApp;
import singh.harneev.atc.utils.Constant;

/**
 * Created by harneev on 22/06/17.
 */

public class CPWrapper {

    public static void createTable(String sql) {

        ATCApp.getInstance().getContentResolver().call(HContentProvider.CONTENT_URI, Constant.METHOD_EXECUTE_SQL,
                sql, null);
    }

    /**
     * Insert values in respective Table
     * Check if field is already present or not in table
     *
     * @param tableName
     * @param values
     * @return
     */
    public static Boolean insert(String tableName, ContentValues values) {

        if (ATCApp.getInstance().getContentResolver().insert(Uri.parse(Constant.URL + "/" + tableName), values) == null)
            return false;
        else
            return true;
    }

    /**
     * Query Content Provider for a respective Table
     *
     * @param tableName     - name of table
     * @param projection    - DB column name array
     * @param selection     - WHERE clause of DB
     * @param selectionArgs - selection arguments
     * @param sortOrder     - result is sorted as per this column
     * @return
     * @throws Exception
     */
    public static Cursor query(String tableName, String[] projection, String selection,
                               String[] selectionArgs, String sortOrder) {

        Uri uri = Uri.parse(Constant.URL + "/" + tableName);

        return ATCApp.getInstance().getContentResolver().query(uri, projection, selection, selectionArgs, sortOrder);
    }

    public static List<String> getTableColumns(String tableName) {

        List<String> tableColumn = new ArrayList<>();

        Bundle bundleResponse = ATCApp.getInstance().getContentResolver().call(Uri.parse(Constant.URL),
                Constant.METHOD_GET_TABLE_COLUMNS, tableName, null);

        if (bundleResponse != null
                && bundleResponse.getString(Constant.VALUE_RESPONSE) != null)
            tableColumn.addAll(Arrays.asList(bundleResponse.getString(Constant.VALUE_RESPONSE).split(";")));

        return tableColumn;
    }
}
