import com.sun.xml.internal.ws.developer.Serialization;

import java.io.Serializable;

public class Professor implements Serializable{
    private String name;

    public String getName() {
        return name;
    }

    public Professor(String name) {
        this.name = name;
    }

}