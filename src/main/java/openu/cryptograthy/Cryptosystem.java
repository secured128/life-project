package openu.cryptograthy;

public interface Cryptosystem {
    String encrypt(String input, String key);

    String decrypt(String input, String key);
}
