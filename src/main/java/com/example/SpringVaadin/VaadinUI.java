package com.example.SpringVaadin;

import com.vaadin.addon.responsive.Responsive;
//import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;

//@Theme("springvaadintheme")

@SpringUI
@UIScope
public class VaadinUI extends UI{
	TextField yaz = new TextField("Sehir gir");
	TextArea cıktı = new TextArea("Güncel Hava Durumu");
	Button button1 = new Button("ara");
	Button button2 = new Button("ekle");
	static String sehir;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void init(VaadinRequest request) {
		AbsoluteLayout layout = new AbsoluteLayout();
		layout.setSizeFull();
		setContent(layout);
		//layout.addComponents(yaz,button);
		// The responsive component needs to have 100% width for 
		// width-ranges to work, and similarly for height-ranges
		// Set a style name to target just this instance
		layout.addStyleName("my-responsive-layout");
		// Add the responsive functionality to the layout
		new Responsive(layout);
		layout.addComponent(yaz,"left: 100px; top: 100px;");
		layout.addComponent(cıktı,"left: 500px; top: 100px;");
		layout.addComponent(button1,"left: 100px; top: 150px;");
		layout.addComponent(button2,"left: 200px; top: 150px;");
		
		cıktı.setHeight("400px");
		cıktı.setWidth("300px");
		
		button1.addClickListener(new Button.ClickListener() {
		
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					sehir = yaz.getValue();
					cıktı.setValue(CurrentWheather.XmlParse());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		button2.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;
			int N=0;
			//int i=0;
			int sol=100;
			int yukarı=200;
			String soldan=null;
			String yukardan=null;
			
			public void buttonClick(ClickEvent event) {
				try {
					Button Sehirler[]=new Button[5];
					soldan = Integer.toString(sol)+"px";
					yukardan = Integer.toString(yukarı)+"px";
					Sehirler[N]= new Button(sehir);
					layout.addComponent(Sehirler[N],"left: "+soldan+"; top: "+yukardan+";");
					N++;
					yukarı+=50;
					Sehirler[0].addClickListener(new Button.ClickListener() {
						private static final long serialVersionUID = 1L;
					
						public void buttonClick(ClickEvent event) {
							try {
								sehir=Sehirler[0].getCaption();
								cıktı.setValue(CurrentWheather.XmlParse());
								//i++;
							}
							catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					});
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
	}
}
