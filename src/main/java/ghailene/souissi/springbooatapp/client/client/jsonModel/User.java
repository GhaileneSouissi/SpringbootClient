package ghailene.souissi.springbooatapp.client.client.jsonModel;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/***
 * The json object
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    //the user id
    private int id;
    //the user name
    private String name;
    //the user age
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

