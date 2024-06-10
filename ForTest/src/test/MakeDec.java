package test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MakeDec { // 수신자 입장.
    
    // 전자봉투 복호화
    public static SecretKey decryptEnvelope(String envelopeFile, PrivateKey receiverPrivateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, receiverPrivateKey);
        
        try (FileInputStream fis = new FileInputStream(envelopeFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher)) {
            byte[] decryptedKeyBytes = cis.readAllBytes();
            return new SecretKeySpec(decryptedKeyBytes, "AES");
        }
    }
    
    // 암호문 복호화
    public static void eliminateCryptogram(String enOrigin, String enSig, String enSendPubK, SecretKey secretKey) throws Exception {
        EnDeCryptogram.eliminate(enOrigin, enSig, enSendPubK, secretKey);
    }
    
    // 전자서명 검증
    public static boolean verifySignature(String pubKeyFile, String sigFile, String dataFile) throws Exception {
        return DigitalSignature.verifySignature(pubKeyFile, sigFile, dataFile);
    }

    public static void main(String[] args) {
        try {
            // 수신자의 개인 키 로드
            PrivateKey receiverPrivateKey = PairKey.loadPriKey("receiverPri.key");

            // 전자봉투 파일과 복호화한 대칭 키 얻기
            String envelopeFile = "DigitalEnvelope.dev";
            SecretKey decryptedKey = decryptEnvelope(envelopeFile, receiverPrivateKey);
            System.out.println("복호화된 비밀 키: " + decryptedKey);

            // 전자봉투 파일 크기 출력
            File envelope = new File(envelopeFile);
            System.out.println("전자봉투 파일 크기: " + envelope.length() + " bytes");

            // 암호문 복호화 실행
            eliminateCryptogram("encryptedOrigin.dat", 
                                "encryptedSignature.dat", 
                                "encryptedSenderPubKey.dat", 
                                decryptedKey);

            // 전자서명 검증
            boolean isVerified = verifySignature("decryptedSenderPubKey.txt", "decryptedSignature.txt", "decryptedOrigin.txt");
            if (isVerified) {
                System.out.println("전자서명 검증 성공");
                
                // 전자서명 검증 성공 후 decryptedOrigin.txt의 내용을 화면에 출력
                String content = new String(Files.readAllBytes(Paths.get("decryptedOrigin.txt")));
                System.out.println("decryptedOrigin.txt의 내용:\n" + content);
            } else {
                System.out.println("전자서명 검증 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("오류 발생");
        }
    }
}