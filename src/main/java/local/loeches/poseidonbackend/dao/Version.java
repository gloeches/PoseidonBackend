package local.loeches.poseidonbackend.dao;

public class Version {
    private final String versionDate;
    private final String versionContent;


    public Version(){
        this.versionDate="14-Nov-2023";
        this.versionContent="01.04";
    }

    public String getVersionDate() {
        return versionDate;
    }

    public String getVersionContent() {
        return versionContent;
    }
}
