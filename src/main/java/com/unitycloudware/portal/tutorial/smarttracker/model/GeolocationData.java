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
    private double lattitude;
    private double longitude;
    private long timestamp;

    public double getLattitude() {
        return lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
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
                "lattitude=" + lattitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                '}';
    }

    public static GeolocationData create(final double lattitude, final double longitude, final long timestamp) {
        GeolocationData geolocationData = new GeolocationData();
        geolocationData.setLattitude(lattitude);
        geolocationData.setLongitude(longitude);
        geolocationData.setTimestamp(timestamp);
        return geolocationData;
    }
}