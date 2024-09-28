package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Photo;
import za.ac.cput.facade.ProductPhoto;
import za.ac.cput.service.PhotoService;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:5119", maxAge = 3600)
@RestController
@RequestMapping("/photo")
public class PhotoController {
    private PhotoService photoService;
    private ProductPhoto productPhoto;

    @Autowired
    PhotoController (PhotoService photoService, ProductPhoto productPhoto){
        this.photoService = photoService;
        this.productPhoto = productPhoto;
    }

    @PostMapping("/create")
    public ResponseEntity<Photo> save(@RequestParam("image") MultipartFile file, @RequestParam("productId") Long id) throws Exception {
        Photo save = productPhoto.savePhoto(id,file);
        if(save == null){
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(photoService.create(save));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        photoService.delete(id);
    }

    @GetMapping(value="/getAll")
    public ResponseEntity<Set<Photo>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(photoService.getAll());

    }
}
