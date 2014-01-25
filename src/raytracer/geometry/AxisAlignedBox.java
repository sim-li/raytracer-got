package raytracer.geometry;

import java.util.LinkedList;

import raytracer.Constants;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Point3;
import raytracer.math.Transform;

/**
 * This immutable class represents an axis aligned box in three-dimensional space. It is defined through its <em>low 
 * bottom far point</em> (lbf) and its <em>right upper near point</em> (run).
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBox extends Geometry {
	
	/**
	 * The low bottom far point of this <code>AxisAlignedBox</code>.
	 * Is part of the left, the back and the bottom plane.
	 */
	private static final Point3 lbf = new Point3(-0.5, -0.5, -0.5);
	/**
	 * The right upper near point of this <code>AxisAlignedBox</code>.
	 * Is part of the top, the front and the right plane.
	 */
	private static final Point3 run = new Point3(0.5, 0.5, 0.5);

	private final Node right = new Node(new Plane(material),
			new Transform().translate(run).rotateZ( -Math.PI/2.0));
	 
	private final Node top =  new Node(new Plane(material),
			new Transform().translate(run));
	
	private final Node front =  new Node(new Plane(material),
			new Transform().translate(run).rotateZ( Math.PI)
			.rotateX(Math.PI / 2.0));
	
	private final Node left =  new Node(new Plane(material),
			new Transform().translate(lbf).rotateZ( Math.PI/2.0));
	
	private final Node bottom =  new Node(new Plane(material),
			new Transform().translate(lbf).rotateX(Math.PI));
	
	
	private final Node back =  new Node(new Plane(material),
			new Transform().translate(lbf).rotateZ(Math.PI)
			.rotateX(-Math.PI / 2.0));
	
	/**
	 * Constructs a new <code>AxisAlignedBox</code> with the specified parameters.
	 * 
	 * @param material	The material of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 */
	public AxisAlignedBox(final Material material) {
		super(material);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		final LinkedList<Hit> hits = new LinkedList<Hit>();
		final LinkedList<Hit> hitsOnPlane = new LinkedList<Hit>();
		final Hit rightHit = right.hit(ray);
		final Hit leftHit = left.hit(ray);
		if (rightHit != null) {
			hitsOnPlane.add(rightHit);
		}
		if (leftHit != null) {
			hitsOnPlane.add(leftHit);
		}
		for (Hit hit : hitsOnPlane) {
			Point3 p = ray.at(hit.t);
			 if( p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
				 hits.add(hit);
			 }
		}
		hitsOnPlane.clear();
		
		final Hit topHit = top.hit(ray);
		final Hit bottomHit = bottom.hit(ray);
		if (topHit != null) {
			hitsOnPlane.add(topHit);
		}
		if (bottomHit != null) {
			hitsOnPlane.add(bottomHit);
		}
		for (Hit hit : hitsOnPlane) {
			Point3 p = ray.at(hit.t);
			 if( p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z ) {
				 hits.add(hit);
			 }
		}
		hitsOnPlane.clear();
		
		final Hit frontHit = front.hit(ray);
		final Hit backHit = back.hit(ray);
		if (frontHit != null) {
			hitsOnPlane.add(frontHit);
		}
		if (backHit != null) {
			hitsOnPlane.add(backHit);
		}
		for (Hit hit : hitsOnPlane) {
			Point3 p = ray.at(hit.t);
			 if( p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y ) {
				 hits.add(hit);
			 }
		}
		
		Hit nearestHit = null;
		//---- alternativ zu Zeile 139-141:
//		double t = Double.MAX_VALUE;
//		for (Hit hit : hits) {
//			if (hit.t > Constants.EPSILON && hit.t - t < Constants.EPSILON) {
		double t = -1;
		for (Hit hit : hits) {
			if ((hit.t < t || t == -1) && hit.t > Constants.EPSILON) {
				t = hit.t;
				nearestHit = hit;
			}
		}
		return nearestHit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lbf == null) ? 0 : lbf.hashCode());
		result = prime * result + ((run == null) ? 0 : run.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AxisAlignedBox other = (AxisAlignedBox) obj;
		if (lbf == null) {
			if (other.lbf != null)
				return false;
		} else if (!lbf.equals(other.lbf))
			return false;
		if (run == null) {
			if (other.run != null)
				return false;
		} else if (!run.equals(other.run))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\tlbf = " + lbf + ",\n" 
								+ "\trun = " + run + "]";
	}
}
