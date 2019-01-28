Order Entry Dashboard POC
=========================
This was a personal project I did to evaluate React as a view for Dashboard applications focused on visualizing real-time data.


Goals
-----
 - Find a solid table component.
 - Experiment with Flux, ImmutableJS and control of the rendering phase of the component lifecycle to obtain good usability in high-load situations.
 - Demonstrate a dashboard containing multiple panels containing graphs, sortable tables, drilldown cells, and summary statistics.
 - Gain more experience w/ the node / webpack ecosystem.
 - Create a bootstrap system capable of supporting future experiments.

Note: The focus was not on creating a slick material-like layout.


Architecture
------------
A stream generator simulates receipt of encoded messages from an upstream real-time system.  The generator targets a sync simulating a network endpoint and triggers an onData callback on the ingest component.  The ingest component parses messages into domain appropriate messages, performs various transformations as needed, and publishes snapshot information to websocket endpoint and/or updates in-memory cache store.

Whether push or pull, there is built in throttle based on either often the UI polls for data or the server pushes snapshot data.  

The UI either receives date updates asyncronously via web-socket or polls an endpoint periodically.  Data flows unidirectionally according to the Flux paradigm and triggers various rendering updates.


Provider
--------
The backend is fairly simple and not intended to represent a scalable solution.  It is meant to provide a streaming source of semi-random data to the UI.  It is also intended to demonstrate receipt of big data and throttling pushing snapshots to UI.


