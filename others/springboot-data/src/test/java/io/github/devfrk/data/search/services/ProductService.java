package io.github.devfrk.data.search.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import io.github.devfrk.data.search.daos.ProductRepository;
import io.github.devfrk.data.search.models.Product;
import io.github.kubesys.devfrk.spring.cores.AbstractHttpHandler;
import io.github.kubesys.devfrk.tools.annotations.ServiceDefinition;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Jennifer Reif
 * @author Michael J. Simons
 */
@ServiceDefinition
public class ProductService extends AbstractHttpHandler {

	@Autowired
    private ProductRepository productRepository;

    public List<Product> searchProducts(String name) {
        return productRepository.findByName(name);
    }

}
