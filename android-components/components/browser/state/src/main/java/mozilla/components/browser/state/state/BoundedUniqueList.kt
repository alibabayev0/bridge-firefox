package mozilla.components.browser.state.state

import java.util.LinkedList

/**
 * A collection that maintains a list of elements with a fixed maximum size.
 * It ensures that elements in the list are unique and maintains the insertion order.
 * When the maximum size is reached, the oldest element is removed upon the addition of a new one.
 *
 * @param T The type of elements held in this collection.
 * @param capacity The fixed maximum size of the list.
 */
class BoundedUniqueList<T>(private val capacity: Int) : AbstractList<T>() {
    private val list = LinkedList<T>()

    /**
     * Returns the number of elements in this list.
     */
    override val size: Int get() = list.size

    /**
     * Adds a new element to the list. If the element already exists, it is moved to the end of the list.
     * If adding a new element exceeds the list's capacity, the oldest element is removed and returned.
     *
     * @param element The element to be added to the list.
     * @return The element that was removed from the list due to the addition if the list was at its capacity,
     *         null otherwise.
     */
    fun add(element: T): T? {
        var removedId: T? = null
        if (list.contains(element)) {
            list.remove(element)
        }
        if (list.size == capacity) {
            removedId = list.removeFirst()
        }
        list.add(element)
        return removedId
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param index The index of the element to return.
     * @return The element at the specified index in this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    override fun get(index: Int): T = list[index]

    /**
     * Returns a standard list containing all elements in this collection.
     *
     * @return A list containing all elements in this collection.
     */
    fun toList(): List<T> = list.toList()
}