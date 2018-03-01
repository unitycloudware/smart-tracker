# Welcome to the Smart Tracker Tutorial!

## Quick links

* [UCW Platform][1]
* [UCW Platform Documentation][2]
* [GitHub][3]
* [Issues][4]

## Description

The Smart Tracker is an example how to implement a monitoring of moving objects for UCW Portal.

### Quick start

 * cd "project root"
 * ./build.sh
 * ./run-portal.sh
 * Browse to [http://localhost:9602](http://localhost:9602).
 * Use default user "admin" and password "admin" to login


#### Sensor Data Generator

The Smart Tracker uses data stream <b>ucw-smarttracker</b> to read data for geolocation coordinates
from GPS modules. Data can be generated when you enable generator in <i>pom.xml</i> file by changing 
to following

<code>\<jvmArgs\>-Ducw.smarttracker.generator.enabled=true\</jvmArgs\></code> 

[1]: https://unitycloudware.com
[2]: https://docs.unitycloudware.com
[3]: https://github.com/unitycloudware
[4]: https://jira.unitycloudware.com/browse/UCW