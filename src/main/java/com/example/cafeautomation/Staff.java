package com.example.cafeautomation;

public class Staff {
    protected static Staff activeStaff;

    //region properties
    protected int id;
    protected int jobId;
    protected String username;
    protected String password;
    protected String name;
    protected String surname;
    //endregion


    private Staff(int id, int jobId, String username, String password, String name, String surname) {
        this.id = id;
        this.jobId = jobId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public static Staff getActiveStaff(int id,int jobId,String username, String password, String name, String surname){
        if(activeStaff== null){
            activeStaff= new Staff(id,jobId,username,password,name,surname);
        }
        return activeStaff;
    }

}
