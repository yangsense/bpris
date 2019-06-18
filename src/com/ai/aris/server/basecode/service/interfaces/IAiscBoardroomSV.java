package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscBoardroomBean;
import com.ai.aris.server.basecode.model.AiscBoardroomModel;
import com.ai.aris.server.common.bean.QryOperatorAscriptionBean;
import com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room;
import com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.User;
import com.ai.common.domain.ResultDTO;

public interface IAiscBoardroomSV {
	 public ResultDTO queryBoardroomList(AiscBoardroomModel model, ResultDTO resultDTO) throws Exception;
    
	 public AiscBoardroomBean getBoardroom(long id) throws Exception;
	 
	 public String saveBoardroom(AiscBoardroomBean bean) throws Exception;
	 public String deleBoardroom(long id) throws Exception;
	 
	 public List<Map> getBoardroomList() throws Exception;
	 
	 public List getInBoardroom(long roomId) throws Exception;
	 
	 public List getNotBoardroom(long roomId) throws Exception;
	 
	 public void saveDouser(String userCode,String roomId)throws Exception;
	 
	 public Room[] boardroomList(String userCode)throws Exception;
	 
	 public QryOperatorAscriptionBean getOperator(String userCode)throws Exception;
	 
	 public void sysBoartUser(String roomId) throws Exception;
	 
	 public User[] getAuthUserList(long roomId) throws Exception;
	 
	 public void cancelDouser(String userCode,String roomId)throws Exception;
	 
	 public void boardUserAdd(String userCode,String userName,String orgId) throws Exception;
	 
}
