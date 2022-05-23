package eapli.base.persistence.impl.inmemory;

import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.AGVId;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryAGVRepository extends InMemoryDomainRepository<AGV, AGVId> implements AGVRepository {

    static{
        InMemoryInitializer.init();
    }

    @Override
    public Optional<AGV> findAGVById(String agvId) {
        throw new UnsupportedOperationException();
    }
}
