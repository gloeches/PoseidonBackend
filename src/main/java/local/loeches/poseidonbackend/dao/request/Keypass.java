package local.loeches.poseidonbackend.dao.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import local.loeches.poseidonbackend.entities.usertype;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Keypass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "keypass_generator")
    private Long id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "usertype",length = 20)
    private local.loeches.poseidonbackend.entities.usertype usertype;
    private String notes;



    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "enterprise_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Enterprise enterprise;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public local.loeches.poseidonbackend.entities.usertype getUsertype() {
        return usertype;
    }

    public void setUsertype(local.loeches.poseidonbackend.entities.usertype usertype) {
        this.usertype = usertype;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}