#include "graph.hpp"
using namespace std;

template<typename T>
class Graph {
    public:
    vector<vector<Vertex<T> > > adjList; // Adjacency List - will be used to form the Graph

    // Parameterised Constructor
    Graph<T>(vector<Edge<T> > const &edges, int n) { // Constructor takes a list of Edges & the number of nodes in the Graph
        adjList.resize(n); //Resizing the vector to hold n elements of type vector<Vertex>
        for (auto &edge: edges) {
            adjList[edge.src.node_id].push_back(edge.dest.node_id);
        } 
    }

    Graph<T>() {  // Default Constructor

    }
};

template<typename T>
bool isAcyclic(vector<Edge<T> > const &edges) {

    return true; //change
}   

template<typename T>
// Perform DFS on the graph and set the departure time of all vertices of the graph
int DFS(Graph<T> const &graph, int v, vector<bool> &discovered, vector<int> &departure, int &time) {
    // mark the current node as discovered
    discovered[v] = true;
 
    // do for every edge (v, u)
    for (Vertex<T> u: graph.adjList[v]) {
        // if u is not yet discovered
        if (!discovered[u.node_id]) {
            DFS(graph, u.node_id, discovered, departure, time);
        }
    }
 
    // ready to backtrack
    // set departure time of vertex v
    departure[v] = time++;
}
 
template<typename T>
// Returns true if given directed graph is DAG
bool isDAG(Graph<T> const &graph, int n) {
    // keep track of whether a vertex is discovered or not
    vector<bool> discovered(n);
 
    // keep track of the departure time of a vertex in DFS
    vector<int> departure(n);
 
    int time = 0;
 
    // Perform DFS traversal from all undiscovered vertices to visit all connected components of a graph
    for (int i = 0; i < n; i++) {
        if (!discovered[i]) {
            DFS(graph, i, discovered, departure, time);
        }
    }
 
    // check if the given directed graph is DAG or not
    for (int u = 0; u < n; u++) {
        // check if (u, v) forms a back-edge.
        for (Vertex<T> v: graph.adjList[u]) {
            // If the departure time of vertex v is greater than equal to the departure time of u, they form a back edge.
            // Note that departure[u] will be equal to departure[v] only if u = v, i.e., vertex contains an edge to itself
            if (departure[u] <= departure[v.node_id]) {
                return false;
            }
        }
    }
    // no back edges
    return true;
}

template<typename T>
int removeNode(Graph<T> g, Vertex<T> vertex) {
    return 0; //change
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