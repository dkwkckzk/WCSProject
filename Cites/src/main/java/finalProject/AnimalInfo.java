// 컴퓨터학과_20211743_김연진

package finalProject;

public class AnimalInfo {
    private String sciName; // 학명
    private String comName; // 일반명
    private String taxon; // 분류
    private String morphChar; // 외형적 특징
    private String sex; // 성별
    private String reproInfo; // 생식 정보
    private String locDiscovery; // 발견 위치
    private String habType; // 서식지 유형
    private String habCond; // 서식지 조건
    private String diet; // 식이 습성
    private String actPatterns; // 활동 패턴
    private String socStruct; // 사회적 구조
    private String consStatus; // 보호 등급
    private String threats; // 위협 요소
    private String consActions; // 보호 조치

    // 생성자
    public AnimalInfo(String sciName, String comName, String taxon, 
                      String morphChar, String sex, String reproInfo, 
                      String locDiscovery, String habType, String habCond, 
                      String diet, String actPatterns, String socStruct, 
                      String consStatus, String threats, String consActions) {
        this.sciName = sciName;
        this.comName = comName;
        this.taxon = taxon;
        this.morphChar = morphChar;
        this.sex = sex;
        this.reproInfo = reproInfo;
        this.locDiscovery = locDiscovery;
        this.habType = habType;
        this.habCond = habCond;
        this.diet = diet;
        this.actPatterns = actPatterns;
        this.socStruct = socStruct;
        this.consStatus = consStatus;
        this.threats = threats;
        this.consActions = consActions;
    }

    // getter 메서드들
    public String getSciName() { return sciName; }
    public String getComName() { return comName; }
    public String getTaxon() { return taxon; }
    public String getMorphChar() { return morphChar; }
    public String getSex() { return sex; }
    public String getReproInfo() { return reproInfo; }
    public String getLocDiscovery() { return locDiscovery; }
    public String getHabType() { return habType; }
    public String getHabCond() { return habCond; }
    public String getDiet() { return diet; }
    public String getActPatterns() { return actPatterns; }
    public String getSocStruct() { return socStruct; }
    public String getConsStatus() { return consStatus; }
    public String getThreats() { return threats; }
    public String getConsActions() { return consActions; }
}
