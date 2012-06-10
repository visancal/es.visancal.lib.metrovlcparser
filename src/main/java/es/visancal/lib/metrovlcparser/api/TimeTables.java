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

package es.visancal.lib.metrovlcparser.api;

import java.util.List;


import java.util.Map;

public interface TimeTables {

	/**
	 * Gets timetables between two stations for one day
	 * @return
	 */
	public Map<Integer,List<String>> getTimeTables();
	
	/**
	 * Adds timetables between two stations 
	 * @param timetables
	 */
	public void setTimeTables(Map<Integer,List<String>> timetables);
	
	/**
	 * Returns all the transit time for one hour 
	 * @param hour
	 * @return
	 */
	public List<String> getTransitTimes(Integer hour);
}
