package finalProject;

import javax.crypto.SecretKey;

public class CreateKey {

    public static void main(String[] args) {
        try {
            // 송신자용 키 쌍 생성 및 저장
            PairKey senderKeyPair = PairKey.getInstance(2048); // 2048비트 RSA 키 쌍 생성
            senderKeyPair.createPKey();
            
            String senderPubKeyFile = "senderPub.key";
            String senderPriKeyFile = "senderPri.key";
            
            senderKeyPair.savePubKey(senderKeyPair.getPubKey(), senderPubKeyFile); // 송신자의 공개키 저장
            senderKeyPair.savePriKey(senderKeyPair.getPriKey(), senderPriKeyFile, AnimalFile.SENDER_PATH); // 송신자의 개인키 저장
            
            System.out.println("송신자 키 쌍이 성공적으로 저장되었습니다.");

            // 수신자용 키 쌍 생성 및 저장
            PairKey receiverKeyPair = PairKey.getInstance(2048); // 2048비트 RSA 키 쌍 생성
            receiverKeyPair.createPKey();
            
            String receiverPubKeyFile = "receiverPub.key";
            String receiverPriKeyFile = "receiverPri.key";
            
            receiverKeyPair.savePubKey(receiverKeyPair.getPubKey(), receiverPubKeyFile); // 수신자의 공개키 저장
            receiverKeyPair.savePriKey(receiverKeyPair.getPriKey(), receiverPriKeyFile, AnimalFile.RECIVER_PATH); // 수신자의 개인키 저장
            
            System.out.println("수신자 키 쌍이 성공적으로 저장되었습니다.");
            
            // 비밀키(AES) 생성 및 저장
            SecretKey secretKey = Secret_Key.createSKey();
            String secretKeyFile = "secretKey.key";
            
            Secret_Key secretKeyHandler = new Secret_Key();
            boolean isSaved = secretKeyHandler.saveSKey(secretKey, secretKeyFile);
            if (!isSaved) {
                System.out.println("비밀키 저장 실패");
            } else {
                System.out.println("비밀키가 성공적으로 저장되었습니다.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}