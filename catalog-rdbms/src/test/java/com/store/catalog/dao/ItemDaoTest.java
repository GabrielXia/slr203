package com.store.catalog.dao;

import com.store.catalog.model.Product;
import com.store.catalog.model.Category;
import com.store.catalog.model.Item;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import static com.store.catalog.utils.ConstantUtils.*;

import static org.junit.Assert.*;

public class ItemDaoTest extends AbstractBaseDaoTestCase {
    


	private Item item = null;

    @Autowired
    private ItemDao itemDao ;
    
    @Autowired
    private ProductDao productDao ;
    
    @Autowired
    private CategoryDao categoryDao ;    
   
    @Before
    public void setUp(){
    	loadItem();
    }
    
    @After
    public void tearDown(){
    	categoryDao = null;
    	productDao = null;
    	itemDao  = null;
    	item = null;
    }


    @Test
    public void testCreateItem() throws Exception {
        throw new Exception("not yet implemented");
    }


    @Test
    public void testUpdateItem() throws Exception {
        throw new Exception("not yet implemented");
    }

    @Test
    public void testGetItem() throws Exception {
        throw new Exception("not yet implemented");
    }


    @Test
    public void testRemoveItem() throws Exception {
        throw new Exception("not yet implemented");
    }


    @Test
    public void testGetItems() throws Exception {
        throw new Exception("not yet implemented");
    }


    @Test
    public void testGetItemsWithProductId() throws Exception {
        throw new Exception("not yet implemented");
    }

    /**
     * 2 points
     * @throws Exception
     */
    @Test
    public void testSearchItem() throws Exception {
        throw new Exception("not yet implemented");
    }
    
    
    /**
     * 
     * @return an instanciated object. The one declared as private field in the test class
     */    
	private void loadItem() {
	   	item = new Item();
        item.setId(new Random().nextLong());
        item.setName(ITEM_NAME);
        item.setImagePath(ITEM_IMAGE_PATH);
        item.setUnitCost(ITEM_PRICE);
        item.setProduct(getProduct());
	}
	
	
	private Item getAnotherItem() {
        Item item2 = new Item();
        item2.setId(new Random().nextLong());
        item2.setName(ITEM_NAME + "2");
        item2.setImagePath(ITEM_IMAGE_PATH + "2");
        item2.setUnitCost(ITEM_PRICE + 10d); 
        item2.setProduct(getProduct());

        return item2;
	}	
    
    
	private Product getProduct() {
	   	Product product = new Product();
        product.setId(new Random().nextLong());
        product.setName(PRODUCT_NAME);
        product.setDescription(PRODUCT_DESCRIPTION);

        Category category = getCategory();
        product.setCategory(category);

        productDao.save(product);        
        
        return product;
	}    
	
	private Product getProduct(String name, String desc) {
	   	Product product = new Product();
        product.setId(new Random().nextLong());
        product.setName(name);
        product.setDescription(desc);

        Category category = getCategory();
        product.setCategory(category);

        productDao.save(product);        
        
        return product;
	}	
	
	private Category getCategory() {
		Category category = new Category();
        category.setId(new Random().nextLong());
        category.setName(CATEGOY_NAME);
        category.setDescription(CATEGORY_DESCRIPTION);

        categoryDao.save(category);
		return category;
	}    	
}
