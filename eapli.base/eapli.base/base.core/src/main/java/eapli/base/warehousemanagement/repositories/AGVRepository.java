package eapli.base.warehousemanagement.repositories;

import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.AGVId;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface AGVRepository extends DomainRepository<AGVId, AGV> {

    Optional<AGV> findAGVById(String agvId);
}
