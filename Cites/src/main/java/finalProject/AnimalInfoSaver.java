// 컴퓨터학과_20211743_김연진

package finalProject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AnimalInfoSaver {	
	
	public static String[] saveAnimalInfoToFile(AnimalInfo animal) throws IOException {
		// 파일 이름을 동물의 학명으로 설정하고, 사용할 수 없는 문자를 제거
		String animalName = animal.getSciName().replaceAll("[^a-zA-Z0-9.-]", "_") + ".txt";
		// 파일을 저장할 절대 경로 설정
		File animalF = new File(AnimalFile.SENDER_PATH, animalName);

		// 디렉토리가 존재하지 않으면 생성
		createParentDirectory(animalF);

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(animalF))) {
			writeAnimalInfo(animal, writer);
		}

		// 파일명과 전체 경로를 반환
		return new String[]{animalName, animalF.getAbsolutePath()};
	}

	private static void createParentDirectory(File file) {
		File parentDir = file.getParentFile();
		if (!parentDir.exists()) {
			parentDir.mkdirs();
		}
	}

	private static void writeAnimalInfo(AnimalInfo animal, BufferedWriter writer) throws IOException {
		writer.write( animal.getSciName() + "\n");
		writer.write( animal.getComName() + "\n");
		writer.write(animal.getTaxon() + "\n");
		writer.write(animal.getMorphChar() + "\n");
		writer.write(animal.getSex() + "\n");
		writer.write(animal.getReproInfo() + "\n");
		writer.write(animal.getLocDiscovery() + "\n");
		writer.write(animal.getHabType() + "\n");
		writer.write(animal.getHabCond() + "\n");
		writer.write(animal.getDiet() + "\n");
		writer.write(animal.getActPatterns() + "\n");
		writer.write(animal.getSocStruct() + "\n");
		writer.write(animal.getConsStatus() + "\n");
		writer.write(animal.getThreats() + "\n");
		writer.write(animal.getConsActions() + "\n");
	}
}
