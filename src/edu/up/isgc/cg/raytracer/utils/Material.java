package edu.up.isgc.cg.raytracer.utils;

import edu.up.isgc.cg.raytracer.Vector3D;

import java.awt.*;

/**
 * A class for modeling materials
 *
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class Material {
    public static final Material METAL = new Material(0.9, 0.3, 0.3, 132, 0, 0);
    public static final Material REFERENCE = new Material(1.0, 1.0, 0.0, 96, 0, 0);
    public static final Material GLASS = new Material(0.1, 0.6, 0.1, 96, 0.5, 1.5);
    public static final Material RUBBER = new Material(0.9, 0.1, 0, 10, 0, 0);
    public static final Material MIRROR = new Material(0.8, 0.3, 1.0, 70, 0, 0);

    /**
     * Represents the diffuse component of the material.
     */
    private double diffuseComponent;

    /**
     * Represents the specular component of the material.
     * */
    private double specularComponent;

    /**
     * Represents the shininess of the material.
     */
    private double shiniess;

    /**
     * Represents the reflectivity of the material.
     */
    private double reflectivity;

    /**
     * Represents the transparency of the material.
     */
    private double transparency;

    /**
     * Represents the refractivity of the material.
     */
    private double refractivity;

    /**
     * Constructs a new Material.
     * @param diffuseComponent The diffuse reflection coefficient
     * @param specularComponent The specular reflection coefficient
     * @param reflectivity The reflectivity of the material
     * @param shiniess The shininess coefficient
     * @param transparency The transparency of the material
     * @param refractivity The refractive index of the material
     */
    public Material(double diffuseComponent, double specularComponent, double reflectivity, double shiniess, double transparency, double refractivity) {
        setShiniess(shiniess);
        setDiffuseComponent(diffuseComponent);
        setSpecularComponent(specularComponent);
        setTransparency(transparency);
        setReflectivity(reflectivity);
        setRefractivity(refractivity);
    }

    /**
     * A getter for the transparency.
     * @return The transparency of the material
     */
    public double getTransparency() {
        return transparency;
    }

    /**
     * A setter for the transparency.
     * @param transparency The new transparency of the material
     */
    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    /**
     * A getter for the refractivity.
     * @return The refractive index of the material
     */
    public double getRefractivity() {
        return refractivity;
    }

    /**
     * A setter for the refractivity.
     * @param refractivity The new refractive index of the material
     */
    public void setRefractivity(double refractivity) {
        this.refractivity = refractivity;
    }

    /**
     * A getter for the shininess coefficient.
     * @return The shininess coefficient of the material
     */
    public double getShiniess() {
        return shiniess;
    }

    /**
     * A setter for the shininess coefficient.
     * @param shiniess The new shininess coefficient of the material
     */
    private void setShiniess(double shiniess) {
        this.shiniess = shiniess;
    }

    /**
     * A method to calculate the diffuse reflection coefficient of a given color.
     * @param color The color of the material
     * @return The diffuse reflection coefficient of the given color
     */
    public Vector3D getkDiffuse(Color color) {
        return Vector3D.scalarMultiplication(Vector3D.colorToVect(color), diffuseComponent);
    }

    /**
     * A method to calculate the specular reflection coefficient of a given color.
     * @param color The color of the material
     * @return The specular reflection coefficient of the given color
     */
    public Vector3D getkSpecular(Color color) {
        return Vector3D.scalarMultiplication(Vector3D.colorToVect(color), specularComponent);
    }

    /**
     * A getter for the diffuse reflection coefficient.
     * @return The diffuse reflection coefficient of the material
     */
    public double getDiffuseComponent() {
        return diffuseComponent;
    }

    /**
     * A setter for the diffuse reflection coefficient.
     * @param diffuseComponent The new diffuse reflection coefficient of the material
     */
    private void setDiffuseComponent(double diffuseComponent) {
        this.diffuseComponent = diffuseComponent;
    }

    /**
     * A getter for the specular reflection coefficient.
     * @return The specular reflection coefficient of the material
     */
    public double getSpecularComponent() {
        return specularComponent;
    }

    /**
     * A setter for the specular reflection coefficient.
     * @param specularComponentValue The new specular reflection coefficient of the material
     */
    private void setSpecularComponent(final double specularComponentValue) {
        this.specularComponent = specularComponentValue;
    }

    /**
     * A getter for the reflectivity.
     * @return The reflectivity of the material
     */
    public double getReflectivity() {
        return reflectivity;
    }

    /**
     * A setter for the reflectivity.
     * @param reflectivityValue The new reflectivity of the material
     */
    private void setReflectivity(final double reflectivityValue) {
        this.reflectivity = reflectivityValue;
    }
}