package BasePackage;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.get;

public class BaseClass {

    public String baseUrl = "https://4ccbaa7a-8fd6-4724-880e-07ec7149a98e.mock.pstmn.io";
    public String basePath = "/test";
    public String installmentPath = "/installment=";

    Response response;
    List<Product> productList;

    @BeforeClass
    public void configureApi() {
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = basePath;
    }

    private void getResponseFields(String installmentValue) {
        response = get(installmentPath + installmentValue);
        int size = response.jsonPath().getList("result.data.products").size();
        productList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Boolean installment = response.jsonPath().getBoolean("result.data.products[" + i + "].installment");
            String installmentText = response.jsonPath().getString("result.data.products[" + i + "].installmentText");
            int productGroupId = response.jsonPath().getInt("result.data.products[" + i + "].productGroupId");
            productList.add(new Product(installment,installmentText,productGroupId));
        }
    }

    public void checkResponseFields(String installmentValue) {
        getResponseFields(installmentValue);
        for (Product item : productList) {
            try {
                if (installmentValue.equals("1")) {
                    Assert.assertTrue(item.getInstallment());
                    Assert.assertNotEquals(item.getInstallmentText(), "");
                    Assert.assertEquals(item.getProductGroupId(), 1);
                }
                else if (installmentValue.equals("0")) {
                    Assert.assertFalse(item.getInstallment());
                    Assert.assertEquals(item.getInstallmentText(), "");
                    Assert.assertEquals(item.getProductGroupId(), 2);
                }
                System.out.println(item);
            } catch (AssertionError e) {
                System.out.println("Installment is different from expected. Actual value: " + item);
            }
        }

    }

    public void checkStatusCode(int statusCode) {
        response = get(installmentPath);
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @AfterClass
    public void close() {
        RestAssured.reset();
    }
}
