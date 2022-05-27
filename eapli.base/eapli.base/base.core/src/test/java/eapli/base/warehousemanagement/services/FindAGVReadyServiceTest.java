package eapli.base.warehousemanagement.services;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.warehousemanagement.Services.FindAGVReadyService;
import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.builders.AGVBuilder;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.MaxVolume;
import eapli.base.warehousemanagement.domain.agv.MaxWeight;
import eapli.base.warehousemanagement.domain.agv.Status;
import eapli.base.warehousemanagement.repositories.AGVRepository;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

public class FindAGVReadyServiceTest {

    /**
    ConfigureAGVController configureAGVController = new ConfigureAGVController();
    FindAGVReadyService findAGVReadyService = new FindAGVReadyService();
    private final AGVRepository agvRepository = PersistenceContext.repositories().agv();


    @Test
    public void findAvailableAGVSForWeight(){
        AGV agv1 =  configureAGVController.configureAGV("agv-1","Fast", "XPTO", 4,10,2,3,10,"1" );
        AGV agv2 = configureAGVController.configureAGV("agv-2","Fast", "XPTO", 2,10,1,2,10,"2" );
        MaxWeight maxWeight = new MaxWeight(3);
        MaxVolume maxVolume = new MaxVolume(1);
        List<AGV> agvList = findAGVReadyService.findAvailableAGVS(maxWeight,maxVolume);
        List<AGV> agvListExpected = new ArrayList<>();
        agvListExpected.add(agv1);

        assertEquals(agvListExpected, agvList);
    }

    @Test
    public void findAvailableAGVSForVolume(){
        AGV agv1 =  configureAGVController.configureAGV("agv-1","Fast", "XPTO", 4,10,2,3,10,"1" );
        AGV agv2 = configureAGVController.configureAGV("agv-2","Fast", "XPTO", 2,10,1,2,10,"2" );
        MaxWeight maxWeight = new MaxWeight(2);
        MaxVolume maxVolume = new MaxVolume(3);
        List<AGV> agvList = findAGVReadyService.findAvailableAGVS(maxWeight,maxVolume);
        List<AGV> agvListExpected = new ArrayList<>();
        agvListExpected.add(agv1);

        assertEquals(agvListExpected, agvList);
    }

    @Test
    public void findReadyAGVS(){
        final AGV agv = new AGVBuilder().agvIdBuild("agv-1").descriptionBuild("fast").modelBuild("xpto").maxWeightBuild(10)
                .maxVolumeBuild(2).statusBuild(Status.READY).positionBuild(2,3).autonomyBuild(200).agvDockIdBuild("1")
                .build();
        final AGV agv1 = new AGVBuilder().agvIdBuild("agv-2").descriptionBuild("fast").modelBuild("xpto").maxWeightBuild(10)
                .maxVolumeBuild(2).statusBuild(Status.BUSY).positionBuild(2,3).autonomyBuild(200).agvDockIdBuild("2")
                .build();

        agvRepository.save(agv);
        agvRepository.save(agv1);

        MaxWeight maxWeight = new MaxWeight(3);
        MaxVolume maxVolume = new MaxVolume(1);
        List<AGV> agvList = findAGVReadyService.findAvailableAGVS(maxWeight,maxVolume);
        List<AGV> agvListExpected = new ArrayList<>();
        agvListExpected.add(agv);

        assertEquals(agvListExpected,agvList);

    }
    **/
}
