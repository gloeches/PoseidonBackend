package local.loeches.poseidonbackend.repository;

import local.loeches.poseidonbackend.dao.request.Agilent;
import local.loeches.poseidonbackend.dao.request.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface  AgilentRepository extends JpaRepository<Agilent, Long> {
    List<Agilent> findByMail(String mail);

}
