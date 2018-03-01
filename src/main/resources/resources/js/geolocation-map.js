Nsys.$(document).ready(function () {

    // Here goes your JS code...

});

function initLocationMap() {
    /*
     *  Adding a Google Map with a Marker to Your Website
     *  https://developers.google.com/maps/documentation/javascript/adding-a-google-map
     *
     *  Disabling the default UI
     *  https://developers.google.com/maps/documentation/javascript/examples/control-disableUI
     *
     *  How do I securely use Google API Keys
     *  https://stackoverflow.com/questions/39625587/how-do-i-securely-use-google-api-keys
     *
     *  How to give referrer url for localhost in google map api?
     *  https://stackoverflow.com/questions/38006171/how-to-give-referrer-url-for-localhost-in-google-map-api
     */
    var defaultLocation = {lat: 49.2789333, lng: -123.124058};

    var map = new google.maps.Map(document.getElementById('location-map'), {
        zoom: 13,
        center: defaultLocation,
        mapTypeId: google.maps.MapTypeId.ROADMAP/*,
        disableDefaultUI: true*/
    });

    var markers = [];

    Nsys.$.ajax({
        type: "GET",
        contentType: "application/json; charset=utf-8",
        url: Nsys.contextPath() + "/smart-tracker/geolocation/map-data",
        success: function(response) {
            /*
             * How to connect list of places with Google Map Points
             * https://stackoverflow.com/questions/35182728/how-to-connect-list-of-places-with-google-map-points
             */
            var infowindow = new google.maps.InfoWindow;

            var marker, i;

            if (response.length == 0) {
                marker = new google.maps.Marker({
                    position: defaultLocation,
                    map: map
                });

                markers.push(marker);

            } else {
                for (i = 0; i < response.length; i++) {
                    var locationData = {lat: response[i].lattitude, lng: response[i].longitude};

                    marker = new google.maps.Marker({
                        position: locationData,
                        map: map,
                        label: String(i + 1)
                    });

                    markers.push(marker);

                    google.maps.event.addListener(marker, 'click', (function(marker, i) {
                        return function() {
                            var locationInfo = "Location # " + (i + 1) +
                                               ", Lat: " + response[i].lattitude +
                                               ", Lng: " + response[i].longitude;

                            infowindow.setContent(locationInfo);
                            infowindow.open(map, marker);
                        }
                    })(marker, i));
                }
            }
        },
        error: function(xhr, error) {
            console.log(
                'ReadyState: ' + xhr.readyState +
                '\nStatus: ' + xhr.status +
                '\nResponseText: ' + xhr.responseText +
                '\nError: ' + error);

            var response = Nsys.$.parseJSON(xhr.responseText);

            alert('An error occurred during validating form! Error: ' + xhr.responseText);
        }
    });
}