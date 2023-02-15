package cz.cvut.fit.warzeada.thesis.rest.dto;

public class FieldValueDto {

    private String fieldType;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    @Override
    public String toString() {
        return "FieldValueDto [fieldType=" + fieldType + "]";
    }

}

