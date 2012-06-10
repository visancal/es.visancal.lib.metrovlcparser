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

package es.visancal.lib.metrovlcparser.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import es.visancal.lib.metrovlcparser.api.MetroServiceParser;
import es.visancal.lib.metrovlcparser.api.Stations;
import es.visancal.lib.metrovlcparser.api.TimeTables;

public class MetroVLCServiceParser implements MetroServiceParser {

	private String html = null;
	private Logger logger = Logger.getLogger("MyLog");
	private TimeTables timetables = null;
	private Stations stations = null;

	public MetroVLCServiceParser(String html) {
		this.html = html;
	}

	public void parseServiceResponse() {
		if (this.html != null) {

			Document doc = Jsoup.parse(this.html);
			// get timetables
			Element elem = doc.getElementById("impresion");
			Elements elems = elem.getElementsByTag("table");
			if (elems.size() > 0) {
				Element e = elems.get(0);
				Elements ee = e.getElementsByTag("tbody");
				if (ee.size() > 0) {
					Element etr = ee.get(0);
					this.timetables = this.parseTimes(etr);
				}
				
			}
			// get stations
			Element elem2 = doc.getElementById("origen");
			if (elem2 != null) {
				this.stations = this.parseStations(elem2);
			}

		} else {

		}

	}

	@Override
	public TimeTables getTimeTables() {
		return this.timetables;
	}

	@Override
	public Stations getStations() {
		return this.stations;
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	private TimeTables parseTimes(Element element) {

		Map<Integer, List<String>> times = new HashMap<Integer, List<String>>();
		for (int i = 0; i < 24; i++) {
			times.put(i,new ArrayList<String>());			
		}
		// trs
		Elements trs = element.children();
		Iterator<Element> iter = trs.iterator();
		while (iter.hasNext()) {			
			// tr
			Element tbody = (Element) iter.next();			
			Elements tds = tbody.children();
			Iterator<Element> iter2 = tds.iterator();
			while (iter2.hasNext()) {
				Element td = (Element) iter2.next();			
				String txt = td.text();
				if (txt.indexOf(":") != -1) {
					String[] p = txt.split(":");
					Integer xhour = new Integer(p[0]);
					times.get(xhour).add(txt);
				} 
			}
		}
		TimeTables tt = new VLCTimeTables();
		tt.setTimeTables(times);

		return tt;
	}

	/**
	 * 
	 * @param elem2
	 * @return
	 */
	private Stations parseStations(Element elem2) {

		// <option value='0'>Elige parada</option>
		Map<Integer, String> stations = new HashMap<Integer, String>();

		Elements elems2 = elem2.getAllElements();
		Iterator<Element> iter = elems2.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Elements opts = element.getElementsByTag("option");
			Iterator<Element> iter3 = opts.iterator();
			while (iter3.hasNext()) {
				Element opt = iter3.next();
				Attributes atts = opt.attributes();
				String sid = atts.get("value");
				Integer id = new Integer(sid);
				String value = opt.text();
				if (id != 0) {
					stations.put(id, value);
				}
			}


		}
		Stations st = new VLCStations();
		st.setStations(stations);

		return st;
	}

}
