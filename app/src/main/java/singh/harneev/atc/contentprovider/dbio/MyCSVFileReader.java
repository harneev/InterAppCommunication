package singh.harneev.atc.contentprovider.dbio;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import singh.harneev.atc.app.ATCApp;

/**
 * Created by harneev on 7/29/15.
 */
public class MyCSVFileReader {

    public static void openDialogToReadCSV(final Activity activity, final Context context) {

        ArrayList<File> mFiles = new ArrayList<>();

        try {
            InputStream inputStream = ATCApp.getInstance().getAssets().open("listOfSchools.csv");

            mFiles.add(createFileFromInputStream(inputStream, ATCApp.getInstance().getCacheDir() + "/listOfSchools.csv"));

            new ImportCVSToSQLiteDataBase(context, activity, mFiles).execute();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File createFileFromInputStream(InputStream inputStream, String filepath) {

        try{
            File file = new File(filepath);

            if (file.getParentFile() != null
                    && !file.getParentFile().exists())
                file.getParentFile().mkdirs();

            OutputStream outputStream = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int length = 0;

            while((length=inputStream.read(buffer)) > 0) {
                outputStream.write(buffer,0,length);
            }

            outputStream.close();
            inputStream.close();

            return file;
        }catch (IOException e) {
            //Logging exception
        }

        return null;
    }

}
