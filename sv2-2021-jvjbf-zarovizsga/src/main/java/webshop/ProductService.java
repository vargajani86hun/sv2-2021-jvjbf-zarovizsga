package webshop;

public class ProductService {

    private ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public void saleProduct(long id, int amount) {
        if (productRepo.findProductById(id).getStock() < amount) {
            throw new IllegalArgumentException("Have got less stock than the required amount: " + amount);
        }
        productRepo.updateProductStock(id, amount);
    }
}
