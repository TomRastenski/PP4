package pl.trastenski.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import pl.trastenski.ecommerce.catalog.Product;
import pl.trastenski.ecommerce.catalog.ProductCatalog;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class AppHTTPTest {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate http;
    @Autowired
    ProductCatalog catalog;

    @Test
    void itLoadEcommerceHomepage() {
        // Arrange
        var url = String.format("http://localhost:%s", port);
        // Act
        ResponseEntity<String> response = http.getForEntity(url, String.class);
        // Assertion
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("My ecommerce"));
    }

    @Test
    void itLoadsProductsOverHttp() {
        catalog.addProduct("My example product", "nice one");
        var url = String.format("http://localhost:%s/%s", port, "api/products");

        ResponseEntity<Product[]> response = http.getForEntity(url, Product[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody())
                .hasSizeGreaterThanOrEqualTo(1)
                .extracting("name")
                .contains("My example product");
    }
}
