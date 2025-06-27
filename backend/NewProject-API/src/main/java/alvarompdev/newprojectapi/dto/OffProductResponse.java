package alvarompdev.newprojectapi.dto;

public class OffProductResponse {
    private int status;
    private OffProduct product;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public OffProduct getProduct() {
        return product;
    }

    public void setProduct(OffProduct product) {
        this.product = product;
    }
}