/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.main;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author makhw
 */
public class ProductTest {

    private Product product;
    
    public ProductTest() {
    }
    
    @BeforeEach
    public void setUp() {
        String[] names = {
            "Cozy Nights Monarch mattress",
            "Restonic Barbados mattress",
            "Sealy Phuket mattress"
        };
        int[][] sales = {
            {25, 18, 20},
            {15, 10, 12},
            {18, 15, 20}
        };
        product = new Product(names, sales);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of GetTotalProductsSold method, of class Product.
     */
    @Test
    public void testGetTotalProductsSold() {
        assertEquals(153, product.GetTotalProductsSold());
    }

    /**
     * Test of GetAverageProductSold method, of class Product.
     */
    @Test
    public void testGetAverageProductSold() {
        assertEquals(17.0, product.GetAverageProductSold(), 0.001);
    }

    /**
     * Test of GetMostPopularProduct method, of class Product.
     */
    @Test
    public void testGetMostPopularProduct() {
        assertEquals("Cozy Nights Monarch mattress (63)", product.GetMostPopularProduct());
    }
    
}