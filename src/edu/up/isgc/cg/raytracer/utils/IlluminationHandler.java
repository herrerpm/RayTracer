package edu.up.isgc.cg.raytracer.utils;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.lights.Light;
import edu.up.isgc.cg.raytracer.objects.Camera;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 * @see <a href="https://graphics.stanford.edu/courses/cs148-10-summer/docs/2006--degreve--reflection_refraction.pdf">...</a>
 *
 * Based on the paper of Bram de Greve
 */
public final class IlluminationHandler {

    private IlluminationHandler() {
    }

    /**
     * This method calculates the light fallof based on a constant,
     * linear and quadratic terms.
     * @param constant The constant term of the equation, it doesn't
     *                 depend on the distance
     * @param linear The linear term is multiplies the distance by a
     *               constant
     * @param quadratic The quadratic term is the quadratic distance
     *                  multiplied by a constant
     * @param intersection The intersection with an object
     * @param light The light hitting the object
     * @return The attenuation for the light at that point
     */
    public static double lightFallof(
            final double constant,
            final double linear,
            final double quadratic,
            final Intersection intersection,
           final Light light) {
        double distanceToLight = light.getDistanceFrom(intersection.getPosition());
        return 1.0 / (constant + (linear * distanceToLight) + (quadratic * distanceToLight * distanceToLight));
    }

    /**
     * An implementation for the specular component of
     * the Blinn-Phong model.
     * @param direction The direction to the light
     * @param camera The main camera of the scene
     * @param closestIntersection The intersection with the object
     * @param light The light calculated
     * @param objectMaterial The material of the object
     * @param constantSpecular The specular constant of the object's
     *                         color
     * @param lightAttenuation The attenuation of the distance to the
     *                         light
     * @return A vector3D representing the color of the intersection
     */
    public static Vector3D blinnPhongSpecular(
            final Vector3D direction,
            final Camera camera,
            final Intersection closestIntersection,
            final Light light,
            final Material objectMaterial,
            final Vector3D constantSpecular,
            final Double lightAttenuation
    ) {
        Vector3D viewDirection = Vector3D.normalize(Vector3D.subtract(camera.getPosition(), closestIntersection.getPosition()));
        Vector3D halfVect = Vector3D.normalize(Vector3D.add(viewDirection, direction));
        double nDotH = Math.max(Vector3D.dotProduct(closestIntersection.getNormal(), halfVect), 0);
        double specularFactor = Math.pow(nDotH, objectMaterial.getShiniess());
        return Vector3D.scalarMultiplication(Vector3D.scalarMultiplication(
                constantSpecular, specularFactor
        ), light.getIntensity() * lightAttenuation);
    }

    /**
     *  An implementation for the diffuse component in
     *  the Blinn-Phong model.
     * @param constantDiffuse The diffuse constant by the
     *                        object's color
     * @param light The light being calculated
     * @param intersection The intersection with an object
     * @param lightAttenuation The attenuation from the distance
     * @return A vector3D representing the color at the intersection
     */
    public static Vector3D blinnPhongDiffuse(
            final Vector3D constantDiffuse,
            final Light light,
            final Intersection intersection,
            final Double lightAttenuation
    ) {
        return Vector3D.scalarMultiplication(Vector3D.scalarMultiplication(
                constantDiffuse, light.getNDotL(intersection)
        ), light.getIntensity() * lightAttenuation);
    }

    /**
     *  A method for calculating the reflection's direction of a ray.
     * @param incomingDirection The incoming ray's direction at the surface
     * @param normalDirection The normal's direction of the surface
     * @return A vector3D with the reflected direction
     */
    public static Vector3D reflection(final Vector3D incomingDirection, final Vector3D normalDirection) {
        double nDotI = Vector3D.dotProduct(incomingDirection, normalDirection);
        return Vector3D.subtract(Vector3D.normalize(incomingDirection), Vector3D.scalarMultiplication(normalDirection, 2 * nDotI));
    }

    /**
     * A method for calculating simple refraction, it takes into account the
     * total internal reflection and in said case returns a reflection
     * direction.
     * @param incomingDirection The incoming ray's direction
     * @param surfaceNormal The surface's normal direction
     * @param refractionRatio The refraction ratio of the medium
     * @return A vector with the refraction direction
     */
    public static Vector3D refraction(
            final Vector3D incomingDirection,
            final Vector3D surfaceNormal,
            final double refractionRatio) {
        Vector3D direction = Vector3D.normalize(incomingDirection);
        Vector3D normal = Vector3D.normalize(surfaceNormal);
        double cosI = -Vector3D.dotProduct(normal, direction);
        double sinT2 = refractionRatio * refractionRatio * (1.0 - cosI * cosI);
        // Total internal reflection
        if (sinT2 > 1.0) {
            return reflection(direction, normal);
        }
        double cosT = Math.sqrt(1.0 - sinT2);
        return Vector3D.add(Vector3D.scalarMultiplication(direction, refractionRatio), Vector3D.scalarMultiplication(normal, refractionRatio * cosI - cosT));
    }
}
