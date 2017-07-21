package localization;

/**
 * Created by Andreas on 04.06.2017.
 */
public interface LocalizedResourceSubject {

    /**
     * observ Registration
     * @param observer
     */

    void registerObserver(LocalizedResourceObserver observer);

    /**
     * observ Unregistration
     * @param observer
     */
    void unregisterObserver(LocalizedResourceObserver observer);
    void notifyObservers();

}
