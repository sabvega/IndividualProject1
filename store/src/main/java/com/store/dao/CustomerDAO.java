package dao;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.store.model.*;
import java.util.Collection;
import java.util.ArrayList;

@Repository
public class CustomerDAO {
  private JdbcTemplate jdbcTemplate;
	  private static final String driverClassName = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/storeproject";
    private static final String dbUsername = "root";
    private static final String dbPassword = "GreatUF123!";

    public CustomerDAO() {
    		this.jdbcTemplate = new JdbcTemplate(this.getDataSource());
        }

    public CustomerDAO(JdbcTemplate jdbcTemp) {
        this.jdbcTemplate = jdbcTemp;
    }

    public Customer createCustomer(Customer customer){
       //when user wants to we add customer and update the table
       this.jdbcTemplate.update(
           "INSERT INTO customers (fname, lname, username, email) VALUES (?,?, ?, ?)", customer.getFname(), customer.getLname(). customer.getUsername(), customer.getEmail()
       );
        return customer;
    }

    public Customer getCustomerUsername(String username){
        Customer customer = new Customer("","", username, "");

        //find customer that contains corresponding username
        this.jdbcTemplate.query(
                "SELECT * FROM customers WHERE username = ?", new Object[] { username },
                (rs, rowNum) -> new Customer(rs.getString("username"), rs.getInt("id"))
        ).forEach(addedCustomer-> {
            customer.setFname(addedCustomer.getFname());
            customer.setLname(addedCustomer.getLname());
            customer.setUsername(addedCustomer.getUsername());
            customer.setEmail(addedCustomer.setEmail());
        });
        return customer;
    }

    // public Collection<Customer> getAllCustomers(){
    //     Collection<Customer> customers = new ArrayList<Customer>();
    //
    //     this.jdbcTemplate.query(
    //             "SELECT * FROM customers", new Object[] { },
    //             (rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("username"))
    //     ).forEach(customer -> customers.add(customer));
    //
    //     return customers;
    // }

    public Customer updateCustomer(Customer customer){
        //changes information in the customer depending on what needs to be updated
          this.jdbcTemplate.update("UPDATE customers SET fname = ?,  lname = ?, email = ? WHERE username = ? ",
          customer.getFname(), customer.getLname(), customer.getEmail(), customer.getUsername()
        );
        return customer;
    }

    public boolean deleteCustomer(String username){
        boolean success = false;
        //deletes the customer specified by id and notifies us by returning
        //a boolean

        this.jdbcTemplate.update("DELETE FROM customers WHERE username = ? ", username  );
        success = true;
        return success;
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
