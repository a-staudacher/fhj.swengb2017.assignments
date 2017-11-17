package fhj.swengb.assignments.tree

import at.fhj.swengb.assignments.tree._
import org.scalatest.{Matchers, WordSpecLike}

import scala.util.Try

/**
  * Test fixtures for the tree assignment.
  *
  * For every working test you'll get 4 points.
  *
  */
class TreeSpec extends WordSpecLike with Matchers {

  import at.fhj.swengb.assignments.tree.Graph._

  private val rootNode: Tree[L2D] = Node(L2D(Pt2D(0.0, 0.0), Pt2D(100, 0.0), colorMap(0)))

  private val treeOfSize1: Branch[L2D] = Branch(
    rootNode,
    Branch(
      Node(L2D(Pt2D(100.0, 0.0), Pt2D(199.985, -1.745), colorMap(0))),
      Node(L2D(Pt2D(100.0, 0.0), Pt2D(199.985, 1.745), colorMap(0)))))

  private val pt0 = Pt2D(0, 0)

  "treeSize0" in {
    val t: Tree[L2D] = Graph.mkGraph(pt0, 0, 100, 0, 1, 0)
    assert(rootNode == t)
  }

  /**
    * tree is illegal since the depth is too high
    */
  "illegalTree" in {
    intercept[IllegalArgumentException] {
      Graph.mkGraph(pt0, 0, 100, 17, 1, 0)
    }
    //    assert(Try(Graph.mkGraph(pt0, 0, 100, 17, 1, 0)).isFailure)
  }

  "treeSize1" in {
    val t: Tree[L2D] = Graph.mkGraph(pt0, 0, 100, 1, 1, 1)
    assert(treeOfSize1 == t)
  }

  "treeSize2" in {
    val t: Tree[L2D] = Graph.mkGraph(pt0, 0, 100, 2, 1, 1)

    Branch(Node(L2D(Pt2D(0.0, 0.0), Pt2D(100.0, 0.0), colorMap(0))), Branch(Branch(Node(L2D(Pt2D(100.0, 0.0), Pt2D(199.985, -1.745), colorMap(0))), Branch(Node(L2D(Pt2D(199.985, -1.745), Pt2D(299.924, -5.235), colorMap(1))), Node(L2D(Pt2D(199.985, -1.745), Pt2D(299.985, -1.745), colorMap(1))))), Branch(Node(L2D(Pt2D(100.0, 0.0), Pt2D(199.985, 1.745), colorMap(0))), Branch(Node(L2D(Pt2D(199.985, 1.745), Pt2D(299.985, 1.745), colorMap(1))), Node(L2D(Pt2D(199.985, 1.745), Pt2D(299.924, 5.235), colorMap(1))))))) shouldEqual t

  }

  "testTraverse" in {
    val seqs: Seq[L2D] = Graph.traverse(treeOfSize1)(l2d => l2d)
    3 shouldEqual seqs.size

    val expected =
      List(
        L2D(Pt2D(0.0, 0.0), Pt2D(100.0, 0.0), colorMap(0)),
        L2D(Pt2D(100.0, 0.0), Pt2D(199.985, -1.745), colorMap(0)),
        L2D(Pt2D(100.0, 0.0), Pt2D(199.985, 1.745), colorMap(0)))
    expected shouldEqual seqs

    expected.foreach {
      l2d => assert(seqs.contains(l2d))
    }

  }

}
