package org.processmining.plugins.rttmining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import com.carrotsearch.hppc.ObjectIntOpenHashMap;
import com.carrotsearch.hppc.ObjectOpenHashSet;

public class HeuristicsNetParser
{
	public Graph parse(String filename, ObjectIntOpenHashMap<String> folded_map)
	{
		Graph g = new Graph();
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
      
			int count = 0;
      
			HashMap<String, String> hashmap = new HashMap();
			String input;
			while ((input = br.readLine()) != null)
			{
				// TODO: rimossa da me
				//String input;
				if (count < 5){
					count++;
				}
				else if ((count >= 5) && (count <= 17))
				{
					String[] inputs = input.split(":");
					if (folded_map.containsKey(inputs[0])){
						Node n = new Node(inputs[0], folded_map.get(inputs[0]));
						g.getMap().put(n, new ObjectOpenHashSet());
            
						hashmap.put(inputs[1].split("@")[1].split("&")[0], inputs[0]);
					}
					count++;
				}
				else if ((count >= 20) && (!input.equals("")))
				{
					String[] ids = input.split("@");
					Node x = g.getNode((String)hashmap.get(ids[0]), folded_map.get((String)hashmap.get(ids[0])));
					String[] arrayOfString1;
					int j;
					int i;
					String[] arrayOfString2;
					int m;
					int k;
					if (!ids[1].equals(".")){
						String[] ids2 = ids[1].split("\\|");
            
						j = (arrayOfString1 = ids2).length;
						for (i = 0; i < j; i++)
						{
							String n = arrayOfString1[i];
							if (!n.equals("")) {
								if (n.contains("&")){
									String[] ms = n.split("&");
                  
									m = (arrayOfString2 = ms).length;
									for (k = 0; k < m; k++)
									{
										String m1 = arrayOfString2[k];
										if (!m1.equals("")) {
											if (hashmap.get(m1) == null) {
												System.out.println("stop");
											}
											Node y = g.getNode((String)hashmap.get(m1), folded_map.get((String)hashmap.get(m1)));
											if (!g.getLista_archi().contains(new Edge(y, x))) {
												g.addEdge(y, x, false);
											}
										}
									}
								}
								else
								{
									Node y = g.getNode((String)hashmap.get(n), folded_map.get((String)hashmap.get(n)));
									if (hashmap.get(n) == null) {
										System.out.println("stop");
									}
									if (!g.getLista_archi().contains(new Edge(y, x))) {
										g.addEdge(y, x, false);
									}
								}
							}
						}
					}
					if (!ids[2].equals(".")){
						String[] ids2 = ids[2].split("\\|");
            
						j = (arrayOfString1 = ids2).length;
						for (i = 0; i < j; i++)
						{
							String n = arrayOfString1[i];
							if (!n.equals("")) {
								if (n.contains("&")){
									String[] ms = n.split("&");
                  
									m = (arrayOfString2 = ms).length;
									for (k = 0; k < m; k++)
									{
										String m2 = arrayOfString2[k];
										if (!m2.equals("")){
											if (hashmap.get(m2) == null) {
												System.out.println("stop");
											}
											Node y = g.getNode((String)hashmap.get(m2), folded_map.get((String)hashmap.get(m2)));
											if (!g.getLista_archi().contains(new Edge(x, y))) {
												g.addEdge(x, y, false);
											}
										}
									}
								}
								else
								{
									if (hashmap.get(n) == null) {
										System.out.println("stop");
									}
									Node y = g.getNode((String)hashmap.get(n), folded_map.get((String)hashmap.get(n)));
									if (!g.getLista_archi().contains(new Edge(x, y))) {
										g.addEdge(x, y, false);
									}
								}
							}
						}
					}
					count++;
				}
				else
				{
					count++;
				}
			}
		}
		catch (Exception localException) {}
		return g;
	}
}
