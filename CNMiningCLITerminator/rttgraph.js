var data = [[
{key: "SSSTTTAAARRRTTT", category: "InitialNode", color: "black"},
{key: "B", category: "Node", color: "orange"},
{key: "A", category: "Node", color: "orange"},
{key: "C", category: "Node", color: "orange"},
{key: "EEENNNDDD", category: "FinalNode", color: "black"},
{key: "D", category: "Node", color: "orange"},
{key: "BranchA", category: "BranchNode", color: "cyan"},
{key: "BranchD", category: "BranchNode", color: "cyan"}
],
[
{from: "SSSTTTAAARRRTTT", to: "A"},
{from: "B", to: "BranchD"},
{from: "A", to: "BranchA"},
{from: "BranchA", to: "C"},
{from: "BranchA", to: "BranchD"},
{from: "C", to: "B"},
{from: "D", to: "EEENNNDDD"},
{from: "BranchD", to: "D"}
]]