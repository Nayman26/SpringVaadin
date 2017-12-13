package com.example.SpringVaadin;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class CurrentWheather {
	
	public static String XmlParse() {
		String sonuc="";
		try {
			// File inputFile = new File("C:\\Users\\ENAYMAN\\Desktop\\input.txt");
			SAXReader reader = new SAXReader();
			// Document document = reader.read( inputFile );
			String urlToRead = "http://api.openweathermap.org/data/2.5/weather?q="+VaadinUI.sehir+"&mode=xml&appid=2fd3b35c4b7fcfe84964b9b829703075";
			//String urlToRead = "http://api.openweathermap.org/data/2.5/weather?q=istanbul&mode=xml&appid=2fd3b35c4b7fcfe84964b9b829703075";
			URL url = null;
			try {
				url = new URL(urlToRead);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Document document = reader.read(url);
			//System.out.println("Root element :" + document.getRootElement().getName() + "\n");
			List<String> liste = new ArrayList<>();
			
			List<Node> nodes = document.selectNodes("/current/city");
			for (Node node : nodes) {
				String sehir ="sehir: "+ node.valueOf("@name");
				String enlem ="enlem: "+ node.selectSingleNode("coord").valueOf("@lat");
				String boylam ="boylam: "+ node.selectSingleNode("coord").valueOf("@lon");
				String ülke = "ülke: "+ node.selectSingleNode("country").getText();
				String gunDogumu ="gun doğumu: "+ node.selectSingleNode("sun").valueOf("@rise");
				String gunBatimi ="gun batımı: "+node.selectSingleNode("sun").valueOf("@set");

				liste.add(sehir);
				liste.add(enlem);
				liste.add(boylam);
				liste.add(ülke);
				liste.add(gunDogumu);
				liste.add(gunBatimi);
				}
			
			nodes = document.selectNodes("/current");
			for (Node node : nodes) {
				String sıcaklık =node.selectSingleNode("temperature").valueOf("@value");
				String Smin=node.selectSingleNode("temperature").valueOf("@min");
				String Smax=node.selectSingleNode("temperature").valueOf("@max");
				int derece = (int) (Float.parseFloat(sıcaklık) - 273);
				int minD = (int) (Float.parseFloat(Smin) - 273);
				int maxD = (int) (Float.parseFloat(Smax) - 273);
				sıcaklık="sıcaklık: "+ Integer.toString(derece)+" °C";
				Smin="sıcaklık min: "+Integer.toString(minD)+" °C";
				Smax="sıcaklık max: "+Integer.toString(maxD)+" °C";
				String nem ="nem: %"+ node.selectSingleNode("humidity").valueOf("@value");
				String basınc ="basınç: "+ node.selectSingleNode("pressure").valueOf("@value")+" hPa";
				//String bulut = "bulut: %"+ node.selectSingleNode("clouds").valueOf("@name");
				String hava ="hava: "+ node.selectSingleNode("weather").valueOf("@value");
				String sonGünceleme ="Son güncelleme: "+node.selectSingleNode("lastupdate").valueOf("@value");
				liste.add(sıcaklık);
				liste.add(Smin);
				liste.add(Smax);
				liste.add(nem);
				liste.add(basınc);
				//liste.add(bulut);
				liste.add(hava);
				liste.add(sonGünceleme);
				}
			nodes = document.selectNodes("/current/wind");
			for (Node node : nodes) {
				String Rname ="Rüzgar adı: "+ node.selectSingleNode("speed").valueOf("@name");
				String Rhızı ="Rüzgar hızı: "+ node.selectSingleNode("speed").valueOf("@value")+" mps";
				String Ryönü="Rüzgar yönü: "+ node.selectSingleNode("direction").valueOf("@value")+"°";
				Ryönü+=" "+node.selectSingleNode("direction").valueOf("@name");
				Ryönü+=" code:"+ node.selectSingleNode("direction").valueOf("@code");
				liste.add(Rname);
				liste.add(Rhızı);
				liste.add(Ryönü);
				}
			for (String listele : liste) {
				sonuc += listele+" \n";
			}
			
			
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return sonuc;
	}	
	/*public static void main(String[] args) {
	System.out.println(XmlParse());
	}*/
}
