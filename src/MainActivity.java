import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;
import org.xml.sax.Attributes;


public class MainActivity {

	public static void main(String argv[]) {
		 
	    try {
	 
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
	 
		DefaultHandler handler = new DefaultHandler() {
	 
		boolean bfname = false;
		boolean blname = false;
		boolean bnname = false;
		boolean bsalary = false;
	 
		public void startElement(String uri, String localName,String qName, 
	                Attributes attributes) throws SAXException {
	 
			//System.out.println("Start Element :" + qName);
	 
			if (qName.equalsIgnoreCase("FIRSTNAME")) {
				bfname = true;
			}
	 
			if (qName.equalsIgnoreCase("LASTNAME")) {
				blname = true;
			}
	 
			if (qName.equalsIgnoreCase("NICKNAME")) {
				bnname = true;
			}
	 
			if (qName.equalsIgnoreCase("SALARY")) {
				bsalary = true;
			}
	 
		}
	 
		public void endElement(String uri, String localName,
			String qName) throws SAXException {
	 
			//System.out.println("End Element :" + qName);
	 
		}
	 
		public void characters(char ch[], int start, int length) throws SAXException {
	 
			if (bfname) {
				System.out.println("First Name : " + new String(ch, start, length));
				bfname = false;
			}
	 
			if (blname) {
				System.out.println("Last Name : " + new String(ch, start, length));
				blname = false;
			}
	 
			if (bnname) {
				System.out.println("Nick Name : " + new String(ch, start, length));
				bnname = false;
			}
	 
			if (bsalary) {
				System.out.println("Salary : " + new String(ch, start, length));
				bsalary = false;
			}
	 
		}
	 
	     };
	 
	     AtomHandler atomhander = new AtomHandler();
	       saxParser.parse("C://test//theverge.xml", atomhander);
	       ArrayList<RSSAutoFeeds> allfeeds = (ArrayList<RSSAutoFeeds>) atomhander.getallfeeds();
	       System.out.println(allfeeds.size());
	     } catch (Exception e) {
	       e.printStackTrace();
	     }
	 
	   }
	 

	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws SAXException 
	 */
/*	public static void main(String[] args) throws ClientProtocolException, IOException, SAXException {
		InputStream is = null;

	    try {
	        is = new FileInputStream("C://test//testxml.xml");

	        //is.close(); 
	    } catch (FileNotFoundException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }	    
		
		MainActivity ma = new MainActivity();
		InputStream stream = ma.load("http://www.theverge.com/rss/frontpage");//whatever your stream is (the document)
		XmlParser handler = new XmlParser(); // your custom parser
		XMLReader xmlreader = XMLReaderFactory.createXMLReader();
		xmlreader.setContentHandler(handler); 
		xmlreader.parse(new InputSource(is));
		System.out.println("now");
		
	}*/
	
	public InputStream load(String uri) throws ClientProtocolException, IOException {
	    final HttpGet httpget = new HttpGet(uri);

	    InputStream feedStream = null;
	    // Send GET request to URI
	      HttpClient  httpclient = new DefaultHttpClient();
		final HttpResponse response = httpclient.execute(httpget);

	      // Check if server response is valid
	      final StatusLine status = response.getStatusLine();
	      if (status.getStatusCode() != HttpStatus.SC_OK) {
	        return null;
	      }

	      // Extract content stream from HTTP response
	      HttpEntity entity = response.getEntity();
	      feedStream = entity.getContent();
	      return feedStream;
	  
	}
}
