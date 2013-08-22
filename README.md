RSSAtomReader
=============
 Code to parse RSS Atom Feed.

 Usage:
  SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		saxParser.parse("C://test//testxml.xml", atomhander);
	 ArrayList<RSSAutoFeeds> allfeeds = (ArrayList<RSSAutoFeeds>) atomhander.getallfeeds();
