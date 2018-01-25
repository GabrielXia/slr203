package com.store.catalog.model;

import com.store.catalog.common.exception.CheckException;
import org.hibernate.cfg.NotYetImplementedException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by zouheir on 04/12/2016.
 */
public class CustomerTest {


    //fixtures ...
    //declare a Customer class
	private static Customer customer;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@BeforeClass
	public static void testSetUp() {
		customer = new Customer();
	}

    //fixtures ...
    //Create a fixture that will be called before the execution of each test method
    //This fixture is responsible of the customer Class declared above
	@Before
	public void testBefore() {
		customer.setPassword("hello");
	}

    /**
     * Test 1
     */
	@Test(expected = CheckException.class)
    public void verifyWhenPasswordNullReturnException() throws Exception {
    	customer.matchPassword(null);
    }

    /**
     * Test 2
     */
	@Test(expected = CheckException.class)
    public void verifyWhenPasswordEmptyReturnException() throws Exception {
        customer.matchPassword("");
    }


    /**
     * Test 3
     */
	@Test(expected = CheckException.class)
    public void verifyWhenPasswordDontMatchReturnException() throws Exception {
		customer.matchPassword("wrong");
    }


    /**
     * Test 4
     */
	@Test
    public void verifyWhenPasswordMatchReturnTrue() throws Exception {
		assertTrue((customer.matchPassword("hello")));
    }


    /**
     * Test 5
     */
	@Test
    public void verifyWhenPasswordNullReturnMsg() throws Exception {
        thrown.expect(CheckException.class);
        thrown.expectMessage(Customer.INVALID_PASSWORD);
        customer.matchPassword(null);
    }

}