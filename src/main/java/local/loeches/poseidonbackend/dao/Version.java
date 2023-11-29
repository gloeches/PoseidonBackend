package local.loeches.poseidonbackend.dao;

public class Version {
    private final String versionDate;
    private final String versionContent;


    public Version(){
        this.versionDate="29-Nov-2023";
        this.versionContent="02.00";
    }

    public String getVersionDate() {
        return versionDate;
    }

    public String getVersionContent() {
        return versionContent;
    }
}
