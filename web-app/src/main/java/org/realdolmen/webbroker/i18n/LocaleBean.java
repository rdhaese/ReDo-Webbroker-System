package org.realdolmen.webbroker.i18n;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Locale;

/**
 * Bean which manages the {@link Locale} and can be used to change the displayed language.
 *
 * @author Youri Flement
 */
@ManagedBean
@SessionScoped
public class LocaleBean {

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        this.locale = new Locale(language);
    }

}