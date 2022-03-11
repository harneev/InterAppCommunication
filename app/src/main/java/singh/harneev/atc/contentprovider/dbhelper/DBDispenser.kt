package singh.harneev.atc.contentprovider.dbhelper

import singh.harneev.atcsdk.school.School
import singh.harneev.atc.contentprovider.CPWrapper
import singh.harneev.atc.contentprovider.table.TableSchool
import singh.harneev.atcsdk.util.toSchool
import java.util.ArrayList

/**
 * Created by harneev on 22/06/17.
 */
object DBDispenser {

    @JvmStatic
    val schoolList: List<School>
        get() {
            val schoolList: MutableList<School> = ArrayList()

            CPWrapper.query(TableSchool.TABLE_NAME, null, null, null, null).use { cursor ->
                while (cursor.moveToNext()) {
                    schoolList.add(cursor.toSchool())
                }
            }

            return schoolList
        }
}