package sg.org.scc.catalogueservice.model;

public class ProductDTO {

   public ProductDTO(Long id, String name, String description, String images, boolean enabled, double price) {
    super();
    this.product = new Product(id, name, description, images, enabled, price);
  }
  
  private Product product;

  public Long getId() {
      return product.getId();
  }

  public void setId(Long id) {
    product.setId(id);
  }

  public String getName() {
      return product.getName();
  }

  public void setName(String name) {
    product.setName(name);
  }

  public String getDescription() {
      return product.getDescription();
  }

  public void setDescription(String description) {
    product.setDescription(description);
  }
  
  public String getImages() {
      return product.getImages();
  }

  public void setImages(String images) {
    product.setImages(images);
  }

  public boolean getEnabled() {
      return product.getEnabled();
  }

  public void setEnabled(boolean enabled) {
    product.setEnabled(enabled);
  }

  public double getPrice() {
      return product.getPrice();
  }

  public void setPrice(double price) {
    product.setPrice(price);
  }
}
