package info.cisflowershop.model.jpa;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author akatt
 */
@Entity
@Table(name = "flowerorder")
@XmlRootElement
public class FlowerOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "customerId")
    private Integer customerId;
    @Size(min = 0, max = 10)
    @Column(name = "orderDate")
    private String orderDate;
    
    
    //KW - variable created to show item quantity selection.
    private Integer itemQuantitySelections;

    public Integer getItemQuantitySelections() {
        return itemQuantitySelections;
    }

    public void setItemQuantitySelections(Integer itemQuantitySelections) {
        this.itemQuantitySelections = itemQuantitySelections;
    }
    
    
    
//    @Size(min = 0, max = 20)
   
    @Column(name = "item1")
    private Integer item1;
    
//    @Size(min = 0, max = 20)
    
    @Column(name = "item2")
    private Integer item2;
    
//    @Size(min = 0, max = 20)
    
    @Column(name = "item3")
    private Integer item3;
    
//    @Size(min = 0, max = 20)
    @Column(name = "item4")
    private Integer item4;
    
//    @Size(min = 0, max = 20)
    @Column(name = "item5")
    private Integer item5;
    
//    @Size(min = 0, max = 20)
    @Column(name = "item6")
    private Integer item6;
    
    
    @Column(name = "orderStatus")
    private Integer orderStatus;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation

    @Column(name = "totalCost")
    private BigDecimal totalCost;
    @Column(name = "amountPaid")
    private BigDecimal amountPaid;

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
        return "info.hccis.cisflowershop.entity.jpa.FlowerOrder[ id=" + id + " ]";
    }
    
}
