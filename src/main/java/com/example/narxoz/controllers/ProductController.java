package com.example.narxoz.controllers;
import com.example.narxoz.lab2.Items;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import java.util.ArrayList;
@Controller
public class ProductController {
    ArrayList<Items> products = new ArrayList<>();
    @GetMapping(value = "/")
    public String indexPage(Model model){
        products.add(new Items(1L,"MacBook Pro","8 GB RAM 255 GB SSD Intel Core i7", 1199.99));
        products.add(new Items(2L,"MacBook Pro","16 GB RAM 500 GB SSD Apple M1", 1499.99));
        products.add(new Items(3L,"MacBook Pro","16 GB RAM 1 TB SSD Apple M1", 1799.99));
        products.add(new Items(4L,"ASUS Tuf Gaming","16 GB RAM 500 GB SSD Intel Core i7", 1299.99));
        products.add(new Items(5L,"HP Laser Pro","8 GB RAM 500 GB SSD Intel Core i5", 999.99));
        products.add(new Items(6L,"Lenovo Legion","32 GB RAM 512 GB SSD Intel Core i7", 1399.99));
        model.addAttribute("products", products);
        return "index";
    }
}
