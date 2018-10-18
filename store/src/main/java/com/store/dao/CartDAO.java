idpackage dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

public class CartDAO {
  private JdbcTemplate jdbcTemplate;
	  private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/storeproject";
    private static final String dbUsername = "root";
    private static final String dbPassword = "GreatUF123!";

    public CartDAO() {
      this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
  }

    public CartDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Cart createCart(Cart cart){
       this.jdbcTemplate.update(
           "INSERT INTO carts (id, fname, lname, username, email, uniqueID) VALUES (?,?)", cart.getId(), cart.getFname(), cart.getLname(). cart.getUsername(), cart.getEmail()
       );
        return cart;
    }

    public void deleteCart(int cartNum, int productId){
      this.jdbcTemplate.update("DELETE FROM sessions WHERE id = ? AND productId = ?",
                cartNum, productId
        );
    }

    public void addProuct(int productId, String username){
        Cart cart = new Cart(0);
        Product product = new Product(0);

        this.jdbcTemplate.query(  "SELECT * FROM carts WHERE username = ? AND completed = 0", new Object[] { username },
                (rs, rowNum) -> new Cart(rs.getInt("id"))
        ).forEach(finalCart -> cart.setId(finalCart.getId()));

        int idNum = cart.getId();
        if (idNum < 1){
            this.jdbcTemplate.update("INSERT INTO carts (username) VALUES (?)", username);
            this.jdbcTemplate.query("SELECT id FROM carts WHERE username = ? AND completed = 0",
            new Object[] { username },
                    (rs, rowNum) -> new Cart(rs.getInt("id"))
            ).forEach(finalCart -> cart.setId(finalCart.getId()));
        }

        this.jdbcTemplate.query("SELECT * FROM inventory WHERE itemId = ?", new Object[] { productId },
                (rs, rowNum) -> new Product(rs.getInt("itemId"))
        ).forEach(finalCart ->
        product.setItemId(finalCart.getId()));

        int productNum = product.getItemId();
        if (productNum > 0){
            this.jdbcTemplate.update("INSERT INTO sessions (id, productId) VALUES (?, ?)", cart.getId(), productId);
        }
    }

    public Collection<Product> itemsByUsername(String username){
          Collection<Product> itemsList = new ArrayList<Product>();

          this.jdbcTemplate.query(
                  "SELECT * FROM sessions INNER JOIN carts ON carts.id = sessions.id INNER JOIN products ON products.itemId = sessions.productId WHERE carts.username = ? AND carts.completed = 0",
                  new Object[] { username },
                  (rs, rowNum) -> new Product(rs.getInt("itemId"), rs.getString("name"), rs.getDouble("msrp"), rs.getDouble("salePrice"))
          ).forEach(finalProduct -> {
              itemsList.add(finalProduct);
          });

          return cartItems;


    public Collection<Customer> getUsernameByItemId(int itemId){
      Collection<Customer> usernameList = new ArrayList<Customer>();

      this.jdbcTemplate.query(
              "SELECT carts.username FROM sessions LEFT JOIN products ON sessions.productId = products.itemId RIGHT JOIN carts ON carts.id = sessions.id WHERE products.itemId = ?  AND carts.completed = 1",
              new Object[] { itemId },
              (rs, rowNum) -> new Customer(rs.getString("username"))
      ).forEach(finalCust -> {
          usernameList.add(finalCust);
      });

      return usernames;
  }

  public void purchaseItems(int id){
    this.jdbcTemplate.update("UPDATE carts SET completed = 1 WHERE id = ? ",id);
    this.jdbcTemplate.update("DELETE FROM inventory WHERE itemId IN (SELECT productId FROM sessions where id = ?) ",
            id
    );

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
