package at.fhj.swengb.protobuf

import java.nio.file.{Files, Path, Paths}

import at.fhj.swengb.protobuf.Example.Helloworld

object ProtobufExample {

  def main(args: Array[String]): Unit = {

    val h: Helloworld =
      Helloworld.newBuilder()
        .setHello("test")
        .setTest(1)
        .build()

    // writes datastructure to disc

    val target = Paths.get("target/representation.bin")

    writeToDisc(h, target)

    val hIn: Helloworld = readFromDisc(target)

    println("saved hello: " + hIn.getHello)
    println("saved hello: " + hIn.getTest)

  }

  private def readFromDisc(target: Path) = {
    val hIn: Helloworld = Helloworld.parseFrom(Files.newInputStream(target))
    hIn
  }

  private def writeToDisc(h: Helloworld, target: Path) = {
    h.writeTo(Files.newOutputStream(target))
    println("wrote " + target.toAbsolutePath.toString)
  }
}
