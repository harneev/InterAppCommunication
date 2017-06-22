package singh.harneev.atcsdk.util;

/**
 * Created by harneev on 22/06/17.
 */

public class Check {

    public static int parseInt(String value) {
        int reply;
        try {
            reply = Integer.parseInt(value);
        } catch (Exception e) {
            reply = -1;
        }

        return reply;
    }
}
