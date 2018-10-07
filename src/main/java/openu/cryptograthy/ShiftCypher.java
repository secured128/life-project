package openu.cryptograthy;

import com.sun.tools.javac.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class ShiftCypher implements Cryptosystem {

    static int Z;

    static Map char2code = new HashMap<Character, Integer>();
    static Map code2char = new HashMap<Integer, Character>();

    static {
        char2code.put('a', 0);
        char2code.put('b', 1);
        char2code.put('c', 2);
        char2code.put('d', 3);
        char2code.put('e', 4);
        char2code.put('f', 5);
        char2code.put('g', 6);
        char2code.put('h', 7);
        char2code.put('i', 8);
        char2code.put('j', 9);
        char2code.put('k', 10);
        char2code.put('l', 11);
        char2code.put('m', 12);
        char2code.put('n', 13);
        char2code.put('o', 14);
        char2code.put('p', 15);
        char2code.put('q', 16);
        char2code.put('r', 17);
        char2code.put('s', 18);
        char2code.put('t', 19);
        char2code.put('u', 20);
        char2code.put('v', 21);
        char2code.put('w', 22);
        char2code.put('x', 23);
        char2code.put('y', 24);
        char2code.put('z', 25);

        code2char.put(0, 'a');
        code2char.put(1, 'b');
        code2char.put(2, 'c');
        code2char.put(3, 'd');
        code2char.put(4, 'e');
        code2char.put(5, 'f');
        code2char.put(6, 'g');
        code2char.put(7, 'h');
        code2char.put(8, 'i');
        code2char.put(9, 'j');
        code2char.put(10, 'k');
        code2char.put(11, 'l');
        code2char.put(12, 'm');
        code2char.put(13, 'n');
        code2char.put(14, 'o');
        code2char.put(15, 'p');
        code2char.put(16, 'q');
        code2char.put(17, 'r');
        code2char.put(18, 's');
        code2char.put(19, 't');
        code2char.put(20, 'u');
        code2char.put(21, 'v');
        code2char.put(22, 'w');
        code2char.put(23, 'x');
        code2char.put(24, 'y');
        code2char.put(25, 'z');

        Assert.check(char2code.size() > 0);
        Assert.check(char2code.size() == code2char.size());
        Z = code2char.size();

    }

    public String encrypt(String input, String key) {

        String inputLowcased = input.toLowerCase();

        int shiftPositions = Integer.valueOf(key);

        char[] output = new char[inputLowcased.length()];
        int i = 0;
        for (char ch : inputLowcased.toCharArray()) {
            int inputCode = Integer.class.cast(char2code.get(ch));
            int encryptedValue = (inputCode + shiftPositions) % Z;
            char outputChar = Character.class.cast(code2char.get(encryptedValue));
            output[i] = outputChar;
            i++;
        }
        return new String(output).toUpperCase();
    }

    public String decrypt(String input, String key) {

        String inputLowcased = input.toLowerCase();

        int shiftPositions = Integer.valueOf(key);
        char[] output = new char[input.length()];
        int i = 0;
        for (char ch : inputLowcased.toCharArray()) {
            int inputCode = Integer.class.cast(char2code.get(ch));
            int encryptedValue = (inputCode - shiftPositions + Z) % Z;
            char outputChar = Character.class.cast(code2char.get(encryptedValue));
            output[i] = outputChar;
            i++;
        }
        return new String(output).toLowerCase();
    }

}
