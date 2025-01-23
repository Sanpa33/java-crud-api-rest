package com.apirest.apirest.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.apirest.apirest.Repositories.ProductoRepository;

import com.apirest.apirest.Entities.Producto;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productRepository;
    
    @GetMapping
    public List<Producto> getAllProduct(){
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Producto getProductById(@PathVariable Long id){
        return productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product with the id was not found " + id ));
    }

    @PostMapping
    public Producto createProduct(@RequestBody Producto producto){
        return productRepository.save(producto);
    }

    @PutExchange("/{id}")
    public Producto updateProduct(@PathVariable Long id, @RequestBody Producto detallesProducto){
        Producto producto = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product with the id was not found " + id ));
        
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        return productRepository.save(producto);
    }


    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id){
        Producto producto = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The product with the id was not found " + id ));

        productRepository.delete(producto);        
        return "El prodcuto con el ID " + id + " fue eliminado correctamente";
    }




}
