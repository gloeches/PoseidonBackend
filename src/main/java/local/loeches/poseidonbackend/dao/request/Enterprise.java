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

    public Enterprise() {
    }

    public Enterprise(String name, String projectLeader)
    {

        this.name = name;
        this.projectLeader = projectLeader;

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
