package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;


public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Product createProduct(Product product){
        //When user wants to add a new product to the product table
        //updating database

        this.jdbcTemplate.update("INSERT INTO products (id, itemId, name, msrp, salePrice, upc, shortDescription, brandName, size, color, gender,) VALUES (?, ?, ?)",
          product.getId(), product.getItemId(), product.getName(), product.getMsrp(), product.getSalePrice(), product.getUpc(), product.getShortDescription(), product.getBrandName(), product.getSize(), product.getColor(), product.getGender()
        );
        return product;
    }

    public Product getProduct(int itemId){
      //list product by ID
        Product product = new Product(id);

        this.jdbcTemplate.query( "SELECT * FROM products WHERE itemId = ?", new Object[] { itemId },
        (rs, rowNum) -> new Product(rs.getString("name"), rs.getInt("itemId"))
      ).forEach(addedProduct -> {
          product.setName(addedProduct.getName());
          product.setItemId(addedProduct.getItemId());
          product.setMsrp(addedProduct.getMsrp());
          product.setSalePrice(addedProduct.getSalePrice());
          product.setUpc(addedProduct.getUpc());
          product.setShortDescription(addedProduct.getShortDescription());
          product.setBrandName(addedProduct.getBrandName());
          product.setSize(addedProduct.getSize());
          product.setColor(addedProduct.getColor());
          product.setGender(addedProduct.getGender());
        }
      );
        return product;
    }

    public Collection<Product> getAllProducts(){
      //listing all items
        Collection<Product> products = new ArrayList<Product>();

        this.jdbcTemplate.query("SELECT * FROM products",
        (rs, rowNum) -> new Product(rs.getInt("id"), rs.getString("title"), rs.getInt("album"))
        ).forEach(product -> products.add(product) );

        return products;
    }

    public Collection<Product> getProductByKeyword(String keyword){
      //listing items by keyword
        Collection<Product> products = new ArrayList<Product>();

        this.jdbcTemplate.query(
                "SELECT id, title FROM products WHERE name = ?", new Object[] { keyword },
                (rs, rowNum) -> new Product(rs.getInt("id"), rs.getString("title"), keyword)
        ).forEach(product -> products.add(product) );

        return products;
    }

    public boolean deleteProduct(Product product){
      //delete specific item
        boolean success = false;
        //remove this product based off id from the database

        this.jdbcTemplate.update( "DELETE FROM products WHERE id = ? ",
          product.getId()
        );
        success = true;
        return success;
    }

    
}
