package org.traccar.geocoder;

import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;

public class NeshanGeocoder extends JsonGeocoder{

    private static String formatUrl(String url) {
        if (url == null) {
            url = "https://api.neshan.org/v5";
        }
        url += "/reverse?lat=%f&lng=%f";

        return url;
    }
    public NeshanGeocoder(Client client, String url,String key, int cacheSize, AddressFormat addressFormat) {
        super(client, formatUrl(url), cacheSize, addressFormat);
        addNewHeader("Api-Key",key);
    }

    @Override
    public Address parseAddress(JsonObject json) {
        System.out.println(">>>>>>>>>>>>>>>>>"+json.toString()+"\n");
        Address address =new Address();
        if(json.getString("status").equalsIgnoreCase("ok")){
            address.setFormattedAddress(json.getString("formatted_address"));
            address.setStreet(json.getString("route_type"));

            address.setDistrict(json.getString("district"));
            address.setState(json.getString("state"));

            address.setHouse(json.getString("neighbourhood"));
            address.setCountry(json.getString("county"));

            address.setPostcode(json.getString("place"));
            address.setSuburb(json.getString("village"));
        }
        return address;
    }
}
