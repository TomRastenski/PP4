package pl.trastenski.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import pl.trastenski.ecommerce.catalog.ArrayListProductStorage;
import pl.trastenski.ecommerce.catalog.ProductCatalog;

import java.math.BigDecimal;


@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createMyCatalog() {
        var catalog = new ProductCatalog(new ArrayListProductStorage());
        var id1 = catalog.addProduct("Lego set 8083", "Nice one");
        catalog.changePrice(id1, BigDecimal.valueOf(10, 10));

        catalog.addProduct("Lego set 8083", "Nice one");
        catalog.addProduct("Cobi blocks", "Nice one");

        return catalog;
    }
}
