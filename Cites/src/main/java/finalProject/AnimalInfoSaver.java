// 컴퓨터학과_20211743_김연진

package finalProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AnimalInfoSaver {
    public static String saveAnimalInfoToFile(AnimalInfo animal) throws IOException {
        // 파일 이름을 동물의 학명으로 설정하고, 사용할 수 없는 문자를 제거
        String fileName = animal.getSciName().replaceAll("[^a-zA-Z0-9.-]", "_") + ".txt";
        // 파일을 저장할 절대 경로 설정
        String fullFileN = AnimalFile.DIRECTORY_PATH + fileName;

        // 디렉토리가 존재하지 않으면 생성
        File file = new File(fullFileN);
        file.getParentFile().mkdirs();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            writer.write("Scientific Name: " + animal.getSciName() + "\n");
            writer.write("Common Name: " + animal.getComName() + "\n");
            writer.write("Taxon: " + animal.getTaxon() + "\n");
            writer.write("Morphological Characteristics: " + animal.getMorphChar() + "\n");
            writer.write("Sex: " + animal.getSex() + "\n");
            writer.write("Reproductive Information: " + animal.getReproInfo() + "\n");
            writer.write("Location of Discovery: " + animal.getLocDiscovery() + "\n");
            writer.write("Habitat Type: " + animal.getHabType() + "\n");
            writer.write("Habitat Conditions: " + animal.getHabCond() + "\n");
            writer.write("Diet: " + animal.getDiet() + "\n");
            writer.write("Activity Patterns: " + animal.getActPatterns() + "\n");
            writer.write("Social Structure: " + animal.getSocStruct() + "\n");
            writer.write("Conservation Status: " + animal.getConsStatus() + "\n");
            writer.write("Threats: " + animal.getThreats() + "\n");
            writer.write("Conservation Actions: " + animal.getConsActions() + "\n");
        }
        //return new String[] { fileName, fullFileN }; // 파일명과 경로 함께 return 
        return fullFileN;
    }
}

