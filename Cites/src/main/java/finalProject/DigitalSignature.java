// 컴퓨터학과_20211743_김연진

package finalProject;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class DigitalSignature {
	
	// ===== 전자서명 생성 =====
    static byte[] createSignature(String senderpriKeyF, String digiSignF, String originF) throws Exception {
        // 개인키 읽기
	    String path = AnimalFile.SENDER_PATH;

	    // 개인키 읽기
	    PrivateKey privateKey = PairKey.loadPriKey(senderpriKeyF, path);

        // 서명에 사용할 데이터 파일의 내용을 byte[] 형태로 읽어들임
        byte[] dataBytes = Files.readAllBytes(Paths.get(originF));

        // Signature 인스턴스 생성 및 초기화
        Signature signature = Signature.getInstance("SHA512withRSA");
        signature.initSign(privateKey);
        signature.update(dataBytes);
        byte[] digitalSignature = signature.sign();
        
        // 서명 파일 경로 설정
        File digiF = new File(path, digiSignF);
        String digiFPath = digiF.getAbsolutePath();

        // 서명 저장
        try (FileOutputStream fos = new FileOutputStream(digiSignF)) {
            fos.write(digitalSignature);
        }

        // 서명 파일 크기 출력
        long signatureFileSize = Files.size(Paths.get(digiSignF));
        System.out.println("전자서명 생성: " + signatureFileSize + " bytes");

        return digitalSignature;
    }
    

 // ===== 전자서명 검증 =====
    static boolean verifySignature(String pubKeyFile, String signatureFileName, String dataFile) throws Exception {
        // 공개키 읽기
        PublicKey publicKey = PairKey.loadPubKey(pubKeyFile, AnimalFile.RECIVER_PATH);

        // 서명 읽기
        byte[] signatureBytes = Files.readAllBytes(Paths.get(signatureFileName));

        // 서명에 사용할 데이터 파일의 내용을 byte[] 형태로 읽어들임
        byte[] dataBytes = Files.readAllBytes(Paths.get(dataFile));

        // Signature 인스턴스 생성 및 초기화
        Signature signature = Signature.getInstance("SHA512withRSA");
        signature.initVerify(publicKey);
        signature.update(dataBytes);

        // 서명 파일 크기 출력
        long signatureFileSize = Files.size(Paths.get(signatureFileName));
        System.out.println("검증전자서명크기: " + signatureFileSize + " bytes");

        // 서명 파일 저장
        File verifiedSignatureFile = new File(AnimalFile.RECIVER_PATH, new File(signatureFileName).getName());
        String verifiedSignatureFilePath = verifiedSignatureFile.getAbsolutePath();
        try (FileOutputStream fos = new FileOutputStream(verifiedSignatureFilePath)) {
            fos.write(signatureBytes);
        }

        return signature.verify(signatureBytes);
    }
}