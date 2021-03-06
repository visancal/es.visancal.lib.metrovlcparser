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

import java.util.HashMap;
import java.util.Map;

import es.visancal.lib.metrovlcparser.api.Stations;

public class VLCStations implements Stations {
	
	private Map<Integer, String> stations = new HashMap<Integer,String>();

	@Override
	public Map<Integer, String> getStations() {
		return this.stations;
	}

	@Override
	public void setStations(Map<Integer, String> stations) {
		this.stations = stations;
		
	}

}
