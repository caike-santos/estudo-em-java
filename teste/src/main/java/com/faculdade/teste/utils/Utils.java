package com.faculdade.teste.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static  void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertiesNames(source));
    }
    
    public static  String[] getNullPropertiesNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> nomes = new HashSet<>();

        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                nomes.add(pd.getName());
            }
        }

        String[] resultado = new String[nomes.size()];
        return nomes.toArray(resultado);
    }
}
