const express = require('express');
const { exec } = require('child_process');
const path = require('path');
const cors = require('cors')

const app = express();
const PORT = 3001;

// Middleware to parse JSON bodies
app.use(express.json());
app.use(cors());


// Endpoint to run a prims
app.get('/prims', (req, res) => {

    //\\    0 1 0 1,1 0 1 0,
       const { graph } = req.query;
    console.log(graph)
    exec(`java Prims.java ${graph}`, (error, stdout, stderr) => {
        if (stderr) {
            console.error(`Error executing command: ${stderr}`);
            return res.status(500).json({ error: stderr });
        }
        console.log(stdout);
        let output = stdout.trim().split(' ').map((i) => parseInt(i));
        console.log(output); // This will print the array of integers
        res.json({
            message: 'Command executed successfully',
            stdout: output,
            stderr: stderr.trim(),
        });
    });
});

app.get('/dfs', (req, res) => {

    const { graph } = req.query;
    console.log(graph);
    exec(`java DFS.java ${graph}`, (error, stdout, stderr) => {
        if (error) {
            console.error(`Error executing command: ${error.message}`);
            return res.status(500).json({ error: error.message });
        }
        let output = stdout.trim().split(' ').map((i) => parseInt(i));
        console.log(output); // This will print the array of integers
        res.json({
            message: 'Command executed successfully',
            stdout: output,
            stderr: stderr.trim(),
        });
    });
});
app.get('/bfs', (req, res) => {

    const { graph } = req.query;
    console.log(graph)
    exec(`java BFS.java ${graph}`, (error, stdout, stderr) => {
        if (error) {
            console.error(`Error executing command: ${error.message}`);
            return res.status(500).json({ error: error.message });
        }
        console.log(stdout);
        let output = stdout.trim().split(' ').map((i) => parseInt(i));
        console.log(output); // This will print the array of integers
        res.json({
            message: 'Command executed successfully',
            stdout: output,
            stderr: stderr.trim(),
        });
    });
});
app.get('/dijkstra', (req, res) => {

    const { graph, source, destination } = req.query;
    console.log(graph, source)
    console.log("java Dijkstra " + graph + " " + source)
    exec(`java Dijkstra.java ${graph} ${source} ${destination}`, (error, stdout, stderr) => {
        if (stderr) {
            console.error(`Error executing command: ${stderr}`);
            return res.status(500).json({ error: stderr });
        }
        console.log(stdout);
        let output = stdout.trim().split(' ').map((i) => parseInt(i));
        console.log(output); // This will print the array of integers
        res.json({
            message: 'Command executed successfully',
            stdout: output,
            stderr: stderr.trim(),
        });
    });
});

// Start the server
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});


