package openu.cryptograthy;

public enum CRYPTOSYSTEM_TYPE {


    SHIFT(new ShiftCypher()), SUBSTITUTION(new SubstitutionCypher());

    private Cryptosystem cryptosystem;

    CRYPTOSYSTEM_TYPE(Cryptosystem cryptosystem) {
        this.cryptosystem = cryptosystem;
    }


    public Cryptosystem getCryptosystem() {
        return cryptosystem;
    }

}
