/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.hccis.cisflowershop.model.jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Khari
 */
@Entity
@Table(name = "flowerorder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FlowerOrder.findAll", query = "SELECT f FROM FlowerOrder f"),
    @NamedQuery(name = "FlowerOrder.findById", query = "SELECT f FROM FlowerOrder f WHERE f.id = :id"),
    @NamedQuery(name = "FlowerOrder.findByCustomerId", query = "SELECT f FROM FlowerOrder f WHERE f.customerId = :customerId"),
    @NamedQuery(name = "FlowerOrder.findByOrderDate", query = "SELECT f FROM FlowerOrder f WHERE f.orderDate = :orderDate"),
    @NamedQuery(name = "FlowerOrder.findByItem1", query = "SELECT f FROM FlowerOrder f WHERE f.item1 = :item1"),
    @NamedQuery(name = "FlowerOrder.findByItem2", query = "SELECT f FROM FlowerOrder f WHERE f.item2 = :item2"),
    @NamedQuery(name = "FlowerOrder.findByItem3", query = "SELECT f FROM FlowerOrder f WHERE f.item3 = :item3"),
    @NamedQuery(name = "FlowerOrder.findByItem4", query = "SELECT f FROM FlowerOrder f WHERE f.item4 = :item4"),
    @NamedQuery(name = "FlowerOrder.findByItem5", query = "SELECT f FROM FlowerOrder f WHERE f.item5 = :item5"),
    @NamedQuery(name = "FlowerOrder.findByItem6", query = "SELECT f FROM FlowerOrder f WHERE f.item6 = :item6"),
    @NamedQuery(name = "FlowerOrder.findByOrderStatus", query = "SELECT f FROM FlowerOrder f WHERE f.orderStatus = :orderStatus"),
    @NamedQuery(name = "FlowerOrder.findByTotalCost", query = "SELECT f FROM FlowerOrder f WHERE f.totalCost = :totalCost"),
    @NamedQuery(name = "FlowerOrder.findByAmountPaid", query = "SELECT f FROM FlowerOrder f WHERE f.amountPaid = :amountPaid"),
    @NamedQuery(name = "FlowerOrder.findByItemQuantitySelections", query = "SELECT f FROM FlowerOrder f WHERE f.itemQuantitySelections = :itemQuantitySelections"),
    @NamedQuery(name = "FlowerOrder.findByItemStatusTypeDescription", query = "SELECT f FROM FlowerOrder f WHERE f.itemStatusTypeDescription = :itemStatusTypeDescription")})
public class FlowerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "customerId")
    private Integer customerId;
    @Column(name = "orderDate")
    private String orderDate;
    @Column(name = "item1")
    private Integer item1;
    @Column(name = "item2")
    private Integer item2;
    @Column(name = "item3")
    private Integer item3;
    @Column(name = "item4")
    private Integer item4;
    @Column(name = "item5")
    private Integer item5;
    @Column(name = "item6")
    private Integer item6;
    @Column(name = "orderStatus")
    private Integer orderStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "totalCost")
    private BigDecimal totalCost;
    @Column(name = "amountPaid")
    private BigDecimal amountPaid;
    @Column(name = "itemQuantitySelections")
    private Integer itemQuantitySelections;
    @Column(name = "itemStatusTypeDescription")
    private String itemStatusTypeDescription;

    public FlowerOrder() {
    }

    public FlowerOrder(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getItem1() {
        return item1;
    }

    public void setItem1(Integer item1) {
        this.item1 = item1;
    }

    public Integer getItem2() {
        return item2;
    }

    public void setItem2(Integer item2) {
        this.item2 = item2;
    }

    public Integer getItem3() {
        return item3;
    }

    public void setItem3(Integer item3) {
        this.item3 = item3;
    }

    public Integer getItem4() {
        return item4;
    }

    public void setItem4(Integer item4) {
        this.item4 = item4;
    }

    public Integer getItem5() {
        return item5;
    }

    public void setItem5(Integer item5) {
        this.item5 = item5;
    }

    public Integer getItem6() {
        return item6;
    }

    public void setItem6(Integer item6) {
        this.item6 = item6;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getItemQuantitySelections() {
        return itemQuantitySelections;
    }

    public void setItemQuantitySelections(Integer itemQuantitySelections) {
        this.itemQuantitySelections = itemQuantitySelections;
    }

    public String getItemStatusTypeDescription() {
        return itemStatusTypeDescription;
    }

    public void setItemStatusTypeDescription(String itemStatusTypeDescription) {
        this.itemStatusTypeDescription = itemStatusTypeDescription;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FlowerOrder)) {
            return false;
        }
        FlowerOrder other = (FlowerOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "info.hccis.cisflowershop.model.jpa.FlowerOrder[ id=" + id + " ]";
    }
    
}
