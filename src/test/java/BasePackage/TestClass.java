package BasePackage;

import org.testng.annotations.Test;

public class TestClass extends BaseClass {

    @Test(priority = 1)
    public void checkInstallment1() {
        checkResponseFields("1");
    }

    @Test(priority = 2)
    public void checkInstallment0() {
        checkResponseFields("0");
    }

    @Test(priority = 3)
    public void checkInstallmentNull() {
        checkStatusCode(500);
    }
}
