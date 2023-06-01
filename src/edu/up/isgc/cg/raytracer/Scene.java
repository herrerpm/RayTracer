/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer;

import edu.up.isgc.cg.raytracer.lights.Light;
import edu.up.isgc.cg.raytracer.objects.Camera;
import edu.up.isgc.cg.raytracer.objects.Object3D;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MigueL Herrera
 * @author Jafet Rodríguez
 */
public class Scene {
    /**
     *  The main camera of the scene.
     */
    private Camera camera;
    /**
     *  The objects conforming the scene.
     */
    private List<Object3D> objects;
    /**
     *  The lights conforming the scene.
     */
    private List<Light> lights;

    /**
     *  The main constructor of the scene, it initializes the lists of objects and lights.
     */
    public Scene() {
        setObjects(new ArrayList<>());
        setLights(new ArrayList<>());
    }

    /**
     * The getLights method returns the lights in the scene,
     * if they were not set before the list is created.
     * @return The lights ArrayList
     */
    public List<Light> getLights() {
        if (lights == null) {
            setLights(new ArrayList<>());
        }
        return lights;
    }

    /**
     * A setter for the Lights in the scene.
     * @param newLights A lights arraylist
     */
    public void setLights(final List<Light> newLights) {
        this.lights = newLights;
    }

    /**
     * A method for adding lights to the scene.
     * @param light A light to be added in the scene
     */
    public void addLight(final Light light) {
        getLights().add(light);
    }

    /**
     *  A method for getting the Camera.
     * @return The main camera of the scene
     */
    public Camera getCamera() {
        return camera;
    }
    /**
     *  A method for setting the newCamera.
     * @param newCamera A newCamera object to be added in the scene
     */
    public void setCamera(final Camera newCamera) {
        this.camera = newCamera;
    }
    /**
     * A method for adding objects in the scene.
     * @param object An Object3D object to be added
     */
    public void addObject(final Object3D object) {
        getObjects().add(object);
    }

    /**
     *  A method for getting the object's list in
     *  the scene, it is initialized if it is null.
     * @return The Objects list of the scene
     */
    public List<Object3D> getObjects() {
        if (objects == null) {
            objects = new ArrayList<>();
        }
        return objects;
    }
    /**
     * A setter for the newObjects in the scene.
     * @param newObjects An arraylist of newObjects for the scene
     */
    public void setObjects(final List<Object3D> newObjects) {
        this.objects = newObjects;
    }
}

