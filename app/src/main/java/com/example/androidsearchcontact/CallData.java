package com.example.androidsearchcontact;

import java.io.Serializable;
import java.util.Date;

public class CallData implements Serializable{

    private String contactname;
    private String contactnumber;

//    private Date calldatetime;
//    private String callduration;

//  this is a constructor, has the same name as our CallData object
//this is the source of our information that is going to populate our views

    public CallData(String contactname, String contactnumber)
    {
//        pass values over to our constructor
//        this.calldatetime=calldatetime;
//        this.callduration=callduration;
        this.contactname=contactname;
        this.contactnumber=contactnumber;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }


    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {this.contactnumber = contactnumber;}


//    public Date getCalldatetime() {
//        return calldatetime;
//    }
//
//    public void setCalldatetime(Date calldatetime) {
//        this.calldatetime = calldatetime;
//    }
//
//    public String getCallduration() {
//        return callduration;
//    }
//
//    public void setCallduration(String callduration) {
//        this.callduration = callduration;
//    }


}

