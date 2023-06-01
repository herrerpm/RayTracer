/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer;


import edu.up.isgc.cg.raytracer.lights.Light;
import edu.up.isgc.cg.raytracer.objects.*;
import edu.up.isgc.cg.raytracer.tools.SceneManager;
import edu.up.isgc.cg.raytracer.utils.IlluminationHandler;
import edu.up.isgc.cg.raytracer.utils.Material;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public final class Raytracer {
    private Raytracer() {
    }

    /**
     * This is the main entry of the program.
     * @param args The arguments of the program
     */
    public static void main(final String[] args) {
        System.out.println(new Date());
        BufferedImage image = raytrace(SceneManager.render03());
        File outputImage = new File("image.png");
        try {
            ImageIO.write(image, "png", outputImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(new Date());
    }


    /**
     * The main function to calculate the color of a pixel.
     * @param mainCamera The mainCamera of the scene
     * @param ray The ray to be traced
     * @param objects The objects of the scene
     * @param lights The lights of the scene
     * @param depth The depth of the scene
     * @param caster The object caster
     * @return The color of the pixel in the scene
     */
    public static Color calculatePixel(final Camera mainCamera, final Ray ray, final List<Object3D> objects, final List<Light> lights, final int depth, final Object3D caster) {
        double[] nearFarPlanes = mainCamera.getNearFarPlanes();
        double cameraZ = mainCamera.getPosition().getZ();
        Intersection closestIntersection;
        Color pixelColor = Color.BLACK;
        // Remove clipping planes if recursion.
        if (depth == 0) {
            closestIntersection = raycast(ray, objects, null, new double[]{cameraZ + nearFarPlanes[0], cameraZ + nearFarPlanes[1]});
        } else {
            closestIntersection = raycast(ray, objects, caster, null);
        }
        if (closestIntersection != null) {
            Object3D currentObject = closestIntersection.getObject();
            Material objectMaterial = currentObject.getMaterial();
            Vector3D kDiffuse = objectMaterial.getkDiffuse(currentObject.getColor());
            Vector3D kSpecular = objectMaterial.getkDiffuse(Color.WHITE);
            Vector3D finalSpecular = Vector3D.getZero();
            Vector3D finalDiffuse = Vector3D.getZero();
            Color reflectionColor = null;
            Vector3D ambientComponent = Vector3D.componentMultiplication(Vector3D.colorToVect(currentObject.getColor()), new Vector3D(0.1, 0.1, 0.1));
            for (Light light : lights) {
                Vector3D direction = light.getDirectionFrom(closestIntersection.getPosition());
                Vector3D offsetPosition = Vector3D.add(closestIntersection.getPosition(), Vector3D.scalarMultiplication(closestIntersection.getNormal(), 0.00001));
                Ray shadow = new Ray(offsetPosition, direction);
                Intersection shadowIntersection = raycast(shadow, objects, closestIntersection.getObject(), null);
                double distance = Vector3D.calculateDistance(light.getPosition(), closestIntersection.getPosition());
                if (shadowIntersection == null || shadowIntersection.getDistance() > distance || shadowIntersection.getObject().getMaterial().getTransparency() > 0.0) {
//                    Change for renders.
//                    double lightFallof = IlluminationHandler.lightFallof(0.01,0.001,0.0001,closestIntersection, light);
//                    double lightFallof = IlluminationHandler.lightFallof(0.001,0.001,0.001,closestIntersection, light);
                    double lightFallof = IlluminationHandler.lightFallof(0.001,0.01,0.01,closestIntersection, light);
                    finalSpecular = Vector3D.add(finalSpecular, Vector3D.componentMultiplication(
                            IlluminationHandler.blinnPhongSpecular(
                                    direction,
                                    mainCamera,
                                    closestIntersection,
                                    light,
                                    objectMaterial,
                                    kSpecular,
                                    lightFallof
                            ), Vector3D.colorToVect(light.getColor())));
                    finalDiffuse = Vector3D.add(finalDiffuse,
                            Vector3D.componentMultiplication(
                                    IlluminationHandler.blinnPhongDiffuse(
                                            kDiffuse,
                                            light,
                                            closestIntersection,
                                            lightFallof
                                    ),
                                    Vector3D.colorToVect(light.getColor())));
                }
                if (shadowIntersection != null && shadowIntersection.getObject().getMaterial().getTransparency() > 0.0) {
                    Vector3D shadowColor = Vector3D.scalarMultiplication(Vector3D.colorToVect(shadowIntersection.getObject().getColor()),objectMaterial.getTransparency()+0.03);
                    finalDiffuse = Vector3D.add(shadowColor, finalDiffuse);
                    finalSpecular = Vector3D.add(shadowColor, finalSpecular);
                }
            }
//             calculate reflection
            if (depth < 10 && objectMaterial.getReflectivity() > 0.0) {
                Vector3D intersectionPoint = closestIntersection.getPosition();
                Vector3D reflectedDirection = IlluminationHandler.reflection(ray.getDirection(), closestIntersection.getNormal());
                Vector3D offsetPosition = Vector3D.add(intersectionPoint, Vector3D.scalarMultiplication(closestIntersection.getNormal(), 0.00001));
                Ray reflectedRay = new Ray(offsetPosition, reflectedDirection);
                reflectionColor = calculatePixel(mainCamera, reflectedRay, objects, lights, depth + 1, null);
            }

            Color refractionColor = null;
            if (depth < 10 && objectMaterial.getTransparency() > 0.0) {
                Vector3D intersectionPoint = closestIntersection.getPosition();
                double refractiveIndex = objectMaterial.getRefractivity();
                Vector3D refractedDirection = IlluminationHandler.refraction(ray.getDirection(), closestIntersection.getNormal(), refractiveIndex);
                if (refractedDirection != null) {
                    Vector3D offsetPosition = Vector3D.add(intersectionPoint, Vector3D.scalarMultiplication(closestIntersection.getNormal(), 0.00001));
                    Ray refractedRay = new Ray(offsetPosition, refractedDirection);
                    refractionColor = calculatePixel(mainCamera, refractedRay, objects, lights, depth + 1, currentObject);
                }
            }

            Vector3D finalColor = Vector3D.add(finalSpecular, finalDiffuse);
            finalColor = Vector3D.add(finalColor, ambientComponent);
            if (reflectionColor != null) {
                finalColor = blendColors(finalColor, Vector3D.colorToVect(reflectionColor), objectMaterial.getReflectivity());
            }
            if (refractionColor != null) {
                Vector3D refractedColorVector = Vector3D.colorToVect(refractionColor);
                Vector3D finalRefractedColor = Vector3D.scalarMultiplication(refractedColorVector, objectMaterial.getTransparency());
                finalColor = Vector3D.add(finalColor, finalRefractedColor);
            }
            pixelColor = new Color(
                    (int) clamp(finalColor.getX() * 255, 0, 255),
                    (int) clamp(finalColor.getY() * 255, 0, 255),
                    (int) clamp(finalColor.getZ() * 255, 0, 255)
            );
        }
        return pixelColor;
    }

    /**
     * A function to blend colors.
     * @param color1 Color 1
     * @param color2 Color 2
     * @param index A scalar
     * @return The blending of both colors
     */
    public static Vector3D blendColors(final Vector3D color1, final Vector3D color2, final double index) {
        Vector3D part1 = Vector3D.scalarMultiplication(color2, index);
        Vector3D part2 = Vector3D.scalarMultiplication(color1, 1 - index);
        return Vector3D.add(part1, part2);
    }
    public static BufferedImage raytrace(final Scene scene) {
        Camera mainCamera = scene.getCamera();
        BufferedImage image = new BufferedImage(mainCamera.getResolutionWidth(), mainCamera.getResolutionHeight(), BufferedImage.TYPE_INT_RGB);
        List<Object3D> objects = scene.getObjects();
        List<Light> lights = scene.getLights();
        Vector3D[][] positionsToRaytrace = mainCamera.calculatePositionsToRay();
        ExecutorService executorService = Executors.newWorkStealingPool();
        for (int i = 0; i < positionsToRaytrace.length; i++) {
            for (int j = 0; j < positionsToRaytrace[i].length; j++) {
                double x = positionsToRaytrace[i][j].getX() + mainCamera.getPosition().getX();
                double y = positionsToRaytrace[i][j].getY() + mainCamera.getPosition().getY();
                double z = positionsToRaytrace[i][j].getZ() + mainCamera.getPosition().getZ();
                    Runnable runnable = tracePixel(mainCamera, x,y,z, objects,lights, image, i, j);
                    executorService.execute(runnable);
                }
            }
        executorService.shutdown();
        try{
            if (!executorService.awaitTermination(240, TimeUnit.MINUTES)){
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (!executorService.isTerminated()){
                System.err.println("Cancel non-finished");
            }
        }
        executorService.shutdownNow();
        return image;
    }

    /**
     *  A method to clamp colors
     * @param value The value to be clamped
     * @param min The max values
     * @param max The min values
     * @return The color clamped
     */
    public static float clamp(double value, double min, double max) {
        if (value < min) {
            return (float) min;
        }
        if (value > max) {
            return (float) max;
        }
        return (float) value;
    }

    /**
     * A method to perform a raycast to check for intersection.
     * @param ray The ray to be casted
     * @param objects The objects in the scene
     * @param caster The object´s caster
     * @param clippingPlanes The clippling planes of the camera
     * @return An intersection if any
     */
    public static Intersection raycast(final Ray ray, final List<Object3D> objects, final Object3D caster, final double[] clippingPlanes) {
        Intersection closestIntersection = null;

        for (int k = 0; k < objects.size(); k++) {
            Object3D currentObj = objects.get(k);
            if (caster == null || !currentObj.equals(caster)) {
                Intersection intersection = currentObj.getIntersection(ray);
                if (intersection != null) {
                    double distance = intersection.getDistance();
                    double intersectionZ = intersection.getPosition().getZ();
                    if (distance >= 0 &&
                            (closestIntersection == null || distance < closestIntersection.getDistance()) &&
                            (clippingPlanes == null || (intersectionZ >= clippingPlanes[0] && intersectionZ <= clippingPlanes[1]))) {
                        closestIntersection = intersection;
                    }
                }
            }
        }

        return closestIntersection;
    }

    /**
     * The paralelization of the raytracer.
     * @param mainCamera The main camera
     * @param x The x position to trace
     * @param y The y position to trace
     * @param z The z position to trace
     * @param objects The objecs in the scene
     * @param lights The lights in the scene
     * @param image The image to color
     * @param i The row of the image
     * @param j The column of the image
     * @return a Runnable for the ray tracer
     */
    public static Runnable tracePixel(
            Camera mainCamera,
            double x, double y, double z,
            List<Object3D> objects, List<Light> lights,
            BufferedImage image,
            int i, int j
    ){
        Runnable aRunnable = new Runnable() {
            @Override
            public void run() {
                Ray ray = new Ray(mainCamera.getPosition(), new Vector3D(x, y, z));
                setRGB(image, i, j, calculatePixel(mainCamera, ray,objects,lights, 0, null));
            }
        };
        return aRunnable;
    }

    /**
     * A lock of the setRGBM method
     * @param image An image to color
     * @param i The row
     * @param j The color
     * @param color A javaAWT color
     */
    public static synchronized void setRGB(
            BufferedImage image,
            int i,
            int j,
            Color color){
        image.setRGB(i, j, color.getRGB());
    }


}
