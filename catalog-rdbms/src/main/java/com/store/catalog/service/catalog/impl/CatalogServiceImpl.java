package com.store.catalog.service.catalog.impl;

import com.store.catalog.model.*;
import com.store.catalog.service.catalog.CatalogService;
import com.store.catalog.common.exception.CheckException;
import com.store.catalog.dao.CategoryDao;
import com.store.catalog.dao.ItemDao;
import com.store.catalog.dao.ProductDao;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatalogServiceImpl implements CatalogService {

	Logger logger = LoggerFactory.getLogger(CatalogServiceImpl.class.getName());


	@Autowired
	private CategoryDao categoryDao ;
	
	@Autowired
	protected ProductDao productDao ;
	
	@Autowired
	private ItemDao itemDao ;
	
	@Autowired
	private Mapper dozerMapper;



	public Mapper getDozerMapper() {
		return dozerMapper;
	}

	public void setDozerMapper(Mapper dozerMapper) {
		this.dozerMapper = dozerMapper;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

    public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	



	// ======================================
	// = Product Business methoods =
	// ======================================
	public ProductDTO createProduct(ProductDTO productDto) throws CheckException {
		if (productDto == null)
            throw new CheckException("ProductDTO object is null");
        
		logger.info("start");
		Product product = dozerMapper.map(productDto, Product.class);
		
		productDao.save(product);
		
		ProductDTO productworkDto = dozerMapper.map(product, ProductDTO.class);
		
		logger.info("end");
		return productworkDto;
	}

	@Transactional(readOnly=true)
	public List<ProductDTO> findProducts() {
        Iterable<Product> lst =  productDao.findAll();

		List<ProductDTO> lstDto = new ArrayList<ProductDTO>();

		for (Product obj:lst){
	        ProductDTO productDto = dozerMapper.map(obj, ProductDTO.class);
	        lstDto.add(productDto);
		}


		return lstDto;
	}

	@Transactional(readOnly=true)
	public List<ProductDTO>  findProducts(Long categoryId) throws CheckException {
		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		CategoryDTO categoryDTO = this.findCategory(categoryId);
		result.addAll(categoryDTO.getProducts());
		return result;
	}

	@Transactional(readOnly=true)
	public ProductDTO findProduct(Long productId) throws CheckException {
		if (productId == null || "".equals(productId))
            throw new CheckException("Invalid id");


        Product product = productDao.findOne(productId);
		
		ProductDTO productDto = dozerMapper.map(product,ProductDTO.class);
		return productDto;
	}	
	

	@Transactional(readOnly=true)
 	public List<ProductDTO> findProducts(String categoryName) throws CheckException {
		List<CategoryDTO> l = this.findCategories();
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		for (CategoryDTO obj: l) {
			if(obj.getName().equals(categoryName)) {
				result.addAll(obj.getProducts());
			}
		}
		return result;
	}	
	
	@Transactional
	public void updateProduct(ProductDTO productDto) throws CheckException {
		if (productDto == null)
            throw new CheckException("product object is null");
        
		if (productDto.getId() == null)
            throw new CheckException("Invalid id");


		Product foundproduct =  productDao.findOne(productDto.getId());

		if(foundproduct == null){
			throw new CheckException("unkown id");
		}

		Product product = dozerMapper.map(productDto, Product.class);
		productDao.save(product);
	}

	@Transactional
	public void deleteProduct(Long productId) throws CheckException {
		if (productId == null || "".equals(productId))
            throw new CheckException("Invalid id");
        
		productDao.delete(productId);	
	}
	
    // ======================================
    // =        Item Business methods       =
    // ======================================	
	
	@Transactional
	public ItemDTO createItem(ItemDTO itemDto) throws CheckException {
		if (itemDto == null)
            throw new CheckException("item object is null");
        
		logger.info("start");
		Item item = dozerMapper.map(itemDto, Item.class);
		
		itemDao.save(item);
		
		ItemDTO itemworkDto = dozerMapper.map(item, ItemDTO.class);
		
		logger.info("end");
		return itemworkDto;
	}

	@Transactional
	public void updateItem(ItemDTO itemDto) throws CheckException {
		if (itemDto == null)
            throw new CheckException("item object is null");
        
		if (itemDto.getId() == null)
            throw new CheckException("Invalid id");


		Item founditem =  itemDao.findOne(itemDto.getId());

		if(founditem == null){
			throw new CheckException("unkown id");
		}

		Item item = dozerMapper.map(itemDto, Item.class);
		itemDao.save(item);
	}


	@Transactional
	public void deleteItem(Long itemId) throws CheckException {
		if (itemId == null || "".equals(itemId))
            throw new CheckException("Invalid id");
        
		itemDao.delete(itemId);	
	}

	@Transactional(readOnly=true)
	public List<ItemDTO> findItems() {
        Iterable<Item> lst =  itemDao.findAll();

		List<ItemDTO> lstDto = new ArrayList<ItemDTO>();

		for (Item obj:lst){
	        ItemDTO itemDto = dozerMapper.map(obj, ItemDTO.class);
	        lstDto.add(itemDto);
		}


		return lstDto;
	}

	@Transactional(readOnly=true)
	public List<ItemDTO> findItems(Long productId) throws CheckException {
		if (productId == null || "".equals(productId))
            throw new CheckException("Invalid id");
		List<ItemDTO> result = new ArrayList<ItemDTO>();
		ProductDTO productDTO = this.findProduct(productId);
		result.addAll(productDTO.getItems());
		return result;
	}

	
	@Transactional(readOnly=true)
	public List<ItemDTO> searchItems(String keyword) throws CheckException{
		if (keyword == null || "".equals(keyword))
            throw new CheckException("Invalid keyword");
		List<ItemDTO> items = this.findItems();
		List<ItemDTO> result = new ArrayList<ItemDTO>();
		for (ItemDTO obj:items) {
			if(obj.getName().contains(keyword)) {
				 result.add(obj);
			}		
		}
		return result;
	}



	@Transactional(readOnly=true)
	public ItemDTO findItem(Long itemId) throws CheckException {
		if (itemId == null || "".equals(itemId))
            throw new CheckException("Invalid id");


        Item item = itemDao.findOne(itemId);
		
		ItemDTO itemDto = dozerMapper.map(item,ItemDTO.class);
		return itemDto;
	}

	
    // ======================================
    // =        Category Business methods       =
    // ======================================	
	
	
	@Transactional
	public CategoryDTO createCategory(CategoryDTO categoryDto) throws CheckException {
		if (categoryDto == null)
            throw new CheckException("Category object is null");
        
		logger.info("start");
		Category category = dozerMapper.map(categoryDto, Category.class);
		
		categoryDao.save(category);
		
		CategoryDTO categoryworkDto = dozerMapper.map(category, CategoryDTO.class);
		
		logger.info("end");
		return categoryworkDto;
	}

	@Transactional
	public void deleteCategory(Long categoryId) throws CheckException {
		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");
        
		categoryDao.delete(categoryId);		
	}

	@Transactional
	public void updateCategory(CategoryDTO categoryDto) throws CheckException {
		if (categoryDto == null)
            throw new CheckException("Category object is null");
        
		if (categoryDto.getId() == null)
            throw new CheckException("Invalid id");


		Category foundCategory =  categoryDao.findOne(categoryDto.getId());

		if(foundCategory == null){
			throw new CheckException("unkown id");
		}

		Category category = dozerMapper.map(categoryDto, Category.class);
		categoryDao.save(category);
	}


	@Transactional(readOnly=true)
	public CategoryDTO findCategory(Long categoryId) throws CheckException {
		if (categoryId == null || "".equals(categoryId))
            throw new CheckException("Invalid id");


        Category category = categoryDao.findOne(categoryId);
		
		CategoryDTO categoryDto = dozerMapper.map(category,CategoryDTO.class);
		return categoryDto;
	}
	
	@Transactional(readOnly=true)
	public List<CategoryDTO> findCategories() {

        Iterable<Category> lst =  categoryDao.findAll();

		List<CategoryDTO> lstDto = new ArrayList<CategoryDTO>();

		for (Category obj:lst){
	        CategoryDTO categoryDto = dozerMapper.map(obj, CategoryDTO.class);
	        lstDto.add(categoryDto);
		}


		return lstDto;
	}


}
