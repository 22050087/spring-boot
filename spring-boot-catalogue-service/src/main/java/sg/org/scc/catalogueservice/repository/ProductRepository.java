package sg.org.scc.catalogueservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.org.scc.catalogueservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}

