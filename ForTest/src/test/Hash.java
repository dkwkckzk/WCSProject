// 컴퓨터학과_20211743_김연진
/*메시지의 해시 값을 계산합니다. 이 해시 값은 메시지의 무결성을 확인하는 데 사용되며, 
 * MakeSignature 클래스에서 디지털 서명을 생성할 때도 사용됩니다.*/

package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
	
	static byte[] claFileHash(String filename) throws NoSuchAlgorithmException, IOException {
	// 1. 평문 파일의 해시 값을 계산하는 기능
		MessageDigest fdigest = MessageDigest.getInstance("SHA-256"); // SHA-1에서 SHA-256으로 바뀜
		try(FileInputStream fis = new FileInputStream(filename)) {
			byte[] data = new byte[1024];
			int count = 0;
			while((count = fis.read(data)) != -1) {
				fdigest.update(data, 0, count);
			}
 		}
		return fdigest.digest();
	}
	
	static void saveHash(String filename, byte[] hash) throws IOException {
	// 2. 해시 값을 파일에 저장하는 기능
	    try (FileOutputStream fos = new FileOutputStream(filename)) {
	        fos.write(hash);
	    }
	}
	
	public static byte[] loadHash(String filename) throws IOException {
	// 3. 파일에 저장된 해시 값을 불러오는 기능
	    try (FileInputStream fis = new FileInputStream(filename)) {
	        return fis.readAllBytes();
	    }
	}
	
    static boolean compareHash(byte[] hash1, byte[] hash2) {
    // 4. 2개의 해시 값이 동일한지 여부를 판단하는 기능
        return MessageDigest.isEqual(hash1, hash2); 
    }
	
	static void printDigest(byte[] digest) {
	// 해시 값 출력
		for (byte bytes : digest) {
			System.out.print(String.format("%02x", bytes) + "\t");
		}
		System.out.println();
	}
	
    // 파일의 해시 값을 계산하는 메서드(검증용)
    static String calculateFileHashString(String filePath) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        FileInputStream fis = new FileInputStream(filePath);
        byte[] byteArray = new byte[1024];
        int bytesCount = 0; 
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        }
        fis.close();
        byte[] bytes = digest.digest();
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
