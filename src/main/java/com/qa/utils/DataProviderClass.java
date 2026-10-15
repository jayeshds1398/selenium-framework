package com.qa.utils;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        Object[][] data = ExcelUtils.getTestData(
                "src/test/resources/testdata/TestData.xlsx",
                "Login");

        return new Object[][] { data[0] };   // LOGIN_006
    }

    @DataProvider(name = "invalidUsernameData")
    public Object[][] invalidUsernameData() {
        Object[][] data = ExcelUtils.getTestData(
                "src/test/resources/testdata/TestData.xlsx",
                "Login");

        return new Object[][] { data[1] };   // LOGIN_007
    }

    @DataProvider(name = "invalidPasswordData")
    public Object[][] invalidPasswordData() {
        Object[][] data = ExcelUtils.getTestData(
                "src/test/resources/testdata/TestData.xlsx",
                "Login");

        return new Object[][] { data[2] };   // LOGIN_008
    }

    @DataProvider(name = "validResetData")
    public Object[][] validResetData() {
        Object[][] data = ExcelUtils.getTestData(
                "src/test/resources/testdata/TestData.xlsx",
                "ForgotPassword");

        return new Object[][] { data[0] };   // FP_008
    }

    @DataProvider(name = "invalidResetData")
    public Object[][] invalidResetData() {
        Object[][] data = ExcelUtils.getTestData(
                "src/test/resources/testdata/TestData.xlsx",
                "ForgotPassword");

        return new Object[][] { data[1] };   // FP_009
    }

    @DataProvider(name = "validCharData")
    public Object[][] validCharData() {
        Object[][] data = ExcelUtils.getTestData(
                "src/test/resources/testdata/TestData.xlsx",
                "ForgotPassword");

        return new Object[][] { data[3] };   // FP_012
    }

}