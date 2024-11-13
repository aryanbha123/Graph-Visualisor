import React, { useState } from 'react';
import './App.css';

// Taskbar component to manage adding nodes and edges
function Taskbar({ addNode, startEdge, endEdge }) {
  return (
    <div className="taskbar">
      <button onClick={addNode}>Add Node</button>
      <button onClick={startEdge}>Start Edge</button>
      <button onClick={endEdge}>End Edge</button>
    </div>
  );
}

// Canvas component to display nodes and edges
function Canvas({ nodes, edges, onNodeClick }) {
  return (
    <div className="canvas">
      {nodes.map((node, index) => (
        <div
          key={index}
          className="node"
          style={{ left: node.x, top: node.y }}
          onClick={() => onNodeClick(index)}
        >
          {index + 1}
        </div>
      ))}
      {edges.map((edge, index) => (
        <svg key={index} className="edge">
          <line
            x1={nodes[edge.start].x + 20}
            y1={nodes[edge.start].y + 20}
            x2={nodes[edge.end].x + 20}
            y2={nodes[edge.end].y + 20}
            stroke="black"
            strokeWidth="2"
          />
        </svg>
      ))}
    </div>
  );
}

// Adjacency Matrix component to display connections
function AdjacencyMatrix({ matrix }) {
  return (
    <div className="adjacency-matrix">
      <h3>Adjacency Matrix</h3>
      <table>
        <tbody>
          {matrix.map((row, rowIndex) => (
            <tr key={rowIndex}>
              {row.map((cell, cellIndex) => (
                <td key={cellIndex}>{cell}</td>
              ))}
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default function App() {
  const [nodes, setNodes] = useState([]);
  const [edges, setEdges] = useState([]);
  const [matrix, setMatrix] = useState([]);
  const [isConnecting, setIsConnecting] = useState(false);
  const [connectStart, setConnectStart] = useState(null);

  // Adds a new node and updates the adjacency matrix
  const addNode = () => {
    const x = Math.random() * 400;
    const y = Math.random() * 400;
    setNodes([...nodes, { x, y }]);
    
    // Expand the matrix with zeros for the new node
    const newMatrix = matrix.map(row => [...row, 0]);
    newMatrix.push(new Array(matrix.length + 1).fill(0));
    setMatrix(newMatrix);
  };

  const startEdge = () => {
    setIsConnecting(true);
  };

  const endEdge = () => {
    setIsConnecting(false);
    setConnectStart(null);
  };

  const handleNodeClick = (index) => {
    if (isConnecting && connectStart !== null && connectStart !== index) {
      // Complete the edge
      setEdges([...edges, { start: connectStart, end: index }]);
      
      // Update the adjacency matrix
      const updatedMatrix = [...matrix];
      updatedMatrix[connectStart][index] = 1;
      updatedMatrix[index][connectStart] = 1; // For undirected graph
      setMatrix(updatedMatrix);

      endEdge();
    } else {
      // Start connecting from this node
      setConnectStart(index);
    }
  };

  return (
    <div className="app">
      <Taskbar addNode={addNode} startEdge={startEdge} endEdge={endEdge} />
      <div className="content">
        <Canvas nodes={nodes} edges={edges} onNodeClick={handleNodeClick} />
        <AdjacencyMatrix matrix={matrix} />
      </div>
    </div>
  );
}
