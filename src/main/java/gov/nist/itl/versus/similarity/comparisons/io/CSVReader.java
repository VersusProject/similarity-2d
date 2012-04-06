
/*
 * This software was developed at the National Institute of Standards and
 * Technology by employees of the Federal Government in the course of
 * their official duties. Pursuant to title 17 Section 105 of the United
 * States Code this software is not subject to copyright protection and is
 * in the public domain. This software is an experimental system. NIST assumes
 * no responsibility whatsoever for its use by other parties, and makes no
 * guarantees, expressed or implied, about its quality, reliability, or
 * any other characteristic. We would appreciate acknowledgment if the
 * software is used.
 *
 *
 *  @author  Benjamin Long, blong@nist.gov
 *  @version 1.1
 *    Date: Tue Jan  3 12:31:41 EST 2012
 */

package gov.nist.itl.versus.similarity.comparisons.io;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CSVReader 
{
	private BufferedReader rdr = null;
	private String fileName;
	
	public CSVReader( String fname ) {
		this.fileName = fname;
	}
	
	public ArrayList<ArrayList> rows()
	{	
		ArrayList<ArrayList> rows = new ArrayList();
		String line = "";
		ArrayList cols = null;
		String delimiters = "\t,";
		String tokens[] = null;
		
		try {
			rdr = new BufferedReader(new FileReader(new File(fileName)));			
			while ( (line=rdr.readLine()) != null )
			{
				cols = new ArrayList();
				tokens = line.split(delimiters);
				for (int i=0; i < tokens.length; i++) {
					cols.add( tokens[i] );
				}
				rows.add( cols );
			}
			rdr.close();
		} catch ( IOException e ) {
			System.out.println(e.getMessage());
			return null;
		}
		
		return rows;
	}
}
