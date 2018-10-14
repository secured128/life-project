package openu.cryptograthy;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
public class CryptosystemsTest {


    @Test
    public void ShiftCypherTest() {
        String input = "khan";
        String keyLength = Integer.toString(3);
        String encrypted = CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem(keyLength).encrypt(input);
        String decrypted = CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem(keyLength).decrypt(encrypted);
        Assert.assertTrue("decrypted string should be identical to input string", input.equals(decrypted));
    }

    @Test
    public void SubstitutionCypherTest() {
        String defaultKey = "";
        for (char ch = CryptosystemApi.START_CHAR; ch <= CryptosystemApi.END_CHAR; ch++) {
            defaultKey = defaultKey + ch;
        }
        CryptosystemApi substitutionCryptosystem = CRYPTOSYSTEM_TYPE.SUBSTITUTION.getCryptosystem(defaultKey);
        String input = "khan";
        String encrypted = substitutionCryptosystem.encrypt(input);
        String decrypted = substitutionCryptosystem.decrypt(encrypted);
        Assert.assertTrue("decrypted string should be identical to input string", input.equals(decrypted));
    }

}
