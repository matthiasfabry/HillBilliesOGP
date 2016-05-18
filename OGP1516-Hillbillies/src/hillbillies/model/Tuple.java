/**
 * 
 */
package hillbillies.model;

/**
 * Simple class representing a tuple of an object with an Integer.
 *
 * @author Matthias Fabry & Lukas Van Riel
 * @version 1.0
 *
 */
public class Tuple<C> implements Comparable<Tuple<C>> {

	// Constructor //

	public Tuple(C c, int v) {
		this.c = c;
		this.v = v;
	}

	// Fields //

	private final C c;
	private final int v;

	// Getters //

	public C getC() {
		return this.c;
	}
	public int getV() {
		return this.v;
	}
	
	// Overrides from Object //

	@Override
	public int hashCode() {
		return (this.getC().hashCode() * this.getV());
	}

	@Override
	public boolean equals(Object object) {
		if (object.getClass() != Tuple.class)
			return false;
		@SuppressWarnings("unchecked")
		Tuple<C> casted = (Tuple<C>) object;
		return this.getC().equals(casted.getC())
				&& this.getV() == (casted.getV());

	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Tuple<C> arg0) {
		return this.getV()-arg0.getV();
	}
}
