#include "vertex.hpp"

template<typename T>
class Vertex {
    public:
    T node_id;

    Vertex<T>(T node_id) {  // Parameterised Constructor
        this->node_id = node_id;
    }

    Vertex<T>() { // Default constructor

    }
};