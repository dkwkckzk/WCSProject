// 컴퓨터학과_20211743_김연진

package test;

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
    // 대칭 키 암호화에 사용할 알고리즘
    private static final String S_ALGORITHM = "AES";
    
    // 대칭 키 암호화에 사용할 키 길이
    private static final int S_LENGTH = 128;

    // 비밀키 생성
    public static SecretKey createSKey() throws NoSuchAlgorithmException {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(S_ALGORITHM);
            keyGenerator.init(S_LENGTH);
            return keyGenerator.generateKey();
    }

    // 비밀키 저장
    public boolean saveSKey(SecretKey secretKey, String keyFname) {
        try (FileOutputStream fos = new FileOutputStream(keyFname);
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
}

