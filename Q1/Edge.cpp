#include "edge.hpp"
using namespace std;

template<typename T>
class Edge {
    public:
    Vertex<T> src, dest;

    Edge<T>(Vertex<T> src, Vertex<T> dest) {  // Parameterised Constructor
        this->src = Vertex<T>(src);
        this->dest = Vertex<T>(dest);
    }

    Edge<T>() {  // Default Constructor

    }
};