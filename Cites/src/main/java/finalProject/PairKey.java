// 컴퓨터학과_20211743_김연진

package finalProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class PairKey {
    private static final String KeyAlgorithm = "RSA";
    
    private KeyPairGenerator keyGen;
    private KeyPair pair;
    
    private PrivateKey priKey;
    private PublicKey pubKey;

    public static PairKey getInstance(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        PairKey rslt = new PairKey();
        
        rslt.keyGen = KeyPairGenerator.getInstance(KeyAlgorithm);
        rslt.keyGen.initialize(keylength);
        
        return rslt;
    }
    
    public void createPKey() {
        this.pair = this.keyGen.generateKeyPair();
        this.priKey = pair.getPrivate();
        this.pubKey = pair.getPublic();
    }
    
    public PrivateKey getPriKey() {
        return priKey;
    }

    public PublicKey getPubKey() {
        return pubKey;
    }
    
    // 공개 키를 파일로 저장하는 메소드
    public void savePubKey(PublicKey pubKey, String filename) throws IOException {
        String fullPath = AnimalFile.DIRECTORY_PATH + filename;
        try (FileOutputStream fos = new FileOutputStream(fullPath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pubKey);
        }
    }

    // 개인 키를 파일로 저장하는 메소드
    public void savePriKey(PrivateKey priKey, String filename) throws IOException {
        String fullPath = AnimalFile.DIRECTORY_PATH + filename;
        try (FileOutputStream fos = new FileOutputStream(fullPath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(priKey);
        }
    }

    // 파일로부터 공개 키를 불러오는 메소드
    public static PublicKey loadPubKey(String filename) throws IOException, ClassNotFoundException {
        String fullPath = AnimalFile.DIRECTORY_PATH + filename;
        try (FileInputStream fis = new FileInputStream(fullPath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (PublicKey) ois.readObject();
        }
    }

    // 파일로부터 개인 키를 불러오는 메소드
    public static PrivateKey loadPriKey(String filename) throws IOException, ClassNotFoundException {
        String fullPath = AnimalFile.DIRECTORY_PATH + filename;
        try (FileInputStream fis = new FileInputStream(fullPath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (PrivateKey) ois.readObject();
        }
    }
    
    public static void main(String[] args) {
        try {
            // 송신자용 키 쌍 생성 및 저장
            PairKey senderKeyPair = PairKey.getInstance(2048); // 2048비트 RSA 키 쌍 생성
            senderKeyPair.createPKey();
            
            String senderPubKeyFile = "senderPub.key";
            String senderPriKeyFile = "senderPri.key";
            
            senderKeyPair.savePubKey(senderKeyPair.getPubKey(), senderPubKeyFile); // 송신자의 공개키 저장
            senderKeyPair.savePriKey(senderKeyPair.getPriKey(), senderPriKeyFile); // 송신자의 개인키 저장
            
            System.out.println("송신자 키 쌍이 성공적으로 저장되었습니다.");

            // 수신자용 키 쌍 생성 및 저장
            PairKey receiverKeyPair = PairKey.getInstance(2048); // 2048비트 RSA 키 쌍 생성
            receiverKeyPair.createPKey();
            
            String receiverPubKeyFile = "receiverPub.key";
            String receiverPriKeyFile = "receiverPri.key";
            
            receiverKeyPair.savePubKey(receiverKeyPair.getPubKey(), receiverPubKeyFile); // 수신자의 공개키 저장
            receiverKeyPair.savePriKey(receiverKeyPair.getPriKey(), receiverPriKeyFile); // 수신자의 개인키 저장
            
            System.out.println("수신자 키 쌍이 성공적으로 저장되었습니다.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

