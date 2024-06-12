// 컴퓨터학과_20211743_김연진
/*비대칭 키 쌍을 사용하여 데이터를 암호화하고 복호화합니다. 예를 들어, 수신자의 공개키로 데이터를 암호화하고, 수신자는 자신의 개인키로 데이터를 복호화할 수 있습니다.*/

package finalProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EnDePairKey {
    
    // 개인 키를 사용하여 데이터를 암호화하고 결과를 암호화된 바이트 배열로 반환
    static byte[] encryptPri(PrivateKey privateKey, byte[] data) 
    		throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
    
    
    // 공개 키를 사용하여 데이터를 암호화하고 결과를 암호화된 파일로 저장
    static void encryptPub(PublicKey publicKey, byte[] data, String encryptedFile) 
    		throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        try (FileOutputStream fos = new FileOutputStream(encryptedFile)) {
            byte[] encryptedData = cipher.doFinal(data);
            fos.write(encryptedData);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            System.err.println("암호화에 실패했습니다: " + e.getMessage());
        }
    }
    
    
    // 공개 키를 사용하여 암호화된 파일을 복호화하고 복호화된 텍스트를 반환
    static String decryptPub(PublicKey publicKey, String encryptedFile) 
    		throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        try (FileInputStream fis = new FileInputStream(encryptedFile);
             CipherInputStream cis = new CipherInputStream(fis, cipher);
             BufferedReader br = new BufferedReader(new InputStreamReader(cis))) {

            StringBuilder decryptedText = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                decryptedText.append(line).append("\n");
            }
            return decryptedText.toString();
        }
    }

    
    // 파일로부터 개인 키를 불러오는 메소드
    static PrivateKey loadPriKey(String keyFname, String path) 
    		throws IOException, ClassNotFoundException, NoSuchAlgorithmException, InvalidKeySpecException {
        File priKeyFile = new File(path, keyFname);
        if (!priKeyFile.exists()) {
            throw new FileNotFoundException("개인키 파일을 찾을 수 없습니다: " + priKeyFile.getAbsolutePath());
        }

        try (FileInputStream fis = new FileInputStream(priKeyFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            byte[] privateKeyBytes = (byte[]) ois.readObject();
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (IOException | ClassNotFoundException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("개인키를 불러오는 중 오류가 발생했습니다: " + e.getMessage());
            return null;
        }
    }
}


