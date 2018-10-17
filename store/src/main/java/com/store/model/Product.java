package com.store.model;

public class Product {
    private int itemId, upc, size;
    private double msrp, salePrice;
    private String name, shortDescription, brandName, color, gender;

    public Product(int itemId, int upc, int size, double msrp, double salePrice, String name, String shortDescription, String brandName, String color, String gender) {
        this.itemId = itemId;
        this.upc = upc;
        this.size = size;
        this.msrp = msrp;
        this.salePrice = salePrice;
        this.name = name;
        this.shortDescription = shortDescription;
        this.brandName = brandName;
        this.color = color;
        this.gender = gender;
    }

    public int getIdtemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId= itemId;
    }

    public int getUpc() {
        return upc;
    }

    public void setUpc(int upc) {
        this.upc = upc;
    }

    public int getSize(){
      return size;
    }

    public void setSize(int size){
      this.size = size;
    }

    public double getMsrp(){
      return size;
    }

    public void setMsrp(double msrp){
      this.msrp = msrp;
    }

    public double getSalePrice(){
      return salePrice;
    }

    public void setSalePrice(double salePrice){
      this.salePrice = salePrice;
    }

    public String getName(){
      return name;
    }

    public void setName(String name){
      this.name = name;
    }

    public String getShortDescription(){
      return shortDescription;
    }

    public void setShortDescription(String shortDescription){
      this.shortDescription = shortDescription;
    }

    public String getBrandName(){
      return brandName;
    }

    public void setBrandName(String brandName){
      this.brandName = brandName;
    }

    public String getColor(){
      return color;
    }

    public void setColor(String color){
      this.color = color;
    }

    public String getGender(){
      return gender;
    }

    public void setGender(String gender){
      this.gender = gender;
    }

    @Override
    public String toString() {

        return String.format(
                "Product[itemId=%d, upc='%d', size=%d, msrp = %d, salePrice = %d, name=%s, shortDescription=%s, brandName=%s, color=%s, gender=%s]",
                itemId, upc, size, msrp, salePrice, name, shortDescription, brandName, color, gender);
    }

}
