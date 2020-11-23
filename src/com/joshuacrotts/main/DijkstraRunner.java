package com.joshuacrotts.main;
import com.joshuacrotts.dijkstra.Edge;
import com.joshuacrotts.dijkstra.GUIDijkstra;
import com.joshuacrotts.dijkstra.GUIEdge;
import com.joshuacrotts.dijkstra.GUIVertex;
import com.joshuacrotts.dijkstra.Vertex;
import com.theta.platform.ThetaGraphicalApplication;

public class DijkstraRunner extends ThetaGraphicalApplication {

  /** */
  private static final int WIDTH = 600;
  
  /** */
  private static final int HEIGHT = 600;
  
  /** */
  private static final String TITLE = "Dijkstra Viewer";
  
  /** */
  private final GUIDijkstra DIJKSTRA_GRAPH;
  
  /** */
  private final VertexPlacementCommand VERTEX_PLACEMENT_COMMAND;
  
  /** */
  private final VertexSelectCommand VERTEX_SELECT_COMMAND;
  
  /** */
  private final VertexDeleteCommand VERTEX_DELETE_COMMAND;
  
  /** */
  private final EdgeSelectCommand EDGE_SELECT_COMMAND;
  
  /** */
  private final VertexModifier VERTEX_MODIFIER;
  
  /** */
  private final DijkstraSolverCommand DIJKSTRA_SOLVER_COMMAND;
  
  public DijkstraRunner() {
    super(WIDTH, HEIGHT, "Dijkstra Viewer");
    this.DIJKSTRA_GRAPH = new GUIDijkstra();
    this.VERTEX_PLACEMENT_COMMAND = new VertexPlacementCommand(this);
    this.VERTEX_SELECT_COMMAND = new VertexSelectCommand(this);
    this.VERTEX_DELETE_COMMAND = new VertexDeleteCommand(this);
    this.EDGE_SELECT_COMMAND = new EdgeSelectCommand(this);
    this.VERTEX_MODIFIER = new VertexModifier(this);
    this.DIJKSTRA_SOLVER_COMMAND = new DijkstraSolverCommand(this);
  }

  @Override
  public void update() {
    this.VERTEX_MODIFIER.update();
  }
  
  @Override
  public void render() {
    for (Vertex v : this.DIJKSTRA_GRAPH.getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      gv.render();
      for (Edge e : gv.getAdjacencyList()) {
        GUIEdge ge = (GUIEdge) e;
        ge.render();
      }
    }
  }
  
  public void unselectAll() {
    for (Vertex v : this.DIJKSTRA_GRAPH.getVertices()) {
      GUIVertex gv = (GUIVertex) v;
      gv.setSelected(false);
    }
  }
  
  public GUIDijkstra getGraph() {
    return this.DIJKSTRA_GRAPH;
  }
  
  public static void main(String[] args) {
    new DijkstraRunner();
  }
}
