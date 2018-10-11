package openu.cryptograthy;

public interface CryptosystemApi {

    public static final int CHARECTERS_NUMBER = 'z' - 'a' + 1;

    String encrypt(String plainText);

    String decrypt(String cypheredText);
}
