package singh.harneev.atcsdk.school

import android.os.Parcelable
import android.os.Parcel
import kotlinx.parcelize.Parcelize
import singh.harneev.atcsdk.school.School
import singh.harneev.atcsdk.util.Check

/**
 * Created by harneev on 22/06/17.
 */
@Parcelize
data class School(
    var schoolId: Int,
    var schoolName: String?,
    var schoolType: String?,
    var feeCurrency: String?,
    var schoolAdd: String?,
    var schoolAddCity: String?,
    var schoolAddCountry: String?,
    var schoolEmail: String?,
    var schoolPhone: String?,
    var principalName: String?,
    var principalPhone: String?,
    var openingTime: String?,
    var closingTime: String?
) : Parcelable