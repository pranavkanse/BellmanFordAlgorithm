import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Node{
	String data;
	Node(String data){
		this.data=data;
	}
}
class NodeDistance{
	Node nodeSource;
	int distance;
	NodeDistance(Node nodeSource, int distance){
		this.nodeSource = nodeSource;
		this.distance = distance;
	}
}
class Edge{
	Node source;
	Node destination;
	int weight;
	Edge(Node src, Node dest, int wght){
		this.source = src;
		this.destination = dest;
		this.weight = wght;
	}
}
class Graph{
	ArrayList<Edge> graphEdgeList = new ArrayList<Edge>();

	public void ImplementBellmanFord(Graph graphObject, Node ndSource) {
		HashMap<Node,NodeDistance> hmap = new HashMap<Node,NodeDistance>();
		hmap.put(ndSource, new NodeDistance(null,0));
		//Set all distances as INFINITY from source node
		for(Edge element : graphObject.graphEdgeList) {
			if(!(hmap.containsKey(element.source)))
				hmap.put(element.source,new NodeDistance(null,Integer.MAX_VALUE));
			if(!(hmap.containsKey(element.destination)))
				hmap.put(element.destination,new NodeDistance(null,Integer.MAX_VALUE));
		}
		
		//Traverse N-1 Times and find and set minimum distance
		for(int i=0;i<hmap.size()-1;i++) {
			for(Edge edge : graphObject.graphEdgeList) {
				if ((hmap.get(edge.source).distance!=Integer.MAX_VALUE)&&((hmap.get(edge.source).distance + edge.weight) < hmap.get(edge.destination).distance)) {
					hmap.get(edge.destination).distance = hmap.get(edge.source).distance + edge.weight;
					hmap.get(edge.destination).nodeSource = edge.source;
				}
			}
		}
		
		//Traverse One more time If there is any change in distance, it means we have a Negative weighted cycle
		for(Edge edge : graphObject.graphEdgeList) {
			if ((hmap.get(edge.source).distance!=Integer.MAX_VALUE)&&((hmap.get(edge.source).distance + edge.weight) < hmap.get(edge.destination).distance)) {
				System.out.println("Graph has a negative Cycle");
				return;
			}
		}
		
		//Output Printing
		System.out.println("\nOriginal source from which distance of other nodes are calculated: " + ndSource.data + "\n");
		System.out.println(ndSource.data+ " distance from Original source = 0, ");
		
		//Printing all nodes and their distance from source node. Also printing the parent of that node
		Queue<Node> visitedNodes = new LinkedList<Node>();
		visitedNodes.add(ndSource);
		while (!(visitedNodes.isEmpty())) {
			Node temp = visitedNodes.remove();
			for (Map.Entry<Node, NodeDistance> entry : hmap.entrySet()) {
				if ((entry.getKey().data!=ndSource.data)&&(temp.data==entry.getValue().nodeSource.data)) {
					if (entry.getValue().distance!=Integer.MAX_VALUE)
						System.out.println(entry.getKey().data + " distance from Original source = " + entry.getValue().distance + " (its parent: " + entry.getValue().nodeSource.data + "), ");
					else
						System.out.println(entry.getKey().data + " distance from Original source = infinity");
					if (entry.getValue().nodeSource!=null)
						visitedNodes.add(entry.getKey());
				}
			}
		}
	}
}
public class BellmanFordImplementation {

	public static void main(String[] args) {
		//Node Creation
		Node ndA = new Node("A");
		Node ndB = new Node("B");
		Node ndC = new Node("C");
		Node ndD = new Node("D");
		Node ndE = new Node("E");
		Node ndF = new Node("F");
		Node ndG = new Node("G");
		Node ndH = new Node("H");
		Node ndI = new Node("I");
		Node ndJ = new Node("J");
		
		//Edge Creation
		Edge e1 = new Edge(ndA,ndB,6);
		Edge e2 = new Edge(ndA,ndD,7);
		Edge e3 = new Edge(ndB,ndD,8);
		Edge e4 = new Edge(ndB,ndC,5);
		Edge e5 = new Edge(ndC,ndB,-2);
		Edge e6 = new Edge(ndB,ndE,-4);
		Edge e7 = new Edge(ndD,ndC,-3);
		Edge e8 = new Edge(ndD,ndE,9);
		Edge e9 = new Edge(ndE,ndC,7);
		Edge e10 = new Edge(ndE,ndA,2);
		Edge e11 = new Edge(ndA,ndF,1);
		Edge e12 = new Edge(ndF,ndG,6);
		Edge e13 = new Edge(ndF,ndI,7);
		Edge e14 = new Edge(ndG,ndI,8);
		Edge e15 = new Edge(ndG,ndH,5);
		Edge e16 = new Edge(ndH,ndG,-2);
		Edge e17 = new Edge(ndG,ndJ,-4);
		Edge e18 = new Edge(ndI,ndH,-3);
		Edge e19 = new Edge(ndI,ndJ,9);
		Edge e20 = new Edge(ndJ,ndH,7);
		Edge e21 = new Edge(ndJ,ndF,2);
		
		//Make the list of edges
		Graph graphObj = new Graph();
		graphObj.graphEdgeList.add(e1);
		graphObj.graphEdgeList.add(e2);
		graphObj.graphEdgeList.add(e3);
		graphObj.graphEdgeList.add(e4);
		graphObj.graphEdgeList.add(e5);
		graphObj.graphEdgeList.add(e6);
		graphObj.graphEdgeList.add(e7);
		graphObj.graphEdgeList.add(e8);
		graphObj.graphEdgeList.add(e9);
		graphObj.graphEdgeList.add(e10);
		graphObj.graphEdgeList.add(e11);
		graphObj.graphEdgeList.add(e12);
		graphObj.graphEdgeList.add(e13);
		graphObj.graphEdgeList.add(e14);
		graphObj.graphEdgeList.add(e15);
		graphObj.graphEdgeList.add(e16);
		graphObj.graphEdgeList.add(e17);
		graphObj.graphEdgeList.add(e18);
		graphObj.graphEdgeList.add(e19);
		graphObj.graphEdgeList.add(e20);
		graphObj.graphEdgeList.add(e21);
		
		//Function to implement Bellman Ford Algorithm
		graphObj.ImplementBellmanFord(graphObj,ndA);
	}

}
