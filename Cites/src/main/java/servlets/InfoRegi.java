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

        String senderPath = AnimalFile.SENDER_PATH;
        String path = AnimalFile.PUBLIC_PATH;

        try {
            // saveAnimalInfoToFile 메서드를 호출하고 반환값을 배열로 받습니다.
            String[] fileInfo = AnimalInfoSaver.saveAnimalInfoToFile(animalInfo);

            // 반환된 파일명과 경로를 사용합니다.
            String originF = fileInfo[1];

            String signatureF = "dsignature.sig";
            String senderPubKeyF = "senderPub.key";
            String senderPriKeyF = "senderPri.key";
            String secretKeyF = "secretKey.key";
            String receiverPubKeyF = "receiverPub.key";
            String encryptedSecretKeyF = "DigitalEnvelope.dev"; //?

            // MakeDEnvelope 클래스 호출
            MakeEnc.execute(originF, senderPriKeyF, signatureF, senderPubKeyF, secretKeyF, receiverPubKeyF, encryptedSecretKeyF, senderPath, path);
            request.setAttribute("message", "동물 정보가 성공적으로 등록되고 파일에 저장되었습니다!");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "오류 발생: " + e.getMessage());
        }

        // 결과 페이지로 포워딩
        request.getRequestDispatcher("/Main.jsp").forward(request, response);
    }
}