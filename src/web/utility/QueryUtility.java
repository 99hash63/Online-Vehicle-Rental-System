
package web.utility;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This load Sql queries available in the BookingQuery.xml
 * @see #CommonUtility
 */
public class QueryUtility extends CommonUtility {

	/**
	 * This method read the SQLQuery.xml file and retrieve the query by
	 * query id.
	 * 
	 * @param id
	 *            QueryID to retrieve elements
	 * 
	 * @return String formatted query will be returned as output
	 * 
	 * @throws ParserConfigurationException
	 *             - Indicates a serious configuration error.
	 * @throws IOException
	 *             - This class is the general class of exceptions produced by
	 *             failed or interrupted I/O operations.
	 * @throws SAXException
	 *             - Encapsulate a general SAX error or warning.
	 * 
	 * @see web.service.*
	 * 
	 */
	public static String queryById(String id) throws SAXException, IOException, ParserConfigurationException {

		NodeList nodeList;
		Element element = null;
		/*
		 * Read the BookingQuery.xml file and read each query node into node
		 * list. It refers tag name query
		 */
		nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(System.getProperty("catalina.base") + "\\wtpwebapps\\onlineVehicleRentingSystem\\WEB-INF\\BookingQuery.xml"))
				.getElementsByTagName(CommonConstants.TAG_NAME);

		/*
		 * Extract the node from node list using query id query id is taken from
		 * query node attribute
		 */
		for (int value = 0; value < nodeList.getLength(); value++) {
			element = (Element) nodeList.item(value);
			if (element.getAttribute(CommonConstants.ATTRIB_ID).equals(id))
				break;
		}
		return element.getTextContent().trim();
	}
}
