package Sample_Socket;

import java.util.HashMap;

public class request {
	
    private String requestType;
    private HashMap<String, String> requestParams;
    private String requestOptionalParam;
    private String protocolVersion;
    private String protocolFormat;
  
	public final String getRequestType() {
	  return requestType;
	}
	
	public final void setRequestType(final String requestType) {
	  this.requestType = requestType;
	}
		
	public final HashMap<String, String> getRequestParams() {
	  return requestParams;
	}
		
	public final void setRequestParams(final HashMap<String, String> requestParams) {
	  this.requestParams = requestParams;
	}
		
	public final String getRequestOptionalParam() {
	  return requestOptionalParam;
	}
		
	public final void setRequestOptionalParam(final String requestOptionalParam) {
	  this.requestOptionalParam = requestOptionalParam;
	}
	
	public final String getProtocolVersion() {
	  return protocolVersion;
	}
		
	public final void setProtocolVersion(final String protocolVersion) {
	  this.protocolVersion = protocolVersion;
	}

	public final String getProtocolFormat() {
	  return protocolFormat;
	}
		
	public final void setProtocolFormat(final String protocolFormat) {
	  this.protocolFormat = protocolFormat;
	}
}
