package org.atomiteam.api.router;

import java.util.Locale;
import java.util.Map;

/**
 * Represents HTTP headers and provides utility methods to extract information, such as locale
 * and request type.
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

    /**
     * Retrieves the value of a specific HTTP header.
     *
     * @param headerName the name of the HTTP header to retrieve.
     * @return the value of the HTTP header, or null if the header is not present.
     */
    public String getHeader(String headerName) {
        return headers.get(headerName);
    }

    /**
     * Determines whether the current request is a page request or an AJAX request.
     *
     * An AJAX request typically includes the "X-Requested-With" header with the value "XMLHttpRequest".
     *
     * @return true if the request is an AJAX request; false otherwise.
     */
    public boolean isAjaxRequest() {
        String requestedWith = headers.get("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(requestedWith);
    }

    /**
     * Determines whether the current request is a standard page HTTP request.
     *
     * A page request is assumed if it is not an AJAX request.
     *
     * @return true if the request is a page request; false otherwise.
     */
    public boolean isPageRequest() {
        return !isAjaxRequest();
    }
}
