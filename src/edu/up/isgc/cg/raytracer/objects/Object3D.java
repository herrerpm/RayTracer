/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.utils.Material;
import java.awt.Color;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public abstract class Object3D implements IIntersectable {
    /**
     * A java AWT color representing the object.
     */
    private Color color;
    /**
     * A Vector3D with the object's position.
     */
    private Vector3D position;
    /**
     * A Material with the object's properties
     */
    private Material material;

    /**
     *  The main constructor of the class.
     * @param positionValue A vector3D representing the position
     * @param colorValue A java AWT representing the color
     * @param materialValue A Material object with the main properties
     */
    public Object3D(final Vector3D positionValue,
                    final Color colorValue,
                    final Material materialValue) {
        setPosition(positionValue);
        setColor(colorValue);
        setMaterial(materialValue);
    }

    /**
     * A getter for the Material property.
     * @return the object's material
     */
    public Material getMaterial() {
        return material;
    }

    /**
     * A setter for the material.
     * @param materialValue A material with the object's properties
     */
    public void setMaterial(final Material materialValue) {
        this.material = materialValue;
    }
    /**
     *  A getter for the object's color.
     * @return the object's color
     */
    public Color getColor() {
        return color;
    }

    /**
     *  A setter for the object's color.
     * @param colorValue A  java AWT color
     */
    public void setColor(final Color colorValue) {
        this.color = colorValue;
    }

    /**
     * A getter for the object's position.
     * @return the object's position
     */
    public Vector3D getPosition() {
        return position;
    }

    /**
     * A setter for the object's position
     * @param positionValue A Vector3D object
     */
    public void setPosition(final Vector3D positionValue) {
        this.position = positionValue;
    }

}
