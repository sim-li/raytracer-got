package raytracer.material;

import raytracer.Color;
import raytracer.Ray;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public class ReflectiveMaterial extends Material{	
	private final Color diffuse;
	private final Color specular;
	private final int exponent;
	private final Color reflection;
	
	public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection){
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
		this.reflection = reflection;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {

		// Formula: c = cd * ca  +  cd * cl * max(0, <n, l>)  +  cs * cl * pow(max(0, <e, rl>), p)  +  cr * fr[pr, rd]
		final Normal3 n = hit.normal;
		final Ray ray = hit.ray;
		final Point3 p = ray.at(hit.t);
		final Vector3 e = ray.d.mul(-1).normalized();
		Color c = diffuse.mul(world.ambientLight);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			if (light.illuminates(p, world)) {
				final Vector3 l = light.directionFrom(p);
				final Vector3 rl = l.reflectedOn(n);
				final double f1 = Math.max(0, n.dot(l));
				final double f2 = Math.max(0, e.dot(rl));
				final Color s1 = diffuse.mul(light.color).mul(f1);
				final Color s2 = specular.mul(light.color).mul(Math.pow(f2, exponent));
				c = c.add(s1).add(s2); 
			}
		}
		final Vector3 rd = ray.d.mul(-1).reflectedOn(n);
		final Color s3 = reflection.mul(tracer.trace(new Ray(p, rd), world));
		return c.add(s3);
	}

}
