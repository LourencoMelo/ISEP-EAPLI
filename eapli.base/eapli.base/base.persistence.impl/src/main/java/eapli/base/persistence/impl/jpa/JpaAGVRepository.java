package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.ordermanagement.domain.Order;
import eapli.base.warehousemanagement.domain.agv.*;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class JpaAGVRepository extends JpaAutoTxRepository<AGV, AGVId, AGVId> implements AGVRepository {

    public JpaAGVRepository(String persistenceUnitName){
        super(persistenceUnitName, Application.settings().getExtendedPersistenceProperties(), "id");
    }

    public JpaAGVRepository(TransactionalContext tx){
        super(tx, "id");
    }

    public Optional<AGV> findAGVById(String agvId){
        final Map<String, Object> params = new HashMap<>();
        AGVId agvIdObject = new AGVId(agvId);
        params.put("id", agvIdObject);
        return matchOne("e.id = :id", params);
    }

    @Override
    public List<AGV> findAvailableAGVS(MaxWeight orderWeight, MaxVolume orderVolume){
        final Map<String, Object> params = new HashMap<>();
        Status status = Status.READY;
        params.put("status", status);
        params.put("maxWeight", orderWeight);
        params.put("maxVolume", orderVolume);
        return match("e.status = :status AND e.maxWeight >= :maxWeight AND e.maxVolume >= :maxVolume", params);
    }

    public Iterable<AGV> findAll(){
        return findAll();
    }

}
