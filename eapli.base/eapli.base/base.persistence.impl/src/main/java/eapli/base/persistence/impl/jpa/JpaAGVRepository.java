package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.AGVId;
import eapli.base.warehousemanagement.domain.agv.Status;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JpaAGVRepository extends JpaAutoTxRepository<AGV, AGVId, AGVId> implements AGVRepository {

    public JpaAGVRepository(String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    public JpaAGVRepository(TransactionalContext tx){
        super(tx, "id");
    }

    public List<AGV> findAGVById(String agvId){
        final Map<String, Object> params = new HashMap<>();
        AGVId agvIdObject = new AGVId(agvId);
        params.put("id", agvIdObject);
        return match("e.id = :id", params);
    }

    @Override
    public Iterable<AGV> findAvailableAGVS(){
        final Map<String, Object> params = new HashMap<>();
        Status status = Status.READY;
        params.put("status", status);
        return match("e.status = :status", params);
    }
}
