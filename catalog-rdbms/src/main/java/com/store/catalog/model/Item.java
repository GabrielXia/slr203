package com.store.catalog.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * This class encapsulates all the data for an Item.
 *
 */
@Entity
@Table(name="T_Item")
@Access(AccessType.FIELD)
public class Item implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3683094094531524261L;
	// ======================================
    // =             Attributes             =
    // ======================================

	@Id
	@Column(name="id")
    private Long id;

	@Column(name="name")
    private String name;

	@Column(name="unitcost")
    private double unitCost;

	@Column(name="imagePath")
    private String imagePath;

	@ManyToOne
	@JoinColumn(name="product_fk", 
			nullable = false)
    private Product product;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Item() {
    }

    public Item(final Long id, final String name, final double unitCost) {
        setId(id);
        setName(name);
        setUnitCost(unitCost);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
    	this.name = name;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(final double unitCost) {
    	this.unitCost = unitCost;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(final String imagePath) {
    	this.imagePath = imagePath;
    }


    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id) .append(name).append(imagePath).hashCode();
    }

}
