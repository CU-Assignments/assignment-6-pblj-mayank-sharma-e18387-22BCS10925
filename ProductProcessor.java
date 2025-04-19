import java.util.*;
import java.util.stream.*;

class Product {
    String name;
    String category;
    double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " - ₹" + price;
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 75000),
            new Product("Phone", "Electronics", 45000),
            new Product("Shampoo", "Personal Care", 200),
            new Product("Toothpaste", "Personal Care", 150),
            new Product("T-shirt", "Clothing", 999),
            new Product("Jeans", "Clothing", 1999)
        );

        // Group by category
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        System.out.println("Products Grouped by Category:");
        groupedByCategory.forEach((category, prods) -> {
            System.out.println(category + ": " + prods);
        });

        // Most expensive product in each category
        Map<String, Optional<Product>> maxPriceByCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
            ));

        System.out.println("\nMost Expensive Product in Each Category:");
        maxPriceByCategory.forEach((category, product) ->
            System.out.println(category + ": " + product.orElse(null)));

        // Average price of all products
        double averagePrice = products.stream()
            .mapToDouble(Product::getPrice)
            .average()
            .orElse(0.0);

        System.out.println("\nAverage Price of All Products: ₹" + averagePrice);
    }
}
