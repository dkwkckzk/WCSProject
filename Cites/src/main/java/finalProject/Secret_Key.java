// 컴퓨터학과_20211743_김연진

package finalProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

class Secret_Key {
    private String algorithm;
    private int keySize;

    public Secret_Key(String algorithm, int keySize) {
        this.algorithm = algorithm;
        this.keySize = keySize;
    }

    // 비밀키 생성
    public SecretKey createSKey() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
            keyGenerator.init(keySize);
            return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("암호화 알고리즘이 없습니다: " + e.getMessage());
            return null;
        }
    }

    // 비밀키 저장
    public boolean saveSKey(SecretKey secretKey, String keyFname) {
        String fullPath = AnimalFile.DIRECTORY_PATH + keyFname;
        try (FileOutputStream fos = new FileOutputStream(fullPath);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(secretKey);
            return true;
        } catch (IOException e) {
            System.out.println("파일 쓰기 오류: " + e.getMessage());
            return false;
        }
    }

    // 비밀키 불러오기
    public static SecretKey loadSKey(String keyFname) {
        //String fullPath = AnimalFile.DIRECTORY_PATH + keyFname;
        try (FileInputStream fis = new FileInputStream(keyFname);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (SecretKey) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("파일을 찾을 수 없습니다: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("파일 읽기 오류: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("클래스를 찾을 수 없습니다: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        String algorithm = "AES"; // 사용할 암호화 알고리즘
        int keySize = 256; // 키의 크기
        String keyFname = "secretKey.ser"; // 비밀키를 저장할 파일 이름

        // Secret_Key 인스턴스 생성
        Secret_Key mySecretKey = new Secret_Key(algorithm, keySize);

        // 비밀키 생성
        SecretKey secretKey = mySecretKey.createSKey();
        if (secretKey == null) {
            System.out.println("비밀키 생성 실패");
            return;
        }

        // 비밀키 파일에 저장
        boolean isSaved = mySecretKey.saveSKey(secretKey, keyFname);
        if (!isSaved) {
            System.out.println("비밀키 저장 실패");
            return;
        }

        // 비밀키 파일에서 불러오기
        SecretKey loadedSecretKey = mySecretKey.loadSKey(keyFname);
        if (loadedSecretKey == null) {
            System.out.println("비밀키 불러오기 실패");
            return;
        }

        System.out.println("비밀키 성공적으로 생성, 저장, 불러오기 완료");
    }
}

