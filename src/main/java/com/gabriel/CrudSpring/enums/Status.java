package com.gabriel.CrudSpring.enums;

public enum Status {
    ACTIVE("Ativo"), INACTIVE("Inativo");

    private String value;

    private Status(String value) {
        this.value = value;
    } 

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
