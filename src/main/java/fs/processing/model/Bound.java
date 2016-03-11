package fs.processing.model;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class Bound1 {
    DateTime datetime;
    public Bound(String datetime){
        DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("HH:mm");
        this.datetime = dateStringFormat.parseDateTime(datetime);
    }

    public DateTime getDateTime(){
        return datetime;
    }
    public boolean isGreaterThan(Bound bound){
        return datetime.isAfter(bound.getDateTime().getMillis()) ;

    }
}
