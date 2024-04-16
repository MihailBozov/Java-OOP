package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer{
    private static final double INITIAL_ENERGY = 60;
    
    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }
    
    @Override
    public void search() {
//        //first
//        super.setEnergy(super.getEnergy() - 7);
//        if (super.getEnergy() < 0) {
//            super.setEnergy(0);
//        }
//        //second
        super.setEnergy(Math.max(0, super.getEnergy() - 7));
    }
}
