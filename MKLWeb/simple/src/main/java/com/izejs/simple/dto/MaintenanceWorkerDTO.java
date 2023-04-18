package com.izejs.simple.dto;

/**
 * <p>
 *
 * </p>
 *
 * @author KunKa
 * @since 2021-04-03
 */
 public class MaintenanceWorkerDTO{
    private String name;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    private String workNumber;

    public String getWorkNumber(){
        return this.workNumber;
    }

    public void setWorkNumber(String workNumber){
        this.workNumber = workNumber;
    }
 }

