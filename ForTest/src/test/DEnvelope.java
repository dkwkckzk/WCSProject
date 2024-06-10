package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;

public class DEnvelope {

    // 대칭 키를 RSA 공개 키로 암호화하는 메서드
    private static byte[] encryptSymmetricKey(PublicKey publicKey, SecretKey symmetricKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(symmetricKey.getEncoded());
    }

    // 데이터를 전자봉투로 봉하고 암호화하는 메서드
    public static void seal(String dataFile, String envelopeFile, String sealedFile, PublicKey publicKey, SecretKey symmetricKey) throws Exception {
        byte[] encryptedSymmetricKey = encryptSymmetricKey(publicKey, symmetricKey); // 대칭 키를 RSA 공개 키로 암호화
        System.out.println("encryptedSymmetricKey 길이: " + encryptedSymmetricKey.length);

        // 전자봉투를 별도 파일에 저장
        try (FileOutputStream fos = new FileOutputStream(envelopeFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(encryptedSymmetricKey); // RSA로 암호화된 대칭 키를 저장
            oos.flush();
            System.out.println("전자봉투에 암호화된 대칭 키 저장 완료");
        }

        // 데이터 암호화 및 저장
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, symmetricKey);

        try (FileInputStream fis = new FileInputStream(dataFile);
             FileOutputStream fos = new FileOutputStream(sealedFile);
             CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }
            System.out.println("데이터 파일 암호화 완료");
        }
        
        // 전자봉투 파일의 해시 값 계산
        String envelopeHash = Hash.calculateFileHashString(envelopeFile);
        System.out.println("전자봉투 파일 해시 값: " + envelopeHash);
    }
}

