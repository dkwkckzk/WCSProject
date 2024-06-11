package finalProject;

import java.io.File;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

public class MakeEnc { // 송신자 역할

	// 전자서명 생성(송신자의 개인키로 암호화 한 원문 해시값)
	public static void createDigSign(String originFile, String senderPriKeyF, String signatureFile) throws Exception {
	    System.out.println("오리지널 경로: " + originFile);

	    // 개인키 읽기
	    PrivateKey privateKey = PairKey.loadPriKey(senderPriKeyF, AnimalFile.SENDER_PATH);

	    // 서명 생성
	    byte[] digitalSignature = DigitalSignature.createSignature(senderPriKeyF, signatureFile, originFile);

	    System.out.println("전자서명 완료.");
	}
	
	
    // 암호문 생성 (<원문, 전자서명, 송신자의 공개키>를 비밀키로 암호화) // 경로
    public static void generateCryptogram(String originF, String signatureF, String senderPubKeyF, String secretKeyF, String path) throws Exception {
    	EnDeCryptogram.generate(originF, signatureF, senderPubKeyF, secretKeyF);
    }
    
    
 // 전자봉투 생성 (비밀키를 수신자의 공개키로 암호화)
    public static void createEnvelope(String envelopeF, PublicKey receiverPubKeyF, SecretKey secret) throws Exception {
        // 대칭 키(secret)를 바이트 배열로 변환
        byte[] secretKeyBytes = secret.getEncoded();

        // 전자봉투 파일 경로 생성
        String envelopeFP = new File(AnimalFile.SENDER_PATH, envelopeF).getAbsolutePath();

        // EnDePairKey 클래스의 encryptPub 함수를 호출하여 대칭 키를 암호화하고 결과를 파일에 저장
        EnDePairKey.encryptPub(receiverPubKeyF, secretKeyBytes, envelopeFP);
    }


    // 실행 메서드
    public static void execute(String originF, String senderPriKeyF, String signatureF,
            String senderPubKeyF, String secretKeyF, String receiverPubKeyF, String encryptedSecretKeyF, String senderPath, String path) throws Exception {

        // 수신자의 공개 키 로드
        PublicKey receiverPublicKey = PairKey.loadPubKey(receiverPubKeyF,AnimalFile.PUBLIC_PATH);

        // 비밀키 로드
        SecretKey secretKey = Secret_Key.loadSKey(secretKeyF);

        // 1. 전자서명 생성
        createDigSign(originF, senderPriKeyF, signatureF);

        // 2. 비밀키로 파일 암호화
        generateCryptogram(originF, signatureF, senderPubKeyF, secretKeyF, path);

        // 3. 전자봉투 생성
        createEnvelope(encryptedSecretKeyF, receiverPublicKey, secretKey);
        
        File envelopeFile = new File(encryptedSecretKeyF);
        System.out.println("전자봉투 파일 크기: " + envelopeFile.length() + " bytes");

        System.out.println("송신(암호화 및 저장) 끝.");
    }
}
