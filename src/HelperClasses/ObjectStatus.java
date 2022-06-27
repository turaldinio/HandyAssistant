package HelperClasses;

public class ObjectStatus {
    public static boolean isWaitResponse() {
        return waitResponse;
    }

    public static void setWaitResponse(boolean waitResponse) {
        ObjectStatus.waitResponse = waitResponse;
    }

    private static boolean waitResponse;

}
