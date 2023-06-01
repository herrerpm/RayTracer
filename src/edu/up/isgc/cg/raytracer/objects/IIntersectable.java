/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;

/**
 * @author Miguel Herrera
 * @author Jafet Rodr�guez
 */
public interface IIntersectable {
    /**
     *  A method for calculating object intersections.
     * @param ray A ray object to be cast
     * @return An intersection if any, with the ray
     */
    Intersection getIntersection(Ray ray);
}
