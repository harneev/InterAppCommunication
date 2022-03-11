package singh.harneev.atcsdk.util

import android.database.Cursor
import singh.harneev.atcsdk.school.School

internal inline fun <reified T> Cursor.getValue(column: String): T {
    val columnIndex = getColumnIndex(column)
    return when (T::class) {
        Int::class -> getInt(columnIndex)
        else -> getString(columnIndex)
    }.let { it as T }
}

fun Cursor.toSchool(): School = School(
    schoolId = getValue("schoolId"),
    schoolName = getValue("schoolName"),
    schoolType = getValue("schoolType"),
    feeCurrency = getValue("feeCurrency"),
    schoolAdd = getValue("schoolAdd"),
    schoolAddCity = getValue("schoolAddCity"),
    schoolAddCountry = getValue("schoolAddCountry"),
    schoolEmail = getValue("schoolEmail"),
    schoolPhone = getValue("schoolPhone"),
    principalName = getValue("principalName"),
    principalPhone = getValue("principalPhone"),
    openingTime = getValue("openingTime"),
    closingTime = getValue("closingTime")
)