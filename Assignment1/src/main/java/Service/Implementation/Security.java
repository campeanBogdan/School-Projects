package Service.Implementation;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Security {

    public String getMd5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashText = no.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }

            return hashText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
