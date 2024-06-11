package finalProject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.crypto.SecretKey;

public class EnDeCryptogram {
	
    public static void generate(String originF, String signatureF, String senderPubKeyF, String secretKeyF) throws Exception {
        // 비밀키 로드
        SecretKey secretKey = Secret_Key.loadSKey(secretKeyF);

        // EnDeSecretKey 객체 생성
        EnDeSecretKey enDeSecretKey = new EnDeSecretKey("AES");

        // 원문 파일 암호화 및 저장 경로 설정
        String encryptedOriginF = new File(AnimalFile.SENDER_PATH, "enc_Ori.dat").getAbsolutePath();
        enDeSecretKey.encryptFile(secretKey, originF, encryptedOriginF);
        System.out.println("원문 암호화 : "+encryptedOriginF);

        // 전자서명 파일 암호화 및 저장 경로 설정
        String encryptedSignatureF = new File(AnimalFile.SENDER_PATH, "enc_Sig.dat").getAbsolutePath();
        enDeSecretKey.encryptFile(secretKey, signatureF, encryptedSignatureF);
        System.out.println("전자서명 암호화 : "+encryptedSignatureF);

        // 송신자의 공개키 파일 암호화 및 저장 경로 설정
        String encryptedSenderPubKeyF = new File(AnimalFile.SENDER_PATH, "enc_SPubK.dat").getAbsolutePath();
        String senderPubKeyF1 = AnimalFile.PUBLIC_PATH + "/" + "senderPub.key"; // 아 이거 아닌것 같은데...;; 근데... 복잡해지네..ㅠㅠㅠ
        enDeSecretKey.encryptFile(secretKey, senderPubKeyF1, encryptedSenderPubKeyF);
        System.out.println("송신자 공개키 암호화 : "+encryptedSenderPubKeyF);

        System.out.println("암호문 생성 완료.");
        // 송신자의 공개키 파일 암호화 및 저장 경로 설정
//        String encryptedSenderPubKeyF = new File(AnimalFile.SENDER_PATH, "enc_SPubK.dat").getAbsolutePath();
//        enDeSecretKey.encryptFile(secretKey, senderPubKeyF, encryptedSenderPubKeyF);
//        System.out.println("송신자 공개키 암호화 : " + encryptedSenderPubKeyF);

        System.out.println("암호문 생성 완료.");
    }
    
    
    // ===== 암호문 파괴? =====
    public static void eliminate(String enOrigin, String enSig, String enSendPubK, SecretKey secretKeyF) 
        throws Exception {
        // EnDeSecretKey 객체 생성
        EnDeSecretKey enDeSecretKey = new EnDeSecretKey("AES");

	    // 암호화된 원문 파일 복호화
	    String decryptedOriginFile = new File(AnimalFile.RECIVER_PATH, "dec_Ori.txt").getAbsolutePath();
	    byte[] decryptedOrigin = enDeSecretKey.decryptFile(secretKeyF, enOrigin);
	    Files.write(Paths.get(decryptedOriginFile), decryptedOrigin);
	    System.out.println("복호화된 원문 파일: " + decryptedOriginFile);
	    System.out.println("복호화된 원문 파일 크기: " + decryptedOrigin.length);
	
	    // 암호화된 전자서명 파일 복호화
	    String decryptedSignatureFile = new File(AnimalFile.RECIVER_PATH, "dec_Sig.txt").getAbsolutePath();
	    byte[] decryptedSignature = enDeSecretKey.decryptFile(secretKeyF, enSig);
	    Files.write(Paths.get(decryptedSignatureFile), decryptedSignature);
	    System.out.println("복호화된 전자서명 파일: " + decryptedSignatureFile);
	    System.out.println("복호화된 전자서명 파일 크기: " + decryptedSignature.length);
	
	    // 암호화된 송신자의 공개키 파일 복호화
	    String decryptedSenderPubKeyFile = new File(AnimalFile.RECIVER_PATH, "dec_SPubK.txt").getAbsolutePath();
	    byte[] decryptedSenderPubKey = enDeSecretKey.decryptFile(secretKeyF, enSendPubK);
	    Files.write(Paths.get(decryptedSenderPubKeyFile), decryptedSenderPubKey);
	    System.out.println("복호화된 송신자의 공개키 파일: " + decryptedSenderPubKeyFile);
	    System.out.println("복호화된 송신자의 공개키 파일 크기: " + decryptedSenderPubKey.length);
	
	    System.out.println("암호문 복호화 완료.");
	}
}
