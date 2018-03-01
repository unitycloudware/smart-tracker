/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.smarttracker.util;

import java.util.ArrayList;
import java.util.List;

import org.nsys.util.JsonUtils;

import com.unitycloudware.core.model.DataMessage;
import com.unitycloudware.core.model.DataStreamItem;
import com.unitycloudware.core.model.DataStreamType;

import com.unitycloudware.portal.tutorial.smarttracker.model.GeolocationData;

/**
 * Smart Tracker Utils
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class SmartTrackerUtils {

    public static List<GeolocationData> getData(final List<DataStreamItem> items) {
        if (items == null || items.isEmpty()) {
            return null;
        }

        List<GeolocationData> data = new ArrayList<GeolocationData>();

        for (DataStreamItem item : items) {
            GeolocationData geolocationData = toGeolocationData(item);

            if (geolocationData == null) {
                continue;
            }

            data.add(geolocationData);
        }

        return data;
    }

    public static GeolocationData toGeolocationData(final DataStreamItem item) {
        if (item == null ||
            item.getType() != DataStreamType.DATA_MESSAGE) { // Process only data for data stream with type of DATA_MESSAGE
            return null;
        }

        DataMessage dataMessage = (DataMessage) item.getData();

        // Transform JSON payload to GeolocationData object
        GeolocationData geolocationData = JsonUtils.fromJson(dataMessage.getData(), GeolocationData.class);

        if (geolocationData.getTimestamp() == 0) {
            geolocationData.setTimestamp(dataMessage.getTimestamp());
        }

        return geolocationData;
    }
}