var data = [[
{key: "f", category: "Node", color: "orange"},
{key: "a", category: "Node", color: "orange"},
{key: "c", category: "Node", color: "orange"},
{key: "f_O", category: "BranchNode", color: "cyan"},
{key: "b", category: "Node", color: "orange"},
{key: "c_O", category: "BranchNode", color: "cyan"},
{key: "e_I1", category: "JoinNode", color: "pink"},
{key: "e", category: "Node", color: "orange"},
{key: "end", category: "FinalNode", color: "black"},
{key: "d", category: "Node", color: "orange"},
{key: "start", category: "InitialNode", color: "black"},
{key: "f_O1", category: "ForkNode", color: "red"},
{key: "a_O", category: "BranchNode", color: "cyan"},
{key: "b_O", category: "BranchNode", color: "cyan"},
{key: "d_I1", category: "JoinNode", color: "pink"},
{key: "a_O1", category: "ForkNode", color: "red"},
{key: "BranchInc", category: "BranchNode", color: "cyan"},
{key: "BranchInb", category: "BranchNode", color: "cyan"},
{key: "BranchIne", category: "BranchNode", color: "cyan"},
{key: "BranchInd", category: "BranchNode", color: "cyan"}
],
[
{from: "start", to: "a"},
{from: "d", to: "end"},
{from: "b", to: "b_O"},
{from: "c", to: "c_O"},
{from: "a", to: "a_O"},
{from: "a_O", to: "a_O1"},
{from: "f", to: "f_O"},
{from: "f_O", to: "f_O1"},
{from: "c_O", to: "d_I1"},
{from: "e", to: "f"},
{from: "c_O", to: "e_I1"},
{from: "b_O", to: "e_I1"},
{from: "b_O", to: "d_I1"},
{from: "f_O1", to: "BranchInb"},
{from: "a_O1", to: "BranchInb"},
{from: "f_O1", to: "BranchInc"},
{from: "a_O", to: "BranchInc"},
{from: "f_O", to: "BranchInc"},
{from: "a_O1", to: "BranchInc"},
{from: "b_O", to: "BranchIne"},
{from: "e_I1", to: "BranchIne"},
{from: "b_O", to: "BranchInd"},
{from: "d_I1", to: "BranchInd"},
{from: "BranchInc", to: "c"},
{from: "BranchInb", to: "b"},
{from: "BranchIne", to: "e"},
{from: "BranchInd", to: "d"}
]]