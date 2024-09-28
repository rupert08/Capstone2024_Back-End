package za.ac.cput.factory;

import za.ac.cput.domain.Photo;
import za.ac.cput.domain.Product;

public class PhotoFactory {
    public static Photo createPhoto(Product product, String imageData, String name, String photoType) throws Exception{
        if(product != null && imageData != null && name != null) {
            return Photo.builder()
                    .product(product)
                    .image(imageData)
                    .name(name)
                    .photoType(photoType)
                    .build();
        }
        return null;
    }
}
