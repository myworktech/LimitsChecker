package fs.processing.model;

import java.io.Serializable;

/**
 * Created by pechenkin on 15.09.2015.
 */
public class Service implements Serializable {

    private String name;

    public Service(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;

        Service service = (Service) o;

        return !(name != null ? !name.equals(service.name) : service.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "'"+name+"'";
    }
}
