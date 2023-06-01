package edu.up.isgc.cg.raytracer.tools;

import edu.up.isgc.cg.raytracer.Scene;
import edu.up.isgc.cg.raytracer.Vector3D;
import edu.up.isgc.cg.raytracer.lights.PointLight;
import edu.up.isgc.cg.raytracer.objects.Camera;
import edu.up.isgc.cg.raytracer.utils.Material;

import java.awt.*;

/**
 * A class to create custom scenes.
 * @author Miguel Herrera
 * @author Jafet Rodríguez
 */
public class SceneManager {
    public static Scene render03(){
        Scene scene = new Scene();
//        scene.setCamera(new Camera(new Vector3D(0, 0, -10), 60, 60, 400, 400, 0.6, 50.0));
        scene.setCamera(new Camera(new Vector3D(0, 0, -10), 60, 60, 400, 400, 0.6, 50.0));
        scene.addLight(new PointLight(new Vector3D(0.0,4.5, -7.0), Color.WHITE, 0.2f));
        scene.addLight(new PointLight(new Vector3D(1.0,4.5, -2.0), new Color(242,175,149), 0.2f));
        scene.addLight(new PointLight(new Vector3D(-1.0,4.5, -5.0), new Color(129,117,131), 0.09f));
        scene.addObject(OBJReader.getModel3D("./src/models/FLOOR1.obj", new Vector3D(0, -4, 0), new Color(245,93,56),
                Material.RUBBER).scale(new Vector3D(0.7,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/FLOOR1.obj", new Vector3D(0, 8, -5), new Color(8,132,59), Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/FRONT1.obj", new Vector3D(0, -4.5, -15), Color.WHITE, Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/FRONT1.obj", new Vector3D(0, -4.5, -24), Color.WHITE, Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/RIGHT1.obj", new Vector3D(20, -3.5, 10), new Color(41,70,104), Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/LEFT1.obj", new Vector3D(-20, -3.5, 10), new Color(167,17,18), Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)));
//        scene.addObject(OBJReader.getModel3D("./src/models/scene05/TATANK.obj", new Vector3D(0, 0, 0), Color.DARK_GRAY, Material.RUBBER));
        scene.addObject(OBJReader.getModel3D("./src/models/Cube.obj", new Vector3D(2, -3.5, -5), Color.WHITE,
                new Material(0.6,0.6,0.8,96,0.0,0.0)).scale(new Vector3D(3,3,3)).rotateY(25));
        scene.addObject(OBJReader.getModel3D("./src/models/Cube.obj", new Vector3D(0, -3.5, -7), Color.BLUE,
                new Material(0.5,0.6,0.3,96,0.3,1.1)).scale(new Vector3D(1,1,1)).rotateY(25));
        scene.addObject(OBJReader.getModel3D("./src/models/Cube.obj", new Vector3D(-2, -3.5, -5), Color.CYAN,
                new Material(0.3,0.2,0.1,5,0.5,1.0)).scale(new Vector3D(2,2,2)).rotateY(-10));
        scene.addObject(OBJReader.getModel3D("./src/models/Cube.obj", new Vector3D(-2, -1.5, -5), Color.GREEN,
                new Material(0.7,0.9,0.0,35,0.0,0.0)).scale(new Vector3D(2,2,2)).rotateY(60));
        scene.addObject(OBJReader.getModel3D("./src/models/Cube.obj", new Vector3D(-2, 0.5, -5), Color.RED,
                new Material(0.4,0.7,0.1,200,0.0,0.0)).scale(new Vector3D(2,2,2)).rotateY(30));


        return scene;
    }

    public static Scene render01(){
        Scene scene = new Scene();
        scene.setCamera(new Camera(new Vector3D(0, 0, -5), 60, 60, 100, 100, 0.6, 50.0));
        scene.addLight(new PointLight(new Vector3D(0.0,4.5, 0.0), Color.WHITE, 0.09f));
//        scene.addLight(new PointLight(new Vector3D(0.0,4.5, -20.0), Color.WHITE, 0.01f));
//        scene.addLight(new PointLight(new Vector3D(0.0,4.5, -20.0), Color.WHITE, 0.01f));
//        scene.addLight(new PointLight(new Vector3D(0.0,10, 10.0), Color.BLACK, 0.2f));
        scene.addObject(OBJReader.getModel3D("./src/models/FLOOR1.obj", new Vector3D(0, -1.5, 0), Color.RED,
                new Material(0.3,0.3,0.6,96,0.0,0.0)).scale(new Vector3D(0.5,0.5,0.5)));
//        scene.addObject(OBJReader.getModel3D("./src/models/FLOOR1.obj", new Vector3D(0, -1.5, -5), Color.WHITE, Material.METAL).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/FLOOR1.obj", new Vector3D(0, 8, -5), Color.BLACK, new Material(0.7, 0.3, 0.8, 200, 0, 0)).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/FRONT1.obj", new Vector3D(0, -1.5, 10), Color.BLUE, new Material(0.7, 0.3, 0.8, 200, 0, 0)).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/FRONT1.obj", new Vector3D(0, -1.5, -5), Color.WHITE, new Material(0.7, 0.3, 0.8, 200, 0, 0)).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/RIGHT1.obj", new Vector3D(-1.3, -1.5, 10), Color.WHITE, new Material(0.7, 0.3, 0.8, 200, 0, 0)).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/LEFT1.obj", new Vector3D(1.3, -1.5, 10), Color.LIGHT_GRAY, new Material(0.7, 0.3, 0.8, 200, 0, 0)).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/render2/Alfil1.obj", new Vector3D(0, -1, -1), Color.WHITE,
                new Material(0.6,0.7,0.5,96,0.2,0.3)).scale(new Vector3D(0.7,0.7,0.7)));
        scene.addObject(OBJReader.getModel3D("./src/models/render2/Peon1.obj", new Vector3D(-1.5, -1.3, -2), Color.WHITE, new Material(0.3,0.7,0.0,96,0.6,1.3)).scale(new Vector3D(0.2,0.2,0.2)));
        scene.addObject(OBJReader.getModel3D("./src/models/render2/Caballo1.obj", new Vector3D(-0.5, -1.2, -3.8), new Color(2,84,100), new Material(0.6,0.7,0.5,96,0.2,0.3)).scale(new Vector3D(0.2,0.2,0.2)).rotateY(150));
        scene.addObject(OBJReader.getModel3D("./src/models/render2/Reina1.obj", new Vector3D(0.7, -1, 1), Color.BLUE, new Material(0.7, 0.3, 0.8, 200, 0, 0)).scale(new Vector3D(0.2,0.2,0.2)));
        scene.addObject(OBJReader.getModel3D("./src/models/render2/Rey1.obj", new Vector3D(-0.8, -1, 0.5), Color.RED, new Material(0.7, 0.3, 0.8, 200, 0, 0)).scale(new Vector3D(0.2,0.2,0.2)));
        scene.addObject(OBJReader.getModel3D("./src/models/render2/Torre1.obj", new Vector3D(1.5, -1.2, -2.5), Color.WHITE, new Material(0.6,0.7,1.0,96,0.0,0.0)).scale(new Vector3D(0.5,0.5,0.5)));
        return scene;
    }

    public static Scene render02(){
        Scene scene = new Scene();
        scene.setCamera(new Camera(new Vector3D(0.3, 0, -10), 60, 60, 1920, 1080, 0.6, 50.0));
        scene.addLight(new PointLight(new Vector3D(0.0,20, 0), Color.WHITE, 0.4f));
//        scene.addLight(new PointLight(new Vector3D(0.5,10, -3), new Color(255,0,0), 1f));
//        scene.addLight(new PointLight(new Vector3D(-7,10, -4), new Color(0,0,255), 1f));
//        scene.addLight(new PointLight(new Vector3D(10,20, -4), new Color(0,255,0), 1f));
//        scene.addLight(new PointLight(new Vector3D(0.0,0.7, -2), Color.YELLOW, 0.4f));
        scene.addObject(OBJReader.getModel3D("./src/models/FLOOR1.obj", new Vector3D(0, -8, 0), Color.WHITE,
                Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)).scale(new Vector3D(1,1,2.0)));
        scene.addObject(OBJReader.getModel3D("./src/models/FRONT1.obj", new Vector3D(0, -11.5, -24), Color.LIGHT_GRAY, new Material(0.3,0.3,0.6,96,0.0,0.0)).scale(new Vector3D(1,0.6,0.5)));

        scene.addObject(OBJReader.getModel3D("./src/models/FLOOR1.obj", new Vector3D(0, 30, -5), Color.LIGHT_GRAY, Material.RUBBER).scale(new Vector3D(1.5,0.5,1.5)));

        scene.addObject(OBJReader.getModel3D("./src/models/RIGHT1.obj", new Vector3D(-1.5, -10, 5), Color.LIGHT_GRAY, Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)));
        scene.addObject(OBJReader.getModel3D("./src/models/LEFT1.obj", new Vector3D(1.5, -10, 5), Color.LIGHT_GRAY, Material.RUBBER).scale(new Vector3D(0.5,0.5,0.5)));

