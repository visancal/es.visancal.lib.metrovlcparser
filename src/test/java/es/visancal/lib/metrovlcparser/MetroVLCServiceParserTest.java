/* libMetroVLCParser
 *
 * Copyright (C) 2012 Vicent Sanjaime Calvet
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 *
 */

/*
 * AUTHORS:
 * 2012 Vicent Sanjaime Calvet  {{Main Developer}}
 */

package es.visancal.lib.metrovlcparser;

import java.io.BufferedReader;
import java.io.FileReader;

import junit.framework.TestCase;
import es.visancal.lib.metrovlcparser.api.MetroServiceParser;
import es.visancal.lib.metrovlcparser.api.Stations;
import es.visancal.lib.metrovlcparser.api.TimeTables;
import es.visancal.lib.metrovlcparser.impl.MetroVLCServiceParser;

public class MetroVLCServiceParserTest extends TestCase {

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		// get html file
		String html = null;
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader("docs/response.html"));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			html = sb.toString();
		} catch (Exception e) {
		}

		// String fileName =
		// ClassLoader.getSystemResource("docs/response.html").getFile();

		// get text html file
		// parse html
		MetroServiceParser parser = new MetroVLCServiceParser(html);
		parser.parseServiceResponse();
		TimeTables tt = parser.getTimeTables();
		Stations st = parser.getStations();

		// validations timetables
		assertTrue(true);

		// validations stations
		assertTrue(true);
	}

}
