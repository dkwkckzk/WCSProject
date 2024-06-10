// 컴퓨터학과_20211743_김연진

package test;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class DigitalSignature {

    static byte[] createSignature(String priKeyFile, String signatureFileName, String dataFile) throws Exception {
        // 개인키 읽기
        PrivateKey privateKey = PairKey.loadPriKey(priKeyFile);

        // 서명에 사용할 데이터 파일의 내용을 byte[] 형태로 읽어들임
        byte[] dataBytes = Files.readAllBytes(Paths.get(dataFile));

        // Signature 인스턴스 생성 및 초기화
        Signature signature = Signature.getInstance("SHA512withRSA");
        signature.initSign(privateKey);
        signature.update(dataBytes);
        byte[] digitalSignature = signature.sign();

        // 서명 저장
        try (FileOutputStream fos = new FileOutputStream(signatureFileName)) {
            fos.write(digitalSignature);
        }

        // 서명 파일 크기 출력
        long signatureFileSize = Files.size(Paths.get(signatureFileName));
        System.out.println("전자서명 생성: " + signatureFileSize + " bytes");

        return digitalSignature;
    }

    static boolean verifySignature(String pubKeyFile, String signatureFileName, String dataFile) throws Exception {
        // 공개키 읽기
        PublicKey publicKey = PairKey.loadPubKey(pubKeyFile);

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

        return signature.verify(signatureBytes);
    }
}