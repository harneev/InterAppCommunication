package singh.harneev.atc.app.activity;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import singh.harneev.atc.R;
import singh.harneev.atc.contentprovider.CPWrapper;
import singh.harneev.atc.contentprovider.dbhelper.AndroidDatabaseManager;
import singh.harneev.atc.contentprovider.dbhelper.DBHelper;
import singh.harneev.atc.contentprovider.dbio.MyCSVFileReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.inflate_db:

                MyCSVFileReader.openDialogToReadCSV(this, MainActivity.this);
                break;

            case R.id.show_db:

                startActivity(new Intent(MainActivity.this, AndroidDatabaseManager.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
