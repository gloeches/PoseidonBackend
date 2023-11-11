package local.loeches.poseidonbackend.service.impl;

import jakarta.transaction.Transactional;
import local.loeches.poseidonbackend.dao.request.Agilent;
import local.loeches.poseidonbackend.repository.AgilentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class AgilentService {
    @Autowired
    private AgilentRepository agilentRepository;
    public ResponseEntity<List<Agilent>> getMails(String name){
        List<Agilent> agilentList=new ArrayList<Agilent>();
        agilentRepository.findAll().forEach((agilentList::add));
        return new ResponseEntity<>(agilentList,HttpStatus.OK);
    }
    public ResponseEntity<String> IsAgilentMail(String mail) {
        List<Agilent> agilentList=new ArrayList<Agilent>();
        agilentRepository.findByMail(mail).forEach(agilentList::add);

        if(agilentList .isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<String>(HttpStatus.OK);
        }

    }



}
