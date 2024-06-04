// 컴퓨터학과_20211743_김연진
/*비대칭 키 쌍을 사용하여 데이터를 암호화하고 복호화합니다. 예를 들어, 수신자의 공개키로 데이터를 암호화하고, 수신자는 자신의 개인키로 데이터를 복호화할 수 있습니다.*/

package finalProject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

public class EnDePairKey {
    
    // 개인 키를 사용하여 데이터 파일을 암호화하고 결과를 암호화된 파일로 저장
    public static void encryptPri(PrivateKey privateKey, String dataFile, String encryptedFile) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        try (FileOutputStream fos = new FileOutputStream(encryptedFile);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher);
             FileInputStream fis = new FileInputStream(dataFile)) {

            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }

    // 공개 키를 사용하여 암호화된 파일을 복호화하고 복호화된 텍스트를 반환
    public static String decryptPub(PublicKey publicKey, String encryptedFile) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        try (FileInputStream fis = new FileInputStream(encryptedFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             BufferedReader br = new BufferedReader(new InputStreamReader(cis))) {

            StringBuilder decryptedText = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                decryptedText.append(line).append("\n");
            }
            return decryptedText.toString();
        }
    }

    // 공개 키를 사용하여 데이터 파일을 암호화하고 결과를 암호화된 파일로 저장
    public static void encryptPub(PublicKey publicKey, String dataFile, String encryptedFile) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        try (FileOutputStream fos = new FileOutputStream(encryptedFile);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher);
             FileInputStream fis = new FileInputStream(dataFile)) {

            byte[] buffer = new byte[2048];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
        }
    }

    // 개인 키를 사용하여 암호화된 파일을 복호화하고 복호화된 텍스트를 반환
    public static String decryptPri(PrivateKey privateKey, String encryptedFile) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        try (FileInputStream fis = new FileInputStream(encryptedFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             BufferedReader br = new BufferedReader(new InputStreamReader(cis))) {

            StringBuilder decryptedText = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                decryptedText.append(line).append("\n");
            }
            return decryptedText.toString();
        }
    }
}