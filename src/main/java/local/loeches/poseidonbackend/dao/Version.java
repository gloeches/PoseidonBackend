package local.loeches.poseidonbackend.dao;

public class Version {
    private final String versionDate;
    private final String versionContent;


    public Version(){
        this.versionDate="31-Oct-2023";
        this.versionContent="01.02";
    }

    public String getVersionDate() {
        return versionDate;
    }

    public String getVersionContent() {
        return versionContent;
    }
}
