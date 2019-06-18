package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisServiceLogValue extends DataStructInterface{

  public final static  String S_ResponseTime = "RESPONSE_TIME";
  public final static  String S_ServiceResponse = "SERVICE_RESPONSE";
  public final static  String S_ServiceBak = "SERVICE_BAK";
  public final static  String S_Total = "TOTAL";
  public final static  String S_ServiceRequest = "SERVICE_REQUEST";
  public final static  String S_RequestTime = "REQUEST_TIME";
  public final static  String S_ServiceStatus = "SERVICE_STATUS";
  public final static  String S_ServiceName = "SERVICE_NAME";
  public final static  String S_ServiceId = "SERVICE_ID";
  public final static  String S_ErrorInfo = "ERROR_INFO";


public Timestamp getResponseTime();

public String getServiceResponse();

public String getServiceBak();

public String getTotal();

public String getServiceRequest();

public Timestamp getRequestTime();

public String getServiceStatus();

public String getServiceName();

public long getServiceId();

public String getErrorInfo();


public  void setResponseTime(Timestamp value);

public  void setServiceResponse(String value);

public  void setServiceBak(String value);

public  void setTotal(String value);

public  void setServiceRequest(String value);

public  void setRequestTime(Timestamp value);

public  void setServiceStatus(String value);

public  void setServiceName(String value);

public  void setServiceId(long value);

public  void setErrorInfo(String value);
}
