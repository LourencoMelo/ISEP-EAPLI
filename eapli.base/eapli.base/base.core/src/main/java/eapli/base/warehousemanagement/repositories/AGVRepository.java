package eapli.base.warehousemanagement.repositories;

import eapli.base.ordermanagement.domain.Order;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.AGVId;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;


public interface AGVRepository extends DomainRepository<AGVId, AGV> {

    AGV findAGVById(String agvId);

    Iterable<AGV> findAvailableAGVS();
}
