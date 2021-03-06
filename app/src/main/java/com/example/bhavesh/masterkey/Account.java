package com.example.bhavesh.masterkey;


class Account {

    private String username;
    private String password;
    private String email;
    private String vendorname;

    public Account() {

    }

    Account(String username, String password, String email, String vendorname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.vendorname = vendorname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Username='" + username + '\n' + "email='" + email + '\n' + "password='" + password + '\n' + "vendorname='" + vendorname + '\n';
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }
}
