package fdi.injection.test.client;

import fdi.injection.test.shared.FieldVerifier;
import fdi.injection.test.shared.SharedObject;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.ScriptElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class GWTExternalInyection implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				sendButton.setEnabled(true);
				sendButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String textToServer = nameField.getText();
				if (!FieldVerifier.isValidName(textToServer)) {
					errorLabel.setText("Please enter at least four characters");
					return;
				}

				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				greetingService.greetServer(textToServer, new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Remote Procedure Call - Failure");
						serverResponseLabel.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Remote Procedure Call");
						serverResponseLabel.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(result);
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		
		
		RootPanel aqui=RootPanel.get("nuevainject");
		SimplePanel SP=new SimplePanel();
		SP.getElement().setId("aqui");
		aqui.add(SP);
		VerticalPanel VP=new VerticalPanel();
		SP.setWidget(VP);
		
		
		Element head = Document.get().getElementsByTagName("head").getItem(0);
	    ScriptElement sce = Document.get().createScriptElement();
	    sce.setType("text/javascript");
	    sce.setSrc("externalApp/gwtexternaljstest.nocache.js");
	    head.appendChild(sce);
		
		
//		ScriptInjector.fromUrl("externalApp/gwtexternaljstest.nocache.js").setCallback(
//			     new Callback<Void, Exception>() {
//
//					@Override
//					public void onFailure(Exception reason) {
//						 Window.alert("Script load failed.");
//						
//					}
//					@Override
//					public void onSuccess(Void result) {
//						Window.alert("Script load success.");
//						alert("Correcto");
//					}
//			     }).inject();
		
	    Button BB = new Button("dale");
	    BB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				alert("aqui",1984);
				
			}
		});
	    aqui.add(BB);
	    
	    greetingService.getExtendSerializedObject(new AsyncCallback<SharedObject>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(SharedObject result) {
				setVariableBase(result,1984);
				getVariableBase(1984);
			}
		});
	    
	}

	public native void alert(String string, int doci) /*-{
		window.parent.setData([string, doci]);
		
	}-*/;
	
	
	public static native SharedObject getVariableBase(int i) /*-{
	$wnd.daletmp = '$wnd.dale = $wnd.DocExpand'+i;
	eval($wnd.daletmp)
	 console.log($wnd.dale);
	  return  $wnd.dale;	  
	}-*/;
	
	
	public static native void setVariableBase(SharedObject DocumentoExpandido, int i) /*-{
		var tmp=DocumentoExpandido;
		console.log(tmp);
		$wnd.dale = '$wnd.DocExpand'+i;
		$wnd.str = '$wnd.DocExpand'+i +' = DocumentoExpandido_0_g$';
		console.log($wnd.str);
		eval($wnd.str)
	  console.log($wnd.dale);
	}-*/;
	
}
