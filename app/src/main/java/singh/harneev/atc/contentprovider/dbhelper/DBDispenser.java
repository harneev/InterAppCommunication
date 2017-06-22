package singh.harneev.atc.contentprovider.dbhelper;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import singh.harneev.atc.contentprovider.CPWrapper;
import singh.harneev.atc.contentprovider.table.TableSchool;
import singh.harneev.atcsdk.school.School;

/**
 * Created by harneev on 22/06/17.
 */

public class DBDispenser {

    private static final String TAG = DBDispenser.class.getSimpleName();

    public static List<School> getSchoolList() {

        Cursor cursor = null;
        List<School> schoolList = new ArrayList<>();

        try {
            cursor = CPWrapper.query(TableSchool.TABLE_NAME, null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {

                // Get columns of voucher table
                List<String> tableColumns = CPWrapper.getTableColumns(TableSchool.TABLE_NAME);

                while (cursor.moveToNext()) {

                    School school = new School();

                    // insert values in object
                    for (String column : tableColumns) {

                        if (cursor.getColumnIndex(column) != -1)
                            school.setField(column, cursor.getString(cursor.getColumnIndex(column)));

                    }

                    schoolList.add(school);
                }
            }
        } catch (Exception e) {
            Log.w(TAG, e);
        } finally {

            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }

        return schoolList;
    }
}
