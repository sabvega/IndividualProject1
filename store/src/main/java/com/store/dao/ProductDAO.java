package dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;


public class ProductDAO {
  private JdbcTemplate jdbcTemplate;
	  private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/storeproject";
    private static final String dbUsername = "root";
    private static final String dbPassword = "GreatUF123!";

    public ProductDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public ProductDAO() {
    this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
}

    public Product createProduct(Product product){
        //When user wants to add a new product to the product table
        //updating database

        this.jdbcTemplate.update("INSERT INTO products (itemId, name, msrp, salePrice, upc, shortDescription, brandName, size, color, gender,) VALUES (?, ?, ?)",
           product.getItemId(), product.getName(), product.getMsrp(), product.getSalePrice(), product.getUpc(), product.getShortDescription(), product.getBrandName(), product.getSize(), product.getColor(), product.getGender()
        );
        return product;
    }

    public Product getProduct(int itemId){
      //list product by ID
        Product product = new Product(itemId);

        this.jdbcTemplate.query( "SELECT * FROM products WHERE itemId = ?", new Object[] { itemId },
        (rs, rowNum) -> new Product(rs.getString("name"), rs.getInt("itemId") rs.getDouble("salePrice"), rs.getInt("upc"),
                        rs.getString("shortDescription"), rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"))
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
        new Object[] { },
        (rs, rowNum) -> new Product(rs.getInt("itemId"),  rs.getString("name"), rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getInt("upc"),
        rs.getString("shortDescription"), rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"))
        ).forEach(product ->
            products.add(product) );
        return products;
    }

    public Collection<Product> getProductByKeyword(String keyword){
      //listing items by keyword
        Collection<Product> products = new ArrayList<Product>();

        this.jdbcTemplate.query(
        "SELECT * FROM products WHERE name LIKE ? OR shortDescription LIKE ? OR brandName LIKE ? OR size LIKE ? OR color LIKE ? OR gender LIKE ?",
        new Object[]
        { "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%", "%"+keyword+"%"},
        (rs, rowNum) ->
                new Product(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"), rs.getDouble("salePrice"), rs.getInt("upc"),
                rs.getString("shortDescription"), rs.getString("brandName"), rs.getString("size"), rs.getString("color"), rs.getString("gender"))
            ).forEach(product -> products.add(product) );
      return products;
    }


    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(dbUsername);
        dataSource.setPassword(dbPassword);
        return dataSource;
    }
}
