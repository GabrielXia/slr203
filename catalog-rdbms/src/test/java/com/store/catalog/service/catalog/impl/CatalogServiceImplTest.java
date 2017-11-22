package com.store.catalog.service.catalog.impl;

import com.store.catalog.dao.CategoryDao;
import com.store.catalog.dao.ItemDao;
import com.store.catalog.dao.ProductDao;
import com.store.catalog.model.*;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatalogServiceImplTest {


	private CategoryDao categoryDaoMock;


	private ProductDao productDaoMock;


	private ItemDao itemDaoMock;


    protected Mapper mockedMapper;

	private CatalogServiceImpl catalogService;
	

	@Before
	public void setUp() throws Exception {

		//init implementation CatalogServiceImpl

		//set dependencies for Daos

		//set dependency for dozer mapper

		
	}

	@After
	public void tearDown() throws Exception {
		catalogService = null;
	}

	
	
	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */
	
	@Test 
	public void saveCategoryTest() throws Exception {
        Category category = getCategory();
        CategoryDTO categoryDTO = getCategoryDto();

        when(categoryDaoMock.save(category)).thenReturn(category);
        when(mockedMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
        when(mockedMapper.map(categoryDTO, Category.class)).thenReturn(category);

		CategoryDTO aCategoryDto = catalogService.createCategory(categoryDTO);

        verify(categoryDaoMock).save(category);

		assertNotNull(aCategoryDto);
		assertEquals(categoryDTO, aCategoryDto);
	}
	
	@Test 
	public void udapteCategoryTest() throws Exception {
        Category category = getCategory();
        CategoryDTO categoryDTO = getCategoryDto();

        when(categoryDaoMock.findOne(category.getId())).thenReturn(category);
        when(mockedMapper.map(categoryDTO, Category.class)).thenReturn(category);

		catalogService.updateCategory(categoryDTO);
		
		verify(categoryDaoMock).save(category);
	}
	
	
	@Test 
	public void deleteCategoryTest() throws Exception {
        Long id = Long.valueOf(1);

        doNothing().when(categoryDaoMock).delete(id);

		catalogService.deleteCategory(id);
		
		verify(categoryDaoMock).delete(id);
	}
	

	@Test 
	public void findCategoriesTest() throws Exception {

        Category category = getCategory();
        Category category2 = getCategory2();

        CategoryDTO categoryDTO = getCategoryDto();
        CategoryDTO categoryDTO2 = getCategoryDto2();

        List<Category> returnedLst = new ArrayList<Category>();
		
		returnedLst.add(category);
		returnedLst.add(category2);
		
		when(categoryDaoMock.findAll()).thenReturn(returnedLst);
        when(mockedMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
        when(mockedMapper.map(category2, CategoryDTO.class)).thenReturn(categoryDTO2);

		List<CategoryDTO> categoriesDto = catalogService.findCategories();
		
		assertNotNull(categoriesDto);
		
		assertEquals(2, categoriesDto.size());

        CategoryDTO returnedCategoryDTO1 = categoriesDto.get(0);
        CategoryDTO returnedCategoryDTO2 = categoriesDto.get(1);
        assertNotNull(returnedCategoryDTO1);
        assertNotNull(returnedCategoryDTO2);
	}	

	@Test 
	public void findCategoryTest() throws Exception {
        Category category = getCategory();
        CategoryDTO categoryDTO = getCategoryDto();

        when(categoryDaoMock.findOne(category.getId())).thenReturn(category);
        when(mockedMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);

		CategoryDTO myCategoryDto = catalogService.findCategory(category.getId());
		
		assertNotNull(myCategoryDto);
		
		assertEquals(Long.valueOf(1L), myCategoryDto.getId());
		assertEquals(categoryDTO, myCategoryDto);
	}	
	

	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */
	//put the right annotation
	public void verifySaveProductThrowExceptionWhenProductDTOIsNull() throws Exception {
		//code simulating savind a product with a null parameter
		throw new Exception("not yet implemented");
	}

	@Test
	public void saveProductTest() throws Exception {
		throw new Exception("not yet implemented");
	}	
	
	@Test 
	public void udapteProductTest() throws Exception {
		throw new Exception("not yet implemented");
	}
	
	
	@Test 
	public void deleteProductTest() throws Exception {
		throw new Exception("not yet implemented");
	}
	

	@Test 
	public void findProductsTest() throws Exception {
		throw new Exception("not yet implemented");
	}

	@Test
	public void findProductsByCategoryIdTest() throws Exception {
		throw new Exception("not yet implemented");
	}

	@Test 
	public void findProductTest() throws Exception {
		throw new Exception("not yet implemented");
	}
	

	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */
	//put the right annotation
	public void verifySaveItemThrowExceptionWhenItemDTOIsNull() throws Exception {
		throw new Exception("not yet implemented");
	}

	
	@Test 
	public void saveItemTest() throws Exception {
		throw new Exception("not yet implemented");
	}	
	
	@Test 
	public void updateItemTest() throws Exception {
		throw new Exception("not yet implemented");
	}
	
	
	@Test 
	public void deleteItemTest() throws Exception {
		throw new Exception("not yet implemented");
	}

    @Test
    public void findItemsTest() throws Exception {
		throw new Exception("not yet implemented");
    }
	

	@Test 
	public void findItemTest() throws Exception {
		throw new Exception("not yet implemented");
	}

	
	@Test 
	public void findItemsByProductIdTest() throws Exception {
		throw new Exception("not yet implemented");
	}	
	
	
	@Test 
	public void searchItemsTest() throws Exception {
		throw new Exception("not yet implemented");
	}
	
	
	
	/* ---------------------------------- */
	/* --------- Private methods -------- */
	/* ---------------------------------- */
	
	private CategoryDTO getCategoryDto(){
		CategoryDTO category = new CategoryDTO(Long.valueOf(1L), "categoryName", "categoryDesc");
		return category;
	}

	private CategoryDTO getCategoryDto2(){
		CategoryDTO category = new CategoryDTO(Long.valueOf(2L), "categoryName2", "categoryDesc2");
		return category;
	}

	private Category getCategory(){
		Category category = new Category(Long.valueOf(1L), "categoryName", "categoryDesc");
		return category;
	}
	

	private Category getCategory2(){
		Category category = new Category(Long.valueOf(2L), "categoryName2", "categoryDesc2");
		return category;
	}	

	
	private ProductDTO getProductDto(){
		ProductDTO product = new ProductDTO(Long.valueOf(1L), "productName", "productDesc");
		return product;
	}

	private ProductDTO getProductDto2(){
		ProductDTO product = new ProductDTO(Long.valueOf(2L), "productName2", "productDesc2");
		return product;
	}
	
	

	private Product getProduct(){
		Product product = new Product(Long.valueOf(1L), "productName", "productDesc");
		return product;
	}	
	
	private Product getProduct2(){
		Product product = new Product(Long.valueOf(2L), "productName2", "productDesc2");
		return product;
	}
	
	
	
	private ItemDTO getItemDto(){
		ItemDTO itemDto = new ItemDTO(Long.valueOf(1L),"articleID",20.0);
		return itemDto;
	}


	private ItemDTO getItemDto2(){
		ItemDTO itemDto = new ItemDTO(Long.valueOf(2L),"articleID2",30.0);
		return itemDto;
	}

	private ItemDTO getItemDto(Item item){
		Mapper mapper = new DozerBeanMapper();
		ItemDTO itemDto = mapper.map(item, ItemDTO.class);
		ProductDTO productDto = mapper.map(item.getProduct(), ProductDTO.class);
		//itemDto.setProduct(productDto);
		return itemDto;
	}
	

	private Item getItem(){
		Item item = new Item(Long.valueOf(1L),"articleID",20.0);
		item.setProduct(getProduct());
		return item;
	}
	
	private Item getItem2(){
		Item item = new Item(Long.valueOf(2L),"articleID2",30.0);
		
		return item;
	}	
	
}
