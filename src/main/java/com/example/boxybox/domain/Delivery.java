package com.example.boxybox.domain;

import javax.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author Artsiom Andryianau
 *
 * @version 1.0
 */
@Entity
public class Delivery {

    /*
     * field
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*
     * field
     */
    private String dateOfOrdering;

    /*
     * field
     */
    private String dateOfDelivery;

    /*
     * field
     */
    private String nameOfCommodity;

    /*
     * field
     */
    private String addressCity;

    /*
     * field
     */
    private String addressStreet;

    /*
     * field
     */
    private String addressNumberOfBuilding;

    /*
     * field
     */
    private String addressNumberOfApartament;

    /*
     * field
     */
    private Double commodityPrise;

    /*
     * field
     */
    private String isDelivered;
    /*
     * field
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User client;
    /*
     * field
     */
    private String filename;


    /*
     * getter/ setter method
     */
    public void setIsDelivered(String isDelivered) {
        this.isDelivered = isDelivered;
    }

    /*
     * non parametred constructor
     */
    public Delivery() { }

    /*
     * getter/ setter method
     */
    public String getAuthorName(){
        return client !=null ? client.getUsername() : "<none>";
    }
    /*
     * getter/ setter method
     */
    public String getAddress(){
        return addressCity + " " + addressStreet + " " + addressNumberOfBuilding + "/" +addressNumberOfApartament;
    }
    /*
     * getter/ setter method
     */
    public String getDateOfOrdering() {
        return dateOfOrdering;
    }
    /*
     * getter/ setter method
     */
    public void setDateOfOrdering(String dateOfOrdering) {
        this.dateOfOrdering = dateOfOrdering;
    }
    /*
     * getter/ setter method
     */
    public User getClient() {
        return client;
    }
    /*
     * getter/ setter method
     */
    public void setClient(User client) {
        this.client = client;
    }
    /*
     * getter/ setter method
     */
    public Integer getId() {
        return id;
    }
    /*
     * getter/ setter method
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /*
     * getter/ setter method
     */
    public String getNameOfCommodity() {
        return nameOfCommodity;
    }
    /*
     * getter/ setter method
     */
    public String getFilename() {
        return filename;
    }
    /*
     * getter/ setter method
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }
    /*
     * getter/ setter method
     */
    public void setNameOfCommodity(String nameOfCommodity) {
        this.nameOfCommodity = nameOfCommodity;
    }
    /*
     * getter/ setter method
     */
    public String getDateOfDelivery() {
        return dateOfDelivery;
    }
    /*
     * getter/ setter method
     */
    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }
    /*
     * getter/ setter method
     */
    public String getAddressCity() {
        return addressCity;
    }
    /*
     * getter/ setter method
     */
    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }
    /*
     * getter/ setter method
     */
    public String getAddressStreet() {
        return addressStreet;
    }
    /*
     * getter/ setter method
     */
    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }
    /*
     * getter/ setter method
     */
    public String getAddressNumberOfBuilding() {
        return addressNumberOfBuilding;
    }
    /*
     * getter/ setter method
     */
    public void setAddressNumberOfBuilding(String addressNumberOfBuilding) {
        this.addressNumberOfBuilding = addressNumberOfBuilding;
    }
    /*
     * getter/ setter method
     */
    public String getAddressNumberOfApartament() {
        return addressNumberOfApartament;
    }
    /*
     * getter/ setter method
     */
    public void setAddressNumberOfApartament(String addressNumberOfApartament) {
        this.addressNumberOfApartament = addressNumberOfApartament;
    }
    /*
     * getter/ setter method
     */
    public Double getCommodityPrise() {
        return commodityPrise;
    }

    /*
     * getter/ setter method
     */
    public void setCommodityPrise(Double commodityPrise) {
        this.commodityPrise = commodityPrise;
    }


    /**
     *
     * @param dateOfDelivery - delivery param
     * @param nameOfCommodity- delivery param
     * @param addressCity- delivery param
     * @param addressStreet- delivery param
     * @param addressNumberOfBuilding- delivery param
     * @param addressNumberOfApartament- delivery param
     * @param commodityPrise- delivery param
     * @param client- delivery param
     */
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

    /*
     * getter/setter method
     *
     */
    public String getIsDelivered() {
        return isDelivered;
    }

    /*
     * getter/setter method
     *
     */
    public void setThatIsDelivered() {
        this.isDelivered = "Dostarczona";
    }
}
