package lesson18_14;

import org.testng.annotations.*;

public class MyTestNg2 {

    @BeforeClass(alwaysRun=true)
    public static void beforeClass() {
        System.out.println("BeforeClass NG2");
    }

    @Test(groups = {"sanity"})
    public void test01() {
        System.out.println("Test01 NG2");
    }

    @Test(groups = {"regression"})
    public void test02() {
        System.out.println("Test02 NG2");
    }

    @BeforeMethod
    public static void beforeMethod() {
        System.out.println("BeforeMethod NG2");
    }

    @AfterMethod
    public static void afterMethod() {
        System.out.println("AfterMethod NG2");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("AfterClass NG2");
    }
}
