package localization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by Andreas on 04.06.2017.
 */
public class Resources implements LocalizedResourceSubject{
    private static Resources singletonResource = new Resources();

    public static Resources getInstance() {
        return singletonResource;
    }


    private ArrayList<LocalizedResourceObserver> observers;
    private Locale currentLocale;
    private ResourceBundle resBundle;
    private Locale[] supportedLocales = {
            Locale.ENGLISH,
            Locale.GERMAN
    };

    private Resources(){
        observers = new ArrayList<LocalizedResourceObserver>();
        currentLocale = Locale.GERMAN;
        resBundle = ResourceBundle.getBundle("localizedText",currentLocale);
    }

    /**
     * Gets a localized string for a specified key
     * @param key the key of the string
     * @returnthe string for the key
     */
    public String label(String key)
    {
        return resBundle.getString(key);
    }

    /**
     * Changes the locale
     * @param desiredLocale The locale to change to
     */
    public void changeLocale(Locale desiredLocale)
    {
        if(Arrays.asList(supportedLocales).contains(desiredLocale))
        {
            currentLocale = desiredLocale;
            resBundle = ResourceBundle.getBundle("localizedText",currentLocale);
            //
            notifyObservers();
        }
    }

    /**
     * returns a list of locales supported by our app
     * @return a list of locales supported by our app
     */
    public Locale[] getSupportedLocales()
    {
        return supportedLocales;
    }

    /**
     *
     * @return the current active locale
     */
    public Locale getCurrentLocale(){ return currentLocale; }

    /**
     * Observer for Registration
     * @param observer
     */


    public void registerObserver(LocalizedResourceObserver observer) {
        if(!observers.contains(observer))
        {
            observers.add(observer);
        }
    }

    /**
     * Observer for Unrgegistration
     * @param observer
     */


    public void unregisterObserver(LocalizedResourceObserver observer) {
        observers.remove(observer);
    }


    /**
     * observ the Observer
     */


    public void notifyObservers() {
        for(LocalizedResourceObserver observer : observers)
        {
            observer.updateFromResources();
        }
    }
}
