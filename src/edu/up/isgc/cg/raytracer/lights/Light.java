/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.lights;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.objects.Object3D;
import java.awt.Color;

/**
 * @author Miguel Herrera
 * @author Jafet Rodr�guez
 */

public abstract class Light extends Object3D {
    /**
     * A double representing the light's intensity.
     */
    private double intensity;

    /**
     * A constructor for the light.
     * @param position A vector with the light's position
     * @param color The light's color from java AWT
     * @param intensityValue A double representing the intensity
     */
    public Light(final Vector3D position, final Color color, final double intensityValue) {
        // A null material to satisfy the Object3D constructor.
        super(position, color, null);
        setIntensity(intensityValue);
    }
    /**
     * A method for retrieving the intensity.
     * @return A double of the light's intensity
     */
    public double getIntensity() {
        return intensity;
    }
    /**
     *  A setter for the intensity value.
     * @param intensityValue A double representing the intensity
     */
    public void setIntensity(final double intensityValue) {
        this.intensity = intensityValue;
    }
    /**
     * An abstract method for implementing the nDotL.
     * @param intersection An intersection of the light's value
     * @return The angle of incidence calculation
     */
    public abstract double getNDotL(Intersection intersection);

    /**
     *  An abstrac method to implement the distance from a point.
     * @param point A vector3D representing the point
     * @return A double representing the distance
     */
    public abstract double getDistanceFrom(Vector3D point);

    /**
     *  An abstract method to implement the direction from a point
     *  to the light.
     * @param point A vector3D representing the point
     * @return A vector3D representing the direction to the light
     */
    public abstract Vector3D getDirectionFrom(Vector3D point);

    /**
     * A null intersection parameter to comply with Object3D.
     * @param ray A ray object
     * @return A null intersection
     */
    public Intersection getIntersection(final Ray ray) {
        return new Intersection(Vector3D.getZero(), -1, Vector3D.getZero(), null);
    }
}
