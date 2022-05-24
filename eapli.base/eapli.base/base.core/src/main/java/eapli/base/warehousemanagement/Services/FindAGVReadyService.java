package eapli.base.warehousemanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;

@ApplicationService
public class FindAGVReadyService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public Iterable<AGV> findAvailableAGVS(){
        return agvRepository.findAvailableAGVS();
    }

}
