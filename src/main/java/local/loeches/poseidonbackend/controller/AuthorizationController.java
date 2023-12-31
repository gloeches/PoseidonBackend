package local.loeches.poseidonbackend.controller;

import local.loeches.poseidonbackend.dao.Version;
import local.loeches.poseidonbackend.dao.request.Enterprise;
import local.loeches.poseidonbackend.dao.request.Keypass;
import local.loeches.poseidonbackend.service.impl.EnterpriseService;
import local.loeches.poseidonbackend.repository.EnterpriseRepository;
import local.loeches.poseidonbackend.dao.request.SigninRequest;
import local.loeches.poseidonbackend.entities.Role;
import local.loeches.poseidonbackend.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthorizationController {
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    private EnterpriseService enterpriseService;
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
/*    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Here is your resource");
    } */
/*    public Version version(String versionDate, String versionContent) {
        return new Version();
    } */
    @GetMapping("/resource")
    public SigninRequest user (){
        SigninRequest myUser= new SigninRequest("a@gmail.com","1234");
        myUser.setEmail("a.gmail.com");
        return myUser;
    }
    @GetMapping("/enterprises")
    public ResponseEntity<List<Enterprise>> getAllEnterprises(@RequestParam(required=false)  String name){
        return enterpriseService.getAllEnterprises(name);
    }
    @GetMapping("/admin/enterprises")
    public ResponseEntity<List<Enterprise>> getAllEnterprisesAdmin(@RequestParam(required=false)  String name){
        return enterpriseService.getAllEnterprises(name);
    }
    @GetMapping("/enterprises/{enterpriseId}/keypass")
    public ResponseEntity<List<Keypass>> getAllkeypassByEnterpriseID(@PathVariable(value="enterpriseId") long enterpriseId){
        return enterpriseService.getAllKeypassByEnterpriseId(enterpriseId);
    }
    @PostMapping("/enterprise")
    public ResponseEntity<Enterprise> createEnterprise(@RequestBody Enterprise enterprise){
 //       Enterprise _enterprise= enterpriseRepository.save (new Enterprise(enterprise.getName(),enterprise.getProjectLeader(),enterprise.getOther_information(),enterprise.getFilePath()));
        Enterprise _enterprise= enterpriseRepository.save (enterprise);
        return new ResponseEntity<>(_enterprise, HttpStatus.CREATED);
    }


    @PostMapping("/enterprises/{enterpriseId}/keypass")
    public ResponseEntity<Keypass>  createKeypass(@PathVariable(value="enterpriseId") Long enterpriseId, @RequestBody Keypass keypassRequest){
        return enterpriseService.createKeypass(enterpriseId,keypassRequest);
    }
    @GetMapping("/enterprise/{id}")
    public ResponseEntity<Enterprise> getEnterpriseById (@PathVariable(value="id") Long enterpriseId){
       return enterpriseService.getEnterpriseById(enterpriseId);
    }

    @GetMapping("/keypass/{id}")
    public ResponseEntity<Keypass> getKeypassById (@PathVariable(value="id") Long keypassId){
        return enterpriseService.getKeypassById(keypassId);
    }

    @DeleteMapping("/keypass/{id}")
    public ResponseEntity<Keypass> delKeypassById (@PathVariable(value="id") Long keypassId){
        return enterpriseService.delKeypassById(keypassId);
    }

    @DeleteMapping("/admin/enterprise/{id}")
    public ResponseEntity<Enterprise> delEnterpriseById2 (@PathVariable(value="id") Long emterpriseId){
        return enterpriseService.delEnterpriseById(emterpriseId);
    }
    @DeleteMapping("/enterprise/{id}")
    public ResponseEntity<Enterprise> delEnterpriseById (@PathVariable(value="id") Long emterpriseId){
        return enterpriseService.delEnterpriseById(emterpriseId);
    }
    @PostMapping("/enterprise/{id}/filesystem2")
    public ResponseEntity<?> uploadImageToFileSystem2(@PathVariable(value="id") long enterpriseId) throws IOException {
        String salida="ok";
        return ResponseEntity.status(HttpStatus.OK).body(salida);
    }
    @PostMapping("/enterprise/{id}/filesystem")
    public ResponseEntity<?> uploadImageToFileSystem(@PathVariable(value="id") long enterpriseId,@RequestParam("image")MultipartFile file) throws IOException {
        ResponseEntity<Enterprise> uploadImage = enterpriseService.uploadImageToFile(enterpriseId,file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
    }

    @PostMapping("/enterprise_upload")
    public ResponseEntity<Enterprise> createEnterpriseUpload(@RequestBody Enterprise enterprise,@RequestParam("image")MultipartFile file) throws IOException{
        Enterprise _enterprise= enterpriseRepository.save (enterprise);
        ResponseEntity<Enterprise> uploadImage = enterpriseService.uploadImageToFile(_enterprise.getId(),file);
        return new ResponseEntity<>(_enterprise, HttpStatus.CREATED);
    }
    @GetMapping("/enterprise/{id}/filesystem")
    public ResponseEntity<?>downloadImageFromFileSystem(@PathVariable(value="id") long enterpriseId) throws  IOException{
       byte[] downloadFiledata =enterpriseService.downloadImageFromFileSystem(enterpriseId);
       return ResponseEntity.status(HttpStatus.OK)
               .body(downloadFiledata);

    }
}
