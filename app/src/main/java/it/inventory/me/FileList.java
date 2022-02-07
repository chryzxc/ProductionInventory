package it.inventory.me;

import java.util.Date;

public class FileList {

    private String id,fileName,password;
    private Date dateCreated,lastUpdated;



    public FileList(String id, String fileName, Date dateCreated, Date lastUpdated, String password) {
        this.id = id;
        this.fileName = fileName;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.password = password;


    }

    public String getId() {

        return id;
    }



    public String getFileName() {
        return fileName;

    }


    public Date getDateCreated() {
        return dateCreated;

    }


    public Date getLastUpdated() {
        return lastUpdated;

    }

    public String getPassword() {
        return password;
    }







}