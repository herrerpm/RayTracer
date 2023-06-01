/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer;

import java.awt.Color;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class Vector3D {
    /**
     * A definition for an origin vector.
     */
    private static final Vector3D ZERO = new Vector3D(0.0, 0.0, 0.0);
    /**
     * The x component of the vector.
     */
    private double x;
    /**
     * The y component of the vector.
     */
    private double y;
    /**
     * The z component of the vector.
     */
    private double z;

    /**
     * The main constructor for the Vector object.
     * @param xComponent A double representing the x-axis
     * @param yComponent A double representing the y-axis
     * @param zComponent A double representing the z-axis
     */
    public Vector3D(final double xComponent, final double yComponent, final double zComponent) {
        setX(xComponent);
        setY(yComponent);
        setZ(zComponent);
    }

    /**
     * A getter for the x component.
     * @return A double representing the x-axis
     */
    public double getX() {
        return x;
    }

    /**
     * A setter for the x-axis.
     * @param xValue A double representing the x value
     */
    public void setX(final double xValue) {
        this.x = xValue;
    }
    /**
     * A getter for the y component.
     * @return A double representing the y-axis
     */
    public double getY() {
        return y;
    }
    /**
     * A setter for the y-axis.
     * @param yValue A double representing the y value
     */
    public void setY(final double yValue) {
        this.y = yValue;
    }
    /**
     * A getter for the z component.
     * @return A double representing the z-axis
     */
    public double getZ() {
        return z;
    }
    /**
     * A setter for the z-axis.
     * @param zValue A double representing the z value
     */
    public void setZ(final double zValue) {
        this.z = zValue;
    }

    /**
     * A method for returning a new instance of the current vector.
     * @return A new vector with the same values
     */
    public Vector3D clone() {
        return new Vector3D(getX(), getY(), getZ());
    }

    /**
     * A method for returning an instance of the Zero vector.
     * @return An origin vector
     */
    public static Vector3D getZero() {
        return ZERO.clone();
    }

    /**
     * A method for printing the current vector representation.
     * @return A string representing the current vector.
     */
    @Override
    public String toString() {
        return "Vector3D{" + "x=" + getX() + ", y=" + getY() + ", z=" + getZ() + "}";
    }

    /**
     * An implementation of the dot product.
     * @param vectorA A vector A
     * @param vectorB A vector B
     * @return The dot product between A and B
     */
    public static double dotProduct(final Vector3D vectorA, final Vector3D vectorB) {
        return (vectorA.getX() * vectorB.getX()) + (vectorA.getY() * vectorB.getY()) + (vectorA.getZ() * vectorB.getZ());
    }

    /**
     *  A method for calculating the distance between two points.
     * @param pointA A point A
     * @param pointB A point B
     * @return The distance between both points
     */
    public static double calculateDistance(final Vector3D pointA, final Vector3D pointB) {
        return Math.sqrt(
                (pointB.getX() - pointA.getX()) * (pointB.getX() - pointA.getX()) +
                (pointB.getY() - pointA.getY()) * (pointB.getY() - pointA.getY()) +
                (pointB.getZ() - pointA.getZ()) * (pointB.getZ() - pointA.getZ())
        );
    }

    /**
     * A method for multiplying the components of two vectors.
     * @param vectorA A vector A
     * @param vectorB A vector B
     * @return A vector3D of the multiplication of each component
     */
    public static Vector3D componentMultiplication(final Vector3D vectorA, final Vector3D vectorB) {
        return new Vector3D(
                vectorA.getX() * vectorB.getX(),
                vectorA.getY() * vectorB.getY(),
                vectorA.getZ() * vectorB.getZ()
        );
    }

    /**
     *  A method for converting a color to a vector.
     * @param color A java AWT color
     * @return A vector representing the normalized color
     */
    public static Vector3D colorToVect(final Color color) {
        final double maxValue = 255d;
        return new Vector3D(
                color.getRed() / maxValue,
                color.getGreen() / maxValue,
                color.getBlue() / maxValue
        );
    }

    /**
     * A method for calculating the cross product of two vectors.
     * @param vectorA A vector A
     * @param vectorB A vector B
     * @return A vector representing the cross product AxB
     */
    public static Vector3D crossProduct(final Vector3D vectorA, final Vector3D vectorB) {
        return new Vector3D((vectorA.getY() * vectorB.getZ()) - (vectorA.getZ() * vectorB.getY()),
                (vectorA.getZ() * vectorB.getX()) - (vectorA.getX() * vectorB.getZ()),
                (vectorA.getX() * vectorB.getY()) - (vectorA.getY() * vectorB.getX()));
    }

    /**
     *  A method for calculating the magnitude of a vector.
     * @param vectorA A vector A
     * @return A double of the vector's magnitude
     */
    public static double magnitude(final Vector3D vectorA) {
        return Math.sqrt(dotProduct(vectorA, vectorA));
    }

    /**
     * A method for adding two vectors.
     * @param vectorA A vector A
     * @param vectorB A vector B
     * @return The vector representing A+B
     */
    public static Vector3D add(final Vector3D vectorA, final Vector3D vectorB) {
        return new Vector3D(vectorA.getX() + vectorB.getX(), vectorA.getY() + vectorB.getY(), vectorA.getZ() + vectorB.getZ());
    }

    /**
     *  A method for subtracting vectors.
     * @param vectorA A vector A
     * @param vectorB A vector B
     * @return A vector representing A-B
     */
    public static Vector3D subtract(final Vector3D vectorA, final Vector3D vectorB) {
        return new Vector3D(vectorA.getX() - vectorB.getX(), vectorA.getY() - vectorB.getY(), vectorA.getZ() - vectorB.getZ());
    }

    /**
     * A method for normalizing a vector.
     * @param vectorA A vector A
     * @return A vector representing norm(A)
     */
    public static Vector3D normalize(final Vector3D vectorA) {
        double mag = Vector3D.magnitude(vectorA);
        return new Vector3D(vectorA.getX() / mag, vectorA.getY() / mag, vectorA.getZ() / mag);
    }
    /**
     * A method for multiplying a vector by a scalar.
     * @param vectorA A vector A
     * @param scalar A double representing the scalar
     * @return The scaled vector
     */
    public static Vector3D scalarMultiplication(final Vector3D vectorA, final double scalar) {
        return new Vector3D(vectorA.getX() * scalar, vectorA.getY() * scalar, vectorA.getZ() * scalar);
    }

    /**
     *  A method for rotating an object in the X axis
     * @param vect The vector to rotate
     * @param angle The angle in degrees
     * @return The rotated vector
     */
    public static Vector3D rotateX(final Vector3D vect, final double angle) {
        Vector3D[] xRotate = new Vector3D[]{
                new Vector3D(1,0,0),
                new Vector3D(0,Math.cos(angle),-Math.sin(angle)),
                new Vector3D(0,Math.sin(angle),Math.cos(angle))
        };

        return new Vector3D(
                Vector3D.dotProduct(vect, xRotate[0]),
                Vector3D.dotProduct(vect, xRotate[1]),
                Vector3D.dotProduct(vect, xRotate[2])
        );
    }
    /**
     *  A method for rotating an object in the Y axis
     * @param vect The vector to rotate
     * @param angle The angle in degrees
     * @return The rotated vector
     */
    public static Vector3D rotateY(final Vector3D vect, final double angle){
        Vector3D[] yRotate = new Vector3D[]{
                new Vector3D(Math.cos(angle),0, -Math.sin(angle)),
                new Vector3D(0, 1, 0),
                new Vector3D(Math.sin(angle),0,Math.cos(angle))
        };

        return new Vector3D(
                Vector3D.dotProduct(vect, yRotate[0]),
                Vector3D.dotProduct(vect, yRotate[1]),
                Vector3D.dotProduct(vect, yRotate[2])
        );

    }
    /**
     *  A method for rotating an object in the Z axis.
     * @param vect The vector to rotate
     * @param angle The angle in degrees
     * @return The rotated vector
     */
    public static Vector3D rotateZ(final Vector3D vect, final double angle){
        Vector3D[] zRotate = new Vector3D[]{
                new Vector3D(Math.cos(angle),-Math.sin(angle),0),
                new Vector3D(Math.sin(angle),Math.cos(angle),0),
                new Vector3D(0,0,1)
        };

        return new Vector3D(
                Vector3D.dotProduct(vect, zRotate[0]),
                Vector3D.dotProduct(vect, zRotate[1]),
                Vector3D.dotProduct(vect, zRotate[2])
        );

    }
}
