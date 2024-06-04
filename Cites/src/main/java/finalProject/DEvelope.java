package finalProject;

import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.SecretKey;

public class DEvelope {
    private SecretKey secretKey;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    private byte[] fileHash;
    private String secretKeyPath;
    private String privateKeyPath;
    private String publicKeyPath;
    private String fileHashPath;

    // 생성자
    public DEvelope() {}

    // 비밀키 설정
    public void setSecretKey(SecretKey secretKey, String path) {
        this.secretKey = secretKey;
        this.secretKeyPath = path;
    }

    // 비대칭키 설정
    public void setPrivateKey(PrivateKey privateKey, String path) {
        this.privateKey = privateKey;
        this.privateKeyPath = path;
    }

    public void setPublicKey(PublicKey publicKey, String path) {
        this.publicKey = publicKey;
        this.publicKeyPath = path;
    }

    // 해시 값 설정
    public void setFileHash(byte[] fileHash, String path) {
        this.fileHash = fileHash;
        this.fileHashPath = path;
    }

    // Getters
    public SecretKey getSecretKey() {
        return secretKey;
    }

    public String getSecretKeyPath() {
        return secretKeyPath;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getPublicKeyPath() {
        return publicKeyPath;
    }

    public byte[] getFileHash() {
        return fileHash;
    }

    public String getFileHashPath() {
        return fileHashPath;
    }
}
