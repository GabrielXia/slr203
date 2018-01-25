package com.store.catalog.model;

import com.store.catalog.common.exception.CheckException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


/**
 * Created by zouheir on 03/12/2016.
 */
public final class Customer implements AbstractBean {

    public static final String INVALID_PASSWORD = "Invalid password";
    public static final String PASSWORD_DONT_MATCH = "Invalid password";


    /**
     *
     */
    private static final long serialVersionUID = 4770450084273556493L;
    // ======================================
    // =             Attributes             =
    // ======================================
    private Long id;
    private String firstname;
    private String password;
    private String lastname;
    private String telephone;
    private String email;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Customer() {
    }

    public Customer(final Long id, final String firstname, final String lastname) {
        setId(id);
        setFirstname(firstname);
        setLastname(lastname);
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(final String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }


    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Given a password, this method throws an exception if the @param
     * is null or empty. Then it checks if the @param matches the customer.password
     *
     * @param password1
     * @throws CheckException thrown if the password is null, empty or different than the one
     *                        store in database
     */
    public boolean matchPassword(final String password1) throws CheckException {
    	if(password1 == null) {
    		throw new CheckException(this.INVALID_PASSWORD);
    	} else if (password1 == "") {
    		throw new CheckException("password is empty");
    	} else if(password1 != this.password) {
    		throw new CheckException("password is not correct");
    	} else {
    		return true;
    	}
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public int hashCode(Object obj) {
        return new HashCodeBuilder()
                .append(id)
                .append(firstname)
                .append(lastname)
                .append(password)
                .append(telephone)
                .append(email)
                .hashCode();
    }
}