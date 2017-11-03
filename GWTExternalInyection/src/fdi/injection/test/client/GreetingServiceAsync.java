package fdi.injection.test.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import fdi.injection.test.shared.SharedObject;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;

	void getExtendSerializedObject(AsyncCallback<SharedObject> asyncCallback);
}
