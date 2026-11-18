package productapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductCalculationsTest {

    private static final double DELTA = 0.001;
    private IProductCalculations instance;

    public ProductCalculationsTest() {
    }

    @BeforeEach
    public void setUp() {
        // Price of R3500 is used as the valid test data
        instance = new ProductCalculations(3500);
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }

    /**
     * Unit Test 1: verifies that the GetVat method in the
     * ProductCalculations class correctly returns the VAT amount.
     */
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        double expResult = 525.0;
        double result = instance.GetVat();
        assertEquals(expResult, result, DELTA);
    }

    /**
     * Unit Test 2: verifies that the GetTotal method in the
     * ProductCalculations class correctly returns the total sale amount.
     */
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        double expResult = 4200.0;
        double result = instance.GetTotal();
        assertEquals(expResult, result, DELTA);
    }

    /**
     * Extra test: GetCommission (covers the third interface method).
     */
    @Test
    public void GetCommission_WithValidData_ReturnsCorrectValue() {
        double expResult = 175.0;
        double result = instance.GetCommission();
        assertEquals(expResult, result, DELTA);
    }
}