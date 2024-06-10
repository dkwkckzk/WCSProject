package finalProject;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.SecretKey;

public class EnDeCryptogram {
    // 모든 필요한 파일 암호화 메서드
    public static void generate(String originFile, String signatureFile, String senderPubKeyF, String secretKeyF) 
            throws Exception {
        // 비밀키 로드
        SecretKey secretKey = Secret_Key.loadSKey(secretKeyF);

        // EnDeSecretKey 객체 생성
        EnDeSecretKey enDeSecretKey = new EnDeSecretKey("AES");

        // 원문 파일 암호화
        String encryptedOriginFile = "encryptedOrigin.dat";
        enDeSecretKey.encryptFile(secretKey, originFile, encryptedOriginFile);

        // 전자서명 파일 암호화
        String encryptedSignatureFile = "encryptedSignature.dat";
        enDeSecretKey.encryptFile(secretKey, signatureFile, encryptedSignatureFile);

        // 송신자의 공개키 파일 암호화
        String encryptedSenderPubKeyFile = "encryptedSenderPubKey.dat";
        enDeSecretKey.encryptFile(secretKey, senderPubKeyF, encryptedSenderPubKeyFile);

        System.out.println("암호문 생성 완.");
    }
    
    public static void eliminate(String encryptedOriginFile, String encryptedSignatureFile, String encryptedSenderPubKeyFile, SecretKey secretKey) 
            throws Exception {
        // EnDeSecretKey 객체 생성
        EnDeSecretKey enDeSecretKey = new EnDeSecretKey("AES");

        // 암호화된 원문 파일 복호화
        byte[] decryptedOrigin = enDeSecretKey.decryptFile(secretKey, encryptedOriginFile);
        // 복호화된 데이터를 파일에 쓰기
        String decryptedOriginFile = "decryptedOrigin.txt";
        Files.write(Paths.get(decryptedOriginFile), decryptedOrigin);
        System.out.println("복호화된 원문 파일: " + decryptedOriginFile);
        System.out.println("복호화된 원문 파일 크기: " + decryptedOrigin.length);

        // 암호화된 전자서명 파일 복호화
        byte[] decryptedSignature = enDeSecretKey.decryptFile(secretKey, encryptedSignatureFile);
        // 복호화된 데이터를 파일에 쓰기
        String decryptedSignatureFile = "decryptedSignature.txt";
        Files.write(Paths.get(decryptedSignatureFile), decryptedSignature);
        System.out.println("복호화된 전자서명 파일: " + decryptedSignatureFile);
        System.out.println("복호화된 전자서명 파일 크기: " + decryptedSignature.length);

        // 암호화된 송신자의 공개키 파일 복호화
        byte[] decryptedSenderPubKey = enDeSecretKey.decryptFile(secretKey, encryptedSenderPubKeyFile);
        // 복호화된 데이터를 파일에 쓰기
        String decryptedSenderPubKeyFile = "decryptedSenderPubKey.txt";
        Files.write(Paths.get(decryptedSenderPubKeyFile), decryptedSenderPubKey);
        System.out.println("복호화된 송신자의 공개키 파일: " + decryptedSenderPubKeyFile);
        System.out.println("복호화된 송신자의 공개키 파일 크기: " + decryptedSenderPubKey.length);

        System.out.println("암호문 복호화 완료.");
    }
}