        scene.addObject(OBJReader.getModel3D("./src/models/FRONT1.obj", new Vector3D(0, -11.5, 0), Color.LIGHT_GRAY, new Material(0.3,0.3,0.6,96,0.0,0.0)).scale(new Vector3D(1,0.6,0.5)));
//
        scene.addObject(OBJReader.getModel3D("./src/models/render2/Alfil1.obj", new Vector3D(0.5, -3.5, -3), Color.GREEN, new Material(0.6,0.7,0.5,96,0.2,0.3)).scale(new Vector3D(1.5,1.5,1.5)).rotateX(25));
        scene.addObject(OBJReader.getModel3D("./src/models/render3/Glass1.obj", new Vector3D(0, -5, -3), Color.WHITE,
                new Material(0.3,0.1,0.1,96,1.0,0.7)).scale(new Vector3D(1,1,1)));

        scene.addObject(OBJReader.getModel3D("./src/models/render2/Rey1.obj", new Vector3D(-5, -3.5, -4), Color.RED, Material.METAL).scale(new Vector3D(0.5,0.5,0.5)).rotateZ(25));
        scene.addObject(OBJReader.getModel3D("./src/models/render3/Glass1.obj", new Vector3D(-6, -5, -4), Color.WHITE,
                new Material(0.5,0.1,0.0,96,1.0,1.3)).scale(new Vector3D(1,1,1)));

        scene.addObject(OBJReader.getModel3D("./src/models/render2/Reina1.obj", new Vector3D(7, -3.5, -4), Color.BLUE, Material.METAL).scale(new Vector3D(0.5,0.5,0.5)).rotateZ(-25));
        scene.addObject(OBJReader.getModel3D("./src/models/render3/Glass1.obj", new Vector3D(6, -5, -4), Color.WHITE,
                new Material(0.5,0.1,0.0,96,1.0,1.5)).scale(new Vector3D(1,1,1)));

        return scene;
    }
}
