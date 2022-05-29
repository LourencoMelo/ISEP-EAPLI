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
        final Map<String, Object> params = new HashMap<>();
        Status status = Status.READY;
        Status status2 = Status.BUSY;
        Status status3 = Status.CHARGING;
        Status status4 = Status.ON;
        Status status5 = Status.LOWBATTERY;
        params.put("status", status);
        params.put("status2", status2);
        params.put("status3", status3);
        params.put("status4", status4);
        params.put("status5", status5);
        return match("e.status = :status OR e.status = :status2 OR e.status = :status3 OR e.status = :status4 OR e.status = :status5", params);
    }

    public Iterable<AGV> findAll2() {
        return findAll();
    }
}
