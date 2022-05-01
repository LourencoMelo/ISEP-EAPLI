package eapli.base.customermanagement.domain;

import eapli.framework.infrastructure.authz.domain.model.Name;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CustomerTest {

    @Test
    public void ensureCustomerEqualsAreTheSameForTheSameInstance() throws Exception {
        final Customer aCustomer = new Customer();

        final boolean expected = aCustomer.equals(aCustomer);

        assertTrue(expected);
    }




}
