/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.objects.Object3D;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class Intersection {
    /**
     * The distance from the caster.
     */
    private double distance;
    /**
     * The normal of the object.
     */
    private Vector3D normal;
    /**
     * The position of the object.
     */
    private Vector3D position;
    /**
     * The object.
     */
    private Object3D object;

    /**
     * A getter for the distance.
     *
     * @return The distance from the start of the ray to the intersection point
     */
    public double getDistance() {
        return distance;
    }

    /**
     * A setter for the distance.
     *
     * @param distanceValue The distance from the start of the ray to the intersection point
     */
    public void setDistance(final double distanceValue) {
        this.distance = distanceValue;
    }

    /**
     * A getter for the normal vector.
     *
     * @return The normal vector at the intersection point
     */
    public Vector3D getNormal() {
        return normal;
    }

    /**
     * A setter for the normal vector.
     *
     * @param normalValue The normal vector at the intersection point
     */
    public void setNormal(final Vector3D normalValue) {
        this.normal = normalValue;
    }

    /**
     * A getter for the position vector.
     *
     * @return The position of the intersection point in 3D space
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * A setter for the position vector.
     *
     * @param positionValue The position of the intersection point in 3D space
     */
    public void setPosition(final Vector3D positionValue) {
        this.position = positionValue;
    }

    /**
     * A getter for the intersected object.
     *
     * @return The object intersected by the ray
     */
    public Object3D getObject() {
        return object;
    }

    /**
     * A setter for the intersected object.
     *
     * @param newObject The object intersected by the ray
     */
    public void setObject(final Object3D newObject) {
        this.object = newObject;
    }

    /**
     * The main constructor of the intersection class.
     *
     * @param positionValue The position of the intersection point in 3D space
     * @param distanceValue The distance from the start of the ray to the intersection point
     * @param normalValue   The normal vector at the intersection point
     * @param newObject   The object intersected by the ray
     */
    public Intersection(
            final Vector3D positionValue,
            final double distanceValue,
            final Vector3D normalValue,
            final Object3D newObject) {
        setPosition(positionValue);
        setDistance(distanceValue);
        setNormal(normalValue);
        setObject(newObject);
    }
}