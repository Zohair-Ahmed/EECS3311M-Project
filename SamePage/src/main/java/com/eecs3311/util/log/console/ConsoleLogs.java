package com.eecs3311.util.log.console;

/**
 * Logs of common messages
 */
public class ConsoleLogs {
    // SUCCESS messages (GREEN)
    public static final String SUCCESSFUL = ConsoleColours.GREEN + "SUCCESSFUL" + ConsoleColours.RESET;
    public static String SUCCESSFUL(String successString) {
        return ConsoleColours.GREEN + successString + ConsoleColours.RESET;
    }

    // ERROR messages (RED)
    public static final String ERROR = ConsoleColours.RED + "ERROR" + ConsoleColours.RESET;
    public static String ERROR(String errString) {
        return ConsoleColours.RED + errString + ConsoleColours.RESET;
    }

    // DATABASE messages (YELLOW_BOLD)
    public static String DATABASE(String dbString) {
        return ConsoleColours.YELLOW_BOLD + dbString + ConsoleColours.RESET;
    }

    // USER messages (BLUE_BOLD)
    public static String USER(String userString) {
        return ConsoleColours.BLUE_BOLD + userString + ConsoleColours.RESET;
    }

    // ACTION messages (WHITE_BOLD) {
    public static String ACTION(String actionString) {
        return ConsoleColours.WHITE_BOLD+ actionString + ConsoleColours.RESET;
    }
}
