/*
  Definizione grafica dei vari tipi di nodi
*/

var RTTmining = RTTmining || {};
RTTmining.Graphics = {};

var $go = $go || go.GraphObject.make; 

RTTmining.Graphics.ActivityNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "RoundedRectangle",
          	new go.Binding("fill", "color")
        ),
        $go(go.TextBlock,
          	new go.Binding("text", "key")
        )
    )
);

RTTmining.Graphics.InitialNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Vertical", 
    	$go(go.Shape, 
    		"Circle", 
    		{ width: 22, height: 22, fill: "black" }
        )/*,
        $go(go.TextBlock,
          	new go.Binding("text", "key")
        )*/
    )
);

RTTmining.Graphics.FinalNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Vertical", 
    	$go(go.Shape,
          	"Circle", 
    		{ width: 16, height: 16, fill: "black" }
        )/*,
        $go(go.TextBlock,
          	new go.Binding("text", "key")
        )*/
    )
);

RTTmining.Graphics.BranchNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "Diamond",{ width: 20, height: 20, fill: 'lightblue', margin: 0})
    )
);

RTTmining.Graphics.ForkNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "Rectangle", { width: 14, height: 30, fill: 'blue', margin: 0})
    )
);

RTTmining.Graphics.JoinNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "Rectangle", { width: 14, height: 30, fill: 'crimson', margin: 0})
    )
);

RTTmining.Graphics.templateMap = new go.Map("string", go.Node);
RTTmining.Graphics.templateMap.add("Node", RTTmining.Graphics.ActivityNode);
RTTmining.Graphics.templateMap.add("InitialNode", RTTmining.Graphics.InitialNode);
RTTmining.Graphics.templateMap.add("FinalNode", RTTmining.Graphics.FinalNode);
RTTmining.Graphics.templateMap.add("BranchNode", RTTmining.Graphics.BranchNode);
RTTmining.Graphics.templateMap.add("ForkNode", RTTmining.Graphics.ForkNode);
RTTmining.Graphics.templateMap.add("JoinNode", RTTmining.Graphics.JoinNode);