package mylistIterator;

/**
 * The ListIterable interface provides just one method
 * for retrieving an iterator.
 *
 * @version     1.0 24 September 2018
 * @author      ilay
 */
public interface ListIterable {
    /**
     *
     * @return an object of the ListIterator that can get round a list and
     *         use specified methods.
     */
    ListIterator listIterator();


}
