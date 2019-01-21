package life.ascii;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 256; i++) {
            try {
                System.out.println(i + " : '" + String.valueOf(Character.toChars(i)) + "'");
            } catch (Exception th) {

            }
        }
    }
}
