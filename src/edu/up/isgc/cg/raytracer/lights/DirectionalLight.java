/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.Color;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class DirectionalLight extends Light {
    /**
     *  A vector representing the direction of the light.
     */
    private Vector3D direction;

    /**
     * The main constructor for a Light, with the addition of direction.
     * @param directionValue The direction the light points to
     * @param color The color of the light
     * @param intensity The light's intensity
     */
    public DirectionalLight(final Vector3D directionValue, final Color color, final double intensity) {
        super(Vector3D.getZero(), color, intensity);
        setDirection(Vector3D.normalize(directionValue));
    }

    /**
     * A method for retrieving the light's direction.
     * @return A vector representing the direction
     */
    public Vector3D getDirection() {
        return direction;
    }

    /**
     *  A setter for the light's direction.
     * @param directionValue A vector representing the direction
     */
    public void setDirection(final Vector3D directionValue) {
        this.direction = directionValue;
    }

    /**
     * A method for retrieving the angle of incidence of the light.
     * @param intersection An intersection with an object
     * @return A double representing the angle of incidence
     */
    @Override
    public double getNDotL(final Intersection intersection) {
        return Math.max(Vector3D.dotProduct(intersection.getNormal(), Vector3D.scalarMultiplication(getDirection(), -1.0)), 0.0);
    }

    /**
     *  A method for getting the distance from a point.
     * @param point A point in space
     * @return A bias constant for directional lights
     */
    @Override
    public double getDistanceFrom(final Vector3D point) {
        final int desiredDistance = 20;
        return desiredDistance;
    }

    /**
     * A method for getting the direction from a point to a light.
     * @param point A point in space
     * @return The inverse of the light's direction
     */
    @Override
    public Vector3D getDirectionFrom(final Vector3D point) {
        final double inverseFactor = -1.0;
        return Vector3D.scalarMultiplication(this.direction, inverseFactor);
    }
}
