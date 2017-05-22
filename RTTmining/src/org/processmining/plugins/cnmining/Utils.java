package org.processmining.plugins.cnmining;

import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;
import java.util.Iterator;

import org.deckfour.xes.extension.std.XConceptExtension;
import org.deckfour.xes.factory.XFactory;
import org.deckfour.xes.in.XMxmlParser;
import org.deckfour.xes.in.XParser;
import org.deckfour.xes.in.XParserRegistry;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XAttribute;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XElement;
import org.deckfour.xes.model.XLog;

public class Utils
{
	public static XLog parseLog(String filename, XFactory factory) throws Exception{
		XParser parser;
		if ((filename.toLowerCase().endsWith(".xes")) || 
				(filename.toLowerCase().endsWith(".xez")) || 
				(filename.toLowerCase().endsWith(".xes.gz"))) 
		{
			parser = new XesXmlParser(factory);
		} 
		else {
			parser = new XMxmlParser(factory);
		}
		Collection<XLog> logs = null;
		try
		{
			logs = parser.parse(new FileInputStream(new File(filename)));
		}
		catch (Exception e)
		{
			logs = null;
		}
		Iterator<?> localIterator;
		if (logs == null)
		{
			localIterator = XParserRegistry.instance().getAvailable().iterator();
			// TODO:: non funziona sta riga ma che cazz
			//break label164;
			for (;;)
			{
				XParser p = (XParser)localIterator.next();
				if (p != parser) {
					try
					{
						logs = p.parse(new FileInputStream(new File(filename)));
						if (logs.size() <= 0) {
							if (localIterator.hasNext()) {
								continue;
							}
						}
					}
					catch (Exception e1)
					{
						logs = null;
					}
				}
			}
		}
		label164:
			if ((logs == null) || (logs.size() == 0)) {
				throw new Exception("No processes contained in log!");
			}
			XLog log = logs.iterator().next();
			if (XConceptExtension.instance().extractName(log) == null) {
				XConceptExtension.instance().assignName(log, 
						"Anonymous log imported from " + filename);
			}
			if (log.isEmpty()) {
				throw new Exception("No process instances contained in log!");
			}
			return log;
	}
  
	public static void printAttributes(XElement element){
		XAttributeMap attributes = element.getAttributes();
		System.out.println("Element Attributes:");
		Iterator<String> iterator = attributes.keySet().iterator();
		while (iterator.hasNext())
		{
			String key = iterator.next();
			System.out.println("key ext - " + key);
			System.out.println("key att - " + attributes.get(key).getKey());
			XAttribute xAttribute = attributes.get(key);
			System.out.println("Attribute - " + xAttribute + " di tipo: " + xAttribute.getClass());
			System.out.println("Extension of Attribute - " + xAttribute.getExtension());
		}
	}
}
