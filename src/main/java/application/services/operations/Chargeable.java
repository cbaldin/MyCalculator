package application.services.operations;

import application.entities.Operation;
import application.entities.User;

public interface Chargeable {

    public void charge(User user, Operation operation);

}
