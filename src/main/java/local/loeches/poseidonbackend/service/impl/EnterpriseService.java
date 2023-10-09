package local.loeches.poseidonbackend.service.impl;

import jakarta.transaction.Transactional;
import local.loeches.poseidonbackend.repository.EnterpriseRepository;
import local.loeches.poseidonbackend.repository.KeypassRepository;
import local.loeches.poseidonbackend.dao.request.Keypass;
import local.loeches.poseidonbackend.dao.request.Enterprise;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;
    @Autowired
    private KeypassRepository keypassRepository;
    public ResponseEntity<List<Enterprise>> getAllEnterprises(String name){
        List<Enterprise> enterpriseList=new ArrayList<Enterprise>();
        if(name==null)
            enterpriseRepository.findAll().forEach(enterpriseList::add);

        else
            enterpriseRepository.findByNameContaining(name).forEach(enterpriseList::add);
        if(enterpriseList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(enterpriseList,HttpStatus.OK);
    }
    public ResponseEntity<List<Keypass>> getAllKeypassByEnterpriseId(long id){
        List<Keypass> keypasses=  keypassRepository.findByEnterpriseId(id);
        keypasses.forEach(p->p.getEnterprise().getName());


        return new ResponseEntity<>(keypasses,HttpStatus.OK);
    }

 public ResponseEntity<Keypass> createKeypass(long id, Keypass keypassRequest){
     Keypass keypass=enterpriseRepository.findById(id).map(enterprise -> {
         keypassRequest.setEnterprise(enterprise);
         return keypassRepository.save(keypassRequest);
     }).orElseThrow(() -> new ResourceNotFoundException("Not found here"));
     return new ResponseEntity<>(keypass, HttpStatus.CREATED);

 }
 public ResponseEntity<Enterprise>getEnterpriseById(long id){
        Optional<Enterprise> enterpriseOptional=enterpriseRepository.findById(id);
        if (enterpriseOptional.isPresent()) {
            return new ResponseEntity<Enterprise>(enterpriseOptional.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
 }
    public ResponseEntity<Keypass>getKeypassById(long id){
        Optional<Keypass> keypassOptional=keypassRepository.findById(id);
        if (keypassOptional.isPresent()) {
            return new ResponseEntity<Keypass>(keypassOptional.get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
