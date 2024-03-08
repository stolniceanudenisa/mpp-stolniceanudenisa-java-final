package org.example.model;

public class Client extends Entity<Long>{

    private String name;
    private String address;

    public Client(Long id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    @Override
    public String toString() {
        return "Client: " +
                "name: " + name + '\'' +
                " address: '" + address + '\'' ;
    }
}