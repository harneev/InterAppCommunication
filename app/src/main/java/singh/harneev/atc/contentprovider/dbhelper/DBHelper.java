package singh.harneev.atc.contentprovider.dbhelper;

import singh.harneev.atc.R;
import singh.harneev.atc.app.ATCApp;
import singh.harneev.atc.contentprovider.CPWrapper;

/**
 * Created by harneev on 22/06/17.
 */

public class DBHelper {

    /**
     * Create all Tables in DB from arrays.xml -> create_table_queries
     */
    public static void CreateTables() {

        String[] createTableStrings = ATCApp.getInstance().getResources().getStringArray(R.array.create_table_queries);

        int i = 0;

        // Create all tables in provider
        for (i = 0; i < createTableStrings.length; i++) {
            CPWrapper.createTable(createTableStrings[i]);
        }
    }
}
