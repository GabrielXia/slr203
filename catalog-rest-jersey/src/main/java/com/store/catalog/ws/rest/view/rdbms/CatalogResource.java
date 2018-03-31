package com.store.catalog.ws.rest.view.rdbms;

import com.store.catalog.model.ProductDTO;
import com.store.catalog.service.catalog.CatalogService;
import com.store.catalog.model.CategoryDTO;
import com.store.catalog.model.ItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by ZCadi on 26/10/2015.
 */

@Path("/catalog")
public class CatalogResource {



    @Autowired CatalogService catalogServiceImpl;

    public CatalogService getCatalogServiceImpl() {
        return catalogServiceImpl;
    }

    public void setCatalogServiceImpl(CatalogService catalogServiceImpl) {
        this.catalogServiceImpl = catalogServiceImpl;
    }

    @Path("/categories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CategoryDTO> getCategories() throws Exception {
    	return catalogServiceImpl.findCategories();
    }
    
    @Path("/category/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory(@PathParam("id") Long id) throws Exception {
    	return Response.ok(catalogServiceImpl.findCategory(id)).build();
    }


    //curl -i http://localhost:8080/catalog/catalog/category/post -H "Content-Type: application/json" -X POST -d '{"id":101,"name":"food","description":"some food","products":null}'
    @Path("/category/post")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCategory(CategoryDTO categoryDTO) throws Exception {

    	catalogServiceImpl.createCategory(categoryDTO);
        return Response.status(200).entity("Done").build();

    }

    //curl -i http://localhost:8080/catalog/catalog/category -H "Content-Type: application/json" -X PUT -d '{"id":101,"name":"food","description":"some food","products":null}'  
    @Path("/category")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCategory(CategoryDTO categoryDTO) throws Exception {

    	catalogServiceImpl.updateCategory(categoryDTO);
        return Response.status(200).entity("Done").build();

    }


    //curl -X DELETE 'http://localhost:8080/catalog/catalog/category/101'
    @Path("/category/{id}")
    @DELETE
    public Response deleteCategory(@PathParam("id") Long id) throws Exception {
    	catalogServiceImpl.deleteCategory(id);
    	return Response.status(200).entity("deleted").build();

    }


    @Path("/products")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() throws Exception {
    	return Response.ok(catalogServiceImpl.findProducts()).build();
    }

    @Path("/product/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProduct(@PathParam("id") Long id) throws Exception {
    	return Response.ok(catalogServiceImpl.findProduct(id)).build();
    }

    //curl -i http://localhost:8080/catalog/catalog/product/post -H "Content-Type: application/json" -X POST -d '{"id":202,"name":"cookies","description":"sweet","category":{"id":101,"name":"food","description":"some food","products":null},"items":null}'
    @Path("/product/post")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductDTO productDTO) throws Exception {
    	catalogServiceImpl.createProduct(productDTO);
        return Response.status(200).entity("Done").build();
    }

    // curl -i http://localhost:8080/catalog/catalog/product/update -H "Content-Type: application/json" -X PUT -d '{"id":202,"name":"cookies","description":"sweet","category":{"id":101,"name":"food","description":"some food","products":null},"items":null}'
    @Path("/product/update")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProduct(ProductDTO productDTO) throws Exception {
    	catalogServiceImpl.updateProduct(productDTO);
    	return Response.status(200).entity("Done").build();
    }

    //curl -X DELETE 'http://localhost:8080/catalog/catalog/product/delete/202'
    @Path("/product/delete/{id}")
    @DELETE
    public Response deleteProduct(@PathParam("id") Long id) throws Exception {
    	catalogServiceImpl.deleteProduct(id);
    	return Response.status(200).entity("deleted").build();

    }

    @Path("/items")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems() throws Exception {

    	return Response.ok(catalogServiceImpl.findItems()).build();
    }


    @Path("/item/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItem(@PathParam("id") Long id) throws Exception {
    	return Response.ok(catalogServiceImpl.findItem(id)).build();
    }

    //curl -i http://localhost:8080/catalog/catalog/item/post -H "Content-Type: application/json" -X POST -d '{"id":303,"name":"cheap cookies","product":{"id":202,"name":"cookies","description":"sweet","category":{"id":101,"name":"food","description":"some food","products":null},"items":null}}}'
    @Path("/item/post")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createItem(ItemDTO itemDTO) throws Exception {
    	catalogServiceImpl.createItem(itemDTO);
    	return Response.status(200).entity("Done").build();
    }
    
    //curl -i http://localhost:8080/catalog/catalog/item/update -H "Content-Type: application/json" -X PUT -d '{"id":303,"name":"cheap cookies","product":{"id":202,"name":"cookies","description":"sweet","category":{"id":101,"name":"food","description":"some food","products":null},"items":null}}}'
    @Path("/item/update")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON) // request
    @Produces(MediaType.APPLICATION_JSON) //response
    public Response updateItem(ItemDTO itemDTO) throws Exception {
    	catalogServiceImpl.updateItem(itemDTO);
    	return Response.status(200).entity("Done").build();
    }

    //curl -X DELETE 'http://localhost:8080/catalog/catalog/item/delete/303'
    @Path("/item/delete/{id}")
    @DELETE
    public Response deleteItem(@PathParam("id") Long id) throws Exception {
    	catalogServiceImpl.deleteItem(id);
    	return Response.status(200).entity("deleted").build();
    }

    @Path("/item/byName/{name}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItemsByName(@PathParam("name") String name) throws Exception {
    	return Response.ok(catalogServiceImpl.searchItems(name)).build();
    }

}
