/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.smarttracker.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.nsys.system.ComponentProvider;
import org.nsys.portal.controller.AbstractPortalController;

import com.unitycloudware.core.model.DataStream;
import com.unitycloudware.core.model.DataStreamItem;
import com.unitycloudware.core.model.Device;
import com.unitycloudware.core.service.DataManager;
import com.unitycloudware.portal.tutorial.smarttracker.model.GeolocationData;
import com.unitycloudware.portal.tutorial.smarttracker.util.TestDataUtils;
import com.unitycloudware.portal.tutorial.smarttracker.util.SmartTrackerUtils;

/**
 * Geolocation Map Controller
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
@Controller
@RequestMapping("/smart-tracker/geolocation")
public class GeolocationMapController extends AbstractPortalController {
    private DataManager dataManager;
    private TestDataUtils testDataUtils;

    public DataManager getDataManager() {
        if (dataManager == null) {
            dataManager = ComponentProvider.getInstance().getComponent(DataManager.class);
        }

        return dataManager;
    }

    public TestDataUtils getTestDataUtils() {
        if (testDataUtils == null) {
            testDataUtils = ComponentProvider.getInstance().getComponent(TestDataUtils.class);
        }

        return testDataUtils;
    }

    @RequestMapping(value = "/map-data", method = RequestMethod.GET)
    @ResponseBody
    public List<GeolocationData> getMapData(
            final HttpServletRequest request,
            final HttpServletResponse response) {

        List<GeolocationData> data = new ArrayList<GeolocationData>();

        Device device = getTestDataUtils().getDevice();

        if (device == null) {
            return data;
        }

        DataStream dataStream = getTestDataUtils().getDataStream();

        if (dataStream == null) {
            return data;
        }

        // Load 14 last records
        List<DataStreamItem> items = getDataManager().loadStream(dataStream, device, 0, 14);

        if (items == null || items.isEmpty()) {
            return data;
        }

        data.addAll(SmartTrackerUtils.getData(items));

        return data;
    }
}