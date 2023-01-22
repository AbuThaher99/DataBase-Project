package com.example.projectdatabase12;

public class Items {
    private String IID;
    private String Name;
    private double price;
    private int quantity;
    private double proft;
    private double purchase;
    private double total;

    public Items(String IID, String Name, double price, int quantity, double proft, double purchase, double total){
        this.IID = IID;
        this.Name = Name;
        this.price = price;
        this.quantity = quantity;
        this.proft = proft;
        this.purchase = purchase;
        this.total = total;
    }

    public Items(String IID, String name, double price, double purchase) {
        this.IID = IID;
        Name = name;
        this.price = price;
        this.purchase = purchase;
    }

    public Items(String IID,String name, double price, int quantity, double total) {
        this.IID = IID;
        Name = name;
        this.price = price;
        this.quantity = quantity;
        this.total = total;
    }

    public String getIID(){
        return IID;
    }

    public String getName(){
        return Name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getProft(){
        return proft = price - purchase;
    }

    public double getPurchase(){
        return purchase;
    }

    public double getTotal(){
        return total = price * quantity;
    }

    public void setIID(String IID){
        this.IID = IID;
    }

    public void setName(String Name){
        this.Name = Name;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setProft(double proft){
        this.proft = proft;
    }

    public void setPurchase(double purchase){
        this.purchase = purchase;
    }

    public void setTotal(double total){
        this.total = total;
    }

    @Override
    public String toString(){
        return "Items{" + "IID=" + IID + ", Name=" + Name + ", price=" + price + ", quantity=" + quantity + ", proft=" + proft + ", purchase=" + purchase + ", total=" + total + '}';
    }

}
