// 컴퓨터학과_20211743_김연진

package test;

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
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pubKey);
        }
    }

    // 개인 키를 파일로 저장하는 메소드
    public void savePriKey(PrivateKey priKey, String filename) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(priKey);
        }
    }

    // 파일로부터 공개 키를 불러오는 메소드
    public static PublicKey loadPubKey(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (PublicKey) ois.readObject();
        }
    }

    // 파일로부터 개인 키를 불러오는 메소드
    public static PrivateKey loadPriKey(String filename) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (PrivateKey) ois.readObject();
        }
    }
}

