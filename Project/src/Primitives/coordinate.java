package Primitives;

import static Primitives.util.*;

/**
 * Class Coordinate is the basic class representing a coordinate for Cartesian
 * coordinate system. The class is based on Util controlling the accuracy.
 * 
 * @author Dan Zilberstein
 */
public final class coordinate {
    /**
     * Coordinate value, intentionally "package-friendly" due to performance
     * constraints
     */
    final double _coord;

    /**
     * Coordinate constructor receiving a coordinate value
     * 
     * @param coord coordinate value
     */
    public coordinate(double coord) {
        // if it too close to zero make it zero
        _coord = alignZero(coord);
    }

    /**
     * Copy constructor for coordinate
     * 
     * @param other
     */
    public coordinate(coordinate other) {
        _coord = other._coord;
    }

    /**
     * Coordinate value getter
     * 
     * @return coordinate value
     */
    public double get() {
        return _coord;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof coordinate)) return false;
        return isZero(_coord - ((coordinate)obj)._coord);
    }

    @Override
    public String toString() {
        return "" + _coord;
    }
}
