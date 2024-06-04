// 컴퓨터학과_20211743_김연진

package finalProject;

import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;

public class DigitalSignature {

    static byte[] createSignature(String priKeyFile1, String signatureFile1, String dataFile1) throws Exception {
        // 개인키 읽기
        PrivateKey privateKey;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(priKeyFile1))) {
            privateKey = (PrivateKey) ois.readObject();
        }

        // 서명에 사용할 데이터 파일의 내용을 byte[] 형태로 읽어들임
        byte[] dataBytes = Files.readAllBytes(Paths.get(dataFile1));

        // Signature 인스턴스 생성 및 초기화
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(dataBytes);
        byte[] digitalSignature = signature.sign();

        // 서명 저장
        try (FileOutputStream fos = new FileOutputStream(signatureFile1)) {
            fos.write(digitalSignature);
        }

        return digitalSignature;
    }

    static boolean verifySignature(String pubKeyFile1, String signatureFile1, String dataFile1) throws Exception {
        // 공개키 읽기
        PublicKey publicKey;
        try (ObjectInputStream oisPub = new ObjectInputStream(new FileInputStream(pubKeyFile1))) {
            publicKey = (PublicKey) oisPub.readObject();
        }

        // 전자서명 읽기
        byte[] signatureBytes = Files.readAllBytes(Paths.get(signatureFile1));

        // Signature 인스턴스 생성 및 초기화
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initVerify(publicKey);

        // 데이터 파일의 내용을 byte[] 형태로 읽어들임
        byte[] dataBytes = Files.readAllBytes(Paths.get(dataFile1));

        // 서명을 위해 데이터 업데이트
        signature.update(dataBytes);

        // 서명 검증
        return signature.verify(signatureBytes);
    }

    public static void main(String[] args) {
        try {
            String privateKeyFile = "path/to/private/key";
            String publicKeyFile = "path/to/public/key";
            String dataFile = "path/to/data/file";
            String signatureFile = "path/to/signature/file";

            // 서명 생성
            createSignature(privateKeyFile, signatureFile, dataFile);

            // 서명 검증
            boolean isVerified = verifySignature(publicKeyFile, signatureFile, dataFile);

            System.out.println("Signature verified: " + isVerified);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


