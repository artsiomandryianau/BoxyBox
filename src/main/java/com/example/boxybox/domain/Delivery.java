package com.example.boxybox.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String dateOfOrdering;

    private String dateOfDelivery;

    private String nameOfCommodity;

    private String addressCity;

    private String addressStreet;

    private String addressNumberOfBuilding;

    private String addressNumberOfApartament;
    private Double commodityPrise;


    private String isDelivered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User client;

    private String filename;

    public void setIsDelivered(String isDelivered) {
        this.isDelivered = isDelivered;
    }

    public Delivery() { }


    public String getAuthorName(){
        return client !=null ? client.getUsername() : "<none>";
    }

    public String getAddress(){
        return addressCity + " " + addressStreet + " " + addressNumberOfBuilding + "/" +addressNumberOfApartament;
    }

    public String getDateOfOrdering() {
        return dateOfOrdering;
    }

    public void setDateOfOrdering(String dateOfOrdering) {
        this.dateOfOrdering = dateOfOrdering;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfCommodity() {
        return nameOfCommodity;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setNameOfCommodity(String nameOfCommodity) {
        this.nameOfCommodity = nameOfCommodity;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressNumberOfBuilding() {
        return addressNumberOfBuilding;
    }

    public void setAddressNumberOfBuilding(String addressNumberOfBuilding) {
        this.addressNumberOfBuilding = addressNumberOfBuilding;
    }

    public String getAddressNumberOfApartament() {
        return addressNumberOfApartament;
    }

    public void setAddressNumberOfApartament(String addressNumberOfApartament) {
        this.addressNumberOfApartament = addressNumberOfApartament;
    }

    public Double getCommodityPrise() {
        return commodityPrise;
    }

    public void setCommodityPrise(Double commodityPrise) {
        this.commodityPrise = commodityPrise;
    }


    public Delivery(String dateOfDelivery, String nameOfCommodity, String addressCity, String addressStreet,
                    String addressNumberOfBuilding, String addressNumberOfApartament, Double commodityPrise,  User client) {
        this.dateOfOrdering = LocalDate.now().toString();
        this.dateOfDelivery = dateOfDelivery;
        this.nameOfCommodity = nameOfCommodity;
        this.addressCity = addressCity;
        this.addressStreet = addressStreet;
        this.addressNumberOfBuilding = addressNumberOfBuilding;
        this.addressNumberOfApartament = addressNumberOfApartament;
        this.commodityPrise = commodityPrise;
        this.client = client;
        this.isDelivered = "Niedostaczona";
    }

    public String getIsDelivered() {
        return isDelivered;
    }

    public void setThatIsDelivered() {
        this.isDelivered = "Dostarczona";
    }
}
