import java.util.Iterator;

import org.deckfour.xes.factory.XFactoryBufferedImpl;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

/*
 * OpenXES
 * 
 * The reference implementation of the XES meta-model for event 
 * log data management.
 * 
 * Copyright (c) 2009 Christian W. Guenther (christian@deckfour.org)
 * 
 * 
 * LICENSE:
 * 
 * This code is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 * 
 * EXEMPTION:
 * 
 * The use of this software can also be conditionally licensed for
 * other programs, which do not satisfy the specified conditions. This
 * requires an exemption from the general license, which may be
 * granted on a per-case basis.
 * 
 * If you want to license the use of this software with a program
 * incompatible with the LGPL, please contact the author for an
 * exemption at the following email address: 
 * christian@deckfour.org
 * 
 */

/**
 * @author Christian W. Guenther (christian@deckfour.org)
 * 
 */
public class CloneAndRemoveTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XFactoryBufferedImpl factory = new XFactoryBufferedImpl();

		XLog log = factory.createLog();
		XTrace trace = factory.createTrace();
		XEvent event = factory.createEvent();

		trace.add(event);
		log.add(trace);

		// doTest(log);

		XLog log2 = (XLog) log.clone();

		doTest(log2);

		doTest(log);
	}

	private static void doTest(XLog log) {
		toString(log);

		for (XTrace trace : log) {
			Iterator<XEvent> it = trace.iterator();
			while (it.hasNext()) {
				it.next();
				it.remove();
			}
		}

		toString(log);

	}

	private static void toString(XLog log) {
		System.out.println("---------------------------------------");
		System.out.println("" + log.hashCode());
		for (XTrace trace : log) {
			System.out.println("  |-" + trace.hashCode());
			for (XEvent event : trace) {
				System.out.println("    |-" + event.hashCode());
			}
		}
	}
}
