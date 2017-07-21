package subscription;

/**
 * Created by Andreas on 05.06.2017.
 */
public enum SubscriptionState { ACTIVE, INACTIVE, SUSPENDED;

    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
