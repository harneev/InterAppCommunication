package singh.harneev.atc.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import singh.harneev.atc.utils.Constant;
import singh.harneev.atcsdk.extra.HashMapParcel;

/**
 * Created by harneev on 22/06/17.
 */

public class HContentProvider extends ContentProvider {

    private static final String TAG = HContentProvider.class.getSimpleName();

    // Final Content Provider URI
    public static final Uri CONTENT_URI = Uri.parse(Constant.URL);

    private SQLiteDatabase mDB;

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor = null;

        try {

            String tableName = getTableName(uri);

            SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
            qb.setTables(tableName);

            cursor = qb.query(mDB, projection, selection, selectionArgs, null, null, sortOrder);

            if (getContext() != null)
                cursor.setNotificationUri(getContext().getContentResolver(), uri);

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        String tableName = getTableName(uri);

        long rowID = 0;
        try {

            rowID = mDB.insert(tableName, "", contentValues);
        } catch (Exception e) {

            Log.w(TAG, e);
        }

        if (rowID > 0) {

            Uri recordUri = ContentUris.withAppendedId(CONTENT_URI, rowID);

            if (getContext() != null)
                getContext().getContentResolver().notifyChange(recordUri, null);

            return recordUri;
        }

        return null;
    }

    @Override
    public Bundle call(String method, String argument, Bundle extras) {

        Bundle bundle = new Bundle();
        Cursor cursor = null;

        switch (method) {

            case Constant.METHOD_EXECUTE_SQL:

                try {
                    cursor = mDB.rawQuery(argument, null);

                    if (cursor != null) {

                        HashMapParcel parcel = new HashMapParcel();
                        parcel.setCursor(cursor);

                        bundle.putParcelable(Constant.KEY_NAME_PARCEL, parcel);
                    }
                } catch (Exception e) {
                    Log.w(TAG, e);
                }

                break;

            case Constant.METHOD_GET_TABLE_COLUMNS:

                StringBuilder responseStr = new StringBuilder();

                String sql_query = "PRAGMA table_info(" + argument + ");";

                try {

                    cursor = mDB.rawQuery(sql_query, null);
                    responseStr.setLength(0);

                    if (cursor != null && cursor.getCount() > 0) {

                        while (cursor.moveToNext()) {
                            responseStr.append(cursor.getString(1));
                            responseStr.append(";");
                        }
                    }

                    bundle.putString(Constant.VALUE_RESPONSE, responseStr.toString());
                } catch (Exception e) {
                    // do nothing
                } finally {

                    if (cursor != null && !cursor.isClosed())
                        cursor.close();
                }

                break;
        }

        return bundle;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    private String getTableName(Uri uri) {

        String value = uri.getPath();
        value = value.replace("/", ""); //we need to remove '/'

        return value;
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        DB dbHelper = new DB(context);

        mDB = dbHelper.getWritableDatabase();
        if (mDB != null) {
            return true;
        }
        return false;
    }
}
