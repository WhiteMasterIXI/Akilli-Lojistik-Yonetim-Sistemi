package Utils;
import java.security.MessageDigest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashUtil {
	
	public static String Sha256(String Pass){
		try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(Pass.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
    private static final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder(10); // cost factor = 10

    public static String bcryptHash(String pass) {
        return encoder.encode(pass);
    }

    public static boolean bcryptCheck(String plainPassword, String storedHash) {
        return encoder.matches(plainPassword, storedHash);
    }
}