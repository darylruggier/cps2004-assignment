#include "graph.hpp"
using namespace std;

template<typename T>
class Graph {
    public:
    vector<vector<Vertex<T>>> adjList; // Adjacency List - will be used to form the Graph

    // Parameterised Constructor
    Graph(vector<Edge<T>> const &edges, int n) { // Constructor takes a list of Edges & the number of nodes in the Graph
        adjList.resize(n); //Resizing the vector to hold n elements of type vector<Vertex>
        if (isAcyclic(edges)) {
            //Adding the edges to the graph if the edges are acyclic
            for (auto &edge: edges) {
                adjList[edge.src.node_id].push_back(edge.dest.node_id);
            } 
        } else cout << "Error: Graph cannot contain cycles.\n";
    }

    Graph() {  // Default Constructor

    }
};

template<typename T>
bool isAcyclic(vector<Edge<T>> const &edges) {
    
}   

template<typename T>
int removeNode(Graph<T> g, Vertex<T> vertex) {
        
}

template<typename T>
int getEdges(Graph<T> g) { // Returns the number of edges of a given Graph
    return g.adjList.size();
}

template<typename T>
void printGraph(Graph<T> const &graph, int n) {
    for (int i = 0; i < n; i++) {
        cout << i << " --> ";

        for (Vertex<T> v: graph.adjList[i]) {
            cout << v.node_id << " ";
        }
        cout << endl;
    }
}