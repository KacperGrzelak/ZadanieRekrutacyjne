import java.security.SecureRandom;
import java.util.Random;

public class LoginUtils {

    static char[] SYMBOLS = ".-_".toCharArray();
    static char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] NUMBERS = "0123456789".toCharArray();
    static char[] ALL_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
    static Random rand = new SecureRandom();

    public static String getlogin(int length) {
        assert length >= 4;
        assert length <= 32;
        char[] login = new char[length];

        login[0] = NUMBERS[rand.nextInt(NUMBERS.length)];
        login[1] = LOWERCASE[rand.nextInt(LOWERCASE.length)];
        login[2] = SYMBOLS[rand.nextInt(SYMBOLS.length)];

        for (int i = 3; i < length; i++) {
           login[i] = ALL_CHARS[rand.nextInt(ALL_CHARS.length)];
        }

        return new String(login);
    }
}