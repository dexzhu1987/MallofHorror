package Room;

import Character.*;

public class Supermarket extends Room {
    public Supermarket() {
        super(6, "Supermarket", 6);
    }

    @Override
    public boolean isFallen() {
        if (roomCharaters.size()>0){
            if (currentZombienumber>4){
                return true;
            } else {
                super.isFallen();
            }
        }
        return false;
    }


}
