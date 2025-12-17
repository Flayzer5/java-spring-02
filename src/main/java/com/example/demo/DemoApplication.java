package com.example.demo;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner testLab02(ProductService productService) {
        return (args) -> {
            System.out.println("\n========== LAB 0-2 TEST START ==========");

            System.out.println("-> Creating products via Service...");
            productService.createProduct(new Product(null, "Mouse-DB", 15.0));
            productService.createProduct(new Product(null, "Keyboard-DB", 40.0));

            System.out.println("\n-> Current List in Database:");
            List<Product> allProducts = productService.getAllProducts();
            allProducts.forEach(System.out::println);

            if (!allProducts.isEmpty()) {
                Long idToUpdate = allProducts.get(0).getId();
                System.out.println("\n-> Updating Product with ID " + idToUpdate + "...");

                Product updateInfo = new Product();
                updateInfo.setName("Gaming Mouse PRO");
                updateInfo.setPrice(99.0);

                Product updated = productService.updateProduct(idToUpdate, updateInfo);
                System.out.println("Updated: " + updated);
            }

            List<Product> currentProducts = productService.getAllProducts();
            if (currentProducts.size() > 1) {
                Long idToDelete = currentProducts.get(1).getId();
                System.out.println("\n-> Deleting Product with ID " + idToDelete + "...");
                productService.deleteProduct(idToDelete);
                System.out.println("-> Product deleted.");
            }

            System.out.println("\n-> Final List in Database:");
            productService.getAllProducts().forEach(System.out::println);

            System.out.println("========== LAB 0-2 TEST END ==========\n");
        };
    }
}
