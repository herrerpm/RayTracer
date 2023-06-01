package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.Color;

/**
 * @author Miguel Herrera
 * @author Jafet Rodr�guez
 */
public class PointLight extends Light {
    /**
     *  The main constructor for the Light, while adding a position.
     * @param position A vector representing the position of the light
     * @param color The color of the light in java AWT
     * @param intensity A double of the light intensity
     */
    public PointLight(final Vector3D position, final Color color, final double intensity) {
        super(position, color, intensity);
    }

    /**
     * A method for getting the angle of incidence with respect to
     * the normal.
     * @param intersection An intersection with the traced object
     * @return A double of the angle of light incidence
     */
    @Override
    public double getNDotL(final Intersection intersection) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.normalize(Vector3D.subtract(this.getPosition(), intersection.getPosition()))), 0.0);
    }
    /**
     * A method for getting the distance from a point to the light.
     * @param point A Vector3D representing the point
     * @return A double of the distance between the light and point
     */
    @Override
    public double getDistanceFrom(final Vector3D point) {
        return Vector3D.calculateDistance(this.getPosition(), point);
    }

    /**
     * A method for getting the direction of an object to the light.
     * @param point A Vector3D representing a point in space
     * @return A normalized vector representing the direction
     */
    @Override
    public Vector3D getDirectionFrom(final Vector3D point) {
        return Vector3D.normalize(
                Vector3D.subtract(this.getPosition(), point));
    }
}
