// 컴퓨터학과_20211743_김연진

package finalProject;

import java.io.*;
import java.security.*;
import javax.crypto.SecretKey;

public class MakeDEnvelope {

    // 파일들을 결합하는 함수
    public static void combineFiles(String[] inputFiles, String outputFile) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            for (String inputFile : inputFiles) {
                try (FileInputStream fis = new FileInputStream(inputFile)) {
                    byte[] buffer = new byte[2048];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                }
            }
        }
    }

    // 전자서명 생성
    public static void createDigitalSignature(String originFile, String hashFile, String senderPriKeyF, String signatureFile) throws Exception {
    	System.out.println("오리지널 경로: " + originFile);
        byte[] fileHash = Hash.claFileHash(originFile);
        //System.out.println("생성된 해시 값:");
        //Hash.printDigest(fileHash);

        try (FileOutputStream fos = new FileOutputStream(AnimalFile.DIRECTORY_PATH + hashFile)) {
            fos.write(fileHash);
        }

        DigitalSignature.createSignature(senderPriKeyF, signatureFile, originFile);
        System.out.println("전자서명이 성공적으로 생성되었습니다.");
    }

    // 비밀키로 암호화
    public static void encryptWithSecretKey(String originFile, String signatureFile, String senderPubKeyF, String secretKeyF, String combinedFile, String encryptedFile) throws Exception {
        //PublicKey senderPubKey = PairKey.loadPubKey(senderPubKeyF);
        SecretKey secretKey = Secret_Key.loadSKey(secretKeyF);
        String[] filesToCombine = {originFile, signatureFile, senderPubKeyF};
        combineFiles(filesToCombine, combinedFile);

        EnDeSecretKey enDeSecretKey = new EnDeSecretKey("AES");
        enDeSecretKey.encryptFile(secretKey, combinedFile, encryptedFile);
        System.out.println("암호화가 성공적으로 완료되었습니다.");
    }

    // 전자봉투 생성
    public static void createDigitalEnvelope(String secretKeyF, String recipientPubKeyF, String encryptedSecretKeyFile) throws Exception {
        PublicKey recipientPubKey = PairKey.loadPubKey(recipientPubKeyF);
        EnDePairKey.encryptPub(recipientPubKey, secretKeyF, encryptedSecretKeyFile);
        System.out.println("전자봉투가 성공적으로 생성되었습니다.");
    }

    // 암호문과 전자봉투 전송
    public static void sendEncryptedDataAndEnvelope(String encryptedFile, String encryptedSecretKeyFile, String finalOutput) throws IOException {
        String[] filesToSend = {encryptedFile, encryptedSecretKeyFile};
        combineFiles(filesToSend, finalOutput);
        System.out.println("암호문과 전자봉투가 성공적으로 저장되었습니다.");
    }
    
    // 실행 메서드 추가
    public static void execute(String originFile, String hashFile, String senderPriKeyF, String signatureFile, String senderPubKeyF, String secretKeyF, String combinedFile, String encryptedFile, String recipientPubKeyF, String encryptedSecretKeyFile, String finalOutput) {
        try {
            createDigitalSignature(originFile, hashFile, senderPriKeyF, signatureFile);
            encryptWithSecretKey(originFile, signatureFile, senderPubKeyF, secretKeyF, combinedFile, encryptedFile);
            createDigitalEnvelope(secretKeyF, recipientPubKeyF, encryptedSecretKeyFile);
            sendEncryptedDataAndEnvelope(encryptedFile, encryptedSecretKeyFile, finalOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

