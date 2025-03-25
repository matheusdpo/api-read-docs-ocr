package br.com.lumen.paypal.v1.thymeleaf.model;

public class Product {
    int id;
    String name;
    double price;
    String image;

    public Product(int id, String name, double price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    // Getters públicos (ESSENCIAIS para o Thymeleaf)
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }
}
