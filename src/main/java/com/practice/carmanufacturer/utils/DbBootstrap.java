package com.practice.carmanufacturer.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.practice.carmanufacturer.entity.CarManufacturer;
import com.practice.carmanufacturer.service.CarManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DbBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private static CarManService carManService;

    public static final String DATABASE_JSON_FILE_PATH = "/carmanufacturer/data/carmfg_data.json";

    @Autowired
    public DbBootstrap(CarManService carManService){
        DbBootstrap.carManService = carManService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initDatabase();
    }

    public void initDatabase(){
        ObjectMapper mapper = new ObjectMapper();
        InputStream inputStream = TypeReference.class.getResourceAsStream(DATABASE_JSON_FILE_PATH);

        try {
            JsonNode data = mapper.readValue(inputStream, TypeFactory.defaultInstance().constructType(JsonNode.class));
            ObjectReader reader = mapper.readerFor(new TypeReference<List<CarManufacturer>>() {
            });
            List<CarManufacturer> entities = reader.readValue(data.get("Data"));

            carManService.initDb(entities);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
