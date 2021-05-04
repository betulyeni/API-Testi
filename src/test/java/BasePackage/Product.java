package BasePackage;

public class Product {
    private Boolean installment;
    private String installmentText;
    private int productGroupId;

    public Product(Boolean installment, String installmentText, int productGroupId) {
        this.installment = installment;
        this.installmentText = installmentText;
        this.productGroupId = productGroupId;
    }

    public Boolean getInstallment() {
        return installment;
    }

    public void setInstallment(Boolean installment) {
        this.installment = installment;
    }

    public String getInstallmentText() {
        return installmentText;
    }

    public void setInstallmentText(String installmentText) {
        this.installmentText = installmentText;
    }

    public int getProductGroupId() {
        return productGroupId;
    }

    public void setProductGroupId(int productGroupId) {
        this.productGroupId = productGroupId;
    }
}
