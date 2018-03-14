import scala.util.Random

class Network(var hiddenLayers: Vector[Vector[Neuron]], var outputLayer: Vector[Neuron],
              inputWidth: Int) {

  //Creates a network with the specified number of hidden layers + input/output width
  def this(layers: Int, neurons: Int, inputWidth: Int, outputWidth: Int) {
    this(createLayer(neurons, inputWidth)
            ++ (1 until layers).map(_ => createLayer(neurons, neurons)),
      createLayer(outputWidth, neurons),
      inputWidth)

    if (layers <= 0) {
      throw new IllegalArgumentException("Cannot create a network with 0 or less layers")
    }

    if (neurons <= 0) {
      throw new IllegalArgumentException("Layers must have a positive number of neurons")
    }

    if (inputWidth <= 0) {
      throw new IllegalArgumentException("Input vectors must have a positive lenth")
    }

    if (outputWidth <= 0) {
      throw new IllegalArgumentException("Layers must have a positive number of neurons")
    }
  }

  //Creates a layer with random weights, with values initialized to 0
  def createLayer(neurons: Int, previous: Int): Vector[Neuron] = {
    val rand = new Random(System.currentTimeMillis())

    (1 to neurons)
            .map(_ => new Neuron((1 to previous)
                    .map(_ => rand.nextDouble() * 2 - 1)
                    .toVector, 0))
            .toVector
  }

  def train(data: Vector[Vector[Int]], labels: Vector[Int], epochs: Int): Unit = {
    for(epoch <- 0 to epochs){

    }
  }

  //Passes the specified input into the network
  //Modifies the values of each neuron accordingly
  //Throws an exception if the input vector is the wrong size
  def propogate(input: Vector[Double]): Unit = {

    hiddenLayers.head.foreach(_.evaluate(input))

    for (i <- 1 until hiddenLayers.size) {
      hiddenLayers(i).foreach(_.evaluate(hiddenLayers(i - 1).map(_.value)))
    }

    outputLayer.foreach(_.evaluate(hiddenLayers.last.map(_.value)))

  }
}