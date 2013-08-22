import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XmlParser extends DefaultHandler{
    private static final int        STATE_FEED      = 0;
    private static final int        STATE_ID        = 1;
    private static int          sDepth          = 0;
    private static int          sCurrentState   = 0;
    private String              mTheString;
public XmlParser(){}

public void startDocument() throws SAXException{}

public void endDocument() throws SAXException{}

public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException{
    mTheString = "";
    sDepth++;
    if (qName.equals("feed")){
        sCurrentState = STATE_FEED;
        return;
    }
    if (qName.equals("id")){
                    sCurrentState = STATE_ID;
            return;
    }
    sCurrentState = 0;
}

public void endElement(String namespaceURI, String localName, String qName) throws SAXException{
    sDepth--;
        switch (sCurrentState){
            case STATE_FEED:
                                   //Do something with the feed
                sCurrentState = 0;
                mTheString = "";
                break;
            case STATE_ID:
                // Save the ID or do whatever
                sCurrentState = 0;
                mTheString = "";
                break;
            default:
                //Do nothing
                mTheString = "";
                return;
        }
        mTheString = "";
}

public void characters(char ch[], int start, int length){
      mTheString = mTheString + new String(ch, start, length);
}
}