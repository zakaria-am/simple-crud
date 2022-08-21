package com.zakaria.simplecrud.enums;

import javax.persistence.AttributeConverter;

public class GenderConverter implements AttributeConverter<Gender, String> {
    @Override
    public String convertToDatabaseColumn(Gender attribute) {
        return attribute.getValue();
    }

    @Override
    public Gender convertToEntityAttribute(String dbData) {
        if (dbData!=null){
            switch (dbData){
                case "M" :
                    return Gender.MALE;
                case "F" :
                    return Gender.FEMALE;
            }
        }
        return null;
    }
}
