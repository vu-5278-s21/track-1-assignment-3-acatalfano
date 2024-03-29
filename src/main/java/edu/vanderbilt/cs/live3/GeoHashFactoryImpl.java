package edu.vanderbilt.cs.live3;

public class GeoHashFactoryImpl implements GeoHashFactory {

    @Override
    public GeoHash with(
        double lat,
        double lon,
        int bitsOfPrecision
    ) {
        return new GeoHashImpl(lat, lon, bitsOfPrecision);
    }

}
