package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * 
 * 
 * @author 
 *
 */
public class LambertMaterial extends Material{
	/**
	 * 
	 */
	private final Color color;
	
	/**
	 * 
	 * 
	 * @param color
	 */
	public LambertMaterial (final Color color){
		this.color = color;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world) {
		
		// c = cr[ca + cl * , max(0, n.dot(l))]
		Color c = color; // TODO color mit Ambientlight multiplizieren
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			final Normal3 n = hit.normal;
			final Point3 p = hit.ray.at(hit.t);
			final Vector3 l = light.directionFrom(p);
			c = c.add(color.mul(light.color.mul(Math.max(0, n.dot(l)))));
		}
		return null;
	}
}
