/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.smarttracker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Geolocation Data
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocationData {
    private double latitude;
    private double longitude;
    private long timestamp;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "GeolocationData{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }

    public static GeolocationData create(final double latitude, final double longitude, final long timestamp) {
        GeolocationData geolocationData = new GeolocationData();
        geolocationData.setLatitude(latitude);
        geolocationData.setLongitude(longitude);
        geolocationData.setTimestamp(timestamp);
        return geolocationData;
    }
}