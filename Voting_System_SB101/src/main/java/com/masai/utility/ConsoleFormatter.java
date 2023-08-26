package com.masai.utility;

public class ConsoleFormatter {

    // ANSI escape codes for text colors
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    // ... Define more colors as needed

    // ANSI escape code to reset formatting
    public static final String RESET = "\u001B[0m";

    // Method to apply a colored border to text
    public static String applyBorder(String text, String color) {
        return color + "╔════════════════════════════════════╗\n" +
               "  " + text + "                        \n" +
               "╚════════════════════════════════════╝" + RESET;
    }
}
