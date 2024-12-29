package org.atomiteam.api.router;

import java.util.Locale;
import java.util.Map;

public class Headers {
    final Map<String, String> headers;

    public Headers(Map<String, String> headers) {
        this.headers = headers == null ? Map.of() : headers;
    }

    public Locale getLocale() {
        String acceptLanguageHeader = headers.get("Accept-Language");

        if (acceptLanguageHeader != null && !acceptLanguageHeader.isEmpty()) {
            // Split the header value to extract the language tag (e.g., en-US, fr-FR)
            String[] languages = acceptLanguageHeader.split(",");
            if (languages.length > 0) {
                // Use the first language preference
                String languageTag = languages[0].split(";")[0].trim();
                return Locale.forLanguageTag(languageTag);
            }
        }

        // Return default locale if header is not available
        return Locale.getDefault();
    }
}