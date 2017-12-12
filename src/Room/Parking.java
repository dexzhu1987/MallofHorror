package Room;


public class Parking extends Room {
    public Parking() {
        super(4, "Parking", 99);
    }



    @Override
    public boolean isFallen(){
        if (roomCharaters.size()>0 && currentZombienumber>0){
            return true;
        }
        else {
            return false;
        }
    }

}
