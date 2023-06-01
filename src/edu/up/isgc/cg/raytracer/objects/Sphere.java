/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.utils.Material;

import java.awt.*;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class Sphere extends Object3D{
    /**
     * Represents the radius of the sphere.
     */
    private double radius;

    /**
     * Returns the radius of the sphere.
     *
     * @return the radius of the sphere.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the sphere.
     *
     * @param radius the radius to set.
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * The main constructor of the sphere
     *
     * @param position the position of the sphere.
     * @param radius the radius of the sphere.
     * @param color the color of the sphere.
     * @param material the material of the sphere.
     */
    public Sphere(Vector3D position, double radius, Color color, Material material) {
        super(position, color, material);
        setRadius(radius);
    }

    /**
     *  Calculates the intersection with a sphere
     * @param ray A ray object to be cast
     * @return The intersection with the sphere if any
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        Vector3D L = Vector3D.subtract(ray.getOrigin(), getPosition());
        double tca = Vector3D.dotProduct(ray.getDirection(), L);
        double L2 = Math.pow(Vector3D.magnitude(L), 2);
        //Intersection
        double d2 = Math.pow(tca, 2) - L2 + Math.pow(getRadius(), 2);
        if(d2 >= 0){
            double d = Math.sqrt(d2);
            double t0 = -tca + d;
            double t1 = -tca - d;

            double distance = Math.min(t0, t1);
            Vector3D position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
            Vector3D normal = Vector3D.normalize(Vector3D.subtract(position, getPosition()));
            return new Intersection(position, distance, normal, this);
        }

        return null;
    }
}
