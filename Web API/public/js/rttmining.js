var RTTmining = RTTmining || {};

var $go = go.GraphObject.make; 

RTTmining.container = null;
RTTmining.model = null;

// Inizializza il contenitore
RTTmining.init = function( id ){
	RTTmining.container = $go(go.Diagram, id,  // create a Diagram for the DIV HTML element
		{
			initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit
	      	contentAlignment: go.Spot.Center,  // align document to the center of the viewport
	      	layout:
	            $go(go.ForceDirectedLayout,  // automatically spread nodes apart
	          		{ maxIterations: 200, defaultSpringLength: 30, defaultElectricalCharge: 100 }
	          	)
		}
	);
	RTTmining.container.layout = $go(go.TreeLayout);

	RTTmining.container.linkTemplate =
	$go(go.Link,
		{
	  		selectable: false,      // links cannot be selected by the user
	  		curve: go.Link.Bezier,
		  	layerName: "Background"  // don't cross in front of any nodes
		},
		$go(go.Shape,  // this shape only shows when it isHighlighted
	  	{ 
			isPanelMain: true, 
			stroke: null, 
			strokeWidth: 5 
		},
		new go.Binding("stroke", "isHighlighted", function(h) { return h ? "red" : null; }).ofObject()),
		$go(go.Shape,
		  	// mark each Shape to get the link geometry with isPanelMain: true
		  	{ isPanelMain: true, stroke: "black", strokeWidth: 1 },
		  	new go.Binding("stroke", "color")),
			$go(go.Shape, { toArrow: "Standard" }
		)
	);

	return RTTmining.container;
}

// Visualizza il grafo forniti nodi e archi
RTTmining.show = function(nodes, edges){

	RTTmining.container.nodeTemplateMap = RTTmining.Graphics.templateMap;

	RTTmining.model = $go(go.GraphLinksModel);

	RTTmining.model.nodeDataArray = nodes;
	RTTmining.model.linkDataArray = edges;

	RTTmining.container.model = RTTmining.model;
}