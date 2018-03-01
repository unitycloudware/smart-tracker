/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.smarttracker;

import java.util.Properties;

import org.nsys.util.ConfigurationManager;

/**
 * Smart Tracker Config
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class SmartTrackerConfig {

    public static final String CONFIG_NAME = "smart-tracker.cfg";
    public static final String CONFIG_NAME_INTERNAL = "smart-tracker.cfg.internal";
    public static final String VERSION = "ucw.smarttracker.version";
    public static final String PLUGIN_KEY = "ucw.smarttracker.pluginKey";
    public static final String GENERATOR_ENABLED = "ucw.smarttracker.generator.enabled";

    public static void loadConfig() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        Properties props = config.loadConfig(String.format("/%s", CONFIG_NAME), SmartTrackerConfig.class);

        if (props != null) {
            config.merge(props);
        }

        props = config.loadConfig(String.format("/%s", CONFIG_NAME_INTERNAL), SmartTrackerConfig.class);

        if (props != null) {
            config.merge(props, true);
        }
    }

    public static String getVersion() {
        String version = ConfigurationManager.getInstance().getProperty(VERSION);
        return ConfigurationManager.getVersion(version);
    }

    public static String getBuildNumber() {
        String version = ConfigurationManager.getInstance().getProperty(VERSION);
        return ConfigurationManager.getBuildNumber(version);
    }

    public static String getPluginKey() {
        return ConfigurationManager.getInstance().getProperty(PLUGIN_KEY);
    }

    public static boolean isGeneratorEnabled() {
        String generatorEnabled = System.getProperty(GENERATOR_ENABLED, "false");
        return Boolean.valueOf(generatorEnabled);
    }
}