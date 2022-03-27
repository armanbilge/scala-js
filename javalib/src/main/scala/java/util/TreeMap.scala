/*
 * Scala.js (https://www.scala-js.org/)
 *
 * Copyright EPFL.
 *
 * Licensed under Apache License 2.0
 * (https://www.apache.org/licenses/LICENSE-2.0).
 *
 * See the NOTICE file distributed with this work for
 * additional information regarding copyright ownership.
 */

package java.util

import java.util.{RedBlackTree => RB}

class TreeMap[K, V] private (tree: RB.Tree[K, V])(
    implicit comp: Comparator[_ >: K])
    extends AbstractMap[K, V] with NavigableMap[K, V] with Cloneable with Serializable {

  // Members declared in java.util.AbstractMap
  def entrySet(): Set[Map.Entry[K, V]] = throw new Exception

  // Members declared in java.util.NavigableMap
  def ceilingEntry(key: K): Map.Entry[K, V] = RB.minNodeAfter(tree, key, RB.InclusiveBound)

  def ceilingKey(key: K): K = RB.minKeyAfter(tree, key, RB.InclusiveBound)

  def descendingKeySet(): java.util.NavigableSet[K] = throw new Exception

  def descendingMap(): java.util.NavigableMap[K, V] = throw new Exception

  def firstEntry(): Map.Entry[K, V] = {
    if (isEmpty())
      throw new NoSuchElementException()
    RB.minNode(tree)
  }

  def floorEntry(key: K): Map.Entry[K, V] =
    RB.maxNodeBefore(tree, key, RB.InclusiveBound)

  def floorKey(key: K): K =
    RB.maxKeyBefore(tree, key, RB.InclusiveBound)

  def headMap(toKey: K): java.util.SortedMap[K, V] = throw new Exception

  def headMap(toKey: K, inclusive: Boolean): java.util.NavigableMap[K, V] = throw new Exception

  def higherEntry(key: K): Map.Entry[K, V] = RB.minNodeAfter(tree, key, RB.ExclusiveBound)

  def higherKey(key: K): K = RB.minKeyAfter(tree, key, RB.ExclusiveBound)

  def lastEntry(): Map.Entry[K, V] = {
    if (isEmpty())
      throw new NoSuchElementException()
    RB.maxNode(tree)
  }

  def lowerEntry(key: K): Map.Entry[K, V] = RB.maxNodeBefore(tree, key, RB.ExclusiveBound)

  def lowerKey(key: K): K = RB.maxKeyBefore(tree, key, RB.ExclusiveBound)

  def navigableKeySet(): java.util.NavigableSet[K] = throw new Exception

  def pollFirstEntry(): Map.Entry[K, V] = {
    val node = RB.minNode(tree)
    if (node ne null) {
      RB.deleteNode(tree, node)
      node
    } else {
      null.asInstanceOf[Map.Entry[K, V]]
    }
  }

  def pollLastEntry(): Map.Entry[K, V] = {
    val node = RB.maxNode(tree)
    if (node ne null) {
      RB.deleteNode(tree, node)
      node
    } else {
      null.asInstanceOf[Map.Entry[K, V]]
    }
  }

  def subMap(fromKey: K, toKey: K): java.util.SortedMap[K, V] = throw new Exception

  def subMap(fromKey: K, fromInclusive: Boolean, toKey: K, toInclusive: Boolean): java.util.NavigableMap[K, V] = throw new Exception

  def tailMap(fromKey: K): java.util.SortedMap[K, V] = throw new Exception

  def tailMap(toKey: K, inclusive: Boolean): java.util.NavigableMap[K, V] = throw new Exception

  // Members declared in java.util.SortedMap
  def comparator(): Comparator[_ >: K] = NaturalComparator.unselect(comp)

  def firstKey(): K = {
    if (isEmpty())
      throw new NoSuchElementException()
    RB.minKey(tree)
  }

  def lastKey(): K = {
    if (isEmpty())
      throw new NoSuchElementException()
    RB.maxKey(tree)
  }

}
