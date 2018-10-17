package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

public class CartDAO {
    private JdbcTemplate jdbcTemplate;

    public CartDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Cart createCart(Cart cart){
       //when user wants to we add cart and update the table

       this.jdbcTemplate.update(
           "INSERT INTO carts (id, fname, lname, username, email, uniqueID) VALUES (?,?)", cart.getId(), cart.getFname(), cart.getLname(). cart.getUsername(), cart.getEmail()
       );
        return cart;
    }

    public Cart getCart(String username){
      //show a shopping cart associated with a specific user
        Cart cart = new Cart(id, "");
        //Part of the insert/update function for carts

        this.jdbcTemplate.query(
                "SELECT * FROM carts WHERE username = ?", new Object[] { username},
                (rs, rowNum) -> new Cart(rs.getInt("id"), rs.getString("username"))
        ).forEach(addedCart-> {
            cart.setFname(addedCart.getFname());
            cart.setLname(addedCart.getLname());
            cart.setUsername(addedCart.getUsername());
            cart.setEmail(addedCart.setEmail());
        });
        return cart;
    }

    public Collection<Cart> getAllCarts(){
        Collection<Cart> carts = new ArrayList<Cart>();
        this.jdbcTemplate.query(
                "SELECT * FROM carts", new Object[] { },
                (rs, rowNum) -> new Cart(rs.getInt("id"), rs.getString("username"))
        ).forEach(cart -> carts.add(cart));

        return carts;
    }

    public Cart updateCart(Cart cart){
        //changes information in the cart depending on what needs to be updated
          this.jdbcTemplate.update("UPDATE carts SET title = ? WHERE id = ? ",
          cart.getTitle(), cart.getId()
        );
        return cart;
    }

    public boolean deleteCart(Cart cart){
        boolean success = false;
        //deletes the cart specified by id and notifies us by returning
        //a boolean

        this.jdbcTemplate.update("DELETE FROM carts WHERE id = ? ",
          cart.getId()
        );
        success = true;
        return success;
    }

    public int purchaseCart(Cart cart){
      this.jdbcTemplate.update("UPADTE carts SET completed = ? WHERE username = ? ",
      1, cart.getUsername());

      return cart;
    }

}
