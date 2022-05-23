package eapli.base.warehousemanagement.builders;

import eapli.base.warehousemanagement.domain.agv.*;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.general.domain.model.Description;

public class AGVBuilder implements DomainFactory<AGV> {

    private AGV agv;

    private AGVId agvId;

    private Description description;

    private Model model;

    private MaxWeight maxWeight;

    private MaxVolume maxVolume;

    private Status status;

    private Position position;

    private AutonomyMin autonomyMin;

    private String agvDockId;

    private Task task;

    @Override
    public AGV build() {
        final AGV agvFinal = buildOrThrow();
        agv = null;
        return agvFinal;
    }

    private AGV buildOrThrow(){
        if(agv != null){
            return agv;
        }else if (agvId != null && description != null && model != null && maxWeight != null && maxVolume != null &&
        status != null && position != null && autonomyMin != null && agvDockId != null){
            return agv = new AGV(agvId,description,model,maxWeight,maxVolume,status,position,autonomyMin, agvDockId);
        }else{
            throw new IllegalStateException();
        }
    }

    /**
     * AGV_ID Builder
     * @param agvId
     * @return
     */
    public AGVBuilder agvIdBuild(final String agvId){
        return agvIdBuild(new AGVId(agvId));
    }

    public AGVBuilder agvIdBuild(final AGVId agvId){
        this.agvId = agvId;
        return this;
    }

    /**
     * Description Builder
     * @param description
     * @return
     */
    public AGVBuilder descriptionBuild(final String description) {
        /**
         * Business Restrictions in description
         */
        try {
            if(description.length() < 30){
                return descriptionBuild(Description.valueOf(description));
            }else{
                throw new IllegalArgumentException();
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println("The description should be less than 30");
        }
        return null;
    }

    public AGVBuilder descriptionBuild(final Description description){
        this.description = description;
        return this;
    }

    /**
     * Model Builder
     * @return
     */
    public AGVBuilder modelBuild(String model){
        return modelBuild(new Model(model));
    }

    public AGVBuilder modelBuild(Model model){
        this.model = model;
        return this;
    }

    /**
     * MaxWeight Builder
     */
    public AGVBuilder maxWeightBuild(double maxWeight){
        return maxWeightBuild(new MaxWeight(maxWeight));
    }

    public AGVBuilder maxWeightBuild(MaxWeight maxWeight){
        this.maxWeight = maxWeight;
        return this;
    }

    /**
     * MaxVolume Builder
     */
    public AGVBuilder maxVolumeBuild(double maxVolume){
        return  maxVolumeBuild(new MaxVolume(maxVolume));
    }

    public AGVBuilder maxVolumeBuild(MaxVolume maxVolume){
        this.maxVolume = maxVolume;
        return this;
    }

    /**
     * Status Builder
     */
    public AGVBuilder statusBuild(){
        return statusBuild(Status.READY);
    }

    public AGVBuilder statusBuild(Status status){
        this.status = status;
        return this;
    }

    /**
     * Position Builder
     */
    public AGVBuilder positionBuild(int x, int y){
        return positionBuild(new Position(x, y));
    }

    public AGVBuilder positionBuild(Position position){
        this.position = position;
        return this;
    }

    /**
     * Autonomy Builder
     */
    public AGVBuilder autonomyBuild(int autonomyMin){
        return autonomyBuild(new AutonomyMin(autonomyMin));
    }

    public AGVBuilder autonomyBuild(AutonomyMin autonomyMin){
        this.autonomyMin = autonomyMin;
        return this;
    }

    /**
     * AGVDockId Builder
     */
    public AGVBuilder agvDockIdBuild(String agvDockId){
        this.agvDockId = agvDockId;
        return this;
    }


    /**
     * Task Builder
     */




}
