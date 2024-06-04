// 컴퓨터학과_20211743_김연진
/*대칭키를 사용하여 데이터를 암호화하고 복호화합니다. 이 클래스는 주로 메시지 내용의 암호화와 복호화에 사용됩니다.*/

package finalProject;

import javax.crypto.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EnDeSecretKey {
    private Cipher cipher;
    
    public EnDeSecretKey(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
        cipher = Cipher.getInstance(algorithm);
    }
    
    public void encryptFile(SecretKey secretKey, String inputFilePath, String outputFilePath)
            throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             FileOutputStream fos = new FileOutputStream(outputFilePath);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            byte[] buffer = new byte[1024];
            int numBytesRead;
            while ((numBytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, numBytesRead);
            }
        }
    }

    public String decryptFile(SecretKey secretKey, String inputFilePath)
            throws InvalidKeyException, IOException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             BufferedReader br = new BufferedReader(new InputStreamReader(cis))) {

            StringBuilder decrypted = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                decrypted.append(line);
            }
            return decrypted.toString();
        }
    }
}