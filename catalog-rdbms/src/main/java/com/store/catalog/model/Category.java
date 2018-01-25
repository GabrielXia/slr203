package com.store.catalog.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * This class encapsulates all the data for a category.
 *
 */

@Entity
@Table(name="T_CATEGORY")
@Access(AccessType.FIELD)
public  class Category implements AbstractBean {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4648729478877163459L;
	// ======================================
    // =             Attributes             =
    // ======================================

	@Id
	@Column(name="id")
	private Long id;
    
	@Column(name="name")
	private String name;

	@Column(name="description")
	private String description;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<Product>();

    // ======================================
    // =            Constructors            =
    // ======================================
    public Category() {
    }

    public Category(final Long id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
    	this.description = description;
    }


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


    public Set<Product> getProducts() {
        return products;
    }
    
  
    public void setProducts(Set<Product> products) {
		this.products = products;
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
        return new HashCodeBuilder().append(id) .append(name).append(description).hashCode();
    }
}
