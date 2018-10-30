package com.practice.carmanufacturer.controller;

import com.practice.carmanufacturer.service.CarManService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CarManufacturerControllerTest {

    @InjectMocks
    private CarManufacturerController carManufacturerController;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllManufacturers() {
    }

    @Test
    public void updateManufacturer() {
    }
}