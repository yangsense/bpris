package com.ai.aris.server.common.constants;


import com.borland.enterprise.util.StrUtil;

/**
 * 
 * @author wuliangyong
 */
public final class SysManageSqlUtil {

	
	/**
	 * 获取当前操作员拥有的所有菜单
	 * @param operatorCode
	 * @param isOnlyRole
	 * @return
	 */
	public static final String appendOperatorMenuPowerSql(String operatorCode,boolean isOnlyRole,String sysType,boolean isFilterState){
		StringBuffer sql = new StringBuffer();
		sql.append(" and( ");
		//角色的菜单
		sql.append(" menu_id in(select c.menu_id from sys_role a inner join sys_operator2role b" +
				" on a.role_id=b.role_id inner join sys_role2menu c on a.role_id=c.role_id" +
				" inner join sys_menu d on c.menu_id=d.menu_id " +
				" where a.role_state="+SysManageConstants.ROLE_STATE_USER+(isFilterState?" and d.state="+SysManageConstants.MENU_STATE_USER:"") +
						" and( b.operator_code='"+operatorCode+"' )");
		if(!StrUtil.isBlank(sysType)){
			sql.append(" AND d.SYS_TYPE="+sysType);
		}
		
		sql.append(")");
		
//		if(!isOnlyRole){
//			菜单权限
			sql.append(" or menu_id in(select a.menu_id from sys_operator2menu a inner join sys_menu b on a.menu_id=b.menu_id where a.operator_code='"+operatorCode+"'" );
			
			if(!StrUtil.isBlank(sysType)){
				sql.append(" and b.sys_type="+sysType );
			}
			
			sql.append(")");
//		}
		
		sql.append(" ) ");
		
		return sql.toString();
	}
	/**
	 * 获取当前操作员角色权限：
	 * 1.拥有的角色
	 * 2.自己创建的角色
	 * @param operatorCode
	 * @return
	 */
	public static final String appendRolePowerSql(String operatorCode,String fieldName){
		StringBuffer sql = new StringBuffer();
		
		sql.append(" AND " +fieldName).append(" in (");
		sql.append(" select a.role_id from  sys_role a inner join sys_operator2role b on a.role_id = b.role_id");
		sql.append(" where 1=1 AND a.ROLE_STATE="+SysManageConstants.ROLE_STATE_USER).append(" AND B.OPERATOR_CODE='"+operatorCode+"'");
		sql.append(" union");
		
		sql.append(" Select C.ROLE_ID from sys_role C WHERE C.ROLE_STATE="+SysManageConstants.ROLE_STATE_USER+" AND C.CREATE_USER='"+operatorCode+"'");
		
		sql.append(")");
		
		
		return sql.toString();
	}
	
	/**
	 * 比较两个字符串拆分后数组内的内容是否相同
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static final boolean compareSplitString(String str1,String str2){
		
		
		if(str1==null&&str1==null){
			return true;
		}
		
		if(str2!=null&&str1!=null&&str1.trim().equals("")&&str1.trim().equals(str2.trim())){
			return true;
		}
		if(str1==null||str1.trim().equals("")){
			if(str2==null||str2.trim().equals("")){
				return true;
			}
			return false;
		}
		if(str2==null||str2.trim().equals("")){
			if(str1==null||str1.trim().equals("")){
				return true;
			}
			return false;
		}
		String[] array1 = str1.split(",");
		String[] array2 = str2.split(",");
		
		if(array1.length!=array2.length){
			return false;
		}

		boolean rs = true;
		
		for (int i = 0; i < array1.length; i++) {
			String id1 = array1[i];
			boolean exitId1 = false;
			for (int j = 0; j < array2.length; j++) {
				String id2 = array2[j];
				if(id1.equals(id2)){
					exitId1 = true;
					break;
				}
			}
			if(!exitId1){
				rs = exitId1;
				break;
			}
		}
		
		
		return rs;
	}
	public static void main(String[] args) {

		String a = "187,181,193,194,182,185,183,184,2,3,1,188,191,190,192,195,189,196,220,220001,200,323,324";
		
		String b = "187,195,220,181,193,194,182,185,183,184,2,3,1,188,191,190,192,189,196,220001,200,323,324";
		boolean rs = compareSplitString(a, b);
		System.out.println(rs);
	}
	
}
