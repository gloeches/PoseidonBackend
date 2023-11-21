package local.loeches.poseidonbackend.service.impl;

import jakarta.transaction.Transactional;
import local.loeches.poseidonbackend.repository.EnterpriseRepository;
import local.loeches.poseidonbackend.repository.KeypassRepository;
import local.loeches.poseidonbackend.dao.request.Keypass;
import local.loeches.poseidonbackend.dao.request.Enterprise;
import org.aspectj.bridge.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
    private final String FOLDER_PATH="d:/poseidon/files/";
    public ResponseEntity<List<Enterprise>> getAllEnterprises(String name){
        List<Enterprise> enterpriseList=new ArrayList<Enterprise>();
        if(name==null)
            enterpriseRepository.findAll(Sort.by("name")).forEach(enterpriseList::add);

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
    public ResponseEntity<Enterprise> uploadImageToFile(long id,MultipartFile file) throws IOException {
        String finalPath=FOLDER_PATH+id+"/";
        String relativePath=id+"/";
        String fileNameFull=finalPath+file.getOriginalFilename();
        Enterprise enterprise =enterpriseRepository.findById(id).map(_enterprise ->{
            _enterprise.setFilePath(relativePath+file.getOriginalFilename());
            try {
                File dir = new File(finalPath);
                dir.mkdir();
                file.transferTo(new File(fileNameFull));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

                    return enterpriseRepository.save(_enterprise);

                }
                ).orElseThrow(() -> new ResourceNotFoundException("Enterprise not found with id: "+ id));
        return new ResponseEntity<Enterprise>(HttpStatus.OK);
    }
    public byte[] downloadImageFromFileSystem(long id) throws IOException {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        if (enterprise.isPresent()) {
            String filePath = FOLDER_PATH+enterprise.get().getFilePath();
            byte[] images = Files.readAllBytes(new File(filePath).toPath());
            return images;
        }
        return null;
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

    public ResponseEntity<Keypass>delKeypassById(long id){
        try {
            keypassRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception handlerException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<Enterprise>delEnterpriseById(long id){
        try {
            enterpriseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception handlerException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
