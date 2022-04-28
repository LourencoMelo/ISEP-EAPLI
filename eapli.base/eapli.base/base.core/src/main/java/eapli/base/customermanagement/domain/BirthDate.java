package eapli.base.customermanagement.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

import javax.persistence.Embeddable;

@Embeddable
public class BirthDate implements ValueObject {

    public int day;
    public int month;
    public int year;

    protected BirthDate(){

    }

    protected BirthDate(int day, int month, int year) throws IllegalArgumentException{
        Preconditions.ensure(day > 0 && day <= 31, "Day is eligible");
        Preconditions.ensure(month >0 && month <= 12, "Month is eligible");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String toString() {
        return this.day + "/" + this.month + "/" + this.year;
    }
}
