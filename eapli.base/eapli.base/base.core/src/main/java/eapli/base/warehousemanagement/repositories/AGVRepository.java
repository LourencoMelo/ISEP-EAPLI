package eapli.base.warehousemanagement.repositories;

import eapli.base.ordermanagement.domain.Order;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.AGVId;
import eapli.base.warehousemanagement.domain.agv.MaxVolume;
import eapli.base.warehousemanagement.domain.agv.MaxWeight;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.List;
import java.util.Optional;


public interface AGVRepository extends DomainRepository<AGVId, AGV> {

    Optional<AGV> findAGVById(String agvId);

    List<AGV> findAvailableAGVS(MaxWeight orderWeight, MaxVolume orderVolume);


}
