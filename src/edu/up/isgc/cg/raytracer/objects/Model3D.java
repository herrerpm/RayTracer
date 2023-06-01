/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.tools.Barycentric;
import edu.up.isgc.cg.raytracer.utils.Material;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Miguel Herrera
 * @author Jafet Rodr�guez
 */

public class Model3D extends Object3D {
    /**
     * A list of the object's triangles.
     */
    private List<Triangle> triangles;

    /**
     *  A constructor for the model3D class.
     * @param position The position of the model 3D
     * @param triangleList The list of triangles for the model
     * @param color The java AWT color
     * @param material The material of the model
     */
    public Model3D(
            final Vector3D position,
            final Triangle[] triangleList,
            final Color color,
            final Material material) {
        super(position, color, material);
        setTriangles(triangleList);
    }

    /**
     *  A getter for the triangle's of the model.
     * @return The list of triangles
     */
    public List<Triangle> getTriangles() {
        return triangles;
    }

    /**
     *  A setter for the triangles of the model.
     * @param triangleList The triangle list
     */
    public void setTriangles(
            final Triangle[] triangleList) {
        Vector3D position = getPosition();
        Set<Vector3D> uniqueVertices = new HashSet<>();
        for (Triangle triangle : triangleList) {
            uniqueVertices.addAll(Arrays.asList(triangle.getVertices()));
        }

        for (Vector3D vertex : uniqueVertices) {
            vertex.setX(vertex.getX() + position.getX());
            vertex.setY(vertex.getY() + position.getY());
            vertex.setZ(vertex.getZ() + position.getZ());
        }

        this.triangles = Arrays.asList(triangleList);
    }

    /**
     *  A method for scaling a model.
     * @param scaling A vector with each component scale
     * @return The scaled model
     */
    public Model3D scale(final Vector3D scaling) {
        Vector3D[] tempVertices = new Vector3D[3];
        Vector3D[] tempNormals = new Vector3D[3];
        Vector3D[] normals;
        Vector3D[] vertices;
        Vector3D center = this.getPosition(); // Assuming you have a method to get the center of the model
        for (Triangle triangle: this.getTriangles()) {
            for (int i = 0; i < 3; i++) {
                normals = triangle.getNormals();
                vertices = triangle.getVertices();
                // Translate to origin, scale, then translate back
                tempVertices[i] = Vector3D.add(Vector3D.componentMultiplication(Vector3D.subtract(vertices[i], center), scaling), center);
                // Scale and normalize normals
                tempNormals[i] = Vector3D.normalize(Vector3D.componentMultiplication(normals[i], scaling));
            }
            triangle.setNormals(tempNormals[0], tempNormals[1], tempNormals[2]);
            triangle.setVertices(tempVertices[0], tempVertices[1], tempVertices[2]);
        }
        return this;
    }

