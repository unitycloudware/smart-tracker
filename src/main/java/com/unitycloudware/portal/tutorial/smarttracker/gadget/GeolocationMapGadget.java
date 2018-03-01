/* Copyright 2018 Unity{Cloud}Ware - UCW Industries Ltd. All rights reserved.
 */

package com.unitycloudware.portal.tutorial.smarttracker.gadget;

import java.util.HashMap;
import java.util.Map;

import org.nsys.portal.gadget.AbstractGadget;

/**
 * Geolocation Map Gadget
 *
 * @author Tomas Hrdlicka <tomas@hrdlicka.co.uk>
 * @see <a href="http://unitycloudware.com">Unity{Cloud}Ware</a>
 */
public class GeolocationMapGadget extends AbstractGadget {

    public static final String TEMPLATE = "/templates/gadget/geolocation-map.vm";

    public GeolocationMapGadget() {
        setTemplate(TEMPLATE);
    }

    @Override
    protected Map<String, Object> createVelocityParams(final Map<String, Object> context) {
        Map<String, Object> velocityParams = new HashMap<String, Object>();
        return velocityParams;
    }
}