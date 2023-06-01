/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer;


/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class Ray {
    /**
     * The origin point of the ray.
     */
    private Vector3D origin;
    /**
     * The direction of the ray, represented as a normalized vector.
     */
    private Vector3D direction;

    /**
     * The constructor for the Ray class.
     * @param originValue The origin point of the ray.
     * @param originDirection The direction vector of the ray.
     */
    public Ray(final Vector3D originValue, final Vector3D originDirection) {
        setOrigin(originValue);
        setDirection(originDirection);
    }

    /**
     *  Getter method for the origin of the ray.
     * @return The origin point of the ray.
     */
    public Vector3D getOrigin() {
        return origin;
    }

    /**
     *  Setter method for the origin of the ray.
     * @param originValue The new origin point of the ray.
     */
    public void setOrigin(final Vector3D originValue) {
        this.origin = originValue;
    }

    /**
     * Getter method for the direction of the ray.
     * @return The normalized direction vector of the ray.
     */
    public Vector3D getDirection() {
        return Vector3D.normalize(direction);
    }

    /**
     * Setter method for the direction of the ray.
     * @param originDirection The new direction vector of the ray.
     */
    public void setDirection(final Vector3D originDirection) {
        this.direction = originDirection;
    }
}
