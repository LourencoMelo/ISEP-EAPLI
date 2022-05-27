package eapli.base.warehousemanagement.services;

import eapli.base.warehousemanagement.Services.FindAGVByIdService;
import eapli.base.warehousemanagement.application.ConfigureAGVController;
import eapli.base.warehousemanagement.builders.AGVBuilder;
import eapli.base.warehousemanagement.domain.agv.AGV;
import eapli.base.warehousemanagement.domain.agv.Status;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;


public class FindAGVByIdServiceTest {


    FindAGVByIdService findAGVByIdService = new FindAGVByIdService();
    ConfigureAGVController configureAGVController = new ConfigureAGVController();

    /**
    @Test
    public void foundAGVByIdService(){
        AGV agv =  configureAGVController.configureAGV("agv-1","Fast", "XPTO", 4,10,2,3,10,"1" );
        Optional<AGV> agvFind = findAGVByIdService.findAGVById("agv-1");
        AGV agv1 = agvFind.get();
        assertEquals(agv.toString(),agv1.toString());
    }

    @Test
    public void notfoundAGVByIdService(){
        Optional<AGV> agvFind = findAGVByIdService.findAGVById("agv-22");
        assertEquals(null, agvFind);
    }

    **/

}
