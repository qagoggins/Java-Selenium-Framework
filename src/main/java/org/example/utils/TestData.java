package org.example.utils;


import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce"}, // right name right password
                {"problem_user", "asdasdasd"}, //right name wrong password
                {"problem_user", "secret_sauce"},//right name right password
                {"goggins", "secret_sauce"} // wrong name right password
        };
    }
}
