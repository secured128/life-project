package openu.cryptograthy;

public class ShiftCypher extends Cypher {

    int shiftPositions;

    public ShiftCypher(String key) {
        super(key);
    }

    protected void init(String key) {
        shiftPositions = Integer.valueOf(key);
    }


    public String encrypt(String plainText) {

        String inputLowcased = plainText.toLowerCase();

        char[] output = new char[inputLowcased.length()];
        int i = 0;
        for (char ch : inputLowcased.toCharArray()) {
            output[i] = ch;
            if (isSupported(ch)) {
                int inputCode = Integer.class.cast(ch - 'a');
                int encryptedValue = (inputCode + shiftPositions) % CHARECTERS_NUMBER;
                char outputChar = (char) (encryptedValue + 'a');
                output[i] = outputChar;
            }
            i++;
        }
        return new String(output).toUpperCase();
    }

    public String decrypt(String cypheredText) {

        String inputLowcased = cypheredText.toLowerCase();

        char[] output = new char[cypheredText.length()];
        int i = 0;
        for (char ch : inputLowcased.toCharArray()) {
            output[i] = ch;
            if (isSupported(ch)) {
                int inputCode = Integer.class.cast(ch - 'a');
                int encryptedValue = (inputCode - shiftPositions + CHARECTERS_NUMBER) % CHARECTERS_NUMBER;
                char outputChar = (char) (encryptedValue + 'a');
                output[i] = outputChar;
            }
            i++;
        }
        return new String(output).toLowerCase();
    }

}
