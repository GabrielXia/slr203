package com.store.catalog.dao;

import com.store.catalog.model.Product;
import com.store.catalog.utils.ConstantUtils;
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
    	itemDao.save(item);
        assertTrue("primary key assigned", item.getId() != null);
    }


    @Test
    public void testUpdateItem() throws Exception {
    	itemDao.save(item);
        
        item.setName(ConstantUtils.ITEM_NAME + "MDF");
        item.setImagePath(ITEM_IMAGE_PATH + "MDF");
        item.setUnitCost(ITEM_PRICE + 1);
        
        itemDao.save(item);
        
        Item itemMdf = itemDao.findOne(item.getId());
        assertEquals(item, itemMdf);
    }

    @Test
    public void testGetItem() throws Exception {
    	itemDao.save(item);
    	
    	Item it = itemDao.findOne(item.getId());
    	
    	assertNotNull(it);
    	assertEquals(item, it);
    }


    @Test
    public void testRemoveItem() throws Exception {
    	itemDao.save(item);
    	
    	Item it = itemDao.findOne(item.getId());
    	
    	assertNotNull(it.getId());
    	assertEquals(item, it);
    	
    	itemDao.delete(item.getId());
    	
    	assertTrue(this.getIterableSize(itemDao.findAll()) == 0);
    }


    @Test
    public void testGetItems() throws Exception {
    	itemDao.save(item);
    	
    	Iterable<Item> lst = itemDao.findAll();
    	
    	assertTrue(getIterableSize(itemDao.findAll()) == 1);
    	
    	Item it2 = getAnotherItem();
    	
    	itemDao.save(it2);
    	
    	assertTrue(getIterableSize(itemDao.findAll()) == 2);
    }


    @Test
    public void testGetItemsWithProductId() throws Exception {
    	Product pro1 = getProduct();
        Product pro2 = getProduct("name", "desc");
        
        Item item1 = new Item();
        item1.setId(new Random().nextLong());
        item1.setName(ITEM_NAME + "1");
        item1.setImagePath(ITEM_IMAGE_PATH + "1");
        item1.setUnitCost(ITEM_PRICE + 10d); 
        item1.setProduct(pro1);
        
        itemDao.save(item1);
        
        Item item2 = new Item();
        item2.setId(new Random().nextLong());
        item2.setName(ITEM_NAME + "2");
        item2.setImagePath(ITEM_IMAGE_PATH + "2");
        item2.setUnitCost(ITEM_PRICE + 10d); 
        item2.setProduct(pro2);
    	
    	itemDao.save(item2);
    	assertTrue(getIterableSize(itemDao.findByProductId(pro1.getId())) == 1);
    	assertTrue(getIterableSize(itemDao.findByProductId(pro2.getId())) == 1);
    }

    /**
     * 2 points
     * @throws Exception
     */
    @Test
    public void testSearchItem() throws Exception {
    	itemDao.save(item);
    	Item it2 = this.getAnotherItem();
    	itemDao.save(it2);
    	assertTrue(getIterableSize(itemDao.findByNameContaining(ITEM_NAME)) == 2);
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
