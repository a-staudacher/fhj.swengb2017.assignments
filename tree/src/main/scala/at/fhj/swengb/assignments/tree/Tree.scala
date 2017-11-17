package at.fhj.swengb.assignments.tree

/**
  * A simple tree datastructure which can contain a T as payload.
  *
  * @tparam T
  */
sealed trait Tree[+T]

case class Branch[T](left: Tree[T], right: Tree[T]) extends Tree[T]

case class Node[T](value: T) extends Tree[T]

