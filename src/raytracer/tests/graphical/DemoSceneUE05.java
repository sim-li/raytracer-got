package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Node;
import raytracer.geometry.ShapeFromFile;
import raytracer.geometry.Sphere;
import raytracer.light.PointLight;
import raytracer.material.LambertMaterial;
import raytracer.material.Material;
import raytracer.material.PhongMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.texture.SingleColorTexture;
import raytracer.ui.ShowImage;

/**
* Generates and displays <code>Renderer</code> objects for demo scenes as demanded in task 6.
* 
* @author Maxim Novichkov
* @author Sebastian Dass&eacute;
*
*/
public final class DemoSceneUE05 {

	/**
	 * Shows all the demo scenes in individual <code>JFrame</code> windows.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		final Renderer[] tracers = new Renderer[]{
//				smartieScene(), 
//				boxScene(), 
				teddyScene(), 
//				bunnyScene()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}

	private static Renderer doubleSlashScene() {
		final String path = "models/cube-v-vn.obj";
		
		final Material material = new LambertMaterial(new Color(1, 1, 1));
		
		final World world = Factory.buildWorld(new double[][]{{0, 0, 0}, {0, 0, 0}}, Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][]{
				{2.5, 2.5, 2.5}, {-1, -1, -1}, {0, 1, 0}, {Math.PI / 4.0}});
		world.addElements(
				new Node(new ShapeFromFile(path, material), new Transform())
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(3, 3, 3), false));
		return new Renderer(world, camera);
	}

	
	/**
	 * Generates figure 1 as shown in the task sheet.
	 * 
	 * @return The <code>Renderer</code> generated by the test method.
	 */
	private static Renderer smartieScene() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(
				new Node(
					new Sphere(new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)), 
					new Transform()
						.rotateY(Math.PI/0.2)
						.rotateZ(Math.PI/0.9)
						.rotateX(Math.PI/1.15)
						.scale(2.0, 0.5, 2.0)
				)
		);
		
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4), false));
		return new Renderer(world, camera, 10);
	}

	/**
	 * Generates figure 2 as shown in the task sheet.
	 * 
	 * @return The <code>Renderer</code> generated by the test method.
	 */
	private static Renderer boxScene() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(
				new Node(
					new AxisAlignedBox(
							new LambertMaterial(
									new SingleColorTexture(
											new Color(1, 1, 0)
									)
							)
					), 
					new Transform()
						.rotateX(Math.PI/1.17)
						.rotateY(Math.PI/0.2) 
						.rotateZ(Math.PI/1.15) 
						.scale(1.0, 0.26, 3.7)
				)
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4), false));
		return new Renderer(world, camera, 10);
	}

	/**
	 * Generates figure 3 as shown in the task sheet.
	 * 
	 * @return The <code>Renderer</code> generated by the test method.
	 */
	private static Renderer teddyScene() {
		final String path = "models/teddy.obj";
		final Material material = new LambertMaterial(
				new SingleColorTexture(
						new Color(1, 1, 1)
				)
		);
		
		final World world = new World(
				new Color(0, 0, 0),
				new Color(0, 0, 0),
				Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG
	    );
		
		final Camera camera = new PerspectiveCamera(
				new Point3(2.5, 2.5, 2.5),
				new Vector3(-1, -1, -1),
				new Vector3(0, 1, 0),
				Math.PI / 4.0
		);
		
		world.addElements(
				new Node(new ShapeFromFile(path, material), new Transform())
		);
		world.addLight(
				new PointLight(new Color(1, 1, 1),
				new Point3(3, 3, 3),
				false)
		);
		return new Renderer(world, camera);
	}

	/**
	 * Generates figure 4 as shown in the task sheet.
	 * 
	 * @return The <code>Renderer</code> generated by the test method.
	 */
	private static Renderer bunnyScene() {
		final String path = "models/bunny.obj";
		final Material material = new LambertMaterial(new Color(1, 1, 1));
		
		final World world = Factory.buildWorld(new double[][]{{0, 0, 0}, {0, 0, 0}}, Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][]{
				{12.5, 12.5, 12.5}, {-1, -1, -1}, {0, 1, 0}, {Math.PI / 4.0}});
		world.addElements(
				new Node(new ShapeFromFile(path, material), new Transform()
						.translate(0, -3, 0)
//						.scale(0.5, 0.5, 0.5)
						)
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(12.5, 12.5, 12.5), false));
		return new Renderer(world, camera);
	}
}
