package application;

import java.io.Serializable;

public class RandomEvent implements Serializable {
	private static final long serialVersionUID = -4378718716859395316L;
	private boolean isBad;

    public RandomEvent(boolean isBad) {
        this.isBad = isBad;
    }

    public boolean isBad() {
        return isBad;
    }
}
