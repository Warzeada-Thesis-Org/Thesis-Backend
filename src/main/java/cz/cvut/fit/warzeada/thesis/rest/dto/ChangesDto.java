package cz.cvut.fit.warzeada.thesis.rest.dto;

public class ChangesDto {

    private FieldValueDto fieldValue;

    public FieldValueDto getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(FieldValueDto fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String toString() {
        return "ChangesDto [fieldValue=" + fieldValue + "]";
    }

}

