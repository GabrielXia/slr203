package com.store.catalog.service.catalog.impl;

import com.store.catalog.common.exception.CheckException;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatalogServiceImplTest {

	@Mock
	private CategoryDao categoryDaoMock;

	@Mock
	private ProductDao productDaoMock;

	@Mock
	private ItemDao itemDaoMock;
			
	@Mock
    protected Mapper mockedMapper;

	private CatalogServiceImpl catalogService;
	

	@Before
	public void setUp() throws Exception {

		//init implementation CatalogServiceImpl
		catalogService = new CatalogServiceImpl();
		
		//set dependencies for Daos
		catalogService.setCategoryDao(categoryDaoMock);
		catalogService.setProductDao(productDaoMock);
		catalogService.setItemDao(itemDaoMock);
		
		//set dependency for dozer mapper
		catalogService.setDozerMapper(mockedMapper);
		
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
	@Test(expected = CheckException.class)
	public void verifySaveProductThrowExceptionWhenProductDTOIsNull() throws Exception {
		//code simulating savind a product with a null parameter
		catalogService.createProduct(null);
	}

	@Test
	public void saveProductTest() throws Exception {
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();

        when(productDaoMock.save(product)).thenReturn(product);
        when(mockedMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
        when(mockedMapper.map(productDTO, Product.class)).thenReturn(product);

		ProductDTO aProductDto = catalogService.createProduct(productDTO);

        verify(productDaoMock).save(product);

		assertNotNull(aProductDto);
		assertEquals(productDTO, aProductDto);
	}
	
	@Test 
	public void findProductTest() throws Exception {
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();

        when(productDaoMock.findOne(product.getId())).thenReturn(product);
        when(mockedMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

		ProductDTO myproductDto = catalogService.findProduct(product.getId());
		
		assertNotNull(myproductDto);
		
		assertEquals(Long.valueOf(1L), myproductDto.getId());
		assertEquals(productDTO, myproductDto);
	}	
	
	@Test 
	public void updateProductTest() throws Exception {
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();

        when(productDaoMock.findOne(product.getId())).thenReturn(product);
        when(mockedMapper.map(productDTO, Product.class)).thenReturn(product);

		catalogService.updateProduct(productDTO);
		
		verify(productDaoMock).save(product);
	}
	
	
	@Test 
	public void deleteProductTest() throws Exception {
        Long id = Long.valueOf(1);

        doNothing().when(productDaoMock).delete(id);

		catalogService.deleteProduct(id);
		
		verify(productDaoMock).delete(id);
	}
	

	@Test 
	public void findProductsTest() throws Exception {
		Product product = getProduct();
        Product product2 = getProduct2();

        ProductDTO productDTO = getProductDto();
        ProductDTO productDTO2 = getProductDto2();

        List<Product> returnedLst = new ArrayList<Product>();
		
		returnedLst.add(product);
		returnedLst.add(product2);
		
		when(productDaoMock.findAll()).thenReturn(returnedLst);
        when(mockedMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
        when(mockedMapper.map(product2, ProductDTO.class)).thenReturn(productDTO2);

		List<ProductDTO> productsDto = catalogService.findProducts();
		
		assertNotNull(productsDto);
		
		assertEquals(2, productsDto.size());

        ProductDTO returnedproductDTO1 = productsDto.get(0);
        ProductDTO returnedproductDTO2 = productsDto.get(1);
        assertNotNull(returnedproductDTO1);
        assertNotNull(returnedproductDTO2);
	}

	@Test
	public void findProductsByCategoryIdTest() throws Exception {
		Product product = getProduct();
        Product product2 = getProduct2();

        ProductDTO productDTO = getProductDto();
        ProductDTO productDTO2 = getProductDto2();

        List<Product> returnedLst = new ArrayList<Product>();
		
		returnedLst.add(product);
		returnedLst.add(product2);
		
		when(productDaoMock.findAll()).thenReturn(returnedLst);
        when(mockedMapper.map(product, ProductDTO.class)).thenReturn(productDTO);
        when(mockedMapper.map(product2, ProductDTO.class)).thenReturn(productDTO2);
        
        Category category = getCategory();
        CategoryDTO categoryDTO = getCategoryDto();
        
        when(categoryDaoMock.findOne(category.getId())).thenReturn(category);
        when(mockedMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
        
        Set<ProductDTO> productsDTO = new HashSet<ProductDTO>();
        productsDTO.add(productDTO);
        categoryDTO.setProducts(productsDTO);

		List<ProductDTO> productsDto = catalogService.findProducts(Long.valueOf(1L));
		
		assertNotNull(productsDto);
		
		assertEquals(1, productsDto.size());

        ProductDTO returnedproductDTO1 = productsDto.get(0);
        assertNotNull(returnedproductDTO1);
	}
	

	/* ---------------------------------- */
	/* ---------------------------------- */
	/* ---------------------------------- */
	//put the right annotation
	@Test(expected = CheckException.class)
	public void verifySaveItemThrowExceptionWhenItemDTOIsNull() throws Exception {
		catalogService.createItem(null);
	}

	
	@Test 
	public void saveItemTest() throws Exception {
        Item item = getItem();
        ItemDTO itemDTO = getItemDto();

        when(itemDaoMock.save(item)).thenReturn(item);
        when(mockedMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);
        when(mockedMapper.map(itemDTO, Item.class)).thenReturn(item);

		ItemDTO aitemDto = catalogService.createItem(itemDTO);

        verify(itemDaoMock).save(item);

		assertNotNull(aitemDto);
		assertEquals(itemDTO, aitemDto);
	}	
	
	@Test 
	public void updateItemTest() throws Exception {
        Item item = getItem();
        ItemDTO itemDTO = getItemDto();

        when(itemDaoMock.findOne(item.getId())).thenReturn(item);
        when(mockedMapper.map(itemDTO, Item.class)).thenReturn(item);

		catalogService.updateItem(itemDTO);
		
		verify(itemDaoMock).save(item);
	}
	
	
	@Test 
	public void deleteItemTest() throws Exception {
        Long id = Long.valueOf(1);

        doNothing().when(itemDaoMock).delete(id);

		catalogService.deleteItem(id);
		
		verify(itemDaoMock).delete(id);
	}

    @Test
    public void findItemsTest() throws Exception {
		Item item = getItem();
        Item item2 = getItem2();

        ItemDTO itemDTO = getItemDto();
        ItemDTO itemDTO2 = getItemDto2();

        List<Item> returnedLst = new ArrayList<Item>();
		
		returnedLst.add(item);
		returnedLst.add(item2);
		
		when(itemDaoMock.findAll()).thenReturn(returnedLst);
        when(mockedMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);
        when(mockedMapper.map(item2, ItemDTO.class)).thenReturn(itemDTO2);

		List<ItemDTO> itemsDto = catalogService.findItems();
		
		assertNotNull(itemsDto);
		
		assertEquals(2, itemsDto.size());

        ItemDTO returneditemDTO1 = itemsDto.get(0);
        ItemDTO returneditemDTO2 = itemsDto.get(1);
        assertNotNull(returneditemDTO1);
        assertNotNull(returneditemDTO2);
    }
	

	@Test 
	public void findItemTest() throws Exception {
		Item item = getItem();
        ItemDTO itemDTO = getItemDto();

        when(itemDaoMock.findOne(item.getId())).thenReturn(item);
        when(mockedMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);

		ItemDTO myitemDto = catalogService.findItem(item.getId());
		
		assertNotNull(myitemDto);
		
		assertEquals(Long.valueOf(1L), myitemDto.getId());
		assertEquals(itemDTO, myitemDto);
	}

	
	@Test 
	public void findItemsByProductIdTest() throws Exception {
		Item item = getItem();
        Item item2 = getItem2();

        ItemDTO itemDTO = getItemDto();
        ItemDTO itemDTO2 = getItemDto2();

        List<Item> returnedLst = new ArrayList<Item>();
		
		returnedLst.add(item);
		returnedLst.add(item2);
		
		when(itemDaoMock.findAll()).thenReturn(returnedLst);
        when(mockedMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);
        when(mockedMapper.map(item2, ItemDTO.class)).thenReturn(itemDTO2);
        
        Product product = getProduct();
        ProductDTO productDTO = getProductDto();

        when(productDaoMock.findOne(product.getId())).thenReturn(product);
        when(mockedMapper.map(product, ProductDTO.class)).thenReturn(productDTO);

        Set<ItemDTO> itemsDtoSet = new HashSet<ItemDTO>();
        itemsDtoSet.add(itemDTO);
        productDTO.setItems(itemsDtoSet);
        
        
		List<ItemDTO> itemsDto = catalogService.findItems(Long.valueOf(1L));
		
		assertNotNull(itemsDto);
		
		assertEquals(1, itemsDto.size());

        ItemDTO returneditemDTO1 = itemsDto.get(0);
        assertNotNull(returneditemDTO1);
	}	
	
	
	@Test 
	public void searchItemsTest() throws Exception {
		Item item = getItem();
        Item item2 = getItem2();

        ItemDTO itemDTO = getItemDto();
        ItemDTO itemDTO2 = getItemDto2();

        List<Item> returnedLst = new ArrayList<Item>();
		
		returnedLst.add(item);
		returnedLst.add(item2);
		
		when(itemDaoMock.findAll()).thenReturn(returnedLst);
        when(mockedMapper.map(item, ItemDTO.class)).thenReturn(itemDTO);
        when(mockedMapper.map(item2, ItemDTO.class)).thenReturn(itemDTO2);

		List<ItemDTO> itemsDto = catalogService.searchItems("articleID");
		
		assertNotNull(itemsDto);
		
		assertEquals(2, itemsDto.size());

        ItemDTO returneditemDTO1 = itemsDto.get(0);
        ItemDTO returneditemDTO2 = itemsDto.get(1);
        assertNotNull(returneditemDTO1);
        assertNotNull(returneditemDTO2);
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
