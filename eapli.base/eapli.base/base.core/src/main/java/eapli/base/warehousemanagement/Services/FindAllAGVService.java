package eapli.base.warehousemanagement.Services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.application.ApplicationService;

import java.util.List;

@ApplicationService
public class FindAllAGVService {

    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    public Iterable<AGV> findAll(){
        return agvRepository.findAll();
    }

}
