var Cnet2AD = Cnet2AD || {};

var $go = go.GraphObject.make; 

Cnet2AD.container = null;
Cnet2AD.model = null;

// Inizializza il contenitore
Cnet2AD.init = function( id ){
	Cnet2AD.container = $go(go.Diagram, id,  // create a Diagram for the DIV HTML element
		{
			initialAutoScale: go.Diagram.Uniform,  // an initial automatic zoom-to-fit
	      	contentAlignment: go.Spot.Center,  // align document to the center of the viewport
	      	layout:
	            $go(go.ForceDirectedLayout,  // automatically spread nodes apart
	          		{ maxIterations: 200, defaultSpringLength: 30, defaultElectricalCharge: 100 }
	          	)
		}
	);
	Cnet2AD.container.layout = $go(go.TreeLayout);

	Cnet2AD.container.linkTemplate =
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

	return Cnet2AD.container;
}

// Visualizza il grafo forniti nodi e archi
Cnet2AD.show = function(nodes, edges){

	Cnet2AD.container.nodeTemplateMap = Cnet2AD.Graphics.templateMap;

	Cnet2AD.model = $go(go.GraphLinksModel);

	Cnet2AD.model.nodeDataArray = nodes;
	Cnet2AD.model.linkDataArray = edges;

	Cnet2AD.container.model = Cnet2AD.model;
}