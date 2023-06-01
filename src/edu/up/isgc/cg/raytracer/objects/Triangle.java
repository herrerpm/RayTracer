/**
 * [1968] - [2023] Centros Culturales de Mexico A.C / Universidad Panamericana
 * All Rights Reserved.
 */
package edu.up.isgc.cg.raytracer.objects;

import edu.up.isgc.cg.raytracer.Intersection;
import edu.up.isgc.cg.raytracer.Ray;
import edu.up.isgc.cg.raytracer.Vector3D;

/**
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class Triangle implements IIntersectable {
    /**
     * Represents a small value used for floating point comparisons.
     */
    public static final double EPSILON = 0.0000001;
    /**
     * Represents the vertices of the triangle.
     */
    private Vector3D[] vertices;
    /**
     * Represents the normals of the triangle.
     */
    private Vector3D[] normals;
    /**
     * Constructs a new triangle with the specified vertices and no normals.
     *
     * @param v1 the first vertex of the triangle.
     * @param v2 the second vertex of the triangle.
     * @param v3 the third vertex of the triangle.
     */
    public Triangle(Vector3D v1, Vector3D v2, Vector3D v3) {
        setVertices(v1, v2, v3);
        setNormals(null);
    }
    /**
     * Constructs a new triangle with the specified vertices and normals.
     *
     * @param v1 the first vertex of the triangle.
     * @param v2 the second vertex of the triangle.
     * @param v3 the third vertex of the triangle.
     * @param n1 the normal at the first vertex of the triangle.
     * @param n2 the normal at the second vertex of the triangle.
     * @param n3 the normal at the third vertex of the triangle.
     */
    public Triangle(Vector3D v1, Vector3D v2, Vector3D v3, Vector3D n1, Vector3D n2, Vector3D n3) {
        this(v1, v2, v3);
        setNormals(n1, n2, n3);
    }
    /**
     * Constructs a new triangle with the specified vertices and normals.
     *
     * @param vertices the vertices of the triangle.
     * @param normal the normals at the vertices of the triangle.
     */
    public Triangle(Vector3D[] vertices, Vector3D[] normal) {
        if (vertices.length == 3) {
            setVertices(vertices[0], vertices[1], vertices[2]);
        } else {
            setVertices(Vector3D.getZero(), Vector3D.getZero(), Vector3D.getZero());
        }
        setNormals(normal);
    }
    /**
     * Returns the vertices of the triangle.
     *
     * @return the vertices of the triangle.
     */
    public Vector3D[] getVertices() {
        return vertices;
    }
    /**
     * Sets the vertices of the triangle.
     *
     * @param vertices the vertices to set.
     */
    private void setVertices(Vector3D[] vertices) {
        this.vertices = vertices;
    }
    /**
     * Sets the vertices of the triangle.
     *
     * @param v1 the first vertex to set.
     * @param v2 the second vertex to set.
     * @param v3 the third vertex to set.
     */
    public void setVertices(Vector3D v1, Vector3D v2, Vector3D v3) {
        setVertices(new Vector3D[]{v1, v2, v3});
    }
    /**
     * Returns the normals of the triangle, calculating them if necessary.
     *
     * @return the normals of the triangle.
     */
    public Vector3D[] getNormals() {
        if (normals == null) {
            Vector3D normal = getNormal();
            setNormals(new Vector3D[]{normal, normal, normal});
        }
        return normals;
    }
    /**
     * Returns the average normal of the triangle, calculating it if necessary.
     *
     * @return the average normal of the triangle.
     */
    public Vector3D getNormal() {
        Vector3D normal = Vector3D.getZero();
        Vector3D[] normals = this.normals;
        if (normals == null) {
            Vector3D[] vertices = getVertices();
            //1 to 0 and 2 to 0, multiplied by -1 or we can use 1 to 2 and 0 to 2
            Vector3D v = Vector3D.subtract(vertices[1], vertices[2]);
            Vector3D w = Vector3D.subtract(vertices[0], vertices[2]);
            normal = Vector3D.normalize(Vector3D.crossProduct(v, w));
        } else {
            for (int i = 0; i < normals.length; i++) {
                normal.setX(normal.getX() + normals[i].getX());
                normal.setY(normal.getY() + normals[i].getY());
                normal.setZ(normal.getZ() + normals[i].getZ());
            }
            normal.setX(normal.getX() / normals.length);
            normal.setY(normal.getY() / normals.length);
            normal.setZ(normal.getZ() / normals.length);
        }

        return normal;
    }
    /**
     * Sets the normals of the triangle.
     *
     * @param normals the normals to set.
     */
    private void setNormals(Vector3D[] normals) {
        this.normals = normals;
    }
    /**
     * Sets the normals of the triangle.
     *
     * @param normal1 the first normal to set.
     * @param normal2 the second normal to set.
     * @param normal3 the third normal to set.
     */
    public void setNormals(Vector3D normal1, Vector3D normal2, Vector3D normal3) {
        Vector3D[] normals = new Vector3D[]{normal1, normal2, normal3};
        setNormals(normals);
    }
    /**
     * Calculates the intersection of a ray with this triangle.
     *
     * @param ray the ray to intersect with.
     * @return the intersection of the ray with this triangle, or null if there is no intersection.
     */
    @Override
    public Intersection getIntersection(Ray ray) {
        Intersection intersection = new Intersection(null, -1, null, null);

        Vector3D[] vert = getVertices();
        Vector3D v2v0 = Vector3D.subtract(vert[2], vert[0]);
        Vector3D v1v0 = Vector3D.subtract(vert[1], vert[0]);
        Vector3D vectorP = Vector3D.crossProduct(ray.getDirection(), v1v0);
        double determinant = Vector3D.dotProduct(v2v0, vectorP);
        double invDet = 1.0 / determinant;
        Vector3D vectorT = Vector3D.subtract(ray.getOrigin(), vert[0]);
        double u = invDet * Vector3D.dotProduct(vectorT, vectorP);

        if (!(u < 0 || u > 1)) {
            Vector3D vectorQ = Vector3D.crossProduct(vectorT, v2v0);
            double v = invDet * Vector3D.dotProduct(ray.getDirection(), vectorQ);
            if (!(v < 0 || (u + v) > (1.0 + EPSILON))) {
                double t = invDet * Vector3D.dotProduct(vectorQ, v1v0);
                intersection.setDistance(t);
            }
        }

        return intersection;
    }
}
