package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalProject.AnimalFile;
import finalProject.AnimalInfo;
import finalProject.AnimalInfoSaver;
import finalProject.MakeEnc;

/**
 * Servlet implementation class InfoRegi
 */

@WebServlet("/InfoRegi")
public class InfoRegi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

     // 파라미터 수집
        String sciName = request.getParameter("sciName");
        String comName = request.getParameter("comName");
        String taxon = request.getParameter("taxon");
        String morphChar = request.getParameter("morphChar");
        String sex = request.getParameter("sex");
        String reproInfo = request.getParameter("reproInfo");
        String locDiscovery = request.getParameter("locDiscovery");
        String habType = request.getParameter("habType");
        String habCond = request.getParameter("habCond");
        String diet = request.getParameter("diet");
        String actPatterns = request.getParameter("actPatterns");
        String socStruct = request.getParameter("socStruct");
        String consStatus = request.getParameter("consStatus");
        String threats = request.getParameter("threats");
        String consActions = request.getParameter("consActions");

     // 동물 정보 객체 생성
        AnimalInfo animalInfo = new AnimalInfo(sciName, comName, taxon, morphChar, sex, reproInfo, locDiscovery, habType, habCond, diet, actPatterns, socStruct, consStatus, threats, consActions);
        
        String path = AnimalFile.DIRECTORY_PATH;
        
        try {
        	String fileInfo = AnimalInfoSaver.saveAnimalInfoToFile(animalInfo);

            // 동적으로 생성된 파일명 사용
            String originFile = fileInfo; // 전체 파일 경로 포함되어 있음.

            
            String hashFile = "hashFile.txt";
            String senderPriKeyF = path + "senderPri.key";
            String signatureFile = "signature.sig";
            String senderPubKeyF = path + "senderPub.key";
            String secretKeyF = path +"secretKey.key";
            String combinedFile = path + "combinedFile.txt";
            String encryptedFile = path + "encryptedFile.enc";
            String recipientPubKeyF = "receiverPub.key";
            String encryptedSecretKeyFile = path + "encryptedSecretKey.dat";
            String finalOutput = path + "antmsehdanf.dat";

            // MakeDEnvelope 클래스 호출
            MakeEnc.execute(originFile, hashFile, senderPriKeyF, signatureFile, senderPubKeyF, 
            		secretKeyF, combinedFile, encryptedFile, recipientPubKeyF, encryptedSecretKeyFile, finalOutput);
            request.setAttribute("message", "동물 정보가 성공적으로 등록되고 파일에 저장되었습니다!");
        } catch (Exception e) {
        	e.printStackTrace();
            request.setAttribute("message", "오류 발생: " + e.getMessage());
        }

        //request.getRequestDispatcher("/.jsp").forward(request, response);
    }
}