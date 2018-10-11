package openu.cryptograthy;

import java.lang.reflect.Constructor;

public enum CRYPTOSYSTEM_TYPE {

    SHIFT(ShiftCypher.class), SUBSTITUTION(SubstitutionCypher.class);

    private Class<? extends Cypher> cryptoSystemClass;

    CRYPTOSYSTEM_TYPE(Class<? extends Cypher> cryptoSystemClass) {
        this.cryptoSystemClass = cryptoSystemClass;
    }

    public CryptosystemApi getCryptosystem(String key) {
        try {
            Constructor<? extends Cypher> ctor = cryptoSystemClass.getConstructor(String.class);
            CryptosystemApi cryptoSystem = ctor.newInstance(new Object[]{key});
            return cryptoSystem;
        } catch (Exception e) {
            throw new Error("Somthing impossible happened", e);
        }

    }

}
