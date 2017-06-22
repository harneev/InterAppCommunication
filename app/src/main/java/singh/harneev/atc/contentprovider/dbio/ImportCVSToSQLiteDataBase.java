package singh.harneev.atc.contentprovider.dbio;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import singh.harneev.atc.contentprovider.CPWrapper;

public class ImportCVSToSQLiteDataBase extends AsyncTask<String, String, String> {

    private static final String TAG = "ImportCVSToSQL";

    private Activity activity;
    private Context context;
    private List<File> files = null;
    private ProgressDialog dialog;

    public ImportCVSToSQLiteDataBase(Context context, Activity activity, List<File> files) {
        this.context = context;
        this.activity = activity;
        this.files = files;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Importing data to local database");
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        dialog.setIcon(android.R.drawable.btn_plus);
        dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        for (File file : files) {
            try {
                storeInDB(file);
            } catch (Exception e) {
                Log.w(TAG, e);
            }
        }

        return "";
    }

    private void storeInDB(File file) throws Exception {
        String tableName = "";
        String fileName = file.getName();

        if (fileName.indexOf(".") > 0)
            tableName = fileName.substring(0, fileName.lastIndexOf("."));

        if (!tableName.isEmpty()) {
            List<String> columnNames = CPWrapper.getTableColumns(tableName);

            // Read from csv file
            CSVReader reader = new CSVReader(new FileReader(file));

            // nextLine[] is an array of values from the line
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

                // Generate content values for table
                ContentValues values = new ContentValues();
                int i = 0;
                for (i = 0; i < nextLine.length; i++) {
                    values.put(columnNames.get(i), nextLine[i]);
                }

                // insert in db
                CPWrapper.insert(tableName, values);
            }
        }
    }

    protected void onPostExecute(String data) {

        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }


}