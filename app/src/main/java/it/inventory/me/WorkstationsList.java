package it.inventory.me;

import java.util.Date;

public class WorkstationsList {
    private String id;
    private int computerName;
    private Date lastUpdated;


    public WorkstationsList(String id, int computerName, Date lastUpdated) {
        this.id = id;
        this.computerName = computerName;
        this.lastUpdated = lastUpdated;


    }
    public String getId() {
        return id;
    }


    public int getComputerName() {

        return computerName;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }
}