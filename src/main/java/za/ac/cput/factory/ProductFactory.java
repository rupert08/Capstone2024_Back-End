package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.SQLException;

public class ProductFactory {
    public static Product createProduct(String name, String description, BigDecimal price, /*String imagePath,*/ Category category) throws Exception {
        //Blob imageBlob = null;
        if (Helper.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (Helper.isNullOrEmpty(description)) {
            description = "No description provided";
        }
        if (Helper.isNullOrEmpty(String.valueOf(category))) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price cannot be null or negative");
        }
//        if (!Helper.isNullOrEmpty(imagePath)) {
//            try {
//                byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
//                imageBlob = new SerialBlob(imageBytes);
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to read Product image file: " + imagePath, e);
//            } catch (SQLException e) {
//                throw new RuntimeException("Failed to create Blob from image bytes", e);
//            }
//        } else {
//            imagePath = "Image path for " + name + " product was null or empty";
//            System.out.println(imagePath);
   //     }

        return Product.builder()
                .name(name)
                .description(description)
                .price(price.setScale(2, RoundingMode.HALF_UP))
                //.image(imageBlob)
                .category(category)
                .build();
    }
}