package eapli.base.productmanagement.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eapli.framework.general.domain.model.Money;
import eapli.framework.strings.FormattedString;
import eapli.framework.strings.XmlCurrencyAdapter;
import eapli.framework.validations.Preconditions;
import eapli.framework.domain.model.ValueObject;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Currency;

@Embeddable
public class Cash implements Comparable<Cash>, Serializable, ValueObject, FormattedString {

    private static final long serialVersionUID = 1L;
    private static final transient int HUNDRED = 100;
    @XmlAttribute
    @JsonProperty
    private final Double amount;
    @XmlAttribute
    @XmlJavaTypeAdapter(XmlCurrencyAdapter.class)
    @JsonProperty
    private final Currency currency;

    protected Cash() {
        this.amount = null;
        this.currency = null;
    }

    public Cash(final double amount, final Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Cash dollars(final double amount) {
        return new Cash(amount, Currency.getInstance("USD"));
    }

    public static Cash euros(final double amount) {
        return new Cash(amount, Currency.getInstance("EUR"));
    }

    public static Cash valueOf(final String value) {
        String[] parts = value.split(" ");
        Preconditions.areEqual((long)parts.length, 2L);
        double am = Double.parseDouble(parts[0]);
        Currency curr = Currency.getInstance(parts[1]);
        return new Cash(am, curr);
    }

    public static Cash valueOf(final double amount, final String currency) {
        Currency curr = Currency.getInstance(currency);
        return new Cash(amount, curr);
    }
    /*public BigDecimal amount() {
        return (new BigDecimal(this.amount)).divide(BigDecimal.valueOf(100L));
    }

     */

    public double amountAsDouble() {
        return this.amount.doubleValue();
    }

    public Currency currency() {
        return this.currency;
    }

    /*public Cash[] divide(final int denominator) {
        BigInteger bigDenominator = BigInteger.valueOf((long)denominator);
        Cash[] result = new Cash[denominator];
        BigInteger simpleResult = this.amount.divide(bigDenominator);

        int remainder;
        for(remainder = 0; remainder < denominator; ++remainder) {
            result[remainder] = new Money(simpleResult, this.currency);
        }

        remainder = this.amount.subtract(simpleResult.multiply(bigDenominator)).intValue();

        for(int i = 0; i < remainder; ++i) {
            result[i] = result[i].add(new Money(BigInteger.valueOf(1L), this.currency));
        }

        return result;
    }

     */

    public Cash add(final Cash arg) {
        Preconditions.ensure(this.currency.equals(arg.currency), "Cannot add different currencies");
        return new Cash(Double.sum(this.amount, arg.amount), this.currency);
    }

    public int compareTo(final Cash arg) {
        Preconditions.areEqual(this.currency, arg.currency, "Cannot add different currencies");
        return this.amount.compareTo(arg.amount);
    }

    public boolean equals(final Object arg) {
        if (!(arg instanceof Cash)) {
            return false;
        } else {
            Cash other = (Cash)arg;
            return this.currency.equals(other.currency) && this.amount.equals(other.amount);
        }
    }

    public int hashCode() {
        int result = 11;
        result = 37 * result + this.amount.hashCode();
        result = 37 * result + this.currency.hashCode();
        return result;
    }

    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        nf.setCurrency(this.currency);
        nf.setGroupingUsed(true);
        nf.setMaximumFractionDigits(this.currency.getDefaultFractionDigits());
        return nf.format(this.amount.doubleValue());
    }

    private static class MoneyCollector {
        private Cash current;

        public MoneyCollector(final Cash zero) {
            this.current = zero;
        }

        public void add(final Cash b) {
            this.current = this.current.add(b);
        }
    }
}

