package finalProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class MakeEnc { // 송신자 역할


 // 전자서명 생성(송신자의 개인키로 암호화 한 원문 해시값)
	public static void createDigitalSignature(String originFile, String senderPriKeyF, String signatureFile) throws Exception {
	    System.out.println("오리지널 경로: " + originFile);

	    // 개인키 읽기
	    PrivateKey privateKey = PairKey.loadPriKey(senderPriKeyF);

	    // 서명 생성
	    byte[] digitalSignature = DigitalSignature.createSignature(senderPriKeyF, signatureFile, originFile);

	    System.out.println("전자서명 완료.");
	}

    // 암호문 생성 (<원문, 전자서명, 송신자의 공개키>를 비밀키로 암호화)
    public static void generateCryptogram(String originFile, String signatureFile, String senderPubKeyF, String secretKeyF) throws Exception {
    	EnDeCryptogram.generate(originFile, signatureFile, senderPubKeyF, secretKeyF);
    }

    // 전자봉투 생성 (비밀키를 수신자의 공개키로 암호화)
    public static void createEnvelope(String envelopeFile, PublicKey receiverPublicKey, SecretKey secret) throws Exception {
        // 대칭 키(secret)를 수신자의 공개 키로 암호화
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, receiverPublicKey);
        byte[] encryptedKey = cipher.doFinal(secret.getEncoded());

        // 암호화된 대칭 키를 파일에 저장
        try (FileOutputStream fos = new FileOutputStream(envelopeFile)) {
            fos.write(encryptedKey); // 암호화된 대칭 키를 저장
            System.out.println("전자봉투 완.");
        }
    }

    // 실행 메서드
    public static void execute(String originFile, String hashFile, String senderPriKeyF, String signatureFile,
            String senderPubKeyF, String secretKeyF, String combinedFile, String encryptedFile,
            String recipientPubKeyF, String encryptedSecretKeyFile, String finalOutput) throws Exception {

        // 수신자의 공개 키 로드
        PublicKey receiverPublicKey = PairKey.loadPubKey(recipientPubKeyF);

        // 비밀키 로드
        SecretKey secretKey = Secret_Key.loadSKey(secretKeyF);

        // 1. 전자서명 생성
        createDigitalSignature(originFile, senderPriKeyF, signatureFile);

        // 2. 비밀키로 파일 암호화
        generateCryptogram(originFile, signatureFile, senderPubKeyF, secretKeyF);

        // 3. 전자봉투 생성
        createEnvelope(encryptedSecretKeyFile, receiverPublicKey, secretKey);
        
        File envelopeFile = new File(encryptedSecretKeyFile);
        System.out.println("전자봉투 파일 크기: " + envelopeFile.length() + " bytes");

        System.out.println("송신(암호화 및 저장) 끝.");
    }

    public static void main(String[] args) {
        try {

            // 실행
            String originFile = "haram.txt";
            String hashFile = "hash.txt";
            String signatureFile = "signature.sig";
            String senderPubKeyF = "senderPub.key";
            String senderPriKeyF = "senderPri.key";
            String secretKeyF = "secretKey.key";
            String combinedFile = "combinedFile.dat";
            String encryptedFile = "cryptogram.dat";
            String receiverPubKeyF = "receiverPub.key";
            String encryptedSecretKeyFile = "DigitalEnvelope.dev";
            String finalOutput = "antmsehdanf.dat";

            execute(originFile, hashFile, senderPriKeyF, signatureFile, senderPubKeyF, secretKeyF,
                    combinedFile, encryptedFile, receiverPubKeyF, encryptedSecretKeyFile, finalOutput);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
