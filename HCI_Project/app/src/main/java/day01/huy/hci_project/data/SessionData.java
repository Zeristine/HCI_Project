package day01.huy.hci_project.data;

public class SessionData {
    private static String username = "";

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionData.username = username;
    }
}
