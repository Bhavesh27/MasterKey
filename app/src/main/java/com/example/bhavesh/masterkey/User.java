package com.example.bhavesh.masterkey;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BHAVESH on 29/12/2016.
 */

public class User  {

    //String email;
    //String password;
    String firstname;
    String lastname;
    long mobileno;

    //List<Account> accounts = new ArrayList<Account>();

    public User() {

    }

    public User(String firstname, String lastname, long mobileno) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobileno = mobileno;
       // this.accounts = accounts;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public long getMobileno() {
        return mobileno;
    }

    public void setMobileno(long mobileno) {
        this.mobileno = mobileno;
    }

   /* public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public int addAccount(Account account) {
        try{
            accounts.add(account);
            return 1;
        }
        catch (Exception e){
            System.out.println(e);
        }
    return 0;
    }*/
}
