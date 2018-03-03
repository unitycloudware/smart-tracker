/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.smarttracker.job;

import java.util.List;
import java.util.Map;

import org.nsys.util.JsonUtils;
import org.nsys.util.RandomRange;
import org.nsys.util.TimeUtils;
import org.nsys.daemon.scheduler.job.AbstractJob;

import com.unitycloudware.core.model.DataStream;
import com.unitycloudware.core.model.Device;
import com.unitycloudware.core.service.DataManager;
import com.unitycloudware.core.model.DataStreamItem;

import com.unitycloudware.portal.tutorial.smarttracker.model.GeolocationData;
import com.unitycloudware.portal.tutorial.smarttracker.util.SmartTrackerUtils;
import com.unitycloudware.portal.tutorial.smarttracker.util.TestDataUtils;

/**
 * Sensor Data Generator Job
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class SensorDataGeneratorJob extends AbstractJob {
    private DataManager dataManager;
    private TestDataUtils testDataUtils;

    public DataManager getDataManager() {
        return dataManager;
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public TestDataUtils getTestDataUtils() {
        return testDataUtils;
    }

    public void setTestDataUtils(TestDataUtils testDataUtils) {
        this.testDataUtils = testDataUtils;
    }

    public void execute(final Map<String, Object> jobDataMap) {
        getLog().info("Executing sensor data generator...");

        if (!jobDataMap.containsKey(ComponentName.DATA_MANAGER)) {
            getLog().error("Unable to find DataManager component in jobDataMap!");
            return;
        }

        if (!jobDataMap.containsKey(ComponentName.TEST_DATA_UTILS)) {
            getLog().error("Unable to find TestDataUtils component in jobDataMap!");
            return;
        }

        setDataManager((DataManager) jobDataMap.get(ComponentName.DATA_MANAGER));
        setTestDataUtils((TestDataUtils) jobDataMap.get(ComponentName.TEST_DATA_UTILS));

        generateData();
    }

    public void generateData() {
        Device device = getTestDataUtils().getDevice();

        if (device == null) {
            return;
        }

        DataStream dataStream = getTestDataUtils().getDataStream();

        if (dataStream == null) {
            return;
        }

        GeolocationData data = null;

        if (getDataManager().countStream(dataStream, device) == 0) { // No data in data stream for device
            data = getTestDataUtils().getDefaultLocation();

        } else {
            List<DataStreamItem> items = getDataManager().loadStream(dataStream, device, 0, 1);

            if (items == null || items.isEmpty()) {
                return;
            }

            GeolocationData lastData = SmartTrackerUtils.toGeolocationData(items.get(0));
            int direction = RandomRange.getRandomInt(0, 1);

            if (direction == 1) {
                data = GeolocationData.create(
                        lastData.getLatitude(),
                        lastData.getLongitude() + RandomRange.getRandomDouble(-0.005, 0.005),
                        TimeUtils.getNow().getTime());
            } else {
                data = GeolocationData.create(
                        lastData.getLatitude() + RandomRange.getRandomDouble(-0.005, 0.005),
                        lastData.getLongitude(),
                        TimeUtils.getNow().getTime());
            }
        }

        String payload = JsonUtils.toJson(data);

        getLog().debugFormat("Storing generated sensor data... Payload: %s", payload);
        getDataManager().storeStream(dataStream, device, payload);
    }
}