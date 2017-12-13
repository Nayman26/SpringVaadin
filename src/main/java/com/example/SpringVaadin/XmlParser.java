package com.example.SpringVaadin;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
public class XmlParser {

	public static void XmlParse() {
		try {
			// File inputFile = new File("C:\\Users\\ENAYMAN\\Desktop\\input.txt");
			SAXReader reader = new SAXReader();
			// Document document = reader.read( inputFile );
			String urlToRead = "http://api.openweathermap.org/data/2.5/forecast?q=" + VaadinUI.sehir+"+&mode=xml&appid=8d8fd4b96a127e311c0ebb6f5c9da39c";
			URL url = null;
			try {
				url = new URL(urlToRead);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document document = reader.read(url);
			System.out.println("Root element :" + document.getRootElement().getName() + "\n");

			List<Node> nodes = document.selectNodes("/weatherdata/location");

			for (Node node : nodes) {
				System.out.println("Sehir adı:" + node.selectSingleNode("name").getText());
				System.out.println("ülkesi:" + node.selectSingleNode("country").getText());
				String enlem = node.selectSingleNode("location").valueOf("@latitude");
				String boylam = node.selectSingleNode("location").valueOf("@longitude");
				System.out.println("enlem:" + enlem + " boylam:" + boylam);
			}
			nodes = document.selectNodes("/weatherdata");
			for (Node node : nodes) {
				System.out.println("gün doğumu:" + node.selectSingleNode("sun").valueOf("@rise"));
				System.out.println("gün doğumu:" + node.selectSingleNode("sun").valueOf("@set"));
			}

			nodes = document.selectNodes("/weatherdata/forecast/time");
			System.out.println("----------------------------");

			for (Node node : nodes) {
				System.out.println("\nCurrent Element: " + node.getName());
				System.out.println("time from:" + node.valueOf("@from") + " to " + node.valueOf("@to"));
				String name = node.selectSingleNode("symbol").valueOf("@name");
				String sembolno = node.selectSingleNode("symbol").valueOf("@number");
				String var = node.selectSingleNode("symbol").valueOf("@var");
				System.out.println("sembol numarası:" + sembolno + " adı:" + "(" + name + ")" + " var:" + var);
				String deg = node.selectSingleNode("windDirection").valueOf("@deg");
				String code = node.selectSingleNode("windDirection").valueOf("@code");
				name = node.selectSingleNode("windDirection").valueOf("@name");
				System.out.println("Rüzgar Yönü:" + " deg:" + deg + " code:" + code + " adı:" + name);
				String hız = node.selectSingleNode("windSpeed").valueOf("@mps");
				name = node.selectSingleNode("windSpeed").valueOf("@name");
				System.out.println("Rüzgar Hızı:" + hız + "mps" + " adı:" + name);
				String deger = node.selectSingleNode("temperature").valueOf("@value");
				String minS = node.selectSingleNode("temperature").valueOf("@min");
				String maxS = node.selectSingleNode("temperature").valueOf("@max");
				double derece = Float.parseFloat(deger) - 273.150;
				double minD = Float.parseFloat(minS) - 273.150;
				double maxD = Float.parseFloat(maxS) - 273.150;
				System.out.format("sıcaklık: %.3fC min: %.3fC max: %.3fC ", derece, minD, maxD);
				System.out.println("basınç:" + node.selectSingleNode("pressure").valueOf("@value") + " hPa");
				System.out.println("nem: %" + node.selectSingleNode("humidity").valueOf("@value"));
				System.out.println("bulut: %" + node.selectSingleNode("clouds").valueOf("@all") + " "
						+ node.selectSingleNode("clouds").valueOf("@value"));
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	/*public static void main(String[] args) {
		XmlParse();
	}*/
}
