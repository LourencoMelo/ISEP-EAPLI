package eapli.base.warehousemanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.warehousemanagement.builders.AGVBuilder;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ConfigureAGVController {

    /**
     * Instance of AGV repository
     */
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private final AGVBuilder agvBuilder;

    public ConfigureAGVController() {
        agvBuilder = new AGVBuilder();
    }

    public AGV configureAGV(final String agvId, final String description, final String model, final double maxWeight,
                            final double maxVolume, final String status, int x, int y, int autonomyMin){

        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.WAREHOUSE_EMPLOYEE);

        final var newAGV = new AGVBuilder()
                .agvIdBuild(agvId)
                .descriptionBuild(description)
                .modelBuild(model)
                .maxWeightBuild(maxWeight)
                .maxVolumeBuild(maxVolume)
                .statusBuild(status)
                .positionBuild(x,y)
                .autonomyBuild(autonomyMin)
                .build();

        return agvRepository.save(newAGV);
    }

}
