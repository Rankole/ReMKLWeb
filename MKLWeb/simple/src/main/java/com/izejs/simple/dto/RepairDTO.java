package com.izejs.simple.dto;

/**
 * <p>
 *
 * </p>
 *
 * @author KunKa
 * @since 2021-04-03
 */
 public class RepairDTO{
    private String brand;

    public String getBrand(){
        return this.brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }
            private String brandType;

    public String getBrandType(){
        return this.brandType;
    }

    public void setBrandType(String brandType){
        this.brandType = brandType;
    }
            private String washingMachineNumber;

    public String getWashingMachineNumber(){
        return this.washingMachineNumber;
    }

    public void setWashingMachineNumber(String washingMachineNumber){
        this.washingMachineNumber = washingMachineNumber;
    }

    private Integer repairUserId;

    public Integer getRepairUserId() {
        return repairUserId;
    }

    public void setRepairUserId(Integer repairUserId) {
        this.repairUserId = repairUserId;
    }
}

