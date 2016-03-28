/**
 * 
 */
package hillbillies.model;

import javafx.util.Pair;

/**
 *
 *
 * @author Matthias Fabry
 * @version 1.0
 *
 */
public class Tuple<C, V> {

	// Constructor //

	public Tuple(C c, V v) {
		this.c = c;
		this.v = v;
	}

	// Fields //

	private final C c;
	private final V v;

	// Getters //

	public C getC() {
		return this.c;
	}
	public V getV() {
		return this.v;
	}

	// Overrides from Object //

	@Override
	public int hashCode() {
		return (this.getC().hashCode() * this.getV().hashCode());
	}

	@Override
	public boolean equals(Object object) {
		if (object.getClass() != Tuple.class)
			return false;
		@SuppressWarnings("unchecked")
		Tuple<C, V> casted = (Tuple<C, V>) object;
		return this.getC().equals(casted.getC())
				&& this.getV().equals(casted.getV());

	}
}
