package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.AGVId;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaAGVRepository extends JpaAutoTxRepository<AGV, AGVId, AGVId> implements AGVRepository {

    public JpaAGVRepository(String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    public JpaAGVRepository(TransactionalContext tx){
        super(tx, "id");
    }
}
