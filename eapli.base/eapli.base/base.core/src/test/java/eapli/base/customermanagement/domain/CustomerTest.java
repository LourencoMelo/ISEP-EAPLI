package eapli.base.customermanagement.domain;

import eapli.base.productmanagement.domain.Product;
import eapli.framework.general.domain.model.EmailAddress;
import eapli.framework.infrastructure.authz.domain.model.Name;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CustomerTest {

    private static final Name CUSTOMER_NAME = Name.valueOf("Maria", "Santos");
    private static final EmailAddress CUSTOMER_EMAIL_ADDRESS = EmailAddress.valueOf("maria@gmail.com");
    private static final Gender CUSTOMER_GENDER = Gender.Feminine;
    private static final PhoneNumber CUSTOMER_PHONE_NUMBER = new PhoneNumber(911111111);
    private static final VAT CUSTOMER_VAT = new VAT("251345345");
    private static final BirthDate CUSTOMER_BIRTHDATE = new BirthDate(15,2,2000);

    @Test
    public void ensureCustomerEqualsAreTheSameForTheSameInstance() throws Exception {
        final Customer aCustomer = new Customer();

        final boolean expected = aCustomer.equals(aCustomer);

        assertTrue(expected);
    }

    @Test
    public void ensureProduct() throws Exception {
        new Customer(CUSTOMER_NAME,CUSTOMER_EMAIL_ADDRESS,CUSTOMER_GENDER,CUSTOMER_PHONE_NUMBER,CUSTOMER_VAT,CUSTOMER_BIRTHDATE);
        assertTrue(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveName() throws Exception {
        new Customer(null,CUSTOMER_EMAIL_ADDRESS,CUSTOMER_GENDER,CUSTOMER_PHONE_NUMBER,CUSTOMER_VAT,CUSTOMER_BIRTHDATE);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveEMAIL_ADDRESS() throws Exception {
        new Customer(CUSTOMER_NAME,null,CUSTOMER_GENDER,CUSTOMER_PHONE_NUMBER,CUSTOMER_VAT,CUSTOMER_BIRTHDATE);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveGENDER() throws Exception {
        new Customer(CUSTOMER_NAME,CUSTOMER_EMAIL_ADDRESS,null,CUSTOMER_PHONE_NUMBER,CUSTOMER_VAT,CUSTOMER_BIRTHDATE);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHavePHONE_NUMBER() throws Exception {
        new Customer(CUSTOMER_NAME,CUSTOMER_EMAIL_ADDRESS,CUSTOMER_GENDER,null,CUSTOMER_VAT,CUSTOMER_BIRTHDATE);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveVAT() throws Exception {
        new Customer(CUSTOMER_NAME,CUSTOMER_EMAIL_ADDRESS,CUSTOMER_GENDER,CUSTOMER_PHONE_NUMBER,null,CUSTOMER_BIRTHDATE);;
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMustHaveBIRTHDATE() throws Exception {
        new Customer(CUSTOMER_NAME,CUSTOMER_EMAIL_ADDRESS,CUSTOMER_GENDER,CUSTOMER_PHONE_NUMBER,CUSTOMER_VAT,null);;
    }
}
