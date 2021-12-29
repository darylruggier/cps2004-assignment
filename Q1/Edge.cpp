#include "edge.hpp"
using namespace std;

template<typename T>
class Edge {
    public:
    Vertex<T> src, dest;

    Edge(Vertex<T> src, Vertex<T> dest) {  // Parameterised Constructor
        this->src = Vertex(src);
        this->dest = Vertex(dest);
    }

    Edge() {  // Default Constructor

    }
};