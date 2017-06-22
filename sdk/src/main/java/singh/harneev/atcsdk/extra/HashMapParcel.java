package singh.harneev.atcsdk.extra;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

/**
 * Created by harneev on 24/09/15.
 */
public class HashMapParcel implements Parcelable {

    private HashMap<String, String> map;
    private Cursor cursor;

    /**
     * Getter / Setter Methods
     */
    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public String get(String key) {
        return map.get(key);
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }

    public void put(String key, String value) {
        map.put(key, value);
    }

    public Cursor getCursor() {
        return cursor;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(map.size());
        for (String s : map.keySet()) {
            dest.writeString(s);
            dest.writeString(map.get(s));
        }
    }

    public void readFromParcel(Parcel in) {
        int count = in.readInt();
        for (int i = 0; i < count; i++) {
            map.put(in.readString(), in.readString());
        }
    }

    public static final Creator<HashMapParcel> CREATOR = new Parcelable.Creator<HashMapParcel>() {
        @Override
        public HashMapParcel createFromParcel(Parcel in) {
            return new HashMapParcel(in);
        }

        @Override
        public HashMapParcel[] newArray(int size) {
            return new HashMapParcel[size];
        }
    };

    public HashMapParcel() {
        map = new HashMap<>();
    }

    public HashMapParcel(Parcel in) {
        map = new HashMap<>();
        readFromParcel(in);
    }
}
