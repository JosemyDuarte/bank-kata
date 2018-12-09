package io.josemyduarte.bank;

public class ConsoleSystemOut implements Console {
    private ConsoleSystemOut() {
    }

    public static ConsoleSystemOut createConsoleSystemOut() {
        return new ConsoleSystemOut();
    }

    @Override
    public void printLine(final String text) {
        System.out.println(text);
    }
}
