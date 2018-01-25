package com.store.catalog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.store.catalog.model.Category;
import com.store.catalog.model.Product;
import com.store.catalog.utils.ConstantUtils;

import static com.store.catalog.utils.ConstantUtils.*;

public class ProductDaoTest extends AbstractBaseDaoTestCase {

    
    @Autowired
    private ProductDao productDao ;
    
    @Autowired
    private CategoryDao categoryDao ;    
    
    private Product product = null;
    
    
    @Before
    public void setUp(){
    	loadProduct();
    }
    
    @After
    public void tearDown(){
    	categoryDao = null;
    	productDao = null;
    }

    @Test
    public void testCreateProduct() throws Exception {
    	productDao.save(product);
        assertTrue("primary key assigned", product.getId() != null);
    }    
   
    @Test
    public void testUpdateProduct() throws Exception {
    	productDao.save(product);
        
        product.setName(ConstantUtils.PRODUCT_NAME + "MDF");
        product.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "MDF");
        
        productDao.save(product);
        
        Product proMdf = productDao.findOne(product.getId());
        assertEquals(product, proMdf);
    }    
    
    
    @Test
    public void testGetProduct() throws Exception {
    	productDao.save(product);
    	
    	Product pro = productDao.findOne(product.getId());
    	
    	assertNotNull(pro);
    	assertEquals(product, pro);
    }   

    
    @Test
    public void testRemoveProduct() throws Exception {
    	productDao.save(product);
    	
    	Product pro = productDao.findOne(product.getId());
    	
    	assertNotNull(pro.getId());
    	assertEquals(product, pro);
    	
    	productDao.delete(product.getId());
    	
    	assertTrue(this.getIterableSize(productDao.findAll()) == 0);
    }

    
    
    @Test
    public void testGetProducts() throws Exception {
    	productDao.save(product);
    	
    	Iterable<Product> lst = productDao.findAll();
    	
    	assertTrue(getIterableSize(productDao.findAll()) == 1);
    	
    	Product pro2 = new Product();
    	pro2.setId(new Random().nextLong());
    	pro2.setName(ConstantUtils.PRODUCT_NAME + "2");
    	pro2.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
    	pro2.setCategory(getCategory());
    	
    	productDao.save(pro2);
    	
    	assertTrue(getIterableSize(productDao.findAll()) == 2);
    }    
    

    @Test
    public void testGetProductsWithCategoryId() throws Exception {
        Category cat1 = getCategory();
        Category cat2 = getCategory2();
        
        Product pro1 = new Product();
    	pro1.setId(new Random().nextLong());
    	pro1.setName(ConstantUtils.PRODUCT_NAME + "2");
    	pro1.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
    	pro1.setCategory(cat1);
    	productDao.save(pro1);
    	
        Product pro2 = new Product();
    	pro2.setId(new Random().nextLong());
    	pro2.setName(ConstantUtils.PRODUCT_NAME + "2");
    	pro2.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
    	pro2.setCategory(cat2);
    	
    	productDao.save(pro2);
    	assertTrue(getIterableSize(productDao.findByCategoryId(cat1.getId())) == 1);
    	assertTrue(getIterableSize(productDao.findByCategoryId(cat2.getId())) == 1);
    }    

    
    @Test
    public void testGetProductsByCategoryName() throws Exception {
    	Category cat1 = getCategory();
        Category cat2 = getCategory2();
        
        Product pro1 = new Product();
    	pro1.setId(new Random().nextLong());
    	pro1.setName(ConstantUtils.PRODUCT_NAME + "2");
    	pro1.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
    	pro1.setCategory(cat1);
    	productDao.save(pro1);
    	
        Product pro2 = new Product();
    	pro2.setId(new Random().nextLong());
    	pro2.setName(ConstantUtils.PRODUCT_NAME + "2");
    	pro2.setDescription(ConstantUtils.PRODUCT_DESCRIPTION + "2");
    	pro2.setCategory(cat2);
    	
    	productDao.save(pro2);
    	assertTrue(getIterableSize(productDao.findByCategoryName(cat1.getName())) == 1);
    	assertTrue(getIterableSize(productDao.findByCategoryName(cat2.getName())) == 1);
    }        
	
	
	private Category getCategory() {
		Category category = new Category();
        category.setId(new Random().nextLong());
        category.setName(CATEGOY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryDao.save(category);
		return category;
	}    
	
	private Category getCategory2() {
		Category category = new Category();
        category.setId(new Random().nextLong());
        category.setName("catName2");
        category.setDescription("description2");

        categoryDao.save(category);
		return category;
	}    
    
	
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */	
	private void loadProduct() {
    	product = new Product();
        product.setId(new Random().nextLong());
    	product.setName(PRODUCT_NAME);
    	product.setDescription(PRODUCT_DESCRIPTION);
    	product.setCategory(getCategory());
	}
	
}
