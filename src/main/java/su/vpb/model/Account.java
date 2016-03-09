package su.vpb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


public class Account implements Serializable {
    public Account() {
    }
    public Account(String name) {
        this.name = name;
    }


    private @Id @GeneratedValue int id;

    private String name;

    public List<Payment> payments;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "'"+name+"'";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account2 = (Account) o;

        return !(name != null ? !name.equals(account2.name) : account2.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
