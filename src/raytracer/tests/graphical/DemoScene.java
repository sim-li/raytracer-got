package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Raytracer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.light.DirectionalLight;
import raytracer.light.PointLight;
import raytracer.light.SpotLight;
import raytracer.material.LambertMaterial;
import raytracer.material.PhongMaterial;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.ui.ShowImage;

/**
 * Generates and displays <code>Raytracer</code> objects for demo scenes as demanded in task 3.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public final class DemoScene {
	
	/**
	 * Shows all the demo scenes in individual <code>JFrame</code> windows.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final Raytracer[] tracers = new Raytracer[]{
			scene1(), 
			scene2(), 
			scene3(), 
			scene4(), 
			scene5(), 
			scene6(), 
			scene7()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}
	
	/**
	 * Generates figure 3 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Raytracer scene1() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new SingleColorMaterial(new Color(1, 0, 0))),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new SingleColorMaterial(new Color(0, 1, 0))),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new SingleColorMaterial(new Color(0, 0, 1))), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new SingleColorMaterial(new Color(1, 1, 0)))
			}
		);
		return new Raytracer(world, camera);
	}
	
	/**
	 * Generates figure 4 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Raytracer scene2() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.0, 0.0, 0.0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new LambertMaterial(new Color(1, 0, 0))),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new LambertMaterial(new Color(0, 1, 0))),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new LambertMaterial(new Color(0, 0, 1))), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new LambertMaterial(new Color(1, 1, 0)))
			}
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4)));
		return new Raytracer(world, camera);
	}
	
	/**
	 * Generates figure 5 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Raytracer scene3() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.0, 0.0, 0.0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64)), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64))
			}
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4)));
		return new Raytracer(world, camera);
	}
	
	/**
	 * Generates figure 6 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	private static Raytracer scene4() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.0, 0.0, 0.0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64)), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64))
			}
		);
		world.addLight(new DirectionalLight(new Color(1, 1, 1), new Vector3(-1, -1, -1)));
		return new Raytracer(world, camera);
	}
	
	/**
	 * Generates figure 7 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	private static Raytracer scene5() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.0, 0.0, 0.0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64)), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64))
			}
		);
		world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI / 14.0));
		return new Raytracer(world, camera);
	}
	
	/**
	 * Generates figure 8 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	private static Raytracer scene6() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.25, 0.25, 0.25} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new PhongMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64)), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new PhongMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 64))
			}
		);
		world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI / 14.0));
		return new Raytracer(world, camera);
	}
	
	/**
	 * Generates our own scene with multiple light sources of different kinds.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	private static Raytracer scene7() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 1, 1, 4 }, { -1, -1, -4 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
				Factory.buildPlane(new double[][] { 
						{ -3, 0, 0 }, { -3, -0.5, -1} }, new PhongMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 64)),
						
				Factory.buildSphere(new double[][] { 
						{ 1.5, 0, 0 }, { 0.5 } }, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, 0 }, { 0.25 } }, new PhongMaterial(new Color(0, 1, 1), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, -5 }, { 1 } }, new PhongMaterial(new Color(1, 1, 0.2), new Color(1, 1, 1), 64)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ 0, 0, -5 }, { 0.5, 0.5, 5 } }, new PhongMaterial(new Color(0.3, 1, 0.3), new Color(1, 1, 1), 64)),
			}
		);
		world.addLight(new PointLight(new Color(0.4, 0.2, 0.2), new Point3(4, 4, 4)));
		world.addLight(new PointLight(new Color(0.1, 0.1, 0.1), new Point3(-4, 4, 4)));
		world.addLight(new DirectionalLight(new Color(0.1, 0.1, 0.1), new Vector3(-1, -1, -1)));
		world.addLight(new SpotLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, 4), new Vector3(-1, -1, -4), Math.PI / 7.0));
		world.addLight(new SpotLight(new Color(0.1, 0.1, 0.1), new Point3(1, 1, 1), new Vector3(-1, -1, -1), Math.PI / 7.0));
		return new Raytracer(world, camera);
	}
}
