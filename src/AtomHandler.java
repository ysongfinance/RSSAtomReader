import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class AtomHandler extends DefaultHandler {
	List<RSSAutoFeeds> allfeeds;
	boolean published = false;
	boolean title = false;
	boolean content = false;
	boolean author = false;
	boolean link = false;
	RSSAutoFeeds currentfeed;
	public AtomHandler(){
		allfeeds = new ArrayList<RSSAutoFeeds>();
		currentfeed = new RSSAutoFeeds();
	}
	
	public List<RSSAutoFeeds> getallfeeds(){
		return allfeeds;
	}
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
		//System.out.println("Start Element :" + qName);
 
		if (qName.equalsIgnoreCase("published")) {
			published = true;			
		}
 
		if (qName.equalsIgnoreCase("title")) {
			title = true;
		}
 
		if (qName.equalsIgnoreCase("content")) {
			content = true;
		}
 
		if (qName.equalsIgnoreCase("name")) {
			author = true;
		}
		if (qName.equalsIgnoreCase("id")) {
			link = true;
		}
 
	}
 
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
 
		//System.out.println("End Element :" + qName);
 
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
 
		if (published) {
			String tmp = new String(ch, start, length);
			System.out.println("Published : " + tmp);
			published = false;
			currentfeed.published = tmp;
		}
 
		if (title) {
			String tmp = new String(ch, start, length);
			System.out.println("Title : " + tmp);
			title = false;
			currentfeed.title = tmp;
		}
 
		if (author) {
			if(!currentfeed.link.endsWith(".xml"))
				allfeeds.add(currentfeed);
			currentfeed = new RSSAutoFeeds();
			
			String tmp = new String(ch, start, length);
			System.out.println("Author : " + tmp);
			author = false;
			currentfeed.author = tmp;
		}
 
		if (link) {
			String tmp = new String(ch, start, length);
			System.out.println("Link : " + tmp);
			link = false;
			currentfeed.link = tmp;
		}
		if(content){
			String tmp = new String(ch, start, length);
			System.out.println("Content : " + tmp);
			content = false;
			currentfeed.description = tmp;
		}
 
	}
 
 }