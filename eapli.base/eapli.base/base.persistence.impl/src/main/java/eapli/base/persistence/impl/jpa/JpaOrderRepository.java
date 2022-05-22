package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.OrderStatus;
import eapli.base.ordermanagement.repositories.OrderRepository;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.domain.ProductCategory;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import org.springframework.security.access.method.P;

import java.util.HashMap;
import java.util.Map;

public class JpaOrderRepository extends JpaAutoTxRepository<Order, Long, Long> implements OrderRepository {

    public JpaOrderRepository(String persistenceUnitName) {
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    public JpaOrderRepository(TransactionalContext tx){
        super(tx, "id");
    }

    @Override
    public Iterable<Order> ordersToBePaid() {
        final Map<String, Object> params = new HashMap<>();
        OrderStatus status = OrderStatus.PAYMENT_PENDING;
        params.put("status", status);
        return match("e.status = :status", params);
    }

    @Override
    public Iterable<Order> ordersToBePrepared() {
        final Map<String, Object> params = new HashMap<>();
        OrderStatus status = OrderStatus.PAID;
        params.put("status", status);
        return match("e.status = :status", params);
    }

    public Iterable<Order> ordersPrepared() {
        final Map<String, Object> params = new HashMap<>();
        OrderStatus status = OrderStatus.PREPARED;
        params.put("status", status);
        return match("e.status = :status", params);
    }
}
