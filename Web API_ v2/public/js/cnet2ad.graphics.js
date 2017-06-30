/*
  Definizione grafica dei vari tipi di nodi
*/

var Cnet2AD = Cnet2AD || {};
Cnet2AD.Graphics = {};

var $go = $go || go.GraphObject.make; 

Cnet2AD.Graphics.ActivityNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "RoundedRectangle",
          	new go.Binding("fill", "color")
        ),
        $go(go.TextBlock,
          	new go.Binding("text", "key")
        )
    )
);

Cnet2AD.Graphics.InitialNode = $go(go.Node, "Spot",
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

Cnet2AD.Graphics.FinalNode = $go(go.Node, "Spot",
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

Cnet2AD.Graphics.BranchNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "Diamond",{ width: 20, height: 20, fill: 'lightblue', margin: 0})
    )
);

Cnet2AD.Graphics.ForkNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "Rectangle", { width: 14, height: 30, fill: 'blue', margin: 0})
    )
);

Cnet2AD.Graphics.JoinNode = $go(go.Node, "Spot",
  	$go(go.Panel, "Auto",
    	$go(go.Shape, "Rectangle", { width: 14, height: 30, fill: 'crimson', margin: 0})
    )
);

Cnet2AD.Graphics.templateMap = new go.Map("string", go.Node);
Cnet2AD.Graphics.templateMap.add("Node", Cnet2AD.Graphics.ActivityNode);
Cnet2AD.Graphics.templateMap.add("InitialNode", Cnet2AD.Graphics.InitialNode);
Cnet2AD.Graphics.templateMap.add("FinalNode", Cnet2AD.Graphics.FinalNode);
Cnet2AD.Graphics.templateMap.add("BranchNode", Cnet2AD.Graphics.BranchNode);
Cnet2AD.Graphics.templateMap.add("ForkNode", Cnet2AD.Graphics.ForkNode);
Cnet2AD.Graphics.templateMap.add("JoinNode", Cnet2AD.Graphics.JoinNode);