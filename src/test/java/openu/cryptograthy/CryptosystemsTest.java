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
        String encrypted = CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem().encrypt(input,keyLength);
        String decrypted = CRYPTOSYSTEM_TYPE.SHIFT.getCryptosystem().decrypt(encrypted,keyLength);
        Assert.assertTrue("decrypted string should be identical to input string",input.equals(decrypted));
    }

    @Test
    public void SubstitutionCypherTest() {
        String input = "khan";
        String encrypted = CRYPTOSYSTEM_TYPE.SUBSTITUTION.getCryptosystem().encrypt(input,null);
        String decrypted = CRYPTOSYSTEM_TYPE.SUBSTITUTION.getCryptosystem().decrypt(encrypted,null);
        Assert.assertTrue("decrypted string should be identical to input string",input.equals(decrypted));
    }

}
