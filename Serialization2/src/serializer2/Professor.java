package serializer2;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Professor implements Serializable{
    private String name;

    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    public String getName() {
        return name;
    }

    public Professor(String name) {
        this.name = name;
    }

    public String toString(){
        return new String(AppLocale.getString( AppLocale.name ) + ": " + name + " " +
                AppLocale.getString( AppLocale.creation ) + ": " + getCreationDate() );
    }
}