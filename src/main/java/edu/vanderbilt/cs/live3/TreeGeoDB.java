package edu.vanderbilt.cs.live3;

import java.util.*;
import java.util.stream.Collectors;

public class TreeGeoDB implements GeoDB {
    private class GeohashEntry {
        private final double latitude;
        private final double longitude;
        private final GeoHash geohash;

        public GeohashEntry(
            double lat,
            double lng
        ) {
            latitude = lat;
            longitude = lng;
            geohash = geoHashFactory.with(lat, lng, resolution);
        }

        public double[] coordinateArray() {
            return new double[] { latitude, longitude };
        }

        @Override
        public int hashCode() {
            return Objects.hash(latitude, longitude, geohash);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof GeohashEntry)) {
                return false;
            }
            GeohashEntry geohashEntry = (GeohashEntry)o;
            return geohash.equals(geohashEntry.geohash)
                && Double.doubleToLongBits(latitude) ==
                    Double.doubleToLongBits(geohashEntry.latitude)
                && Double.doubleToLongBits(longitude) ==
                    Double.doubleToLongBits(geohashEntry.longitude);
        }
    }

    private final int resolution;
    private final GeoHashFactory geoHashFactory;
    private final List<Set<GeohashEntry>> geoTree;

    public TreeGeoDB(
        GeoHashFactory factory,
        int bitsOfPrecision
    ) {
        resolution = bitsOfPrecision;
        geoHashFactory = factory;
        final int capacity = (int)Math.pow(2, resolution);
        geoTree = new ArrayList<>(capacity);
        for(int i = 0; i < capacity; i++) {
            geoTree.add(new HashSet<>());
        }
    }

    @Override
    public void insert(
        double lat,
        double lon
    ) {
        int index = index(lat, lon);
        geoTree.get(index).add(new GeohashEntry(lat, lon));
    }

    @Override
    public boolean delete(
        double lat,
        double lon
    ) {
        int index = index(lat, lon);
        Set<GeohashEntry> targetSet = geoTree.get(index);
        GeohashEntry targetGeohash = new GeohashEntry(lat, lon);
        return targetSet.remove(targetGeohash);
    }

    @Override
    public List<double[]> deleteAll(
        double lat,
        double lon,
        int bitsOfPrecision
    ) {
        int rangeStartIndex = rangeStartIndex(lat, lon, bitsOfPrecision);
        int rangeEndIndex = rangeEndIndex(rangeStartIndex, bitsOfPrecision);
        List<double[]> deletedEntries = geoTree
            .subList(rangeStartIndex, rangeEndIndex)
            .stream()
            .filter(Objects::nonNull)
            .flatMap(Set::stream)
            .map(GeohashEntry::coordinateArray)
            .collect(Collectors.toList());

        for(int i = rangeStartIndex; i < rangeEndIndex; i++) {
            geoTree.get(i).clear();
        }
        return deletedEntries;
    }

    @Override
    public boolean contains(
        double lat,
        double lon,
        int bitsOfPrecision
    ) {
        int rangeStartIndex = rangeStartIndex(lat, lon, bitsOfPrecision);
        int rangeEndIndex = rangeEndIndex(rangeStartIndex, bitsOfPrecision);
        return geoTree
            .subList(rangeStartIndex, rangeEndIndex)
            .stream()
            .anyMatch(set -> !set.isEmpty());
    }

    @Override
    public List<double[]> nearby(
        double lat,
        double lon,
        int bitsOfPrecision
    ) {
        int rangeStartIndex = rangeStartIndex(lat, lon, bitsOfPrecision);
        int rangeEndIndex = rangeEndIndex(rangeStartIndex, bitsOfPrecision);
        return geoTree
            .subList(rangeStartIndex, rangeEndIndex)
            .stream()
            .filter(Objects::nonNull)
            .flatMap(Set::stream)
            .map(GeohashEntry::coordinateArray)
            .collect(Collectors.toList());
    }

    private int index(
        double latitude,
        double longitude
    ) {
        return rangeStartIndex(latitude, longitude, resolution);
    }

    private int rangeStartIndex(
        double latitude,
        double longitude,
        int precision
    ) {
        String geohashString =
            zeroPaddedGeohashString(latitude, longitude, precision);
        return Integer.parseInt(geohashString, 2);
    }

    private int rangeEndIndex(
        int startIndex,
        int precision
    ) {
        return (int)Math.pow(2, (resolution - precision)) + startIndex;
    }

    private String zeroPaddedGeohashString(
        double latitude,
        double longitude,
        int precision
    ) {
        String unpaddedGeohash =
            geoHashFactory.with(latitude, longitude, precision).toString();
        StringBuilder paddedGeohashBuilder = new StringBuilder(unpaddedGeohash);
        char[] padding = new char[resolution - precision];
        Arrays.fill(padding, '0');
        paddedGeohashBuilder.append(padding);
        return paddedGeohashBuilder.toString();
    }
}