    /**
     *  A method for rotating the model in the X axis.
     * @param rotationAngle An angle in degrees to rotate the model
     * @return the rotated model
     */
    public Model3D rotateX(final double rotationAngle){
        Vector3D[] tempVertices = new Vector3D[3];
        Vector3D[] tempNormals = new Vector3D[3];
        Vector3D[] normals;
        Vector3D[] vertices;
        Vector3D center = this.getPosition(); // Assuming you have a method to get the center of the model
        for (Triangle triangle: this.getTriangles()) {
            for (int i = 0; i < 3; i++) {
                normals = triangle.getNormals();
                vertices = triangle.getVertices();
                // Translate to origin, scale, then translate back
                tempVertices[i] = Vector3D.add(Vector3D.rotateX(Vector3D.subtract(vertices[i], center), Math.toRadians(rotationAngle)), center);
                // Scale and normalize normals
                tempNormals[i] = Vector3D.normalize(Vector3D.rotateX(normals[i], Math.toRadians(rotationAngle)));
            }
            triangle.setNormals(tempNormals[0], tempNormals[1], tempNormals[2]);
            triangle.setVertices(tempVertices[0], tempVertices[1], tempVertices[2]);
        }
        return this;
    }
    /**
     *  A method for rotating the model in the Y axis.
     * @param rotationAngle An angle in degrees to rotate the model
     * @return the rotated model
     */
    public Model3D rotateY(double rotationAngle) {
        Vector3D[] tempVertices = new Vector3D[3];
        Vector3D[] tempNormals = new Vector3D[3];
        Vector3D[] normals;
        Vector3D[] vertices;
        Vector3D center = this.getPosition(); // Assuming you have a method to get the center of the model
        for (Triangle triangle: this.getTriangles()) {
            for (int i = 0; i < 3; i++) {
                normals = triangle.getNormals();
                vertices = triangle.getVertices();
                // Translate to origin, scale, then translate back
                tempVertices[i] = Vector3D.add(Vector3D.rotateY(Vector3D.subtract(vertices[i], center), Math.toRadians(rotationAngle)), center);
                // Scale and normalize normals
                tempNormals[i] = Vector3D.normalize(Vector3D.rotateY(normals[i], Math.toRadians(rotationAngle)));
            }
            triangle.setNormals(tempNormals[0], tempNormals[1], tempNormals[2]);
            triangle.setVertices(tempVertices[0], tempVertices[1], tempVertices[2]);
        }
        return this;
    }
    /**
     *  A method for rotating the model in the Z axis.
     * @param rotationAngle An angle in degrees to rotate the model
     * @return the rotated model
     */
    public Model3D rotateZ(double rotationAngle) {
        Vector3D[] tempVertices = new Vector3D[3];
        Vector3D[] tempNormals = new Vector3D[3];
        Vector3D[] normals;
        Vector3D[] vertices;
        Vector3D center = this.getPosition(); // Assuming you have a method to get the center of the model
        for (Triangle triangle: this.getTriangles()) {
            for (int i = 0; i < 3; i++) {
                normals = triangle.getNormals();
                vertices = triangle.getVertices();
                // Translate to origin, scale, then translate back
                tempVertices[i] = Vector3D.add(Vector3D.rotateZ(Vector3D.subtract(vertices[i], center), Math.toRadians(rotationAngle)), center);
                // Scale and normalize normals
                tempNormals[i] = Vector3D.normalize(Vector3D.rotateZ(normals[i], Math.toRadians(rotationAngle)));
            }
            triangle.setNormals(tempNormals[0], tempNormals[1], tempNormals[2]);
            triangle.setVertices(tempVertices[0], tempVertices[1], tempVertices[2]);
        }
        return this;
    }

    /**
     *  A method for calculating the intersection with a model.
     * @param ray A ray object to be cast
     * @return An intersection, if any
     */
    @Override
    public Intersection getIntersection(final Ray ray) {
        double distance = -1;
        Vector3D normal = Vector3D.getZero();
        Vector3D position = Vector3D.getZero();

        for (Triangle triangle : getTriangles()) {
            Intersection intersection = triangle.getIntersection(ray);
            double intersectionDistance = intersection.getDistance();
            if (intersection != null && intersectionDistance > 0 &&
                    (intersectionDistance < distance || distance < 0)) {
                distance = intersectionDistance;
                position = Vector3D.add(ray.getOrigin(), Vector3D.scalarMultiplication(ray.getDirection(), distance));
                //normal = triangle.getNormal();
                normal = Vector3D.getZero();
                double[] uVw = Barycentric.calculateBarycentricCoordinates(position, triangle);
                Vector3D[] normals = triangle.getNormals();
                for (int i = 0; i < uVw.length; i++) {
                    normal = Vector3D.add(normal, Vector3D.scalarMultiplication(normals[i], uVw[i]));
                }
            }
        }

        if (distance == -1) {
            return null;
        }

        return new Intersection(position, distance, normal, this);
    }
}
