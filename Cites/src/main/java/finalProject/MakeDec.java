package finalProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MakeDec {
    // 전자봉투 복호화
    public static SecretKey decryptEnvelope(String envelopeFile, PrivateKey receiverPrivateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, receiverPrivateKey);

        try (FileInputStream fis = new FileInputStream(envelopeFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher)) {
            byte[] decryptedKeyBytes = cis.readAllBytes();
            return new SecretKeySpec(decryptedKeyBytes, "AES");
        } catch (IOException e) {
            System.err.println("IOException 발생: " + e.getMessage());
            throw e;
        }
    }

    // 암호문 복호화
    public static void eliminateCryptogram(String enOrigin, String enSig, String enSendPubK, SecretKey secretKey) throws Exception {
    	String path = AnimalFile.SENDER_PATH+"/";
    	String SPK = path+enSendPubK;
    	EnDeCryptogram.eliminate(enOrigin, enSig, SPK, secretKey);
    }

    // 전자서명 검증
    public static boolean verifySignature(String pubKeyFile, String sigFile, String dataFile) throws Exception {

        return DigitalSignature.verifySignature(pubKeyFile, sigFile, dataFile);
    }


 // 실행 함수
    public static boolean execute(String envelopeFile, String receiverPriKeyF, String enOrigin, String enSig, String enSendPubK)
            throws Exception {
        // 수신자의 개인 키 로드
        PrivateKey receiverPrivateKey = PairKey.loadPriKey(receiverPriKeyF, AnimalFile.RECIVER_PATH);

        // 전자봉투 복호화
        SecretKey decryptedKey = decryptEnvelope(envelopeFile, receiverPrivateKey);
        System.out.println("복호화된 비밀 키: " + decryptedKey);

        // 전자봉투 파일 크기 출력
        File envelope = new File(envelopeFile);
        System.out.println("전자봉투 파일 크기: " + envelope.length() + " bytes");

        // 암호문 복호화
        eliminateCryptogram(enOrigin, enSig, enSendPubK, decryptedKey);


        // 전자서명 검증
        boolean isVerified = verifySignature(
        		"dec_SPubK.txt",
                new File(AnimalFile.RECIVER_PATH, "dec_Sig.txt").getAbsolutePath(),
                new File(AnimalFile.RECIVER_PATH, "dec_Ori.txt").getAbsolutePath()
        );

        return isVerified;
    }
    
//    public static void main(String[] args) {
//    	String path = AnimalFile.SENDER_PATH+"/";
//        try {
//            // 테스트 파일 경로 설정
//            String envelopeFile = path+"DigitalEnvelope.dev";
//            String receiverPriKeyFile = "receiverPri.key";
//            String enOrigin = path+"enc_Ori.dat";
//            String enSig = path+"enc_Sig.dat";
//            String enSendPubK = "enc_SPubK.dat";
//
//            // MakeDec 클래스의 execute 메서드 호출
//            boolean isVerified = MakeDec.execute(envelopeFile, receiverPriKeyFile, enOrigin, enSig, enSendPubK);
//
//            // 전자서명 검증 결과에 따라 출력
//            if (isVerified) {
//                System.out.println("전자서명 검증 성공");
//
//                // 전자서명 검증 성공 후 decryptedOrigin.txt의 내용을 출력
//                String content = new String(Files.readAllBytes(Paths.get(new File(AnimalFile.RECIVER_PATH, "dec_Ori.txt").getAbsolutePath())));
//                System.out.println("decryptedOrigin.txt의 내용:\n" + content);
//            } else {
//                System.out.println("전자서명 검증 실패");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("오류 발생: " + e.getMessage());
//        }
//    }
}