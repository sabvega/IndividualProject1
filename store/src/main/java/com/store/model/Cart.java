package com.store.model;

import java.util.Collection;

public class Cart {
    private int id;
    private String username;
    private int completed;
    private Collection<Product> products;

    public Cart(int id, String username, int completed, Collection<Product> products) {
        this.id = id;
        this.username = username;
        this.completed = completed;
        this.products = products;
    }

    public int getId(){
      return id;
    }

    public void setId(int id){
      this.id = id;
    }

    public void setUsername( String username){
      this.username = username;
    }
    public String getUsername() {
        return username;
    }

    public int getCompleted(){
      return completed;
    }

    public void setCompleted( int completed){
      this.completed = completed;
    }

    public void setList(int )

    @Override
    public String toString() {

        return String.format(
                "Cart[id=%d, username='%s', completed='%s']",
                id, username, completed);
    }

}
