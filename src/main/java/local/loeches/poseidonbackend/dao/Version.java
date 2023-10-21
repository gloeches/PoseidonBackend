package local.loeches.poseidonbackend.dao;

public class Version {
    private final String versionDate;
    private final String versionContent;


    public Version(){
        this.versionDate="16-Oct-2023";
        this.versionContent="01.01";
    }

    public String getVersionDate() {
        return versionDate;
    }

    public String getVersionContent() {
        return versionContent;
    }
}
