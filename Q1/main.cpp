using namespace std;
#include "graph.hpp"
#include "graph.cpp"


int main() {

    // Setting the Vertices
    Vertex<int> *v1 = new Vertex<int>(0);
    Vertex<int> *v2 = new Vertex<int>(1);
    Vertex<int> *v3 = new Vertex<int>(2);
    Vertex<int> *v4 = new Vertex<int>(3);
    Vertex<int> *v5 = new Vertex<int>(4);

    // Creating 4 edges with the previously set Vertices
    Edge<int> *e1 = new Edge<int>(*v1, *v2);
    Edge<int> *e2 = new Edge<int>(*v2, *v3);
    Edge<int> *e3 = new Edge<int>(*v3, *v4);
    Edge<int> *e4 = new Edge<int>(*v4, *v5);


    vector<Edge<int> > edgeList = {e1, e2, e3, e4}; // List of edges

    int n = 4; // Number of node
    
    // Constructing the graph 
    Graph<int> g(edgeList, n);

    // Printing the graph's adjacency list
    printGraph(g, n);

    return 0;
}