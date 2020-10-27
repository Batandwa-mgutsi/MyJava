
/**
 * A Period object represents a period calendar time.
 * It has a start date and an end date.
 *
 * @author Stephan Jamieson
 * @version 14/10/2020
 */
public class Period {

    private final Date start;
    private final Date end;
    
 
    public Period(final Date start, final Date end) {
        this.start = start;
        this.end = end;
    }
    
    public Date start() { return start; }
    public Date end() { return end; }
    
    public int days() {
        return (int)end.subtract(start).intValue(TimeUnit.DAY);
    }
    
    public boolean contains(final Date date) {
        return start.compareTo(date)<=0 && end.compareTo(date)>=0;
    }
    
    public int indexOf(final Date date) {
        return (int)date.subtract(start).intValue(TimeUnit.DAY);
    }
 
    public String toString() {
        return start.toString()+" - "+end.toString();
    }
}
