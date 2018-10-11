package openu.cryptograthy;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SubstitutionCypher extends Cypher {

    Map<Character, Character> char2code;
    Map<Character, Character> code2char;

    public SubstitutionCypher(String key) {
        super(key);
    }

    protected void init(String key) {
        if (StringUtils.isNotEmpty(key) && key.length() == CHARECTERS_NUMBER) {

            char2code = new HashMap<Character, Character>();
            code2char = new HashMap<Character, Character>();

            for (char ch = 'a'; ch <= 'z'; ch++) {
                char newChar = Character.toUpperCase(key.charAt(ch - 'a'));
                if (char2code.containsValue(newChar)) {
                    throw new IllegalArgumentException("Key must be " + CHARECTERS_NUMBER + " unique characters length");
                } else {
                    char2code.put(ch, Character.toUpperCase(key.charAt(ch - 'a')));
                }
            }

            for (Map.Entry<Character, Character> entry : char2code.entrySet()) {
                code2char.put(entry.getValue(), entry.getKey());
            }
        } else {
            throw new IllegalArgumentException("Key must be " + CHARECTERS_NUMBER + " unique characters length");
        }

    }

    @Override
    public String encrypt(String plainText) {

        String inputLowcased = plainText.toLowerCase();

        char[] output = new char[inputLowcased.length()];
        int i = 0;
        for (char ch : inputLowcased.toCharArray()) {
            output[i] = ch;
            if (isSupported(ch)) {
                output[i] = Character.class.cast(char2code.get(new Character(ch)));
            }
            i++;
        }
        return new String(output).toUpperCase();
    }

    @Override
    public String decrypt(String cypheredText) {

        String inputUppercased = cypheredText.toUpperCase();

        char[] output = new char[inputUppercased.length()];
        int i = 0;
        for (char ch : inputUppercased.toCharArray()) {
            output[i] = ch;
            if (isSupported(ch)) {
                char outputChar = Character.class.cast(code2char.get(ch));
                output[i] = outputChar;
            }
            i++;
        }
        return new String(output).toLowerCase();
    }


}
