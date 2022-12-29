package ua.mk.rmnv.pizzeria.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ordersArchive")
@NoArgsConstructor
@AllArgsConstructor
public class OrdersArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "productName", nullable = false, length = 100)
    private String productName;

    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;

    @Column(name = "customerSurname", nullable = false, length = 100)
    private String customerSurname;

    @Column(name = "customerPhonenumber", nullable = false, length = 15)
    private String customerPhonenumber;

    @Column(name = "customerAddress", nullable = false, length = 255)
    private String customerAddress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public String getCustomerPhonenumber() {
        return customerPhonenumber;
    }

    public void setCustomerPhonenumber(String customerPhonenumber) {
        this.customerPhonenumber = customerPhonenumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}

