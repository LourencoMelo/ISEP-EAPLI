package eapli.base.warehousemanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.MaxVolume;
import eapli.base.warehousemanagement.domain.agv.MaxWeight;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;

import java.util.List;

@ApplicationService
public class FindAGVReadyService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public List<AGV> findAvailableAGVS(MaxWeight orderWeight, MaxVolume orderVolume){
        return agvRepository.findAvailableAGVS(orderWeight, orderVolume);
    }

}
