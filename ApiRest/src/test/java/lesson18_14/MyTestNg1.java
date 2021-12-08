package lesson18_14;

import org.testng.annotations.*;

public class MyTestNg1 {

        @BeforeClass
        public static void beforeClass() {
            System.out.println("BeforeClass");
        }

        @Test(groups = {"sanity"})
        public void test01() {
            System.out.println("Test01");
        }

        @Test(groups = {"regression"})
        public void test02() {
            System.out.println("Test02");
        }

        @BeforeMethod
        public static void beforeMethod() {
            System.out.println("BeforeMethod");
        }

        @AfterMethod
        public static void afterMethod() {
            System.out.println("AfterMethod");
        }

        @AfterClass
        public static void afterClass() {
            System.out.println("AfterClass");
        }


}
