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
import finalProject.MakeDEnvelope;

/**
 * Servlet implementation class InfoRegi
 */
@SuppressWarnings("serial")
@WebServlet("/InfoRegi")
public class InfoRegi extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // 파라미터 수집
        String scientificName = request.getParameter("scientificName");
        String commonName = request.getParameter("commonName");
        String taxonomy = request.getParameter("taxonomy");
        String morphologicalCharacteristics = request.getParameter("morphologicalCharacteristics");
        String sex = request.getParameter("sex");
        String reproductiveInformation = request.getParameter("reproductiveInformation");
        String locationOfDiscovery = request.getParameter("locationOfDiscovery");
        String habitatType = request.getParameter("habitatType");
        String habitatConditions = request.getParameter("habitatConditions");
        String diet = request.getParameter("diet");
        String activityPatterns = request.getParameter("activityPatterns");
        String socialStructure = request.getParameter("socialStructure");
        String conservationStatus = request.getParameter("conservationStatus");
        String threats = request.getParameter("threats");
        String conservationMeasures = request.getParameter("conservationActions");

        // 동물 정보 객체 생성
        AnimalInfo animalInfo = new AnimalInfo(scientificName, commonName, taxonomy, morphologicalCharacteristics, sex, reproductiveInformation, 
        		locationOfDiscovery, habitatType, habitatConditions, diet, activityPatterns, socialStructure, conservationStatus, threats, conservationMeasures);
        
        String path = AnimalFile.DIRECTORY_PATH;
        
        try {
            String fileInfo = AnimalInfoSaver.saveAnimalInfoToFile(animalInfo);

            // 동적으로 생성된 파일명 사용
            String originFile = fileInfo; // 전체 파일 경로 포함되어 있음.

            
            String hashFile = "hashFile.txt";
            String senderPriKeyF = "senderPri.key";
            String signatureFile = "signature.sig";
            String senderPubKeyF = "senderPub.key";
            String secretKeyF = "secretKey.ser";
            String combinedFile = "combinedFile.txt";
            String encryptedFile = "encryptedFile.enc";
            String recipientPubKeyF = "receiverPub.key";
            String encryptedSecretKeyFile = "encryptedSecretKey.dat";
            String finalOutput = path + "antmsehdanf.dat";

            // MakeDEnvelope 클래스 호출 (파일 경로를 명시하지 않고 파일 이름만 사용)
            MakeDEnvelope.execute(originFile, hashFile, senderPriKeyF, signatureFile, senderPubKeyF, 
            		secretKeyF, combinedFile, encryptedFile, recipientPubKeyF, encryptedSecretKeyFile, finalOutput);
            request.setAttribute("message", "동물 정보가 성공적으로 등록되고 파일에 저장되었습니다!");
        } catch (Exception e) {
        	e.printStackTrace();
            request.setAttribute("message", "오류 발생: " + e.getMessage());
        }

        //request.getRequestDispatcher("/.jsp").forward(request, response);
    }
}