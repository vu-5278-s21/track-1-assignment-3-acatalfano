package edu.vanderbilt.cs.live3;

public class DefaultGeoDBConfiguration {

    /**
     * @return
     */
    public GeoDBFactory getDBFactory() {
        return new GeoDBFactory();
    }

    /**
     * @return
     */
    public GeoHashFactory getHashFactory() {
        return new GeoHashFactoryImpl();
    }

}
