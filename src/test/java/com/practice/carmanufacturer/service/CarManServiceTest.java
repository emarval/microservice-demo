package com.practice.carmanufacturer.service;

import com.practice.carmanufacturer.entity.CarManufacturer;
import com.practice.carmanufacturer.entity.Response;
import com.practice.carmanufacturer.repository.CarManufacturerRepository;
import com.practice.carmanufacturer.utils.SearchParameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarManServiceTest {

    @Mock
    private CarManufacturerRepository carManRepositoryMock;

    @InjectMocks
    private CarManService carManService;

    private List<CarManufacturer> carManDataMocked;


    @Before
    public void setUp() throws Exception {

        carManDataMocked = new ArrayList<>(Arrays.asList(
                new CarManufacturer("Venezuela", "Tesla", 1,"Tesla Inc",new ArrayList<VehicleType>() ),
                new CarManufacturer("Colombia", "Tesla", 2,"Tesla Inc",new ArrayList<VehicleType>() ),
                new CarManufacturer("Ecuador", "Tesla", 3,"Tesla Inc",new ArrayList<VehicleType>() ),
                new CarManufacturer("Peru", "Tesla", 4,"Tesla Inc",new ArrayList<VehicleType>() )
        ));

        when(carManRepositoryMock.findAll()).thenReturn(carManDataMocked);



        when(carManRepositoryMock.findById(0)).thenReturn(Optional.of(carManDataMocked.get(0)));

//        when(carManRepositoryMock.deleteById(1)).getMock();

    }

    @Test
    public void addManufacturerWithNullManufacturer() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.addManufacturer(null).getStatusCode());
    }

    @Test
    public void addManufacturerWithEmptyManufacturer() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.addManufacturer(new CarManufacturer()).getStatusCode());
    }

    @Test
    public void addManufacturerWithCorrectManufacturer() {


        CarManufacturer correctManufacturer =  new CarManufacturer("Venezuela", "Tesla", 1,"Tesla Inc",new ArrayList<VehicleType>() );

        Response<CarManufacturer> responseToCheck = carManService.addManufacturer(correctManufacturer);

        assertEquals(HttpStatus.CREATED,responseToCheck.getStatusCode());
        assertEquals(correctManufacturer,responseToCheck.getData());

    }

    @Test
    public void initDbWithNullList() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.initDb(null).getStatusCode());

    }

    @Test
    public void initDbWithEmptyList() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.initDb(new ArrayList<>()).getStatusCode());

    }

    @Test
    public void initDbWithListContainingDuplicates() {

        fail("not implemented yet");



    }

    @Test
    public void initDbWithListWithNoDuplicates() {

        Response<List<CarManufacturer>> responseToCheck = carManService.initDb(carManDataMocked);

        assertEquals(HttpStatus.CREATED,responseToCheck.getStatusCode());
        assertEquals(carManDataMocked,responseToCheck.getData());

    }

    @Test
    public void removeManufacturerNullId() throws Exception{

        Response<CarManufacturer> responseToCheck = carManService.removeManufacturer(null);

        assertEquals(HttpStatus.BAD_REQUEST.value(),responseToCheck.getStatusCode());

    }

//    @Test
//    public void removeManufacturerEmptyId() {
//
//        Response<CarManufacturer> responseToCheck = carManService.removeManufacturer();
//
//        assertEquals(HttpStatus.BAD_REQUEST,responseToCheck.getStatusCode());
//    }

    @Test
    public void removeManufacturerCorrectId() throws Exception {

        Response<CarManufacturer> responseToCheck = carManService.removeManufacturer("0");

        assertEquals(HttpStatus.OK,responseToCheck.getStatusCode());

        assertEquals(carManDataMocked.get(0),responseToCheck.getData());
    }

    @Test
    public void updateManufacturerNullIdNullManufacturer() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.updateManufacturer(null,null).getStatusCode());
    }

    @Test
    public void updateManufacturerNullIdEmptyManufacturer() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.updateManufacturer(new CarManufacturer(),null).getStatusCode());
    }

    @Test
    public void updateManufacturerEmptyIdNullManufacturer() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.updateManufacturer(null,"").getStatusCode());
    }

    @Test
    public void updateManufacturerEmptyIdEmptyManufacturer() {

        assertEquals(HttpStatus.BAD_REQUEST,carManService.updateManufacturer(new CarManufacturer(),"").getStatusCode());
    }

    @Test
    public void updateManufacturerCorrectId() {

        fail("not implemented yet");
    }

    @Test
    public void findAllWithNullOrderByAndEmptySearchParameters() {


        assertEquals("Search Parameter is Empty + sortBy is null.",
                carManDataMocked, carManService.findBy(new SearchParameters(),null));

    }

    @Test
    public void  findAllWithNullOrderByNullSearchParams(){

        assertEquals("Search Parameter is null + sortBy is null",
                carManDataMocked,carManService.findBy(null,null));

    }

    @Test
    public void  findAllWithEmptyOrderByNullSearchParams(){

        assertEquals("Search Parameter is null + sortBy is Empty",
                carManDataMocked,carManService.findBy(null,""));

    }

    @Test
    public void findAllWithEmptyOrderByEmptySearchParams(){

        assertEquals("Search Parameter is Empty + sortBy is Empty",
                carManDataMocked,carManService.findBy(new SearchParameters(),""));
    }

    @Test
    public void findByWithCorrectParametersAndEmptyOrderBy(){

        SearchParameters correctSearchParameters = new SearchParameters();

        correctSearchParameters.setCountry("Venezuela");

        CarManufacturer correctManufacturerExample = new CarManufacturer();

        correctManufacturerExample.setCountry("Venezuela");

        when(carManRepositoryMock.findAll(Example.of(correctManufacturerExample))).thenReturn(carManDataMocked);

        assertEquals(carManDataMocked,carManService.findBy(correctSearchParameters,""));

    }


}