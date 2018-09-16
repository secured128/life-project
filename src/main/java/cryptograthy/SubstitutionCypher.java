package cryptograthy;

import com.sun.tools.javac.util.Assert;

import java.util.HashMap;
import java.util.Map;

public class SubstitutionCypher implements Cryptosystem {

    static int Z;

    static Map char2code = new HashMap<Character, Character>();
    static Map code2char = new HashMap<Character, Character>();

    static {
        char2code.put('a', 'K');
        char2code.put('b', 'J');
        char2code.put('c', 'E');
        char2code.put('d', 'O');
        char2code.put('e', 'N');
        char2code.put('f', 'I');
        char2code.put('g', 'Z');
        char2code.put('h', 'Q');
        char2code.put('i', 'R');
        char2code.put('j', 'W');
        char2code.put('k', 'S');
        char2code.put('l', 'T');
        char2code.put('m', 'U');
        char2code.put('n', 'L');
        char2code.put('o', 'X');
        char2code.put('p', 'A');
        char2code.put('q', 'Y');
        char2code.put('r', 'B');
        char2code.put('s', 'V');
        char2code.put('t', 'M');
        char2code.put('u', 'C');
        char2code.put('v', 'H');
        char2code.put('w', 'F');
        char2code.put('x', 'P');
        char2code.put('y', 'G');
        char2code.put('z', 'D');

        code2char.put('A', 'p');
        code2char.put('B', 'r');
        code2char.put('C', 'u');
        code2char.put('D', 'z');
        code2char.put('E', 'c');
        code2char.put('F', 'w');
        code2char.put('G', 'y');
        code2char.put('H', 'v');
        code2char.put('I', 'f');
        code2char.put('J', 'b');
        code2char.put('K', 'a');
        code2char.put('L', 'n');
        code2char.put('M', 't');
        code2char.put('N', 'e');
        code2char.put('O', 'd');
        code2char.put('P', 'x');
        code2char.put('Q', 'h');
        code2char.put('R', 'i');
        code2char.put('S', 'k');
        code2char.put('T', 'l');
        code2char.put('U', 'm');
        code2char.put('V', 's');
        code2char.put('W', 'j');
        code2char.put('X', 'o');
        code2char.put('Y', 'q');
        code2char.put('Z', 'g');

        Assert.check(char2code.size() > 0);
        Assert.check(char2code.size() == code2char.size());
        Z = code2char.size();
        for (Object entry : char2code.keySet()) {
            Character mapKey = (Character.class.cast(entry));
            Character cypherChar = Character.class.cast(char2code.get(mapKey));
            Character deCypherChar = Character.class.cast(code2char.get(cypherChar));
            Assert.check(mapKey.equals(deCypherChar));
        }

    }


    @Override
    public String encrypt(String input, String key) {

        String inputLowcased = input.toLowerCase();

        char[] output = new char[inputLowcased.length()];
        int i = 0;
        for (char ch : inputLowcased.toCharArray()) {
            output[i] = Character.class.cast(char2code.get(new Character(ch)));
            i++;
        }
        return new String(output).toUpperCase();
    }

    @Override
    public String decrypt(String input, String key) {

        String inputUppercased = input.toUpperCase();

        char[] output = new char[inputUppercased.length()];
        int i = 0;
        for (char ch : inputUppercased.toCharArray()) {
            char outputChar = Character.class.cast(code2char.get(ch));
            output[i] = outputChar;
            i++;
        }
        return new String(output).toLowerCase();
    }


}
