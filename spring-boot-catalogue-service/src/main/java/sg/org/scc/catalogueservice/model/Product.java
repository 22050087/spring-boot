package sg.org.scc.catalogueservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This tells Hibernate to create a table of this class
@Table(name = "products")
public class Product {
	
  public Product(Long id, String name, String description, String images, boolean enabled, double price) {
    super();
    this.id = id;
    this.name = name;
    this.description = description;
    this.images = images;
    this.enabled = enabled;
    this.price = price;
  }
  
  public Product(ProductDTO p) {
    super();
    this.id = p.getId();
    this.name = p.getName();
    this.description = p.getDescription();
    this.images = p.getImages();
    this.enabled = p.getEnabled();
    this.price = p.getPrice();
  }

  public Product() {
    super();
  }

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	// By @GeneratedValue, JPA makes a unique key automatically and applies the key to the field having @Id
    @Column(name="id")
    private Long id;
	
	@Column(name="name")
    private String name;
    
	@Column(name="description")
    private String description;
	
    @Column(name="images")
    private String images;
    
    @Column(name="enabled")
    private boolean enabled ;
    
    @Column(name="price")
    private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name  + ", description=" + description + ", images=" + images + ", enabled=" + enabled + ", price=" + price + "]";
	}
}
