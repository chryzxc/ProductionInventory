package it.inventory.me;

import java.util.Date;

public class WorkstationsList {
    private String id;
    private int computerName;


    public WorkstationsList(String id, int computerName) {
        this.id = id;
        this.computerName = computerName;


    }

    public int getComputerName() {

        return computerName;
    }

    public String getId() {
        return id;
    }


}