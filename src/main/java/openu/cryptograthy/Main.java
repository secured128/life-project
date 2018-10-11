package openu.cryptograthy;

public class Main {


    public static void main(String[] args) {


        CRYPTOSYSTEM_TYPE type = CRYPTOSYSTEM_TYPE.valueOf(args[0].toUpperCase());


        MODE mode = MODE.valueOf(args[1].toUpperCase());

        String input = args[2];

        String key = args[3];

        CryptosystemApi cryptosystem = type.getCryptosystem(key);

        String output;

        switch (mode) {
            case E:
                output = cryptosystem.encrypt(input);
                break;
            case D:
                output = cryptosystem.decrypt(input);
                break;
            default:
                output = "ERROR : unsupported option " + args[1];
                break;
        }
        System.out.println(output);
    }

}
