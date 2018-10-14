package openu.cryptograthy;

public interface CryptosystemApi {

    public static final int START_CHAR = 'a';
    public static final int END_CHAR = 'z';
    public static final int CHARECTERS_NUMBER = END_CHAR - START_CHAR + 1;

    String encrypt(String plainText);

    String decrypt(String cypheredText);
}
