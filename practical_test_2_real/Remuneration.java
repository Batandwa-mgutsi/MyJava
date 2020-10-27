/**
 * Remuneration is an amount of money received in return for work done over a period of time.
 *
 * @author Stephan Jamieson
 * @version 14/10/2020
 */
public abstract class Remuneration {

    private Period period;
    
    protected Remuneration(final Period period) {
        this.period = period;
    }
    
    protected Remuneration(final Date startDate, final Date endDate) {
        this(new  Period(startDate, endDate));
    }
        
    public Period period() { 
        return this.period; 
    }

    protected void set(Period period) {
        this.period = period;
    }
    
    public abstract Money amount();
    
}
