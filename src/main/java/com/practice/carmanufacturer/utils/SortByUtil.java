package com.practice.carmanufacturer.utils;

import com.practice.carmanufacturer.entity.CarManufacturer;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.util.comparator.CompoundComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortByUtil {


    private static List<SortByParams> processSortByParams(String sortByString){
        List<SortByParams> processedSortByParams = new ArrayList<>();

        if(sortByString != null){
            List<String> sortByParamsList = Arrays.asList(sortByString.split("\\ "));

            for (String ob : sortByParamsList) {
                SortByParams sortByParams = SortByParams.fromString(ob);

                if (sortByParams != null) {
                    processedSortByParams.add(sortByParams);
                }

            }
        }

        return processedSortByParams;
    }

    public static List<CarManufacturer> sortBy(List<CarManufacturer> responseToSort, String sortByString){

        List<SortByParams> processedSortByParams = processSortByParams(sortByString);

        CompoundComparator<CarManufacturer> comparator = new CompoundComparator<>();

        for (SortByParams param: processedSortByParams) {
            comparator.addComparator(new PropertyComparator(param.getParam(),false,true));
        }

        if(comparator.getComparatorCount()>0){
            responseToSort.sort(comparator);
        }

        return responseToSort;

    }




}
