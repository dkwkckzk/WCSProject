// 컴퓨터학과_20211743_김연진
/*대칭키를 사용하여 데이터를 암호화하고 복호화합니다. 이 클래스는 주로 메시지 내용의 암호화와 복호화에 사용됩니다.*/

package test;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class EnDeSecretKey {
    private Cipher cipher;

    // 생성자: 주어진 알고리즘으로 Cipher 객체 초기화
    public EnDeSecretKey(String algorithm) throws NoSuchAlgorithmException, NoSuchPaddingException {
        cipher = Cipher.getInstance(algorithm);
    }

    // 파일 암호화 메서드
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

    // 파일 복호화 메서드
    public byte[] decryptFile(SecretKey secretKey, String inputFilePath)
            throws InvalidKeyException, IOException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        try (FileInputStream fis = new FileInputStream(inputFilePath);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }
}
