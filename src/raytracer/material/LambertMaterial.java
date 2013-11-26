package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

public class LambertMaterial extends Material{
	private final Color color;
	
	public LambertMaterial (final Color color){
		this.color = color;
	}
	@Override
	public Color colorFor(final Hit hit, final World world) {
		// TODO Auto-generated method stub
		return null;
	}

}