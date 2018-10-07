package openu.cryptograthy;

public class Main {


    public static void main(String[] args) {


        CRYPTOSYSTEM_TYPE type = CRYPTOSYSTEM_TYPE.valueOf(args[0].toUpperCase());

        Cryptosystem cryptosystem = type.getCryptosystem();
        MODE mode = MODE.valueOf(args[1].toUpperCase());

        String input = args[2];

        String key = null;

        if (args.length > 3) {
            key = args[3];
        }

        String output;

        switch (mode) {
            case E:
                output = cryptosystem.encrypt(input, key);
                break;
            case D:
                output = cryptosystem.decrypt(input, key);
                break;
            default:
                output = "ERROR : unsupported option " + args[1];
                break;
        }
        System.out.println(output);
    }

}
