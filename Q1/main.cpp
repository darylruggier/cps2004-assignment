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
    Edge<int> *e3 = new Edge<int>(*v3, *v1);
    Edge<int> *e4 = new Edge<int>(*v5, *v3);


    vector<Edge<int> > edgeList; // List of edges
    edgeList.push_back(*e1);
    edgeList.push_back(*e2);
    edgeList.push_back(*e3);
    edgeList.push_back(*e4);

    int n = 5; // Number of node
    
    // Constructing the graph 
    Graph<int> g(edgeList, n);

    if (!isDAG(g, n)) {
        cout << "Error: The graph is not acyclic." << endl;
        return 0;
    }

    // Printing the graph's adjacency list
    printGraph(g, n);

    return 0;
}