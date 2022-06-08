import javax.swing.JOptionPane;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        LibrarySystem L = new LibrarySystem();
        L.initialization();
        L.start();
    }
}
