package eapli.base.warehousemanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;

import java.util.List;
import java.util.Optional;

@ApplicationService
public class FindAGVByIdService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public AGV findAGVById(String agvId){
        return this.agvRepository.findAGVById(agvId);
    }
}
