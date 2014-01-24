package welp.restaurants;

import javax.persistence.*;

@Entity
public class Restaurant {
    @Id @GeneratedValue private long id;
    private String name;
    private String address;
    private String description;
//    private long cusine_category_id Hibernate/JPA might have something nicer for this?

    protected Restaurant() {}

    public Restaurant(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}