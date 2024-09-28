package za.ac.cput.factory;

import za.ac.cput.domain.Category;
import za.ac.cput.domain.Product;
import za.ac.cput.util.Helper;

import javax.sql.rowset.serial.SerialBlob;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;
public class CategoryFactory {

    public static Category createCategory(String name, String description, /*String imagePath,*/ Set<Product> products) throws Exception {
        // Check if the imagePath is null or empty and set a default string if true
//        Blob imageBlob = null;
//        if (imagePath != null && !imagePath.isEmpty()) {
//            // Attempt to read the image file and create a Blob if the path is valid
//            try {
//                byte[] imageBytes = Files.readAllBytes(Paths.get(imagePath));
//                imageBlob = new SerialBlob(imageBytes);
//            } catch (Exception e) {
//                // Handle potential file reading issues (e.g., file not found)
//                System.out.println("Image not found or invalid path: " + imagePath+ " " + e.getMessage());
//                imagePath = "image not found or invalid path";
//            }
//        } else {
//            // Default message for invalid image paths
//            imagePath = "image not found or invalid path";
//            System.out.println(imagePath);
//        }

        if(Helper.isNullOrEmpty(name)){
            throw new IllegalArgumentException("Category name cannot be null/empty");
        }
        if(Helper.isNullOrEmpty(description)){
            description = "no description";
        }

        if(Helper.isNullOrEmpty(String.valueOf(products))){
            products = new HashSet<>();
        }

        Category category = Category.builder()
                .name(name)
                .description(description)
                //.image(imagePath)
                .products(products)
                .build();
//        if (products != null) {
//            for (Product product : products) {
//                category.addProduct(product);
//            }
//        }
        return category;
    }
    public static Category createCategoryWithoutProducts(String name, String description/*, String imagePath*/) throws Exception {
        return createCategory(name, description, /*imagePath,*/ new HashSet<>());
    }

}