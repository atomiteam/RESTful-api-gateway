package org.atomiteam.api.router;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Headers class.
 */
public class HeadersTest {

    @Test
    public void testGetLocale_withAcceptLanguageHeader() {
        Map<String, String> headersMap = Map.of("Accept-Language", "en-US,en;q=0.9");
        Headers headers = new Headers(headersMap);

        Locale locale = headers.getLocale();

        assertEquals(Locale.US, locale);
    }

    @Test
    public void testGetLocale_withoutAcceptLanguageHeader() {
        Headers headers = new Headers(Map.of());

        Locale locale = headers.getLocale();

        assertEquals(Locale.getDefault(), locale);
    }

    @Test
    public void testGetLocale_withEmptyAcceptLanguageHeader() {
        Map<String, String> headersMap = Map.of("Accept-Language", "");
        Headers headers = new Headers(headersMap);

        Locale locale = headers.getLocale();

        assertEquals(Locale.getDefault(), locale);
    }

    @Test
    public void testGetHeader_existingHeader() {
        Map<String, String> headersMap = Map.of("Content-Type", "application/json");
        Headers headers = new Headers(headersMap);

        String headerValue = headers.getHeader("Content-Type");

        assertEquals("application/json", headerValue);
    }

    @Test
    public void testGetHeader_nonExistingHeader() {
        Headers headers = new Headers(Map.of());

        String headerValue = headers.getHeader("Authorization");

        assertNull(headerValue);
    }

    @Test
    public void testIsAjaxRequest_withAjaxHeader() {
        Map<String, String> headersMap = Map.of("X-Requested-With", "XMLHttpRequest");
        Headers headers = new Headers(headersMap);

        assertTrue(headers.isAjaxRequest());
    }

    @Test
    public void testIsAjaxRequest_withoutAjaxHeader() {
        Headers headers = new Headers(Map.of());

        assertFalse(headers.isAjaxRequest());
    }

    @Test
    public void testIsAjaxRequest_withNonAjaxHeaderValue() {
        Map<String, String> headersMap = Map.of("X-Requested-With", "OtherValue");
        Headers headers = new Headers(headersMap);

        assertFalse(headers.isAjaxRequest());
    }

    @Test
    public void testIsPageRequest_withAjaxHeader() {
        Map<String, String> headersMap = Map.of("X-Requested-With", "XMLHttpRequest");
        Headers headers = new Headers(headersMap);

        assertFalse(headers.isPageRequest());
    }

    @Test
    public void testIsPageRequest_withoutAjaxHeader() {
        Headers headers = new Headers(Map.of());

        assertTrue(headers.isPageRequest());
    }

    @Test
    public void testIsPageRequest_withNonAjaxHeaderValue() {
        Map<String, String> headersMap = Map.of("X-Requested-With", "OtherValue");
        Headers headers = new Headers(headersMap);

        assertTrue(headers.isPageRequest());
    }
}
