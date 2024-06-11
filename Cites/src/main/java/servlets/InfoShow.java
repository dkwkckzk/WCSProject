package servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import finalProject.AnimalFile;
import finalProject.MakeDec;

/**
 * Servlet implementation class InfoShow
 */
@WebServlet("/InfoShow")
public class InfoShow extends HttpServlet {
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // 사용은 안하지만 남김.
        // 폼 데이터 수집
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

        // 요청에 속성 설정
        request.setAttribute("sciName", sciName);
        request.setAttribute("comName", comName);
        request.setAttribute("taxon", taxon);
        request.setAttribute("morphChar", morphChar);
        request.setAttribute("sex", sex);
        request.setAttribute("reproInfo", reproInfo);
        request.setAttribute("locDiscovery", locDiscovery);
        request.setAttribute("habType", habType);
        request.setAttribute("habCond", habCond);
        request.setAttribute("diet", diet);
        request.setAttribute("actPatterns", actPatterns);
        request.setAttribute("socStruct", socStruct);
        request.setAttribute("consStatus", consStatus);
        request.setAttribute("threats", threats);
        request.setAttribute("consActions", consActions);

        // 결과 페이지로 포워드
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
    
    
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = AnimalFile.SENDER_PATH + "/";
        try {
            // 전자봉투 관련 파일 및 개인 키 파일 경로 설정
            String envelopeFile = path + "DigitalEnvelope.dev";
            String receiverPriKeyFile = "receiverPri.key";
            String enOrigin = path + "enc_Ori.dat";
            String enSig = path + "enc_Sig.dat";
            String enSendPubK = "enc_SPubK.dat";

            // MakeDec 클래스의 execute 메서드 호출
            boolean isVerified = MakeDec.execute(envelopeFile, receiverPriKeyFile, enOrigin, enSig, enSendPubK);

            // 전자서명 검증 결과에 따라 파일 내용 출력
            if (isVerified) {
                System.out.println("전자서명 검증 성공");

                // 전자서명 검증 성공 후 파일에서 데이터 읽어오기
                Path decryptedFilePath = Paths.get(new File(AnimalFile.RECIVER_PATH, "dec_Ori.txt").getAbsolutePath());
                List<String> lines = Files.readAllLines(decryptedFilePath);

                // 파일에서 읽어온 데이터를 파라미터로 설정
                request.setAttribute("sciName", lines.get(0));
                request.setAttribute("comName", lines.get(1));
                request.setAttribute("taxon", lines.get(2));
                request.setAttribute("morphChar", lines.get(3));
                request.setAttribute("sex", lines.get(4));
                request.setAttribute("reproInfo", lines.get(5));
                request.setAttribute("locDiscovery", lines.get(6));
                request.setAttribute("habType", lines.get(7));
                request.setAttribute("habCond", lines.get(8));
                request.setAttribute("diet", lines.get(9));
                request.setAttribute("actPatterns", lines.get(10));
                request.setAttribute("socStruct", lines.get(11));
                request.setAttribute("consStatus", lines.get(12));
                request.setAttribute("threats", lines.get(13));
                request.setAttribute("consActions", lines.get(14));

                // 결과 페이지로 포워드
                request.getRequestDispatcher("ShowInfo.jsp").forward(request, response);
            } else {
                System.out.println("전자서명 검증 실패");
                response.setContentType("text/plain; charset=UTF-8");
                response.getWriter().write("전자서명 검증 실패");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain; charset=UTF-8");
            response.getWriter().write("오류 발생: " + e.getMessage());
        }
    }
}