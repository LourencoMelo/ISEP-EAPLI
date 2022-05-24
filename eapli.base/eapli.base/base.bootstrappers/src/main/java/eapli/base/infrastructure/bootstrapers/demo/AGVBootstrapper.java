package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.customermanagement.domain.Address;
import eapli.base.customermanagement.domain.Customer;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.ordermanagement.domain.PaymentMethod;
import eapli.base.ordermanagement.domain.ShipmentMethod;
import eapli.base.productmanagement.domain.Product;
import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.TransactionSystemException;

import java.util.Calendar;
import java.util.Map;
import java.util.Optional;

public class AGVBootstrapper implements Action {

    ConfigureAGVController controller = new ConfigureAGVController();

    @Override
    public boolean execute() {

        registerAGV("agv-1", "agv numero 1", "xpto", 25.00, 30.00, 1, 3,
                120, "D1");
        registerAGV("agv-2", "agv numero 2", "xpto", 40.00, 50.00, 1, 5,
                180, "D2");

        System.out.println("AGV Bootstrapp done.");
        return true;
    }

    private Optional<AGV> registerAGV(final String agvId, final String description, final String model, final double maxWeight,
                                      final double maxVolume, int x, int y, int autonomyMin, String agvDockId) {
        try {
            return Optional.of(controller.configureAGV(agvId, description, model, maxWeight, maxVolume, x, y,
                    autonomyMin, agvDockId));
        } catch (final IntegrityViolationException | ConcurrencyException
                | TransactionSystemException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated object
            return Optional.empty();
        }

    }
}
