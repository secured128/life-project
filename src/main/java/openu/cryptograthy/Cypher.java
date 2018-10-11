package openu.cryptograthy;

public abstract class Cypher implements CryptosystemApi {

    public Cypher(String key) {
        init(key);
    }

    protected abstract void init(String key);

    protected boolean isSupported(char ch) {
        char lch = Character.toLowerCase(ch);
        if (lch >= 'a' && lch <= 'z') {
            return true;
        }
        return false;
    }

}
