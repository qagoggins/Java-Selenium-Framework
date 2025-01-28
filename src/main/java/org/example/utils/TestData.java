package org.example.utils;


import org.testng.annotations.DataProvider;

import java.util.Arrays;

public class TestData {

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"}, // right name right password
//                {"problem_user", "asdasdasd"}, //right name wrong password
//                {"problem_user", "secret_sauce"},//right name right password
//                {"goggins", "secret_sauce"} // wrong name right password
        };
    }

    @DataProvider
    public Object[][] productsData() {
        return new Object[][] {
        { Arrays.asList("Sauce Labs Bolt T-Shirt", "Sauce Labs Backpack", "Sauce Labs Bike Light") }
//                { "Sauce Labs Bolt T-Shirt" },
//                { "Sauce Labs Backpack" },
//                { "Sauce Labs Bike Light" }
        };
    }

    @DataProvider
    public Object[][] checkoutFormData() {
        return new Object[][] {
                {"Cristiano", "Messi", "125030"}
        };
    }
}
