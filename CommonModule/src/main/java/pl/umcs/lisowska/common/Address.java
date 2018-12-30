package pl.umcs.lisowska.common;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String street;

    private Integer buildingNumber;

    private Integer localNumber;

    private String code;

    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Address(){

    }
    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(Integer buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Integer getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(Integer localNumber) {
        this.localNumber = localNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", buildingNumber='" + buildingNumber + '\'' +
                ", localNumber=" + localNumber +
                ", code=" + code +
                ", city=" + city +
                '}';
    }
}
