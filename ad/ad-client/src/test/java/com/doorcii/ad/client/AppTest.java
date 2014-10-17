package com.doorcii.ad.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public void testhdfs() {
    	InputStream is = null;
    	try {
    		is = new URL("hdfs://advnode.mna.myqcloud.com/test").openStream();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	if(null != is) {
    		try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
}
