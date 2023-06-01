/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import java.awt.Color;

/**
 * @author Miguel Herrera
 * @author Jafet Rodr�guez
 */

public class Camera extends Object3D {
    /**
     * The field of view of the camara, [0] is
     * horizontal, and [1] is vertical.
     */
    private double[] fieldOfView = new double[2];
    /**
     * The default Z viewing distance.
     */
    private double defaultZ = 15.0;
    /**
     * The resolution of the camera [0] is width,
     * [1] is height.
     */
    private int[] resolution;
    /**
     * The near Far planes, [0] is near, [1] is far.
     */
    private double[] nearFarPlanes = new double[2];

    /**
     * The main constructor for the class.
     * @param position A vector3D position
     * @param fieldOfViewHorizontal The horizontal FOV
     * @param fieldOfViewVertical THe vertical FOV
     * @param widthResolution The width
     * @param heightResolution The height
     * @param nearPlane The nearPlane
     * @param farPlane The farPlane
     */
    public Camera(final Vector3D position,
                  final double fieldOfViewHorizontal,
                  final double fieldOfViewVertical,
                  final int widthResolution,
                  final int heightResolution,
                  final double nearPlane,
                  final double farPlane) {
        super(position, Color.BLACK, null);
        setFieldOfViewHorizontal(fieldOfViewHorizontal);
        setFieldOfViewVertical(fieldOfViewVertical);
        setResolution(new int[]{widthResolution, heightResolution});
        setNearFarPlanes(new double[]{nearPlane, farPlane});
    }

    /**
     *  A getter for the NearFarPlanes.
     * @return The planes' array
     */
    public double[] getNearFarPlanes() {
        return nearFarPlanes;
    }

    /**
     * A setter for the planes.
     * @param nearFarPlanesValue The planes' array
     */
    private void setNearFarPlanes(
            final double[] nearFarPlanesValue) {
        this.nearFarPlanes = nearFarPlanesValue;
    }

    /**
     * A getter for the field of view.
     * @return The field of view array
     */
    public double[] getFieldOfView() {
        return fieldOfView;
    }

    /**
     * A setter for the field of view.
     * @param fieldOfViewValue The new field of view array
     */
    private void setFieldOfView(final double[] fieldOfViewValue) {
        this.fieldOfView = fieldOfViewValue;
    }

    /**
     * A getter for the horizontal field of view.
     * @return The horizontal field of view
     */
    public double getFieldOfViewHorizontal() {
        return fieldOfView[0];
    }

    /**
     * A setter for the horizontal field of view.
     * @param fieldOfViewHorizontal The new horizontal field of view
     */
    public void setFieldOfViewHorizontal(final double fieldOfViewHorizontal) {
        fieldOfView[0] = fieldOfViewHorizontal;
    }

    /**
     * A getter for the vertical field of view.
     * @return The vertical field of view
     */
    public double getFieldOfViewVertical() {
        return fieldOfView[1];
    }

    /**
     * A setter for the vertical field of view.
     * @param fieldOfViewVertical The new vertical field of view
     */
    public void setFieldOfViewVertical(final double fieldOfViewVertical) {
        fieldOfView[1] = fieldOfViewVertical;
    }

    /**
     * A getter for the default z-coordinate value.
     * @return The default z-coordinate value
     */
    public double getDefaultZ() {
        return defaultZ;
    }

    /**
     * A setter for the default z-coordinate value.
     * @param defaultZValue The new default z-coordinate value
     */
    public void setDefaultZ(final double defaultZValue) {
        this.defaultZ = defaultZValue;
    }

    /**
     * A getter for the resolution array.
     * @return The resolution array
     */
    public int[] getResolution() {
        return resolution;
    }

    /**
     * A getter for the resolution width.
     * @return The resolution width
     */
    public int getResolutionWidth() {
        return resolution[0];
    }

    /**
     * A getter for the resolution height.
     * @return The resolution height
     */
    public int getResolutionHeight() {
        return resolution[1];
    }

    /**
     * A setter for the resolution array.
     * @param resolutionValue The new resolution array
     */
    private void setResolution(final int[] resolutionValue) {
        this.resolution = resolutionValue;
    }

    /**
     *  Calculate the set of positions to be traced.
     * @return A list of the vectors with the positions to trace
     */
    public Vector3D[][] calculatePositionsToRay() {
        double angleMaxX = getFieldOfViewHorizontal() / 2.0;
        double radiusMaxX = getDefaultZ() / Math.cos(Math.toRadians(angleMaxX));

        double maxX = Math.sin(Math.toRadians(angleMaxX)) * radiusMaxX;
        double minX = -maxX;

        double angleMaxY = getFieldOfViewVertical() / 2.0;
        double radiusMaxY = getDefaultZ() / Math.cos(Math.toRadians(angleMaxY));

        double maxY = Math.sin(Math.toRadians(angleMaxY)) * radiusMaxY;
        double minY = -maxY;

        Vector3D[][] positions = new Vector3D[getResolutionWidth()][getResolutionHeight()];
        double posZ = getDefaultZ();

        for (int x = 0; x < positions.length; x++) {
            for (int y = 0; y < positions[x].length; y++) {
                double posX = minX + (((maxX - minX) / (double) getResolutionWidth()) * x);
                double posY = maxY - (((maxY - minY) / (double) getResolutionHeight()) * y);
                positions[x][y] = new Vector3D(posX, posY, posZ);
            }
        }

        return positions;
    }

    /**
     * The intersection implementation from Object3D.
     * @param ray A ray object to be cast
     * @return A null intersection
     */
    @Override
    public Intersection getIntersection(final Ray ray) {
        return new Intersection(Vector3D.getZero(), -1, Vector3D.getZero(), null);
    }
}
