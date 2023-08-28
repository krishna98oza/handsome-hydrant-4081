package com.masai.utility;

public class ConsoleFormatter {

    // ANSI escape codes for text colors
	public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String MAGENTA = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    // ... Define more colors as needed

    // ANSI escape code to reset formatting
    public static final String RESET = "\u001B[0m";

    // Method to apply a colored border to text
    public static String applyBorderTop(String text, String color) {
        return color + "╔══════════════════════════════════╗"+ RESET;
    }
    public static String applyBorderBottom(String text, String color) {
        return color + "╚══════════════════════════════════╝" + RESET;
    }
    public static String applyBorderLine(String text, String color) {
        return color +"║" + text + "║"+ RESET;
    }
    public static String applyBorderLineLeft(String text, String color) {
        return color +"║" + text + RESET;
    }
    
    public static String applyBorderColor(String text, String color) {
        return color + text+ RESET;
    }
}
