package za.ac.cput.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import za.ac.cput.domain.Photo;
import za.ac.cput.domain.Product;
import za.ac.cput.factory.PhotoFactory;
import za.ac.cput.service.PhotoService;
import za.ac.cput.service.ProductService;

import java.io.File;
import java.io.IOException;

@Component
public class ProductPhoto {
    private PhotoService photoService;
    private ProductService productService;
    private final String FOLDER_PATH = "C:\\Users\\User\\OneDrive\\Desktop\\Hardware-Ecommerce (24Aug)\\Images";

    @Autowired
    public ProductPhoto(PhotoService photoService, ProductService productService) {
        this.photoService = photoService;
        this.productService = productService;
    }

    public Photo savePhoto(Long id, MultipartFile file) throws Exception {
        Product obj = productService.read(id);

        if(obj == null){
            return null;
        }

        String filePath = FOLDER_PATH + file.getOriginalFilename();

        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            System.out.println("Error:" + e.getLocalizedMessage());
            return null;
        }

        return PhotoFactory.createPhoto(obj, filePath, file.getOriginalFilename(), file.getContentType());
    }
}
