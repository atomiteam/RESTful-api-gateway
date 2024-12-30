package org.atomiteam.api.router;

import java.util.Locale;
import java.util.Map;

/**
 * Represents HTTP headers and provides utility methods to extract information, such as locale.
 */
public class Headers {
    final Map<String, String> headers;

    /**
     * Constructs a Headers object.
     *
     * @param headers a map of header key-value pairs. If null, an empty map is used.
     */
    public Headers(Map<String, String> headers) {
        this.headers = headers == null ? Map.of() : headers;
    }

    /**
     * Extracts the locale from the "Accept-Language" HTTP header.
     *
     * @return the extracted Locale, or the default Locale if the header is not available.
     */
    public Locale getLocale() {
        String acceptLanguageHeader = headers.get("Accept-Language");

        if (acceptLanguageHeader != null && !acceptLanguageHeader.isEmpty()) {
            String[] languages = acceptLanguageHeader.split(",");
            if (languages.length > 0) {
                String languageTag = languages[0].split(";")[0].trim();
                return Locale.forLanguageTag(languageTag);
            }
        }

        return Locale.getDefault();
    }
}
