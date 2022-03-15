package quantifiedElementPackage;
import domainObjects.ElementDomainObject;

/**
 * Class to hold a relationship between an element and how many atoms of it are
 * in one molecule of its containing compound
 * 
 * @author Mad, Ace, Joshua B
 *
 */
public class QuantifiedElement {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((element == null) ? 0 : element.hashCode());
		result = prime * result + quantityInCompound;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuantifiedElement other = (QuantifiedElement) obj;
		if (element == null) {
			if (other.element != null)
				return false;
		} else if (!element.equals(other.element))
			return false;
		if (quantityInCompound != other.quantityInCompound)
			return false;
		return true;
	}

	private ElementDomainObject element;
	private int quantityInCompound;

	/**
	 * Constructor
	 * 
	 * @param e the element
	 * @param q its quantity in its compound
	 */
	public QuantifiedElement(ElementDomainObject e, int q) {
		element = e;
		setQuantityInCompound(q);
	}

	public ElementDomainObject getElement() {
		return element;
	}

	public void setElement(ElementDomainObject element) {
		this.element = element;
	}

	public int getQuantityInCompound() {
		return quantityInCompound;
	}

	public void setQuantityInCompound(int quantityInCompound) {
		this.quantityInCompound = quantityInCompound;
	}

}