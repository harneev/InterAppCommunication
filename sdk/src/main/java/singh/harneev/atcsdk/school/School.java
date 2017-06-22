package singh.harneev.atcsdk.school;

import android.os.Parcel;
import android.os.Parcelable;

import singh.harneev.atcsdk.util.Check;

/**
 * Created by harneev on 22/06/17.
 */

public class School implements Parcelable{

    private int schoolId;
    private String schoolName;
    private String schoolType;

    private String feeCurrency;
    private String schoolAdd;
    private String schoolAddCity;
    private String schoolAddCountry;

    private String schoolEmail;
    private String schoolPhone;

    private String principalName;
    private String principalPhone;

    private String openingTime;
    private String closingTime;

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(schoolId);
        parcel.writeString(schoolName);
        parcel.writeString(schoolType);

        parcel.writeString(feeCurrency);
        parcel.writeString(schoolAdd);
        parcel.writeString(schoolAddCity);
        parcel.writeString(schoolAddCountry);

        parcel.writeString(schoolEmail);
        parcel.writeString(schoolPhone);

        parcel.writeString(principalName);
        parcel.writeString(principalPhone);

        parcel.writeString(openingTime);
        parcel.writeString(closingTime);
    }

    public static final Creator<School> CREATOR = new Parcelable.Creator<School>() {
        @Override
        public School createFromParcel(Parcel in) {
            return new School(in);
        }

        @Override
        public School[] newArray(int size) {
            return new School[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    private School(Parcel in) {

        this.schoolId = in.readInt();
        this.schoolName = in.readString();
        this.schoolType = in.readString();

        this.feeCurrency = in.readString();
        this.schoolAdd = in.readString();
        this.schoolAddCity = in.readString();
        this.schoolAddCountry = in.readString();

        this.schoolEmail = in.readString();
        this.schoolPhone = in.readString();

        this.principalName = in.readString();
        this.principalPhone = in.readString();

        this.openingTime = in.readString();
        this.closingTime = in.readString();
    }

    public School() {

        this.schoolId = -1;
        this.schoolName = "";
        this.schoolType = "";

        this.feeCurrency = "";
        this.schoolAdd = "";
        this.schoolAddCity = "";
        this.schoolAddCountry = "";

        this.schoolEmail = "";
        this.schoolPhone = "";

        this.principalName = "";
        this.principalPhone = "";

        this.openingTime = "";
        this.closingTime = "";
    }

    public void setField(String field, String value) {

        switch (field) {

            case "schoolId":
                setSchoolId(Check.parseInt(value));
                break;

            case "schoolName":
                setSchoolName(value);
                break;

            case "schoolType":
                setSchoolType(value);
                break;

            case "feeCurrency":
                setFeeCurrency(value);
                break;

            case "schoolAdd":
                setSchoolAdd(value);
                break;

            case "schoolAddCity":
                setSchoolAddCity(value);
                break;

            case "schoolAddCountry":
                setSchoolAddCountry(value);
                break;

            case "schoolEmail":
                setSchoolEmail(value);
                break;

            case "schoolPhone":
                setSchoolPhone(value);
                break;

            case "principalName":
                setPrincipalName(value);
                break;

            case "principalPhone":
                setPrincipalPhone(value);
                break;

            case "openingTime":
                setOpeningTime(value);
                break;

            case "closingTime":
                setClosingTime(value);
                break;
        }
    }

    /**
     * GETTER
     */

    public int getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public String getFeeCurrency() {
        return feeCurrency;
    }

    public String getSchoolAdd() {
        return schoolAdd;
    }

    public String getSchoolAddCity() {
        return schoolAddCity;
    }

    public String getSchoolAddCountry() {
        return schoolAddCountry;
    }

    public String getSchoolEmail() {
        return schoolEmail;
    }

    public String getSchoolPhone() {
        return schoolPhone;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    /**
     * SETTER
     */

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public void setFeeCurrency(String feeCurrency) {
        this.feeCurrency = feeCurrency;
    }

    public void setSchoolAdd(String schoolAdd) {
        this.schoolAdd = schoolAdd;
    }

    public void setSchoolAddCity(String schoolAddCity) {
        this.schoolAddCity = schoolAddCity;
    }

    public void setSchoolAddCountry(String schoolAddCountry) {
        this.schoolAddCountry = schoolAddCountry;
    }

    public void setSchoolEmail(String schoolEmail) {
        this.schoolEmail = schoolEmail;
    }

    public void setSchoolPhone(String schoolPhone) {
        this.schoolPhone = schoolPhone;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }
}
