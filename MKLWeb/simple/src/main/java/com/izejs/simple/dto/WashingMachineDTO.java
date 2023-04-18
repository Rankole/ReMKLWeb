package com.izejs.simple.dto;

/**
 * <p>
 *
 * </p>
 *
 * @author KunKa
 * @since 2021-04-03
 */
 public class WashingMachineDTO{
    private String brandName;

    public String getBrandName(){
        return this.brandName;
    }

    public void setBrandName(String brandName){
        this.brandName = brandName;
    }
            private String typeName;

    public String getTypeName(){
        return this.typeName;
    }

    public void setTypeName(String typeName){
        this.typeName = typeName;
    }
            private String number;

    public String getNumber(){
        return this.number;
    }

    public void setNumber(String number){
        this.number = number;
    }
            private String status;

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }
 }

