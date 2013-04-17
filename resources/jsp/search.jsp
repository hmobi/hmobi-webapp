<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
   <title>Find a Doctor | hMobi</title>
   <script src="http://maps.google.com/maps/api/js?sensor=false"
           type="text/javascript"></script>
</head>
<body>
   <div id="map" style="width: 400px; height: 300px;"></div>

   <script type="text/javascript">
   var map = new google.maps.Map(document.getElementById('map'), {
          mapTypeId: google.maps.MapTypeId.TERRAIN,
          zoom: 11
      });

   var geocoder = new google.maps.Geocoder();
   <%
        String[] addresses = (String[]) request.getAttribute("addresses");
        if(addresses != null)
        {
            for(int i=0;i<addresses.length;i++)
            {
                out.println("geocoder.geocode({'address':'" + addresses[i] + "'},plot);");
            }

        }
   %>

   function plot(results, status) {
         if(status == google.maps.GeocoderStatus.OK) {
            new google.maps.Marker({
               position: results[0].geometry.location,
               map: map,
               title: results[0].formatted_address
            });
            map.setCenter(results[0].geometry.location);
         }
         else {
            // Google couldn't geocode this request. Handle appropriately.
         }
   }
   </script>
</body>
</html>