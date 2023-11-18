package local.loeches.poseidonbackend.dao.request;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Enterprise {

    private @Id @GeneratedValue Long id;
    private String name;
    private String projectLeader;
    private String other_information;
    private String filePath;

    public String getOther_information() {
        return other_information;
    }

    public void setOther_information(String other_information) {
        this.other_information = other_information;
    }

    public Enterprise() {
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Enterprise(String name, String projectLeader, String other_information, String filePath)
    {

        this.name = name;
        this.projectLeader = projectLeader;
        this.other_information=other_information;
        this.filePath=filePath;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }
}
