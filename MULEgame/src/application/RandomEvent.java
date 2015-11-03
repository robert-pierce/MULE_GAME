package application;

import java.io.Serializable;

public class RandomEvent implements Serializable {
	private static final long serialVersionUID = -4378718716859395316L;
	private boolean isBad;

    public RandomEvent(boolean isBad) {
        this.isBad = isBad;
    }

//    @Override

//    public boolean equals(Object other) {
//        if (other == null) {
//            return false;
//        } else if (this == other) {
//            return true;
//        } else if (other instanceof RandomEvent) {
//            RandomEvent that = (RandomEvent) other;
//            return this.name.equals(that.getName());
//        } else {
//            return false;
//        }
//    }

//    public String getName() {
//        return name;
//    }

    public boolean isBad() {
        return isBad;
    }
}
