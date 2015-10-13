package org.realdolmen.webbroker.i18n;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * Class which can be used to get internationalized {@link String}s to be displayed on the front-end.
 * This class attempts to load resources from the <code><b>messages</b></code> directory, with filenames starting
 * with <code><b>text</b></code> and which have the extension <code><b>properties</b></code>.
 *
 * @author Youri Flement
 */
public class Text extends ResourceBundle {

    protected static final String BUNDLE_NAME = "messages.text";
    protected static final String BUNDLE_EXTENSION = "properties";
    protected static final ResourceBundle.Control UTF8_CONTROL = new UTF8Control();

    public Text() {
        setParent(ResourceBundle.getBundle(BUNDLE_NAME, FacesContext.getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL));
    }

    @Override
    protected Object handleGetObject(String key) {
        return parent.getObject(key);
    }

    @Override
    public Enumeration getKeys() {
        return parent.getKeys();
    }

    protected static class UTF8Control extends Control {
        public ResourceBundle newBundle
                (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException {

            // The below code is copied from default Control#newBundle() implementation.
            // Only the PropertyResourceBundle line is changed to read the file as UTF-8.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, BUNDLE_EXTENSION);
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
                if (stream == null) {
                    stream = getClass().getClassLoader().getResourceAsStream(resourceName);
                }
            }
            if (stream != null) {
                try {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }

}
