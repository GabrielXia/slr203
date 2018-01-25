package com.store.catalog.dao;

import com.store.catalog.utils.ConstantUtils;
import static com.store.catalog.utils.ConstantUtils.*;
import com.store.catalog.model.Category;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

import static org.junit.Assert.*;

public class CategoryDaoTest  extends AbstractBaseDaoTestCase{

	
	
	@Autowired
    private CategoryDao categoryDao;
    
	
    private Category category = null;
    
    @Before
    public void setUp(){
    	loadCategory();
    }

    
    @After
    public void tearDown(){
    	category = null;
    	categoryDao = null;
    }


    @Test
    public void testCreateCategory() throws Exception {
        categoryDao.save(category);
        assertTrue("primary key assigned", category.getId() != null);
    }

    @Test
    public void testUpdateCategory() throws Exception {
        categoryDao.save(category);
        
        category.setName(ConstantUtils.CATEGOY_NAME + "MDF");
        category.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "MDF");
        
        categoryDao.save(category);
        
        Category catMdf = categoryDao.findOne(category.getId());
        assertEquals(category, catMdf);
    }

    @Test
    public void testGetCategory() throws Exception {
    	categoryDao.save(category);
    	
    	Category cat = categoryDao.findOne(category.getId());
    	
    	assertNotNull(cat);
    	assertEquals(category, cat);
    }

    @Test
    public void testRemoveCategory() throws Exception {
    	categoryDao.save(category);
    	
    	Category cat = categoryDao.findOne(category.getId());
    	
    	assertNotNull(cat.getId());
    	assertEquals(category, cat);
    	
    	categoryDao.delete(category.getId());
    	
    	assertTrue(this.getIterableSize(categoryDao.findAll()) == 0);
    }


    @Test
    public void testGetCategories() throws Exception {
    	categoryDao.save(category);
    	
    	Iterable<Category> lst = categoryDao.findAll();
    	
    	assertTrue(getIterableSize(categoryDao.findAll()) == 1);
    	
    	Category cat2 = new Category();
    	cat2.setId(new Random().nextLong());
    	cat2.setName(ConstantUtils.CATEGOY_NAME + "2");
    	cat2.setDescription(ConstantUtils.CATEGORY_DESCRIPTION + "2");
    	
    	categoryDao.save(cat2);
    	
    	assertTrue(getIterableSize(categoryDao.findAll()) == 2);
    }

    
    /**
     * 
     * create an instanciated object. The one declared as private field in the test class
     */   
	private void  loadCategory() {
		category = new Category();
        category.setId(new Random().nextLong());
		category.setName(ConstantUtils.CATEGOY_NAME);
		category.setDescription(ConstantUtils.CATEGORY_DESCRIPTION);
	}


    
}
